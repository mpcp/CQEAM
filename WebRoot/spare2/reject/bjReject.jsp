<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.dto.DTOSet" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.ams.constant.DictConstant" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.spare.dto.AmsItemTransHDTO" %>
<%@ page import="com.sino.ams.spare.dto.AmsItemTransLDTO" %>
<%@ page import="com.sino.ams.spare2.bean.SpareLookUpConstant" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%--
  User: yuyao
  Date: 2007-11-13
  Time: 22:56:04
--%>
<html>
<head><title>备件报废</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/RadioProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
    <script language="javascript" src="/WebLibary/js/TableProcess.js"></script>
    <script language="javascript" src="/flow/flow.js"></script>
    <script language="javascript" src="/WebLibary/js/json.js"></script>

</head>
<body topMargin=1 leftMargin=1 onload="initPage();">
<jsp:include page="/message/MessageProcess"/>
<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
    AmsItemTransHDTO dto = (AmsItemTransHDTO) request.getAttribute("AIT_HEADER");
    String action = parser.getParameter("act");
    DTOSet set = (DTOSet) request.getAttribute("AIT_LINES");
%>
<form action="/servlet/com.sino.ams.spare2.reject.servlet.AmsBjRejectServlet" name="mainForm" method="post">
<%--<jsp:include page="/flow/include.jsp" flush="true"/>--%>
<table width="100%" border="1" bordercolor="#9FD6FF" bgcolor="F2F9FF" id="table1" style="border-collapse: collapse">
    <tr>
        <td>
            <table width="100%" id="table2" border="0" cellspacing="1" bgcolor="#F2F9FF">
                <tr height="22">
                    <td width="10%" align="right">报废单据：</td>
                    <td width="15%"><input type="text" name="transNo" class="blueBorderGray" readonly
                                           style="width:100%"
                                           value="<%=dto.getTransNo()%>"></td>
                    <td width="10%" align="right">公司：</td>
                    <td width="20%"><input type="text" name="fromOrganizationName" class="blueBorderGray" readonly
                                           style="width:80%" value="<%=dto.getFromOrganizationName()%>">

                    </td>
                    <td align="right">类型：</td>
                    <td><input type="radio" name="attribute1" id="attribute1a" value="1" style="background-color:#F2F9FF" <%=dto.getAttribute1().equals("1")?"checked":""%>><label for="attribute1a">待修</label>
                    <input type="radio" name="attribute1" id="attribute1b" value="2" style="background-color:#F2F9FF" <%=dto.getAttribute1().equals("2")?"checked":""%>><label for="attribute1b">送修</label>
                    </td>
                </tr>
                <tr>
                    <td width="10%" align="right">创建人：</td>
                    <td width="10%"><input type="text" name="createdUser" class="blueBorderGray" readonly
                                           value="<%=dto.getCreatedUser()%>"></td>
                    <td width="10%" align="right">创建时间：</td>
                    <td width="8%"><input type="text" name="creationDate" class="blueBorderGray" readonly
                                          value="<%=dto.getCreationDate()%>"></td>
                    <td width="10%" align="right">单据状态：</td>
                    <td width="20%"><input type="text" name="transStatusName" class="blueBorderGray" readonly
                                           value="<%=dto.getTransStatusName()%>"></td>

                </tr>
                <tr>
                    <td align="right">备注：</td>
                    <td colspan="7"><textarea name="remark" rows="3" cols="" style="width:90%"
                                              class="blueBorder"><%=dto.getRemark()%></textarea>
                    
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<fieldset>
    <legend>
        <%
            //单据非完成状态并且当前用户是创建人才有操作权限
            if (!dto.getTransStatus().equals(DictConstant.COMPLETED) && dto.getCreatedBy().equals(user.getUserId())) {
        %>
        <img src="/images/eam_images/add_data.jpg" alt="添加数据" onclick="do_add();">
        <img src="/images/eam_images/delete_line.jpg" alt="删除行" onClick="do_Delete();">
        <img src="/images/eam_images/save.jpg" alt="保存" onClick="do_save()">
        <img src="/images/eam_images/submit.jpg" alt="提交" onClick="submitH()">

        <%
            }
        %>
        <img src="/images/eam_images/close.jpg" alt="关闭" onclick="window.close()">
    </legend>
    <script type="text/javascript">

        var columnArr = new Array("checkbox", "物料编码", "设备名称", "规格型号", "数量", "报废数量");
        var widthArr = new Array("3%", "12%", "15%", "20%", "8%", "8%");
        printTableHead(columnArr, widthArr);
    </script>
    <div style="width:100%;overflow-y:scroll;height:500px">
        <table width="100%" border="1" bordercolor="#9FD6FF" id="mtlTable" cellpadding="1" cellspacing="0">
            <%
                if (set == null || set.isEmpty()) {
            %>
            <tr height=20px style="display:none" onMouseMove="style.backgroundColor='#EFEFEF'"
                onMouseOut="style.backgroundColor='#FFFFFF'">
                <td align=center width=3%><input type="checkbox" name="subCheck" id="subCheck0" value=""></td>
                <td align=center width="12%">
                    <input class="noBorderGray" type="text" name="barcode" id="barcode0" style="width:100%"></td>
                <td width="15%" name="itemName" id="itemName0"></td>
                <td width="20%" name="itemSpec" id="itemSpec0"></td>
                <td width="8%"><input type="text" class="noBorderGray" name="onhandQty" id="onhandQty0" readonly
                                      style="width:100%;text-align:right"></td>
                <td width="8%"><input type="text" class="blueBorderYellow" name="quantity" id="quantity0"
                                      onblur="checkQty(this)"
                                      style="width:100%;text-align:right"></td>
                <td width=1% style="display:none"><input type="hidden" name="itemCode" id="itemCode0">
                    <input type="hidden" name="lineId" id="lineId0" value="">
                    <input type="hidden" name="storageId" id="storageId0" value="">
                    <input type="hidden" name="sourceId" id="sourceId0" value="">
                </td>

            </tr>
            <%
            } else {
                for (int i = 0; i < set.getSize(); i++) {
                    AmsItemTransLDTO lineDto = (AmsItemTransLDTO) set.getDTO(i);
            %>
            <tr height=20px onMouseMove="style.backgroundColor='#EFEFEF'"
                onMouseOut="style.backgroundColor='#FFFFFF'">
                <td align=center width=3%><input type="checkbox" name="subCheck" id="subCheck<%=i%>"
                                                 value="<%=lineDto.getLineId()%>"></td>
                <td align=center width="12%">
                    <input type="text" class="noBorderGray" name="barcode" id="barcode<%=i%>" style="width:100%"
                           value="<%=lineDto.getBarcode()%>">
                </td>
                <td width="15%" name="itemName" id="itemName<%=i%>"><%=lineDto.getItemName()%>
                </td>
                <td width="20%" name="itemSpec" id="itemSpec<%=i%>"><%=lineDto.getItemSpec()%>
                </td>
                <td width="8%"><input type="text" class="noBorderGray" name="onhandQty" id="onhandQty<%=i%>"
                                      style="width:100%;text-align:right"
                                      value="<%=lineDto.getOnhandQty()%>">
                </td>
                <td width="8%"><input type="text" class="blueBorderYellow" name="quantity" id="quantity<%=i%>"
                                      style="width:100%;text-align:right" onblur="checkQty(this)"
                                      value="<%=lineDto.getQuantity()%>">
                </td>
                <td width=1% style="display:none"><input type="hidden" name="itemCode" id="itemCode<%=i%>"
                                                         value="<%=lineDto.getItemCode()%>">
                    <input type="hidden" name="lineId" id="lineId<%=i%>" value="<%=lineDto.getLineId()%>">
                    <input type="hidden" name="storageId" id="storageId<%=i%>" value="<%=lineDto.getStorageId()%>">
                    <input type="hidden" name="sourceId" id="sourceId<%=i%>" value="<%=lineDto.getSourceId()%>">
                </td>

            </tr>
            <%
                    }
                }
                System.out.println("333333");

            %>
        </table>
    </div>
