package com.ssn.practica.work.Store;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Store {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	private String name;

	@OneToMany
	private List<ArticleStore> articles = new ArrayList<>();

	public List<ArticleStore> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleStore> articles) {
		this.articles = articles;
	}

	public Store(String name) {
		super();
		this.name = name;
	}

	public Store() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
