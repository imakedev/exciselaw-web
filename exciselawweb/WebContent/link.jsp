<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.6.2.min.js"></script>  
<script type="text/javascript" src='<%=request.getContextPath() %>/js/jquery.calculation.min.js'></script>
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
var localIndex=0;
var localElementArray=[];  
$(document).ready(function() {
	  // Handler for .ready() called.
		callAjax();
	});
function loadLink(_value,rkmKey){
	//alert(_obj.value)
	//linkTableElement
	var idSplit=_value.split("|");
	//alert(idSplit.length)
	if(idSplit.length>1){
		if(idSplit[1]=='MS_STATUTE'){
			// call ajax
			//alert("you select out page");
			 LinkExciseLawAjax.listMsStatute({
				 callback:function(data_MsStatute_FromServer){
					 //alert(data_MsStatute_FromServer)
					 var link_selectStr=""+
						"<select name=\"linkTableSelect\" style=\"border:1px solid #dcdcdc;background-color: #FFFFFF;\">"+
						"<option VALUE=\"0\">-- เลือกลิ้งค์ --</option>";
						for(var i=0;i<data_MsStatute_FromServer.length;i++){
							link_selectStr=link_selectStr+"<option value=\""+data_MsStatute_FromServer[i].statuteId+"\">"+data_MsStatute_FromServer[i].name+"</option>";
						}
						link_selectStr=link_selectStr+"</select>"; 
						$("#linkTableElement").html(link_selectStr);
						if(rkmKey!='0'){
							$("select[name=linkTableSelect]").val(rkmKey);
						}
				 }
			});
		}else if(idSplit[1]=='null'){
			//alert("you select in page")
			var link_selectStr=""+
			"<select name=\"linkTableSelect\" style=\"border:1px solid #dcdcdc;background-color: #FFFFFF;\">"+
			"<option VALUE=\"0\">-- เลือกลิ้งค์ --</option>";
			for(var i=0;i<localElementArray.length;i++){
				link_selectStr=link_selectStr+"<option value=\"local_"+localElementArray[i].id+"\">"+localElementArray[i].name+"</option>";
			}
			link_selectStr=link_selectStr+"</select>"; 
			$("#linkTableElement").html(link_selectStr);
		 
			/* if(rkmKey!='0'){
				$("select[name=linkTableSelect]").val(rkmKey);
			} */
			//$("#linkTableElement").html("");
		}
	}else{
		$("#linkTableElement").html("");
	}
	
}
function callAjax(){
	//alert("call Ajax");
	var textValue='aoe';//document.getElementById('aoee').value;
	//var textValue = this.getContentElement( 'tab1', 'input1' );
	  LinkExciseLawAjax.listRelTableMap({
		 callback:function(dataFromServer){
             if(dataFromServer!=null && dataFromServer.length>0){
				 //alert(dataFromServer)
				// document.getElementById('myDiv').innerHTML=dataFromServer;
				 var link_selectStr=""+
					"<select name=\"linkSelect\" style=\"border:1px solid #dcdcdc;background-color: #FFFFFF;\" onChange=\"loadLink(this.value,'0')\">"+
					"<option value=\"0\">-- เลือกชนิดลิ้งค์ --</option>";
					for(var i=0;i<dataFromServer.length;i++){
						link_selectStr=link_selectStr+"<option value=\""+dataFromServer[i].rtmId+"|"+dataFromServer[i].rtmTableName+"\">"+dataFromServer[i].rtmName+"</option>";
					}
					link_selectStr=link_selectStr+"</select>"; 
				 var editor = CKEDITOR.replace( 'editor1',
							{
								// Defines a simpler toolbar to be used in this sample.
								// Note that we have added out "MyButton" button here.
								toolbar : [ [ 'Source', '-', 'Bold', 'Italic', 'Underline', 'Strike','-','Link', '-', 'Unlink','-','Anchor' ,'-', 'Excise Link' ] ]
							//toolbar : [ [ 'Source', '-', 'Bold', 'Italic', 'Underline', 'Strike','-','Link', '-', 'Unlink' ] ]
								//toolbar : [ [ 'Source', '-', 'Bold', 'Italic', 'Underline', 'Strike','-', 'MyButton' ] ]
							});

						// Listen for the "pluginsLoaded" event, so we are sure that the
						// "dialog" plugin has been loaded and we are able to do our
						var getSelectedSpan = function( editor )
							{
								try
								{
									var selection = editor.getSelection();
									if ( selection.getType() == CKEDITOR.SELECTION_ELEMENT )
									{
										var selectedElement = selection.getSelectedElement();
										if ( selectedElement.is( 'span' ) )
											return selectedElement;
									}

									var range = selection.getRanges( true )[ 0 ];
									range.shrink( CKEDITOR.SHRINK_TEXT );
									var root = range.getCommonAncestor();
									return root.getAscendant( 'span', true );
								}
								catch( e ) { return null; }
							};	
				 editor.on( 'pluginsLoaded', function( ev )
							{
							 
					          /*
					 			if ( !CKEDITOR.dialog.exists( 'myMarkPointDialog' ) ){
					 				//CKEDITOR.dialog.add( 'myDialog', href );
									CKEDITOR.dialog.add( 'myMarkPointDialog', function( api )
											{
												// CKEDITOR.dialog.definition
												var dialogDefinition =
												{
													title : 'Mark Point Dialog',
													minWidth : 390,
													minHeight : 130,
													contents : [
														{
															id : 'tab1',
															label : 'Label',
															title : 'Title',
															expand : true,
															padding : 0,
															elements :
															[
																 
																{
																	type : 'select',
																	id : 'markPointTypeSelect',
																	label : 'เลือกการ Mark Point',
																	items : [ [ 'MarkPoint' ], [ 'UnMarkPoint' ] ],
																	'default' : 'MarkPoint',
																	onChange : function( api ) { 
																	}
																}
																
															]
														}
													],
													buttons : [ CKEDITOR.dialog.okButton, CKEDITOR.dialog.cancelButton ],
													onOk : function() { 
											            var editor1 = CKEDITOR.instances["editor1"]; 
													var selection = editor1.getSelection();
													var text = selection.getNative();
													var ranges = selection.getRanges(); 
													var type = selection.getType(); 
													var isSave=true;
													var element_Id="";
													if(type==CKEDITOR.SELECTION_TEXT){  
														var xx=CKEDITOR.dom.selection(editor1.document); 
														var element = selection.getStartElement();  
														if(element.is('span')){
															//alert("span id"+element.getId());
															element_Id=element.getId();
															isSave=false;
														}
													}  
													var data = editor1.getData(); 
															if ( ranges.length == 1  )
															{ 
																var newElement	; 
																var linkSelect = this.getContentElement( 'tab1', 'markPointTypeSelect' );
																var localObj;
																if(linkSelect.getValue()=='MarkPoint'){
																	
																	 if(isSave){
																		 localIndex++;  
																		newElement=CKEDITOR.dom.element.createFromHtml( '<span id="local_'+localIndex+'" class="local" style="color:yellow">'+text +'</span>' );
																		localObj={id:localIndex+"",name:"local_"+localIndex};
																		localElementArray.push(localObj);
																		//alert(localElementArray.length);
																	 }else{ // update
																		// localObj={id:localIndex,name:element_Id}; 
																		newElement=CKEDITOR.dom.element.createFromHtml( '<span id="'+element_Id+'" class="local" style="color:yellow">'+text +'</span>' );
																	 }
																}
																else{
																	newElement=CKEDITOR.dom.element.createFromHtml( text  );
																	var index=0;
																	var haveIndex=false;
																	for(var i=0;i<localElementArray.length;i++ ){ 
																		alert("id="+localElementArray[i].id)
																		alert("element_Id="+element_Id)
																	    if(("local_"+localElementArray[i].id)==element_Id){
																			index=i;
																			haveIndex=true;
																			break;
																		} 
																	}
																	if(haveIndex){
																		alert("delete index "+index)
																		localElementArray.splice(index, 1);
																	}
																}
																alert(localElementArray.length)	
																 ranges[0].deleteContents();
																 ranges[0].insertNode(newElement);
																 ranges[0].selectNodeContents( newElement ); 	
																
															}
													}
												};

												return dialogDefinition;
											} );
					   			}
					          */
								// If our custom dialog has not been registered, do that now.
								if ( !CKEDITOR.dialog.exists( 'myDialog' ) )
								{
									 
									//CKEDITOR.dialog.add( 'myDialog', href );
									CKEDITOR.dialog.add( 'myDialog', function( api )
											{
												// CKEDITOR.dialog.definition
												var dialogDefinition =
												{
													title : 'ลิ้งค์ Dialog',
													minWidth : 390,
													minHeight : 130,
													contents : [
														{
															id : 'tab1',
															label : 'Label',
															title : 'Title',
															expand : true,
															padding : 0,
															elements :
															[
																{
																	id:'linkType',
																	type : 'html',
																	html : link_selectStr
																},
																{
																	id:'linkTable',
																	type : 'html',
																	html : '<span id="linkTableElement"></span>'
																},
																{
																	type : 'select',
																	id : 'linkTypeSelect',
																	label : 'เลือกการทำลิ้งค์',
																	items : [ [ 'Link' ], [ 'UnLink' ] ],
																	'default' : 'Link',
																	onChange : function( api ) {
																	// this = CKEDITOR.ui.dialog.select
																	//alert( 'Current value: ' + this.getValue() );
																	}
																}
																
															]
														}
													],
													buttons : [ CKEDITOR.dialog.okButton, CKEDITOR.dialog.cancelButton ],
													onOk : function() {
														// "this" is now a CKEDITOR.dialog object.
														// Accessing dialog elements:
														//var textareaObj = this.getContentElement( 'tab1', 'textareaId' );
													//var textareaObj = this.getContentElement( 'tab1', 'input1' );
													//	alert( "You have entered: " + textareaObj.getValue() ); 
													//var aoe_html=	this.getContentElement( 'tab1', 'aoe_html' );
													//alert(aoe_html);
											            var editor1 = CKEDITOR.instances["editor1"]; 
													var selection = editor1.getSelection();
													var text = selection.getNative();
													var ranges = selection.getRanges(); 
													var type = selection.getType();
													//alert($("select[name=linkSelect]").val()+",aoe select");
													/*
													 * if(type==CKEDITOR.SELECTION_NONE) alert("no Select");
													 */
													//alert(type)
													//alert(text)
													//alert("xxx="+CKEDITOR.SELECTION_TEXT)
													var isSave=true;
													var element_Id="";
													if(type==CKEDITOR.SELECTION_TEXT){  
														var xx=CKEDITOR.dom.selection(editor1.document); 
														var element = selection.getStartElement(); 
														//alert(element);
														//alert(element.is('span'));
														if(element.is('span')){
															//alert("span id"+element.getId());
															element_Id=element.getId();
															isSave=false;
														}
													} 
													/*
													 * CKEDITOR.SELECTION_NONE (1): No selection. CKEDITOR.SELECTION_TEXT
													 * (2): Text is selected or collapsed selection.
													 * CKEDITOR.SELECTION_ELEMENT (3): A element selection.
													 * 
													 */
													 
													var data = editor1.getData(); 
															if ( ranges.length == 1  )
															{ 
																//var textNode = new CKEDITOR.dom.text( "<div>xxxxxxx</div>", editor1.document );
																var newElement	; 
																var linkSelect = this.getContentElement( 'tab1', 'linkTypeSelect' );
																if(linkSelect.getValue()=='Link'){
																	var idSplit=$("select[name=linkSelect]").val().split("|");
																	//alert(idSplit[0])
																	//if(idSplit.length>1){
																	var relTableMap={rtmId:idSplit[0]};
																    var relKeyMap={rkmKey:$("select[name=linkTableSelect]").val(),rkmName:text+"",relTableMap:relTableMap};
																	/*  alert("relKeyMap="+relKeyMap);
																	 alert("rtmId="+idSplit[0]);
																	 alert("rkmKey="+$("select[name=linkTableSelect]").val());
																	 alert("text="+relKeyMap.rkmName); */
																	 if(isSave){
																		LinkExciseLawAjax.saveRelKeyMap(relKeyMap,{
																			 callback:function(dataFrom_saveRelKeyMap){
																			  // alert("dataFrom_saveRelKeyMap="+dataFrom_saveRelKeyMap);
																			   //alert("dataFrom_saveRelKeyMap length="+dataFrom_saveRelKeyMap.length);
																			     if(dataFrom_saveRelKeyMap!=null){
																	            	 newElement=CKEDITOR.dom.element.createFromHtml( '<span id=\"'+dataFrom_saveRelKeyMap+'\" style="color:orange" class="exciseLawLink">'+text+'</span>' );
																	            	 ranges[0].deleteContents();
																					 ranges[0].insertNode(newElement);
																					 ranges[0].selectNodeContents( newElement ); 	
																					// alert(dataFrom_saveRelKeyMap)
																	             }
																			 }
																		});
																	 }else{ // update
																		 //alert("update") 
																	     var relKeyMapUpdate={rkmId:element_Id,rkmKey:$("select[name=linkTableSelect]").val(),rkmName:text+"",relTableMap:relTableMap};
																		 LinkExciseLawAjax.updateRelKeyMap(relKeyMapUpdate,{
																			 callback:function(dataFrom_updateRelKeyMap){
																			  // alert("updateRelKeyMap="+updateRelKeyMap);
																			   //alert("updateRelKeyMap length="+updateRelKeyMap.length);
																			     if(dataFrom_updateRelKeyMap!=null){
																	            	 newElement=CKEDITOR.dom.element.createFromHtml( '<span id=\"'+element_Id+'\" style="color:orange" class="exciseLawLink">'+text+'</span>' );
																	            	 ranges[0].deleteContents();
																					 ranges[0].insertNode(newElement);
																					 ranges[0].selectNodeContents( newElement ); 	
																					// alert(dataFrom_saveRelKeyMap)
																	             }
																			 }
																		});
																	 }
																	//newElement=CKEDITOR.dom.element.createFromHtml( '<span id="aoe_12" style="color:orange">'+textareaObj.getValue() +'</span>' );
																}
																else{
																	//newElement=CKEDITOR.dom.element.createFromHtml( textareaObj.getValue()  );
																	 var relKeyMap={rkmId:element_Id};
																	LinkExciseLawAjax.deleteRelKeyMap(relKeyMap);
																	newElement=CKEDITOR.dom.element.createFromHtml( text );
																	ranges[0].deleteContents();
																	ranges[0].insertNode(newElement);
																	ranges[0].selectNodeContents( newElement ); 
																} 
																
															}
													}
												};

												return dialogDefinition;
											} );
								}

								// Register the command used to open the dialog.
								editor.addCommand( 'myDialogCmd',new CKEDITOR.dialogCommand( 'myDialog' ) );
							//	editor.addCommand( 'myMarkPointDialogCmd',new CKEDITOR.dialogCommand( 'myMarkPointDialog' ) );
								/*		
								{
								    		exec : function( editor )
								    		{
								    			new CKEDITOR.dialogCommand( 'myDialog' );
								    		}
								    	//	startDisabled : true     // Command is available in wysiwyg mode only.
								});
						        */
								//new CKEDITOR.dialogCommand( 'myDialog' ) );

								// Add the a custom toolbar buttons, which fires the above
								// command..
								editor.ui.addButton( 'Excise Link',
									{
										label : 'สร้างลิ้งค์',//'My Dialog',
										command : 'myDialogCmd'
									} );
								/*
								editor.ui.addButton( 'Excise Mark Point',
										{
											label : 'Mark Point',//'My Dialog',
											command : 'myMarkPointDialogCmd'
										} );
								*/
							}); 
			     editor.on( 'dialogShow', function( evt ){			    	
   		    	     var selectionShow = editor.getSelection();
					 var element = selectionShow.getStartElement();  
						if(!element.is('span')){ 
							$("select[name=linkSelect]").val("0");
							loadLink("0","0");
						}else{
							var id = element.getId();
						 //	alert("id="+id.indexOf("local_"))
							if(id.indexOf("local_")!=-1){
								
							}else{
							//alert("id==>"+id)
								  LinkExciseLawAjax.getRelKeyMapById(id,{
										 callback:function(data_tRelKeyMap_FromServer){
										//  alert("data_tRelKeyMap_FromServer="+data_tRelKeyMap_FromServer);
										   if(data_tRelKeyMap_FromServer!=null){
								            	 var  rtmId= data_tRelKeyMap_FromServer.relTableMap.rtmId;
								            	 var rtmTableName =  data_tRelKeyMap_FromServer.relTableMap.rtmTableName;
								            	 var rkmKey=data_tRelKeyMap_FromServer.rkmKey;
								            	// alert("rtmId="+rtmId+",rkmKey="+rkmKey);
								            	 var linkSelectId=rtmId+((rtmId!=1)?"|"+rtmTableName:"");
								            	 $("select[name=linkSelect]").val(linkSelectId);
								            	 loadLink(linkSelectId,rkmKey+"");
								            	/*  if(rtmId!=1){
								            		// alert("not link in page")
								            		$("select[name=linkTableSelect]").val(rkmKey);
								            	 } */
								             }
									}
								  });
							}
						}
			     });
				 editor.on( 'doubleclick', function( evt )
							{
								//alert("aoe doubleclick")
								var element =getSelectedSpan( editor ) || evt.data.element; 
								if ( !element.isReadOnly() )
									{
										if ( element.is( 'span' ) ){ 
							//alert("aaaa="+element.getId());
							//$("select[name=linkSelect]").val("0");
							//loadLink("0");
											var id = element.getId();
										/* 	if(id.indexOf("local_")!=-1){
												evt.data.dialog = 'myMarkPointDialog';
											}else{ */
												evt.data.dialog = 'myDialog';	 
										//	}
											
										}			 
									}
				    		}); 
             }
		 }
	});  
}
</script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>  
<style id="styles" type="text/css">

		.cke_button_myDialogCmd .cke_icon
		{
			display: none !important;
		}

		.cke_button_myDialogCmd .cke_label
		{
			display: inline !important;
		}
		/*
		.cke_button_myMarkPointDialogCmd .cke_icon
		{
			display: none !important;
		}

		.cke_button_myMarkPointDialogCmd .cke_label
		{
			display: inline !important;
		}
		*/

