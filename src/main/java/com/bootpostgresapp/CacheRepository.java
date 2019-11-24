package com.bootpostgresapp;

import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Implemented this Repository implementation but javax.persistence.lock.timeout doesn't seem to be working here
 * @author goels10
 *
 */
@Repository
public interface CacheRepository extends CrudRepository<Cache, Integer> {
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout",value = "50000")})
	public Optional<Cache> findById(int id);
	
}
