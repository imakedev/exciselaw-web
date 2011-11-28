package com.exciselaw.law.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

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
import com.exciselaw.law.utils.Paging;


/**
 * The high-level PetClinic business interface.
 *
 * <p>This is basically a data access object.
 * PetClinic doesn't have a dedicated business facade.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 */
public interface ExciseLawService {

	/**
	 * Retrieve all <code>Vet</code>s from the data store.
	 * @return a <code>Collection</code> of <code>Vet</code>s
	 */
//	Collection<Thuser> getThusers() throws DataAccessException;
	public Long saveTsLoginLog(TsLoginLog persistentInstance);
	public int updateTsLoginLog(TsLoginLog persistentInstance);
	public TsLoginLog getTsLoginLogByUserId(String userId);
	
	public void deleteMsStatute(MsStatute persistentInstance); 
	public Long saveMsStatute(MsStatute persistentInstance); 
	public int updateMsStatute(MsStatute transientInstance);
	public List searchMsStatute(MsStatute instance,Paging paging); 
	public MsStatute findMsStatuteById(Long statuteId); 
	
	public void deleteMsSubmatr(MsSubmatr persistentInstance); 
	public Long saveMsSubmatr(MsSubmatr persistentInstance); 
	public int updateMsSubmatr(MsSubmatr transientInstance);
	public List searchMsSubmatr(MsSubmatr instance,Paging paging); 
	public MsSubmatr findMsSubmatrById(Long submatrId); 

	
	public void deleteMsMatrgroup(MsMatrgroup persistentInstance); 
	public Long saveMsMatrgroup(MsMatrgroup persistentInstance); 
	public int updateMsMatrgroup(MsMatrgroup transientInstance);
	public List searchMsMatrgroup(MsMatrgroup instance,Paging paging); 
	public MsMatrgroup findMsMatrgroupById(Long matrgroupId); 
	
	public void deleteTsStatuteLegalitem(TsStatuteLegalitem persistentInstance); 
	public Long saveTsStatuteLegalitem(TsStatuteLegalitem persistentInstance);	 
	public int updateTsStatuteLegalitem(TsStatuteLegalitem transientInstance);
	public List searchTsStatuteLegalitem(TsStatuteLegalitem instance,Paging paging); 
	public TsStatuteLegalitem findTsStatuteLegalitemById(TsStatuteLegalitemPK id); 
	
	public void deleteTsStatutesubLegalitem(TsStatutesubLegalitem persistentInstance); 
	public Long saveTsStatutesubLegalitem(TsStatutesubLegalitem persistentInstance);	 
	public int updateTsStatutesubLegalitem(TsStatutesubLegalitem transientInstance);
	public List searchTsStatutesubLegalitem(TsStatutesubLegalitem instance,Paging paging); 
	public TsStatutesubLegalitem findTsStatutesubLegalitemById(TsStatutesubLegalitemPK id); 
	
	public void deleteStMatr(StMatr persistentInstance); 
	public Long saveStMatr(StMatr persistentInstance); 
	public int updateStMatr(StMatr transientInstance);
	public List searchStMatr(StMatr instance,Paging paging); 
	public List<StMatr> findStMatrListByStatuteId(Long statuteId);
	public StMatr findStMatrById(Long stMatrId); 
	
	public void deleteStStatutesub(StStatutesub persistentInstance); 
	public Long saveStStatutesub(StStatutesub persistentInstance); 
	public int updateStStatutesub(StStatutesub transientInstance);
	public List searchStStatutesub(StStatutesub instance,Paging paging,Long productId); 
	public StStatutesub findStStatutesubById(Long stStatutesubId);
	
	public void deleteTsRelStatute(TsRelStatute persistentInstance); 
	public Long saveTsRelStatute(TsRelStatute persistentInstance); 
	public int updateTsRelStatute(TsRelStatute transientInstance);
	public List searchTsRelStatute(TsRelStatute instance,Paging paging); 
	public TsRelStatute findTsRelStatuteById(TsRelStatutePK id);
	
	List <MsSubmatr> listMsSubmatrs() throws DataAccessException;	
	List <MsStatute> listMsStatutes(String status) throws DataAccessException;
	List <MsStatutesub> listMsStatutesubs() throws DataAccessException;
	List <MsMatrgroup> listMsMatrgroups() throws DataAccessException;
	List <MsLegalitem> listMsLegalitems( Long legalitemtypeId) throws DataAccessException;
	public void saveTsStatuteLegalitem(List<TsStatuteLegalitem> persistentInstance);
	public void saveTsStatutesubLegalitem(List<TsStatutesubLegalitem> persistentInstance);
	public void saveTsRelStatute(List<TsRelStatute> persistentInstance);
	public List<TsStatuteLegalitem> searchTsStatuteLegalitemByStatuteId(Long statuteId);
	public List<TsStatutesubLegalitem> searchTsStatutesubLegalitemByStStatutesubId(Long stStatutesubId); 
	public List<TsRelStatute> searchTsRelStatuteByStStatutesubId(Long stStatutesubId);
	
