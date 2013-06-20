package com.sino.ams.workorder.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsWorkorderDTO;
import com.sino.ams.workorder.model.TrunListPrintQueryModel;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

public class TrunListPrintQueryDAO extends BaseDAO {
	private SfUserDTO user = null;

	public TrunListPrintQueryDAO(SfUserDTO userAccount, EtsWorkorderDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        user = userAccount;
        initSQLProducer(userAccount, dtoParameter);
    }
	
	@Override
	protected void initSQLProducer(BaseUserDTO dto, DTO dtoParameter) {
		// TODO Auto-generated method stub
		EtsWorkorderDTO ffDTO=(EtsWorkorderDTO) dtoParameter;
//        super.sqlProducer = new TrunListQueryModel((SfUserDTO)userAccount, ffDTO);
        super.sqlProducer = new TrunListPrintQueryModel((SfUserDTO)userAccount, ffDTO);
        
	}
	
	
	/**
	 * 功能：获取转资条码查询Excel文件
	 * @return
	 * @throws DataTransException
	 */
	public File exportFile(EtsWorkorderDTO dto,String opinion,List partBarCode) throws DataTransException {
		File file = null;
		try {
			SQLModel sqlModel;
			if(opinion.equals("part")){
				 dto.setOpinionType("part");
				 dto.setPartBarcode(partBarCode);
				 sqlModel = sqlProducer.getPageQueryModel();
			}else{
				 sqlModel = sqlProducer.getPageQueryModel();
			}
			String reportTitle = reportTitle = "转资条码查询";
			String fileName = reportTitle + ".xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);			
			rule.setFieldMap(getFieldMap(dto));
			CustomTransData custData = new CustomTransData();
			custData.setReportTitle(reportTitle);
			custData.setReportPerson(user.getUsername());
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
	
	private Map getFieldMap(EtsWorkorderDTO dto){
		Map fieldMap = new HashMap();
		fieldMap.put("BARCODE", "标签号");
		fieldMap.put("PRINT_NUM", "历史打印次数");
		fieldMap.put("ASSETS_LOCATION_CODE", "地点编号");
        fieldMap.put("ASSETS_LOCATION", "地点简称");
		fieldMap.put("SEGMENT1", "项目编号");
        fieldMap.put("NAME", "项目名称");
        fieldMap.put("ORG_NAME", "公司");
        
        
        fieldMap.put("ITEM_NAME", "资产名称");
        fieldMap.put("ITEM_SPEC", "规格型号");
        fieldMap.put("ITEM_QTY", "数量");
        fieldMap.put("ITEM_CATEGORY_DESC", "资产类别");
        fieldMap.put("DEPT_NAME", "责任部门");
        fieldMap.put("USER_NAME", "责任人");
        
        fieldMap.put("MAINTAIN_DEPT", "使用部门");
        fieldMap.put("MAINTAIN_USER", "使用人");
        
        fieldMap.put("CREATION_DATE", "创建日期");
        fieldMap.put("LAST_UPDATE_DATE", "最近修改日期");
        
        return fieldMap;
	}
}
