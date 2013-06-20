package com.sino.nm.spare2.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.ConvertUtil;
import com.sino.flow.bean.FlowAction;
import com.sino.flow.constant.FlowConstant;
import com.sino.flow.dto.FlowDTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.nm.spare2.dto.AmsItemTransHDTO;
import com.sino.nm.spare2.dto.AmsItemTransLDTO;
import com.sino.nm.spare2.model.AmsItemTransHModel;
import com.sino.nm.spare2.model.AmsItemTransLModel;
import com.sino.sinoflow.bean.FlowCommonUtil;
import com.sino.sinoflow.flowinterface.AppFlowBaseDTO;


/**
 * <p>Title: AmsItemTransHDAO</p>
 * <p>Description:程序自动生成服务程序“AmsItemTransHDAO”，请根据需要自行修改</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: 北京思诺博信息技术有限公司</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsItemTransHDAO extends AMSBaseDAO {
      private String hId = "";
    private AmsItemTransHDTO amsItemTransH = null;
     protected FlowCommonUtil flowCommonUtil = null;

    /**
     * 功能：备件事务头表(AMS) AMS_ITEM_TRANS_H 数据访问层构造函数
     * @param userAccount  SfUserDTO 代表本系统的最终操作用户对象
     * @param dtoParameter AmsItemTransHDTO 本次操作的数据
     * @param conn         Connection 数据库连接，由调用者传入。
     */
    public AmsItemTransHDAO(SfUserDTO userAccount, AmsItemTransHDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        this.amsItemTransH = (AmsItemTransHDTO) super.dtoParameter;
    }

    /**
     * 功能：SQL生成器BaseSQLProducer的初始化。
     * @param userAccount  BaseUserDTO 本系统最终操作用户类
     * @param dtoParameter DTO 本次操作的数据
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsItemTransHDTO dtoPara = (AmsItemTransHDTO) dtoParameter;
        super.sqlProducer = new AmsItemTransHModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * 功能：插入备件事务头表(AMS)表“AMS_ITEM_TRANS_H”数据。
     */
    public void createData() throws DataHandleException {
        super.createData();
        getMessage().addParameterValue("备件事务头表(AMS)");
    }

    /**
     * 功能：更新备件事务头表(AMS)表“AMS_ITEM_TRANS_H”数据。
     */
    public void updateData() throws DataHandleException {
        super.updateData();
        getMessage().addParameterValue("备件事务头表(AMS)");
    }

    /**
     * 功能：删除备件事务头表(AMS)表“AMS_ITEM_TRANS_H”数据。
     */
    public void deleteData() throws DataHandleException {
        super.deleteData();
        getMessage().addParameterValue("备件事务头表(AMS)");
    }

    /**
     * 保存单据
     * @param lineSet 行数据
     * @param flowDTO FlowDTO
     * @return success
     * @throws SQLException
     */
    public boolean saveOrder(DTOSet lineSet, FlowDTO flowDTO) throws SQLException {
        boolean success = true;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            String transId = amsItemTransH.getTransId();
            if (transId.equals("")) {
                OrderNumGenerator ong = new OrderNumGenerator(conn, userAccount.getCompanyCode(), amsItemTransH.getTransType());
                String transNo;
                if (amsItemTransH.getTransType().equals(DictConstant.BJSL)) {
                    transNo = ong.getBjslOrderNo();
                } else {
                    transNo = ong.getOrderNum();
                }
                amsItemTransH.setTransNo(transNo);
                SeqProducer seq = new SeqProducer(conn);
                transId = seq.getGUID();
                amsItemTransH.setTransId(transId);
                createData();
                if (amsItemTransH.getTransType().equals(DictConstant.BJSL)) {
                    flowDTO.setApplyId(transId);
                    flowDTO.setApplyNo(amsItemTransH.getTransNo());
                    flowDTO.setActivity("9");
                    FlowAction fa = new FlowAction(conn);
                    fa.setDto(flowDTO);
                    fa.add2Flow(flowDTO.getProcName());
                }
            } else {
                updateData();
                //有数据，删行信息
                deleteLines(transId);
            }
            saveLines(lineSet);
            conn.commit();
            prodMessage("SAVE_SUCCESS");
            success = true;
        } catch (SQLException e) {
            conn.rollback();
            success = false;
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        } catch (DataHandleException e) {
            conn.rollback();
            success = false;
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        } catch (QueryException e) {
            conn.rollback();
            success = false;
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        } finally {
            conn.setAutoCommit(autoCommit);
        }

        return success;
    }

    /**
     * 提交单据
     * @param lineSet 行数据
     * @param flowDTO FlowDTO
     * @return success
     * @throws SQLException
     */
    public boolean submitOrder(DTOSet lineSet, FlowDTO flowDTO,String sf_appFieldValue ) throws SQLException {
        boolean success = false;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            String transId = amsItemTransH.getTransId();
//            amsItemTransH.setTransStatus(DictConstant.COMPLETED);
            amsItemTransH.setTransDate(CalendarUtil.getCurrDate());
//            if (flowDTO == null) {
//                flowDTO = new FlowDTO();
//            }
////            if (flowDTO.getActId().equals("")) {
//            flowDTO.setApproveContent(FlowConstant.APPROVE_CONTENT_SUBMIT);
////            } else {
////                flowDTO.setApproveContent(FlowConstant.APPROVE_CONTENT_RESUBMIT);
////            }
//            FlowAction fa = null;

            if (transId.equals("")) {
                OrderNumGenerator ong = new OrderNumGenerator(conn, userAccount.getCompanyCode(), amsItemTransH.getTransType());

                String transNo;
                if (amsItemTransH.getTransType().equals(DictConstant.BJSL)) {
                    transNo = ong.getOrderNum();
                } else {
                    transNo = ong.getOrderNum();
                }
                amsItemTransH.setTransNo(transNo);
                SeqProducer seq = new SeqProducer(conn);
                transId = seq.getGUID();
                amsItemTransH.setTransId(transId);
                createData();

                /*if (amsItemTransH.getTransType().equals(DictConstant.BJSL)){
                    fa.add2Flow(flowDTO.getProcName());
                }*/

            } else {
                updateData();
                //有数据，删行信息
                deleteLines(transId);
            }
            saveLines(lineSet);
            if (amsItemTransH.getTransType().equals(DictConstant.BJRK)) {
//                addToItemInfo(lineSet, "SPARE");     //V1.0 备件做为条码管理时调用，写ETS_ITEM_INFO
                addSpareInfo(lineSet);

            } else if (amsItemTransH.getTransType().equals(DictConstant.TMRK)) { //条码设备入库
                addToItemInfo(lineSet, "UNKNOW");
            } else if (amsItemTransH.getTransType().equals(DictConstant.TMCK)) { //条码设备出库
                updateAddressIdOnWay(lineSet);
            } else if (amsItemTransH.getTransType().equals(DictConstant.FTMRK)) {//非条码设备入库
//                addToItemInfo(lineSet, "OTHERS");
                InvStorageIn(lineSet);
            } else if (amsItemTransH.getTransType().equals(DictConstant.FTMCK)) {//非条码设备出库
                InvStorageOut(lineSet);
            } else if (amsItemTransH.getTransType().equals(DictConstant.BJFK)) {
                //判断条码是否存在,有则修改地点,没有则新增加
//                addToItemInfo(lineSet, "SPARE");
                addSpareInfo(lineSet);
            } else if (amsItemTransH.getTransType().equals(DictConstant.ZKZY)) {
                //子库存转移
                invTrans();
            } else if (amsItemTransH.getTransType().equals(DictConstant.BJGH)) {
                //归还
                spareReturnSubmit();
            } else if (amsItemTransH.getTransType().equals(DictConstant.BJSL)) {
                flowDTO.setApplyId(transId);
                flowDTO.setApplyNo(amsItemTransH.getTransNo());
                flowDTO.setActivity(FlowConstant.FLOW_CODE_NEXT);
//                fa = new FlowAction(conn, flowDTO);
//                fa.flow();
              	processFlow(true, sf_appFieldValue, amsItemTransH);
            }
            conn.commit();
            prodMessage("SAVE_SUCCESS");
            success = true;
        } catch (SQLException e) {
            conn.rollback();
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        } catch (DataHandleException e) {
            conn.rollback();
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        } catch (CalendarException e) {
            conn.rollback();
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        }/* catch (QueryException e) {
            conn.rollback();
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        }*/ finally {
            conn.setAutoCommit(autoCommit);
        }
        return success;
    }