	//------------------------------ START LAW -------------------------------------------- //

	public List<TmStatue> getTmStatueList();	
	public List<TmLawType> getTmLawTypeList();	
	public List<TmLawType> getTmLawTypeListByPriority0();	
	public List<TmArticleGroup> getTmArticleGroupList();
	public List<TmArticleSection> getTmArticleSectionList();
	public List<TsArticle> getTsArticleList(BigDecimal exciseArticleType);
	public List<TsExArticleHeader> getTsExArticleHeaderList();	
	public List<TsExArticleCompleted> getTsArticleCompletedList();
	public List<TsExArticleHeader> getExArticleHeaderListByLawTypeId(BigDecimal lawTypeId);
	
	public List searchStatueList(TmStatue statue, Paging paging);
	public TmStatue findTmStatueByStatueId(Long statueId); 
	public String checkStatueOrder(BigDecimal statueOrder); 
	public BigDecimal findMaxStatueOrder(); 	
	public Long saveTmStatue(TmStatue persistentInstance); 
	public int updateTmStatue(TmStatue persistentInstance); 
	public void deleteTmStatue(TmStatue persistentInstance); 
	public List<TsStatueGood> findTsStatueGoodByStatueId(BigDecimal statueId);	
	public Long saveTsStatueGood(TsStatueGood persistentInstance); 
	public void deleteTsStatueGood(TsStatueGood persistentInstance);
	public List<TsStatueAttach> findTsStatueAttachListByStatueId(Long statueId);
	public TsStatueAttach findTsStatueAttachByAttachId(Long attachId);
	public Long saveTsStatueAttach(TsStatueAttach persistentInstance); 
	public void deleteTsStatueAttach(TsStatueAttach persistentInstance); 

	public List searchLawTypeList(TmLawType lawType, Paging paging);
	public TmLawType findTmLawTypeByLawTypeId(Long lawTypeId); 
	public String checkLawTypeOrder(BigDecimal lawTypeOrder); 
	public BigDecimal findMaxLawTypeOrder(); 
	public Long saveTmLawType(TmLawType persistentInstance); 
	public int updateTmLawType(TmLawType persistentInstance); 
	public void deleteTmLawType(TmLawType persistentInstance); 

	public List searchArticleGroupList(TmArticleGroup articleGroup, Paging paging);
	public TmArticleGroup findTmArticleGroupByArticleGroupId(Long articleGroupId); 
	public BigDecimal findMaxArticleGroupOrder(); 
	public Long saveTmArticleGroup(TmArticleGroup persistentInstance); 
	public int updateTmArticleGroup(TmArticleGroup persistentInstance); 
	public void deleteTmArticleGroup(TmArticleGroup persistentInstance); 
	public void callReorderArticleGroup(Integer articleGroupOrder);

	public List searchArticleSectionList(TmArticleSection articleSection, Paging paging);
	public TmArticleSection findTmArticleSectionByArticleSectionId(Long articleSectionId); 
	public BigDecimal findMaxArticleSectionOrder(); 
	public Long saveTmArticleSection(TmArticleSection persistentInstance); 
	public int updateTmArticleSection(TmArticleSection persistentInstance); 
	public void deleteTmArticleSection(TmArticleSection persistentInstance); 

	public List searchExArticleHeadList(TsExArticleHeader tsExArticleHeader, Paging paging);
	public TsExArticleHeader findTsExArticleHeaderByArticleHeaderId(Long articleHeaderId); 
	public List<TsExArticleHeader> findTsExArticleHeaderListByStatueIdLawTypeId(BigDecimal statueId, BigDecimal lawTypeId); 
	public Long saveTsExArticleHeader(TsExArticleHeader persistentInstance); 
	public int updateTsExArticleHeader(TsExArticleHeader persistentInstance); 
	public void deleteTsExArticleHeader(TsExArticleHeader persistentInstance); 
	public List<TsExArticleHeaderAttach> findTsExArticleHeaderAttachListByArticleHeaderId(Long articleHeaderId);
	public TsExArticleHeaderAttach findTsExArticleHeaderAttachByAttachId(Long attachId);
	public Long saveTsExArticleHeadAttach(TsExArticleHeaderAttach persistentInstance); 
	public void deleteTsExArticleHeadAttach(TsExArticleHeaderAttach persistentInstance);
	
