<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=GBK"%>                                                                                                               
<%@ taglib uri="oms" prefix="oms"%>
<%@ taglib uri="oms-rights" prefix="oms_auth"%>																	     
<%@ taglib uri="extremecomponents" prefix="ec" %>
<%@ taglib uri="/WEB-INF/drilltag.tld" prefix="Drill"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" /> 
<html>
																		     
<%																				     
   		if(request.getAttribute("totalRows") != null) {                                       
   			String totalRows = (String)request.getAttribute("totalRows");    	      
   			request.setAttribute("totalRows",Integer.parseInt(totalRows));//一共多少记录  
   		}										      
   		if(request.getAttribute("totalRows")== null) {					      
   		    String totalRows ="0";							      
   		    request.setAttribute("totalRows",Integer.parseInt(totalRows));		      
   		}										      
    String contextPath = request.getContextPath();		
        String findflag="";
     findflag= request.getParameter("drillcommfindbz");		
												     
%>																				     
	<!-- include thre header file -->    
<head>	
<meta http-equiv="Content-Type" content="text/html; charset=GBK">											     
																		     
<title> </title> 																	     
<link href="<%=contextPath%>/css/AutoComplete.css" type="text/css" rel="stylesheet">           
<link rel="stylesheet" href="<%=contextPath%>/css/extremecomponents.css" type="text/css">      
<link href="<%=contextPath%>/css/table.css" rel="stylesheet" type="text/css" />										     
<link rel="stylesheet" type="text/css" media="all" href="<%=contextPath%>/css/calendar-blue.css"  />
<script language="JavaScript" type="text/JavaScript" src="<%=contextPath%>/js/report.js"></script>
<script language="javascript" type="text/javascript" src="<%=contextPath%>/js/jquery-latest.js"></script>
<script language="javascript" type="text/javascript" src="<%=contextPath%>/js/jquery.AutoComplete.js"></script>
<script src="<%=contextPath%>/js/browserSniffer.js" type="text/javascript" language="javascript"></script>
<script src="<%=contextPath%>/js/cfm.js" type="text/javascript" language="javascript"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=contextPath%>/js/drilljs/checkdata.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=contextPath%>/js/drilljs/message.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/calendar.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/calendar-zh.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/calendar-setup.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/common.js"></script>
<script language="javascript" src="<%=contextPath%>/js/drilljs/syscommon.js"></script>			       
<script language="javascript" src="<%=contextPath%>/js/drilljs/common.js"></script>
<script language="javascript" type="text/javascript">
    $(function(){
        $("#wellName").AutoComplete("<%=contextPath%>/drill/ajaxquerywellname.do",{seperator:";",autoTab:true,parameterName:"wellName",kwlength:1});
    });
</script>																			     
																				     
