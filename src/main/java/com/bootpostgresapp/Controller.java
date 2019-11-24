package com.bootpostgresapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@Autowired
	private CacheService cacheService;
	
	@GetMapping(path = "/api/fetch/{id}")
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
	public String fetch(@PathVariable int id) {
		Cache data = cacheService.fetchData(id);
		return data.toString();
	}
	
	/**
	 * Used @Transactional on this method because I want have multiple operations as part of one single transaction.
	 * @param id
	 * @param name
	 * @return
	 */
	@PostMapping("/api/save/{id}/{name}")
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
	public String save(@PathVariable int id, @PathVariable String name) {

		Cache data = cacheService.fetchData(id);
		data.setName(name);

		// Making a trasaction to wait for some seconds to that I can execute another transaction to test concurrency
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		cacheService.saveData(data);

		Cache updatedData = cacheService.fetchData(id);
		return updatedData.toString();
	}
}