	public List searchArticleList(TsArticle tsArticle, BigDecimal exciseArticleType, Paging paging);
	public TsArticle findTsArticleByArticleId(Long articleId); 
	public BigDecimal findMaxArticleOrder(BigDecimal exciseArticleType, BigDecimal statueId, BigDecimal lawTypeId); 
	public Long saveTsArticle(TsArticle persistentInstance); 
	public int updateTsArticle(TsArticle persistentInstance); 
	public void deleteTsArticle(TsArticle persistentInstance); 
	public void callReorderArticle(Integer articleOrder, Integer exciseArticleType, Integer statueId, Integer lawTypeId);

	public List searchArticleCompletedList(TsExArticleCompleted tsArticle, Paging paging);
	public TsExArticleCompleted findTsExArticleCompletedByArticleCompletedId(Long articleCompletedId); 
	public TsExArticleCompleted findTsExArticleCompletedByStatueId(BigDecimal statueId); 
	public BigDecimal findMaxArticleCompletedOrder(BigDecimal statueId); 
	public Long saveTsExArticleCompleted(TsExArticleCompleted persistentInstance); 
	public int updateTsExArticleCompleted(TsExArticleCompleted persistentInstance); 
	public void deleteTsExArticleCompleted(TsExArticleCompleted persistentInstance); 
	public void callReorderArticleCompleted(Integer articleOrder, Integer statueId);
	public TsExArticleCompleted findArticleCompletedByStatueIdArticleNumber(BigDecimal statueId, String articleNumber);
	
	public List searchTariffList(TsTariff tsTariff, Paging paging);
	public TsTariff findTsTariffByTariffId(Long tariffId); 
	public Long saveTsTariff(TsTariff persistentInstance); 
	public int updateTsTariff(TsTariff persistentInstance); 
	public void deleteTsTariff(TsTariff persistentInstance); 
	
	public List searchLawList(TsLaw tsLaw, BigDecimal lawExciseStatus, Paging paging);
	public TsLaw findTsLawByLawId(Long tariffId); 
	public Long saveTsLaw(TsLaw persistentInstance); 
	public int updateTsLaw(TsLaw persistentInstance); 
	public void deleteTsLaw(TsLaw persistentInstance); 

	public List<TsLawAttach> findTsLawAttachListByLawId(Long lawId);
	public TsLawAttach findTsLawAttachByAttachId(Long attachId);
	public Long saveTsLawAttach(TsLawAttach persistentInstance); 
	public void deleteTsLawAttach(TsLawAttach persistentInstance);

	public List<TsLawStatue> findTsLawStatueListByLawId(BigDecimal lawId);
	public Long saveTsLawStatue(TsLawStatue persistentInstance); 
	public void deleteTsLawStatue(TsLawStatue persistentInstance);
	
	public List<TsLawGood> findTsLawGoodByLawId(BigDecimal lawId);	
	public Long saveTsLawGood(TsLawGood persistentInstance); 
	public void deleteTsLawGood(TsLawGood persistentInstance);
	//------------------------------ FINISH LAW ------------------------------------------- //

	//------------------------------ START MANAGEMENT ------------------------------------- //
	public List searchRoleList(String roleName, Paging paging);
	public TmRole findTmRoleByRoleId(String roleId); 
	public List searchScreenList(String screenName, Paging paging);
	public List<TmScreen> getScreenList();
	public TmScreen findTmScreenByScreenId(Long screenId); 
	public List<TmAction> getActionList();
	public List<TmScreen> getScreenListInTsRoleScreen(String roleId);
	public List<TmScreen> getScreenListNotInTsRoleScreen(String roleId);
	public List<TmAction> getActionListByScreenId(BigDecimal screenId);
	
	public List<TsRoleScreen> getTsRoleScreenByRole(String roleId, BigDecimal screenId);
	public Long saveTsRoleScreen(TsRoleScreen persistentInstance); 
	public void deleteTsRoleScreen(TsRoleScreen persistentInstance); 	
	
	public TmUserinfo getUserpassword(String userId, String password);
	public HashMap<String, String> getTmScreenMapByRoleId(String roleId);
	public List<TmScreen> getTmScreenListByRoleId(String roleId);
	public String getActionListByRoleIdScreenId(String roleId, String screenId);	

	public List searchVConsultDocList(ConsultDocFilter consultDocFilter, Paging paging);

