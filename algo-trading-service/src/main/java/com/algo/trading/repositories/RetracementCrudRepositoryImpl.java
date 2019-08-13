package com.algo.trading.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.algo.trading.entities.Retracement;

public class RetracementCrudRepositoryImpl implements RetracementCrudRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Retracement findRetracementById(final String id) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Retracement> criteria = builder.createQuery(Retracement.class);
		Root<Retracement> retracement = criteria.from(Retracement.class);
		criteria.select(retracement);
		criteria.where(criteriaBuilder.equal(retracement.get("symbol"), id));
		TypedQuery<Retracement> query = entityManager.createQuery(criteria);
		return query.getSingleResult();
	}
}
