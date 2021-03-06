package com.sino.ams.spare.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.StrUtil;

import com.sino.framework.dto.BaseUserDTO;
import com.sino.sms.bean.MessageSaver;
import com.sino.sms.constant.SMSConstant;
import com.sino.sms.dto.SfMsgDefineDTO;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.spare.dto.AmsItemAllocateDDTO;
import com.sino.ams.spare.dto.AmsItemAllocateHDTO;
import com.sino.ams.spare.model.SpareDBCancelModel;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-12-02
 * Time: 00:00:00
 * To change this template use File | Settings | File Templates.
 */
public class SpareDBCancelDAO extends AMSBaseDAO {


    private SfUserDTO sfUser = null;
    private AmsItemAllocateHDTO amsItemTransH = null;

    public SpareDBCancelDAO(SfUserDTO userAccount, AmsItemAllocateHDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        this.sfUser = userAccount;
        this.amsItemTransH = (AmsItemAllocateHDTO) super.dtoParameter;
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsItemAllocateHDTO dtoPara = (AmsItemAllocateHDTO) dtoParameter;
        super.sqlProducer = new SpareDBCancelModel((SfUserDTO) userAccount, dtoPara);
    }

    public RowSet getLines2(String transId) throws QueryException {
        SpareDBCancelModel model = (SpareDBCancelModel) sqlProducer;
        SimpleQuery sq = new SimpleQuery(model.getDataByTransIdMode2(transId), conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }

    /**
     * 功能：备件调拨撤销。
     * @param transId
     * @throws DataHandleException
     */
    public void updateData(String transId) throws DataHandleException {
        SpareDBCancelModel model = new SpareDBCancelModel(sfUser, null);
        DBOperator.updateRecord(model.updateData(transId), conn);
    }


    /** 功能：撤销调拨单。
     * @return success
     * @throws java.sql.SQLException
     */
    public boolean cancelOrder(String transId) throws SQLException, ContainerException {
        boolean success = true;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            amsItemTransH.setTransDate(CalendarUtil.getCurrDate());
            DTOSet  lineSet = getlineSet(transId);
            cancelDB(lineSet); //撤消保留量
            SpareDBCancelModel model = new SpareDBCancelModel(sfUser, null);
            DBOperator.updateRecord(model.getUpdateHStatusModel(amsItemTransH.getTransId()), conn);//撤销备件调拨状态
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
        } catch (CalendarException e) {
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
     * 功能：获得要撤销的行信息。
     */
    public DTOSet getlineSet(String transId) throws QueryException {
        SQLModel  sqlModel;
        SpareDBCancelModel model = new SpareDBCancelModel(sfUser, null);
        sqlModel=  model.getLinesData(transId);
        SimpleQuery  sq = new SimpleQuery(sqlModel, conn);
        sq.setDTOClassName(AmsItemAllocateDDTO.class.getName());
        sq.executeQuery();
        return sq.getDTOSet();
    }

    /**
     * 功能：撤消备件调拨写保留量。
     * @param lineSet
     * @throws SQLException
     */
    public void cancelDB(DTOSet lineSet) throws SQLException {
        CallableStatement cStmt = null;
        String sqlStr = "{call dbo.AITS_CANCEL_RESERVED_QTY(?,?,?)}";
        try {
            cStmt = conn.prepareCall(sqlStr);
            for (int i = 0; i < lineSet.getSize(); i++) {
                AmsItemAllocateDDTO dto = (AmsItemAllocateDDTO) lineSet.getDTO(i);
                cStmt = conn.prepareCall(sqlStr);
                cStmt.setString(1, amsItemTransH.getTransId());
                cStmt.setString(2, dto.getBarcode());
                cStmt.setInt(3, dto.getQuantity());
                cStmt.execute();
            }
        } finally {
            DBManager.closeDBStatement(cStmt);
        }
    }


  /**
	 * 功能：发送手机短信给备件调拨人
	 * @throws DataHandleException
	 */
	public void saveMessage() throws DataHandleException {

        AmsItemAllocateHDTO headerDTO = (AmsItemAllocateHDTO) dtoParameter;
        String userId = "";
		String orderNum = headerDTO.getTransNo();
		String userName = "";
		String userTel = "";
        String toOrgId = "";
        String fromCompanyName = "";
        Row row = null;
        try {
			MessageSaver msgSaver = new MessageSaver(conn);

            toOrgId = getToOrgId(conn,headerDTO.getTransId());
            fromCompanyName = getFromCompanyName(conn,headerDTO.getTransId());

            if (!StrUtil.isEmpty(toOrgId)) {
                RowSet rows = getUserNames(conn, toOrgId);
                if (rows != null && rows.getSize() > 0) {
                    for (int i = 0; i < rows.getSize(); i++) {
                             row = rows.getRow(i);

                        userId = row.getValue("USER_ID").toString();
                        userName = row.getValue("USERNAME").toString();

                        userTel = getMovetel(conn,userId);

                        SfMsgDefineDTO msgDefineDTO = new SfMsgDefineDTO();
                        msgDefineDTO.setMsgCategoryId(SMSConstant.SPARE_RECIEVE_ID);
                        msgDefineDTO.setCreatedBy(sfUser.getUserId());
                        msgDefineDTO.setCellPhone(userTel);
                        msgDefineDTO.setApplyNumber(orderNum);
                        msgDefineDTO.setUserId(userId);
                        msgDefineDTO.setMsgContent("您好"+userName +":"+fromCompanyName + "公司正常库调拨备件到您公司仓库，请注意接收，调拨单号:"+orderNum);
                        msgSaver.saveMsg(msgDefineDTO);
                    }
                }
            }
		} catch (QueryException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (ContainerException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (Exception ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		}
	}


    /**
     * 功能：查找用户的移动电话
     * @param conn
     * @param userId
     * @return
     * @throws QueryException
     * @throws ContainerException
     */
    private String getMovetel(Connection conn, String userId) throws QueryException, ContainerException {
          String movetel = "";
          SQLModel sqlModel = new SQLModel();
          List sqlArgs = new ArrayList();
          String sqlStr = " SELECT DISTINCT SU.MOVETEL FROM SF_USER SU WHERE SU.USER_ID = ?";
          sqlArgs.add(userId);
          sqlModel.setSqlStr(sqlStr);
          sqlModel.setArgs(sqlArgs);
          SimpleQuery sq = new SimpleQuery(sqlModel, conn);
          sq.executeQuery();
          Row row = sq.getFirstRow();
          movetel = row.getValue("MOVETEL").toString();
          return movetel;
      }

    /**
     * 功能：获得调往的公司id
     * @param conn
     * @param transId
     * @return
     * @throws QueryException
     * @throws ContainerException
     */
    private String getToOrgId(Connection conn, String transId) throws QueryException, ContainerException {
          String fromOrgId = "";
          SQLModel sqlModel = new SQLModel();
          List sqlArgs = new ArrayList();
          String sqlStr = " SELECT" +
                          " AIAH.TO_ORGANIZATION_ID\n" +
                          " FROM " +
                          " AMS_ITEM_ALLOCATE_H AIAH\n" +
                          " WHERE " +
                          " AIAH.TRANS_ID = ?";
          sqlArgs.add(transId);
          sqlModel.setSqlStr(sqlStr);
          sqlModel.setArgs(sqlArgs);
          SimpleQuery sq = new SimpleQuery(sqlModel, conn);
          sq.executeQuery();
          Row row = sq.getFirstRow();
          fromOrgId = row.getValue("TO_ORGANIZATION_ID").toString();
          return fromOrgId;
      }

    /**
     * 功能：获得派送的公司的名称
     * @param conn
     * @param transId
     * @return
     * @throws QueryException
     * @throws ContainerException
     */
     private String getFromCompanyName(Connection conn, String transId) throws QueryException, ContainerException {
          String fromCompanyName = "";
          SQLModel sqlModel = new SQLModel();
          List sqlArgs = new ArrayList();
          String sqlStr = " SELECT " +
                          " AIAH.FROM_ORGANIZATION_ID,\n" +
                          " AMS_PUB_PKG.GET_ORGNIZATION_NAME(AIAH.FROM_ORGANIZATION_ID) COMPANY_ANME\n" +
                          " FROM " +
                          " AMS_ITEM_ALLOCATE_H AIAH\n" +
                          " WHERE " +
                          " AIAH.TRANS_ID = ?";
          sqlArgs.add(transId);
          sqlModel.setSqlStr(sqlStr);
          sqlModel.setArgs(sqlArgs);
          SimpleQuery sq = new SimpleQuery(sqlModel, conn);
          sq.executeQuery();
          Row row = sq.getFirstRow();
          fromCompanyName = row.getValue("COMPANY_ANME").toString();
          return fromCompanyName;
      }


    private RowSet getUserNames(Connection conn, String orgId) throws QueryException, ContainerException {
          SQLModel sqlModel = new SQLModel();
          List sqlArgs = new ArrayList();
          String sqlStr = "SELECT " +
                  " SU.USER_ID,\n" +
                  " SU.USERNAME,\n" +
                  " SU.ORGANIZATION_ID,\n" +
                  " AMS_PUB_PKG.GET_ORGNIZATION_NAME(SU.ORGANIZATION_ID)\n" +
                  " FROM " +
                  " SF_USER SU, SF_USER_RIGHT SUR, SF_ROLE SR\n" +
                  " WHERE " +
                  " SUR.ROLE_ID = SR.ROLE_ID\n" +
                  " AND SUR.USER_ID = SU.USER_ID\n" +
                  " AND (SR.ROLENAME LIKE '%地市备品备件管理员%' OR SR.ROLENAME LIKE '%省公司备品备件管理员%')\n" +
                  " AND SU.ORGANIZATION_ID = ?";
          sqlArgs.add(orgId);
          sqlModel.setSqlStr(sqlStr);
          sqlModel.setArgs(sqlArgs);
          SimpleQuery sq = new SimpleQuery(sqlModel, conn);
          sq.executeQuery();
          return sq.getSearchResult();
      }

}
