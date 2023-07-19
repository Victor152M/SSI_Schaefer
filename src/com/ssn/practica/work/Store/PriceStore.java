package com.ssn.practica.work.Store;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class PriceStore {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	private int price;

	@ManyToOne
	@JoinColumn
	private ArticleStore article;

	@ManyToOne
	private Store store = new Store();
	// I suspect you need a List but there is a problem in RunStore

	public PriceStore() {
	}

	public PriceStore(int price, ArticleStore article, Store store) {
		super();
		this.price = price;
		this.article = article;
		this.store = store;
	}

	public Long getId() {
		return id;
	}

	public ArticleStore getArticle() {
		return article;
	}

	public void setArticle(ArticleStore article) {
		this.article = article;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
