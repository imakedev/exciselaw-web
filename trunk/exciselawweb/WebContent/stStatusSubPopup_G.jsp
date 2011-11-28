<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.6.2.min.js"></script>   
<script type="text/javascript"
        src='<%=request.getContextPath() %>/dwr/interface/LinkExciseLawAjax.js'> 
</script>
<script type="text/javascript"
        src='<%=request.getContextPath() %>/dwr/engine.js'> 
</script>
<script type="text/javascript"
        src='<%=request.getContextPath() %>/dwr/util.js'>
</script> 
<script>
var _path='<%=request.getContextPath() %>';
var id='<%=request.getParameter("id") %>';
</script>
<script type="text/javascript"
    src='<%=request.getContextPath() %>/js/aoe.js'>
</script> 
<script>
$(document).ready(function() {   
	naviArray=[];
	var naviObj={id:id+"",name:"Home",display:"<span id=\""+id+"\" onclick=\"init()\" style=\"cursor: pointer;\">Home</span>"};
	naviArray.push(naviObj);
	init();
});
</script>
</head>
<body> 
<table>
		<tr>
		    <td><span id="navigatorLinkElement"></span></td>
		 </tr>
		<tr>
		    <td>
		    	<div id="contentLinkElement">
		    	</div>
		    </td>
		</tr>
</table> 
</body>
</html>

