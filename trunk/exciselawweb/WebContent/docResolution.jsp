<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.io.*,java.util.*" %>  
<%@page import="com.exciselaw.law.domain.*"%> 
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.exciselaw.law.service.ExciseLawService"%>
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

var restId='<%=request.getParameter("restId") %>';

function findTsDocResolution(){
	ExciseLawAjax.findTsDocResolution(restId, {
		callback:function(data){
			if(data.restDetail!=null){
				CKEDITOR.instances["detail"].setData(data.restDetail);
			}else{
				CKEDITOR.instances["detail"].setData("");
			}
		}	
	});
}
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="init();">
<form name="frmLawlist" action="#" method="post">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="20%" class="text" valign="top"><div align="right" >รายละเอียด&nbsp;</div></td>
		<td width="80%" class="text">
			<textarea name="detail" cols="50" rows="10"></textarea>
			<script type="text/javascript">		
				CKEDITOR.replace( 'detail',
				{
					contenteditable : false,
					extraPlugins : 'uicolor',
					toolbar :
						[ ['Source', '-','Maximize']
						]
				});
			</script>
		</td>
	</tr>	
</table>
</form>
<script language="JavaScript" type="text/JavaScript">
function init(){
	findTsDocResolution();
}
</script>
</body>
</html>
