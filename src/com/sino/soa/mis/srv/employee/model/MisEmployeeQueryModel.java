package com.sino.soa.mis.srv.employee.model;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.soa.mis.srv.employee.dto.SBHRHRSrvEmployeeInfoDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-9-7
 * Time: 16:48:31
 * To change this template use File | Settings | File Templates.
 */
public class MisEmployeeQueryModel extends AMSSQLProducer {

	 public MisEmployeeQueryModel(SfUserDTO userAccount, SBHRHRSrvEmployeeInfoDTO dtoParameter) {
	        super(userAccount, dtoParameter);
	    }

	 public void setServletConfig(ServletConfigDTO servletConfig) {
		 super.setServletConfig(servletConfig);
	 }

	 public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();

        	SBHRHRSrvEmployeeInfoDTO dto = (SBHRHRSrvEmployeeInfoDTO) dtoParameter;
                String sqlStr = "SELECT " +
                		"AME.USER_NAME," +
                		"AME.EMPLOYEE_NUMBER," +
                		"AME.HR_DEPT_NAME," +
                		"AME.DEPT_CODE," +
                		"AME.ENABLED \n" +
                		"FROM " +
                		"AMS_MIS_EMPLOYEE AME \n" +
                		"WHERE \n"+
                		"AME.USER_NAME LIKE dbo.NVL(?, AME.USER_NAME)\n"+
                		"AND AME.HR_DEPT_NAME LIKE dbo.NVL(?, AME.HR_DEPT_NAME)\n"+
                		"AND AME.DEPT_CODE = dbo.NVL(?, AME.DEPT_CODE)\n"+
                		"AND AME.EMPLOYEE_NUMBER = dbo.NVL(?, AME.EMPLOYEE_NUMBER)";
                sqlArgs.add(dto.getFullName());
                sqlArgs.add(dto.getOrganization());
                sqlArgs.add(dto.getDeptCode());
                sqlArgs.add(dto.getEmployeeNumber());

                sqlModel.setSqlStr(sqlStr);
                sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

}