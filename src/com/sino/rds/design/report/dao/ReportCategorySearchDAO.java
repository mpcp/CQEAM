package com.sino.rds.design.report.dao;

import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.rds.appbase.dao.RDSBaseDAO;

import java.sql.Connection;

public class ReportCategorySearchDAO extends RDSBaseDAO {

    public ReportCategorySearchDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }
}