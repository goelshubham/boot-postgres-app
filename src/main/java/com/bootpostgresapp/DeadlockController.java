package com.bootpostgresapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeadlockController {
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private DataService dataService;

	@GetMapping(path = "/api/process/{id}")
	@Transactional
	public void process(@PathVariable int id) {		
		deleteProcess(id);
		createProcess(id);
	}
	
	//@Transactional
	public void deleteProcess(int id) {
		System.out.println("********* DELETE PROCESS***************");
		Cache cache = cacheService.fetchCache(id);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Data data = dataService.fetchData(id);

		cache.setName("Set cache after fetch");
		data.setName("Set data after fetch");

		cacheService.saveCache(cache);
		dataService.saveData(data);
	}

	//@Transactional
	public void createProcess(int id) {
		System.out.println("********* CREATE PROCESS***************");
		Data data = dataService.fetchData(id);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Cache cache = cacheService.fetchCache(id);

		cache.setName("Set cache after fetch");
		data.setName("Set data after fetch");
		dataService.saveData(data);
		cacheService.saveCache(cache);
	}
}
