package com.bootpostgresapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {
	
	@Autowired
	private DataRepository repository;

	public Data fetchData(int id) {
		return repository.getById(id).get();
	}

	public void saveData(Data Data) {
		repository.save(Data);
	}
	
	public void release() {
		repository.release();
	}
}
