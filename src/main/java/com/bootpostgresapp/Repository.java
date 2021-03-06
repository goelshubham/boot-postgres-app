package com.bootpostgresapp;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

/**
 * Implemented another repostory version with entity manager.
 * javax.persistence.lock.timeout is working here. Try making this value than
 * the thread wait time and then it will fail.
 * 
 * @author goels10
 *
 */
@Component
public class Repository {

	@PersistenceContext
	private EntityManager em;
	/**
	 * Lock is acquired when Select query and released at the end of Transaction (marked by @Trasactional)
	 * PESSIMISTIC_WRITE makes the select as Select for Update
	 * OPTIMISTIC lock makes select as Select for share
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Optional<Cache> getById(int id){
		List<Cache> list = em.createQuery("select c from Cache c where c.id = ?1")
							.setParameter(1, id)
							.setHint("javax.persistence.lock.timeout", 30000)
							.setLockMode(LockModeType.PESSIMISTIC_WRITE)
							.getResultList();

		if(list == null || list.isEmpty()) {
			Cache newCache = new Cache();
			newCache.id = 1;
			newCache.name = "name during fetch";
			em.persist(newCache);
			
			list = em.createQuery("select c from Cache c where c.id = ?1")
					.setParameter(1, id)
					.setHint("javax.persistence.lock.timeout", 30000)
					.setLockMode(LockModeType.PESSIMISTIC_WRITE)
					.getResultList();
		}
		return Optional.ofNullable(list.get(0));
	}
	
	public void save(Cache cache) {
		cache = em.find(Cache.class, cache.getId());
		if(cache == null) {
			em.persist(cache);
		}else {
			cache = em.merge(cache);
		}
		em.flush();
	}
	
    public void release() {
        em.clear();
    }
}
