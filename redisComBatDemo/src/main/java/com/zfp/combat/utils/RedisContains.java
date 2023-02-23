package com.zfp.combat.utils;

public interface RedisContains {

    String shopKey = "shop:cache:";
    long  shopCacheTime = 30;
    long  shopCachePenetrateTime=2;
    String shopLockKey="shop_lock_";

}
