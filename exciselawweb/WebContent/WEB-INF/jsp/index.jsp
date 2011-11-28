<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@page import="java.util.*"%>

<%@page import="com.exciselaw.law.domain.TmUserinfo"%><html>
<head>
<title>ระบบกฏหมายสรรพสามิต</title>
<script language="JavaScript" type="text/JavaScript">
<!--
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
//-->
</script>

<!-- ######กำหนด IFrame ให้ทุก	 browser	ทำงานเหมือนกัน###### -->
<script language="JavaScript">
<!--
function resize_iframe(){
	var height=window.innerWidth;//Firefox
	if (document.body.clientHeight){
		height=document.body.clientHeight;//IE
	}
	document.getElementById("glu").style.height=parseInt(height-document.getElementById("glu").offsetTop-130)+"px";
}
window.onresize=resize_iframe; 
//-->
</script>
<!-- จบ -->

<!--######Show/Hide กำหนดให้เมนูด้านซ้ายหายทั้งหมด###### -->
<script type="text/javascript">
function change(id){ 
	ID = document.getElementById(id); 
	ID.style.width="200px";
    if(ID.style.display == "") 
    	ID.style.display = "none"; 
    else 
    	ID.style.display = ""; 
}

</script>
<!-- จบ -->

<!--######Show/Hide เมนู###### -->
<script type="text/javascript">
function MenuChange(id){ 
	id=parseInt(id);
	MenuMax=10; // กำหนดจำนวนเมน+1
	for(var a = 0; a < (MenuMax-1); a++){
		if(a == id) {
			document.getElementById('Menu'+a).style.display = ""; 
			document.getElementById('SubMenu'+a).style.display = ""; 
			document.getElementById('TdMenu'+a).style.backgroundImage ="url(images/bg_menu2.jpg)";  
			document.getElementById('TdMenu'+a).onmouseout = function(){
				this.style.backgroundImage ="url(images/bg_menu2.jpg)";  
			};
		}else { 
			document.getElementById("Menu"+a+"").style.display = "none"; 		
			document.getElementById('SubMenu'+a+"").style.display = "none"; 
			document.getElementById('TdMenu'+a+"").style.backgroundImage ="url(images/bg_menu1.jpg)";  
			document.getElementById('TdMenu'+a+"").onmouseover = function(){
				this.style.backgroundImage ="url(images/bg_menu2.jpg)";  
			};
			document.getElementById('TdMenu'+a+"").onmouseout = function(){
				this.style.backgroundImage ="url(images/bg_menu1.jpg)";  
			};
		}
	}
}

var _path = '<%=request.getContextPath()%>';
function submitPage(){
	document.forms["index_form"].submit();
}

</script>
<style> 
.Normal {background-image:url("images/bg_menu1.jpg");}
.Highlight {background-image:url("images/bg_menu2.jpg");}

</style>
<!-- จบ -->

