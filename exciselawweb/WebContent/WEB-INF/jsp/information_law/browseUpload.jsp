<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%--
StringControl myStrControl=new StringControl();

    	String testType=myStrControl.gNullToBlank(request.getParameter("TYPE"));

    	--%>
<script language="javascript">

function Attach() 
{   
		var pathFile=document.form1.file.value;
		var type = document.getElementById("TYPE").value;
		alert(pathFile);
		if(type == "LAW")
		{
			document.form1.action="testupload2.jsp?filePATH="+ pathFile + "&File_Name="+document.form1.FileNameAdd.value+"&ID="+document.form1.LEGAL_ID.value+"&ID2="+document.form1.LEGAL_TYPE_ID.value+"&TYPE=LAW";
		}
		else if(type == "LAWOTHER")
		{
			document.form1.action="testupload2.jsp?filePATH="+ pathFile + "&File_Name="+document.form1.FileNameAdd.value+"&ID="+document.form1.LEGAL_OTHER_ID.value+"&TYPE=LAW";
		}
		else if(type == "NEWS")
		{
			document.form1.action="testupload2.jsp?filePATH="+ pathFile + "&File_Name="+document.form1.FileNameAdd.value+"&ID="+document.form1.NEWS_ID.value+"&TYPE=NEWS";
		}
		else if(type == "COURT")
		{
			//document.form1.action="testupload2.jsp?filePATH="+ pathFile + "&File_Name="+document.form1.FileNameAdd.value+"&ID="+document.form1.COURT_ID.value+"&TYPE=COURT";
			document.form1.action="testupload2.jsp?filePATH="+ pathFile + "&File_Name="+document.form1.FileNameAdd.value+"&ID="+document.form1.COURT_ID.value+"&TYPE=COURT&COURT_TYPE=COURT1";
		}
		
		document.form1.submit();
		return true;

}
</script>
<title>Insert title here</title>
<link href="css/sura.css" rel="stylesheet" type="text/css">
</head>
<body>

<form name="form1" method="post" enctype="multipart/form-data" >
<table id="t5" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		 <td align="right" valign="baseline">เอกสารแนบท้าย&nbsp;</td>
		 <td><input type="file" name="file" size="40"/> &nbsp; </td>
	</tr>
	<tr>
         <td><div align="right">ชื่อเอกสาร&nbsp;</div></td>
		<td align= "left" class="text">
	   		<input type="text" name="FileNameAdd" size="40" />
        </td>     
    </tr>
    <tr>
    <%
    	
    %>
    </tr>
    <tr>
    	<td>
    		 
    	</td>
    	<td align="left">
    		<input type="Button" name="Upload" value="แนบไฟล์" onclick="JavaScript:Attach()" />
    	</td>
    </tr>
    <tr>
    	<td>
    	<%--
    	out.println("testType>>"+testType);
    	
    		if(testType.trim().equals("LAW"))
    		{
    	--%>
	    	<input type="hidden" name="TYPE" id="TYPE" value="<%--=testType --%>"/>
	    	<input type="hidden" name="LEGAL_ID" value="<%--=request.getParameter("LEGAL_ID") --%>"/>
	    	<input type="hidden" name="LEGAL_TYPE_ID" value="<%--=request.getParameter("LEGAL_TYPE_ID") --%>"/>
    	<%--
    		}
    		else if(testType.trim().equals("LAWOHTER"))
    		{
    	--%>
	    	<input type="hidden" name="TYPE" id="TYPE"  value="<%--=testType --%>"/>
	    	<input type="hidden" name="LEGAL_OTHER_ID" value="<%--=request.getParameter("LEGAL_OTHER_ID") --%>"/>
    	<%--
    		}
    		else if(testType.equals("NEWS"))
    		{
    	--%>
    		<input type="hidden" name="TYPE" id="TYPE"  value="<%--=testType --%>"/>
    	    <input type="hidden" name="NEWS_ID" value="<%--=request.getParameter("NEWS_ID") --%>"/>
    	<%--
			}
    		else if(testType.trim().equals("COURT"))
    		{
    	--%>
    		<input type="hidden" name="TYPE" id="TYPE"  value="<%--=request.getParameter("TYPE") --%>"/>
    		<input type="hidden" name="COURT_ID" value="<%--=request.getParameter("COURT_ID") --%>"/>
	    	<input type="hidden" name="COURT_TYPE" value="<%--=request.getParameter("COURT_TYPE") --%>"/>	
	    <%--
    		}
	    --%>
    	</td>
    </tr>
</table>
</form>           
</body>
</html>