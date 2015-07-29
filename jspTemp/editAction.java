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
 * 标题：中石油集团公司生产管理系统
 *
 * 专业：钻井专业
 * 
 * 公司: 西部世纪
 * 
 * 作者：贺强，2007-7-31
 *
 * 描述：事故处理-事故钻头情况Action
 *
 * 说明: 
 */
edit_action
	  //	 定义页面显示字符串（代码表中的大类id）
	private String basecode = MVCDRILLConstants.BIT_BIGTYPE + ";" + MVCDRILLConstants.BIT_PRODUCER ;	
	/**
	 * 覆盖父类的setDTOValue方法
	 */
	public void setDTOValue1(com.cnpc.oms.message.ISvrMsg requestDTO,
			ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws Exception {
		    super.setDTOValue(requestDTO, mapping, form, request, response);
			requestDTO.setValue("dirllbaseid", basecode);
	}	
	/**
	 * 覆盖父类的setDTOValue方法
	 */
	public void setDTOValue2(com.cnpc.oms.message.ISvrMsg requestDTO,
			ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws Exception {
		    super.setDTOValue(requestDTO, mapping, form, request, response);
			// 从session里获得查询条件执行查询/
		    HttpSession session = request.getSession(false);
		    String currentOpWellId = (String) session.getAttribute("drill_session_currOpWellName");
		    requestDTO.setValue("currentOpWellName", currentOpWellId);

	}
	
	
	public ActionForward executeResponse(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		/**
		 * 得到成功失败码
		 */
		//String retCodex = "";
		ISvrMsg respDto = (ISvrMsg) request.getAttribute(MVCConstants.RESPONSE_DTO);
		try {
			//String retCodex = respDto.getRetCode();
			/**
			 * 调用第一个服务
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
			 * 调用第二个服务
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
		

			//得到返回 list
			request.setAttribute("responseDTO", respDto2);
			//ec 组件中取得的list
				MsgElement me = respDto2.getMsgElement("AuditVO");
				if(me!=null){
					HashMap hm = (HashMap) me.toMap();
					String bitBigtype = (String) hm.get("bitBigtype");
					request.setAttribute("bitBigtype", bitBigtype);
					String bitProducer = (String) hm.get("bitProducer");
					request.setAttribute("bitProducer", bitProducer);

				}
				// 当前日期 日期
				request.setAttribute("nowdate", DateTimeUtil.getDate());
				// 得到当前时间 日期 和 时间
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