<link href="css/law.css" rel="stylesheet" type="text/css">
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="MM_preloadImages('images/top_1b.jpg','images/top_2b.jpg','images/top_3b.jpg','images/top_4b.jpg','images/top_5b.jpg','images/tool_01b.jpg','images/tool_02b.jpg','images/tool_03b.jpg','images/tool_04b.jpg','images/tool_05b.jpg')">
<form name="index_form" id="index_form" method="post" action="logout.do">
<%
HashMap<String, String> screenMap = new HashMap<String, String>();
TmUserinfo userInfo = new TmUserinfo();
if(request.getSession().getAttribute("screenMap")!=null){ 
	screenMap = (HashMap<String, String>)request.getSession().getAttribute("screenMap");
}
if(request.getSession().getAttribute("userInfo")!=null){
	userInfo = (TmUserinfo)request.getSession().getAttribute("userInfo");
}
%>
<c:set value="<%=screenMap%>" var="screenMap"/>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr> 
		<td valign="top" background="images/bg_top.jpg" >
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top" colspan="3"><img src="images/Head.gif" width="100%"></td> 				
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td valign="top">
			<table border="0" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr> 
								<td background="images/bg_person.jpg">
									<table width="100%" border="0" cellspacing="0" cellpadding="5">
										<tr> 
											<td width="25" align="right"><img src="images/icon_personal.gif" width="14" height="16"></td>
											<td class="text-small"><strong><%=userInfo.getUserName()%></strong></td>
											<td></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="200" valign="top" bgcolor="#CCE7FC" id="TdMemuRight">
						<table width="100%" height="80" border="0" cellspacing="0" cellpadding="0">		
							<tr id="Menu0">
								<td height="30" background="images/bg_menu1.jpg">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td width="30" align="center"><img src="images/icon_01.gif" width="22" height="22"></td>
											<td class="menu-left"><a href="#" class="menu-left">บริหารโปรแกรม</a></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="SubMenu0"> 
								<td  valign="top" bgcolor="#FFFFCC">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
															<% if("mnm01".equals(screenMap.get("9"))){ %>														
																<a href="<spring:url value="/management/role.do" htmlEscape="true" />" class="menu-sub" target="wa">กำหนดสิทธิ์ผู้ใช้งานระบบ</a>
															<% }else{ %>
																กำหนดสิทธิ์ผู้ใช้งานระบบ
															<% } %>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>									 
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">

																<a href="<spring:url value="/management/consultDocLock.do" htmlEscape="true" />" class="menu-sub" target="wa">จำกัดสิทธิ์หนังสือข้อหารือ</a>
															


														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>								 
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">

																<a href="<spring:url value="/management/masterWord.do" htmlEscape="true" />" class="menu-sub" target="wa">คำเหมือน/คำคล้าย</a>
															
															
															
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>
									</table>
								</td>
							</tr>
							<!-- ######Menu1###### -->
							<tr style="display: none"  id="Menu1"> 
								<td height="30" background="images/bg_menu1.jpg">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td width="30" align="center"><img src="images/icon_02.gif"></td>
											<td class="menu-left"><a href="#" class="menu-left">ข่าว/ประชาสัมพันธ์</a></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr style="display: none" id="SubMenu1"> 
								<td  valign="top" bgcolor="#FFFFCC">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
															<% if("new01".equals(screenMap.get("10"))){ %>
																<a href="<spring:url value="/news/news.do" htmlEscape="true" />" class="menu-sub" target="wa">ข่าว/ประชาสัมพันธ์</a>
															<% }else{ %>
																<font color="gray">ข่าว/ประชาสัมพันธ์</font> 
															<% } %>														
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>
									</table>
								</td>
							</tr>
							<!-- ######Menu2###### -->
							<tr style="display: none" id="Menu2"> 
								<td height="30" background="images/bg_menu1.jpg">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td width="30" align="center"><img src="images/icon_05.gif" ></td>
											<td class="menu-left"><a href="#" class="menu-left">ข้อมูลกฎหลัก</a></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr style="display: none" id="SubMenu2"> 
								<td  valign="top" bgcolor="#FFFFCC">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
															<% if("law05".equals(screenMap.get("15"))){ %>
																<a href="<spring:url value="/law/statue.do" htmlEscape="true" />" class="menu-sub" target="wa">เพิ่ม/ แก้ไข พระราชบัญญัติ</a>
															<% }else{ %>
																<font color="gray">เพิ่ม/ แก้ไข พระราชบัญญัติ</font> 
															<% } %>	
														</td>
													</tr>
												</table>
											</td>
										</tr> 
										<tr>
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">															
															<% if("law07".equals(screenMap.get("17"))){ %>
																<a href="<spring:url value="/law/articleGroup.do" htmlEscape="true" />" class="menu-sub" target="wa">เพิ่ม/ แก้ไข หมวด</a>
															<% }else{ %>
																<font color="gray">เพิ่ม/ แก้ไข หมวด</font> 
															<% } %>	
														</td>
													</tr>
												</table>
											</td>
										</tr> 
										<tr>
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
															<% if("law08".equals(screenMap.get("18"))){ %>
																<a href="<spring:url value="/law/articleSection.do" htmlEscape="true" />" class="menu-sub" target="wa">เพิ่ม/ แก้ไข ส่วน</a>
															<% }else{ %>
																<font color="gray">เพิ่ม/ แก้ไข ส่วนที่</font> 
															<% } %>	
														</td>
													</tr>
												</table>
											</td>
										</tr> 
										<tr>
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
															<% if("law06".equals(screenMap.get("16"))){ %>
																<a href="<spring:url value="/law/lawType.do" htmlEscape="true" />" class="menu-sub" target="wa">เพิ่ม/ แก้ไข ประเภทกฎหมาย</a>
															<% }else{ %>
																<font color="gray">เพิ่ม/ แก้ไข ประเภทกฎหมาย</font> 
															<% } %>	
														</td>
													</tr>
												</table>
											</td>
										</tr> 
									</table>
								</td>
							</tr>
							<!-- ######Menu3###### -->
							<tr style="display: none" id="Menu3"> 
								<td height="30" background="images/bg_menu1.jpg">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td width="30" align="center"><img src="images/icon_03.gif" ></td>
											<td class="menu-left"><a href="#" class="menu-left">กฎหมายสรรพสามิต</a></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr style="display: none" id="SubMenu3"> 
								<td  valign="top" bgcolor="#FFFFCC">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td> 
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub" align="left">มาตรากฎหมายสรรพสามิต</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 													
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 	
														<td width="40" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub"><a href="<spring:url value="/law/exArticleHeader.do" htmlEscape="true" />" class="menu-sub" target="wa">ข้อมูล พ.ร.บ./พ.ร.ก./พ.ร.ฎ.</a></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 													
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 	
														<td width="40" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub"><a href="<spring:url value="/law/article.do" htmlEscape="true" />" class="menu-sub" target="wa">ข้อมูลมาตรากฎหมายสรรพสามิต</a></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 													
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 	
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub"><a href="<spring:url value="/law/exArticleCompleted.do" htmlEscape="true" />" class="menu-sub" target="wa">พระราชบัญญัติฉบับสมบูรณ์</a></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub"><a href="<spring:url value="/law/law.do" htmlEscape="true" />" class="menu-sub" target="wa">ข้อมูลกฎหมาย</a></td>
													</tr>
												</table>
											</td>
										</tr> 
									</table>
								</td>
							</tr>
							<!-- ######menu4###### -->
							<tr style="display: none" id="Menu4"> 
								<td height="30" background="images/bg_menu1.jpg">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td width="30" align="center"><img src="images/icon_04.gif" ></td>
											<td class="menu-left"><a href="#" class="menu-left">ข้อมูลกฎหมายอื่นๆ</a></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr style="display: none" id="SubMenu4"> 
								<td  valign="top" bgcolor="#FFFFCC">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub"><a href="<spring:url value="/law/otherArticle.do" htmlEscape="true" />" class="menu-sub" target="wa">ข้อมูลมาตรากฎหมายอื่นๆ</a></td>
													</tr>
												</table>
											</td>
										</tr> 
										<tr>
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr>
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub"><a href="<spring:url value="/law/otherLaw.do" htmlEscape="true" />" class="menu-sub" target="wa">ข้อมูลกฎหมายอื่นๆ</a></td>
													</tr>
												</table>
											</td>
										</tr> 
										<tr>
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>
									</table>
								</td>
							</tr>
							<!-- ######menu5###### -->
							<tr style="display: none" id="Menu5"> 
								<td height="30" background="images/bg_menu1.jpg">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td width="30" align="center"><img src="images/icon_06.gif" ></td>
											<td class="menu-left"><a href="#" class="menu-left">ข้อมูลคำพิพากษา/คำวินิจฉัย</a></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr style="display: none" id="SubMenu5"> 
								<td  valign="top" bgcolor="#FFFFCC">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
															<% if("jus01".equals(screenMap.get("19"))){ %>
																<a href="<spring:url value="/judgement/constitutionalCourt.do" htmlEscape="true" />" class="menu-sub" target="wa">คำวินิจฉัยศาลรัฐธรรมนูญ</a>
															<% }else{ %>
																<font color="gray">คำวินิจฉัยศาลรัฐธรรมนูญ</font> 
															<% } %>			
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'">
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
															<% if("jus02".equals(screenMap.get("20"))){ %>
																<a href="<spring:url value="/judgement/justiceCourt.do" htmlEscape="true" />" class="menu-sub" target="wa">คำพิพากษาศาลยุติธรรม</a>
															<% }else{ %>
																<font color="gray">คำพิพากษาศาลยุติธรรม</font> 
															<% } %>			
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'">
											<table width="100%" border="0" cellspacing="0" cellpadding="2">
												<tr> 
													<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
													<td class="menu-sub">
														<% if("jus03".equals(screenMap.get("21"))){ %>
															<a href="<spring:url value="/judgement/administrativeCourt.do" htmlEscape="true" />" class="menu-sub" target="wa">คำพิพากษาศาลปกครอง</a>
														<% }else{ %>
															<font color="gray">คำพิพากษาศาลปกครอง</font> 
														<% } %>			
													</td>
												</tr>
											</table>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'">
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
															<% if("jus04".equals(screenMap.get("22"))){ %>
																<a href="<spring:url value="/judgement/otherCourt.do" htmlEscape="true" />" class="menu-sub" target="wa">ความเห็นของหน่วยงานอื่นๆ</a>
															<% }else{ %>
																<font color="gray">ความเห็นของหน่วยงานอื่นๆ</font> 
															<% } %>			
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>
									</table>
								</td>
							</tr>
							<!-- ######menu6###### -->
							<tr style="display: none" id="Menu6"> 
								<td height="30" background="<%=request.getContextPath() %>/images/bg_menu1.jpg">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td width="30" align="center"><img src="<%=request.getContextPath() %>/images/icon_07.gif" ></td>
											<td class="menu-left"><a href="#" class="menu-left" >งานหนังสือตอบข้อหารือ</a></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr style="display: none" id="SubMenu6"> 
								<td  valign="top" bgcolor="#FFFFCC">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="<%=request.getContextPath() %>/images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
															<% if("csd01".equals(screenMap.get("1"))){ %>
																<a href="<spring:url value="/consult_doc/consultDocNew.do" htmlEscape="true" />" class="menu-sub" target="wa">บันทึกหนังสือข้อหารือ</a>
															<% }else{ %>
																<font color="gray">บันทึกหนังสือข้อหารือ</font> 
															<% } %>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="<%=request.getContextPath() %>/images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'">
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="<%=request.getContextPath() %>/images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
															<% if("csd02".equals(screenMap.get("2"))){ %>
																<a href="<spring:url value="/consult_doc/consultDocLaw.do" htmlEscape="true" />" class="menu-sub" target="wa">หนังสือข้อหารือ(ผ.อ.สำนัก)</a>
															<% }else{ %>
																<font color="gray">หนังสือข้อหารือ(ผ.อ.สำนัก)</font> 
															<% } %>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="<%=request.getContextPath() %>/images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'">
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="<%=request.getContextPath() %>/images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
															<% if("csd03".equals(screenMap.get("3"))){ %>
																<a href="<spring:url value="/consult_doc/consultDocSection.do" htmlEscape="true" />" class="menu-sub" target="wa">หนังสือข้อหารือ(ผ.อ.ส่วน)</a>
															<% }else{ %>
																<font color="gray">หนังสือข้อหารือ(ผ.อ.ส่วน)</font> 
															<% } %>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="<%=request.getContextPath() %>/images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'">
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="<%=request.getContextPath() %>/images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
															<% if("csd04".equals(screenMap.get("4"))){ %>
																<a href="<spring:url value="/consult_doc/consultDocLawyer.do" htmlEscape="true" />" class="menu-sub" target="wa">หนังสือข้อหารือ(นิติกร)</a>
															<% }else{ %>
																<font color="gray">หนังสือข้อหารือ(นิติกร)</font> 
															<% } %>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="<%=request.getContextPath() %>/images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'">
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="<%=request.getContextPath() %>/images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
															<% if("csd05".equals(screenMap.get("5"))){ %>
																<a href="<spring:url value="/consult_doc/consultDocProfessional.do" htmlEscape="true" />" class="menu-sub" target="wa">หนังสือข้อหารือ(ผู้เชี่ยวชาญ)</a>
															<% }else{ %>
																<font color="gray">หนังสือข้อหารือ(ผู้เชี่ยวชาญ)</font> 
															<% } %>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="<%=request.getContextPath() %>/images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'">
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="<%=request.getContextPath() %>/images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
															<% if("csd08".equals(screenMap.get("8"))){ %>
																<a href="<spring:url value="/consult_doc/consultDocAnswer.do" htmlEscape="true" />" class="menu-sub" target="wa">ตอบหนังสือข้อหารือ</a>
															<% }else{ %>
																<font color="gray">ตอบหนังสือข้อหารือ</font> 
															<% } %>
														</td>	
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="<%=request.getContextPath() %>/images/xpic.gif" width="1" height="1"></td>
										</tr>
									</table>
								</td>
							</tr>
							<!-- ######menu7###### -->
							<tr style="display: none"  id="Menu7"> 
								<td height="30" background="images/bg_menu1.jpg">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td width="30" align="center"><img src="images/icon_08.gif"></td>
											<td class="menu-left"><a href="#" class="menu-left">ระบบข้อหารือเผยแพร่</a></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr style="display: none" id="SubMenu7"> 
								<td  valign="top" bgcolor="#FFFFCC">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
															<% if("dis01".equals(screenMap.get("23"))){ %>
																<a href="<spring:url value="/consult_publish/consultPublish.do" htmlEscape="true" />" class="menu-sub" target="wa">ข้อหารือเผยแพร่</a>
															<% }else{ %>
																<font color="gray">ข้อหารือเผยแพร่</font> 
															<% } %>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="images/xpic.gif" width="1" height="1"></td>
										</tr>
									</table>
								</td>
							</tr>
							<!-- ######end menu###### -->
							<!-- ######menu8###### -->
							<tr style="display: none"  id="Menu8"> 
								<td height="30" background="images/bg_menu1.jpg">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td width="30" align="center"><img src="images/icon_09.gif"></td>
											<td class="menu-left"><a href="#" class="menu-left">รายงานและสถิติ</a></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr style="display: none" id="SubMenu8"> 
								<td  valign="top" bgcolor="#FFFFCC">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'"> 
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">

																<a href="<spring:url value="/report/exArticleCompletedReport.do" htmlEscape="true" />" class="menu-sub" target="wa">รายงานเรื่องพระราชบัญญัติฉบับสมบูรณ์</a>
															


														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="<%=request.getContextPath() %>/images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'">
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="<%=request.getContextPath() %>/images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
														
																<a href="<spring:url value="/report/articleReport.do" htmlEscape="true" />" class="menu-sub" target="wa">รายงานเรื่องพระราชบัญญัติฉบับแก้ไข</a>
														</td>	
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="<%=request.getContextPath() %>/images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'">
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="<%=request.getContextPath() %>/images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
														
																<a href="<spring:url value="/report/lawReport.do" htmlEscape="true" />" class="menu-sub" target="wa">รายงานเรื่องข้อมูลกฎหมาย</a>
														</td>	
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="<%=request.getContextPath() %>/images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'">
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="<%=request.getContextPath() %>/images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
														
																<a href="<spring:url value="/report/consultDocReport.do" htmlEscape="true" />" class="menu-sub" target="wa">รายงานเรื่องงานตอบหนังสือข้อหารือ</a>
														</td>	
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="<%=request.getContextPath() %>/images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'">
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="<%=request.getContextPath() %>/images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
														
																<a href="<spring:url value="/report/loginLogReport.do" htmlEscape="true" />" class="menu-sub" target="wa">สถิติการปฏิบัติงาน</a>
														</td>	
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="<%=request.getContextPath() %>/images/xpic.gif" width="1" height="1"></td>
										</tr>
										<tr> 
											<td onMouseOut="this.bgColor='#FFFFCC'" onMouseOver="this.bgColor='#FDE08E'">
												<table width="100%" border="0" cellspacing="0" cellpadding="2">
													<tr> 
														<td width="20" align="right"><img src="<%=request.getContextPath() %>/images/icon_arrow_2.gif" width="9" height="9"></td>
														<td class="menu-sub">
														
																<a href="<spring:url value="/report/loginLogDailyReport.do" htmlEscape="true" />" class="menu-sub" target="wa">สถิติการปฏิบัติงานประจำวัน</a>
														</td>	
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td bgcolor="#FDE08E"><img src="<%=request.getContextPath() %>/images/xpic.gif" width="1" height="1"></td>
										</tr>
									</table>
								</td>
							</tr>
							<!-- ######end menu###### -->
						</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">							
							<tr>
								<td valign="bottom" bgcolor="#FFFFCC">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td  id="TdMenu0" height="30" background="images/bg_menu2.jpg" onMouseOver="this.className='Highlight'" onMouseOut="this.className='Highlight'">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr> 
														<td width="30" align="center"><img src="images/icon_01.gif" width="22" height="22"></td>
														<td width="169" class="menu-left"><a onClick="MenuChange('0')" href="#" class="menu-left">บริหารโปรแกรม</a></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td  id="TdMenu1" height="30" background="images/bg_menu1.jpg" onMouseOver="this.className='Highlight'" onMouseOut="this.className='Normal'">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr> 
														<td width="30" align="center"><img src="images/icon_02.gif"></td>
														<td width="169" class="menu-left"><a onClick="MenuChange('1')" href="#" class="menu-left">ข่าว/ประชาสัมพันธ์</a></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td  id="TdMenu2" height="30" background="images/bg_menu1.jpg" onMouseOver="this.className='Highlight'" onMouseOut="this.className='Normal'">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr> 
														<td width="30" align="center"><img src="images/icon_05.gif"></td>
														<td width="169" class="menu-left"><a onClick="MenuChange('2')" href="#" class="menu-left">ข้อมูลหลัก</a></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td  id="TdMenu3" height="30" background="images/bg_menu1.jpg" onMouseOver="this.className='Highlight'" onMouseOut="this.className='Normal'">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr> 
														<td width="30" align="center"><img src="images/icon_03.gif"></td>
														<td width="169" class="menu-left"><a onClick="MenuChange('3')" href="#" class="menu-left">ข้อมูลกฏหมายสรรพสามิต</a></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td  id="TdMenu4" height="30" background="images/bg_menu1.jpg" onMouseOver="this.className='Highlight'" onMouseOut="this.className='Normal'">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr> 
														<td width="30" align="center"><img src="images/icon_04.gif"></td>
														<td width="169" class="menu-left"><a onClick="MenuChange('4')" href="#" class="menu-left">ข้อมูลกฏหมายอื่นๆ</a></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td id="TdMenu5" height="30" background="images/bg_menu1.jpg" onMouseOver="this.className='Highlight'" onMouseOut="this.className='Normal'">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr> 
														<td width="30" align="center"><img src="images/icon_06.gif"></td>
														<td width="169" class="menu-left"><a onClick="MenuChange('5')" href="#" class="menu-left">ข้อมูลคำพิพากษา/คำวินิจฉัย</a></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr> 
											<td  id="TdMenu6" height="30" background="images/bg_menu1.jpg"  onmouseover="this.className='Highlight'" onMouseOut="this.className='Normal'">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr> 
														<td width="30" align="center"><img src="images/icon_07.gif"></td>
														<td width="169" class="menu-left"><a onClick="MenuChange('6')" href="#" class="menu-left">งานหนังสือตอบข้อหารือ</a></td>
													</tr>
												</table>
											</td>
										</tr>		
										<tr> 
											<td  id="TdMenu7" height="30" background="images/bg_menu1.jpg"  onmouseover="this.className='Highlight'" onMouseOut="this.className='Normal'">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr> 
														<td width="30" align="center"><img src="images/icon_08.gif"></td>
														<td width="169" class="menu-left"><a onClick="MenuChange('7')" href="#" class="menu-left">ระบบข้อหารือเผยแพร่</a></td>
													</tr>
												</table>
											</td>
										</tr>		
										<tr> 
											<td  id="TdMenu8" height="30" background="images/bg_menu1.jpg"  onmouseover="this.className='Highlight'" onMouseOut="this.className='Normal'">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr> 
														<td width="30" align="center"><img src="images/icon_09.gif"></td>
														<td width="169" class="menu-left"><a onClick="MenuChange('8')" href="#" class="menu-left">รายงานและสถิติ</a></td>
													</tr>
												</table>
											</td>
										</tr>	
										<tr> 
											<td  id="TdMenu9" height="30" background="images/bg_menu1.jpg"  onmouseover="this.className='Highlight'" onMouseOut="this.className='Normal'">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr> 
														<td width="30" align="center"><img src="images/icon_10.gif"></td>
														<td width="169" class="menu-left"><a onClick="submitPage()" href="#" class="menu-left">ออกจากระบบ</a></td>
													</tr>
												</table>
											</td>
										</tr>		
									</table>
								</td>
							</tr>
						</table>
					</td>
					<td align="center" width="8"><a onClick="change('TdMemuRight')" href="#"><img src="images/arrow_ver.jpg" width="7" border="0"></a></td>
					<td valign="top">
						<iframe id="glu" name="wa" width="100%" onload="resize_iframe()" frameborder=0 MARGINWIDTH=0 MARGINHEIGHT=0 src="<spring:url value="/iFrame.do" htmlEscape="true" />" ></iframe>
					</td>
				</tr>
			</table> 
		</td>
	</tr>
</table>
</form>
</body>
</html>

    