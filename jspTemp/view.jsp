<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=GBK"%>                                                                                                               
<%@ taglib uri="oms" prefix="oms"%>																     
<%@ taglib uri="extremecomponents" prefix="ec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>																			     
<%	
String contextPath = request.getContextPath();														     
%>																				     
	<!-- include thre header file -->    
<head>	
<meta http-equiv="Content-Type" content="text/html; charset=GBK">											     
																		     
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
																		     
																				     
<script language="javascript">																     
function cancle(){																		     
        //window.location.href="<%=contextPath%>/drill/site/accaccidentbitquery.do";                                   
		window.history.back();	
	}																		     
										      
</script>																			     
</head>																			     
																				     
<body >																			     
 <form id="checkForm"  name="checkForm" action="<%=contextPath%>/drill/site/accaccidentbitsave.do" method="post" onsubmit="return validateCheckForm(this);">
<table  border="0" cellpadding="0" cellspacing="0" class="Tab_page_title_zj">
  <tr>
    <td class="Tab_page_title_text"></td>
  </tr>
</table>
<table  border="0" cellpadding="0" cellspacing="0" class="rtab_info">											     
  <tr>																			     
    <td colspan="4" class="Tab_Header">浏览</td>													     
  </tr>
  <tr>
    <td class="ali4"> 井号：</td>
    <td class="ali5" >&nbsp;<oms:object property="AuditVO.wellName"/> </td>
    <td class="ali4"> 井筒号：</td>
    <td class="ali5" >&nbsp;<oms:object property="AuditVO.branchNoName"/> </td>
  </tr>
trtd
  <tr class="odd">																		     
    <td colspan="4" class="ali3">			     
    <input name="btnCreate2" type="button" class="iButton2" onClick="cancle();" value="返回">								     
    </td>																			     
  </tr>																			     
</table>																			     
  </form>																			     
</body>																			     
</html>		
