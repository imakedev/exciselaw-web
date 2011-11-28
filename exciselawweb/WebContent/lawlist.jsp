<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.io.*,java.util.*" %>  
<%@page import="com.exciselaw.law.domain.*"%> 
<%@include file="/WEB-INF/jsp/includes.jsp" %>
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
function setLawTypeList(){
	ExciseLawAjax.getTmLawTypeList({
		callback:function(data){
			if(data!=null && data.length>0){
				for(var i=0;i<data.length;i++){
		            dwr.util.removeAllOptions("lawTypeId");
		            dwr.util.addOptions("lawTypeId", ["- - - Please Select - - -"]);
		            dwr.util.addOptions("lawTypeId", data, "lawTypeId", "lawTypeName");
				}
			}
		}	
	});
}
function setStatueList(){
	ExciseLawAjax.getTmStatueList({
		callback:function(data){
			if(data!=null && data.length>0){
				for(var i=0;i<data.length;i++){
		            dwr.util.removeAllOptions("statueId");
		            dwr.util.addOptions("statueId", ["- - - Please Select - - -"]);
		            dwr.util.addOptions("statueId", data, "statueId", "statueName");
				}
			}
		}	
	});
}
function getTopic(){
	var lawTypeId = document.getElementById("lawTypeId").value;
	var statueId = document.getElementById("statueId").value;
	if(lawTypeId != "" && statueId != "" && lawTypeId != "- - - Please Select - - -" && statueId != "- - - Please Select - - -"){
		if(lawTypeId == "1" || lawTypeId == "2" || lawTypeId == "3"){
			ExciseLawAjax.findStMatrListBystatueId(lawTypeId ,{
				callback:function(data){
					if(data!=null && data.length>0){
						for(var i=0;i<data.length;i++){
				            dwr.util.removeAllOptions("stMatrId");
				            dwr.util.addOptions("stMatrId", ["- - - Please Select - - -"]);
				            dwr.util.addOptions("stMatrId", data, "stMatrId", "matr");
						}
						document.getElementById("stMatrList").style.display="block";
						document.getElementById("stSatutesubList").style.display="none";
						document.getElementById("stSatutesubList").value="";
						document.getElementById("lawDetail").style.display="none";
						CKEDITOR.instances["detail"].setData("");
					}else{
						document.getElementById("stMatrList").style.display="none";
						document.getElementById("stMatrList").value="";
						document.getElementById("stSatutesubList").style.display="none";
						document.getElementById("stSatutesubList").value="";
						document.getElementById("lawDetail").style.display="none";
						CKEDITOR.instances["detail"].setData("");
					}
				}	
			});

		}else{
			ExciseLawAjax.getStStatutesubList(statueId, lawTypeId,{
				callback:function(data){
					if(data!=null && data.length>0){
						for(var i=0;i<data.length;i++){
				            dwr.util.removeAllOptions("stSatutesubId");
				            dwr.util.setEscapeHtml(true);
				            dwr.util.addOptions("stSatutesubId", ["- - - Please Select - - -"]);
				            dwr.util.addOptions("stSatutesubId", data, "stlawTypeId", "article");
						}
						document.getElementById("stSatutesubList").style.display="block";
						document.getElementById("stMatrList").style.display="none";
						document.getElementById("stMatrList").value="";
						document.getElementById("lawDetail").style.display="none";
						CKEDITOR.instances["detail"].setData("");
					}else{
						document.getElementById("stSatutesubList").style.display="none";
						document.getElementById("stSatutesubList").value="";
						document.getElementById("stMatrList").style.display="none";
						document.getElementById("stMatrList").value="";
						document.getElementById("lawDetail").style.display="none";
					}
				}	
			});
		}
	}
}
function getMatrDetail(){
	var stMatrId = document.getElementById("stMatrId").value;
	if(stMatrId != ""){
		ExciseLawAjax.findStMatrById(stMatrId,{
			callback:function(data){
				if(data.detail!=null){
					document.getElementById("lawDetail").style.display="block";
					CKEDITOR.instances["detail"].setData(data.detail);
				}else{
					CKEDITOR.instances["detail"].setData("");
				}
			}	
		});
	}
}
function getLawDetail(){
	var stSatutesubId = document.getElementById("stSatutesubId").value;
	if(stSatutesubId != ""){
		ExciseLawAjax.findStStatutesubById(stSatutesubId,{
			callback:function(data){
				if(data.detail!=null){
					document.getElementById("lawDetail").style.display="block";
					CKEDITOR.instances["detail"].setData(data.detail);
				}else{
					CKEDITOR.instances["detail"].setData("");
				}
			}	
		});
	}
}
function setLawData(){
	var detail = CKEDITOR.instances["detail"].getData();
	var lawdetail = window.opener.CKEDITOR.instances.lawInvolved;
	lawdetail.setData(detail);
	window.close();
}
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="init();">
<form name="frmLawlist" action="#" method="post">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="30" class="head2" ><strong>ข้อมูลกฎหมาย >> เชื่อมโยงข้อมูล >></strong><font color="#000000">รายละเอียด</font></td>
		<td align="right">
			<input type="button" value="เลือกข้อมูล" onclick="setLawData();">
		</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">		
	<tr><td height="5">&nbsp;</td></tr>
	<tr>
		<td valign="top" align="right" class="text"><div align="right" >ชื่อพระราชบัญญัติ&nbsp;</div></td>
		<td align="left">
			<select name="statueId" id="statueId" style="width: 300px" onchange="getTopic()">
			    <option value="">- - - Please Select - - -</option> 
			</select>
		</td>
	</tr>
	<tr>
		<td valign="top" align="right" class="text" width="20%"><div align="right" >ประเภทกฎหมาย&nbsp;</div></td>
		<td align="left" width="80%">
			<select name="lawTypeId" id="lawTypeId" style="width: 300px" onchange="getTopic()">
			    <option value="">- - - Please Select - - -</option> 
			</select>
		</td>
	</tr>
	<tr id="stMatrList" style="display: none;">
		<td valign="top" align="right" class="text"><div align="right" >ข้อมูลมาตรา&nbsp;</div></td>
		<td align="left">
			<select name="stMatrId" id="stMatrId" style="width: 100px" onchange="getMatrDetail()">
			    <option value="">- - - Please Select - - -</option> 
			</select>
		</td>
	</tr>	
	<tr id="stSatutesubList" style="display: none;">
		<td valign="top" align="right" class="text"><div align="right" >ข้อมูลกฎหมาย&nbsp;</div></td>
		<td align="left">
			<select name="stSatutesubId" id="stSatutesubId" onchange="getLawDetail()">
			    <option value="">- - - Please Select - - -</option> 
			</select>
		</td>
	</tr>	 
	<tr id="lawDetail" style="display: none;">
		<td width="20%" class="text" valign="top"><div align="right" >เนื้อหา&nbsp;</div></td>
		<td width="80%" class="text">
			<textarea name="detail" cols="50" rows="10"></textarea>
			<script type="text/javascript">		
				CKEDITOR.replace( 'detail',
				{
					extraPlugins : 'uicolor',
					toolbar :
						[ ['Source', '-','Cut','Copy','PasteText','PasteFromWord','Preview','-', 'Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript', 'Superscript', '-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','NumberedList', 'BulletedList','-','Indent','Outdent','-','Link','Unlink', '-', 'MyButton' ] ,'/', 
							 ['Styles','Format','Font','FontSize','-','TextColor','BGColor','Table']
						]
				});
			</script>
		</td>
	</tr>	
</table>
</form>
<script language="JavaScript" type="text/JavaScript">
function init(){
	setStatueList();
	setLawTypeList();
}
</script>
</body>
</html>