<script language="javascript">																     
																				     
										      
   	//新增											      
   		function addmodel(){								      
   												      
   			var form = document.forms[0];						      
add_action
   			form.submit();								      
   		}										      
   												      
   	//修改											      
   		function modifymodel(){								      
   			var pk = getSelRadioValue("radioitem");					      
   			var form = document.forms[0];
12345
   			var s= new Array();
   			s=pk.substr(0,pk.length-1).split(";");
   			if(pk==""||s.length>1){							      
   			    alert("请先选择一条记录");						      
   			    return;								      
   			}
   			proj.value = s[0];									      
modify_action
   			form.submit();								      
   		}										      
   												      
   	//删除											      
   												      
   		function deletemodel(){								      
   			var pk = getSelRadioValue("radioitem");					      
   			var form = document.forms[0];
12345
   			var s= new Array();
   			s=pk.substr(0,pk.length-1).split(";");
   			if(pk==""||s.length>1){							      
   			    alert("请先选择一条记录");						      
   			    return;								      
   			}
   			proj.value = s[0];									      
   			if(window.confirm("确定删除所选信息")) {				      
delete_action
   				form.submit();							      
   			}									      
   												      
   		}	
   		
   	//查看										      
   												      
   		function viewmodel(){								      
   			var pk = getSelRadioValue("radioitem");					      
   			var form = document.forms[0];
12345
   			var s= new Array();
   			s=pk.substr(0,pk.length-1).split(";");
   			if(pk==""||s.length>1){							      
   			    alert("请先选择一条记录");						      
   			    return;								      
   			}
   			proj.value = s[0];
view_action
   				form.submit();							      
   			}									      
   												      							      
   		function toBack(){
to_Back
   		}
   	//选择单选id，以后此方法统一放在js文件中						      
   												      
   		function getSelRadioValue(radioname){						      
   			var temp=document.getElementsByName(radioname);				      
   			var ret = "";							      
   			for (i=0;i<temp.length;i++){						      
   				if(temp[i].checked){						      
   					ret += temp[i].value+";";			      
   			    }									      
   			}									      
   			return ret;								      
   		}						
   	
   		function draw(){
   			var form = document.forms[0];	
draw_action
   			form.submit();	
   		}
   												      
   		function verify(){						      
   			var pk = getSelRadioValue("radioitem");					      
   			var form = document.forms[0];
12345
   			proj.value = pk;					
   			if(window.confirm("确定审核所选信息")) {
verify_action
   				form.submit();	
   			}
   		}										      
   		function verifyAll(){						      
   			var form = document.forms[0];			      
   			if(window.confirm("确定审核所有信息")) {
verifyAll_action
   				form.submit();	
   			}
   		}										      
</script>																			     
</head>																			     
																				     
		<body> 
<form name="checkForm" 
list_action

		    <table  border="0" cellpadding="0" cellspacing="0" class="Tab_page_title_zj">
			  <tr>
			    <td class="Tab_page_title_text"> </td>
			  </tr>
			</table>																					       
<table  border="0" cellpadding="0" cellspacing="0" class="form_info" id="FilterLayer">
  <tr>
    <td class="inquire_item">起始日期：</td>
	<td class="inquire_form">
	 <input type="text" name="consumeDate" id="consumeDate" readonly  class="input_width" value="<%=request.getAttribute("consumeDate") %>" />
     <img src="<%=contextPath%>/images/calendar.gif" id="tributton"  style="cursor:hand;" onmouseover="dateSelector(consumeDate,tributton);" />
	</td>
	<td class="inquire_item">结束日期：</td>
	<td class="inquire_form">
	 <input type="text" name="consumeDate2" id="consumeDate2" readonly  class="input_width" value="<%=request.getAttribute("consumeDate2") %>" />
     <img src="<%=contextPath%>/images/calendar.gif" id="tributton2"  style="cursor:hand;" onmouseover="dateSelector(consumeDate2,tributton2);" />
	</td>
	</tr>
  <tr>
    <td class="inquire_item">井号：　</td>
    <td class="inquire_form">
	 <input type="text" id="wellName" name="wellName"  class="input_width" value="${param.wellName}">
	 <img src="<%=contextPath%>/images/well.gif" id="tributton1" width="16" height="16" style="cursor:hand;" value="选择" onClick="returnselWellvaluesList(new Array(document.all.wellId.value),'<%=contextPath%>','showType=noTree;selectType=single');"/>
      <input type="hidden" name="wellId"  class="input_width"  value="<Drill:wellinfo filed="wellId"/>"/>
	</td>
	<td class="inquire_item">&nbsp;</td>
    <td class="inquire_form">&nbsp;
    </td>

hidden_cementFluidAddId
			    <input type="hidden" id="modifiDate" name="modifiDate"   value="${nowdatetime}" />																											
    
  </tr>
 
  <tr class="ali1">
    <td colspan="4">
    <input type="hidden" name="isQuery" id="isQuery" value="true"/> 
    <input name="Submit5" type="submit" class="iButton2" value="查询" />
    </td>
  </tr>
