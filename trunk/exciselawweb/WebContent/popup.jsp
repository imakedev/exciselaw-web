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
$(document).ready(function() {  
	 
	 var str="<a name=\"xx\"><a>aaa<br/><span id=\"xx\">aaa chatchai2</span><br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		"	aaa<br/>"+
		
	"detail.jspdetail.jspdetail.jspdetail.jspdetail.<a href=\"#xx\" class=\"scroll\">jspdetail.jsp</a>"
	  $("#bscKPIDetail").html(str);
	
		$(".scroll").click(function(event){
			//prevent the default action for the click event
			event.preventDefault();

			//get the full url - like mysitecom/index.htm#home
			var full_url = this.href;

			//split the url by # and get the anchor target name - home in mysitecom/index.htm#home
			var parts = full_url.split("#");
			var trgt = parts[1];

			//get the top offset of the target anchor
			var target_offset = $("#"+trgt).offset();
			var target_top = target_offset.top;
		 //   alert(target_top);
			//goto that anchor by setting the body scroll top to anchor top
			$('html, body').animate({scrollTop:target_top}, 500);
			});
});

</script>
</head>
<body> 
<table>
		<tr>
		    <td><a href="1">mastra 1</a>,<a href="1">mastra 2</a>,<a href="1">mastra 3</a>,<a href="1">mastra 4</a>,<a href="1">mastra 5</a></td>
		 </tr>
		<tr>
		    <td>
		    	<div id="bscKPIDetail">
		    	<a name=xx><a>aaa<br/><span id="xx">aaa xxxxxx</span><br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		aaa<br/>
		
	detail.jspdetail.jspdetail.jspdetail.jspdetail.<a href="#xx" class=scroll>jspdetail.jsp</a></div>
		    </td>
		</tr>
</table> 
</body>
</html>

