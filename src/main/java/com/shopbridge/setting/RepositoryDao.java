/**
 * 
 */
package com.shopbridge.setting;

import java.io.Serializable;

public interface RepositoryDao {

	public void addnew(Object object) throws Exception;
	
	public void update(Object object) throws Exception;
	
	public void deleteObj(Object object) throws Exception;
	
	public <OBJ> OBJ findById(Class<OBJ> entity, Serializable id);
	
	public <OBJ> OBJ findByKey(Class<OBJ> entity, String keyFirst, Object entityFirst, String keySecond, Object entitySecond);
	
	public <OBJ> OBJ findBySingleKey(Class<OBJ> entity,String param,Object obj) ;
	
	 public <OBJ> OBJ list(Class<OBJ> entity, String keyFirst, String order, Object obj) throws Exception;
	
	
}
