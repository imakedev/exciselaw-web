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

function callfunc(functype)	{
	var msg = "";				
	if(functype!="doDelete"){	
		if(document.getElementById("lawTypeType").value == ""){
			msg += "- กรุณาเลือกกฎหมาย\n";
		}	
		if(document.getElementById("lawTypeName").value == ""){
			msg += "- กรุณากรอกหมวด\n";
		}		
		if(document.getElementById("lawTypeOrder").value == ""){
			msg += "- กรุณากรอกลำดับแสดงผล\n";
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
		ExciseLawAjax.findMaxLawTypeOrder(callback2);
	}
}
function callback2(data){
	if(data!=null && data.length>0){ 
		var order = parseInt(data)+1;
		dwr.util.setValue("lawTypeOrder", order);
	}	
}
function numberOnly(evt){
   var charCode = (evt.which) ? evt.which : event.keyCode;
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;
   return true;
}
function checkLawTypeOrder(value){
	if(value!=''){
		ExciseLawAjax.checkLawTypeOrder(value, callback);
	}
}
-->
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<spring:url value="/law/lawType.do" var="formAction"></spring:url>
<form:form modelAttribute="masterLawForm" id="form1" method="post" action="${fn:escapeXml(formAction)}"> 
<form:hidden path="action" id="action"/> 
<form:hidden path="tmLawType.lawTypeId"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">	
	<tr>
		<td height="30" class="head2"><strong>ข้อมูลหลัก >> ประเภทกฎหมาย >> </strong><font color="#000000">รายละเอียดข้อมูล</font></td>
		<td align="right">
			<c:if test="${masterLawForm.view=='true'}">
				<INPUT TYPE="button" value="ย้อนกลับ" style="width: 80px" onClick="javascript:window.location='<spring:url value="/law/lawType.do?action=search" htmlEscape="true" />'" >
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
			<form:select path="tmLawType.lawTypeType" id="lawTypeType" cssStyle="width: 200px" cssClass="dropdown">
			    <form:option value="" label="- - - Please Select - - -"/> 
    			<form:options items="${lawTypeList}" itemValue="id" itemLabel="name"/> 
			</form:select>
		</td>
	</tr>
	
	<!-- fix type เป็น 1 เสมอ tr>
		<td width="20%" class="text"><div align="right">กลุ่มกฎหมาย&nbsp;</div></td>
		<td width="80%" class="text">
			<form:select path="tmLawType.lawTypePriority" id="lawTypePriority" cssStyle="width: 200px" cssClass="dropdown">
			    <form:option value="" label="- - - Please Select - - -"/> 
    			<form:options items="${lawPriorityList}" itemValue="id" itemLabel="name"/> 
			</form:select>
		</td>
	</tr>	
	 -->
	<tr>
		<td width="20%" class="text" valign='top'><div align="right">ชื่อประเภทกฎหมาย&nbsp;</div></td>
		<td width="80%" class="text" align="left">
			<form:input path="tmLawType.lawTypeName" id="lawTypeName" cssClass="text" size="50" maxlength="100"/>
			ลำดับแสดงผล&nbsp;
			<form:input path="tmLawType.lawTypeOrder" id="lawTypeOrder" cssClass="text" size="3" maxlength="3" onkeypress="return numberOnly(event);" onblur="checkLawTypeOrder(this.value);"/>&nbsp;
		</td>
	</tr>
	<c:if test="${masterLawForm.action=='edit' || masterLawForm.action=='doEdit'}">
	<tr>
		<td class="text"><div align="right"><font color='#0000CC'>ผู้นำเข้า&nbsp;</font></div></td>
		<td class="text">
			<form:input path="tmLawType.createdBy" id="createdBy" cssClass="text" size="20" disabled="true"/>
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
		document.getElementById("lawTypeType").value="";
		document.getElementById("lawTypeName").value="";
		document.getElementById("lawTypeOrder").value="";
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
