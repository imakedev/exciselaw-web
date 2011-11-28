<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.io.*,java.util.*" %>  
<%@page import="com.exciselaw.law.domain.*"%> 
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.exciselaw.law.service.ExciseLawService"%>
<%@include file="/WEB-INF/jsp/includes.jsp" %>
<%
	
%>
<html>
<head>
<title></title>
<link href="<%=request.getContextPath() %>/css/law.css" rel="stylesheet" type="text/css">
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
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="25" class="text">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="30" class="head2"><strong>ข้อมูลกฎหมาย</strong>>> <font color="#FF6600">เชื่อมโยงข้อมูล</font></strong>>><font color="#000000">มาตรา</font></td>
								<td align="right">
									<INPUT TYPE="submit" value="เลือกมาตรา">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr><td height='5'>&nbsp;</td></tr>
				<tr align='center'>
					<td align='center'>
						<table width="100%" border="0" cellspacing="0" cellpadding="2" >
							<tr>
								<td valign="top" align='right' class="text">ประเภทกฎหมาย</td>
								<td>
									<select name="cboType" >
									<option >เลือก</option>
									<option >- พระราชบัญญัติ</option>
									<option >- พระราชกำหนด</option>
									<option >- พระราชกฤษฎีกา</option>
									<option >- กฎกระทรวง</option>
									<option >- ระเบียบกระทรวง</option>
									<option >- ประกาศกระทรวง</option>
									<option >- คำสั่งกระทรวง</option>
									<option >- ระเบียบกรมสรรพสามิต</option>
									<option >- ประกาศกรมสรรพสามิต</option>
									<option >- คำสั่งกรมสรรพสามิต</option>
									<option >- ข้อบังคับกรมสรรพสามิต</option>
								</td>
							</tr>
							<tr>
								<td valign="top" align='right' class="text">ชื่อพระราชบัญญัติ</td>
								<td>
									<select name="cboType" >
									<option >เลือก</option>
									<option >พระราชบัญญัติ2</option>
									<option >พระราชบัญญัติ3</option>
									<option >พระราชบัญญัติ4</option>
									<option >พระราชบัญญัติ5</option>
								</td>
							</tr>
							<tr>
								<td valign="top" align='right' class="text">ฉบับที่</td>
								<td><select name="cboType" >
									<option >เลือก</option>
									<option >ฉบับที่ 1</option>
									<option >ฉบับที่ 2</option>
									<option >ฉบับที่ 3</option>
									<option >ฉบับที่ 4</option></td>
							</tr>
							<tr>
								<td valign="top" align='right' class="text">ปี พ.ศ.</td>
								<td><select name="cboType" >
									<option >เลือก</option>
									<option >2547</option>
									<option >2549</option>
									<option >2551</option>
									</td>
							</tr>
							<tr>
								<td valign="top" align='right' class="text">หมวด</td>
								<td valign="top" align='left' class="text">
									<select name="cboType" >
									<option >เลือก</option>
									<option >หมวด 1</option>
									<option >หมวด 2</option>
									<option >หมวด 3</option>
								</td>
							</tr>
							<tr>
								<td valign="top" align='right' class="text">ส่วน</td>
								<td valign="top" align='left' class="text">
									<select name="cboType" >
									<option >เลือก</option>
									<option >ส่วน 1</option>
									<option >ส่วน 2</option>
									<option >ส่วน 3</option>
								</td>
							</tr>
							<tr>
								<td valign="top" align='right' class="text">มาตรา</td>
								<td >
									<select name="cboType" >
									<option >เลือก</option>
									<option >มาตรา 1</option>
									<option >มาตรา 2</option>
									<option >มาตรา 3</option>
								</td>
							</tr>
							<tr>
								<td valign="top" class="text"><div align="right">เนื้อหา</div></td>
								<td colspan="7"><textarea name="textarea2" cols="40" rows="10"></textarea></td>
							</tr>
						</table>
					</td>
				</tr>
				
			</table>
		</td>
	</tr>
</table>
</body>
</html>
