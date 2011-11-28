<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<title></title>
<link href="<%=request.getContextPath() %>/css/law.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/Calendar/calendarTLE.js"></script> 
<script	type="text/javascript" src="<%=request.getContextPath() %>/Calendar/calendar-setup.js"></script> 
<script	type="text/javascript" src="<%=request.getContextPath() %>/Calendar/lang/calendar-th.js"></script> 
<script	type="text/javascript" src="<%=request.getContextPath() %>/TabContent/tabcontent.js"></script>
<style type="text/css">
	@import url("<%=request.getContextPath() %>/Calendar/calendar-win2k-cold-1.css");
</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>
<script src="<%=request.getContextPath() %>/ckeditor/sample.js" type="text/javascript"></script>
<link href="<%=request.getContextPath() %>/ckeditor/sample.css" rel="stylesheet" type="text/css" />
<!-- Sam Skin CSS for TabView -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/yui/build/tabview/assets/skins/sam/tabview.css"> 
<!-- JavaScript Dependencies for Tabview: -->
<script src="<%=request.getContextPath() %>/js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script src="<%=request.getContextPath() %>/js/yui/build/element/element-min.js"></script> 
<!-- Source file for TabView -->
<script src="<%=request.getContextPath() %>/js/yui/build/tabview/tabview-min.js"></script>
<script language="JavaScript" type="text/JavaScript">
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

