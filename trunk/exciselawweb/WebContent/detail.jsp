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
function popupwnd(url, toolbar, menubar, locationbar, resize, scrollbars, statusbar, left, top, width, height)
{
   var popupwindow = this.open(url, '', 'toolbar=' + toolbar + ',menubar=' + menubar + ',location=' + locationbar + ',scrollbars=' + scrollbars + ',resizable=' + resize + ',status=' + statusbar + ',left=' + left + ',top=' + top + ',width=' + width + ',height=' + height);
}
function callAjax(_id){
	alert(_id)
	$.facebox.settings.closeImage=_path+'/images/closelabel.png'; 
	$("#bscKPIDetail").html("<a name=\"xx\"><a>aaa<br/><span id=\"xx\">aaa</span><br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>aaa<br/>"+
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
		
	"detail.jspdetail.jspdetail.jspdetail.jspdetail.<a href=\"#xx\" class=\"scroll\">jspdetail.jsp</a>"); 
	
	jQuery.facebox({ div:'#bscKPIDetail'});
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
	    alert(target_top);
		//goto that anchor by setting the body scroll top to anchor top
		$('html, body').animate({scrollTop:target_top}, 500);
		});
}
$(document).ready(function() { 
	
	 $('.exciseLawLink').each(function (){
		 	  $(this).css("cursor", "pointer");
			  $(this).click(function() {
				  popupwnd('popup.jsp?id='+$(this).attr("id")+'','no','no','no','yes','yes','no','300','70','800','440'); 
				   
				 // alert('Handler for .click() called.');
			  });
			
	 });
	 
});
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
