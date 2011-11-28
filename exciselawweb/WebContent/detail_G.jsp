<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.6.2.min.js"></script>  
<!-----------------   Lib for Facebox       ------------------------->
<link type="text/css" href="<%=request.getContextPath()%>/css/jquery.facebox.css" media="screen" rel="stylesheet"/> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/facebox.js"></script>
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
function callAjax(b){alert(b);$.facebox.settings.closeImage=_path+"/images/closelabel.png";$("#bscKPIDetail").html('<a name="xx"><a>aaa<br/><span id="xx">aaa</span><br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>\taaa<br/>detail.jspdetail.jspdetail.jspdetail.jspdetail.<a href="#xx" class="scroll">jspdetail.jsp</a>');
jQuery.facebox({div:"#bscKPIDetail"});$(".scroll").click(function(a){a.preventDefault();a=this.href.split("#")[1];a=$("#"+a).offset().top;alert(a);$("html, body").animate({scrollTop:a},500)})}$(document).ready(function(){$(".exciseLawLink").each(function(){$(this).css("cursor","pointer");$(this).click(function(){callAjax($(this).attr("id"))})})});
</script>
</head>
<body> 
<table>
		<tr>
			<td>
				<p><span class="exciseLawLink" id="48" style="color:orange;">chatchai</span>
  				<span class="exciseLawLink" id="49" style="color:orange">pimtun</span>
   				aoe <span class="exciseLawLink" id="50" style="color:orange">aodd</span>
   				</p>
   			</td>
		</tr>
		<tr>
		    <td>
		    	<div id="bscKPIDetail" style="display:none;"></div>
		    </td>
		</tr>
</table> 
</body>
</html>
