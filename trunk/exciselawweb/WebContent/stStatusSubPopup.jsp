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
var naviArray=[];
var haveLink=false;
function genOnClickElement(){
	 $("a[href^=#]").click(function(event){ 
				//prevent the default action for the click event
				event.preventDefault();
				//get the full url - like mysitecom/index.htm#home
				var full_url = this.href;
				//split the url by # and get the anchor target name - home in mysitecom/index.htm#home
				var parts = full_url.split("#");
				var trgt = parts[1];
				//get the top offset of the target anchor
				var target_offset =$("a[name="+trgt+"]").offset(); ; //$("#"+trgt).offset();
				var target_top = target_offset.top; 
				//goto that anchor by setting the body scroll top to anchor top
				$('html, body').animate({scrollTop:target_top}, 500);
				});
	    $('.exciseLawLink').each(function (){
		 	  $(this).css("cursor", "pointer");
			  $(this).click(function() {
				  displayLawLink($(this).attr("id"));
			  }); 
	    });
}
function displayLawLink(linkId){ 
	LinkExciseLawAjax.getRelMapById(linkId,{
		callback:function(data_RelKeyMap){
			if(data_RelKeyMap!=null){
				if(data_RelKeyMap.rmTable=='ST_MATR'){
					LinkExciseLawAjax.getStMatr(parseInt(data_RelKeyMap.rmKey),{
						callback:function(data_MsStatute){
							if(data_MsStatute!=null)
							$("#contentLinkElement").html(data_MsStatute.detail);
							genOnClickElement();
						}
					});
				}else if(data_RelKeyMap.rmTable=='ST_STATUTESUB'){
					LinkExciseLawAjax.getStStatutesub(parseInt(data_RelKeyMap.rmKey),{
						callback:function(data_MsStatute){
							if(data_MsStatute!=null)
							$("#contentLinkElement").html(data_MsStatute.detail);
							genOnClickElement();
						}
					});
				}
			haveLink=false;
			for(var i=0;i<naviArray.length;i++){
				if(naviArray[i].name!='Home' && naviArray[i].id==linkId){
					haveLink=true;
					break;
				}
			 }
			if(!haveLink) 				
				naviArray.push({id:linkId+"",name:data_RelKeyMap.rmName,display:"<span id=\""+linkId+"\" class=\"exciseLawLink\">"+data_RelKeyMap.rmName+"</span>"});			
			if(naviArray.length>0){
				var navi="";
				var navi_size=naviArray.length; 
				for(var i=0;i<navi_size;i++){ 
					navi=navi+naviArray[i].display+((i==(navi_size-1))?"":",");
				}
				$("#navigatorLinkElement").html(navi);
				genOnClickElement();
			 }
		    }
		  }
		});
}
function init(){	
	LinkExciseLawAjax.getStStatutesub(id,{
		callback:function(data){
			    if(data!=null)
		  			$("#contentLinkElement").html(data.detail);
			    genOnClickElement();
		}
	});
}
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

