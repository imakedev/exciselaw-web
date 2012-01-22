package com.excise.law.service.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excise.law.domain.MsStatute;
import com.excise.law.domain.MsStatutesub;
import com.excise.law.domain.StMatr;
import com.excise.law.domain.StStatutesub;
import com.excise.law.domain.TmArticleGroup;
import com.excise.law.domain.TmArticleSection;
import com.excise.law.domain.TmLawType;
import com.excise.law.domain.TmStatue;
import com.excise.law.domain.TsArticle;
import com.excise.law.domain.TsExArticleCompleted;
import com.excise.law.domain.TsExArticleHeader;
import com.excise.law.domain.TsLaw;
import com.excise.law.domain.TsRel;
import com.excise.law.domain.TsRelDTO;
import com.excise.law.domain.TsRelKeyMap;
import com.excise.law.domain.TsRelMap;
import com.excise.law.domain.TsRelTableMap;
import com.excise.law.service.LinkExciseLawService;

/**
 * @author Chatchai Pimtun
 */
@Repository
@Transactional
public class LinkExciseLawServiceImpl extends LawServiceCommon implements
		LinkExciseLawService {

	private SessionFactory sessionAnnotationFactory;

	@Autowired
	public LinkExciseLawServiceImpl(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<TsRelTableMap> listRelTableMap() {
		return (List<TsRelTableMap>) this.sessionAnnotationFactory
				.getCurrentSession()
				.createQuery("from TsRelTableMap tsrelTableMap").list();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public void deleteRelKeyMap(TsRelKeyMap persistentInstance) {
		// TODO Auto-generated method stub
		//System.out.println("un link " + persistentInstance.getRkmId());
		delete(this.sessionAnnotationFactory.getCurrentSession(),
				persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public Long saveRelKeyMap(TsRelKeyMap persistentInstance) {
		// TODO Auto-generated method stub
		/*System.out.println("  persistentInstance.getRelTableMap()===>"
				+ persistentInstance.getRelTableMap());
		System.out.println("  persistentInstance.getRkmName()===>"
				+ persistentInstance.getRkmName());*/
		return save(this.sessionAnnotationFactory.getCurrentSession(),
				persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public int updateRelKeyMap(TsRelKeyMap transientInstance) {
		// TODO Auto-generated method stub
		/*System.out.println("  updateRelKeyMap getRkmId ()===>"
				+ transientInstance.getRkmId());
		System.out.println("  updateRelKeyMap getRkmKey ()===>"
				+ transientInstance.getRkmKey());
		System.out.println("  updateRelKeyMap getRelTableMap().getRtmId ()===>"
				+ transientInstance.getRelTableMap().getRtmId());*/
		return update(this.sessionAnnotationFactory.getCurrentSession(),
				transientInstance);
	}

	@Transactional(readOnly = true)
	public TsRelKeyMap getRelKeyMapById(Long rkmId) {
		// TODO Auto-generated method stub
		Query query = this.sessionAnnotationFactory
				.getCurrentSession()
				.createQuery(
						" from TsRelKeyMap tsrelKeyMap where tsrelKeyMap.rkmId=?  ");
		query.setParameter(0, rkmId);
		Object obj = query.uniqueResult();
		if (obj != null) {
			return (TsRelKeyMap) obj;
		}
		return null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<MsStatute> listMsStatute() {
		return (List<MsStatute>) this.sessionAnnotationFactory
				.getCurrentSession().createQuery("from MsStatute msStatute")
				.list();
	}

	@Transactional(readOnly = true)
	public MsStatute getMsStatute(Long statuteId) {
		Query query = this.sessionAnnotationFactory
				.getCurrentSession()
				.createQuery(
						" from MsStatute msStatute where msStatute.statuteId=?  ");
		query.setParameter(0, statuteId);
		Object obj = query.uniqueResult();
		if (obj != null) {
			return (MsStatute) obj;
		}
		return null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<StStatutesub> listStStatutesub() {
		return (List<StStatutesub>) this.sessionAnnotationFactory
				.getCurrentSession()
				.createQuery("from StStatutesub stStatutesub").list();
	}

	@Transactional(readOnly = true)
	public StStatutesub getStStatutesub(Long stStatutesubId) {
		Query query = this.sessionAnnotationFactory
				.getCurrentSession()
				.createQuery(
						" from StStatutesub stStatutesub where stStatutesub.stStatutesubId=?  ");
		query.setParameter(0, stStatutesubId);
		Object obj = query.uniqueResult();
		if (obj != null) {
			return (StStatutesub) obj;
		}
		return null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<MsStatutesub>  listMsStatutesub() {
		// TODO Auto-generated method stub
		return (List<MsStatutesub>) this.sessionAnnotationFactory
				.getCurrentSession()
				.createQuery(
						"from MsStatutesub msStatutesub order by msStatutesub.name asc ")
				.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true) 
	public List<StStatutesub> listStStatutesubByMsStatutesub(
			MsStatutesub msStatutesub) { 
		// TODO Auto-generated method stub
		Query query = this.sessionAnnotationFactory
				.getCurrentSession()
				.createQuery(
						" from StStatutesub stStatutesub where stStatutesub.msStatutesub.statutesubId=?  ");
		query.setParameter(0, msStatutesub.getStatutesubId());
		return (List<StStatutesub>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<StMatr> listStMatrByMsStatute(MsStatute msStatute) {
		// TODO Auto-generated method stub
		Query query = this.sessionAnnotationFactory
				.getCurrentSession()
				.createQuery(
						" from StMatr stMatr where stMatr.msStatute.statuteId=?  ");
		query.setParameter(0, msStatute.getStatuteId());
		return (List<StMatr>) query.list();
	}

	@Transactional(readOnly = true)
	public StMatr getStMatr(Long stMatrId) {
		// TODO Auto-generated method stub
		Query query = this.sessionAnnotationFactory.getCurrentSession()
				.createQuery(" from StMatr stMatr where stMatr.stMatrId=?  ");
		query.setParameter(0, stMatrId);
		Object obj = query.uniqueResult();
		if (obj != null) {
			return (StMatr) obj;
		}
		return null;
	}
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public List listMsStatuteAndMsStatutesub() {
		// TODO Auto-generated method stub
		List result = new ArrayList(2);
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		String msStatuteStr = "from MsStatute msStatute order by msStatute.name asc";
		String MsStatutesubStr = "from MsStatutesub msStatutesub order by msStatutesub.name asc";
		Query query = session.createQuery(msStatuteStr);
		result.add(query.list());
		query = session.createQuery(MsStatutesubStr);
		result.add(query.list());
		return result;
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<StStatutesub> listStStatutesub(MsStatute msStatute,
			MsStatutesub msStatutesub) {
		// TODO Auto-generated method stub
		Query query = this.sessionAnnotationFactory
				.getCurrentSession()
				.createQuery(
						" from StStatutesub stStatutesub where "
								+ "  stStatutesub.msStatute.statuteId=? and stStatutesub.msStatutesub.statutesubId=?");
		query.setParameter(0, msStatute.getStatuteId());
		query.setParameter(1, msStatutesub.getStatutesubId());
		return (List<StStatutesub>) query.list();
	}

	@Transactional(readOnly = true)
	public TsRelMap getRelMapById(Long rmId) {
		// TODO Auto-generated method stub
		Query query = this.sessionAnnotationFactory.getCurrentSession()
				.createQuery(" from TsRelMap tsRelMap where tsRelMap.rmId=?  ");
		query.setParameter(0, rmId);
		Object obj = query.uniqueResult();
		if (obj != null) {
			return (TsRelMap) obj;
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public Long saveRelMap(TsRelMap persistentInstance) {
		// TODO Auto-generated method stub
		/*System.out.println("  persistentInstance.getRmTable()===>"
				+ persistentInstance.getRmTable());
		System.out.println("  persistentInstance.getRmName()===>"
				+ persistentInstance.getRmName());*/
		return save(this.sessionAnnotationFactory.getCurrentSession(),
				persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public int updateRelMap(TsRelMap transientInstance) {
		// TODO Auto-generated method stub
		/*System.out.println("  updateRelMap getRkmId ()===>"
				+ transientInstance.getRmId());
		System.out.println("  updateRelMap getRmKey ()===>"
				+ transientInstance.getRmKey());*/
		return update(this.sessionAnnotationFactory.getCurrentSession(),
				transientInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public void deleteRelMap(TsRelMap persistentInstance) {
		// TODO Auto-generated method stub
		//System.out.println("un link " + persistentInstance.getRmId());
		delete(this.sessionAnnotationFactory.getCurrentSession(),
				persistentInstance);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TsRelDTO> listTsRel(Long rmId) {
		// TODO Auto-generated method stub
		List<TsRelDTO> tsRelDTOs=null;
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery(" from TsRel tsRel where tsRel.tsRelMap.rmId=?  ");
		query.setParameter(0, rmId);
		List<TsRel> tsRels = query.list(); 
		
		if (tsRels != null && tsRels.size() > 0) {
			tsRelDTOs = new ArrayList<TsRelDTO>(tsRels.size());
			for (TsRel tsRel : tsRels) {
				TsRelDTO tsRelDTO = new TsRelDTO();
				String trTableName = tsRel.getTrTableName();
				String trKey = tsRel.getTrKey();
				String rmIdStr = rmId.intValue()+"";
				String rmName = tsRel.getTsRelMap().getRmName()+"";
				tsRelDTO.setRmId(rmIdStr);
				tsRelDTO.setRmName(rmName);
				tsRelDTO.setTrId(tsRel.getTrId());
				tsRelDTO.setTrTableName(trTableName);
				tsRelDTO.setTrKey(trKey);
				String trTitle = null;
				if (trTableName != null) {
					if (trTableName.equals("ST_MATR")) {
						query = session
						.createQuery(" from StMatr stMatr where stMatr.stMatrId=?  ");
						query.setParameter(0, Long.parseLong(trKey));
						Object obj= query.uniqueResult();
						trTitle=((StMatr)obj).getMatr();
					} else if (trTableName.equals("ST_STATUTESUB")) {
						query = session
						.createQuery(" from StStatutesub stStatutesub where stStatutesub.stStatutesubId=?  ");
						query.setParameter(0, Long.parseLong(trKey));
						Object obj= query.uniqueResult();
						trTitle=((StStatutesub)obj).getArticle();
					}
					tsRelDTO.setTrTitle(trTitle);					
				}
				tsRelDTOs.add(tsRelDTO);
			}
		}
		return tsRelDTOs;
	}

	@Transactional(readOnly = true)
	public TsRel getTsRelById(Long trId) {
		// TODO Auto-generated method stub
		Query query = this.sessionAnnotationFactory.getCurrentSession()
				.createQuery(" from TsRel tsRel where tsRel.trId=?  ");
		query.setParameter(0, trId);
		Object obj = query.uniqueResult();
		if (obj != null) {
			return (TsRel) obj;
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public Long saveTsRel(TsRel persistentInstance) {
		// TODO Auto-generated method stub
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		TsRelMap tsRelMap = persistentInstance.getTsRelMap();

		Long rmId = tsRelMap.getRmId();
		//System.out.println(" chatchai debug rmId=" + rmId);
		if (!(tsRelMap != null && rmId != null && rmId.intValue() != 0)) {
			Object obj = session.save(tsRelMap);
			if (obj != null) {
				rmId = (Long) obj;
			}
		}
		tsRelMap.setRmId(rmId);
		persistentInstance.setTsRelMap(tsRelMap);
		session.save(persistentInstance);
		return tsRelMap.getRmId();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public int updateTsRel(TsRel transientInstance) {
		// TODO Auto-generated method stub
		return update(this.sessionAnnotationFactory.getCurrentSession(),
				transientInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public List<TsRelDTO>  deleteTsRel(TsRel persistentInstance) {
		// TODO Auto-generated method stub 
		List<TsRelDTO> tsRelDTOs=null;
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery(" from TsRel tsRel where tsRel.trId=?  ");
		query.setParameter(0, persistentInstance.getTrId());
		Object objTsRel=query.uniqueResult();
		if(objTsRel!=null){
			TsRel tsRelObj=(TsRel)objTsRel;
			TsRelMap tsRelMap =tsRelObj.getTsRelMap();
			session.delete(tsRelObj);
			if(tsRelMap!=null && tsRelMap.getRmId()!=null && tsRelMap.getRmId().intValue()!=0){
				query = session
				.createQuery(" from TsRel tsRel where tsRel.tsRelMap.rmId=?  ");
				query.setParameter(0, tsRelMap.getRmId());
				List<TsRel> tsRels = query.list(); 				
				if (tsRels != null && tsRels.size() > 0) {
					tsRelDTOs = new ArrayList<TsRelDTO>(tsRels.size());
					for (TsRel tsRel : tsRels) {
						TsRelDTO tsRelDTO = new TsRelDTO();
						String articleHeaderTable = tsRel.getArticleHeaderTable(); 
						int articleHeaderId_key =tsRel.getArticleHeaderId().intValue();
						String rmIdStr = tsRelMap.getRmId()+"";
						String rmName = tsRel.getTsRelMap().getRmName()+"";
						tsRelDTO.setRmId(rmIdStr);
						tsRelDTO.setRmName(rmName);
						tsRelDTO.setTrId(tsRel.getTrId());
						tsRelDTO.setTrTableName(articleHeaderTable);
						tsRelDTO.setTrKey(articleHeaderId_key+"");
						String trTitle = null;
						
						/*String trTableName = tsRel.getTrTableName();
						String trKey = tsRel.getTrKey();
						tsRelDTO.setTrId(tsRel.getTrId());
						tsRelDTO.setTrTableName(trTableName);
						tsRelDTO.setTrKey(trKey);
						String trTitle = null;*/
						/*if (trTableName != null) {
							if (trTableName.equals("ST_MATR")) {
								query = session
								.createQuery(" from StMatr stMatr where stMatr.stMatrId=?  ");
								query.setParameter(0, Long.parseLong(trKey));
								Object obj= query.uniqueResult();
								trTitle=((StMatr)obj).getMatr();
							} else if (trTableName.equals("ST_STATUTESUB")) {
								query = session
								.createQuery(" from StStatutesub stStatutesub where stStatutesub.stStatutesubId=?  ");
								query.setParameter(0, Long.parseLong(trKey));
								Object obj= query.uniqueResult();
								trTitle=((StStatutesub)obj).getArticle();
							}
							tsRelDTO.setTrTitle(trTitle);					
						}
						tsRelDTOs.add(tsRelDTO);*/
						if (articleHeaderTable != null) {
							if (articleHeaderTable.equals("TS_LAW")) {
								query = session
								.createQuery(" from TsLaw tsLaw where tsLaw.lawId="+articleHeaderId_key);
								//query.setParameter(0, Long.parseLong(trKey));
								Object obj= query.uniqueResult();
								trTitle=((TsLaw)obj).getLawTitleThai();
							} else if (articleHeaderTable.equals("TS_ARTICLE")) {
								query = session
								        .createQuery(" from TsExArticleHeader tsExArticleHeader where tsExArticleHeader.articleHeaderId="+articleHeaderId_key); 
								/*query = session
						        .createQuery(" from TsArticle tsArticle where tsArticle.articleId="+articleHeaderId_key); */
								Object obj= query.uniqueResult();
							//	int articleHeaderId=((TsArticle)obj).getArticleHeaderId().intValue();
								trTitle=((TsExArticleHeader)obj).getArticleHeaderName();
								/*query = session
								        .createQuery(" from TsExArticleHeader tsExArticleHeader where tsExArticleHeader.articleHeaderId="+articleHeaderId); 
										obj= query.uniqueResult();
								trTitle=((TsExArticleHeader)obj).getArticleHeaderName();*/
							}else if (articleHeaderTable.equals("TS_EX_ARTICLE_COMPLETED")) {
								/*query = session
								        .createQuery(" from TsExArticleCompleted tsExArticleCompleted where tsExArticleCompleted.articleCompletedId="+articleHeaderId_key); 
										Object obj= query.uniqueResult();
										int articleHeaderId=((TsExArticleCompleted)obj).getArticleHeaderId().intValue();*/
										query = session
												.createQuery(" from TsExArticleHeader tsExArticleHeader where tsExArticleHeader.articleHeaderId="+articleHeaderId_key); 
										Object obj= query.uniqueResult();
										trTitle=((TsExArticleHeader)obj).getArticleHeaderName();
							}
							tsRelDTO.setTrTitle(trTitle);					
						}
						tsRelDTOs.add(tsRelDTO);
					}
				}
			}
			
		}
		
		return tsRelDTOs;
	}
	@Transactional(readOnly = true)
	public List<TmStatue> listTmStatues(){
		List<TmStatue> tmStatues=null;
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery(" from TmStatue tmStatue order by tmStatue.statueOrder ");
		tmStatues = query.list(); 
		return tmStatues;
	}
	@Transactional(readOnly = true)
	public List<TmLawType> listTmLawTypes(){
		List<TmLawType> tmLawTypes=null;
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery(" from TmLawType tmLawType order by tmLawType.lawTypeOrder ");
		tmLawTypes= query.list(); 
		return tmLawTypes;
	}
	
	@Transactional(readOnly = true)
	public List<TmArticleGroup> listTmArticleGroups(){
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery(" from TmArticleGroup tmArticleGroup order by tmArticleGroup.articleGroupOrder ");
        return query.list(); 
	}
	@Transactional(readOnly = true)
	public List<TmArticleSection> listTmArticleSections(){
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery(" from TmArticleSection tmArticleSection order by tmArticleSection.articleSectionOrder ");
        return query.list(); 
	}
	@Transactional(readOnly = true)
	public List<TsExArticleCompleted> listTsExArticleCompleteds(String statueId, String lawTypeId){
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery(" from TsExArticleCompleted tsExArticleCompleted order by tsExArticleCompleted.articleCompletedOrder ");
        return query.list(); 
	}
	@Transactional(readOnly = true)
	public List<TsArticle> listTsArticles(String statueId, String lawTypeId){
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery(" from TsArticle tsArticle order by tsArticle.articleOrder ");
        return query.list(); 
	}
	@Transactional(readOnly = true)
	public List<TsLaw> listTsLaws(String statueId, String lawTypeId){
   
	Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery(" from TsLaw TsLaw order by TsLaw.lawOrder ");
        return query.list(); 
    }
	public List<TsExArticleHeader> listTsExArticleHeader(String type,String statueId, String lawTypeId){
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery(" from TsExArticleHeader tsExArticleHeader ");
        return query.list(); 
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TsRelDTO> listExistingLink(Long rmId) {
		// TODO Auto-generated method stub
		List<TsRelDTO> tsRelDTOs=null;
		Session session = this.sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery(" from TsRel tsRel where tsRel.tsRelMap.rmId=?  ");
		query.setParameter(0, rmId);
		List<TsRel> tsRels = query.list(); 
		
		if (tsRels != null && tsRels.size() > 0) {
			tsRelDTOs = new ArrayList<TsRelDTO>(tsRels.size());
			for (TsRel tsRel : tsRels) {
				TsRelDTO tsRelDTO = new TsRelDTO();
				//String trTableName =tsRel.getTrTableName();
				//String trKey =tsRel.getTrKey();
				String articleHeaderTable = tsRel.getArticleHeaderTable(); 
				int articleHeaderId_key =tsRel.getArticleHeaderId().intValue();
				String rmIdStr = rmId.intValue()+"";
				String rmName = tsRel.getTsRelMap().getRmName()+"";
				tsRelDTO.setRmId(rmIdStr);
				tsRelDTO.setRmName(rmName);
				tsRelDTO.setTrId(tsRel.getTrId());
				tsRelDTO.setTrTableName(articleHeaderTable);
				tsRelDTO.setTrKey(articleHeaderId_key+"");
				String trTitle = null;
				
				if (articleHeaderTable != null) {
					if (articleHeaderTable.equals("TS_LAW")) {
						query = session
						.createQuery(" from TsLaw tsLaw where tsLaw.lawId="+articleHeaderId_key);
						//query.setParameter(0, Long.parseLong(trKey));
						Object obj= query.uniqueResult();
						trTitle=((TsLaw)obj).getLawTitleThai();
					} else if (articleHeaderTable.equals("TS_ARTICLE")) {
						query = session
						        .createQuery(" from TsExArticleHeader tsExArticleHeader where tsExArticleHeader.articleHeaderId="+articleHeaderId_key); 
						/*query = session
				        .createQuery(" from TsArticle tsArticle where tsArticle.articleId="+articleHeaderId_key); */
						Object obj= query.uniqueResult();
					//	int articleHeaderId=((TsArticle)obj).getArticleHeaderId().intValue();
						trTitle=((TsExArticleHeader)obj).getArticleHeaderName();
						/*query = session
						        .createQuery(" from TsExArticleHeader tsExArticleHeader where tsExArticleHeader.articleHeaderId="+articleHeaderId); 
								obj= query.uniqueResult();
						trTitle=((TsExArticleHeader)obj).getArticleHeaderName();*/
					}else if (articleHeaderTable.equals("TS_EX_ARTICLE_COMPLETED")) {
						/*query = session
						        .createQuery(" from TsExArticleCompleted tsExArticleCompleted where tsExArticleCompleted.articleCompletedId="+articleHeaderId_key); 
								Object obj= query.uniqueResult();
								int articleHeaderId=((TsExArticleCompleted)obj).getArticleHeaderId().intValue();*/
								query = session
										.createQuery(" from TsExArticleHeader tsExArticleHeader where tsExArticleHeader.articleHeaderId="+articleHeaderId_key); 
								Object obj= query.uniqueResult();
								trTitle=((TsExArticleHeader)obj).getArticleHeaderName();
					}
					tsRelDTO.setTrTitle(trTitle);					
				}
				tsRelDTOs.add(tsRelDTO);
			}
		}
		return tsRelDTOs;
	}

	@Transactional(readOnly = true)
	public TsLaw getTsLaw(Long lawId){
		Query query = this.sessionAnnotationFactory.getCurrentSession()
				.createQuery(" from TsLaw tsLaw where tsLaw.lawId=?  ");
		query.setParameter(0, lawId);
		Object obj = query.uniqueResult();
		if (obj != null) {
			return (TsLaw) obj;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public TsExArticleCompleted getTsExArticleCompleted(Long articleCompletedId){
		Query query = this.sessionAnnotationFactory.getCurrentSession()
				.createQuery(" from TsExArticleCompleted tsExArticleCompleted where tsExArticleCompleted.articleCompletedId=?  ");
		query.setParameter(0, articleCompletedId);
		Object obj = query.uniqueResult();
		if (obj != null) {
			return (TsExArticleCompleted) obj;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public TsArticle getTsArticle(Long articleId){
		Query query = this.sessionAnnotationFactory.getCurrentSession()
				.createQuery(" from TsArticle tsArticle where tsArticle.articleId=?  ");
		query.setParameter(0, articleId);
		Object obj = query.uniqueResult();
		if (obj != null) {
			return (TsArticle) obj;
		}
		return null;
	}
	@Transactional(readOnly = true)
	@Override
	public List<TsArticle> getTsArticleList(String groupId) {//statue_id, law_type_id, article_header_id
		String[] ids=groupId.split("_");
	/*	private BigDecimal statueId;
		private BigDecimal lawTypeId;
		private BigDecimal articleHeaderId;*/
		// TODO Auto-generated method stub
		Query query = this.sessionAnnotationFactory.getCurrentSession()
				.createQuery(" from TsArticle tsArticle where tsArticle.statueId=:statueId " +
						"and tsArticle.lawTypeId=:lawTypeId and tsArticle.articleHeaderId=:articleHeaderId order by tsArticle.articleOrder ");
		query.setParameter("statueId", new BigDecimal(ids[0]));
		query.setParameter("lawTypeId", new BigDecimal(ids[1]));
		query.setParameter("articleHeaderId", new BigDecimal(ids[2]));
		return query.list(); 
	}
}
