package com.data.service.dataservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.data.service.dataservice.entity.PreOpenStock;

public class StockPriceRepositoryImpl implements StockPriceRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<PreOpenStock> findPriceBetweenRange(final Double upparPrice, final Double lowerPrice) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PreOpenStock> criteria = builder.createQuery(PreOpenStock.class);
		Root<PreOpenStock> order = criteria.from(PreOpenStock.class);
		criteria.select(order);
		criteria.where(criteriaBuilder.between(order.get("price"), upparPrice, lowerPrice));
		criteria.orderBy(criteriaBuilder.asc(order.get("price")));

		TypedQuery<PreOpenStock> query = entityManager.createQuery(criteria);
		final List<PreOpenStock> stocks = query.getResultList();
		return stocks != null ? stocks : null;
	}

}
