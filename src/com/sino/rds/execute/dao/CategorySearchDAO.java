package com.sino.rds.execute.dao;

import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.rds.appbase.dao.RDSBaseDAO;

import java.sql.Connection;

public class CategorySearchDAO extends RDSBaseDAO {

    public CategorySearchDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }
}