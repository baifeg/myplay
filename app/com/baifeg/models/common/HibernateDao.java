package com.baifeg.models.common;

import javax.persistence.EntityManager;

import play.db.jpa.JPA;

public class HibernateDao<T>
{
	private final EntityManager em;
	private final Class<T> clszz;

	public HibernateDao(Class<T> clszz)
	{
		this.clszz = clszz;
		em = JPA.em();
	}

	public T findById(Object id)
	{
		return em.find(clszz, id);
	}
}
