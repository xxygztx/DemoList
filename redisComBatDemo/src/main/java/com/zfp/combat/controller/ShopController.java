package com.zfp.combat.controller;


import com.zfp.combat.entity.Shop;
import com.zfp.combat.service.ShopService;
import com.zfp.common.system.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Result queryShopListById(@RequestParam("shopId") String shopId){
        Shop shop = shopService.getById(shopId);
        return Result.success(shop);
    }

}

