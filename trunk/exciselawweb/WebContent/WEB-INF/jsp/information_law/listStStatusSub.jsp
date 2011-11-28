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
<style>
.tooltip{ position: absolute; top: 0; left: 0; z-index: 3; display: none; }
</style>
<script> 
function popupwnd(url, toolbar, menubar, locationbar, resize, scrollbars, statusbar, left, top, width, height)
{
   var popupwindow = this.open(url, '', 'toolbar=' + toolbar + ',menubar=' + menubar + ',location=' + locationbar + ',scrollbars=' + scrollbars + ',resizable=' + resize + ',status=' + statusbar + ',left=' + left + ',top=' + top + ',width=' + width + ',height=' + height);
}
$(document).ready(function() {	
	LinkExciseLawAjax.listStStatutesub({
		callback:function(data){
			if(data!=null && data.length>0){
				var str="";
				for(var i=0;i<data.length;i++){
					if(data[i].stStatutesubId==429){
						str=str+"<span class=\"exciseLawStstatuteSubLink\" id=\""+data[i].stStatutesubId+"\">"+data[i].article+"</span><br/>";
						break;
					}
				}
				$("#listStStatusElement").html(str);
				 $('.exciseLawStstatuteSubLink').each(function (){
				 	  $(this).css("cursor", "pointer");
					  $(this).click(function() {
						  popupwnd('stStatusSubPopup_G.jsp?id='+$(this).attr("id")+'','no','no','no','yes','yes','no','300','70','800','440'); 
					  }); 
				 });
			}
		}
	}); 
});
</script>
</head>
<body> 
<table> 
		<tr>
		    <td>
		    	<div id="listStStatusElement"></div>
		    </td>
		</tr>
</table> 
</body>
</html>
