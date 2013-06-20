package com.sino.sinoflow.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: 工程属性 SfProject</p>
* <p>Description: 程序自动生成DTO数据传输对象</p>
* <p>Copyright: 北京思诺博信息科技有限公司 Copyright (c) 2006</p>
* <p>Company: 北京思诺博信息科技有限公司</p>
* @author 唐明胜
* @version 1.0
*/

public class SfProjectDTO extends CheckBoxDTO{

	private int projectId = 0;
	private String projectName = "";
	private int createdBy = -1;
	private String creationDate = "";
	private String lastUpdatedBy = "";
	private String lastUpdateDate = "";
	private String enabled = "";
	private String effectiveDate = "";
	private String version = "";
	private String description = "";
    private String filename = "";

    public SfProjectDTO() {
		super();
	}

	/**
	 * 功能：设置工程属性属性 工程 ID
	 * @param projectId String
	 */
	public void setProjectId(int projectId){
		this.projectId = projectId;
	}

	/**
	 * 功能：设置工程属性属性 工程名称
	 * @param projectName String
	 */
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}

	/**
	 * 功能：设置工程属性属性 创建人
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * 功能：设置工程属性属性 创建日期
	 * @param creationDate String
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * 功能：设置工程属性属性 修改人
	 * @param lastUpdatedBy String
	 */
	public void setLastUpdatedBy(String lastUpdatedBy){
		this.lastUpdatedBy = lastUpdatedBy;
	}

	/**
	 * 功能：设置工程属性属性 修改日期
	 * @param lastUpdateDate String
	 */
	public void setLastUpdateDate(String lastUpdateDate){
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * 功能：设置工程属性属性 生效
	 * @param enabled String
	 */
	public void setEnabled(String enabled){
		this.enabled = enabled;
	}

	/**
	 * 功能：设置工程属性属性 生效日期
	 * @param effectiveDate String
	 */
	public void setEffectiveDate(String effectiveDate){
		this.effectiveDate = effectiveDate;
	}

	/**
	 * 功能：设置工程属性属性 版本号
	 * @param version String
	 */
	public void setVersion(String version){
		this.version = version;
	}

	/**
	 * 功能：设置工程属性属性 描述
	 * @param description String
	 */
	public void setDescription(String description){
		this.description = description;
	}

    /**
     * 功能：设置工程属性属性 〉文件名
     * @param filename String
     */
    public void setFilename(String filename){
        this.filename = filename;
    }

	/**
	 * 功能：获取工程属性属性 工程 ID
	 * @return String
	 */
	public int getProjectId() {
		return this.projectId;
	}

	/**
	 * 功能：获取工程属性属性 工程名称
	 * @return String
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * 功能：获取工程属性属性 创建人
	 * @return String
	 */
	public int getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * 功能：获取工程属性属性 创建日期
	 * @return String
	 */
	public String getCreationDate() {
		return this.creationDate;
	}

	/**
	 * 功能：获取工程属性属性 修改人
	 * @return String
	 */
	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	/**
	 * 功能：获取工程属性属性 修改日期
	 * @return String
	 */
	public String getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	/**
	 * 功能：获取工程属性属性 生效
	 * @return String
	 */
	public String getEnabled() {
		return this.enabled;
	}

	/**
	 * 功能：获取工程属性属性 生效日期
	 * @return String
	 */
	public String getEffectiveDate() {
		return this.effectiveDate;
	}

	/**
	 * 功能：获取工程属性属性 版本号
	 * @return String
	 */
	public String getVersion() {
		return this.version;
	}

	/**
	 * 功能：获取工程属性属性 描述
	 * @return String
	 */
	public String getDescription() {
		return this.description;
	}

    /**
     * 功能：获取工程属性属性 描述
     * @return String
     */
    public String getFilename() {
        return this.filename;
    }
}