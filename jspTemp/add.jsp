<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=GBK"%>                                                                                                               
<%@ taglib uri="oms" prefix="oms"%>																     
<%@ taglib uri="extremecomponents" prefix="ec" %>
<%@ taglib uri="/WEB-INF/drilltag.tld" prefix="Drill"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
%>																				     
	<!-- include thre header file -->    
<head>	
											     
																		     
<title>
@title@
</title> 
<meta http-equiv="Content-Type" content="text/html; charset=GBK">																	     
<link href="<%=contextPath%>/css/table.css" rel="stylesheet" type="text/css" />										     
<link rel="stylesheet" href="<%=contextPath%>/css/dynCalendar.css" type="text/css" media="screen"> 									     
<script src="<%=contextPath%>/js/browserSniffer.js" type="text/javascript" language="javascript"></script>							     
<script src="<%=contextPath%>/js/cfm.js" type="text/javascript" language="javascript"></script>								     									     
<script language="JavaScript" type="text/JavaScript" src="<%=contextPath%>/js/drilljs/checkdata.js"></script>						     
<script language="JavaScript" type="text/JavaScript" src="<%=contextPath%>/js/drilljs/message.js"></script>							     
<link rel="stylesheet" type="text/css" media="all" href="<%=contextPath%>/css/calendar-blue.css" />    
<script type="text/javascript" src="<%=contextPath%>/js/calendar.js"></script>			       
<script type="text/javascript" src="<%=contextPath%>/js/calendar-zh.js"></script>		       
<script type="text/javascript" src="<%=contextPath%>/js/calendar-setup.js"></script>		       
<script type="text/javascript" src="<%=contextPath%>/js/common.js"></script>
<script language="javascript" src="<%=contextPath%>/js/drilljs/syscommon.js"></script>			       
<script language="javascript" src="<%=contextPath%>/js/drilljs/common.js"></script>				       
																			     
																				     
<script language="javascript">																     
function cancle(){																		     
        //window.location.href="<%=contextPath%>/drill/site/accaccidentbitquery.do";
        window.history.back();                                   
	}	

function addcheckData(){																	     
checkdata
																				     								       								       
 return true;																		     
} 																				     
   										      
   												      
										      
</script>																			     
</head>																			     
																				     
<body >																			     
 <form id="checkForm"  name="checkForm" 
add_action
<table  border="0" cellpadding="0" cellspacing="0" class="Tab_page_title_zj">
  <tr>
    <td class="Tab_page_title_text">@title@</td>
  </tr>
</table>
<table  border="0" cellpadding="0" cellspacing="0" class="rtab_info">											     
  <tr>																			     
    <td colspan="4" class="Tab_Header">新增</td>													     
  </tr>
  <tr>
    <td class="ali4">井号：</td>
	<td class="ali5" >
        <input type="text" name="wellName"  class="input_width" value="<Drill:wellinfo filed="wellName"/>" readonly /><font color="red">*</font>
      <img src="<%=contextPath%>/images/well.gif" id="tributton1" width="16" height="16" style="cursor:hand;" value="选择" onClick="returnselWellvalues(new Array(document.all.wellId.value),'<%=contextPath%>','showType=noTree;selectType=single');"/>
      <input type="hidden" name="wellId"  class="input_width"  value="<Drill:wellinfo filed="wellId"/>"/>
	</td>
   <td class="ali4">井筒号：</td>														     
    <td class="ali5" >    <input type="text" id="branchNoName" name="branchNoName" value="主眼1" class="input_width" readonly/><font color="red">*</font>
	<img src="<%=contextPath%>/images/project.gif" border="0" alt="井筒号" style="border: 0px solid #fff" onclick="openSelecttcode(wellboreId,branchNoName,'contentPath=<%=contextPath%>;rootId=BRANCH_NO;');">
 	<input type="hidden" id="branchNo" name="wellboreId"  class="input_width"  value="1600300001000000001"/> 	</td>									     
    </tr>
trtd
  <tr class="odd">																		     
    <td colspan="4" class="ali3"><input name="Submit2" type="submit" class="iButton2" value="确定" onClick="return addcheckData()"/>				     
    <input name="btnCreate2" type="button" class="iButton2" onClick="cancle();" value="返回">								     
    </td>																			     
  </tr>																			     
</table>
<Drill:omsDrillToken/>																			     
  </form>																			     
</body>																			     
</html>		
