package com.ssn.practica.work.Store;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class DatabaseOperations {

	Store getStoreByName(String name, Session session) {
		Query<Store> query = session.createQuery("from Store where name = :name", Store.class);
		query.setParameter("name", name);
		Store store = query.uniqueResult();
		return store;
	}

	ArticleStore getArticleByName(String name, Session session) {
		Query<ArticleStore> query = session.createQuery("from ArticleStore where name = :name", ArticleStore.class);
		query.setParameter("name", name);
		ArticleStore article = query.uniqueResult();
		return article;
	}

	void showArticles(Session session) {
		Query<ArticleStore> query = session.createQuery("FROM ArticleStore", ArticleStore.class);
		List<ArticleStore> results = query.getResultList();
		for (ArticleStore article : results) {
			System.out.println(article.getName());
		}
	}

	Integer[] getArticleIds(Session session) {
		Query<ArticleStore> query = session.createQuery("SELECT price FROM ArticleStore", ArticleStore.class);
		List<ArticleStore> results = query.getResultList();
		int i = 0;
		Integer[] ids = new Integer[100];
		for (ArticleStore article : results) {
			ids[i] = article.getId();
			i++;
		}
		return ids;
	}

	/*
	 * void showTable(Session session) { Query<ArticleStore> query =
	 * session.createQuery("FROM ArticleStore");
	 * System.out.println(query.toString()); }
	 * 
	 * String lowestPrice(Session session) { Query query =
	 * session.createQuery("SELECT MIN(Price) AS " +
	 * "a join a.prices price where a.id=:id order by price.price");
	 * query.setParameter("id", article.id); query.setMaxResults(1); return (Price)
	 * query.uniqueResult(); }
	 */
}
