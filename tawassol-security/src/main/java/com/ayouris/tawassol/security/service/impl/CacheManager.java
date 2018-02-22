package com.ayouris.tawassol.security.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.util.CacheConstants;

@Service
public class CacheManager {

    @Caching(evict = { @CacheEvict(value = CacheConstants.COUNTRY_CACHE, allEntries = true),/*
            @CacheEvict(value = CacheConstants.MEMBER_CACHE, allEntries = true)*/ })
    public void clearCacheData() {
    }
}