package com.example.bankaccount.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JpaUtils {

	private static final String PERSIST_NAME_UNIT = "bankaccount";
	
	public static EntityManagerFactory entityManagerFactory;
	
	private static final Log LOG = LogFactory.getLog(JpaUtils.class);

	static {
		init();
	}
	
	private static void init() {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSIST_NAME_UNIT);
		} catch (Throwable ex) {
			LOG.error("Initial SessionFactory creation failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	

}