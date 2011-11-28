package com.exciselaw.law.service;

import java.util.List;

import com.exciselaw.law.domain.MsStatute;
import com.exciselaw.law.domain.MsStatutesub;
import com.exciselaw.law.domain.StMatr;
import com.exciselaw.law.domain.StStatutesub;
import com.exciselaw.law.domain.TsRel;
import com.exciselaw.law.domain.TsRelDTO;
import com.exciselaw.law.domain.TsRelMap;

public interface LinkExciseLawService {

	
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
}
