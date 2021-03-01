/**
 * 
 */
package com.shopbridge.settingDao;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.shopbridge.setting.ConnectionDao;
import com.shopbridge.setting.RepositoryDao;


@Transactional
@Repository("RepositoryDao")
public class RepositoryDaoImpl extends ConnectionDao implements RepositoryDao {

	public void addnew(Object object) throws Exception {
		persist(object);
	};

	public void update(Object object) throws Exception {
		saveOrUpdate(object);
	};

	public void deleteObj(Object object) {
		delete(object);
	}

	@Override
	public <OBJ> OBJ findById(Class<OBJ> entity, Serializable id) {
		return (OBJ) getSession().get(entity, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <OBJ> OBJ findByKey(Class<OBJ> entity, String keyFirst, Object entityFirst, String keySecond,
			Object entitySecond) {
		Criteria criteria = getSession().createCriteria(entity).add(Restrictions.eq(keyFirst, entityFirst))
				.add(Restrictions.eq(keySecond, entitySecond));
		return (OBJ) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <OBJ> OBJ findBySingleKey(Class<OBJ> entity, String param, Object obj) {
		Criteria criteria = getSession().createCriteria(entity).add(Restrictions.eq(param, obj));
		return (OBJ) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <OBJ> OBJ list(Class<OBJ> entity, String keyFirst, String order, Object obj) throws Exception {
		Criteria criteria = getSession().createCriteria(entity).add(Restrictions.eq(keyFirst, obj))
				.addOrder(Order.desc(order));
		return (OBJ) criteria.list();
	}

}
