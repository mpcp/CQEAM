package com.sino.ams.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.dto.AmsItemCorrectLogDTO;
import com.sino.ams.newasset.dto.AmsItemInfoHistoryDTO;
import com.sino.ams.newasset.model.ItemSpareModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA. User: T_suhuipeng Date: 2011-6-10 Time: 22:42:38 To
 * change this template use File | Settings | File Templates.
 */
public class ItemSpareDAO extends AMSBaseDAO {
	private AmsItemCorrectLogDAO logDAO = null;
	private AmsItemInfoHistoryDAO hisLogDAO = null;
	private SimpleQuery simp = null;

	public ItemSpareDAO(SfUserDTO userAccount,
			AmsAssetsAddressVDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		logDAO = new AmsItemCorrectLogDAO(userAccount, null, conn);
		hisLogDAO = new AmsItemInfoHistoryDAO(userAccount, null, conn);
	}

	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
		sqlProducer = new ItemSpareModel((SfUserDTO) userAccount, dto);
	}

	public boolean updateItems(String[] barcodeNos) {
		boolean operateResult = false;
		boolean autoCommit = true;
		String barcodes = "";
		try {
			AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			
			for (int i = 0; i < barcodeNos.length; i++) {
				barcodes += barcodeNos[i] + ",";
				dto.setBarcode(barcodeNos[i]);
				// barcodeNos[i]-->address_id, responsibilityUser,
				// responsibilityDetpt(ϵͳ)
				setDTOParameter(dto);
				logDAO.setDTOParameter(getLogDTO());
				updateData();
				
				logDAO.createData();
				hisLogDAO.setDTOParameter(getHisLogDTO());
				hisLogDAO.recordHistory();
			}
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} catch (SQLException ex) {
			Logger.logError(ex);
		} catch (QueryException ex) {
			ex.printLog();
		} finally {
			try {
				if (operateResult) {
					prodMessage(AssetsMessageKeys.ITEM_UPDATE_SUCCESS);
					conn.commit();
				} else {
					prodMessage(AssetsMessageKeys.ITEM_UPDATE_FAILURE);
					conn.rollback();
				}
				message.setIsError(!operateResult);
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex1) {
				Logger.logError(ex1);
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ�����̨��ά����־
	 * 
	 * @return AmsItemCorrectLogDTO
	 * @throws QueryException
	 */
	private AmsItemCorrectLogDTO getLogDTO() throws QueryException {
		AmsItemCorrectLogDTO logDTO = null;
		try {
			AmsAssetsAddressVDTO newDTO = (AmsAssetsAddressVDTO) dtoParameter;
			ItemSpareModel modelProducer = (ItemSpareModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPrimaryKeyDataModel();
			String logId = new SeqProducer(conn).getGUID();
			if (simp == null) {
				simp = new SimpleQuery(sqlModel, conn);
				simp.setDTOClassName(AmsAssetsAddressVDTO.class.getName());
				simp.setCalPattern(LINE_PATTERN);
			} else {
				simp.setSql(sqlModel);
			}
			simp.executeQuery();
			if (simp.hasResult()) {
				AmsAssetsAddressVDTO oldDTO = (AmsAssetsAddressVDTO) simp.getFirstDTO();
				
				logDTO = new AmsItemCorrectLogDTO();
				logDTO.setBarcode(newDTO.getBarcode());
				logDTO.setLogId(logId);
				StringBuffer correctContent = new StringBuffer();
				String[] fieldNames = { "ITEM_CODE", "ITEM_CATEGORY",
						"ITEM_CATEGORY_NAME", "ITEM_NAME", "ITEM_SPEC",
						"ADDRESS_ID", "WORKORDER_OBJECT_CODE",
						"WORKORDER_OBJECT_NAME", "RESPONSIBILITY_USER",
						"EMPLOYEE_NUMBER", "RESPONSIBILITY_USER_NAME",
						"RESPONSIBILITY_DEPT", "RESPONSIBILITY_DEPT_NAME",
						"MAINTAIN_USER", "MAINTAIN_DEPT_NAME", "START_DATE",
						"SPECIALITY_DEPT" };
				String[] fieldDescs = { "�豸�������", "�豸רҵ����", "�豸רҵ����", "�豸����",
						"�豸�ͺ�", "�ص�ID", "�ص����", "�ص�����", "������Ա��ID", "������Ա�����",
						"����������", "���β��Ŵ���", "���β�������", "ʹ����", "ʹ�ò���", "��������",
						" ʵ�ﲿ��" };
				String fieldName = "";
				String javaField = "";
				String oldValue = "";
				String newValue = "";
				for (int i = 0; i < fieldNames.length; i++) {
					fieldName = fieldNames[i];
					javaField = StrUtil.getJavaField(fieldName);
					oldValue = String.valueOf(ReflectionUtil.getProperty(
							oldDTO, javaField));
					if (oldValue.equals("")) {
						oldValue = "��";
					}
					newValue = String.valueOf(ReflectionUtil.getProperty(
							newDTO, javaField));
					if (!oldValue.equals(newValue) && !newValue.equals("")) {
						correctContent.append(fieldDescs[i]);
						correctContent.append(":");
						correctContent.append(oldValue);
						correctContent.append("-->>");
						correctContent.append(newValue);
						correctContent.append(WorldConstant.ENTER_CHAR);
					}
				}
				logDTO.setCorrectContent(correctContent.toString());
			}else{
				logDTO = new AmsItemCorrectLogDTO();
				logDTO.setLogId(logId);
				logDTO.setBarcode(newDTO.getBarcode());
				//logDTO.setCorrectContent("");
				logDTO.setCreatedBy(userAccount.getUserId());
			}
		} catch (ReflectException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return logDTO;
	}

	/**
	 * ���ܣ�����̨��ά���ı䶯��ʷ
	 * 
	 * @return AmsItemCorrectLogDTO
	 * @throws QueryException
	 */
	private AmsItemInfoHistoryDTO getHisLogDTO() throws QueryException {
		AmsItemInfoHistoryDTO logDTO = new AmsItemInfoHistoryDTO();
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
		String currTime = CalendarUtil.getCurrCalendar(CAL_PATT_45);
		String remark = userAccount.getUsername() + "��" + currTime
				+ "ͨ��ʵ��̨��ά������";
		logDTO.setRemark(remark);
		logDTO.setBarcode(dto.getBarcode());
		logDTO.setAddressId(dto.getAddressId());
		logDTO.setItemCode(dto.getItemCode());
		logDTO.setResponsibilityDept(dto.getResponsibilityDept());
		logDTO.setResponsibilityUser(dto.getResponsibilityUser());
		logDTO.setOrderNo("ʵ��̨��ά������");
		logDTO.setOrderCategory("0");
		return logDTO;
	}

	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			ItemSpareModel modelProducer = (ItemSpareModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "��Ʒ����̨�ʵ����豸";
			String fileName = reportTitle + ".xls";
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);
			rule.setFieldMap(getFieldMap());
			CustomTransData custData = new CustomTransData();
			custData.setReportTitle(reportTitle);
			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			rule.setCalPattern(LINE_PATTERN);
			TransferFactory factory = new TransferFactory();
			DataTransfer transfer = factory.getTransfer(rule);
			transfer.transData();
			file = (File) transfer.getTransResult();
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataTransException(ex);
		}
		return file;
	}

	private Map getFieldMap() {
		Map fieldMap = new HashMap();
		fieldMap.put("COMPANY", "��˾");
		fieldMap.put("BARCODE", "�豸����");
		fieldMap.put("ITEM_NAME", "�豸����");
		fieldMap.put("ITEM_SPEC", "����ͺ�");
		fieldMap.put("ITEM_STATUS_NAME", "�豸״̬");
		fieldMap.put("IS_ABATE", "�Ƿ�ʧЧ");
		fieldMap.put("ITEM_UNIT", "������λ");
		fieldMap.put("START_DATE", "�������");
		fieldMap.put("RESPONSIBILITY_DEPT_NAME", "���β���");
		fieldMap.put("WORKORDER_OBJECT_CODE", "�ص����");
		fieldMap.put("WORKORDER_OBJECT_NAME", "�ص���");
		fieldMap.put("MANUFACTURER_CODE", "���̴���");
		fieldMap.put("MANUFACTURER_NAME", "����");
		fieldMap.put("MAINTAIN_USER", "ʹ����");
		fieldMap.put("SPECIALITY_DEPT", "ʵ�ﲿ��");
		fieldMap.put("USERNAME", "רҵ������");
		fieldMap.put("CONTENT_CODE", "Ŀ¼����");
		fieldMap.put("CONTENT_NAME", "Ŀ¼����");
		fieldMap.put("REMARK", "��ע");
		fieldMap.put("ITEM_CATEGORY", "�ʲ����");
		fieldMap.put("ITEM_CODE", "�ʲ�����");
		fieldMap.put("ITEM_QTY", "����");
		fieldMap.put("PRICE", "����");
		fieldMap.put("IS_TD", "�Ƿ�TD");
		return fieldMap;
	}

	public int checkItemStatus(String[] barcodes, String itemStatus)
			throws QueryException, SQLModelException {
		int count = 0;
		ItemSpareModel modelProducer = (ItemSpareModel) sqlProducer;
		for (int i = 0; i < barcodes.length; i++) {
			String barcode = barcodes[i];
			SQLModel sqlModel = modelProducer.getCheckItemStatusModel(barcode,
					itemStatus);
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			if (simp.hasResult()) {
				count++;
			}
		}
		return count;
	}

    public void logItemChgHistory(String [] barcodes){
        AmsItemInfoHistoryDAO historyDAO = new AmsItemInfoHistoryDAO(userAccount,null,conn);
        String orderURL = "/servlet/com.sino.ams.newasset.servlet.ItemSpareServlet"; //��Ҫ�ջ���ȷ�ϴ˴��Ƿ��е��ݲ������еĻ���д�뵥�ݵ���ϸ��Ϣ��ַ������Ӧ��Ϊ��

        for (int i = 0; i < barcodes.length; i++) {
            String barcode = barcodes[i];

            AmsItemInfoHistoryDTO historyDTO=new AmsItemInfoHistoryDTO();
            historyDTO.setOrderCategory("3");
            historyDTO.setOrderNo("");
            historyDTO.setCreatedBy(userAccount.getUserId());
//            historyDTO.setOrderDtlUrl(orderURL);
            historyDTO.setBarcode(barcode);
            historyDAO.setDTOParameter(historyDTO);
            historyDAO.recordHistory();
        }
    }}