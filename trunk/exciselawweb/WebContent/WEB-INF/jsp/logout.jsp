<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<title></title>
</head>
<body>
<%
out.println("<script language=JavaScript>"
		+ "alert('คุณ '+'"+request.getSession().getAttribute( "userId" )+"'+' ออกจากระบบเรียบร้อย');</script>");
request.getSession().removeAttribute("userId");
String sCriptJava="<script language=\"JavaScript\" type=\"text/JavaScript\">window.location='"+request.getContextPath()+"/welcome2.do';</script>";
out.println(sCriptJava);
%>
</body>
</html>