</fieldset>
<input type="hidden" name="act" value="<%=action%>">
<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
<input type="hidden" name="transId" value="<%=dto.getTransId()%>">
<input type="hidden" name="transType" value="<%=DictConstant.BJBF%>">
<input type="hidden" name="fromObjectNo" value="<%=dto.getFromObjectNo()%>">
<input type="hidden" name="transStatus" value="<%=dto.getTransStatus()%>">
<input type="hidden" name="objectCategory" value="76">

</form>
</body>
</html>
<script type="text/javascript">
function do_SelectObject() {
    var projects = getLookUpValues("<%=LookUpConstant.LOOK_UP_ASSETS_OBJECT%>", 48, 30, "organizationId=<%=user.getOrganizationId()%>&objectCategory=<%=DictConstant.INV_NORMAL%>");
    if (projects) {
        //            dto2Frm(projects[0], "form1");
        document.mainForm.fromObjectName.value = projects[0].workorderObjectName;
        document.mainForm.fromObjectNo.value = projects[0].workorderObjectNo;
        //        document.mainForm.toObjectLocation.value = projects[0].workorderObjectLocation;
    }
}
function do_add() {
    var attribute1 = getRadioValue("attribute1");
    var lookUpName = "<%=SpareLookUpConstant.BJBF_SPARE_INFO_DX%>";
    if(attribute1 == "2"){    //从送修数量中报废
        lookUpName = "<%=SpareLookUpConstant.BJBF_SPARE_INFO_SX%>";
    }
    var dialogWidth = 50;
    var dialogHeight = 30;
    var userPara = "objectNo=" + mainForm.fromObjectNo.value;
    //LOOKUP传参数 必须和DTO中一致
    var users = getSpareLookUpValues(lookUpName, dialogWidth, dialogHeight, userPara);
    var tab = document.getElementById("mtlTable")
    if (users) {
        var user = null;
        for (var i = 0; i < users.length; i++) {
            user = users[i];
            if (!isItemExist(user)) {
                appendDTORow(tab, user);
            }
            //增加整行信息时候用到的方法
        }
        if(document.getElementById("attribute1a").checked){
            document.getElementById("attribute1b").disabled = true;
        }else{
            document.getElementById("attribute1a").disabled = true;
        }

    }
}
function isItemExist(itemObj) {
    var retVal = false;
    var tabObj = document.getElementById("mtlTable");
    if (tabObj.rows) {
        var trObjs = tabObj.rows;
        var trCount = trObjs.length;
        var trObj = null;
        var itemValue = itemObj.barcode;
        var rowValue = null;
        for (var i = 0; i < trCount; i++) {
            trObj = trObjs[i];
            rowValue = trObj.cells[1].childNodes[0].value;
            if (itemValue == rowValue) {
                retVal = true;
            }
        }
    }
    return retVal;
}
function do_selectName() {
    var lookUpName = "<%=LookUpConstant.LOOK_UP_BF%>";
    var dialogWidth = 50;
    var dialogHeight = 30;
    var userPara = "objectCategory=" + mainForm.objectCategory.value;
    var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight, userPara);
    if (users) {
        var user = null;
        for (var i = 0; i < users.length; i++) {
            user = users[i];
            dto2Frm(user, "mainForm");
        }
    }
}
function do_Delete() {
    var tab = document.getElementById("mtlTable");
    deleteTableRow(tab, 'subCheck');
    var tab2 = document.getElementById("mtlTable");
    if(!tab2.length || tab2.length == 0){
        document.getElementById("attribute1a").disabled = false;
        document.getElementById("attribute1b").disabled = false;
    }
}
function do_save() {
    //        var value1 = mainForm.workorderObjectName.value;
    /*  if (value1 == "") {
      alert("请选择库存！");
  } else {*/
    if (!getvalues()) {
        return;
    }
    document.getElementById("attribute1a").disabled = false;
    document.getElementById("attribute1b").disabled = false;
    mainForm.transStatus.value = "<%=DictConstant.SAVE_TEMP%>";
    mainForm.act.value = "<%=WebActionConstant.SAVE_ACTION%>";
    mainForm.submit();
    /*}*/
}
function do_cancle() {
    mainForm.act.value = "<%=WebActionConstant.CANCEL_ACTION%>";
    mainForm.submit();
}
function do_submit() {
    var orgId = "<%=user.getOrganizationId()%>";
    var userId = "<%=user.getUserId()%>";
    var groupId = "<%=user.getCurrGroupId()%>";
    var procdureName = "备件报废流程";
    var flowCode = "";
    var paramObj = new Object();
    paramObj.orgId = orgId;
    paramObj.useId = userId;
    paramObj.groupId = groupId;
    paramObj.procdureName = procdureName;
    paramObj.flowCode = flowCode;
    paramObj.submitH = "submitH()";
    assign(paramObj);

}
function submitH() {

    if (!getvalues()) {
        return;
    }
    document.getElementById("attribute1a").disabled = false;
    document.getElementById("attribute1b").disabled = false;
    mainForm.transStatus.value = "<%=DictConstant.COMPLETED%>";
    mainForm.act.value = "<%=WebActionConstant.SUBMIT_ACTION%>";
    mainForm.submit();
    //        }

}
function getvalues() {
    var tab = document.getElementById("mtlTable");
    if (tab.rows.length == 0 || (tab.rows[0].style.display == 'none' && tab.rows.length == 1)) {
        alert("没有选择行数据，请选择行数据！");
        return false;
    }
    return true;
}

function checkQty(obj) {
    var val = obj.value;
    if (val == "") {
        obj.value = 0;
    } else if (isNaN(val)) {
        alert("请输入数字！");
        obj.value = "";
        obj.focus();
    } else if (Number(val) < 0) {
        alert("请输入不小于0的整数！");
        obj.value = "";
        obj.focus();
    } else {
        var tempId = obj.id.substring(8, obj.id.length);
        var onhandQty = Number(document.getElementById("onhandQty" + tempId).value);
        if (onhandQty < Number(val) ) {
            alert("报废数量不能大于现有数量，请重新输入！");
            obj.focus();
        }
    }
}
function initPage(){
    var act = "<%=action%>";
    if(act == "<%=WebActionConstant.DETAIL_ACTION%>"){
        document.getElementById("attribute1a").disabled = true;
        document.getElementById("attribute1b").disabled = true;
    }
}
</script>
