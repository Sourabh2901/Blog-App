package com.sr.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;


@Component
public class AppCache {
	
//	@Autowired
//	private ExternalApiRepo externalApiRepo;
	
	public Map<String, String> cache;
	
//	@PostConstruct
//	public void init() {
//		cache = new HashMap<>();
//		List<ExternalApiEntity> list = externalApiRepo.findAll();
//		
//		for(ExternalApiEntity obj : list) {
//			cache.put(obj.getKey(), obj.getValue());
//		}
//	}
}