	public List searchMasterWord(String masterWord, Paging paging);
	public TsMasterWord findTsMasterWordBymasterWordId(Long masterWordId);
	public Long saveTsMasterWord(TsMasterWord persistentInstance); 
	public int updateTsMasterWord(TsMasterWord persistentInstance); 
	public void deleteTsMasterWord(TsMasterWord persistentInstance); 
	public List searchThesaurus(BigDecimal masterWordId, String masterWordThesaurus, Paging paging);
	public TsThesaurus findTsThesaurusByThesaurusId(Long thesaurusId);
	public Long saveTsThesaurus(TsThesaurus persistentInstance); 
	public int updateTsThesaurus(TsThesaurus persistentInstance); 
	public void deleteTsThesaurus(TsThesaurus persistentInstance); 	
	//------------------------------ FINISH MANAGEMENT ------------------------------------ //
	
	//------------------------------ START NEWS ------------------------------------------- //
	public List searchNewsList(NewsFilter newsFilter, Paging paging);
	public TsNews findTsNewsByNewsId(Long newsId); 
	public Long saveTsNews(TsNews persistentInstance); 
	public int updateTsNews(TsNews persistentInstance); 
	public void deleteTsNews(TsNews persistentInstance); 	
	public List<TsNewsAttach> findTsNewsAttachListByNewsId(Long newsId);
	public TsNewsAttach findTsNewsAttachByAttachId(Long attachId);
	public Long saveTsNewsAttach(TsNewsAttach persistentInstance); 
	public void deleteTsNewsAttach(TsNewsAttach persistentInstance); 
	//------------------------------ FINISH NEWS ------------------------------------------ //
	
	//------------------------------ START CONSULTDOC ------------------------------------- //
	public List<MviewEdOffice> getInternalOfficeList();
	public List<MviewEdOffice> getExternalOfficeList();
	public TmConsultDoc findTmConsultDocById(Long docId);  
	public Long saveTmConsultDoc(TmConsultDoc persistentInstance); 
	public int updateTmConsultDoc(TmConsultDoc persistentInstance); 
	public void deleteConsultDoc(TmConsultDoc persistentInstance); 	

	public TsDocState findTsDocStateByStateId(Long stateId); 
	public VConsultDoc findVConsultDocByDocId(Long docId); 
	public VConsultDocList findVConsultDocListByDocId(Long docId); 
	public Long saveTsDocState(TsDocState persistentInstance); 
	public int updateTsDocState(TsDocState persistentInstance);
	public Long saveTsDocAttach(TsDocAttach persistentInstance); 
	public void deleteDocAttach(TsDocAttach persistentInstance); 
	public List<TsDocAttach> findTsDocAttachListByDocId(Long docId);
	public TsDocAttach findTsDocAttachByAttachId(Long attachId);
	public List<VDocHistory> findVDocHistory(Long docId);
	public List<TsDocState> findDocCommentList(Long docId);	
	
	public List searchVConsultDocList(ConsultDocFilter consultDocFilter, long flowId, Paging paging);
	public List findVConsultDocListByDocId(BigDecimal docId);  
	public List findVConsultDocByDocId(BigDecimal docId);  
	public List <MviewOrganization> listOrganization(String orgId) throws DataAccessException;	

	public List<TsDocStatue> findTsDocStatueBydocId(BigDecimal docId);
	public Long saveTsDocStatue(TsDocStatue persistentInstance); 
	public void deleteTsDocStatue(TsDocStatue persistentInstance);

	public List<MviewPersonel> getLawyerList(String orgId);
	public List<MviewPersonel> getProfessionalList();
	public Long saveTsDocResolution(TsDocResolution persistentInstance); 
	public TsDocResolution findTsDocResolution(Long restId);
	public TsDocResolution findLastTsDocResolution(Long docId);
	public List<TsDocResolution> findTsDocResolutionList(Long docId);
	public List<TsDocResolution> getTsDocResolutionHistory(Long docId);	
	
	//------------------------------ FINISH CONSULTDOC ------------------------------------ //