public boolean processFlow(boolean isSubmit, String Sf_appFieldValue, AmsItemTransHDTO headerdto) {
        AmsItemTransHDTO batchDTO = (AmsItemTransHDTO) dtoParameter;
        hId = batchDTO.getTransId();
        AppFlowBaseDTO headerDTO = new AppFlowBaseDTO() {
            public String getPrimaryKey() {
                return hId;
            }

            public void setPrimaryKey(String primaryKey) {
            }
        };

        headerDTO.setApp_dataID(batchDTO.getTransId());
        headerDTO.setPrimaryKey(batchDTO.getTransId());
        headerDTO.setOrderNo(batchDTO.getTransNo());
        headerDTO.setOrderName("备件申领");
        headerDTO.setSf_appFieldValue(Sf_appFieldValue);

        flowCommonUtil = new FlowCommonUtil(headerDTO, conn);
        return flowCommonUtil.processProcedure(isSubmit);
    }
    /**
     * 保存行信息至单据表
     * @param lineSet 行数据
     */
    public void saveLines(DTOSet lineSet) throws DataHandleException {
        if (lineSet != null && !lineSet.isEmpty()) {
            AmsItemTransLDAO lineDAO = new AmsItemTransLDAO(userAccount, null, conn);
            for (int i = 0; i < lineSet.getSize(); i++) {
                AmsItemTransLDTO lineData = (AmsItemTransLDTO) lineSet.getDTO(i);
                lineData.setTransId(amsItemTransH.getTransId());
                lineDAO.setDTOParameter(lineData);
                lineDAO.createData();
            }
        }
    }

    public void deleteLines(String transId) throws DataHandleException {
        AmsItemTransLModel model = new AmsItemTransLModel(userAccount, null);
        DBOperator.updateRecord(model.getDeleteByTransIdModel(transId), conn);
    }

    /**
     * 写ETS_ITEM_INFO表
     * @param lineSet     行数据
     * @param financeProp 类型
     * @throws DataHandleException
     */
    private void addToItemInfo(DTOSet lineSet, String financeProp) throws DataHandleException, SQLException {
        if (lineSet != null && !lineSet.isEmpty()) {
            CallableStatement cStmt = null;
            String sqlStr = "{call dbo.AMS_ITEM_TRANS_ADD_ITEM_INFO(?,?,?,?,?,?)}";
            try {
                cStmt = conn.prepareCall(sqlStr);
                for (int i = 0; i < lineSet.getSize(); i++) {
                    AmsItemTransLDTO lineData = (AmsItemTransLDTO) lineSet.getDTO(i);
                    cStmt.setString(1, lineData.getBarcode());
                    cStmt.setString(2, lineData.getItemCode());
                    cStmt.setString(3, Integer.toString(userAccount.getOrganizationId()));
                    cStmt.setString(4, Integer.toString(userAccount.getUserId()));
                    cStmt.setString(5, amsItemTransH.getToObjectNo());
                    cStmt.setString(6, financeProp);
                    cStmt.execute();
                }
            } finally {
                DBManager.closeDBStatement(cStmt);
            }
        }
    }

    /**
     * 写AMS_SPARE_INFO表
     * @param lineSet 行数据
     * @throws DataHandleException
     */
    private void addSpareInfo(DTOSet lineSet) throws DataHandleException, SQLException {
        if (lineSet != null && !lineSet.isEmpty()) {
            CallableStatement cStmt = null;
            String sqlStr = "{call dbo.AMS_INV_TRANS2_ADD_SPARE_INFO(?,?,?,?,?,?)}";
            conn.setAutoCommit(true);
            try {
                cStmt = conn.prepareCall(sqlStr);
                for (int i = 0; i < lineSet.getSize(); i++) {
                    AmsItemTransLDTO lineData = (AmsItemTransLDTO) lineSet.getDTO(i);
                    cStmt.setString(1, lineData.getBarcode());
                    cStmt.setString(2, lineData.getItemCode());
                    cStmt.setInt(3, lineData.getQuantity());
                    cStmt.setInt(4, userAccount.getOrganizationId());
                    cStmt.setInt(5, userAccount.getUserId());
                    cStmt.setString(6, amsItemTransH.getToObjectNo());
                    cStmt.execute();
                }
            } finally {
                DBManager.closeDBStatement(cStmt);
            }
        }
    }

    /**
     * 更新条码所在仓库为在途库
     * @param dtoSet DTOSet
     * @throws SQLException
     */
    private void updateAddressIdOnWay(DTOSet dtoSet) throws SQLException {
        if (dtoSet != null && !dtoSet.isEmpty()) {
            CallableStatement cStmt = null;
            String sqlStr = "{call AMS_ITEM_TRANS.ITEM_ON_THE_WAY(?,?,?)}";
            try {
                cStmt = conn.prepareCall(sqlStr);
                for (int i = 0; i < dtoSet.getSize(); i++) {
                    AmsItemTransLDTO lineData = (AmsItemTransLDTO) dtoSet.getDTO(i);
                    cStmt.setString(1, lineData.getBarcode());
                    cStmt.setString(2, ConvertUtil.int2String(amsItemTransH.getToOrganizationId()));
                    cStmt.setString(3, Integer.toString(userAccount.getUserId()));
                    cStmt.execute();
                }
            } finally {
                DBManager.closeDBStatement(cStmt);
            }
        }
    }

    public void InvStorageIn(DTOSet lineSet) throws SQLException {
        if (lineSet != null && !lineSet.isEmpty()) {
            CallableStatement cStmt = null;
            String sqlStr = "{call AMS_ITEM_TRANS.INV_STORAGE_IN(?,?,?,?,?,?)}";
            try {
                cStmt = conn.prepareCall(sqlStr);
                for (int i = 0; i < lineSet.getSize(); i++) {
                    AmsItemTransLDTO lineData = (AmsItemTransLDTO) lineSet.getDTO(i);
//                    String batchNo =  lineData.getBatchNo().equals("")?
                    cStmt.setString(1, lineData.getBatchNo());
                    cStmt.setString(2, amsItemTransH.getToObjectNo());
                    cStmt.setString(3, ConvertUtil.int2String(amsItemTransH.getToOrganizationId()));
                    cStmt.setString(4, lineData.getItemCode());
                    cStmt.setInt(5, lineData.getQuantity());
                    cStmt.setString(6, Integer.toString(userAccount.getUserId()));
                    cStmt.execute();
                }
            } finally {
                DBManager.closeDBStatement(cStmt);
            }
        }
    }

    public void InvStorageOut(DTOSet lineSet) throws SQLException {
        if (lineSet != null && !lineSet.isEmpty()) {
            CallableStatement cStmt = null;
            String sqlStr = "{call AMS_ITEM_TRANS.INV_STORAGE_OUT(?,?,?)}";
            try {
                cStmt = conn.prepareCall(sqlStr);
                for (int i = 0; i < lineSet.getSize(); i++) {
                    AmsItemTransLDTO lineData = (AmsItemTransLDTO) lineSet.getDTO(i);
                    cStmt.setString(1, lineData.getStorageId());
                    cStmt.setInt(2, lineData.getOutQuantity());
                    cStmt.setInt(3, userAccount.getUserId());
                    cStmt.execute();
                }
            } finally {
                DBManager.closeDBStatement(cStmt);
            }
        }
    }

    /**
     * 子库存转移
     * @throws SQLException
     */
    public void invTrans() throws SQLException {
        CallableStatement cStmt = null;
//        String sqlStr = "{call AMS_INV_TRANS2.SUB_INV_TRANS(?,?)}";
        String sqlStr = "{call dbo.AMS_INV_TRANS2_SUB_INV_TRANS(?,?)}";
        conn.setAutoCommit(true);
        try {
            cStmt = conn.prepareCall(sqlStr);
            cStmt.setString(1, amsItemTransH.getTransId());
            cStmt.setInt(2, userAccount.getUserId());
            cStmt.execute();
        } finally {
            DBManager.closeDBStatement(cStmt);
        }
    }


    private void spareReturnSubmit() throws SQLException {
        CallableStatement cStmt = null;
        conn.setAutoCommit(true);
        String sqlStr = "{call dbo.AMS_INV_TRANS2_SPARE_RETURN_SUBMIT(?)}";
        try {
            cStmt = conn.prepareCall(sqlStr);
            cStmt.setString(1, amsItemTransH.getTransId());
//            cStmt.setString(2, userAccount.getUserId());
            cStmt.execute();
        } finally {
            DBManager.closeDBStatement(cStmt);
        }
    }
}