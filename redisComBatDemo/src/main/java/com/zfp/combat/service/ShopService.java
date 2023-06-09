package com.zfp.combat.service;

import com.zfp.combat.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zfp.common.system.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zfp
 * @since 2023-02-19
 */
public interface ShopService extends IService<Shop> {

    Result queryShopListById(Long shopId);

    Result updateShopById(Shop shop);
}
