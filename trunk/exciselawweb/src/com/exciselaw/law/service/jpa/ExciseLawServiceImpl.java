package com.exciselaw.law.service.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exciselaw.law.domain.ConsultDocFilter;
import com.exciselaw.law.domain.MsLegalitem;
import com.exciselaw.law.domain.MsMatrgroup;
import com.exciselaw.law.domain.MsStatute;
import com.exciselaw.law.domain.MsStatutesub;
import com.exciselaw.law.domain.MsSubmatr;
import com.exciselaw.law.domain.MviewDutyGroup;
import com.exciselaw.law.domain.MviewEdOffice;
import com.exciselaw.law.domain.MviewOrganization;
import com.exciselaw.law.domain.MviewPersonel;
import com.exciselaw.law.domain.NewsFilter;
import com.exciselaw.law.domain.StMatr;
import com.exciselaw.law.domain.StStatutesub;
import com.exciselaw.law.domain.TmAction;
import com.exciselaw.law.domain.TmArticleGroup;
import com.exciselaw.law.domain.TmArticleSection;
import com.exciselaw.law.domain.TmConsultDoc;
import com.exciselaw.law.domain.TmJudgementType;
import com.exciselaw.law.domain.TmLawType;
import com.exciselaw.law.domain.TmRole;
import com.exciselaw.law.domain.TmScreen;
import com.exciselaw.law.domain.TmStatue;
import com.exciselaw.law.domain.TmUserinfo;
import com.exciselaw.law.domain.TsArticle;
import com.exciselaw.law.domain.TsDiscussPublished;
import com.exciselaw.law.domain.TsDocAttach;
import com.exciselaw.law.domain.TsDocResolution;
import com.exciselaw.law.domain.TsDocState;
import com.exciselaw.law.domain.TsDocStatue;
import com.exciselaw.law.domain.TsExArticleCompleted;
import com.exciselaw.law.domain.TsExArticleHeader;
import com.exciselaw.law.domain.TsExArticleHeaderAttach;
import com.exciselaw.law.domain.TsJudgement;
import com.exciselaw.law.domain.TsJudgementAttach;
import com.exciselaw.law.domain.TsJudgementStatue;
import com.exciselaw.law.domain.TsLaw;
import com.exciselaw.law.domain.TsLawAttach;
import com.exciselaw.law.domain.TsLawGood;
import com.exciselaw.law.domain.TsLawStatue;
import com.exciselaw.law.domain.TsLoginLog;
import com.exciselaw.law.domain.TsMasterWord;
import com.exciselaw.law.domain.TsNews;
import com.exciselaw.law.domain.TsNewsAttach;
import com.exciselaw.law.domain.TsPublishedAttach;
import com.exciselaw.law.domain.TsPublishedGood;
import com.exciselaw.law.domain.TsPublishedStatue;
import com.exciselaw.law.domain.TsRelStatute;
import com.exciselaw.law.domain.TsRelStatutePK;
import com.exciselaw.law.domain.TsRoleScreen;
import com.exciselaw.law.domain.TsStatueAttach;
import com.exciselaw.law.domain.TsStatueGood;
import com.exciselaw.law.domain.TsStatuteLegalitem;
import com.exciselaw.law.domain.TsStatuteLegalitemPK;
import com.exciselaw.law.domain.TsStatutesubLegalitem;
import com.exciselaw.law.domain.TsStatutesubLegalitemPK;
import com.exciselaw.law.domain.TsTariff;
import com.exciselaw.law.domain.TsThesaurus;
import com.exciselaw.law.domain.VConsultDoc;
import com.exciselaw.law.domain.VConsultDocList;
import com.exciselaw.law.domain.VDocHistory;
import com.exciselaw.law.domain.ViewStStatutesub;
import com.exciselaw.law.service.ExciseLawService;
import com.exciselaw.law.utils.DateUtils;
import com.exciselaw.law.utils.Paging;

/**
 * @author Chatchai Pimtun
 */
@Repository
@Transactional
public class ExciseLawServiceImpl extends ExciseLawServiceCommon implements ExciseLawService {

	private SessionFactory sessionAnnotationFactory;

