package com.example.bankaccount.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.example.bankaccount.utils.JpaUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ACCOUNT")
public class Account {

	private static final Log LOGGER = LogFactory.getLog(Account.class);
	
	@Id
	@Column(name = "ID")
    private long id;
	
	@Column(name = "ACCOUNT_NUMBER", nullable = false)
	private long accountNumber;
	
	@Column(name = "BALANCE", nullable = false)
    private BigDecimal balance;
	
	public static Account findAccount (long accountNumber) {
		EntityManager entityManager = JpaUtils.getEntityManagerFactory().createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
		Root<Account> root = criteriaQuery.from(Account.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.<Long>get("accountNumber"), accountNumber));
		Account account = null;
		try {
			account = entityManager.createQuery(criteriaQuery).getSingleResult();
		} catch (NoResultException nre) {
			LOGGER.error("No account found for account number "+ accountNumber);
		}
		return account;
	}
	
	public void update() {
		EntityManager entityManager = JpaUtils.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(this);
		entityManager.flush();
		entityManager.getTransaction().commit();
	}
	
}
