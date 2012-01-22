package com.excise.law.service;

import java.util.List;

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

public interface LinkExciseLawService {
	public List<TsRelTableMap> listRelTableMap();
	public Long saveRelKeyMap(TsRelKeyMap persistentInstance);
	public int updateRelKeyMap(TsRelKeyMap transientInstance);	
	public void deleteRelKeyMap(TsRelKeyMap persistentInstance);  
	public TsRelKeyMap getRelKeyMapById(Long rkmId); 
	
	public List<MsStatute> listMsStatute();	
	public MsStatute getMsStatute(Long statuteId);
	public List<StStatutesub> listStStatutesub();
	public StStatutesub getStStatutesub(Long stStatutesubId);
	public StMatr getStMatr(Long stMatrId);
	List<StMatr> listStMatrByMsStatute(MsStatute msStatute);
	public List<MsStatutesub> listMsStatutesub();
	public List<StStatutesub> listStStatutesubByMsStatutesub(MsStatutesub msStatutesub); 
	public List listMsStatuteAndMsStatutesub(); 
	public List<StStatutesub> listStStatutesub( MsStatute msStatute,MsStatutesub msStatutesub);
	public TsRelMap getRelMapById(Long rmId);
	public Long saveRelMap(TsRelMap persistentInstance);
	public int updateRelMap(TsRelMap transientInstance);
	public void deleteRelMap(TsRelMap persistentInstance);
	
	public List<TsRelDTO> listTsRel(Long rmId);
	public TsRel getTsRelById(Long trId);
	public Long saveTsRel(TsRel persistentInstance);
	public int updateTsRel(TsRel transientInstance);
	public List<TsRelDTO>  deleteTsRel(TsRel persistentInstance);
	
	// new Link
	public List<TmStatue> listTmStatues();
	public List<TmLawType> listTmLawTypes();
	
	public List<TmArticleGroup> listTmArticleGroups();
	public List<TmArticleSection> listTmArticleSections();
	
	public List<TsExArticleCompleted> listTsExArticleCompleteds(String statueId, String lawTypeId);
	public List<TsArticle> listTsArticles(String statueId, String lawTypeId);
	public List<TsLaw> listTsLaws (String statueId, String lawTypeId);
	
	public List<TsExArticleHeader> listTsExArticleHeader(String type,String statueId, String lawTypeId);
	
	public List<TsRelDTO> listExistingLink(Long rmId);
	public TsLaw getTsLaw(Long lawId);
	public List<TsArticle> getTsArticleList(String groupId);
	
	public TsExArticleCompleted getTsExArticleCompleted(Long articleCompletedId);
	public TsArticle getTsArticle(Long articleId);
}
