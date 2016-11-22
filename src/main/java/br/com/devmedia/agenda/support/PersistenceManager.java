package br.com.devmedia.agenda.support;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum PersistenceManager {

	INSTANCE;

	private EntityManagerFactory entityManagerFactory;

	private EntityManager entityManager;

	private PersistenceManager() {

		entityManagerFactory = Persistence.createEntityManagerFactory("agendaPU");

		entityManager = entityManagerFactory.createEntityManager();
	}

	public EntityManager getEntityManager() {

		return entityManager;
	}

	public void close() {

		entityManagerFactory.close();
	}
}
