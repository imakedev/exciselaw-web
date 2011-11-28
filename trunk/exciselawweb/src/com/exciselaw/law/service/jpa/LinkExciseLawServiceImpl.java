package com.exciselaw.law.service.jpa;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exciselaw.law.domain.MsStatute;
import com.exciselaw.law.domain.MsStatutesub;
import com.exciselaw.law.domain.StMatr;
import com.exciselaw.law.domain.StStatutesub;
import com.exciselaw.law.domain.TsRel;
import com.exciselaw.law.domain.TsRelDTO;
import com.exciselaw.law.domain.TsRelMap;
import com.exciselaw.law.service.LinkExciseLawService;

/**
 * @author Chatchai Pimtun
 */
@Repository
@Transactional
public class LinkExciseLawServiceImpl extends ExciseLawServiceCommon implements
		LinkExciseLawService {

	private SessionFactory sessionAnnotationFactory;

	@Autowired
	public LinkExciseLawServiceImpl(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
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
	public List<MsStatutesub> listMsStatutesub() {
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
		System.out.println("  persistentInstance.getRmTable()===>"
				+ persistentInstance.getRmTable());
		System.out.println("  persistentInstance.getRmName()===>"
				+ persistentInstance.getRmName());
		return save(this.sessionAnnotationFactory.getCurrentSession(),
				persistentInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public int updateRelMap(TsRelMap transientInstance) {
		// TODO Auto-generated method stub
		System.out.println("  updateRelMap getRkmId ()===>"
				+ transientInstance.getRmId());
		System.out.println("  updateRelMap getRmKey ()===>"
				+ transientInstance.getRmKey());
		return update(this.sessionAnnotationFactory.getCurrentSession(),
				transientInstance);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	public void deleteRelMap(TsRelMap persistentInstance) {
		// TODO Auto-generated method stub
		System.out.println("un link " + persistentInstance.getRmId());
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
		System.out.println(" chatchai debug rmId=" + rmId);
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
						String trTableName = tsRel.getTrTableName();
						String trKey = tsRel.getTrKey();
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
			}
			
		}
		
		return tsRelDTOs;
	}
}
