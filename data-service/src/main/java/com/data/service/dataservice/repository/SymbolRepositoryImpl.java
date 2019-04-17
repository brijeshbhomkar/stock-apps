package com.data.service.dataservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.data.service.dataservice.entity.Symbol;

public class SymbolRepositoryImpl implements StockSymbolRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Symbol findSymbol(final String id) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Symbol> criteria = builder.createQuery(Symbol.class);
		Root<Symbol> symbol = criteria.from(Symbol.class);
		criteria.select(symbol);
		criteria.where(criteriaBuilder.equal(symbol.get("symbolId"), id));
		TypedQuery<Symbol> query = entityManager.createQuery(criteria);
		return query.getSingleResult();

	}

}
