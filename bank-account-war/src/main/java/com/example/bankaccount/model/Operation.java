package com.example.bankaccount.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
@Table(name = "OPERATION")
public class Operation {
	

	private static final String SEQUENCE_STYLE_GENERATOR = "org.hibernate.id.enhanced.SequenceStyleGenerator";
	private static final String SEQUENCE_NAME_PARAM_NAME = "sequence_name";
	private static final String OPERATION_SEQ = "OPERATION_SEQ";
	private static final String INCREMENT_SIZE_PARAM_NAME = "increment_size";
	private static final String INCREMENT_SIZE_VALUE_1 = "1";
	
	public static final String DEPOSIT_TYPE = "DEPOSIT";
	
	public static final String WITHDRAWAL_TYPE = "WITHDRAWAL";
	
	@Id
	@GeneratedValue(generator = "operation-generator")
	@GenericGenerator(name = "operation-generator", strategy = SEQUENCE_STYLE_GENERATOR, parameters = {
			@Parameter(name = SEQUENCE_NAME_PARAM_NAME, value = OPERATION_SEQ),
			@Parameter(name = INCREMENT_SIZE_PARAM_NAME, value = INCREMENT_SIZE_VALUE_1)})
	@Column(name = "ID")
    private long id;
	
	@Column(name = "ACCOUNT_ID", nullable = false)
	private long accountId;
	
	@Column(name = "TYPE", nullable = false)
	private String type;
	
	@Column(name = "AMOUNT", nullable = false)
	private BigDecimal amount;
	
	public static List<Operation> findAllByAccountId(long accountId) {
		EntityManager entityManager = JpaUtils.getEntityManagerFactory().createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Operation> criteriaQuery = criteriaBuilder.createQuery(Operation.class);
		Root<Operation> root = criteriaQuery.from(Operation.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.<Long>get("accountId"), accountId));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
	public void create() {
		EntityManager entityManager = JpaUtils.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(this);
		entityManager.flush();
		entityManager.getTransaction().commit();
	}
}
