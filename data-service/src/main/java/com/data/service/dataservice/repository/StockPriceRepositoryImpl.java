package com.data.service.dataservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.data.service.dataservice.entity.Stocks;

public class StockPriceRepositoryImpl implements StockPriceRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Stocks> findPriceBetweenRange(final Double upparPrice, final Double lowerPrice) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Stocks> criteria = builder.createQuery(Stocks.class);
		Root<Stocks> order = criteria.from(Stocks.class);
		criteria.select(order);
		criteria.where(criteriaBuilder.between(order.get("price"), upparPrice, lowerPrice));
		criteria.orderBy(criteriaBuilder.asc(order.get("price")));

		TypedQuery<Stocks> query = entityManager.createQuery(criteria);
		final List<Stocks> stocks = query.getResultList();
		return stocks != null ? stocks : null;
	}

}