function callfunc(functype)	{
	var msg = "";				
	if(functype!="doDelete"){	
		if(document.getElementById("statueId").value == ""){
			msg += "- กรุณาเลือกพระราชบัญญัติ\n";
		}	
		if(document.getElementById("lawTypeId").value == ""){
			msg += "- กรุณาเลือกประเภทกฎหมาย\n";
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
</script>
</head> 
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" class="yui-skin-sam">
<script type="text/javascript"> 
var myTabs = new YAHOO.widget.TabView("demo");
</script> 
<!--begin custom header content for this example-->
<script type="text/javascript"> 
document.documentElement.className = "yui-pe";
</script>
<spring:url value="/law/otherLaw.do" var="formAction"></spring:url>
<form:form modelAttribute="exciseLawForm"  id="form1" method="post" action="${fn:escapeXml(formAction)}" enctype="multipart/form-data"> 
<form:hidden path="action" id="action"/> 
<table width="100%" border="0" cellpadding="0" cellspacing="0">	
	<tr>
		<td height="30" class="head2"><strong>กฎหมายอื่นๆ >> ข้อมูลกฏหมายอื่นๆ >> </strong><font color="#000000">รายละเอียดข้อมูล</font></td>
		<td align="right">
			
				<INPUT TYPE="button" value="ย้อนกลับ" style="width: 80px" onClick="javascript:window.location='<spring:url value="/law/otherLaw.do?action=search" htmlEscape="true" />'" >
			
			<c:if test="${exciseLawForm.action=='edit' || exciseLawForm.action=='doEdit' || exciseLawForm.action=='doDelete'}">
			
					<INPUT TYPE="submit" value="บันทึกข้อมูล" style="width: 80px" onClick="return callfunc('doEdit')" />	
				
					<INPUT TYPE="submit" value="ลบข้อมูล" style="width: 80px" onClick="return callfunc('doDelete')" />
			
			</c:if>
			<c:if test="${exciseLawForm.action=='add' || exciseLawForm.action=='doSave'}">

					<INPUT TYPE="submit" value="บันทึกข้อมูล" style="width: 80px" onClick="return callfunc('doSave')" />		

			</c:if>	
		</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">			
	<tr><td height='5'>&nbsp;</td></tr>
	<tr>
		<td width="20%" class="text"><div align="right">พระราชบัญญัติ&nbsp;</div></td>
		<td width="25%">
			<form:select path="tsLaw.statueId" id="statueId" cssStyle="width: 250px" cssClass="dropdown">
			    <form:option value="" label="- - - Please Select - - -"/> 
    			<form:options items="${statueList}" itemValue="statueId" itemLabel="statueName"/> 
			</form:select>
		</td>
		<td width="15%" class="text"><div align="right">ประเภทกฎหมาย&nbsp;</div></td>                       
		<td width="40%">
			<form:select path="tsLaw.lawTypeId" id="lawTypeId" cssStyle="width: 250px" cssClass="dropdown">
			    <form:option value="" label="- - - Please Select - - -"/> 
    			<form:options items="${lawTypeList}" itemValue="lawTypeId" itemLabel="lawTypeName"/> 
			</form:select>&nbsp;&nbsp;
			ลำดับที่&nbsp;<form:input path="primaryKey" id="lawId" cssClass="text" cssStyle="width: 50px" maxlength="100" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td width="20%" class="text"><div align="right">ประกาศในราชกิจจานุเบกษา ฉบับ&nbsp;</div></td>
		<td width="25%" class="text">
			<form:input path="tsLaw.gazetteNo" id="gazetteNo" cssClass="text" cssStyle="width: 100px" maxlength="100"/>&nbsp;	
			เล่มที่&nbsp;<form:input path="tsLaw.gazetteVol" id="gazetteVol" cssClass="text" cssStyle="width: 100px" maxlength="100"/>		
		</td>
		<td width="15%" class="text"><div align="right">ตอนที่&nbsp;</div></td>                       
		<td width="40%" class="text"><form:input path="tsLaw.gazetteEpisode" id="gazetteEpisode" cssClass="text" cssStyle="width: 100px" maxlength="100"/>&nbsp;		
			ลงวันที่&nbsp;<form:input path="tsLaw.gazetteDated" id="gazetteDated" cssClass="text" cssStyle="width: 100px" maxlength="100"/>
		</td>
	</tr>
	<tr>
		<td width="20%" class="text"><div align="right">สถานะการบังคับใช้&nbsp;</div></td>
		<td width="25%" class="text">	
			<form:select path="tsLaw.lawEnforceStatus" id="lawEnforceStatus" cssStyle="width: 250px" cssClass="dropdown">
			    <form:option value="" label="- - - Please Select - - -"/> 
    			<form:options items="${statusList}" itemValue="id" itemLabel="name"/> 
			</form:select>
		</td>
		<td width="15%" class="text"><div align="right">สถานะการแสดงผล&nbsp;</div></td>                       
		<td width="40%">
			<form:select path="tsLaw.lawDisplayStatus" id="lawDisplayStatus" cssStyle="width: 250px" cssClass="dropdown">
			    <form:option value="" label="- - - Please Select - - -"/> 
    			<form:options items="${displayList}" itemValue="id" itemLabel="name"/> 
			</form:select>	
		</td>
	</tr>
	<tr><td height='5'>&nbsp;</td></tr>	
	<tr><td height='5'>&nbsp;</td></tr>	
	<tr>
		<td colspan="4" valign="top" class="text">
			<div  id="demo" class="yui-navset">
				<ul class="yui-nav">
					<li class="selected"><a href="#tab1"><em>ชื่อเรื่อง</em></a></li>
					<li><a href="#tab2"><em>เนื้อความ</em></a></li>
					<li><a href="#tab3"><em>ตารางแนบท้าย</em></a></li>
					<li><a href="#tab4"><em>เอกสารแนบ</em></a></li>
					<li><a href="#tab5"><em>กฎหมาย/สินค้าและบริการที่เกี่ยวข้อง</em></a></li>
				</ul>    
				<div class="yui-content"> 
	       			<%--start tab1 --%> 
					<div> 
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
	                		<tr>
			                    <td height="15" valign="top" class="text">&nbsp;</td>
			                    <td height="15" align="left" valign="top">&nbsp;</td>
                   			</tr>
                    		<tr>
                     			<td width="18%" valign="top" class="text">
									<div align="right" valign="top">ชื่อเรื่อง(ภาษาไทย)&nbsp;</div>
								</td>
								<td width="82%" align="left" valign="top">
									<form:textarea path="tsLaw.lawTitleThai" cols="60" rows="3"/>
									<script type="text/javascript">		
											CKEDITOR.replace( 'tsLaw.lawTitleThai',
											{
												extraPlugins : 'uicolor',
												toolbar :
													[ 	['Source','Templates','-','Cut','Copy','Paste','PasteText','PasteFromWord','Undo','Redo','-','Find','Replace','SelectAll'],'/',
														['Bold','Italic','Underline','Strike','Subscript','Superscript','RemoveFormat','-',
															'NumberedList','BulletedList','Outdent','Indent','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-',
															'Link','Unlink','-','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],'/',
														['Styles','Format','Font','FontSize','-','TextColor','BGColor','-','Maximize']
													],
												height: "50"
											});
									</script>
						 		</td>
							</tr>
							<tr>
								<td valign="middle" class="text">
									<div align="right">ฉบับที่&nbsp;</div>	  
								</td>
			  					<td height="30" align="left" valign="middle" class="text">
			  						<form:input path="tsLaw.lawNo" id="lawNo" cssClass="text" cssStyle="width: 100px" maxlength="100"/>
			     					&nbsp;ปี พ.ศ.&nbsp;
			      					<form:input path="tsLaw.lawYear" id="lawYear" cssClass="text" cssStyle="width: 100px" maxlength="100"/>
			      				</td>
							</tr>
							<tr>
                     			<td width="18%" valign="top" class="text">
									<div align="right" valign="top">ชื่อเรื่อง(Eng)&nbsp;</div>
								</td>
								<td width="82%" align="left" valign="top">
									<form:textarea path="tsLaw.lawTitleEng" cols="60" rows="3"/>
									<script type="text/javascript">		
											CKEDITOR.replace( 'tsLaw.lawTitleEng',
											{
												extraPlugins : 'uicolor',
												toolbar :
													[ 	['Source','Templates','-','Cut','Copy','Paste','PasteText','PasteFromWord','Undo','Redo','-','Find','Replace','SelectAll'],'/',
														['Bold','Italic','Underline','Strike','Subscript','Superscript','RemoveFormat','-',
															'NumberedList','BulletedList','Outdent','Indent','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-',
															'Link','Unlink','-','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],'/',
														['Styles','Format','Font','FontSize','-','TextColor','BGColor','-','Maximize']
													],
												height: "50"
											});
									</script>
						 		</td>
							</tr>
        				</table>
        			</div>
        			<%--end tab1 --%>
			        <%--start tab2 --%>
					<div>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
			                    <td height="15" valign="top" class="text">&nbsp;</td>
			                    <td height="15" align="left" valign="top">&nbsp;</td>
                   			</tr>
                    		<tr>
                     			<td width="18%" valign="top" class="text">
									<div align="right" valign="top">เนื้อหา&nbsp;</div>
								</td>
								<td width="82%" align="left" valign="top">
									<form:textarea path="tsLaw.lawDetail" cols="60" rows="3"/>
									<script type="text/javascript">		
											CKEDITOR.replace( 'tsLaw.lawDetail',
											{
												extraPlugins : 'uicolor',
												toolbar :
													[ 	['Source','Templates','-','Cut','Copy','Paste','PasteText','PasteFromWord','Undo','Redo','-','Find','Replace','SelectAll'],'/',
														['Bold','Italic','Underline','Strike','Subscript','Superscript','RemoveFormat','-',
															'NumberedList','BulletedList','Outdent','Indent','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-',
															'Link','Unlink','-','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],'/',
														['Styles','Format','Font','FontSize','-','TextColor','BGColor','-','Maximize']
													]
											});
									</script>
						 		</td>
							</tr>
                    		<tr>
                     			<td width="18%" valign="top" class="text">
									<div align="right" valign="top">หมายเหตุ&nbsp;</div>
								</td>
								<td width="82%" align="left" valign="top">
									<form:textarea path="tsLaw.lawRemark" id="lawRemark" cols="60" rows="3"/>
						 		</td>
							</tr>
        				</table>
        			</div>
        			<%--end tab2 --%>
			        <%--start tab3 --%>
					<div>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
			                    <td height="15" valign="top" class="text">&nbsp;</td>
			                    <td height="15" align="left" valign="top">&nbsp;</td>
                   			</tr>
                    		<tr>
                     			<td width="18%" valign="top" class="text">
									<div align="right" valign="top">ตารางแนบท้าย&nbsp;</div>
								</td>
								<td width="82%" align="left" valign="top">
									<form:textarea path="tsLaw.lawAttacthTable" cols="60" rows="3"/>
									<script type="text/javascript">		
											CKEDITOR.replace( 'tsLaw.lawAttacthTable',
											{
												extraPlugins : 'uicolor',
												toolbar :
													[ 	['Source','Templates','-','Cut','Copy','Paste','PasteText','PasteFromWord','Undo','Redo','-','Find','Replace','SelectAll'],'/',
														['Bold','Italic','Underline','Strike','Subscript','Superscript','RemoveFormat','-',
															'NumberedList','BulletedList','Outdent','Indent','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-',
															'Link','Unlink','-','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],'/',
														['Styles','Format','Font','FontSize','-','TextColor','BGColor','-','Maximize']
													]
											});
									</script>
						 		</td>
							</tr>
        				</table>
        			</div>
        			<%--end tab3 --%>
			        <%--start tab4 --%>
					<div>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
			                    <td height="15" valign="top" class="text">&nbsp;</td>
			                    <td height="15" align="left" valign="top">&nbsp;</td>
                   			</tr>   
							<tr>
                     			<td width="18%" valign="top" class="text">
									<div align="right" valign="top">เอกสารแนบ&nbsp;</div>
								</td>
								<td width="82%" align="left" valign="top">
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
							<c:if test="${exciseLawForm.action=='edit' || exciseLawForm.action=='doEdit'}">
							<tr>
								<td>&nbsp;</td>
								<td>
									<table width="80%" border="0" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF" class="table1">
										<thead>
											<tr>
												<td align="center" class="text" width="5%">&nbsp;</td>
												<td align="center" class="text" width="*"><strong>ชื่อเอกสาร</strong></td>
												<td align="center" class="text" width="10%"><strong>ดูเอกสาร</strong></td>		
												<td align="center" class="text" width="10%"><strong>ลบเอกสาร</strong></td>								
											</tr>
										</thead>
										<tbody  class="text">
											<c:forEach items="${lawAttachList}" var="lawAttach" varStatus="loop">  
						 					<tr>
						 						<td align='right' class='text'>
						 						<c:set var="type" value="${fn:substring(lawAttach.fileName, fn:indexOf(lawAttach.fileName,'.')+1, fn:length(lawAttach.fileName))}"></c:set>
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
												<td align='left' class='text'>${lawAttach.fileName}</td>
												<td align="center">
													<a href="<spring:url value="/servlets.FileDownloadServlet?module=news&attachId=${lawAttach.lawAttachId}"/>">
														<img border="0" src="<%=request.getContextPath() %>/images/icon_15.gif">
													</a>
												</td>						
												<td align="center">
													<form:checkbox path="deleteFileBoxes" value="${lawAttach.lawAttachId}"/>
												</td>
											</tr>
											</c:forEach>				
										</tbody>
									</table>
								</td>
							</tr>
							</c:if>
        				</table>
        			</div>
        			<%--end tab4 --%>
			        <%--start tab5 --%>
					<div>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
			                    <td height="15" valign="top" class="text">&nbsp;</td>
			                    <td height="15" align="left" valign="top">&nbsp;</td>
                   			</tr>                   			
							<tr>
                     			<td width="18%" valign="top" class="text">
									<div align="right" valign="top">กฎหมายที่เกี่ยวข้อง&nbsp;</div>
								</td>
								<td width="82%" align="left" valign="top">
									<table>
										<tr>
											<td colspan="3" class="Ghead" align="left"><input type="checkbox" name="selectAllStatute" id="selectAllStatute" onClick="checkStatueAll()" />เลือกทั้งหมด</td>
										</tr>	
										<tr>
											<td width="auto"> 
											<c:set value="" var="endCol"/>
											<c:forEach items="${statutes}" var="box" varStatus="loop">  
											    <c:set value="${loop.index mod 3}" var="endCol"/>
												<c:choose>
											 		<c:when test="${loop.index mod 3 == 0}"> 
											 			<tr>
															<td align="left" class="texthead">
														 		<form:checkbox path="statutesBoxes"   value="${box.statueId}"/>	
																<c:out value="${box.statueName}"/>
															</td>				 
											 		</c:when>
										 			<c:when test="${loop.index mod 3 == 2}">  
															<td align="left" class="texthead">
															 	<form:checkbox path="statutesBoxes"  value="${box.statueId}"/>	
																<c:out value="${box.statueName}"/>
															</td>				 
											  			</tr>
										 			</c:when>
										 			<c:otherwise>
														<td align="left" class="texthead">
														 	<form:checkbox path="statutesBoxes"  value="${box.statueId}"/>	
															<c:out value="${box.statueName}"/> 
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
                     			<td width="18%" valign="top" class="text">
									<div align="right" valign="top">สินค้าและบริการตามพระราชบัญญัติ&nbsp;</div>
								</td>
								<td width="82%" align="left" valign="top">
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
                     			<td width="18%" valign="top" class="text">
									<div align="right" valign="top">คำช่วยค้น (Keyword)&nbsp;</div>
								</td>
								<td width="82%" align="left" valign="top">
									<form:textarea path="tsLaw.lawKeyword" id="lawKeyword" rows="3" cols="90"/>
								</td>
							</tr>
        				</table>
        			</div>
        			<%--end tab5 --%>
        		</div>
			</div>
		</td>
	</tr>
	<c:if test="${exciseLawForm.action=='edit' || exciseLawForm.action=='doEdit'}">
	<tr>
		<td class="text"><div align="right"><font color='#0000CC'>ผู้นำเข้า&nbsp;</font></div></td>
		<td class="text" colspan="3">
			<form:input path="tsLaw.createdBy" id="createdBy" cssClass="text" size="20" disabled="true"/>
		</td>
	</tr>
	<tr>
		<td class="text"><div align="right"><font color='#0000CC'>วันที่นำเข้า&nbsp;</font></div></td>
		<td class="text" colspan="3">
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
		document.getElementById("statueId").value="";
		document.getElementById("lawTypeId").value="";
		document.getElementById("lawEnforceStatus").value="";
		document.getElementById("gazetteNo").value="";
		document.getElementById("gazetteVol").value="";
		document.getElementById("gazetteEpisode").value="";
		document.getElementById("gazetteDated").value="";
		document.getElementById("lawTitleThai").value="";
		document.getElementById("lawNo").value="";
		document.getElementById("lawYear").value="";
		document.getElementById("lawRemark").value="";
		document.getElementById("lawYear").value="";
		document.getElementById("lawKeyword").value="";
		
		CKEDITOR.instances["tsLaw.lawTitleThai"].setData("");
		CKEDITOR.instances["tsLaw.lawTitleEng"].setData("");
		CKEDITOR.instances["tsLaw.lawDetail"].setData("");
		CKEDITOR.instances["tsLaw.lawAttacthTable"].setData("");		
	}	
}
</script>
<c:if test="${exciseLawForm.isSusses=='1' && exciseLawForm.action == 'doSave'}">
<script>
statusMessage("บันทึกข้อมูลเรียบร้อย\n",'doSave');
</script>
</c:if>
<c:if test="${exciseLawForm.isSusses=='1' && exciseLawForm.action == 'doEdit'}">
<script>
statusMessage("แก้ใขข้อมูลเรียบร้อย\n",'doEdit');
</script>
</c:if>
<c:if test="${exciseLawForm.isSusses=='1' && exciseLawForm.action == 'doDelete'}">
<script>
statusMessage("ลบข้อมูลเรียบร้อย\n",'doDelete');
</script>
</c:if>
</body>
</html>
