package com.sino.sinoflow.dto;

import com.sino.base.dto.CheckBoxDTO;

public class ProcedureLookUpDTO extends CheckBoxDTO {

	private String companyName = "";    //��˾
	private String procedureName = "";   //��������
    private String processTime = "";  //ƽ������ʱ��
	private String startDate = "";  //��ʼʱ��
	private String endDate = "";  //����ʱ��
    private String procedureNameOpt = ""; //����������
    private float hoursPerDay = 8;    //һ�칤��Сʱ��
    private String companyNameOpt = ""; //���������
    private String act = "";

    public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public String getProcessTime() {
		return processTime;
	}

	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

    public String getCompanyNameOpt() {
        return companyNameOpt;
    }

    public void setCompanyNameOpt(String companyNameOpt) {
        this.companyNameOpt = companyNameOpt;
    }

    public String getProcedureNameOpt() {

        return procedureNameOpt;
    }

    public void setProcedureNameOpt(String procedureNameOpt) {

        this.procedureNameOpt = procedureNameOpt;
    }

    public float getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(float hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }
}