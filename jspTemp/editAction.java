package com.cnpc.oms.webapp.mb.action;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.OMSAction;
import org.apache.struts.config.ServiceCallConfig;

import com.cnpc.oms.message.ISvrMsg;
import com.cnpc.oms.message.MsgElement;
import com.cnpc.oms.message.SrvMsgUtil;
import com.cnpc.oms.util.OMSConstants;
import com.cnpc.oms.webapp.drill.common.DrillCommBasecodeAction;
import com.cnpc.oms.webapp.drill.common.MVCDRILLConstants;
import com.cnpc.oms.webapp.drill.util.DateTimeUtil;
import com.cnpc.oms.webapp.srvclient.IServiceCall;
import com.cnpc.oms.webapp.srvclient.ServiceCallFactory;
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
edit_action
	  //	 ����ҳ����ʾ�ַ�����������еĴ���id��
	private String basecode = MVCDRILLConstants.BIT_BIGTYPE + ";" + MVCDRILLConstants.BIT_PRODUCER ;	
	/**
	 * ���Ǹ����setDTOValue����
	 */
	public void setDTOValue1(com.cnpc.oms.message.ISvrMsg requestDTO,
			ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws Exception {
		    super.setDTOValue(requestDTO, mapping, form, request, response);
			requestDTO.setValue("dirllbaseid", basecode);
	}	
	/**
	 * ���Ǹ����setDTOValue����
	 */
	public void setDTOValue2(com.cnpc.oms.message.ISvrMsg requestDTO,
			ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws Exception {
		    super.setDTOValue(requestDTO, mapping, form, request, response);
			// ��session���ò�ѯ����ִ�в�ѯ/
		    HttpSession session = request.getSession(false);
		    String currentOpWellId = (String) session.getAttribute("drill_session_currOpWellName");
		    requestDTO.setValue("currentOpWellName", currentOpWellId);

	}
	
	
	public ActionForward executeResponse(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		/**
		 * �õ��ɹ�ʧ����
		 */
		//String retCodex = "";
		ISvrMsg respDto = (ISvrMsg) request.getAttribute(MVCConstants.RESPONSE_DTO);
		try {
			//String retCodex = respDto.getRetCode();
			/**
			 * ���õ�һ������
			 */
			ServiceCallConfig callConfig1 = mapping.findServiceCallConfig("codeservice");
			if (callConfig1 == null)
				throw new Exception(
						"OMSAction: default serviceCallConfig is null");
			// create dto
			ISvrMsg requestDTO1 = SrvMsgUtil.createISvrMsg(callConfig1
					.getOperationName());
			// set dto value
			setDTOValue1(requestDTO1, mapping, form, request, response);
			// call service
			IServiceCall call = ServiceCallFactory.getIServiceCall();
			// response dto
			ISvrMsg respDto1 = call.callWithDTO(requestDTO1, callConfig1);

			DrillCommBasecodeAction base = new DrillCommBasecodeAction();
			base.basecodeexecute(basecode, request, respDto1);
			
			/**
			 * ���õڶ�������
			 */
			ServiceCallConfig callConfig2 = mapping.findServiceCallConfig("audit");
			if (callConfig2 == null)
				throw new Exception(
						"OMSAction: default serviceCallConfig is null");
			// create dto
			ISvrMsg requestDTO2 = SrvMsgUtil.createISvrMsg(callConfig2
					.getOperationName());
			// set dto value
			setDTOValue2(requestDTO2, mapping, form, request, response);
			// call service
			IServiceCall call2 = ServiceCallFactory.getIServiceCall();
			// response dto
			ISvrMsg respDto2 = call2.callWithDTO(requestDTO2, callConfig2);
		    String retCodex = respDto2.getRetCode();
		

			//�õ����� list
			request.setAttribute("responseDTO", respDto2);
			//ec �����ȡ�õ�list
				MsgElement me = respDto2.getMsgElement("AuditVO");
				if(me!=null){
					HashMap hm = (HashMap) me.toMap();
					String bitBigtype = (String) hm.get("bitBigtype");
					request.setAttribute("bitBigtype", bitBigtype);
					String bitProducer = (String) hm.get("bitProducer");
					request.setAttribute("bitProducer", bitProducer);

				}
				// ��ǰ���� ����
				request.setAttribute("nowdate", DateTimeUtil.getDate());
				// �õ���ǰʱ�� ���� �� ʱ��
				request.setAttribute("nowdatetime", DateTimeUtil.getDateTime());
	    if (OMSConstants.DEFAULT_SUCESS_STATUS.equals(retCodex)) {
			return mapping.findForward(MVCConstants.FORWARD_SUCESS);
		} else if (MVCDRILLConstants.DATA_NOT_EXIST.equals(retCodex)) {
			        request.getSession().setAttribute(
					MVCDRILLConstants.ERROR_MSG_DRILL,
					MVCDRILLConstants.ERROR_DATA_NOT_MSG_DRILL);
             return mapping.findForward(MVCConstants.FORWARD_SUCESS+"1");
          }
	    
	    else {
			return mapping.findForward(MVCConstants.FORWARD_FAILED);
		}
	} catch (Exception ex) {
		ex.printStackTrace();
		return mapping.findForward(MVCConstants.FORWARD_FAILED);
	}
	
  }
}
