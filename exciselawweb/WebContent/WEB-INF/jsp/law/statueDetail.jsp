<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<title></title>
<link href="<%=request.getContextPath() %>/css/law.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/interface/ExciseLawAjax.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script language="JavaScript" type="text/JavaScript">
<!--
var _path='<%=request.getContextPath() %>';
function MM_preloadImages() { //v3.0
	var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
function MM_findObj(n, d) { //v4.01
	var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
	if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
	for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
	if(!x && d.getElementById) x=d.getElementById(n); return x;
}
function MM_swapImgRestore() { //v3.0
	var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_swapImage() { //v3.0
	var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
	if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
function MM_jumpMenu(targ,selObj,restore){ //v3.0
	eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
	if (restore) selObj.selectedIndex=0;
}

function checkGoodsServicesAll(){
	checkGoodsServicesAllByElementName(document.getElementsByName('dutyGroupGoodsBoxes'));
	checkGoodsServicesAllByElementName(document.getElementsByName('dutyGroupServicesBoxes'));
}
function checkLawAllByElementName(el_collection){
	var selectAllValue= document.getElementById("selectAllLaw");
	for (var i=0;i<el_collection.length;i++){
 		el_collection[i].checked=selectAllValue.checked; 		
 	} 	
}
function checkGoodsServicesAllByElementName(el_collection){
	var selectAllValue= document.getElementById("selectAllGoodsServices");
	for (var i=0;i<el_collection.length;i++){
 		el_collection[i].checked=selectAllValue.checked; 		
 	} 	
}
function uncheckAll(){
	var dutyGroupGoodsBoxes = document.getElementsByName('dutyGroupGoodsBoxes');
	for (var i=0;i<dutyGroupGoodsBoxes.length;i++){
		dutyGroupGoodsBoxes[i].checked=false; 		
 	} 	

	var dutyGroupServicesBoxes = document.getElementsByName('dutyGroupServicesBoxes');
	for (var i=0;i<dutyGroupServicesBoxes.length;i++){
		dutyGroupServicesBoxes[i].checked=false; 		
 	} 	
}
function callfunc(functype)	{
	var msg = "";				
	if(functype!="doDelete"){	
		if(document.getElementById("statueType").value == ""){
			msg += "- กรุณาเลือกกฎหมาย\n";
		}	
		if(document.getElementById("statueName").value == ""){
			msg += "- กรุณากรอกชื่อพระราชบัญญัติ\n";
		}	
		if(document.getElementById("statueOrder").value == ""){
			msg += "- กรุณากรอกลำดับแสดงผล\n";
		}	
		if(document.getElementById("statueStatus").value == ""){
			msg += "- กรุณาเลือกสถานะ\n";
		}	
	}else if(functype=="doDelete"){
		if(confirm('Do you want to delete')==false){
			return false;
		}
	}
	if(msg != ""){
		alert(msg); 
		return false;
	} 
	document.getElementById("action").value = functype;
	return true;
}
function callback(data){
	if(data=="true"){
		alert('มีลำดับอยู่ในระบบแล้ว ระบบจะแสดงลำดับล่าสุดให้');
		ExciseLawAjax.findMaxStatueOrder(callback2);
	}
}
function callback2(data){
	if(data!=null && data.length>0){ 
		var order = parseInt(data)+1;
		dwr.util.setValue("statueOrder", order);
	}	
}
function numberOnly(evt){
   var charCode = (evt.which) ? evt.which : event.keyCode;
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;
   return true;
}
function checkStatueOrder(value){
	if(value!=''){
		ExciseLawAjax.checkStatueOrder(value, callback);
	}
}
-->
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<spring:url value="/law/statue.do" var="formAction"></spring:url> 
<form:form modelAttribute="masterLawForm" id="form1" method="post" action="${fn:escapeXml(formAction)}" enctype="multipart/form-data"> 
<form:hidden path="action" id="action"/> 
<form:hidden path="tmStatue.statueId"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">	
	<tr>
		<td height="30" class="head2"><strong>ข้อมูลหลัก >> พระราชบัญญัติ >> </strong><font color="#000000">รายละเอียดข้อมูล</font></td>
		<td align="right">
			<c:if test="${masterLawForm.view=='true'}">
				<INPUT TYPE="button" value="ย้อนกลับ" style="width: 80px" onClick="javascript:window.location='<spring:url value="/law/statue.do?action=search" htmlEscape="true" />'" >
			</c:if>
			<c:if test="${masterLawForm.action=='edit' || masterLawForm.action=='doEdit' || masterLawForm.action=='doDelete'}">
				<c:if test="${masterLawForm.edit=='true'}">
					<INPUT TYPE="submit" value="บันทึกข้อมูล" style="width: 80px" onClick="return callfunc('doEdit')" />	
				</c:if>
				<c:if test="${masterLawForm.delete=='true'}">
					<INPUT TYPE="submit" value="ลบข้อมูล" style="width: 80px" onClick="return callfunc('doDelete')" />
				</c:if>
			</c:if>
			<c:if test="${masterLawForm.action=='add' || masterLawForm.action=='doSave'}">
				<c:if test="${masterLawForm.add=='true'}">
					<INPUT TYPE="submit" value="บันทึกข้อมูล" style="width: 80px" onClick="return callfunc('doSave')" />		
				</c:if>
			</c:if>
		</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">			
	<tr><td height='5'>&nbsp;</td></tr>
	<tr>
		<td width="20%" class="text" valign='top'><div align="right">กฎหมาย&nbsp;</div></td>
		<td width="80%" class="text" align="left">
			<form:select path="tmStatue.statueType" id="statueType" cssStyle="width: 200px" cssClass="dropdown">
			    <form:option value="" label="- - - Please Select - - -"/> 
    			<form:options items="${lawTypeList}" itemValue="id" itemLabel="name"/> 
			</form:select>
		</td>
	</tr>
	<tr>
		<td width="20%" class="text"><div align="right">ชื่อพระราชบัญญัติ&nbsp;</div></td>
		<td width="80%" class="text">
			<form:input path="tmStatue.statueName" id="statueName" cssClass="text" size="50" maxlength="100"/>&nbsp;
			ลำดับแสดงผล&nbsp;
			<form:input path="tmStatue.statueOrder" id="statueOrder" cssClass="text" size="3" maxlength="3" onkeypress="return numberOnly(event);" onblur="checkStatueOrder(this.value);"/>&nbsp;
		</td>
	</tr>	
	<tr>
		<td width="20%" class="text" valign='top'><div align="right">สถานะ&nbsp;</div></td>
		<td width="80%" class="text" align="left">
			<form:select path="tmStatue.statueStatus" id="statueStatus" cssStyle="width: 200px" cssClass="dropdown">
			    <form:option value="" label="- - - Please Select - - -"/> 
    			<form:options items="${statusList}" itemValue="id" itemLabel="name"/> 
			</form:select>
		</td>
	</tr>
	<tr>
		<td width="20%" class="text" valign="top"><div align="right" >สินค้าและบริการตามพระราชบัญญัติ&nbsp;</div></td>
		<td width="80%" class="text">
			<table>
				<tr>
					<td colspan="3" class="Ghead" align="left"><input type="checkbox" name="selectAllGoodsServices" id="selectAllGoodsServices" onClick="checkGoodsServicesAll()" />เลือกทั้งหมด</td>
				</tr>	
				<tr>
					<td colspan="3" class="text">สินค้า</td>
				</tr>
				<tr>
					<td width="auto"> 
					<c:set value="" var="endCol"/>
					<c:forEach items="${dutyGroupGoods}" var="goodsBox" varStatus="loop">  
					    <c:set value="${loop.index mod 3}" var="endCol"/>
						<c:choose>
					 		<c:when test="${loop.index mod 3 == 0}"> 
					 			<tr>
									<td align="left" class="texthead">
								 		<form:checkbox path="dutyGroupGoodsBoxes"   value="${goodsBox.groupId}"/>	
										<c:out value="${goodsBox.groupName}"/>
									</td>				 
					 		</c:when>
				 			<c:when test="${loop.index mod 3 == 2}">  
									<td align="left" class="texthead">
									 	<form:checkbox path="dutyGroupGoodsBoxes"  value="${goodsBox.groupId}"/>	
										<c:out value="${goodsBox.groupName}"/>
									</td>				 
					  			</tr>
				 			</c:when>
				 			<c:otherwise>
								<td align="left" class="texthead">
								 	<form:checkbox path="dutyGroupGoodsBoxes"  value="${goodsBox.groupId}"/>	
									<c:out value="${goodsBox.groupName}"/> 
						 		</td> 
				 			</c:otherwise>   
						</c:choose>
					</c:forEach>
				<c:if test="${endCol!='2'}">
				</tr>
				</c:if>	
				<tr>
					<td colspan="3" class="text">บริการ</td>
				</tr>
				<tr>
					<td width="auto"> 
					<c:set value="" var="endCol"/>
					<c:forEach items="${dutyGroupServices}" var="servicesBox" varStatus="loop">  
					    <c:set value="${loop.index mod 3}" var="endCol"/>
						<c:choose>
					 		<c:when test="${loop.index mod 3 == 0}"> 
					 			<tr>
									<td align="left" class="texthead">
								 		<form:checkbox path="dutyGroupServicesBoxes"   value="${servicesBox.groupId}"/>	
										<c:out value="${servicesBox.groupName}"/>
									</td>				 
					 		</c:when>
				 			<c:when test="${loop.index mod 3 == 2}">  
									<td align="left" class="texthead">
									 	<form:checkbox path="dutyGroupServicesBoxes"  value="${servicesBox.groupId}"/>	
										<c:out value="${servicesBox.groupName}"/>
									</td>				 
					  			</tr>
				 			</c:when>
				 			<c:otherwise>
								<td align="left" class="texthead">
								 	<form:checkbox path="dutyGroupServicesBoxes"  value="${servicesBox.groupId}"/>	
									<c:out value="${servicesBox.groupName}"/> 
						 		</td> 
				 			</c:otherwise>   
						</c:choose>
					</c:forEach>
				<c:if test="${endCol!='2'}">
				</tr>
				</c:if>
			</table>
		</td>
	</tr>	
	<tr>
		<td width="20%" class="text"><div align="right">เอกสารแนบ</div></td>
		<td width="80%" class="text">
			<input type="file" name="fileData" size="50"><img src="<%=request.getContextPath() %>/images/add_page.png" width="20" border="0" height="20" onClick="JavaScript:addFile();"> 
			<span id="mySpan"></span>
		</td>
	</tr>
	<tr>
		<td valign="top" align='right' class="Ghead">&nbsp;</td>
		<td align='left'>
			<input type="button" name="fileScan" value="แสกนเอกสาร">
		</td>
	</tr>
	<c:if test="${masterLawForm.action=='edit' || masterLawForm.action=='doEdit'}">
	<tr>
		<td>&nbsp;</td>
		<td >
			<table width="80%" border="0" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF" class="table1">
				<thead>
					<tr>
						<td align="center" class="text" width="5%">&nbsp;</td>
						<td align="center" class="text" width="*"><strong>ชื่อเอกสาร</strong></td>
						<td align="center" class="text" width="10%"><strong>ดูเอกสาร</strong></td>		
						<td align="center" class="text" width="10%"><strong>ลบเอกสาร</strong></td>								
					</tr >
				</thead>
				<tbody  class="text">
					<c:forEach items="${tsStatueAttachList}" var="statueAttach" varStatus="loop">  
 					<tr>
 						<td align='right' class='text'>
 						<c:set var="type" value="${fn:substring(statueAttach.fileName, fn:indexOf(statueAttach.fileName,'.')+1, fn:length(statueAttach.fileName))}"></c:set>
 						<c:if test="${type == 'xls' || type == 'xlsx' || type == 'doc' || type == 'docx'}">
 							<img src="<%=request.getContextPath() %>/images/MSWord.png" width="26" height="26">
 						</c:if>
 						<c:if test="${type == 'png' || type == 'jpg' || type == 'gif'}">
 							<img src="<%=request.getContextPath() %>/images/ACDsee.png" width="26" height="26">
 						</c:if>
 						<c:if test="${type == 'pdf'}">
 							<img src="<%=request.getContextPath() %>/images/Acrobat.png" width="26" height="26">
 						</c:if>
 						</td>
						<td align='left' class='text'>${statueAttach.fileName}</td>
						<td align="center">
							<a href="<spring:url value="/servlets.FileDownloadServlet?module=news&attachId=${statueAttach.publishedAttachId}"/>">
								<img border="0" src="<%=request.getContextPath() %>/images/icon_15.gif">
							</a>
						</td>						
						<td align="center">
							<form:checkbox path="deleteFileBoxes" value="${statueAttach.publishedAttachId}"/>
						</td>
					</tr>
					</c:forEach>					
				</tbody>
			</table>
		</td>
	</tr>
	<tr>
		<td class="text"><div align="right"><font color='#0000CC'>ผู้นำเข้า&nbsp;</font></div></td>
		<td class="text">
			<form:input path="tmStatue.createdBy" id="createdBy" cssClass="text" size="20" disabled="true"/>
		</td>
	</tr>
	<tr>
		<td class="text"><div align="right"><font color='#0000CC'>วันที่นำเข้า&nbsp;</font></div></td>
		<td class="text">
			<form:input path="createdDate" id="createdDate" cssClass="text" size="10" disabled="true"/>
		</td>
	</tr>
	</c:if>
</table>
</form:form>
<script>
function statusMessage(msg,mode){
	alert(msg);
	if(mode=='doSave'){
		uncheckAll();
		document.getElementById("statueType").value="";
		document.getElementById("statueName").value="";
		document.getElementById("statueOrder").value="";
		document.getElementById("statueStatus").value="";
	}	
}
</script>
<c:if test="${masterLawForm.isSusses=='1' && masterLawForm.action == 'doSave'}">
<script>
statusMessage("บันทึกข้อมูลเรียบร้อย\n",'doSave');
</script>
</c:if>
<c:if test="${masterLawForm.isSusses=='1' && masterLawForm.action == 'doEdit'}">
<script>
statusMessage("แก้ใขข้อมูลเรียบร้อย\n",'doEdit');
</script>
</c:if>
<c:if test="${masterLawForm.isSusses=='1' && masterLawForm.action == 'doDelete'}">
<script>
statusMessage("ลบข้อมูลเรียบร้อย\n",'doDelete');
</script>
</c:if>
</body>
</html>
