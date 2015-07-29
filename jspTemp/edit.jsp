<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=GBK"%>                                                                                                               
<%@ taglib uri="oms" prefix="oms"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>																     
<html>																			     
<%		 																		     
    String contextPath = request.getContextPath();														     
%>																				     
<head>																			     
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />											     
<title></title> 																	     
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
function updatecheckData(){																	     

checkdata
 return true;																		     
} 																				     
</script>																			     
</head>																			     
																				     
<body >																			     
 <form id="checkForm"  name="checkForm" 
edit_action
<table  border="0" cellpadding="0" cellspacing="0" class="Tab_page_title_zj">
  <tr>
    <td class="Tab_page_title_text"> </td>
  </tr>
</table>
<table  border="0" cellpadding="0" cellspacing="0" class="rtab_info">											     
  <tr>																			     
    <td colspan="4" class="Tab_Header">修改</td>													     
  </tr>
  <tr>
    <td class="ali4">井号：</td>
	<td class="ali5" >
        <input type="text" name="wellName"  class="input_width"  value="<oms:object property="AuditVO.wellName"/>" readonly />
      <img src="<%=contextPath%>/images/well.gif" id="tributton1" width="16" height="16" style="cursor:hand;" value="选择" onClick="returnselWellvalues(new Array(document.all.wellId.value),'<%=contextPath%>','showType=noTree;selectType=single');"/>
      <input type="hidden" name="wellId"  class="input_width" value="<oms:object property="AuditVO.wellId"/>"/>
      <input type="hidden" name="bsflag" id="bsflag" value="0">
      <input type="hidden" name="sendIndicate" id="sendIndicate" value="<oms:object property="AuditVO.sendIndicate"/>">
      <input type="hidden" name="sendIndicate" id="sendIndicate" value="<oms:object property="AuditVO.sendIndicate"/>">
      
      <input type="hidden" name="createDate" id="createDate" value="<oms:object property="AuditVO.createDate"/>">
      <input type="hidden" name="a7Auditor" id="a7Auditor" value="<oms:object property="AuditVO.a7Auditor"/>">
      <input type="hidden" name="a1ExtractTime" id="a1ExtractTime" value="<oms:object property="AuditVO.a1ExtractTime"/>">
      <input type="hidden" name="a1BackTime" id="a1BackTime" value="<oms:object property="AuditVO.a1BackTime"/>">
      <input type="hidden" name="a1BackComments" id="a1BackComments" value="<oms:object property="AuditVO.a1BackComments"/>">
      <input type="hidden" name="a1Backor" id="a1Backor" value="<oms:object property="AuditVO.a1Backor"/>">
      <input type="hidden" name="creator" id="creator" value="<oms:object property="AuditVO.creator"/>">
      <input type="hidden" id="modifiDate" name="modifiDate"   value="${nowdatetime}" /> 
	</td>
       <td class="ali4">井筒号：</td>			
    <td class="ali5">    <input type="text" id="branchNoName" name="branchNoName" value="<oms:object property="AuditVO.branchNoName"/>" class="input_width" readonly/>			
	<img src="<%=contextPath%>/images/project.gif" border="0" alt="井筒号" style="border: 0px solid #fff" onclick="openSelecttcode(wellboreId,branchNoName,'contentPath=<%=contextPath%>;rootId=BRANCH_NO;');">		
 	<input type="hidden" id="wellboreId" name="wellboreId"  class="input_width"  value="<oms:object property="AuditVO.wellboreId"/>"/> 	</td>	
    </tr>
trtd

  <tr class="odd">																		     
    <td colspan="4" class="ali3"><input name="Submit2" type="submit" class="iButton2" value="确定" onClick="return updatecheckData()"/>				     
    <input name="btnCreate2" type="button" class="iButton2" onClick="cancle();" value="返回">								     
    </td>																			     
  </tr>																			     
</table>																			     
  </form>																			     
</body>																			     
</html>		
