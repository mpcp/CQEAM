package com.sino.ams.yj.servlet;

import java.sql.Connection;
import java.io.IOException;
import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.message.*;
import com.sino.base.db.conn.DBManager;
import com.sino.base.message.Message;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.*;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.base.constant.message.MsgKeyConstant;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

import com.sino.ams.yj.dto.AmsYjDrillDTO;
import com.sino.ams.yj.model.AmsYjDrillModel;
import com.sino.ams.yj.util.YjManagerUtil;
import com.sino.ams.yj.dao.AmsYjDrillDAO;
import com.sino.ams.bean.OptionProducer;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.newasset.bean.AssetsOptProducer;

/**
 * <p>Title: AmsYjDrillServlet</p>
 * <p>Description:程序自动生成服务程序“AmsYjDrillServlet”，请根据需要自行修改</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: 北京思诺博信息技术有限公司</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:应急管理-应急演练情况维护
 */

public class AmsYjDrillServlet extends BaseServlet {

	/**
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		Connection conn = null;
		try {
			SfUserDTO user = (SfUserDTO)SessionUtil.getUserAccount(req);
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(AmsYjDrillDTO.class.getName());
			AmsYjDrillDTO dtoParameter = (AmsYjDrillDTO)req2DTO.getDTO(req);
			String action = dtoParameter.getAct();
			conn = getDBConnection(req);
			AmsYjDrillDAO amsYjDrillDAO = new AmsYjDrillDAO(user, dtoParameter, conn);
             OptionProducer prd = new OptionProducer(user, conn);
            String ouoption = prd.getAllOrganizationYj(dtoParameter.getOrganizationId(), true);
            req.setAttribute(WebAttrConstant.OU_OPTION, ouoption);

            AssetsOptProducer optProducer = new AssetsOptProducer(user, conn);
            String opt = optProducer.getAllOu(dtoParameter.getOrganizationId());
            dtoParameter.setOrganizationOption(opt);
            if (action.equals("")) {
				forwardURL = "/yj/yjDrillSearch.jsp";
			} else if (action.equals(WebActionConstant.QUERY_ACTION)) {
				BaseSQLProducer sqlProducer = new AmsYjDrillModel(user, dtoParameter);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                pageDAO.setCalPattern(CalendarConstant.LINE_PATTERN);
				pageDAO.produceWebData();
				forwardURL = "/yj/yjDrillSearch.jsp";
			} else if (action.equals(WebActionConstant.NEW_ACTION)) {
                AmsYjDrillDTO amsYjDrill = new AmsYjDrillDTO();
                amsYjDrill.setOrganizationOption(prd.getAllOrganization(dtoParameter.getOrganizationId(), false));
                req.setAttribute("AMS_YJ_DRILL", amsYjDrill);
                forwardURL = "/yj/yjDrillDetail.jsp";
            } else if (action.equals(WebActionConstant.DETAIL_ACTION)) {
                amsYjDrillDAO.setDTOClassName(AmsYjDrillDTO.class.getName());
                AmsYjDrillDTO amsYjDrill = (AmsYjDrillDTO) amsYjDrillDAO.getDataByPrimaryKey();
                if (amsYjDrill == null) {
                    amsYjDrill = new AmsYjDrillDTO();
                    message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
                    message.setIsError(true);
                }
                opt = optProducer.getAllOu(amsYjDrill.getOrganizationId());
                amsYjDrill.setOrganizationOption(opt);
                req.setAttribute("AMS_YJ_DRILL", amsYjDrill);
                forwardURL = "/yj/yjDrillDetail.jsp";
            } else if (action.equals(WebActionConstant.CREATE_ACTION)) {
                boolean isNew = StrUtil.isEmpty(dtoParameter.getDrillId());
              	YjManagerUtil yjUtil= new YjManagerUtil(user, conn);
                if (isNew) {
                    dtoParameter.setDrillId(String.valueOf(yjUtil.getYjManagerMax("AMS_YJ_DRILL_SEQ")));
                }
                amsYjDrillDAO.createData();
                forwardURL = "/servlet/com.sino.ams.yj.servlet.AmsYjDrillServlet?act=" + WebActionConstant.QUERY_ACTION + "&drillId=" + dtoParameter.getDrillId();
            } else if (action.equals(WebActionConstant.UPDATE_ACTION)) {
                amsYjDrillDAO.updateData();
                forwardURL = "/servlet/com.sino.ams.yj.servlet.AmsYjDrillServlet?act=" + WebActionConstant.QUERY_ACTION + "&drillId=" + dtoParameter.getDrillId();
            } else if (action.equals(WebActionConstant.DELETE_ACTION)) {       //删除
                amsYjDrillDAO.deleteData();
                forwardURL = "/servlet/com.sino.ams.yj.servlet.AmsYjDrillServlet?act=" + WebActionConstant.QUERY_ACTION;
            } else if (action.equals(WebActionConstant.EXPORT_ACTION)) {      //导出到Excel
                File file = amsYjDrillDAO.exportFile();
                WebFileDownload fileDown = new WebFileDownload(req, res);
                fileDown.setFilePath(file.getAbsolutePath());
                fileDown.download();
                file.delete();
            }  else {
				message = getMessage(MsgKeyConstant.INVALID_REQ);
				message.setIsError(true);
				forwardURL = MessageConstant.MSG_PRC_SERVLET;
			}
		} catch (PoolPassivateException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.POOL_PASSIVATE_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (DTOException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.DTO_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (QueryException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.QUERY_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (DataHandleException ex) {
			ex.printLog();
			//请根据实际情况处理消息
			forwardURL = "保持界面录入的数据，返回到原页面，并显示上面给出的消息";
		} catch (WebFileDownException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
			DBManager.closeDBConnection(conn);
			setHandleMessage(req, message);
			ServletForwarder forwarder = new ServletForwarder(req, res);
			forwarder.forwardView(forwardURL);
			//根据实际情况修改页面跳转代码。
		}
	}
}