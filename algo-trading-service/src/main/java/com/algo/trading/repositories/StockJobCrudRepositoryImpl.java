package com.algo.trading.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import com.algo.trading.entities.StockJob;

public class StockJobCrudRepositoryImpl implements StockJobCrudRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public StockJob findStockJobById(final String id) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StockJob> criteria = builder.createQuery(StockJob.class);
		Root<StockJob> stockJob = criteria.from(StockJob.class);
		criteria.select(stockJob);
		criteria.where(criteriaBuilder.equal(stockJob.get("symbol"), id));
		TypedQuery<StockJob> query = entityManager.createQuery(criteria);
		return query.getSingleResult();
	}

	@Override
	public boolean updateStockJobStatus(final String id, final String state) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<StockJob> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(StockJob.class);
		Root<StockJob> stockJobRoot = criteriaUpdate.from(StockJob.class);
		criteriaUpdate.set(stockJobRoot.get("status"), state)
				.where(criteriaBuilder.equal(stockJobRoot.get("symbol"), id));
		int result = entityManager.createQuery(criteriaUpdate).executeUpdate();
		if (result == 1) {
			return true;
		}
		return false;
	}

}