</style>
<script type="text/javascript">
	//<![CDATA[

// When opening a dialog, its "definition" is created for it, for
// each editor instance. The "dialogDefinition" event is then
// fired. We should use this event to make customizations to the
// definition of existing dialogs.
/*
CKEDITOR.on( 'dialogDefinition', function( ev )
	{
		// Take the dialog name and its definition from the event
		// data.
		var dialogName = ev.data.name;
		var dialogDefinition = ev.data.definition;

		// Check if the definition is from the dialog we're
		// interested on (the "Link" dialog).
		//alert(dialogName)
		if ( dialogName == 'link' )
		{
			// Get a reference to the "Link Info" tab.
			var infoTab = dialogDefinition.getContents( 'info' );

			// Add a text field to the "info" tab.
			infoTab.add( {
					type : 'text',
					label : 'My Custom Field',
					id : 'customField',
					'default' : 'Sample!',
					validate : function()
					{
 //alert(this.getValue());
						if ( /\d/.test( this.getValue() ) )
							return 'My Custom Field must not contain digits';
					}
				});

			// Remove the "Link Type" combo and the "Browser
			// Server" button from the "info" tab.
			infoTab.remove( 'linkType' );
			infoTab.remove( 'browse' );

			// Set the default value for the URL field.
			var urlField = infoTab.get( 'url' );
			urlField['default'] = 'www.example.com';

			// Remove the "Target" tab from the "Link" dialog.
			dialogDefinition.removeContents( 'target' );

			// Add a new tab to the "Link" dialog.
			dialogDefinition.addContents({
				id : 'customTab',
				label : 'My Tab',
				accessKey : 'M',
				elements : [
					{
						id : 'myField1',
						type : 'text',
						label : 'My Text Field'
					},
					{
						id : 'myField2',
						type : 'text',
						label : 'Another Text Field'
					}
				]
			});

			// Rewrite the 'onFocus' handler to always focus 'url' field.
			dialogDefinition.onFocus = function()
			{
				var urlField = this.getContentElement( 'info', 'url' );
				urlField.select();
			};
		}
		else if ( dialogName == 'myDialog' )
		{
			dialogDefinition.onFocus = function()
			{
			//	var aoe = this.getContentElement( 'tab1', 'input1' );
 			//	alert("xxx="+aoe.value)
			};
		}
	});
*/
	//]]>
	</script>
</head>
<body>
	<h1 class="samples">
		CKEditor Sample &mdash; Using CKEditor Dialog API
	</h1>
	<div class="description">
	</div>
	<textarea cols="80" id="editor1" name="editor1" rows="10"><span id="local_9" class="local">test</span>
	</textarea>
	<%--	
	&lt;p&gt;This is some &lt;strong&gt;sample text&lt;/strong&gt;. You are using 	 &lt;span id="aoe_12" &gt;xxx&lt;/span&gt; 
	 .&lt;/p&gt;
	
	 --%>
	<script type="text/javascript">
		//<![CDATA[
			// Replace the <textarea id="editor1"> with an CKEditor instance.
			
			
			// customizations.
			// aoe ajax
				/*
				
				*/
				
			
		//]]>
	</script>
	<div id="footer">
		<hr />
		<p>
			CKEditor - The text editor for the Internet - <a class="samples" href="http://ckeditor.com/">http://ckeditor.com</a>
			<input type="button" value="click" onclick="callAjax()"/>
		</p>
		<p id="copy">
			Copyright &copy; 2003-2011, <a class="samples" href="http://cksource.com/">CKSource</a> - Frederico
			Knabben. All rights reserved.
		</p>
	</div>
</body>
</html>
