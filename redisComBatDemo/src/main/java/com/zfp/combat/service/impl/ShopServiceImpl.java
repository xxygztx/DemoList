package com.zfp.combat.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zfp.combat.entity.Shop;
import com.zfp.combat.mapper.ShopMapper;
import com.zfp.combat.service.ShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfp.combat.utils.RedisContains;
import com.zfp.common.system.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zfp
 * @since 2023-02-19
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 我们要解决几个问题， 缓存更新问题，缓存穿透问题，缓存雪崩，缓存击穿问题。
     *
     * @param shopId
     * @return
     */
    @Override
    public Result queryShopListById(Long shopId) {

        Result result = solveCachePenetrate(shopId);
        return result;
    }

    /**
     * 解决缓存穿透问题（使用缓存null值），已经使用主动更新，超时兜底作为解决方案
     *
     * @param shopId
     * @return
     */
    public Result solveCachePenetrate(Long shopId) {
        String key = RedisContains.shopKey + shopId;
        //1.到redis中查找,查找到直接返回
        Map entries = redisTemplate.opsForHash().entries(key);
        if (entries.get("null") != null) {
            return Result.fail("没有此商品");
        }
        if (!entries.isEmpty()) {
            Shop shop = BeanUtil.fillBeanWithMap(entries, new Shop(), false);
            return Result.success(shop);
        }
        //2.redis中没有，到mysql中查找
        Shop shop = this.getById(shopId);
        //3.在redis中没有找到直接返回
        if (Objects.isNull(shop)) {
            //当缓存和数据库都没找到时，发生了缓存穿透，使用空对象解决缓存穿透(因为hash不能存null所以我模拟一个效果相似)
            HashMap hashMap = new HashMap();
            hashMap.put("null", "null");
            redisTemplate.opsForHash().putAll(key, hashMap);
            //设置一个较短的时间，防止缓存穿透
            redisTemplate.expire(key, 2, TimeUnit.MINUTES);
            return Result.fail("没有此商品");
        }
        //4.找到数据更新缓存,设置过期时间作为兜底
        redisTemplate.opsForHash().putAll(key, BeanUtil.beanToMap(shop));
        redisTemplate.expire(key, RedisContains.shopCacheTime, TimeUnit.MINUTES);
        //5.返回数据
        return Result.success(shop);
    }

    /**
     * 解决缓存击穿问题，需要明确：缓存击穿就是热点key问题，只不过他失效了，所以他在数据库中是一定存在的。
     * 分析问题：当热点key失效的瞬间，有大量的请求直接打在数据库上，我们需要使这么的请求不能直接打在数据库上，
     * 在redis查看不到之后加锁，到重建缓存之后释放锁，这是一种解决方案，但是这又一个很严重的弊端，在失效和重建的过程中，
     * 其他线程需要等待，用户体验不好，
     *
     * @param shopId
     * @return
     */
    public Result solveCacheBreakdownByLock(Long shopId) {
        String key = RedisContains.shopKey + shopId;
        Map entries = redisTemplate.opsForHash().entries(key);
        if (entries.get("null") != null) {
            return Result.fail("没有此商品");
        }
        if (!entries.isEmpty()) {
            Shop shop = BeanUtil.fillBeanWithMap(entries, new Shop(), false);
            return Result.success(shop);
        }
        //这里使用synchronized是不符合要求，我们的synchronized不能判断是否获取锁，而且使用jvm内部锁，我们在集群的情况下就会失效。
        //我们在这里可以考虑使用redis的setNx,setEx命令实现分布式锁，来解决这个问题。
        Shop shop = this.getById(shopId);
        //使用Redission的分布式锁来解决。

        if (Objects.isNull(shop)) {
            HashMap hashMap = new HashMap();
            hashMap.put("null", "null");
            redisTemplate.opsForHash().putAll(key, hashMap);
            redisTemplate.expire(key, 2, TimeUnit.MINUTES);
            return Result.fail("没有此商品");
        }
        redisTemplate.opsForHash().putAll(key, BeanUtil.beanToMap(shop));
        redisTemplate.expire(key, RedisContains.shopCacheTime, TimeUnit.MINUTES);
        return Result.success(shop);


    }

    /**
     * 修改商品的时候直接删除缓存
     * 我们要尽可能要保证 修改mysql的数据和删除redis的数据的一致性。
     *
     * @param shop
     * @return
     */
    @Override
    public Result updateShopById(Shop shop) {
        boolean result = this.updateById(shop);
        if (!result) {
            return Result.fail("商品信息修改失败");
        }
        Boolean delete = redisTemplate.delete(RedisContains.shopKey + shop.getId());
//        下面这个判断有很大的问题，如何活我们的缓存失效，我们的delete就为false，就会给前端报错是错误的这种写法
//        if(!delete){
//            return Result.fail("缓存删除失败");
//        }
        return Result.success();
    }
}