</table>
<table  border="0" cellpadding="0" cellspacing="0" class="ali6">
  <tr>
    <td><a href="#" onClick="switchSysBar1();return false;" onMouseOut="restoreImage(2)" onMouseOver="changeImage(2)"><img src="<%=contextPath%>/images/21.gif" name="ImageUp" width="50" height="8" border="0" ></a></td>
  </tr>
</table>
</form> 		                                                                                                                                                                                                                            
		<Drill:omsDrillError/> 																											       
		<form action="" class="formstyle" method="post">
			<table  border="0" cellpadding="0" cellspacing="0" class="Tab_new_mod_del">		
			<input type="hidden" name="drillcommfindbz" id="drillcommfindbz" value="<%=findflag%>"/> 																	
			  <tr class="ali7">																										
			    <td>
				<input type="button" name="add1" class="iButton2" value="新增" onclick="addmodel();">																	
				<input type="button" name="modify1" class="iButton2" value="修改" onclick="modifymodel();">																
				<input type="button" name="del1" class="iButton2" value="删除" onclick="deletemodel();">
				<!--			
				-->
	        <oms_auth:button type="button" value="新增" name="add" css="iButton2" functionId="" event=" onclick='return addmodel()' "/> 
	   		<oms_auth:button type="button" value="修改" name="modify" css="iButton2" functionId="" event=" onclick='return modifymodel()' "/> 
	   		<oms_auth:button type="button" value="删除" name="del" css="iButton2" functionId="" event=" onclick='return deletemodel()' "/> 
	   		<oms_auth:button type="button" value="查看" name="view" css="iButton2" functionId="" event=" onclick='return viewmodel()' "/>  
				<input type="button" name="colsewindow" class="iButton2" value="提取" onclick="return draw()">																	
				<input type="button" name="colsewindow" class="iButton2" value="审核" onclick="return verify()">																			
				<input type="button" name="colsewindow" class="iButton2" value="全部审核" onclick="return verifyAll()">																			
			    </td>																											
			  </tr>																												
			</table>																											
			</form>																												
	     <ec:table																											
		    tableId="SelectForm"																								
			items="drilldatelist"																								
			var="DrillDate"																									
			retrieveRowsCallback="org.extremecomponents.table.callback.LimitCallback"		
ec_action
			imagePath="${pageContext.request.contextPath}/images/table/*.gif"																		
			width="100%"																									
			rowsDisplayed="10"																								
			styleClass="eXtremeTable_info"																							
			filterable="false"																								
			>																										
																													
			<ec:row highlightRow="true">																							
			   <ec:column property="选择" >
checkbox_value
		    </ec:column>
                
                <ec:column property="wellName" title="井号">
				 <a href='javascript:viewListinfo("<%=contextPath%>","${wellBaseInfoUrl}?wellId=${DrillDate.wellId}&drillcommfindbz=1");'>${DrillDate.wellName}</a>
				</ec:column>
                <ec:column property="branchNoName" title="井筒"/>
ec_column
			<ec:column property="sendIndicate" title="状态">			
				  <c:if test="${DrillDate.sendIndicate==null}">
				    未审核
				  </c:if>
				  <c:if test="${DrillDate.sendIndicate=='1'}">
				    未审核
				  </c:if>
				  <c:if test="${DrillDate.sendIndicate=='2'}">
				    审核通过
				  </c:if> 
				  <c:if test="${DrillDate.sendIndicate=='3'}">
				    审核未通过
				  </c:if>
				  <c:if test="${DrillDate.sendIndicate=='4'}">
				   A1已提取
				  </c:if> 
				  <c:if test="${DrillDate.sendIndicate=='5'}">
				    A1打回
				  </c:if>
				</ec:column>
				<ec:column property="modifiDate" title="修改时间"/>
			</ec:row>																									
		</ec:table>																										 
		</body>
		<%@ include file="/common/jspBottom.jsp" %>																											 
	</html>
