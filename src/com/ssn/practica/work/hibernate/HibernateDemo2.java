package com.ssn.practica.work.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateDemo2 {
	public static void main(String[] args) throws Exception {
		HibernateDemo2 demo = new HibernateDemo2();
		demo.run();
	}

	private EntityManagerFactory sessionFactory;

	private void run() throws Exception {
		setUp();
		saveEntities();
//		queryEntities();
	}

	private void queryEntities() {
		EntityManager entityManager = sessionFactory.createEntityManager();
		List<Event> result = entityManager.createQuery("from Event", Event.class).getResultList();
		for (Event event : result) {
			System.out.println("Event (" + event.getDate() + ") : " + event.getTitle());
		}
		entityManager.close();
	}

	private void saveEntities() {
		EntityManager entityManager = sessionFactory.createEntityManager();

		entityManager.getTransaction().begin();

		Trainee trainee1 = new Trainee("Ghita", 20);
		entityManager.persist(trainee1);
		Trainee trainee2 = new Trainee("Ion", 21);
		entityManager.persist(trainee2);

		Course course1 = new Course("Romana");
		entityManager.persist(course1);
		Course course2 = new Course("Matematica");
		entityManager.persist(course2);

		trainee1.getCourses().add(course2);
		trainee2.getCourses().add(course1);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	protected void setUp() throws Exception {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
		}
	}
}
