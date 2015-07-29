package com.cnpc.oms.webapp.mb.action;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.OMSAction;

import com.cnpc.oms.message.ISvrMsg;
import com.cnpc.oms.message.MsgMetaBean;
import com.cnpc.oms.util.OMSConstants;
import com.cnpc.oms.webapp.drill.common.ActionUtils;
import com.cnpc.oms.webapp.util.MVCConstants;
/**
 *  
 * ���⣺��ʯ�ͼ��Ź�˾��������ϵͳ
 *
 * רҵ���꾮רҵ
 * 
 * ��˾: ��������
 * 
 * ���ߣ���ǿ��2007-7-31
 *
 * �������¹ʴ���-�¹���ͷ���Action
 *
 * ˵��: 
 */
query_action
	public ActionForward executeResponse(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	/**
	 * �õ��ɹ�ʧ����
	 */
	ISvrMsg respDto = (ISvrMsg) request.getAttribute(MVCConstants.RESPONSE_DTO);
	try {
		String retCodex = respDto.getRetCode();
		//��̨�����õ���list
		//getObjects()
		Vector respList = (Vector) respDto.getObjects("drilldatelist");
		List respMList = this.getData(respList);
		//ec �����ȡ�õ�list
		request.setAttribute("drilldatelist", respMList);
		/**
		 * ��ҳ��¼��
		 */
	    request.setAttribute("totalRows",respDto.getValue("totalRecords"));
        if (respDto.getValue("consumeDate") != null) {
            request.setAttribute("consumeDate",
                    respDto.getValue("consumeDate"));
            request.setAttribute("consumeDate2",
                    respDto.getValue("consumeDate2"));
        }
        else {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();// ��ʱ��ӡ����ȡ����ϵͳ��ǰʱ��
            request.setAttribute("consumeDate2",
                    sdf.format(calendar.getTime()));
            calendar.add(Calendar.DATE, -1);
            request.setAttribute("consumeDate",
                    sdf.format(calendar.getTime()));
        }
	 /**
	  * ��ǰ̨�����ɹ�ʧ����תҳ��
	  */ 
	    
	    if (OMSConstants.SUCESS_RET_CODE.equals(retCodex)) {
			return mapping.findForward(MVCConstants.FORWARD_SUCESS);
		} 
	    
	    else {
			return mapping.findForward(MVCConstants.FORWARD_FAILED);
		}
	} catch (Exception ex) {
		ex.printStackTrace();
		return mapping.findForward(MVCConstants.FORWARD_FAILED);
	}
	
  }
	public static List getData(Vector respList) {
		List list = new ArrayList();
		for (int i = 0; i < respList.size(); i++) {
			Vector vt = (Vector) (respList.get(i));
			java.util.Map president = new java.util.HashMap();
			for (int j = 0; j < vt.size(); j++) {
				String Value = ((MsgMetaBean) vt.get(j)).getValue();
				String Key = ((MsgMetaBean) vt.get(j)).getKey();
				if( Key.equals("startdateTemp")) {
						Value = ActionUtils.getYearValue(Value);
					}
				if( Key.equals("enddateTemp")) {
					Value = ActionUtils.getYearValue(Value);
				}
				president.put(Key, Value);
			}
			list.add(president);
		}
		return list;
	}
}
