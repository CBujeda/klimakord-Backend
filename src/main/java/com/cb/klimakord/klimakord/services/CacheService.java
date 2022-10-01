package com.cb.klimakord.klimakord.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class CacheService {

	private Map<String, Object> cache;

	public CacheService() {
		this.cache = new HashMap<>();
	}
	
	public void saveCache(String x, String y, String z,String a, String key, Object data) {
		String cacheKey = String.format("%s-%s-%s-%s-%s", x, y, z, a, key);
		this.cache.put(cacheKey, data);
	}
	
	public boolean existe(String x, String y, String z,String a, String key) {
		String cacheKey = String.format("%s-%s-%s-%s-%s", x, y, z, a, key);
		return this.cache.containsKey(cacheKey);
		
	}
	
	public Object getCahe(String x, String y, String z,String a, String key) {
		String cacheKey = String.format("%s-%s-%s-%s-%s", x, y, z, a, key);
		return this.cache.get(cacheKey);
		
	}
}
