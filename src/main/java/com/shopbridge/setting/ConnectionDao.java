/**
 * 
 */
package com.shopbridge.setting;

import java.io.Serializable;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ConnectionDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void persist(Object entity) {
		getSession().persist(entity);
	}

	public void delete(Object entity) {
		getSession().delete(entity);
	}

	public <T> T getObjectById(Class<T> entity, Serializable id) {
		return (T) getSession().get(entity, id);
	}

	public void saveOrUpdate(Object entity) {
		getSession().saveOrUpdate(entity);
	}
	
	protected DataSource getDataSource(){
		return dataSource;
	}
	
	
}
