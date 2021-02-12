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
	
	@GetMapping(path = "/api/deadlock/{id}")
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
	public void fetchAndSave(@PathVariable int id) {
		long start = System.currentTimeMillis();
		System.out.println("Start Time: " + start);
		try {
			Cache data = cacheService.fetchCache(id);
			
			try {
				Thread.sleep(70000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			data.setName("Name after fetch");
			// Making a trasaction to wait for some seconds to that I can execute another transaction to test concurrency
			cacheService.saveCache(data);
			/*
			 * try { Thread.sleep(10000); } catch (InterruptedException e) {
			 * e.printStackTrace(); }
			 */		
			cacheService.release();
		}catch(Exception ex) {
			System.out.println("End Time w/ exception: " + (System.currentTimeMillis() - start));
			ex.printStackTrace();
		}
		System.out.println("End Time: " + (System.currentTimeMillis() - start));
		//return data.toString();
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

		Cache data = cacheService.fetchCache(id);
		data.setName(name);

		// Making a trasaction to wait for some seconds to that I can execute another transaction to test concurrency
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		cacheService.saveCache(data);

		Cache updatedData = cacheService.fetchCache(id);
		return updatedData.toString();
	}
}
