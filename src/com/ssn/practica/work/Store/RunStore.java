package com.ssn.practica.work.Store;

import org.hibernate.Session;

import com.ssn.practica.work.utils.WithSessionAndTransaction;

public class RunStore {

	private BasicOperations operations = new BasicOperations();
	private DatabaseOperations dbOperations = new DatabaseOperations();

	public static void main(String[] args) throws Exception {
		RunStore runner = new RunStore();
		runner.run();
	}

	private void run() {
		boolean running = true;
		while (running) {
			displayMenu();
			int select = operations.intGetter("Insert a number: ");
			switch (select) {
			case 1:
				addArticle();
				break;
			case 2:
				addStore();
				break;
			case 3:
				addPrice();
				break;
			case 4:
				showStatistics();
				break;
			case 0:
				running = false;
				break;
			}
		}
	}

	private void showStatistics() {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				dbOperations.showArticles(session);
			}
		}.run();
	}

	private void addPrice() {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				// gets a store name + checks if it exists
				String storeName = operations.stringGetter("Insert a store name: ");
				Store store = dbOperations.getStoreByName(storeName, session);
				if (store == null) {
					throw new RuntimeException("Store named " + storeName + " does not exist");
				}

				// gets an article name + checks if it exists
				String articleName = operations.stringGetter("Insert the article name: ");
				ArticleStore article = dbOperations.getArticleByName(articleName, session);
				if (article == null) {
					throw new RuntimeException("Articol inexistent: " + articleName);
				}

				int valuePrice = operations.intGetter("Insert the price: ");
				PriceStore price = new PriceStore(valuePrice, article, store);
				session.save(price);
			}
		}.run();

	}

	private void addStore() {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				String name = operations.stringGetter("Insert an store name: ");
				Store store = new Store(name);
				session.save(store);
			}
		}.run();
	}

	private void addArticle() {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				String name = operations.stringGetter("Insert an article name: ");
				ArticleStore article = new ArticleStore(name);
				session.save(article);
			}
		}.run();
	}

	private void displayMenu() {
		System.out.println("1. Adaugare articol");
		System.out.println("2. Adaugare magazin");
		System.out.println("3. Adaugare pret");
		System.out.println("4. Afisare statistici");
		System.out.println("0. Iesire");
	}

}