	//------------------------------ START CONSULTPUBLISH --------------------------------- //
	public List <MviewDutyGroup> listMviewDutyGroupGoods();
	public List <MviewDutyGroup> listMviewDutyGroupServices();
	public List searchConsultPublishList(TsDiscussPublished consultPublish, Paging paging);
	public TsDiscussPublished findTsDiscussPublishedByPublishId(Long publishedId); 
	public Long saveTsDiscussPublished(TsDiscussPublished persistentInstance); 
	public int updateTsDiscussPublished(TsDiscussPublished persistentInstance); 
	public void deleteTsDiscussPublished(TsDiscussPublished persistentInstance); 
	public List<TsPublishedStatue> findTsPublishedStatueByPublishedId(BigDecimal publishedId);
	public Long saveTsPublishedStatue(TsPublishedStatue persistentInstance); 
	public void deleteTsPublishedStatue(TsPublishedStatue persistentInstance);
	public List<TsPublishedGood> findTsPublishedGoodByPublishedId(BigDecimal publishedId);	
	public Long saveTsPublishedGood(TsPublishedGood persistentInstance); 
	public void deleteTsPublishedGood(TsPublishedGood persistentInstance);
	public List<TsPublishedAttach> findTsPublishedAttachListByPublishId(Long publishedId);
	public TsPublishedAttach findTsPublishedAttachByAttachId(Long attachId);
	public Long saveTsPublishedAttach(TsPublishedAttach persistentInstance); 
	public void deleteTsPublishedAttach(TsPublishedAttach persistentInstance); 

	public MsStatutesub getMsStatutesubById(Long statutesubId);
	public List<MsStatutesub> getMsStatutesubList();
	public List<StStatutesub> getStStatutesubList(Long statuteId, Long stStatutesubId);
	
	public List<TmConsultDoc> getConsultDocComplete();
	//------------------------------ FINISH CONSULTPUBLISH -------------------------------- //
	
	//------------------------------ START JUDGEMENT -------------------------------------- //
	public List<TmJudgementType> findTmJudgementTypeByGroup(BigDecimal judgementGroup);
	public List searchJudgementList(TsJudgement tsJudgement, Paging paging);
	public List searchJusticeCourtList(TsJudgement tsJudgement, Paging paging);
	public List searchAdministrativeCourtList(TsJudgement instance, Paging paging);
	public List searchOtherCourtList(TsJudgement instance, Paging paging);
	public TsJudgement findTsJudgementByjudgementId(Long judgementId); 
	public Long saveTsJudgement(TsJudgement persistentInstance); 
	public int updateTsJudgement(TsJudgement persistentInstance); 
	public void deleteTsJudgement(TsJudgement persistentInstance); 
	public List<TsJudgementStatue> findTsJudgementStatueByJudgementId(BigDecimal judgementId);
	public Long saveTsJudgementStatue(TsJudgementStatue persistentInstance); 
	public void deleteTsJudgementStatue(TsJudgementStatue persistentInstance);
	public List<TsJudgementAttach> findTsJudgementAttachListByJudgementId(Long judgementId);
	public TsJudgementAttach findTsJudgementAttachByAttachId(Long attachId);
	public Long saveTsJudgementAttach(TsJudgementAttach persistentInstance); 
	public void deleteTsJudgementAttach(TsJudgementAttach persistentInstance); 
	//------------------------------ FINISH JUDGEMENT ------------------------------------- //
	/**
	 * Retrieve all <code>PetType</code>s from the data store.
	 * @return a <code>Collection</code> of <code>PetType</code>s
	 */
	//Collection<PetType> getPetTypes() throws DataAccessException;

	/**
	 * Retrieve <code>Owner</code>s from the data store by last name,
	 * returning all owners whose last name <i>starts</i> with the given name.
	 * @param lastName Value to search for
	 * @return a <code>Collection</code> of matching <code>Owner</code>s
	 * (or an empty <code>Collection</code> if none found)
	 */
//	Collection<Owner> findOwners(String lastName) throws DataAccessException;

	/**
	 * Retrieve an <code>Owner</code> from the data store by id.
	 * @param id the id to search for
	 * @return the <code>Owner</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException if not found
	 */
//	Owner loadOwner(int id) throws DataAccessException;

	/**
	 * Retrieve a <code>Pet</code> from the data store by id.
	 * @param id the id to search for
	 * @return the <code>Pet</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException if not found
	 */
//	Pet loadPet(int id) throws DataAccessException;

	/**
	 * Save an <code>Owner</code> to the data store, either inserting or updating it.
	 * @param owner the <code>Owner</code> to save
	 * @see BaseEntity#isNew
	 */
	//void storeOwner(Owner owner) throws DataAccessException;

	/**
	 * Save a <code>Pet</code> to the data store, either inserting or updating it.
	 * @param pet the <code>Pet</code> to save
	 * @see BaseEntity#isNew
	 */
	//void storePet(Pet pet) throws DataAccessException;

	/**
	 * Save a <code>Visit</code> to the data store, either inserting or updating it.
	 * @param visit the <code>Visit</code> to save
	 * @see BaseEntity#isNew
	 */
	//void storeVisit(Visit visit) throws DataAccessException;

	/**
	 * Deletes a <code>Pet</code> from the data store.
	 */
	//void deletePet(int id) throws DataAccessException;

}
