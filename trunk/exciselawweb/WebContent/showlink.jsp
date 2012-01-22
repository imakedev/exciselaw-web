<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/redmond/jquery-ui-1.8.16.custom.css" type="text/css">
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/js/jquery-ui-1.8.16.custom.min.js"></script>

 
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/interface/LinkExciseLawAjax.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/util.js"></script> 
<script>

var _path='<%=request.getContextPath() %>';
var id='<%=request.getParameter("id") %>';
var table='<%=request.getParameter("table") %>';
var naviArray=[];
var haveLink=false;
function genOnClickElement(){
	/*
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
	*/
	    $('.exciseLawLink').each(function (){
		 	  $(this).css("cursor", "pointer");
			  $(this).click(function() {
				  displayLawLink($(this).attr("id"));
			  }); 
	    });
}
function render(linkId,key,table_name,indexLink,mode){ 
	//alert(table_name+",key="+key+",indexLink="+indexLink)
	if(table_name=='TS_LAW'){
		LinkExciseLawAjax.getTsLaw(parseInt(key),{
			callback:function(data_MsStatute){
				if(data_MsStatute!=null){
				    $("#tabs-"+linkId).html(data_MsStatute.lawDetail);
				    $('#tabs').tabs('select', indexLink);
				   if(mode!='one')
					   $( "#dialog_link" ).dialog( "close" );
				}
				genOnClickElement();
			}
		}); 
	}else if(table_name=='TS_ARTICLE'){
		LinkExciseLawAjax.getTsArticle(parseInt(key),{ 
			callback:function(data_MsStatute){
				//alert(data_MsStatute.articleDetail)
				if(data_MsStatute!=null){
					$("#tabs-"+linkId).html(data_MsStatute.articleDetail);
					$('#tabs').tabs('select', indexLink);
					 if(mode!='one')
						   $( "#dialog_link" ).dialog( "close" );
				}
				genOnClickElement();
			}
		});
	}else if(table_name=='TS_EX_ARTICLE_COMPLETED'){
		LinkExciseLawAjax.getTsExArticleCompleted(parseInt(key),{
			callback:function(data_MsStatute){
				if(data_MsStatute!=null){
					$("#tabs-"+linkId).html(data_MsStatute.articleCompletedDetail);
					$('#tabs').tabs('select', indexLink);
					 if(mode!='one')
						   $( "#dialog_link" ).dialog( "close" );
				}
				genOnClickElement();
			}
		});
	}
	
}
function displayLawLink(linkId){ 
	haveLink=false;
	var indexLink=0;
	for(var i=0;i<naviArray.length;i++){
		if(naviArray[i].name!='Home' && naviArray[i].id==linkId){
			haveLink=true;
			indexLink++;
			break;
		}
	 }
  if(haveLink){
		$('#tabs').tabs('select', indexLink); 
  }else{ 
	indexLink++;
	LinkExciseLawAjax.getRelMapById(linkId,{
		callback:function(data_RelKeyMap){
			if(data_RelKeyMap!=null){
				if(!haveLink){
					addTab(linkId,data_RelKeyMap.rmName);
				}
				LinkExciseLawAjax.listExistingLink(linkId,{
					 callback:function(data){ 
						 //alert(data.length)
						 if(data!=null && data.length>0){
							 if(data.length==1){
								 render(linkId,data[0].trKey,data[0].trTableName,indexLink,"one");
							 }else{
								 // show dialog
								 var str="<table style=\"border:1px solid #ccc;\"><tr><td width=\"390\">ลิ้งค์ที่จับคู่ไว้</td></tr>";
								   for(var i=0;i<data.length;i++){
									   str=str+"<tr><td><span onclick=\"render('"+linkId+"','"+data[i].trKey+"','"+data[i].trTableName+"',"+indexLink+",'multi')\" style=\"cursor: pointer;color: red;\">"+(i+1)+". "+data[i].trTitle+"</span></td></tr>";											   
								   } 
								   str=str+"</table>";
								   $("#select_link").html(str);
								   $( "#dialog_link" ).dialog({
										modal: true,
										show: 'clip' , 
										buttons: {
											Ok: function() {
												$( this ).dialog( "close" );
											}
										}
									}); 
							 } 
						 } 
					} 
				});
			
			if(!haveLink) 				
				naviArray.push({id:linkId+"",name:data_RelKeyMap.rmName,display:"<span id=\""+linkId+"\" class=\"exciseLawLink\">"+data_RelKeyMap.rmName+"</span>"});			
			if(naviArray.length>0){
				var navi="";
				var navi_size=naviArray.length; 
				for(var i=0;i<navi_size;i++){ 
					navi=navi+naviArray[i].display+((i==(navi_size-1))?"":",");
				}
				//$("#navigatorLinkElement").html(navi);
				genOnClickElement();
			 }
		    }
		  }
		});
    }	 
}
function init(){	
	if(table!=null && table=='ts_law'){
		LinkExciseLawAjax.getTsLaw(id,{
		    callback:function(data){
		        if(data!=null)
	  			    $("#tabs-0").html(data.lawDetail);
		        genOnClickElement();
	        }
        });
	}else if(table!=null && table=='group'){
		LinkExciseLawAjax.getTsArticleList(id,{
		    callback:function(data){
		    	var str="";
		        if(data!=null && data.length>0){
		        	 str="<table width=\"100%\">";
		        	for(var i=0;i<data.length;i++){
		                str=str+"<tr><td><strong>"+data[i].articleNumber+"</strong></td></tr>";		
		                str=str+"<tr><td>"+data[i].articleDetail+"</td></tr>";
		        	}
		        	str=str+"</table>";
		        }
	  			$("#tabs-0").html(str);
		        genOnClickElement();
	        }
        });
	}else if(table!=null && table=='ts_article'){
		//default look up table ts_article
		LinkExciseLawAjax.getTsArticle(id,{
			callback:function(data){
				    if(data!=null)
			  			$("#tabs-0").html(data.articleDetail);
				    genOnClickElement();
			}
		});
	}
}
function addTab(id,tab_title) {
	$( "#tabs").tabs( "add", "#tabs-" + id, tab_title );
} 
$(document).ready(function() {   
	naviArray=[];
	var naviObj={id:id+"",name:"Home",display:"<span id=\""+id+"\" onclick=\"init()\" style=\"cursor: pointer;\">Home</span>"};
	naviArray.push(naviObj);
	init();
	 $("input:button").button();  
		var $tabs = $( "#tabs").tabs({
			tabTemplate:"<li><a href='\#{href}'>\#{label}</a></li>" ,
			remove: function( event, ui ) {
				//http://localhost:8080/Test/tabs.jsp#tabs-2 
			   //alert("remove="+ui.tab+", ui.index ="+ ui.index  );
			}
		});  
});
</script>
<style>
.ui-widget {
    font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;
    font-size: 13px;
}
</style>
</head>
<body> 
<div id="dialog_link" title="เลือกลิงค์" style="display: none;">
   <div id="select_link"></div>
</div> 
<table width="100%">
		<tr class="detail-right">
		    <td></td>
		</tr>
		<tr>
		    <td><img src="images/Untitled-3_02.jpg" width="100%" ></img></td>
		</tr>
		<tr>
		    <td class="detail-right" width="100%"> 
				<div id="tabs">
					<ul>
						 <li><a href="#tabs-0">Home</a></li> 
					</ul>
					<div id="tabs-0">
					</div>
				</div>
		    </td>
		</tr>
</table> 
</body>
</html>