	@Autowired
	public ExciseLawServiceImpl(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	

//	@Transactional(readOnly = true)
//	@SuppressWarnings("unchecked")
//	public Collection<Thuser> getThusers() {
//		return this.sessionAnnotationFactory.getCurrentSession().createQuery("from Thuser thuser ").list();
//	}
/*
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}

	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
*/
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsLoginLog(TsLoginLog persistentInstance) {
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTsLoginLog(TsLoginLog persistentInstance) {
		return update(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(readOnly = true)
	public TsLoginLog getTsLoginLogByUserId(String userId){
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsLoginLog l where l.loginDatetime = " +
				"(select max(ll.loginDatetime) from TsLoginLog ll where ll.userId = ? ) ");
	    query.setParameter(0, userId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsLoginLog)obj;
	    }
	    return null;	 
	}
	
	@Transactional(readOnly = true)
	public List<MsStatute> listMsStatutes(String status) throws DataAccessException {
		// TODO Auto-generated method stub
		//if(status!=null)
		List<MsStatute> list = this.sessionAnnotationFactory.getCurrentSession().
				createQuery("from MsStatute msStatute "+((status!=null)?" where msStatute.active="+status:"")+"order by msStatute.statuteOrder ").list();
		return list;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteMsStatute(MsStatute persistentInstance) {
		// TODO Auto-generated method stub
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMsStatute(MsStatute persistentInstance) {
		// TODO Auto-generated method stub
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMsStatute(MsStatute transientInstance) {
		// TODO Auto-generated method stub
		return update(this.sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}

	@Transactional(readOnly = true)
	public List searchMsStatute(MsStatute instance, Paging paging) {
		// TODO Auto-generated method stub
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
	//	instance.getMatrgroupId();
		String name = instance.getName();
		Map map =  new HashMap();
		boolean haveCondition =false;
		int paramindex= 0;
		int linkIndex=9;
		/*
		if(statuteId!=null&& statuteId.intValue()!=0) { 
			queryStr.append((haveCondition?" and ":" where ")+" msMatrgroup.msStatute.statuteId=? ");
			map.put(""+paramindex++, statuteId);
			haveCondition=true;
		}
		if(submatrId!=null&& submatrId.intValue()!=0) { 
			queryStr.append((haveCondition?" and ":" where ")+" msMatrgroup.msSubmatr.submatrId=? ");
			map.put(""+paramindex++, submatrId);
			haveCondition=true;
		}*/
		if(name!=null&& !name.equals("")) { 
			//queryStr.append((haveCondition?" and ":" where ")+" lower(msMatrgroup.name)  like '%?%' ");
			queryStr.append((haveCondition?" and ":" where ")+" lower(msStatute.name)  like '%"+name.toLowerCase()+"%'");
			//linkIndex = paramindex;
			//map.put(""+paramindex++, name);
			haveCondition=true;
		}
		Query query = session.createQuery("from MsStatute msStatute "+queryStr.toString());
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize()
				* (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		query = session
				.createQuery("select count(msStatute) from  MsStatute msStatute "+queryStr.toString());
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}
	@Transactional(readOnly = true)
	public MsStatute findMsStatuteById(Long statuteId) {
		// TODO Auto-generated method stub
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from MsStatute msStatute where msStatute.statuteId=?  ");//matrgroupId);
	    query.setParameter(0, statuteId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (MsStatute)obj;
	    }
	    return null;	 
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteMsSubmatr(MsSubmatr persistentInstance) {
		// TODO Auto-generated method stub
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMsSubmatr(MsSubmatr persistentInstance) {
		// TODO Auto-generated method stub
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMsSubmatr(MsSubmatr transientInstance) {
		// TODO Auto-generated method stub
		return update(this.sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	@Transactional(readOnly = true)
	public List searchMsSubmatr(MsSubmatr instance, Paging paging) {
		// TODO Auto-generated method stub
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		String name = instance.getName();
		Map map =  new HashMap();
		boolean haveCondition =false;
		int paramindex= 0;
		int linkIndex=9;
		if(name!=null&& !name.equals("")) { 
			queryStr.append((haveCondition?" and ":" where ")+" lower(msSubmatr.name)  like '%"+name.toLowerCase()+"%'");
			haveCondition=true;
		}
		Query query = session.createQuery("from MsSubmatr msSubmatr "+queryStr.toString());
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize()
				* (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		query = session
				.createQuery("select count(msSubmatr) from  MsSubmatr msSubmatr "+queryStr.toString());
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}
	@Transactional(readOnly = true)
	public MsSubmatr findMsSubmatrById(Long submatrId) {
		// TODO Auto-generated method stub
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from MsSubmatr msSubmatr where msSubmatr.submatrId=?  ");//matrgroupId);
	    query.setParameter(0, submatrId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (MsSubmatr)obj;
	    }
	    return null;	 
	}

	@Transactional(readOnly = true)
	public List<MsSubmatr> listMsSubmatrs() throws DataAccessException {
		// TODO Auto-generated method stub
		List<MsSubmatr> list = this.sessionAnnotationFactory.getCurrentSession().createQuery("from MsSubmatr msSubmatr order by msSubmatr.submatrOrder ").list();
		return list;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteMsMatrgroup(MsMatrgroup persistentInstance) {
		// TODO Auto-generated method stub
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMsMatrgroup(MsMatrgroup persistentInstance) {
		// TODO Auto-generated method stub 
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMsMatrgroup(MsMatrgroup transientInstance) {
		// TODO Auto-generated method stub
		return update(this.sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}

	@Transactional(readOnly = true)
	public List searchMsMatrgroup(MsMatrgroup instance, Paging paging) {
		// TODO Auto-generated method stub
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
	//	instance.getMatrgroupId();
		Long statuteId  =  instance.getMsStatute()!=null?instance.getMsStatute().getStatuteId():null;
		Long submatrId = instance.getMsSubmatr()!=null?instance.getMsSubmatr().getSubmatrId():null;
		String name = instance.getName();
		Map map =  new HashMap();
		boolean haveCondition =false;
		int paramindex= 0;
		int linkIndex=9;
		if(statuteId!=null&& statuteId.intValue()!=0) { 
			queryStr.append((haveCondition?" and ":" where ")+" msMatrgroup.msStatute.statuteId=? ");
			map.put(""+paramindex++, statuteId);
			haveCondition=true;
		}
		if(submatrId!=null&& submatrId.intValue()!=0) { 
			queryStr.append((haveCondition?" and ":" where ")+" msMatrgroup.msSubmatr.submatrId=? ");
			map.put(""+paramindex++, submatrId);
			haveCondition=true;
		}
		if(name!=null&& !name.equals("")) { 
			//queryStr.append((haveCondition?" and ":" where ")+" lower(msMatrgroup.name)  like '%?%' ");
			queryStr.append((haveCondition?" and ":" where ")+" lower(msMatrgroup.name)  like '%"+name.toLowerCase()+"%'");
			//linkIndex = paramindex;
			//map.put(""+paramindex++, name);
			haveCondition=true;
		}
		Query query = session.createQuery("from MsMatrgroup msMatrgroup "+queryStr.toString());
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			System.out.println("key="+key);
			System.out.println("index="+index);
			System.out.println("linkIndex="+linkIndex);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			System.out.println("value="+value);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize()
				* (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		query = session
				.createQuery("select count(msMatrgroup) from  MsMatrgroup msMatrgroup"+queryStr.toString());
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}
	@Transactional(readOnly = true)
	public MsMatrgroup findMsMatrgroupById(Long matrgroupId) {
		// TODO Auto-generated method stub
		//System.out.println("debug get class = "+MsMatrgroup.class.toString());
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from MsMatrgroup msMatrgroup where msMatrgroup.matrgroupId=?  ");//matrgroupId);
	    query.setParameter(0, matrgroupId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (MsMatrgroup)obj;
	    }
	    return null;
	}

	@Transactional(readOnly = true)
	public List<MsLegalitem> listMsLegalitems(Long legalitemtypeId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = this.sessionAnnotationFactory.getCurrentSession().
			createQuery("from MsLegalitem msLegalitem "+((legalitemtypeId!=null)?" where msLegalitem.msLegalitemtype.legalitemtypeId="+legalitemtypeId:"")+" order by msLegalitem.legalitemOrder ");
	//	query.setParameter(0, legalitemtypeId);
		List<MsLegalitem> list = query.list();
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsStatuteLegalitem(TsStatuteLegalitem persistentInstance) {
		// TODO Auto-generated method stub
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}


	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsStatuteLegalitem(TsStatuteLegalitem persistentInstance) {
		// TODO Auto-generated method stub
		Object obj = this.sessionAnnotationFactory.getCurrentSession().save(persistentInstance);
		System.out.println("savveeee = "+obj.getClass());
		return null;
		
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTsStatuteLegalitem(TsStatuteLegalitem transientInstance) {
		// TODO Auto-generated method stub
		return update(this.sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}

	@Transactional(readOnly = true)
	public List searchTsStatuteLegalitem(TsStatuteLegalitem instance,
			Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public TsStatuteLegalitem findTsStatuteLegalitemById(TsStatuteLegalitemPK id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void  saveTsStatuteLegalitem(
			List<TsStatuteLegalitem> persistentInstance) {
		// TODO Auto-generated method stub
	    Session session = this.sessionAnnotationFactory.getCurrentSession();
		if(persistentInstance!=null && persistentInstance.size()>0){
			int size=persistentInstance.size();
				for (int i = 0; i < size; i++) {
					session.save(persistentInstance.get(i));
				}
		}
	}


	public List<TsStatuteLegalitem> searchTsStatuteLegalitemByStatuteId(Long statuteId) {
		// TODO Auto-generated method stub
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery("from TsStatuteLegalitem tsStatuteLegalitem where tsStatuteLegalitem.id.msStatute.statuteId=? ");
		query.setParameter(0, statuteId);
		List<TsStatuteLegalitem> list = query.list();
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteStMatr(StMatr persistentInstance) {
		// TODO Auto-generated method stub
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveStMatr(StMatr persistentInstance) {
		// TODO Auto-generated method stub
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateStMatr(StMatr transientInstance) {
		// TODO Auto-generated method stub
		return update(this.sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}

	@Transactional(readOnly = true)
	public List searchStMatr(StMatr instance, Paging paging) {
		// TODO Auto-generated method stub
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
	//	instance.getMatrgroupId();
		String matr = instance.getMatr();
		String part = instance.getPart();
  	    MsMatrgroup msMatrgroup= instance.getMsMatrgroup();
  	    MsStatute msStatute= instance.getMsStatute();
		 
		Map map =  new HashMap();
		boolean haveCondition =false;
		int paramindex= 0;
		
		if(msMatrgroup!=null&& msMatrgroup.getMatrgroupId()!=null && msMatrgroup.getMatrgroupId().intValue()!=0) { 
			queryStr.append((haveCondition?" and ":" where ")+" stMatr.msMatrgroup.matrgroupId=? ");
			map.put(""+paramindex++, msMatrgroup.getMatrgroupId());
			haveCondition=true;
		}
		if(msStatute!=null&& msStatute.getStatuteId()!=null && msStatute.getStatuteId().intValue()!=0) { 
			queryStr.append((haveCondition?" and ":" where ")+" stMatr.msStatute.statuteId=? ");
			map.put(""+paramindex++, msStatute.getStatuteId());
			haveCondition=true;
		}
		if(matr!=null&& !matr.equals("")) {  
			queryStr.append((haveCondition?" and ":" where ")+" lower(stMatr.matr)  like '%"+matr.toLowerCase()+"%'"); 
			haveCondition=true;
		}
		if(part!=null&& !part.equals("")) {  
			queryStr.append((haveCondition?" and ":" where ")+" lower(stMatr.part)  like '%"+part.toLowerCase()+"%'"); 
			haveCondition=true;
		}
		queryStr.append(" order by stMatr.msStatute.statuteId , stMatr.matr");
		Query query = session.createQuery("from StMatr stMatr "+queryStr.toString());
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize()
				* (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		query = session
				.createQuery("select count(stMatr) from  StMatr stMatr "+queryStr.toString());
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}

	// TODO add by LEK 13/10/2011
	@Transactional(readOnly = true)
	public List<StMatr> findStMatrListByStatuteId(Long statuteId) {
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from StMatr stMatr where stMatr.msStatute.statuteId=? order by stMatr.matr ");
	    query.setParameter(0, statuteId);
	    List<StMatr> list = query.list();
	    return list;
	}

	@Transactional(readOnly = true)
	public StMatr findStMatrById(Long stMatrId) {
		// TODO Auto-generated method stub
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from StMatr stMatr where stMatr.stMatrId=?  ");//matrgroupId);
	    query.setParameter(0, stMatrId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (StMatr)obj;
	    }
	    return null;
	}


	public List<MsMatrgroup> listMsMatrgroups() throws DataAccessException {
		// TODO Auto-generated method stub
		List<MsMatrgroup> list = this.sessionAnnotationFactory.getCurrentSession().createQuery("from MsMatrgroup msMatrgroup order by msMatrgroup.matrgroupOrder ").list();
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteStStatutesub(StStatutesub persistentInstance) {
		// TODO Auto-generated method stub
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveStStatutesub(StStatutesub persistentInstance) {
		// TODO Auto-generated method stub
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateStStatutesub(StStatutesub transientInstance) {
		// TODO Auto-generated method stub
		return update(this.sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}

	@Transactional(readOnly = true)
	public List searchStStatutesub(StStatutesub instance, Paging paging,Long productId) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		MsStatute msStatute= instance.getMsStatute();
		MsStatutesub msStatutesub = instance.getMsStatutesub();
		String article = instance.getArticle();
		String keyword = instance.getKeyword();
		/*
  	    MsMatrgroup msMatrgroup= instance.getMsMatrgroup();  	     
  	    MsStatute msStatute= instance.getMsStatute();
		 */
		Map map =  new HashMap();
		boolean haveCondition =false;
		int paramindex= 0;
	 
	 	if(msStatute!=null&& msStatute.getStatuteId()!=null && msStatute.getStatuteId().intValue()!=0) { 
			queryStr.append((haveCondition?" and ":" where ")+" viewStStatutesub.statuteId=? ");
			map.put(""+paramindex++, msStatute.getStatuteId());
			haveCondition=true;
		}
		if(msStatutesub!=null&& msStatutesub.getStatutesubId()!=null && msStatutesub.getStatutesubId().intValue()!=0) { 
			queryStr.append((haveCondition?" and ":" where ")+" viewStStatutesub.statutesubId=? ");
			map.put(""+paramindex++, msStatutesub.getStatutesubId());
			haveCondition=true;
		}
		if(productId!=null&& productId.intValue()!=0) { 
			queryStr.append((haveCondition?" and ":" where ")+" viewStStatutesub.legalitemId=? ");
			map.put(""+paramindex++, productId);
			haveCondition=true;
		}
		if(article!=null&& !article.equals("")) {  
			queryStr.append((haveCondition?" and ":" where ")+" lower(viewStStatutesub.article)  like '%"+article.toLowerCase()+"%'"); 
			haveCondition=true;
		}
		if(keyword!=null&& !keyword.equals("")) {  
			queryStr.append((haveCondition?" and ":" where ")+" lower(viewStStatutesub.keyword)  like '%"+keyword.toLowerCase()+"%'"); 
			haveCondition=true;
		}
	  

	 
//		queryStr.append(" order by stMatr.msStatute.statuteId , stMatr.matr");
		Query query = session.createQuery("from ViewStStatutesub viewStStatutesub "+queryStr.toString());
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize()
				* (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
	//	query.setEntity("viewStStatutesub", arg1)(arg0, arg1)
		List<ViewStStatutesub> list = query.list();
		List stStatutesubs =null;
		if(list!=null ){
			int stStatutesubs_size=list.size();
			stStatutesubs = new ArrayList(stStatutesubs_size);
			for (ViewStStatutesub element : list) {
				StStatutesub stStatutesub =new StStatutesub();
				stStatutesub.setStStatutesubId(element.getStStatutesubId());
				stStatutesub.setAnnounceBook(element.getAnnounceBook());
				stStatutesub.setAnnounceDate(element.getAnnounceDate());
				stStatutesub.setAnnounceNo(element.getAnnounceNo());
				stStatutesub.setAnnouncePart(element.getAnnouncePart());
				stStatutesub.setArticle(element.getArticle());
				stStatutesub.setArticleEng(element.getArticleEng());
				stStatutesub.setBookNo(element.getBookNo());
				stStatutesub.setBookYear(element.getBookYear());
				stStatutesub.setDetail(element.getDetail());
				stStatutesub.setDisplay(element.getDisplay());
				stStatutesub.setHightlight(element.getHightlight());
				stStatutesub.setIntro(element.getIntro());
				stStatutesub.setKeyword(element.getKeyword());
				stStatutesub.setRemark(element.getRemark());
				stStatutesub.setStOrder(element.getStOrder());
				stStatutesub.setStatus(element.getStatus());
				stStatutesub.setTableAttach(element.getTableAttach());
				stStatutesub.setUpdateTime(element.getUpdateTime());
				stStatutesub.setUpdateUser(element.getUpdateUser());
				if(element.getStatuteId()!=null && element.getStatuteId().intValue()!=0){
					MsStatute msstatute =new MsStatute();
					if(element.getStatuteName()!=null)
						msstatute.setName(element.getStatuteName());
					stStatutesub.setMsStatute(msstatute);
				}
				if(element.getStatutesubId()!=null && element.getStatutesubId().intValue()!=0){
					MsStatutesub msstatutesub =new MsStatutesub();
					if(element.getStatuteSubName()!=null)
						msstatutesub.setName(element.getStatuteSubName());
					stStatutesub.setMsStatutesub(msstatutesub);
				} 
				stStatutesubs.add(stStatutesub);
			}  
		}
		System.out.println("stStatutesubs.size()=>"+stStatutesubs.size());
		query = session
				.createQuery("select count(viewStStatutesub) from  ViewStStatutesub viewStStatutesub "+queryStr.toString());
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= map.get(key);
			query.setParameter(index, value);
		} 
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(stStatutesubs); 
		transList.add(count+""); 
		
		return transList;
	
	}

	@Transactional(readOnly = true)
	public StStatutesub findStStatutesubById(Long stStatutesubId) {
		// TODO Auto-generated method stub
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from StStatutesub stStatutesub where stStatutesub.stStatutesubId=?  ");//matrgroupId);
	    query.setParameter(0, stStatutesubId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (StStatutesub)obj;
	    }
	    return null;
	}

	@Transactional(readOnly = true)
	public List<MsStatutesub> listMsStatutesubs() throws DataAccessException {
		// TODO Auto-generated method stub
		List<MsStatutesub> list = this.sessionAnnotationFactory.getCurrentSession().createQuery("from MsStatutesub msStatutesub order by msStatutesub.statutesubOrder ").list();
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsStatutesubLegalitem(
			TsStatutesubLegalitem persistentInstance) {
		// TODO Auto-generated method stub
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsStatutesubLegalitem(
			TsStatutesubLegalitem persistentInstance) {
		// TODO Auto-generated method stub
		Object obj = this.sessionAnnotationFactory.getCurrentSession().save(persistentInstance); 
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTsStatutesubLegalitem(
			TsStatutesubLegalitem transientInstance) {
		// TODO Auto-generated method stub
		return update(this.sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}

	@Transactional(readOnly = true)
	public List searchTsStatutesubLegalitem(TsStatutesubLegalitem instance,
			Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public TsStatutesubLegalitem findTsStatutesubLegalitemById(
			TsStatutesubLegalitemPK id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void saveTsStatutesubLegalitem(
			List<TsStatutesubLegalitem> persistentInstance) {
		// TODO Auto-generated method stub
		  Session session = this.sessionAnnotationFactory.getCurrentSession();
			if(persistentInstance!=null && persistentInstance.size()>0){
				int size=persistentInstance.size();
					for (int i = 0; i < size; i++) {
						session.save(persistentInstance.get(i));
					}
			}
	}
	@Transactional(readOnly = true)
	public List<TsStatutesubLegalitem> searchTsStatutesubLegalitemByStStatutesubId(
			Long stStatutesubId) {
		// TODO Auto-generated method stub
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery("from TsStatutesubLegalitem tsStatutesubLegalitem where tsStatutesubLegalitem.id.stStatutesub.stStatutesubId=? ");
		query.setParameter(0, stStatutesubId);
		List<TsStatutesubLegalitem> list = query.list();
		return list;
	}


	public void deleteTsRelStatute(TsRelStatute persistentInstance) {
		// TODO Auto-generated method stub
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);
	}


	public Long saveTsRelStatute(TsRelStatute persistentInstance) {
		// TODO Auto-generated method stub
		Object obj = this.sessionAnnotationFactory.getCurrentSession().save(persistentInstance);
		return null;
	}


	public int updateTsRelStatute(TsRelStatute transientInstance) {
		// TODO Auto-generated method stub
		return update(this.sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}


	public List searchTsRelStatute(TsRelStatute instance, Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}


	public TsRelStatute findTsRelStatuteById(TsRelStatutePK id) {
		// TODO Auto-generated method stub
		return null;
	}


	public void saveTsRelStatute(List<TsRelStatute> persistentInstance) {
		// TODO Auto-generated method stub
		 Session session = this.sessionAnnotationFactory.getCurrentSession();
			if(persistentInstance!=null && persistentInstance.size()>0){
				int size=persistentInstance.size();
					for (int i = 0; i < size; i++) {
						session.save(persistentInstance.get(i));
					}
			}
	}


	public List<TsRelStatute> searchTsRelStatuteByStStatutesubId(
			Long stStatutesubId) {
		// TODO Auto-generated method stub
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery("from TsRelStatute tsRelStatute where tsRelStatute.id.stStatutesub.stStatutesubId=? ");
		query.setParameter(0, stStatutesubId);
		List<TsRelStatute> list = query.list();
		return list;
	}
	//------------------------------ START LAW -------------------------------------------- //
	
	@Transactional(readOnly = true)
	public List<TmStatue> getTmStatueList(){
		List<TmStatue> resultList = new ArrayList<TmStatue>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TmStatue s where s.statueStatus='A' order by s.statueOrder ");
		resultList = query.list();
		return resultList;		
	}	
	
	@Transactional(readOnly = true)
	public List<TmLawType> getTmLawTypeList(){
		List<TmLawType> resultList = new ArrayList<TmLawType>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TmLawType l order by l.lawTypeOrder ");
		resultList = query.list();
		return resultList;		
	}	
	
	@Transactional(readOnly = true)
	public List<TmLawType> getTmLawTypeListByPriority0(){
		List<TmLawType> resultList = new ArrayList<TmLawType>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TmLawType l where l.lawTypePriority=0 order by l.lawTypeOrder ");
		resultList = query.list();
		return resultList;		
	}	
	
	@Transactional(readOnly = true)
	public List<TmArticleGroup> getTmArticleGroupList(){
		List<TmArticleGroup> resultList = new ArrayList<TmArticleGroup>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TmArticleGroup a where a.articleGroupStatus='A' order by a.articleGroupOrder ");
		resultList = query.list();
		return resultList;		
	}
	
	@Transactional(readOnly = true)
	public List<TmArticleSection> getTmArticleSectionList(){
		List<TmArticleSection> resultList = new ArrayList<TmArticleSection>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TmArticleSection s where s.articleSectionStatus='A' order by s.articleSectionOrder ");
		resultList = query.list();
		return resultList;		
	}

	@Transactional(readOnly = true)
	public List<TsArticle> getTsArticleList(BigDecimal exciseArticleType){
		List<TsArticle> resultList = new ArrayList<TsArticle>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsArticle a where a.exciseArticleType = ? order by a.articleOrder ");
		query.setParameter(0, exciseArticleType);
		resultList = query.list();
		return resultList;		
	}

	@Transactional(readOnly = true)
	public List<TsExArticleHeader> getTsExArticleHeaderList(){
		List<TsExArticleHeader> resultList = new ArrayList<TsExArticleHeader>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsExArticleHeader h order by h.articleHeaderId ");
		resultList = query.list();
		return resultList;		
	}

	@Transactional(readOnly = true)
	public List<TsExArticleCompleted> getTsArticleCompletedList(){
		List<TsExArticleCompleted> resultList = new ArrayList<TsExArticleCompleted>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsExArticleCompleted c order by c.articleCompletedOrder ");
		resultList = query.list();
		return resultList;		
	}

	@Transactional(readOnly = true)
	public List<TsExArticleHeader> getExArticleHeaderListByLawTypeId(BigDecimal lawTypeId){
		List<TsExArticleHeader> resultList = new ArrayList<TsExArticleHeader>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsExArticleHeader h where h.lawTypeId = ? order by h.articleHeaderId ");
	    query.setParameter(0, lawTypeId);
		resultList = query.list();
		return resultList;		
	}
	
	@Transactional(readOnly = true)
	public List searchStatueList(TmStatue instance, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		String statueType = instance.getStatueType();
		String statueStatus = instance.getStatueStatus();
		String statueName = instance.getStatueName();
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(statueType!=null && !statueType.equals("")) { 
			queryStr.append(" and s.statueType  = ?");
			map.put(""+paramindex++, statueType);
		}
		if(statueStatus!=null && !statueStatus.equals("")) { 
			queryStr.append(" and s.statueStatus  = ?");
			map.put(""+paramindex++, statueStatus);
		}
		if(statueName!=null && !statueName.equals("")) { 
			queryStr.append(" and lower(s.statueName) like '%"+statueName.toLowerCase()+"%'");
		}
		
		String queryOrder = " order by s.statueOrder";
		
		Query query = session.createQuery(" from TmStatue s where 1=1 "+queryStr.toString()+queryOrder);
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		
		query = session.createQuery("select count(s) from TmStatue s where 1=1 "+queryStr.toString());
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}	

	@Transactional(readOnly = true)
	public TmStatue findTmStatueByStatueId(Long statueId){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TmStatue s where s.statueId = ? ");
	    query.setParameter(0, statueId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TmStatue)obj;
	    }
	    return null;	 
	}

	@Transactional(readOnly = true)
	public String checkStatueOrder(BigDecimal statueOrder){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TmStatue s where s.statueOrder = ? ");
	    query.setParameter(0, statueOrder);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return "true";
	    }
	    return "false";	 
	}

	@Transactional(readOnly = true)
	public BigDecimal findMaxStatueOrder(){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" select max(s.statueOrder) from TmStatue s");
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (BigDecimal)obj;
	    }
	    return new BigDecimal("1");	 
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTmStatue(TmStatue persistentInstance) {
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTmStatue(TmStatue persistentInstance) {
		return update(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTmStatue(TmStatue persistentInstance) {
		update(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}	
	
	@Transactional(readOnly = true)
	public List<TsStatueGood> findTsStatueGoodByStatueId(BigDecimal statueId){
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsStatueGood g where g.statueId=? ");
		query.setParameter(0, statueId);
		List<TsStatueGood> list = query.list();
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsStatueGood(TsStatueGood persistentInstance){
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsStatueGood(TsStatueGood persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}

	@Transactional(readOnly = true)
	public List<TsStatueAttach> findTsStatueAttachListByStatueId(Long statueId){
		List<TsStatueAttach> resultList = new ArrayList<TsStatueAttach>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsStatueAttach a where a.statueId  = ? ");
		query.setBigDecimal(0,BigDecimal.valueOf(statueId));
		resultList = query.list();
		return resultList;
	}
	
	@Transactional(readOnly = true)
	public TsStatueAttach findTsStatueAttachByAttachId(Long attachId){
		TsStatueAttach tsStatueAttach = new TsStatueAttach();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsStatueAttach a where a.statueAttachId = ? ");
		query.setParameter(0, attachId);

	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsStatueAttach)obj;
	    }
		return tsStatueAttach;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsStatueAttach(TsStatueAttach persistentInstance){
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsStatueAttach(TsStatueAttach persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}
		
	@Transactional(readOnly = true)
	public List searchLawTypeList(TmLawType instance, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		String lawTypeType = instance.getLawTypeType();
		BigDecimal lawTypePriority = instance.getLawTypePriority();
		String lawTypeName = instance.getLawTypeName();
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(lawTypeType!=null && !lawTypeType.equals("")) { 
			queryStr.append(" and t.lawTypeType  = ?");
			map.put(""+paramindex++, lawTypeType);
		}
		if(lawTypePriority!=null) { 
			queryStr.append(" and t.lawTypePriority  = ?");
			map.put(""+paramindex++, lawTypePriority);
		}
		if(lawTypeName!=null && !lawTypeName.equals("")) { 
			queryStr.append(" and lower(t.lawTypeName) like '%"+lawTypeName.toLowerCase()+"%'");
		}
		
		String queryOrder = " order by  t.lawTypeOrder";
				
		Query query = session.createQuery(" from TmLawType t where 1=1 "+queryStr.toString()+queryOrder);
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		
		query = session.createQuery("select count(t) from TmLawType t where 1=1 "+queryStr.toString());
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}	

	@Transactional(readOnly = true)
	public TmLawType findTmLawTypeByLawTypeId(Long lawTypeId){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TmLawType t where t.lawTypeId = ? ");
	    query.setParameter(0, lawTypeId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TmLawType)obj;
	    }
	    return null;	 
	}

	@Transactional(readOnly = true)
	public String checkLawTypeOrder(BigDecimal lawTypeOrder){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TmLawType t where t.lawTypeOrder = ? ");
	    query.setParameter(0, lawTypeOrder);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return "true";
	    }
	    return "false";	 
	}

	@Transactional(readOnly = true)
	public BigDecimal findMaxLawTypeOrder(){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" select max(t.lawTypeOrder) from TmLawType t ");
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (BigDecimal)obj;
	    }
	    return new BigDecimal("1");	 
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTmLawType(TmLawType persistentInstance) {
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTmLawType(TmLawType persistentInstance) {
		return update(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTmLawType(TmLawType persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}
	
	@Transactional(readOnly = true)
	public List searchArticleGroupList(TmArticleGroup instance, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		String articleGroupType = instance.getArticleGroupType();
		BigDecimal statueId = instance.getStatueId();
		String articleGroupName = instance.getArticleGroupName();
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(articleGroupType!=null && !articleGroupType.equals("")) { 
			queryStr.append(" and g.articleGroupType  = ?");
			map.put(""+paramindex++, articleGroupType);
		}
		if(statueId!=null) { 
			queryStr.append(" and g.statueId  = ?");
			map.put(""+paramindex++, statueId);
		}
		if(articleGroupName!=null && !articleGroupName.equals("")) { 
			queryStr.append(" and lower(g.articleGroupName) like '%"+articleGroupName.toLowerCase()+"%'");
		}
		
		String queryOrder = " order by g.articleGroupOrder";
		
		Query query = session.createQuery(" from VArticleGroup g where g.articleGroupStatus = 'A' "+queryStr.toString()+queryOrder);
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		
		query = session.createQuery("select count(g) from VArticleGroup g where g.articleGroupStatus = 'A' "+queryStr.toString());
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}	

	@Transactional(readOnly = true)
	public TmArticleGroup findTmArticleGroupByArticleGroupId(Long articleGroupId){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TmArticleGroup g where g.articleGroupId = ? ");
	    query.setParameter(0, articleGroupId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TmArticleGroup)obj;
	    }
	    return null;	 
	}

	@Transactional(readOnly = true)
	public BigDecimal findMaxArticleGroupOrder(){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" select max(g.articleGroupOrder) from TmArticleGroup g ");
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	BigDecimal order = (BigDecimal)obj;
	    	return order.add(new BigDecimal("1"));
	    }
	    return new BigDecimal("1");	 
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTmArticleGroup(TmArticleGroup persistentInstance) {
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTmArticleGroup(TmArticleGroup persistentInstance) {
		return update(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTmArticleGroup(TmArticleGroup persistentInstance) {
		update(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}
	
	@Transactional(readOnly = true)
	public List searchArticleSectionList(TmArticleSection instance, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		String articleSectionType = instance.getArticleSectionType();
		BigDecimal statueId = instance.getStatueId();
		BigDecimal articleGroupId = instance.getArticleGroupId();
		String articleSectionName = instance.getArticleSectionName();
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(articleSectionType!=null && !articleSectionType.equals("")) { 
			queryStr.append(" and s.articleSectionType  = ?");
			map.put(""+paramindex++, articleSectionType);
		}
		if(statueId!=null) { 
			queryStr.append(" and s.statueId  = ?");
			map.put(""+paramindex++, statueId);
		}
		if(articleGroupId!=null) { 
			queryStr.append(" and s.articleGroupId  = ?");
			map.put(""+paramindex++, articleGroupId);
		}
		if(articleSectionName!=null && !articleSectionName.equals("")) { 
			queryStr.append(" and lower(s.articleSectionName) like '%"+articleSectionName.toLowerCase()+"%'");
		}
		String queryOrder = " order by s.articleSectionOrder";
		
		Query query = session.createQuery(" from VArticleSection s where s.articleSectionStatus = 'A' "+queryStr.toString()+queryOrder);
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		
		query = session.createQuery("select count(s) from VArticleSection s where s.articleSectionStatus = 'A' "+queryStr.toString());
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}	

	@Transactional(readOnly = true)
	public TmArticleSection findTmArticleSectionByArticleSectionId(Long articleSectionId){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TmArticleSection s where s.articleSectionId = ? ");
	    query.setParameter(0, articleSectionId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TmArticleSection)obj;
	    }
	    return null;	 
	}

	@Transactional(readOnly = true)
	public BigDecimal findMaxArticleSectionOrder(){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" select max(s.articleSectionOrder) from TmArticleSection s ");
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	BigDecimal order = (BigDecimal)obj;
	    	return order.add(new BigDecimal("1"));
	    }
	    return new BigDecimal("1");	 
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTmArticleSection(TmArticleSection persistentInstance) {
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTmArticleSection(TmArticleSection persistentInstance) {
		return update(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTmArticleSection(TmArticleSection persistentInstance) {
		update(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}	
	
	@Transactional(readOnly = true)
	public List searchExArticleHeadList(TsExArticleHeader instance, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		BigDecimal statueId = instance.getStatueId();
		BigDecimal lawTypeId = instance.getLawTypeId();
		String articleHeaderName = instance.getArticleHeaderName();
		Long articleHeaderId = instance.getArticleHeaderId();
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(statueId!=null) { 
			queryStr.append(" and h.statueId  = ?");
			map.put(""+paramindex++, statueId);
		}
		if(lawTypeId!=null && !lawTypeId.equals("")) { 
			queryStr.append(" and h.lawTypeId  = ?");
			map.put(""+paramindex++, lawTypeId);
		}
		if(articleHeaderName!=null && !articleHeaderName.equals("")) { 
			queryStr.append(" and lower(h.articleHeaderName) like '%"+articleHeaderName.toLowerCase()+"%'");
		}
		if(articleHeaderId!=null && articleHeaderId.longValue()>0) { 
			queryStr.append(" and h.articleHeaderId  = ?");
			map.put(""+paramindex++, articleHeaderId.longValue());
		}
		String queryOrder = " order by h.articleHeaderId";
		
		Query query = session.createQuery(" from VExArticleHeader h where 1=1 "+queryStr.toString()+queryOrder);
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		
		query = session.createQuery("select count(h) from VExArticleHeader h where 1=1 "+queryStr.toString());
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}	

	@Transactional(readOnly = true)
	public TsExArticleHeader findTsExArticleHeaderByArticleHeaderId(Long articleHeaderId){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsExArticleHeader h where h.articleHeaderId = ? ");
	    query.setParameter(0, articleHeaderId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsExArticleHeader)obj;
	    }
	    return null;	 
	}	
	
	@Transactional(readOnly = true)
	public List<TsExArticleHeader> findTsExArticleHeaderListByStatueIdLawTypeId(BigDecimal statueId, BigDecimal lawTypeId){ 
		List<TsExArticleHeader> resultList = new ArrayList<TsExArticleHeader>();
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsExArticleHeader h where h.statueId = ? and h.lawTypeId = ?");
	    query.setParameter(0, statueId);
	    query.setParameter(1, lawTypeId);
	    resultList = query.list();
	    return resultList;	 
	}


	@Transactional(readOnly = true)
	public BigDecimal findMaxArticleOrder(BigDecimal exciseArticleType, BigDecimal statueId, BigDecimal lawTypeId){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" select max(a.articleOrder)" +
				" from TsArticle a where a.exciseArticleType = ? and a.statueId = ? and a.lawTypeId = ?");
		query.setParameter(0, exciseArticleType);
		query.setParameter(1, statueId);
	    query.setParameter(2, lawTypeId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	BigDecimal order = (BigDecimal)obj;
	    	return order.add(new BigDecimal("1"));
	    }
	    return new BigDecimal("1");	 
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsExArticleHeader(TsExArticleHeader persistentInstance) {
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTsExArticleHeader(TsExArticleHeader persistentInstance) {
		return update(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsExArticleHeader(TsExArticleHeader persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}		

	@Transactional(readOnly = true)
	public List<TsExArticleHeaderAttach> findTsExArticleHeaderAttachListByArticleHeaderId(Long articleHeaderId){
		List<TsExArticleHeaderAttach> resultList = new ArrayList<TsExArticleHeaderAttach>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsExArticleHeaderAttach a where a.articleHeaderId  = ? ");
		query.setBigDecimal(0,BigDecimal.valueOf(articleHeaderId));
		resultList = query.list();
		return resultList;
	}
	
	@Transactional(readOnly = true)
	public TsExArticleHeaderAttach findTsExArticleHeaderAttachByAttachId(Long attachId){
		TsExArticleHeaderAttach tsExArticleHeaderAttach = new TsExArticleHeaderAttach();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsExArticleHeaderAttach a where a.articleHeadAttachId = ? ");
		query.setParameter(0, attachId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsExArticleHeaderAttach)obj;
	    }
		return tsExArticleHeaderAttach;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsExArticleHeadAttach(TsExArticleHeaderAttach persistentInstance){
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsExArticleHeadAttach(TsExArticleHeaderAttach persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}
	
	@Transactional(readOnly = true)
	public List searchArticleList(TsArticle instance, BigDecimal exciseArticleType, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		BigDecimal statueId = instance.getStatueId();
		BigDecimal lawTypeId = instance.getLawTypeId();
		BigDecimal articleGroupId = instance.getArticleGroupId();
		BigDecimal articleSectionId = instance.getArticleSectionId();
		String articleNumber = instance.getArticleNumber();
		Long articleId = instance.getArticleId();
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(statueId!=null) { 
			queryStr.append(" and a.statueId  = ?");
			map.put(""+paramindex++, statueId);
		}
		if(lawTypeId!=null && !lawTypeId.equals("")) { 
			queryStr.append(" and a.lawTypeId  = ?");
			map.put(""+paramindex++, lawTypeId);
		}
		if(articleGroupId!=null) { 
			queryStr.append(" and a.articleGroupId  = ?");
			map.put(""+paramindex++, articleGroupId);
		}
		if(articleSectionId!=null) { 
			queryStr.append(" and a.articleSectionId  = ?");
			map.put(""+paramindex++, articleSectionId);
		}
		if(articleNumber!=null && !articleNumber.equals("")) { 
			queryStr.append(" and lower(a.articleNumber) like '%"+articleNumber.toLowerCase()+"%'");
		}
		if(articleId!=null && articleId.longValue()>0) { 
			queryStr.append(" and a.articleId  = ?");
			map.put(""+paramindex++, articleId.longValue());
		}
		if(exciseArticleType!=null) { 
			queryStr.append(" and a.exciseArticleType  = ?");
			map.put(""+paramindex++, exciseArticleType);
		}
		String queryOrder = " order by a.articleOrder";
		
		Query query = session.createQuery(" from VArticle a where 1=1 "+queryStr.toString()+queryOrder);
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		
		query = session.createQuery("select count(a) from VArticle a where 1=1 "+queryStr.toString());
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}	

	@Transactional(readOnly = true)
	public TsArticle findTsArticleByArticleId(Long articleId){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsArticle a where a.articleId = ? ");
	    query.setParameter(0, articleId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsArticle)obj;
	    }
	    return null;	 
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsArticle(TsArticle persistentInstance) {
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTsArticle(TsArticle persistentInstance) {
		return update(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsArticle(TsArticle persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}	
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void callReorderArticle(Integer articleOrder, Integer exciseArticleType, Integer statueId, Integer lawTypeId){
		this.sessionAnnotationFactory.getCurrentSession().beginTransaction();
        Query q = this.sessionAnnotationFactory.getCurrentSession().createSQLQuery("{CALL REORDER_ARTICLE(?, ?, ?, ?)}");
        q.setInteger(0, articleOrder);  
        q.setInteger(1, exciseArticleType); 
        q.setInteger(2, statueId); 
        q.setInteger(3, lawTypeId); 
        q.executeUpdate();
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void callReorderArticleGroup(Integer articleGroupOrder){
		this.sessionAnnotationFactory.getCurrentSession().beginTransaction();
        Query q = this.sessionAnnotationFactory.getCurrentSession().createSQLQuery("{CALL REORDER_ARTICLE_GROUP(?)}");
        q.setInteger(0,articleGroupOrder);  
        q.executeUpdate();
	}

	@Transactional(readOnly = true)
	public List searchArticleCompletedList(TsExArticleCompleted instance, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		BigDecimal statueId = instance.getStatueId();
		BigDecimal articleGroupId = instance.getArticleGroupId();
		BigDecimal articleSectionId = instance.getArticleSectionId();
		String articleCompletedNumber = instance.getArticleCompletedNumber();
		Long articleCompletedId = instance.getArticleCompletedId();
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(statueId!=null) { 
			queryStr.append(" and c.statueId  = ?");
			map.put(""+paramindex++, statueId);
		}
		if(articleGroupId!=null) { 
			queryStr.append(" and c.articleGroupId  = ?");
			map.put(""+paramindex++, articleGroupId);
		}
		if(articleSectionId!=null) { 
			queryStr.append(" and c.articleSectionId  = ?");
			map.put(""+paramindex++, articleSectionId);
		}
		if(articleCompletedNumber!=null && !articleCompletedNumber.equals("")) { 
			queryStr.append(" and lower(c.articleCompletedNumber) like '%"+articleCompletedNumber.toLowerCase()+"%'");
		}
		if(articleCompletedId!=null && articleCompletedId.longValue()>0) { 
			queryStr.append(" and c.articleCompletedId  = ?");
			map.put(""+paramindex++, articleCompletedId.longValue());
		}
		String queryOrder = " order by c.articleCompletedOrder";
		
		Query query = session.createQuery(" from VArticleCompleted c where 1=1 "+queryStr.toString()+queryOrder);
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		
		query = session.createQuery("select count(c) from VArticleCompleted c where 1=1 "+queryStr.toString());
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}	

	@Transactional(readOnly = true)
	public TsExArticleCompleted findTsExArticleCompletedByArticleCompletedId(Long articleCompletedId){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsExArticleCompleted c where c.articleCompletedId = ? ");
	    query.setParameter(0, articleCompletedId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsExArticleCompleted)obj;
	    }
	    return null;	 
	}

	@Transactional(readOnly = true)
	public TsExArticleCompleted findTsExArticleCompletedByStatueId(BigDecimal statueId){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery("select distinct c from TsExArticleCompleted c where c.statueId = ? ");
	    query.setParameter(0, statueId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsExArticleCompleted)obj;
	    }
	    return null;	 
	}	

	@Transactional(readOnly = true)
	public BigDecimal findMaxArticleCompletedOrder(BigDecimal statueId){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" select max(c.articleCompletedOrder) from TsExArticleCompleted c where c.statueId = ? ");
		query.setParameter(0, statueId);
		Object obj = query.uniqueResult();
	    if(obj!=null){
	    	BigDecimal order = (BigDecimal)obj;
	    	return order.add(new BigDecimal("1"));
	    }
	    return new BigDecimal("1");	 
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsExArticleCompleted(TsExArticleCompleted persistentInstance) {
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTsExArticleCompleted(TsExArticleCompleted persistentInstance) {
		return update(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsExArticleCompleted(TsExArticleCompleted persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}	
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void callReorderArticleCompleted(Integer articleCompletedOrder, Integer statueId){
		this.sessionAnnotationFactory.getCurrentSession().beginTransaction();
        Query q = this.sessionAnnotationFactory.getCurrentSession().createSQLQuery("{CALL REORDER_ARTICLE_COMPLETED(?, ?)}");
        q.setInteger(0,articleCompletedOrder);  
        q.setInteger(1,statueId);  
        q.executeUpdate();
	}
	
	
	@Transactional(readOnly = true)
	public TsExArticleCompleted findArticleCompletedByStatueIdArticleNumber(BigDecimal statueId, String articleCompletedNumber){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsExArticleCompleted c where c.statueId = ? and c.articleCompletedNumber = ? ");
	    query.setParameter(0, statueId);
	    query.setParameter(1, articleCompletedNumber);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsExArticleCompleted)obj;
	    }
	    return null;	 
	}

	@Transactional(readOnly = true)
	public List searchTariffList(TsTariff instance, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		BigDecimal articleHeaderId = instance.getArticleHeaderId();
		long tariffId = instance.getTariffId();
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(articleHeaderId!=null) { 
			queryStr.append(" and t.articleHeaderId  = ?");
			map.put(""+paramindex++, articleHeaderId);
		}
		if(tariffId>0) { 
			queryStr.append(" and t.tariffId  = ?");
			map.put(""+paramindex++, tariffId);
		}
		String queryOrder = " order by t.tariffId";
		
		Query query = session.createQuery(" from TsTariff t where 1=1 "+queryStr.toString()+queryOrder);
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		
		query = session.createQuery("select count(t) from TsTariff t where 1=1 "+queryStr.toString());
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}	
	
	@Transactional(readOnly = true)
	public TsTariff findTsTariffByTariffId(Long tariffId){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsTariff t where t.tariffId = ? ");
	    query.setParameter(0, tariffId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsTariff)obj;
	    }
	    return null;	 
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsTariff(TsTariff persistentInstance) {
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTsTariff(TsTariff persistentInstance) {
		return update(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsTariff(TsTariff persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}	
	
	@Transactional(readOnly = true)
	public List searchLawList(TsLaw instance, BigDecimal lawExciseStatus, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		BigDecimal statueId = instance.getStatueId();
		BigDecimal lawTypeId = instance.getLawTypeId(); 
		String lawTitleThai = instance.getLawTitleThai(); 
		BigDecimal lawEnforceStatus = instance.getLawEnforceStatus(); 
		long lawId = instance.getLawId();
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(statueId!=null) { 
			queryStr.append(" and l.statueId  = ?");
			map.put(""+paramindex++, statueId);
		}
		if(lawTypeId!=null) { 
			queryStr.append(" and l.lawTypeId  = ?");
			map.put(""+paramindex++, lawTypeId);
		}
		if(lawTitleThai!=null && !lawTitleThai.equals("")) { 
			queryStr.append(" and lower(l.lawTitleThai) like '%"+lawTitleThai.toLowerCase()+"%'");
		}
		if(lawEnforceStatus!=null) { 
			queryStr.append(" and l.lawEnforceStatus  = ?");
			map.put(""+paramindex++, lawEnforceStatus);
		}
		if(lawExciseStatus!=null) { 
			queryStr.append(" and l.lawExciseStatus  = ?");
			map.put(""+paramindex++, lawExciseStatus);
		}
		if(lawId>0) { 
			queryStr.append(" and l.lawId  = ?");
			map.put(""+paramindex++, lawId);
		}
		String queryOrder = " order by l.lawId";
		
		Query query = session.createQuery(" from TsLaw l where 1=1 "+queryStr.toString()+queryOrder);
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		
		query = session.createQuery("select count(l) from TsLaw l where 1=1 "+queryStr.toString());
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}	
	
	@Transactional(readOnly = true)
	public TsLaw findTsLawByLawId(Long lawId){ 
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsLaw l where l.lawId = ? ");
	    query.setParameter(0, lawId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsLaw)obj;
	    }
	    return null;	 
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsLaw(TsLaw persistentInstance) {
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTsLaw(TsLaw persistentInstance) {
		return update(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsLaw(TsLaw persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	} 

	@Transactional(readOnly = true)
	public List<TsLawAttach> findTsLawAttachListByLawId(Long lawId){
		List<TsLawAttach> resultList = new ArrayList<TsLawAttach>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsLawAttach a where a.lawId  = ? ");
		query.setBigDecimal(0,BigDecimal.valueOf(lawId));
		resultList = query.list();
		return resultList;
	}
	
	@Transactional(readOnly = true)
	public TsLawAttach findTsLawAttachByAttachId(Long attachId){
		TsLawAttach tsLawAttach = new TsLawAttach();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsLawAttach a where a.lawAttachId = ? ");
		query.setParameter(0, attachId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsLawAttach)obj;
	    }
		return tsLawAttach;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsLawAttach(TsLawAttach persistentInstance){
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsLawAttach(TsLawAttach persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}

	@Transactional(readOnly = true)
	public List<TsLawStatue> findTsLawStatueListByLawId(BigDecimal lawId){
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery("from TsLawStatue s where s.lawId=? ");
		query.setParameter(0, lawId);
		List<TsLawStatue> list = query.list();
		return list;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsLawStatue(TsLawStatue persistentInstance){
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsLawStatue(TsLawStatue persistentInstance){
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}
	
	@Transactional(readOnly = true)
	public List<TsLawGood> findTsLawGoodByLawId(BigDecimal lawId){
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsLawGood g where g.lawId=? ");
		query.setParameter(0, lawId);
		List<TsLawGood> list = query.list();
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsLawGood(TsLawGood persistentInstance){
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsLawGood(TsLawGood persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}
	//------------------------------ FINISH LAW ------------------------------------------- //

	//------------------------------ START MANAGEMENT ------------------------------------- //
	@Transactional(readOnly = true)
	public List searchRoleList(String roleName, Paging paging) {
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		int paramindex= 0;
		int linkIndex=9;
		if(roleName!=null && !roleName.equals("")) { 
			queryStr.append(" and lower(r.roleName) like '%"+roleName.toLowerCase()+"%'");
		}
		String queryOrder = " order by r.roleId";
		Query query = session.createQuery(" from TmRole r where 1=1 " +queryStr.toString() + queryOrder);
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		
		query = session.createQuery("select count(r) from TmRole r where 1=1 " +queryStr.toString());
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}	
	
	@Transactional(readOnly = true)
	public TmRole findTmRoleByRoleId(String roleId) {
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TmRole c where c.roleId = ? ");
	    query.setParameter(0, roleId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TmRole)obj;
	    }
	    return null;	 
	}
	
	@Transactional(readOnly = true)
	public List searchScreenList(String screenName, Paging paging) {
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		int paramindex= 0;
		int linkIndex=9;
		if(screenName!=null && !screenName.equals("")) { 
			queryStr.append(" and lower(s.screenName) like '%"+screenName.toLowerCase()+"%'");
		}
		String queryOrder = " order by s.screenCode";
		Query query = session.createQuery(" from TmScreen s where 1=1 " +queryStr.toString()+queryOrder);
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		
		query = session.createQuery("select count(s) from TmScreen s where 1=1 " +queryStr.toString());
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}	
	
	public List<TmScreen> getScreenList(){
		List<TmScreen> resultList = new ArrayList<TmScreen>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TmScreen s order by s.screenCode ");
		resultList = query.list();
		return resultList;
	}
	
	@Transactional(readOnly = true)
	public TmScreen findTmScreenByScreenId(Long screenId) {
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TmScreen s where s.screenId = ? ");
	    query.setParameter(0, screenId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TmScreen)obj;
	    }
	    return null;	 
	}
	
	public List<TmAction> getActionList(){
		List<TmAction> resultList = new ArrayList<TmAction>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TmAction a order by a.actionName ");
		resultList = query.list();
		return resultList;
	}
	
	public List<TmScreen> getScreenListInTsRoleScreen(String roleId){
		List<TmScreen> resultList = new ArrayList<TmScreen>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TmScreen s " +
				" where s.screenId in (select rs.screenId from TsRoleScreen rs where rs.roleId = ?)" +
				" order by s.screenCode ");
		query.setParameter(0, roleId);
		resultList = query.list();
		return resultList;
	}
	
	public List<TmScreen> getScreenListNotInTsRoleScreen(String roleId){
		List<TmScreen> resultList = new ArrayList<TmScreen>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TmScreen s " +
				" where s.screenId not in (select rs.screenId from TsRoleScreen rs where rs.roleId = ?)" +
				" order by s.screenCode ");
		query.setParameter(0, roleId);
		resultList = query.list();
		return resultList;
	}
	
	public List<TmAction> getActionListByScreenId(BigDecimal screenId){
		List<TmAction> resultList = new ArrayList<TmAction>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TmAction a where a.actionId in (" +
				" select sa.actionId from TmScreenAction sa " +
				" where sa.screenId = ? ) " +
				" order by a.actionId ");
		query.setParameter(0, screenId);
		resultList = query.list();
		return resultList;
	}
	
	public List<TsRoleScreen> getTsRoleScreenByRole(String roleId, BigDecimal screenId){
		List<TsRoleScreen> resultList = new ArrayList<TsRoleScreen>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsRoleScreen rs where rs.roleId = ?" +
				" and rs.screenId = ?  " +
				" order by rs.actionId ");
		query.setParameter(0, roleId);
		query.setParameter(1, screenId);
		resultList = query.list();
		return resultList;		
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsRoleScreen(TsRoleScreen persistentInstance) {
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}


	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsRoleScreen(TsRoleScreen persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}


	@Transactional(readOnly = true)
	public TmUserinfo getUserpassword(String userId, String password){
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TmUserinfo u where u.userId = ? and u.userPassword = ? ");
	    query.setParameter(0, userId);
	    query.setParameter(1, password);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TmUserinfo)obj;
	    }		
		return null;
	}

	@Transactional(readOnly = true)
	public HashMap<String, String> getTmScreenMapByRoleId(String roleId){
		HashMap<String, String> screenMap = new HashMap<String, String>();
		List<TmScreen> resultList = new ArrayList<TmScreen>();
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TmScreen s where s.screenId in (select rs.screenId " +
		" from TsRoleScreen rs where rs.roleId = ? group by rs.screenId)");
	    query.setParameter(0, roleId);
		resultList = query.list();
		for(TmScreen tscreen : resultList){
			screenMap.put(String.valueOf(tscreen.getScreenId()), tscreen.getScreenCode()) ;
		}
		return screenMap;
	}

	@Transactional(readOnly = true)
	public List<TmScreen> getTmScreenListByRoleId(String roleId){
		List<TmScreen> resultList = new ArrayList<TmScreen>();
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TmScreen s where s.screenId in (select rs.screenId " +
				" from TsRoleScreen rs where rs.roleId = ? group by rs.screenId)");
	    query.setParameter(0, roleId);
		resultList = query.list();
		return resultList;
	}

	@Transactional(readOnly = true)
	public String getActionListByRoleIdScreenId(String roleId, String screenId){
		String actionList = "";
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TmAction a where a.actionId in (select rs.actionId " +
				" from TsRoleScreen rs where rs.roleId = ? and rs.screenId = ?)");
	    query.setParameter(0, roleId);
	    query.setParameter(1, screenId);
		List<TmAction>resultList = query.list();
		if(resultList != null && resultList.size() > 0){
			for(int i=0;i<resultList.size();i++){
				TmAction action = (TmAction)resultList.get(i);
				actionList += action.getActionName();
			}
		}
		return actionList;
	}

	@Transactional(readOnly = true)
	public List searchVConsultDocList(ConsultDocFilter instance, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		String docType = instance.getDocType();
		String consultOrg = instance.getConsultOrg();
		String officeRecNo = instance.getOfficeRecNo();
		String docNo = instance.getDocNo();
		String docDateStr = instance.getDocDateStr();
		String bureauRecNo = instance.getBureauRecNo();
		String bureauRecDateStr = instance.getBureauRecDateStr();
		String sectionRecNo = instance.getSectionRecNo();
		String sectionRecDateStr = instance.getSectionRecDateStr();
		String status = instance.getStatus();
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(docType!=null && !docType.equals("0")) { 
			queryStr.append(" and c.docType  = ?");
			map.put(""+paramindex++, docType);
		}
		if(consultOrg!=null && !consultOrg.equals("")) { 
			queryStr.append(" and lower(c.consultOrg) like '%"+consultOrg.toLowerCase()+"%'");
		}
		if(officeRecNo!=null && !officeRecNo.equals("")) { 
			queryStr.append(" and lower(c.officeRecNo) like '%"+officeRecNo.toLowerCase()+"%'");
		}
		if(docNo!=null && !docNo.equals("")) { 
			queryStr.append(" and lower(c.docNo) like '%"+docNo.toLowerCase()+"%'");
		}
		if(docDateStr!=null && !docDateStr.equals("")) { 
			queryStr.append(" and c.docDate  = to_date('"+docDateStr+"','DD/MM/YYYY')");
		}
		if(bureauRecNo!=null && !bureauRecNo.equals("")) { 
			queryStr.append(" and lower(c.bureauRecNo) like '%"+bureauRecNo.toLowerCase()+"%'");
		}
		if(bureauRecDateStr!=null && !bureauRecDateStr.equals("")) { 
			queryStr.append(" and c.bureauRecDateStr  = to_date('"+bureauRecDateStr+"','DD/MM/YYYY')");
		}
		if(sectionRecNo!=null && !sectionRecNo.equals("")) { 
			queryStr.append(" and lower(c.sectionRecNo) like '%"+sectionRecNo.toLowerCase()+"%'");
		}
		if(sectionRecDateStr!=null && !sectionRecDateStr.equals("")) { 
			queryStr.append(" and c.sectionRecDateStr  = to_date('"+sectionRecDateStr+"','DD/MM/YYYY')");
		}
		if(status!=null && !status.equals("") && !status.equals("all")){
			queryStr.append(" and c.completed  = "+status);
		}
		String queryOrder = " order by c.docId";
		Query query = session.createQuery(" from VConsultDocList c where 1=1 "+queryStr.toString()+queryOrder);
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();

		query = session.createQuery("select count(c) from VConsultDocList c where 1=1 "+queryStr.toString());
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}

	@Transactional(readOnly = true)
	public List searchMasterWord(String masterWord, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(masterWord!=null && !masterWord.equals("")) { 
			queryStr.append(" and lower(m.masterWord) like '%"+masterWord.toLowerCase()+"%'");
		}
		String queryOrder = " order by m.masterWordId";
		Query query = session.createQuery(" from TsMasterWord m where 1=1 "+queryStr.toString()+queryOrder);
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();

		query = session.createQuery("select count(m) from TsMasterWord m where 1=1 "+queryStr.toString());
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}
	
	@Transactional(readOnly = true)
	public TsMasterWord findTsMasterWordBymasterWordId(Long masterWordId){
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsMasterWord m where m.masterWordId = ? ");
	    query.setParameter(0, masterWordId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsMasterWord)obj;
	    }
	    return null;	
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsMasterWord(TsMasterWord persistentInstance) {
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTsMasterWord(TsMasterWord persistentInstance) {
		return update(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsMasterWord(TsMasterWord persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}	

	@Transactional(readOnly = true)
	public List searchThesaurus(BigDecimal masterWordId, String masterWordThesaurus, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(masterWordId!=null && !masterWordId.equals("")) { 
			queryStr.append(" and t.masterWordId = ?");
			map.put(""+paramindex++, masterWordId);
		}
		if(masterWordThesaurus!=null && !masterWordThesaurus.equals("")) { 
			queryStr.append(" and lower(t.masterWordThesaurus) like '%"+masterWordThesaurus.toLowerCase()+"%'");
		}
		String queryOrder = " order by t.thesaurusId";
		Query query = session.createQuery(" from TsThesaurus t where 1=1 "+queryStr.toString()+queryOrder);
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();

		query = session.createQuery("select count(t) from TsThesaurus t where 1=1 "+queryStr.toString());
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}
	
	@Transactional(readOnly = true)
	public TsThesaurus findTsThesaurusByThesaurusId(Long thesaurusId){
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsThesaurus t where t.thesaurusId = ? ");
	    query.setParameter(0, thesaurusId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsThesaurus)obj;
	    }
	    return null;	
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsThesaurus(TsThesaurus persistentInstance) {
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTsThesaurus(TsThesaurus persistentInstance) {
		return update(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsThesaurus(TsThesaurus persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}	
	//------------------------------ FINISH MANAGEMENT ------------------------------------ //		

	//------------------------------ START NEWS ------------------------------------------- //
	@Transactional(readOnly = true)
	public List searchNewsList(NewsFilter instance, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		String newsTopic = instance.getNewsTopic();
		String newsDisplayStatus = instance.getNewsDisplayStatus();
		String newsStartDate = DateUtils.getStringDateByStringDate(instance.getNewsStartDate());
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(newsDisplayStatus!=null && !newsDisplayStatus.equals("")) { 
			queryStr.append(" and n.newsDisplayStatus  = ?");
			map.put(""+paramindex++, newsDisplayStatus);
		}
		if(newsTopic!=null && !newsTopic.equals("")) { 
			queryStr.append(" and lower(n.newsTopic) like '%"+newsTopic.toLowerCase()+"%'");
		}
		if(newsStartDate!=null && !newsStartDate.equals("")) { 
			queryStr.append(" and n.newsStartDate  = to_date('"+newsStartDate+"','DD/MM/YYYY')");
		}
		Query query = session.createQuery(" from TsNews n where n.newsStatus = 'A' "+queryStr.toString());

		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		
		query = session.createQuery("select count(n) from TsNews n where n.newsStatus = 'A' "+queryStr.toString());

		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}	

	@Transactional(readOnly = true)
	public TsNews findTsNewsByNewsId(Long newsId) {
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsNews n where n.newsId = ? ");
	    query.setParameter(0, newsId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsNews)obj;
	    }
	    return null;	 
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsNews(TsNews persistentInstance) {
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTsNews(TsNews persistentInstance) {
		return update(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsNews(TsNews persistentInstance) {
		update(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsNewsAttach(TsNewsAttach persistentInstance){
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsNewsAttach(TsNewsAttach persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}
	
	@Transactional(readOnly = true)
	public List<TsNewsAttach> findTsNewsAttachListByNewsId(Long newsId){
		List resultList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsNewsAttach a where a.newsId  = ? ");
		query.setBigDecimal(0,BigDecimal.valueOf(newsId));
		resultList = query.list();
		return resultList;
	}

	@Transactional(readOnly = true)
	public TsNewsAttach findTsNewsAttachByAttachId(Long attachId){
		TsNewsAttach tsNewsAttach = new TsNewsAttach();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsNewsAttach a where a.attachId = ? ");
		query.setParameter(0,Long.valueOf(attachId));

	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsNewsAttach)obj;
	    }
		return tsNewsAttach;
	}
	//------------------------------ FINISH NEWS ------------------------------------------ //
	
	//------------------------------ START CONSULTDOC ------------------------------------- //	
	@Transactional(readOnly = true)
	public List<MviewEdOffice> getInternalOfficeList(){
		List<MviewEdOffice> resultList = new ArrayList<MviewEdOffice>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from MviewEdOffice o where o.indcOff in ('A','B','C') order by o.offname ");
		resultList = query.list();
		return resultList;
	}

	@Transactional(readOnly = true)
	public List<MviewEdOffice> getExternalOfficeList(){
		List<MviewEdOffice> resultList = new ArrayList<MviewEdOffice>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from MviewEdOffice o where o.indcOff in ('D','E','F') order by o.offname ");
		resultList = query.list();
		return resultList;
	}

	@Transactional(readOnly = true)
	public TmConsultDoc findTmConsultDocById(Long docId) {
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TmConsultDoc c where c.docId = ? ");
	    query.setParameter(0, docId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TmConsultDoc)obj;
	    }
	    return null;	 
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTmConsultDoc(TmConsultDoc persistentInstance) {
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTmConsultDoc(TmConsultDoc persistentInstance) {
		return update(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteConsultDoc(TmConsultDoc persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}

	@Transactional(readOnly = true)
	public TsDocState findTsDocStateByStateId(Long stateId) {
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsDocState s where s.stateId = ? ");
	    query.setParameter(0, stateId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsDocState)obj;
	    }
	    return null;	 
	}
	
	@Transactional(readOnly = true)
	public VConsultDoc findVConsultDocByDocId(Long docId) {
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from VConsultDoc d where d.docId = ? ");
	    query.setParameter(0, BigDecimal.valueOf(docId));
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (VConsultDoc)obj;
	    }
	    return null;	 
	}
	
	@Transactional(readOnly = true)
	public VConsultDocList findVConsultDocListByDocId(Long docId) {
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from VConsultDocList d where d.docId = ? ");
	    query.setParameter(0, BigDecimal.valueOf(docId));
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (VConsultDocList)obj;
	    }
	    return null;	 
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsDocState(TsDocState persistentInstance){
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTsDocState(TsDocState persistentInstance) {
		return update(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsDocAttach(TsDocAttach persistentInstance){
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteDocAttach(TsDocAttach persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}
	
	@Transactional(readOnly = true)
	public List<TsDocAttach> findTsDocAttachListByDocId(Long docId){
		List resultList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsDocAttach a where a.status ='A' and a.docId  = ? ");
		query.setBigDecimal(0,BigDecimal.valueOf(docId));
		resultList = query.list();
		return resultList;
	}

	@Transactional(readOnly = true)
	public TsDocAttach findTsDocAttachByAttachId(Long attachId){
		TsDocAttach tsDocAttach = new TsDocAttach();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsDocAttach a where a.attachId = ? ");
		query.setParameter(0,Long.valueOf(attachId));

	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsDocAttach)obj;
	    }
		return tsDocAttach;
	}
	
	@Transactional(readOnly = true)
	public List<VDocHistory> findVDocHistory(Long docId){
		List<VDocHistory> resultList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from VDocHistory h where h.docId  = ? order by h.stateId asc ");
		query.setBigDecimal(0,BigDecimal.valueOf(docId));
		resultList = query.list();
		return resultList;
	}

	@Transactional(readOnly = true)
	public List<TsDocState> findDocCommentList(Long docId){
		List<TsDocState> resultList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsDocState s where s.docId  = ? order by s.stateId asc ");		
		
		query.setBigDecimal(0,BigDecimal.valueOf(docId));
		resultList = query.list();
		return resultList;
	}
	
	
	@Transactional(readOnly = true)
	public List searchVConsultDocList(ConsultDocFilter instance, long flowId ,Paging paging) {
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		String docType = instance.getDocType();
		String consultOrg = instance.getConsultOrg();
		String officeRecNo = instance.getOfficeRecNo();
		String docNo = instance.getDocNo();
		String docDateStr = instance.getDocDateStr();
		String bureauRecNo = instance.getBureauRecNo();
		String bureauRecDateStr = instance.getBureauRecDateStr();
		String sectionRecNo = instance.getSectionRecNo();
		String sectionRecDateStr = instance.getSectionRecDateStr();
		String status = instance.getStatus();
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(docType!=null && !docType.equals("0")) { 
			queryStr.append(" and c.docType  = ?");
			map.put(""+paramindex++, docType);
		}
		if(consultOrg!=null && !consultOrg.equals("")) { 
			queryStr.append(" and lower(c.consultOrg) like '%"+consultOrg.toLowerCase()+"%'");
		}
		if(officeRecNo!=null && !officeRecNo.equals("")) { 
			queryStr.append(" and lower(c.officeRecNo) like '%"+officeRecNo.toLowerCase()+"%'");
		}
		if(docNo!=null && !docNo.equals("")) { 
			queryStr.append(" and lower(c.docNo) like '%"+docNo.toLowerCase()+"%'");
		}
		if(docDateStr!=null && !docDateStr.equals("")) { 
			queryStr.append(" and c.docDate  = to_date('"+docDateStr+"','DD/MM/YYYY')");
		}
		if(bureauRecNo!=null && !bureauRecNo.equals("")) { 
			queryStr.append(" and lower(c.bureauRecNo) like '%"+bureauRecNo.toLowerCase()+"%'");
		}
		if(bureauRecDateStr!=null && !bureauRecDateStr.equals("")) { 
			queryStr.append(" and c.bureauRecDateStr  = to_date('"+bureauRecDateStr+"','DD/MM/YYYY')");
		}
		if(sectionRecNo!=null && !sectionRecNo.equals("")) { 
			queryStr.append(" and lower(c.sectionRecNo) like '%"+sectionRecNo.toLowerCase()+"%'");
		}
		if(sectionRecDateStr!=null && !sectionRecDateStr.equals("")) { 
			queryStr.append(" and c.sectionRecDateStr  = to_date('"+sectionRecDateStr+"','DD/MM/YYYY')");
		}
		if(status!=null && !status.equals("") && !status.equals("all")){
			queryStr.append(" and c.completed  = "+status);
		}
		Query query = null;
//		if(flowId == 1){
//			query = session.createQuery(" from VConsultDocList c where c.status = 'A' " +queryStr.toString());
//		}else if(flowId == 2){
//			query = session.createQuery(" from VConsultDocList c where c.status = 'A' and c.flowId in ('2','3','4','5','6','7','8','9','10') " +queryStr.toString());
//		}else if(flowId == 3){
//			query = session.createQuery(" from VConsultDocList c where c.status = 'A' and c.flowId in ('3','5','7') " +queryStr.toString());
//		}else{
//			query = session.createQuery(" from VConsultDocList c where c.status = 'A' and c.flowId = "+flowId +queryStr.toString());
//		}
//		if(flowId == 1){
//			query = session.createQuery(" from VConsultDocList c where c.status = 'A' " +queryStr.toString());
//		}else 
		if(flowId == 0){
			query = session.createQuery(" from VConsultDocList c where c.status = 'A' and c.lockStatus = 0 "+queryStr.toString());
//			if(flowId == 99){
//				query = session.createQuery(" from VConsultDocList c where c.status = 'A' " +queryStr.toString());
//			}else 
		}else if(flowId == 2){
			query = session.createQuery(" from VConsultDocList c where c.status = 'A' and c.completed = 0 and c.lockStatus = 0 and c.flowId in ('2','7','9') " +queryStr.toString());
		}else if(flowId == 3){
			query = session.createQuery(" from VConsultDocList c where c.status = 'A' and c.completed = 0 and c.lockStatus = 0 and c.flowId in ('3','5') " +queryStr.toString());
//		}else if(flowId == 4){
//			query = session.createQuery(" from VConsultDocList c where c.status = 'A' and c.flowId not in ('1','2','3','11','12') " +queryStr.toString());
//		}else if(flowId == 5){
//			query = session.createQuery(" from VConsultDocList c where c.status = 'A' and c.flowId not in ('1','2','3','4','11','12') " +queryStr.toString());
//		}else if(flowId == 6){
//			query = session.createQuery(" from VConsultDocList c where c.status = 'A' and c.flowId not in ('1','2','3','4','5','11','12') " +queryStr.toString());
//		}else if(flowId == 9){
//			query = session.createQuery(" from VConsultDocList c where c.status = 'A' and c.flowId not in ('1','2','3','4','5','6','7','8','12') " +queryStr.toString());
		}else{
			query = session.createQuery(" from VConsultDocList c where c.status = 'A' and c.completed = 0 and c.lockStatus = 0 and c.flowId = "+flowId +queryStr.toString());
		}
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
//		if(flowId == 1){
//			query = session.createQuery("select count(c) from VConsultDocList c where c.status = 'A' " +queryStr.toString());
//		}else 

		if(flowId == 0){
			query = session.createQuery("select count(c) from VConsultDocList c where c.status = 'A' and c.lockStatus = 0 "+queryStr.toString());
//			if(flowId == 99){
//				query = session.createQuery("select count(c) from VConsultDocList c where c.status = 'A' " +queryStr.toString());
//			}else 				
		}else if(flowId == 2){
				query = session.createQuery("select count(c) from VConsultDocList c where c.status = 'A' and c.completed = 0 and c.lockStatus = 0 and c.flowId in ('2','7','9') " +queryStr.toString());
		}else if(flowId == 3){
				query = session.createQuery("select count(c) from VConsultDocList c where c.status = 'A' and c.completed = 0 and c.lockStatus = 0 and c.flowId in ('3','5') " +queryStr.toString());
//		}else if(flowId == 4){
//			query = session.createQuery("select count(c) from VConsultDocList c where c.status = 'A' and c.flowId not in ('1','2','3','11','12') " +queryStr.toString());
//		}else if(flowId == 5){
//			query = session.createQuery("select count(c) from VConsultDocList c where c.status = 'A' and c.flowId not in ('1','2','3','4','11','12') " +queryStr.toString());
//		}else if(flowId == 6){
//			query = session.createQuery("select count(c) from VConsultDocList c where c.status = 'A' and c.flowId not in ('1','2','3','4','5','11','12') " +queryStr.toString());
//		}else if(flowId == 9){
//			query = session.createQuery("select count(c) from VConsultDocList c where c.status = 'A' and c.flowId not in ('1','2','3','4','5','6','7','8','12') " +queryStr.toString());
		}else{
			query = session.createQuery("select count(c) from VConsultDocList c where c.status = 'A' and c.completed = 0 and c.lockStatus = 0 and c.flowId = "+flowId +queryStr.toString());
		}
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}	

	@Transactional(readOnly = true)
	public List findVConsultDocListByDocId(BigDecimal docId) {
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery(" from VConsultDocList c where c.docId = ?");
	    query.setParameter(0, docId);
	    Object obj = query.uniqueResult();
	    List resultList = null;
	    if(obj!=null){
	    	resultList = new ArrayList();
	    	TmConsultDoc doc = new TmConsultDoc();
	    	TsDocState state = new TsDocState();
	    	VConsultDocList vConsultDoc = (VConsultDocList)obj;
	    	doc.setAgenda(vConsultDoc.getAgenda()!=null?vConsultDoc.getAgenda():"");
	    	doc.setConsultDetail(vConsultDoc.getConsultDetail()!=null?vConsultDoc.getConsultDetail():"");
	    	doc.setConsultOrg(vConsultDoc.getConsultOrg()!=null?vConsultDoc.getConsultOrg():"");
	    	doc.setDocId(vConsultDoc.getDocId()!=null?vConsultDoc.getDocId().longValue():null);
	    	doc.setDocNo(vConsultDoc.getDocNo()!=null?vConsultDoc.getDocNo():"");
	    	doc.setDocType(vConsultDoc.getDocType()!=null?vConsultDoc.getDocType():"");
	    	doc.setDocDate(vConsultDoc.getDocDate()!=null?vConsultDoc.getDocDate():null);
	    	doc.setInform(vConsultDoc.getInform()!=null?vConsultDoc.getInform():"");
	    	doc.setOfficeRecNo(vConsultDoc.getOfficeRecNo()!=null?vConsultDoc.getOfficeRecNo():"");
	    	doc.setOfficeRecDate(vConsultDoc.getOfficeRecDate()!=null?vConsultDoc.getOfficeRecDate():null);
	    	doc.setBureauRecNo(vConsultDoc.getBureauRecNo()!=null?vConsultDoc.getBureauRecNo():"");
	    	doc.setBureauRecDate(vConsultDoc.getBureauRecDate()!=null?vConsultDoc.getBureauRecDate():null);
	    	doc.setReferTo(vConsultDoc.getReferTo()!=null?vConsultDoc.getReferTo():"");
	    	doc.setSecurityLevel(vConsultDoc.getSecurityLevel()!=null?vConsultDoc.getSecurityLevel():null);
	    	doc.setSectionRecNo(vConsultDoc.getSectionRecNo()!=null?vConsultDoc.getSectionRecNo():"");
	    	doc.setSectionRecDate(vConsultDoc.getSectionRecDate()!=null?vConsultDoc.getSectionRecDate():null);
	    	doc.setCreatedBy(vConsultDoc.getDoccreatedBy()!=null?vConsultDoc.getDoccreatedBy():"");
	    	doc.setCreatedDate(vConsultDoc.getDoccreatedDate()!=null?vConsultDoc.getDoccreatedDate():null);
	    	doc.setDocSendNo(vConsultDoc.getDocSendNo()!=null?vConsultDoc.getDocSendNo():"");
	    	doc.setDocSendDate(vConsultDoc.getDocSendDate()!=null?vConsultDoc.getDocSendDate():null);
	    	doc.setDocSendNo(vConsultDoc.getDocSendNo()!=null?vConsultDoc.getDocSendNo():"");
	    	doc.setDocSendDate(vConsultDoc.getDocSendDate()!=null?vConsultDoc.getDocSendDate():null);
	    	doc.setBureauSendNo(vConsultDoc.getBureauSendNo()!=null?vConsultDoc.getBureauSendNo():"");
	    	doc.setBureauSendDate(vConsultDoc.getBureauSendDate()!=null?vConsultDoc.getBureauSendDate():null);
	    	doc.setSectionSendNo(vConsultDoc.getSectionSendNo()!=null?vConsultDoc.getSectionSendNo():"");
	    	doc.setSectionSendDate(vConsultDoc.getSectionSendDate()!=null?vConsultDoc.getSectionSendDate():null);
	    	doc.setDocRefNo(vConsultDoc.getDocRefNo()!=null?vConsultDoc.getDocRefNo():"");
	    	doc.setDocRefDate(vConsultDoc.getDocRefDate()!=null?vConsultDoc.getDocRefDate():null);
	    	doc.setDocAttachment(vConsultDoc.getDocAttachment()!=null?vConsultDoc.getDocAttachment():"");

	    	state.setStateId(vConsultDoc.getStateId()!=0?vConsultDoc.getStateId():null);
	    	state.setDocComment(vConsultDoc.getDocComment()!=null?vConsultDoc.getDocComment():"");
	    	state.setAssignLawyer(vConsultDoc.getAssignLawyer()!=null?vConsultDoc.getAssignLawyer():null);
	    	state.setAssignProf(vConsultDoc.getAssignProf()!=null?vConsultDoc.getAssignProf():null);
	    	state.setFlowId(vConsultDoc.getFlowId()!=null?vConsultDoc.getFlowId():null);
	    	state.setToSection(vConsultDoc.getToSection()!=null?vConsultDoc.getToSection():null);
	    	state.setCreatedDate(vConsultDoc.getStatecreatedDate()!=null?vConsultDoc.getStatecreatedDate():null);
	    	resultList.add(doc);
	    	resultList.add(state);
	    }
	    return resultList;	 
	}
	
	@Transactional(readOnly = true)
	public List findVConsultDocByDocId(BigDecimal docId) {
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery(" from VConsultDoc c where c.docId = ?");
	    query.setParameter(0, docId);
	    Object obj = query.uniqueResult();
	    List resultList = null;
	    if(obj!=null){
	    	resultList = new ArrayList();
	    	TmConsultDoc doc = new TmConsultDoc();
	    	TsDocState state = new TsDocState();
	    	VConsultDoc vConsultDoc = (VConsultDoc)obj;
	    	doc.setAgenda(vConsultDoc.getAgenda()!=null?vConsultDoc.getAgenda():"");
	    	doc.setConsultDetail(vConsultDoc.getConsultDetail()!=null?vConsultDoc.getConsultDetail():"");
	    	doc.setConsultOrg(vConsultDoc.getConsultOrg()!=null?vConsultDoc.getConsultOrg():"");
	    	doc.setDocId(vConsultDoc.getDocId()!=null?vConsultDoc.getDocId().longValue():null);
	    	doc.setDocNo(vConsultDoc.getDocNo()!=null?vConsultDoc.getDocNo():"");
	    	doc.setDocType(vConsultDoc.getDocType()!=null?vConsultDoc.getDocType():"");
	    	doc.setDocDate(vConsultDoc.getDocDate()!=null?vConsultDoc.getDocDate():null);
	    	doc.setInform(vConsultDoc.getInform()!=null?vConsultDoc.getInform():"");
	    	doc.setOfficeRecNo(vConsultDoc.getOfficeRecNo()!=null?vConsultDoc.getOfficeRecNo():"");
	    	doc.setOfficeRecDate(vConsultDoc.getOfficeRecDate()!=null?vConsultDoc.getOfficeRecDate():null);
	    	doc.setBureauRecNo(vConsultDoc.getBureauRecNo()!=null?vConsultDoc.getBureauRecNo():"");
	    	doc.setBureauRecDate(vConsultDoc.getBureauRecDate()!=null?vConsultDoc.getBureauRecDate():null);
	    	doc.setReferTo(vConsultDoc.getReferTo()!=null?vConsultDoc.getReferTo():"");
	    	doc.setSecurityLevel(vConsultDoc.getSecurityLevel()!=null?vConsultDoc.getSecurityLevel():null);
	    	doc.setSectionRecNo(vConsultDoc.getSectionRecNo()!=null?vConsultDoc.getSectionRecNo():"");
	    	doc.setSectionRecDate(vConsultDoc.getSectionRecDate()!=null?vConsultDoc.getSectionRecDate():null);
	    	doc.setCreatedBy(vConsultDoc.getDoccreatedBy()!=null?vConsultDoc.getDoccreatedBy():"");
	    	doc.setCreatedDate(vConsultDoc.getDoccreatedDate()!=null?vConsultDoc.getDoccreatedDate():null);
	    	doc.setDocSendNo(vConsultDoc.getDocSendNo()!=null?vConsultDoc.getDocSendNo():"");
	    	doc.setDocSendDate(vConsultDoc.getDocSendDate()!=null?vConsultDoc.getDocSendDate():null);
	    	doc.setBureauSendNo(vConsultDoc.getBureauSendNo()!=null?vConsultDoc.getBureauSendNo():"");
	    	doc.setBureauSendDate(vConsultDoc.getBureauSendDate()!=null?vConsultDoc.getBureauSendDate():null);
	    	doc.setSectionSendNo(vConsultDoc.getSectionSendNo()!=null?vConsultDoc.getSectionSendNo():"");
	    	doc.setSectionSendDate(vConsultDoc.getSectionSendDate()!=null?vConsultDoc.getSectionSendDate():null);
	    	doc.setDocRefNo(vConsultDoc.getDocRefNo()!=null?vConsultDoc.getDocRefNo():"");
	    	doc.setDocRefDate(vConsultDoc.getDocRefDate()!=null?vConsultDoc.getDocRefDate():null);
	    	doc.setDocAttachment(vConsultDoc.getDocAttachment()!=null?vConsultDoc.getDocAttachment():"");
	    	
	    	state.setStateId(vConsultDoc.getStateId()!=0?vConsultDoc.getStateId():null);
	    	state.setDocComment(vConsultDoc.getDocComment()!=null?vConsultDoc.getDocComment():"");
	    	state.setAssignLawyer(vConsultDoc.getAssignLawyer()!=null?vConsultDoc.getAssignLawyer():null);
	    	state.setAssignProf(vConsultDoc.getAssignProf()!=null?vConsultDoc.getAssignProf():null);
	    	state.setFlowId(vConsultDoc.getFlowId()!=null?vConsultDoc.getFlowId():null);
	    	state.setToSection(vConsultDoc.getToSection()!=null?vConsultDoc.getToSection():null);
	    	state.setCreatedBy(vConsultDoc.getStatecreatedBy()!=null?vConsultDoc.getStatecreatedBy():"");
	    	state.setCreatedDate(vConsultDoc.getStatecreatedDate()!=null?vConsultDoc.getStatecreatedDate():null);
	    	resultList.add(doc);
	    	resultList.add(state);
	    }
	    return resultList;	 
	}
	
	@Transactional(readOnly = true)
	public List <MviewOrganization> listOrganization(String orgId) throws DataAccessException {
		List<MviewOrganization> list = this.sessionAnnotationFactory.getCurrentSession().
				createQuery("from MviewOrganization o where o.orgIdRef = 719 "+((orgId!=null)?" and o.orgId="+orgId:"")+"order by o.orgName ").list();
		return list;
	}

	@Transactional(readOnly = true)
	public List<TsDocStatue> findTsDocStatueBydocId(BigDecimal docId){
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery("from TsDocStatue s where s.docId=? ");
		query.setParameter(0, docId);
		List<TsDocStatue> list = query.list();
		return list;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsDocStatue(TsDocStatue persistentInstance){
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsDocStatue(TsDocStatue persistentInstance){
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}
	
	@Transactional(readOnly = true)
	public List<MviewPersonel> getLawyerList(String orgId) throws DataAccessException {
		List<MviewPersonel> list = this.sessionAnnotationFactory.getCurrentSession().
				createQuery("from MviewPersonel p where trim(p.plCode) = '512403' and p.orgId1 = "+orgId+" order by p.perName||' '||p.perSurname ").list();
		return list;
	}
	
	@Transactional(readOnly = true)
	public List<MviewPersonel> getProfessionalList() throws DataAccessException {
		List<MviewPersonel> list = this.sessionAnnotationFactory.getCurrentSession().
				createQuery("from MviewPersonel p where trim(p.pmCode) in ('0-98','0601') order by p.perName||' '||p.perSurname ").list();
		return list;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsDocResolution(TsDocResolution persistentInstance){
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(readOnly = true)
	public TsDocResolution findTsDocResolution(Long restId){
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery("from TsDocResolution r where r.restId = ? ");
		query.setParameter(0, restId);
		Object obj = query.uniqueResult();
		if(obj!=null){
			return (TsDocResolution)obj;
		}
		return null;	 
	}
	
	@Transactional(readOnly = true)
	public TsDocResolution findLastTsDocResolution(Long docId) {
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery("from TsDocResolution d " +
				"where d.docId = ? and d.restId = (select max(r.restId) from TsDocResolution r where r.docId = ?) ");
	    query.setParameter(0, docId);
	    query.setParameter(1, docId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsDocResolution)obj;
	    }
	    return null;	 
	}
	
	@Transactional(readOnly = true)
	public List<TsDocResolution> findTsDocResolutionList(Long docId) {
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsDocResolution s where s.docId = ? order by s.stateId");
	    query.setParameter(0, docId);
	    List<TsDocResolution> list  = query.list();
	    return list;	 
	}

	@Transactional(readOnly = true)
	public List<TsDocResolution> getTsDocResolutionHistory(Long docId) {
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery("from TsDocResolution d " +
		"where d.docId = ? and d.restId not in (select max(r.restId) from TsDocResolution r where r.docId = ?) ");
	    query.setParameter(0, docId);
	    query.setParameter(1, docId);
	    List<TsDocResolution> list  = query.list();
	    return list;	 
	}
	//------------------------------ FINISH CONSULTDOC ------------------------------------ //	
	
	//------------------------------ START CONSULTPUBLISH --------------------------------- //
	@Transactional(readOnly = true)
	public List<MviewDutyGroup> listMviewDutyGroupGoods(){
		List<MviewDutyGroup> list = this.sessionAnnotationFactory.getCurrentSession().
				createQuery(" from MviewDutyGroup d where d.groupId not in ('0901', '0902', '1001', '1002', '1101', '1201') "+
								" and d.groupStatus = 'N' order by d.groupId ").list();
		return list;
	}
	
	@Transactional(readOnly = true)
	public List <MviewDutyGroup> listMviewDutyGroupServices(){
		List<MviewDutyGroup> list = this.sessionAnnotationFactory.getCurrentSession().
				createQuery(" from MviewDutyGroup d where d.groupId in ('0901', '0902', '1001', '1002', '1101', '1201') "+
							" and d.groupStatus = 'N' order by d.groupId ").list();
		return list;
	}
	
	@Transactional(readOnly = true)
	public List searchConsultPublishList(TsDiscussPublished instance, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		String publishedName = instance.getPublishedName();
		BigDecimal publishedDisplay = instance.getPublishedDisplay();
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(publishedName!=null && !publishedName.equals("")) { 
			queryStr.append(" and lower(p.publishedName) like '%"+publishedName.toLowerCase()+"%'");
		}
		if(publishedDisplay!=null && !publishedDisplay.equals("")) { 
			queryStr.append(" and p.publishedDisplay  = ?");
			map.put(""+paramindex++, publishedDisplay);
		}
		Query query = session.createQuery(" from TsDiscussPublished p where p.publishedStatus = 'A' "+queryStr.toString());

		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		
		query = session.createQuery("select count(p) from TsDiscussPublished p where p.publishedStatus = 'A' "+queryStr.toString());

		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}	
	

	@Transactional(readOnly = true)
	public TsDiscussPublished findTsDiscussPublishedByPublishId(Long publishedId){
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsDiscussPublished p where p.publishedId = ? ");
	    query.setParameter(0, publishedId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsDiscussPublished)obj;
	    }
	    return null;	 
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsDiscussPublished(TsDiscussPublished persistentInstance) {
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTsDiscussPublished(TsDiscussPublished persistentInstance) {
		return update(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsDiscussPublished(TsDiscussPublished persistentInstance) {
		update(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}	

	@Transactional(readOnly = true)
	public List<TsPublishedStatue> findTsPublishedStatueByPublishedId(BigDecimal publishedId){
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsPublishedStatue s where s.publishedId=? ");
		query.setParameter(0, publishedId);
		List<TsPublishedStatue> list = query.list();
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsPublishedStatue(TsPublishedStatue persistentInstance){
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsPublishedStatue(TsPublishedStatue persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}
	
	@Transactional(readOnly = true)
	public List<TsPublishedGood> findTsPublishedGoodByPublishedId(BigDecimal publishedId){
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsPublishedGood g where g.publishedId=? ");
		query.setParameter(0, publishedId);
		List<TsPublishedGood> list = query.list();
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsPublishedGood(TsPublishedGood persistentInstance){
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsPublishedGood(TsPublishedGood persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}

	@Transactional(readOnly = true)
	public List<TsPublishedAttach> findTsPublishedAttachListByPublishId(Long publishId){
		List resultList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsPublishedAttach a where a.discussPublishedId  = ? ");
		query.setBigDecimal(0,BigDecimal.valueOf(publishId));
		resultList = query.list();
		return resultList;
	}
	
	@Transactional(readOnly = true)
	public TsPublishedAttach findTsPublishedAttachByAttachId(Long attachId){
		TsPublishedAttach tsPublishedAttach = new TsPublishedAttach();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsPublishedAttach a where a.publishedAttachId = ? ");
		query.setParameter(0, attachId);

	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsPublishedAttach)obj;
	    }
		return tsPublishedAttach;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsPublishedAttach(TsPublishedAttach persistentInstance){
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsPublishedAttach(TsPublishedAttach persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}
	
	@Transactional(readOnly = true)
	public MsStatutesub getMsStatutesubById(Long statutesubId) throws DataAccessException {
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from MsStatutesub s where s.statutesubId = ? ");
	    query.setParameter(0, statutesubId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (MsStatutesub)obj;
	    }
	    return null;
	}
	
	@Transactional(readOnly = true)
	public List<MsStatutesub> getMsStatutesubList() throws DataAccessException {
		List<MsStatutesub> list = this.sessionAnnotationFactory.getCurrentSession().createQuery("from MsStatutesub s order by s.statutesubId ").list();
		return list;
	}
	
	public List<StStatutesub> getStStatutesubList(Long statuteId, Long statutesubId){
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery("from StStatutesub s where s.msStatute.statuteId = ? and s.msStatutesub.statutesubId = ? order by s.article ");
		query.setParameter(0, statuteId);
		query.setParameter(1, statutesubId);
		List<StStatutesub> list = query.list();
		return list;
	}
	
	public List<TmConsultDoc> getConsultDocComplete(){
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery("from TmConsultDoc d where d.status = 'A' and d.completed = 1 order by d.docId ");
		List<TmConsultDoc> list = query.list();
		return list;
	}

	//------------------------------ FINISH CONSULTPUBLISH -------------------------------- //

	//------------------------------ START JUDGEMENT -------------------------------------- //
	@Transactional(readOnly = true)
	public List<TmJudgementType> findTmJudgementTypeByGroup(BigDecimal judgementGroup){
		List<TmJudgementType> list = this.sessionAnnotationFactory.getCurrentSession().
			createQuery("from TmJudgementType t "+((judgementGroup!=null)?" where t.judgementGroup="+judgementGroup:"")+" order by t.judgementTypeId ").list();
		return list;		
	}
	
	@Transactional(readOnly = true)
	public List searchJudgementList(TsJudgement instance, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		String judgementNumber1 = instance.getJudgementNumber1();
		String accuser = instance.getAccuser();
		String accused = instance.getAccused();
		String judgementTopic = instance.getJudgementTopic();
		String judgementKeyword = instance.getJudgementKeyword();
		
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(judgementNumber1!=null && !judgementNumber1.equals("")) { 
			queryStr.append(" and lower(j.judgementNumber1) like '%"+judgementNumber1.toLowerCase()+"%'");
		}
		if(accuser!=null && !accuser.equals("")) { 
			queryStr.append(" and lower(j.accuser) like '%"+accuser.toLowerCase()+"%'");
		}
		if(accused!=null && !accused.equals("")) { 
			queryStr.append(" and lower(j.accused) like '%"+accused.toLowerCase()+"%'");
		}
		if(judgementTopic!=null && !judgementTopic.equals("")) { 
			queryStr.append(" and lower(j.judgementTopic) like '%"+judgementTopic.toLowerCase()+"%'");
		}
		if(judgementKeyword!=null && !judgementKeyword.equals("")) { 
			queryStr.append(" and lower(j.judgementKeyword) like '%"+judgementKeyword.toLowerCase()+"%'");
		}
		
		Query query = session.createQuery(" from TsJudgement j where j.judgementStatus = 'A' and j.judgementType  = 1 "+queryStr.toString());
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();

		query = session.createQuery("select count(j) from TsJudgement j where j.judgementStatus = 'A' and j.judgementType  = 1 "+queryStr.toString());		
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}		

	@Transactional(readOnly = true)
	public List searchJusticeCourtList(TsJudgement instance, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		String judgementNumber1 = instance.getJudgementNumber1();
		String accuser = instance.getAccuser();
		String accused = instance.getAccused();
		String judgementTopic = instance.getJudgementTopic();
		String judgementKeyword = instance.getJudgementKeyword();
		BigDecimal judgementType = instance.getJudgementType();
		
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(judgementNumber1!=null && !judgementNumber1.equals("")) { 
			queryStr.append(" and lower(j.judgementNumber1) like '%"+judgementNumber1.toLowerCase()+"%'");
		}
		if(accuser!=null && !accuser.equals("")) { 
			queryStr.append(" and lower(j.accuser) like '%"+accuser.toLowerCase()+"%'");
		}
		if(accused!=null && !accused.equals("")) { 
			queryStr.append(" and lower(j.accused) like '%"+accused.toLowerCase()+"%'");
		}
		if(judgementTopic!=null && !judgementTopic.equals("")) { 
			queryStr.append(" and lower(j.judgementTopic) like '%"+judgementTopic.toLowerCase()+"%'");
		}
		if(judgementKeyword!=null && !judgementKeyword.equals("")) { 
			queryStr.append(" and lower(j.judgementKeyword) like '%"+judgementKeyword.toLowerCase()+"%'");
		}
		if(judgementType!=null) { 
			queryStr.append(" and j.judgementType  = ? ");
			map.put(""+paramindex++, judgementType);
		}else{
			queryStr.append(" and j.judgementType in (2, 3, 4) ");
		}
		Query query = session.createQuery(" from VJudgement j where j.judgementStatus = 'A' "+queryStr.toString());
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();

		query = session.createQuery("select count(j) from VJudgement j where j.judgementStatus = 'A' "+queryStr.toString());		
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}		

	@Transactional(readOnly = true)
	public List searchAdministrativeCourtList(TsJudgement instance, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		String judgementNumber1 = instance.getJudgementNumber1();
		String judgementNumber2 = instance.getJudgementNumber2();
		String accuser = instance.getAccuser();
		String accused = instance.getAccused();
		String judgementTopic = instance.getJudgementTopic();
		String judgementKeyword = instance.getJudgementKeyword();
		BigDecimal judgementType = instance.getJudgementType();
		
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(judgementNumber1!=null && !judgementNumber1.equals("")) { 
			queryStr.append(" and lower(j.judgementNumber1) like '%"+judgementNumber1.toLowerCase()+"%'");
		}
		if(judgementNumber2!=null && !judgementNumber2.equals("")) { 
			queryStr.append(" and lower(j.judgementNumber2) like '%"+judgementNumber2.toLowerCase()+"%'");
		}
		if(accuser!=null && !accuser.equals("")) { 
			queryStr.append(" and lower(j.accuser) like '%"+accuser.toLowerCase()+"%'");
		}
		if(accused!=null && !accused.equals("")) { 
			queryStr.append(" and lower(j.accused) like '%"+accused.toLowerCase()+"%'");
		}
		if(judgementTopic!=null && !judgementTopic.equals("")) { 
			queryStr.append(" and lower(j.judgementTopic) like '%"+judgementTopic.toLowerCase()+"%'");
		}
		if(judgementKeyword!=null && !judgementKeyword.equals("")) { 
			queryStr.append(" and lower(j.judgementKeyword) like '%"+judgementKeyword.toLowerCase()+"%'");
		}
		if(judgementType!=null) { 
			queryStr.append(" and j.judgementType  = ? ");
			map.put(""+paramindex++, judgementType);
		}else{
			queryStr.append(" and j.judgementType in (5, 6) ");
		}
		Query query = session.createQuery(" from VJudgement j where j.judgementStatus = 'A' "+queryStr.toString());
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();

		query = session.createQuery("select count(j) from VJudgement j where j.judgementStatus = 'A' "+queryStr.toString());		
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}		

	@Transactional(readOnly = true)
	public List searchOtherCourtList(TsJudgement instance, Paging paging){
		ArrayList transList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		StringBuffer queryStr  =new StringBuffer();
		String agencyConsultant = instance.getAgencyConsultant();
		String judgementTopic = instance.getJudgementTopic();
		String judgementKeyword = instance.getJudgementKeyword();
		BigDecimal judgementType = instance.getJudgementType();
		
		Map map =  new HashMap();
		int paramindex= 0;
		int linkIndex=9;
		if(judgementTopic!=null && !judgementTopic.equals("")) { 
			queryStr.append(" and lower(j.judgementTopic) like '%"+judgementTopic.toLowerCase()+"%'");
		}
		if(judgementKeyword!=null && !judgementKeyword.equals("")) { 
			queryStr.append(" and lower(j.judgementKeyword) like '%"+judgementKeyword.toLowerCase()+"%'");
		}
		if(judgementType!=null) { 
			queryStr.append(" and j.judgementType  = ? ");
			map.put(""+paramindex++, judgementType);
		}else{
			queryStr.append(" and j.judgementType in (7, 8) ");
		}
		Query query = session.createQuery(" from VJudgement j where j.judgementStatus = 'A' "+queryStr.toString());
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();

		query = session.createQuery("select count(j) from VJudgement j where j.judgementStatus = 'A' "+queryStr.toString());		
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		int count = Integer.parseInt(query.uniqueResult().toString());
		transList.add(list); 
		transList.add(count+""); 
		return transList;
	}		
	
	@Transactional(readOnly = true)
	public TsJudgement findTsJudgementByjudgementId(Long judgementId){
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsJudgement j where j.judgementId = ? ");
	    query.setParameter(0, judgementId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsJudgement)obj;
	    }
	    return null;	 
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsJudgement(TsJudgement persistentInstance) {
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateTsJudgement(TsJudgement persistentInstance) {
		return update(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsJudgement(TsJudgement persistentInstance) {
		update(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}

	@Transactional(readOnly = true)
	public List<TsJudgementStatue> findTsJudgementStatueByJudgementId(BigDecimal judgementId){
		Query query = this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsJudgementStatue s where s.judgementId=? ");
		query.setParameter(0, judgementId);
		List<TsJudgementStatue> list = query.list();
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsJudgementStatue(TsJudgementStatue persistentInstance){
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsJudgementStatue(TsJudgementStatue persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}

	@Transactional(readOnly = true)
	public List<TsJudgementAttach> findTsJudgementAttachListByJudgementId(Long judgementId){
		List<TsJudgementAttach> resultList = new ArrayList();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsJudgementAttach a where a.judgementId = ? ");
		query.setBigDecimal(0, BigDecimal.valueOf(judgementId));
		resultList = query.list();
		return resultList;
	}
	
	@Transactional(readOnly = true)
	public TsJudgementAttach findTsJudgementAttachByAttachId(Long attachId){
		TsJudgementAttach tsNewsAttachAttach = new TsJudgementAttach();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsJudgementAttach a where a.judgementAttachId = ? ");
		query.setParameter(0, attachId);

	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsJudgementAttach)obj;
	    }
		return tsNewsAttachAttach;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveTsJudgementAttach(TsJudgementAttach persistentInstance){
		return save(this.sessionAnnotationFactory.getCurrentSession(), persistentInstance);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public void deleteTsJudgementAttach(TsJudgementAttach persistentInstance) {
		delete(this.sessionAnnotationFactory.getCurrentSession(),persistentInstance);	
	}
	//------------------------------ FINISH JUDGEMENT ------------------------------------- //
}
