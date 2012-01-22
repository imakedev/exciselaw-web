<%@ page contentType="text/html; charset=utf-8" %> 
<html>
<head>
<title></title>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css"></link>	
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/redmond/jquery-ui-1.8.16.custom.css" type="text/css">
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/js/jquery-ui-1.8.16.custom.min.js"></script>
<%--
<script type="text/javascript" language="javascript" src="/media/js/jquery.js"></script>
 --%>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/media/js/jquery.dataTables.js"></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/interface/LawAjax.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/interface/LinkExciseLawAjax.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script language="JavaScript" type="text/JavaScript"> 
var state = 'none'; 
var _path='<%=request.getContextPath()%>';
function popupwnd(url, toolbar, menubar, locationbar, resize, scrollbars, statusbar, left, top, width, height)
{
   var popupwindow = this.open(url, '', 'toolbar=' + toolbar + ',menubar=' + menubar + ',location=' + locationbar + ',scrollbars=' + scrollbars + ',resizable=' + resize + ',status=' + statusbar + ',left=' + left + ',top=' + top + ',width=' + width + ',height=' + height);
}
</script>

</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"> 
<table width="100%" border="0" cellpadding="0" cellspacing="2" class="detail" id="example">
	<thead>
		<tr id="lawTypeList">
	   		<td></td>
	   		<td><td>
			<td align="center"><img id="mainLaw" src="../images/main_c.png" onclick="getlawTypeList()"></td>
			<td align="left" class="detail-right">&nbsp;  </td>
			<td align="left" class="detail-right">&nbsp;  </td>  
		</tr>
	</thead>
	<tbody>  
		   	<tr id="lawTypeList ">
		   		<td>1</td>
		   		<td>type id</td>
				<td align="center"><img src="../images/closed.png" id="close${lawType.lawTypeId}" ></td>
				<td><span style="cursor: pointer, align: center;" title="ดูรายละเอียดข้อมูล"  onclick="popupwnd('<%=request.getContextPath() %>/showlink.jsp?id=1_2_1&table=group','no','no','no','yes','yes','no','300','70','800','440');"><img  border="0" src="../images/IE9-Logo.gif"/></span></td>
			</tr> 	
	</tbody>
</table> 
</body>
</html>