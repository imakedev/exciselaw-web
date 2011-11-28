<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.io.*,java.util.*" %>  
<%@page import="com.exciselaw.law.domain.*"%> 
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.exciselaw.law.service.ExciseLawService"%>
<%@include file="/WEB-INF/jsp/includes.jsp" %>
<%
	String action =  request.getParameter("action");
	String masterWordId = request.getParameter("masterWordId");
%>
<html>
<head>
<title></title>
<link href="<%=request.getContextPath() %>/css/law.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/interface/ExciseLawAjax.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>
<script src="<%=request.getContextPath() %>/ckeditor/sample.js" type="text/javascript"></script>
<link href="<%=request.getContextPath() %>/ckeditor/sample.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/JavaScript">
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
function getMasterWordDetail(masterWordId){
	ExciseLawAjax.findTsMasterWordBymasterWordId(masterWordId,{
		callback:function(data){
			if(data.masterWord!=null){
				dwr.util.setValue("masterWordId", data.masterWordId);
				dwr.util.setValue("masterWord", data.masterWord);
			}
		}	
	});
}
function doSave(){
	var masterWord={ 
			masterWord:document.getElementById("masterWord").value
		};

	ExciseLawAjax.saveTsMasterWord(masterWord,{
		callback:function(data){
			if(data!=null && data >1){
				alert("บันทึกข้อมูลเรียบร้อย");
			}else{
				alert("เกิดความผิดพลาด");
			}
		}	
	});
}
function doEdit(){
	var masterWord={ 
			masterWordId:document.getElementById("masterWordId").value,
			masterWord:document.getElementById("masterWord").value
		};

	ExciseLawAjax.updateTsMasterWord(masterWord,{
		callback:function(data){
			if(data!=null && data >1){
				alert("บันทึกข้อมูลเรียบร้อย");
			}else{
				alert("เกิดความผิดพลาด");
			}
		}	
	});
	window.close();
}
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="init();">
<form name="managementForm" action="#" method="post">
<input type="hidden" name="masterWordId" id="masterWordId"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="30" class="head2" ><strong>คำเหมือน/คำคล้าย >></strong>
			<font color="#000000">
				<% if("add".equals(action)){%>
					เพิ่มข้อมูล
				<%} else {%>
					แก้ไขข้อมูล
				<%} %>
			</font>
		</td>
		<td align="right">
			<% if("add".equals(action)){%>
				<input type="button" value="เพิ่มข้อมูล" onclick="doSave();">
			<%} else {%>
				<input type="button" value="เแก้ไขข้อมูล" onclick="doEdit();">
			<%} %>
		</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">		
	<tr><td height="5">&nbsp;</td></tr>
	<tr>
		<td valign="top" align="right" class="text" width="20%"><div align="right" >คำหลัก&nbsp;</div></td>
		<td align="left" width="80%">
			<input type="text" name="masterWord" id="masterWord" cssClass="text" size="30"/>
		</td>
	</tr> 
</table>
</form>
<script language="JavaScript" type="text/JavaScript">

function init(){
	var action = '<%=action%>';
	var masterWordId = '<%=masterWordId%>';
	if(action == 'edit')
		getMasterWordDetail(masterWordId);
}
</script>
</body>
</html>
