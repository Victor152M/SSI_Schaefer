package com.ssn.practica.work.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ssn.practica.work.utils.WithSessionAndTransaction;

public class DatabaseOperations {

	public void addArticle(String name) {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				Article a = new Article(name);
				session.save(a);
			}
		}.run();

	}

	public void addStore(String name) {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				Store store = getStoreByName(name, session);

				if (store != null) {
					throw new RuntimeException("Magazinul exista deja: " + name);
				}

				Store a = new Store(name);
				session.save(a);
			}
		}.run();

	}

	public void addPrice(String storeName, String articleName, int price) {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				Store store = getStoreByName(storeName, session);

				if (store == null) {
					throw new RuntimeException("Magazin inexistent: " + storeName);
				}

				Article article = getArticleByName(articleName, session);
				if (article == null) {
					throw new RuntimeException("Articol inexistent: " + articleName);
				}

				Price p = new Price(price, store, article);
				session.save(p);

			}
		}.run();
	}

	private Store getStoreByName(String name, Session session) {
		Query<Store> query = session.createQuery("from Store where name = :name", Store.class);
		query.setParameter("name", name);
		Store store = query.uniqueResult();
		return store;
	}

	private Article getArticleByName(String articleName, Session session) {
		Query<Article> query = session.createQuery("from Article where name = :name", Article.class);
		query.setParameter("name", articleName);
		Article article = query.uniqueResult();
		return article;
	}

}
