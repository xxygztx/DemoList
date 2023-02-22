package com.zfp.combat.controller;


import com.zfp.combat.entity.Shop;
import com.zfp.combat.service.ShopService;
import com.zfp.common.system.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zfp
 * @since 2023-02-19
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private ShopService shopService;

    @GetMapping("/queryShopList")
    public Result queryShopList(){
        List<Shop> list = shopService.list();
        return Result.success(list);
    }

    @GetMapping("/queryShopListById")
    public Result queryShopListById(@RequestParam("shopId") Long shopId){
       return shopService.queryShopListById(shopId);
    }

    @PutMapping("/updateShopById")
    public Result updateShopById(@RequestBody Shop shop){
       return shopService.updateShopById(shop);
    }

}

