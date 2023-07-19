package com.ssn.practica.work.Store;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class ArticleStore {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;

	private String name;

	/*
	 * @ManyToMany(mappedBy = "courses") private List<Trainee> trainees = new
	 * ArrayList<>();
	 * 
	 * @OneToMany(mappedBy = "course") private List<Evaluation> evaluations = new
	 * ArrayList<>();
	 */

	@OneToMany
	private List<PriceStore> prices;

	@OneToMany
	private List<Store> stores = new ArrayList<>();

	public List<PriceStore> getPrices() {
		return prices;
	}

	public void setPrices(List<PriceStore> prices) {
		this.prices = prices;
	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	public ArticleStore() {

	}

	public ArticleStore(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
