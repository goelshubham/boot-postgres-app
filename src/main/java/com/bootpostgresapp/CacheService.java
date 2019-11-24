package com.bootpostgresapp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
public class CacheService {

	/*
	 * private final TransactionTemplate transactionTemplate;
	 * 
	 * 
	 * public CacheService(PlatformTransactionManager transactionManager) {
	 * Assert.notNull(transactionManager,
	 * "The 'transactionManager' argument must not be null.");
	 * this.transactionTemplate = new TransactionTemplate(transactionManager);
	 * 
	 * // the transaction settings can be set here explicitly if so desired
	 * this.transactionTemplate.setIsolationLevel(TransactionDefinition.
	 * ISOLATION_SERIALIZABLE); this.transactionTemplate.setTimeout(30); // 30
	 * seconds // and so forth... }
	 */
	
	/*
	 * @Autowired private CacheRepository cacheRepository;
	 */
	
	@Autowired
	private Repository repository;

	public Cache fetchData(int id) {
		Optional<Cache> op = Optional.empty();
		return repository.getById(id).get();
	}

	public void saveData(Cache cache) {
		repository.save(cache);
	}
}
