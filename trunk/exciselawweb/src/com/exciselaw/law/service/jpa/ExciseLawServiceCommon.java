package com.exciselaw.law.service.jpa;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/*
* 
* @author Chatchai Pimtun
*/
@Repository
@Transactional
public class ExciseLawServiceCommon {
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void delete(Session session,Object persistentInstance) {
		// TODO Auto-generated method stub
		session.delete(persistentInstance);	
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long save(Session session,Object persistentInstance) {
		// TODO Auto-generated method stub 
		 Long returnId  = null; 
				Object obj = session.save(persistentInstance);
			
				if(obj!=null){
					returnId =(Long) obj;
				} 
			return returnId;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int update(Session session,Object transientInstance) {
		// TODO Auto-generated method stub
		int canUpdate = 0;
		try {
			session.update(transientInstance);
			canUpdate =1; 
		} catch (Exception e) {
			// TODO: handle exception
		}
			
			
		return canUpdate;
	}
	@Transactional(readOnly = true)
	public Object findById(Session session, String classType, Long id) {
		Object obj = null;
		try {
			obj = session.get(classType, id);
			return obj;
		} catch (RuntimeException re) {
			throw re;
		}
	}

}
