package com.excise.law.service.jpa;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excise.law.domain.MsStatute;
import com.excise.law.domain.MsStatutesub;
import com.excise.law.domain.StStatutesub;
import com.excise.law.domain.TmArticleGroup;
import com.excise.law.domain.TmArticleSection;
import com.excise.law.domain.TmJudgementType;
import com.excise.law.domain.TmLawType;
import com.excise.law.domain.TmStatue;
import com.excise.law.domain.TsArticle;
import com.excise.law.domain.TsExArticleCompleted;
import com.excise.law.domain.TsExArticleHeader;
import com.excise.law.domain.TsExArticleHeaderAttach;
import com.excise.law.domain.TsLaw;
import com.excise.law.domain.TsLawAttach;
import com.excise.law.domain.TsLawGood;
import com.excise.law.domain.TsLawStatue;
import com.excise.law.domain.TsMasterWord;
import com.excise.law.domain.TsStatueAttach;
import com.excise.law.domain.TsStatueGood;
import com.excise.law.service.LawService;
import com.excise.law.utils.Paging;

/**
 * @author Chatchai Pimtun
 */
@Repository
@Transactional
public class LawServiceImpl extends LawServiceCommon implements LawService {

	private SessionFactory sessionAnnotationFactory;

	@Autowired
	public LawServiceImpl(SessionFactory sessionAnnotationFactory) {
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
	@Transactional(readOnly = true)
	public MsStatute getMsStatuteById(Long statuteId) throws DataAccessException {
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from MsStatute s where s.statuteId = ? ");
	    query.setParameter(0, statuteId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (MsStatute)obj;
	    }
	    return null;
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
	public List<TmLawType> getTmLawTypeListByPriority1(){
		List<TmLawType> resultList = new ArrayList<TmLawType>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TmLawType l where l.lawTypePriority=1 order by l.lawTypeOrder ");
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
	public List<TsExArticleCompleted> getTsArticleCompletedList(BigDecimal statueId){
		List<TsExArticleCompleted> resultList = new ArrayList<TsExArticleCompleted>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsExArticleCompleted a where a.statueId = ? order by a.articleCompletedOrder ");
		query.setParameter(0, statueId);
		resultList = query.list();
		return resultList;		
	}
	
	@Transactional(readOnly = true)
	public List<TsArticle> getTsArticleList(BigDecimal statueId, BigDecimal lawTypeId){
		List<TsArticle> resultList = new ArrayList<TsArticle>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsArticle a where a.statueId = ? and a.lawTypeId = ? order by a.articleOrder ");
		query.setParameter(0, statueId);
		query.setParameter(1, lawTypeId);
		resultList = query.list();
		return resultList;		
	}
	
	@Transactional(readOnly = true)
	public List<TsLaw> getTsLawList(BigDecimal statueId, BigDecimal lawTypeId){
		List<TsLaw> resultList = new ArrayList<TsLaw>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsLaw l where l.statueId = ? and l.lawTypeId = ? order by l.lawOrder ");
		query.setParameter(0, statueId);
		query.setParameter(1, lawTypeId);
		resultList = query.list();
		return resultList;		
	}

	@Transactional(readOnly = true)
	public void getMetaDataFromTable(){ 
		ResultSet tables = null;	
		try {
			tables = this.sessionAnnotationFactory.getCurrentSession().connection().getMetaData().getTables(null, null, "Ts_Article", null);
			while(tables.next()){  
	            String currentTableName = tables.getString("TABLE_NAME");  
	            System.out.println("currentTableName : "+currentTableName);
	            if(currentTableName.equalsIgnoreCase("Ts_Article")){  
	            	
	                break;  
	            }  
				tables.close();
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}  	 
	}

	@Transactional(readOnly = true)
	public List<TsMasterWord> getTsMasterWordList(){
		List<TsMasterWord> resultList = new ArrayList<TsMasterWord>();
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session.createQuery(" from TsMasterWord w order by w.masterWordId ");
		resultList = query.list();
		return resultList;		
	}

	@Transactional(readOnly = true)
	public TsMasterWord findTsMasterWordByMasterWordId(Long masterWordId){
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery(" from TsMasterWord w where w.masterWordId = ? ");
	    query.setParameter(0, masterWordId);
	    Object obj = query.uniqueResult();
	    if(obj!=null){
	    	return (TsMasterWord)obj;
	    }
	    return null;		
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
		Query query= this.sessionAnnotationFactory.getCurrentSession().createQuery("from TsExArticleCompleted c where c.statueId = ? group by c.articleHeaderId, c.statueId");
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
		
		Query query = session.createQuery(" from VLaw l where 1=1 "+queryStr.toString()+queryOrder);
	
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			int index = Integer.parseInt(key);
			Object value= (linkIndex==index)?"'%"+map.get(key)+"%'":map.get(key);
			query.setParameter(index, value);
		}
		query.setFirstResult(paging.getPageSize() * (paging.getPageNo() - 1));
		query.setMaxResults(paging.getPageSize());
		List list = query.list();
		
		query = session.createQuery("select count(l) from VLaw l where 1=1 "+queryStr.toString());
	
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

	//------------------------------ START JUDMENT ---------------------------------------- //
	public List<TmJudgementType> getTmJudgementGroup(){
		Criteria crit = this.sessionAnnotationFactory.getCurrentSession().createCriteria(TmJudgementType.class)		
			.setProjection( Projections.distinct( Projections.projectionList()
			.add( Projections.property("judgementGroup"), "judgementGroup")
			.add( Projections.property("groupDescription"), "groupDescription")))
			.setResultTransformer(Transformers.aliasToBean(TmJudgementType.class))
			.addOrder(Order.asc("judgementGroup"));
		List<TmJudgementType> list = crit.list();
		return list;
	}
	
	public List<TmJudgementType> getJudgementTypeByJudgementGroup(BigDecimal judgementGroup){
		Criteria crit = this.sessionAnnotationFactory.getCurrentSession().createCriteria(TmJudgementType.class)		
			.add( Restrictions.eq("judgementGroup", judgementGroup))
			.addOrder(Order.asc("judgementTypeId"));
		List<TmJudgementType> list = crit.list();
		return list;
	}
	//------------------------------ FINISH JUDMENT --------------------------------------- //
}
