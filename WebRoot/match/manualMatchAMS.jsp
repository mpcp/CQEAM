<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.system.fixing.dto.EtsItemInfoDTO" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%--
  Created by HERRY.
  Date: 2007-11-26
  Time: 16:22:29
--%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
    <script type="text/javascript" language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script type="text/javascript" language="javascript" src="/WebLibary/js/RadioProcess.js"></script>
    <script type="text/javascript" language="javascript" src="/WebLibary/js/TableProcess.js"></script>
    <script type="text/javascript" language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" language="javascript" src="/WebLibary/js/LookUp.js"></script>
    <script type="text/javascript" language="javascript" src="/WebLibary/js/AssetsLookUp.js"></script>
    <style type="text/css">
        .finput {
            WIDTH: 100%;
            BORDER-RIGHT: 0px ridge;
            BORDER-TOP: 0px ridge;
            BORDER-LEFT: 0px ridge;
            BORDER-BOTTOM: 0px ridge;
            font-size: 12px;
        }

        .finput2 {
            WIDTH: 100%;
            BORDER-RIGHT: 0px ridge;
            BORDER-TOP: 0px ridge;
            BORDER-LEFT: 0px ridge;
            BORDER-BOTTOM: 0px ridge;
            font-size: 12px;
            text-align: center;
        }

        .finput3 {
            WIDTH: 100%;
            BORDER-RIGHT: 0px ridge;
            BORDER-TOP: 0px ridge;
            BORDER-LEFT: 0px ridge;
            BORDER-BOTTOM: 0px ridge;
            font-size: 12px;
            text-align: right;
        }
    </style>
    <% 
    	String titleText="";
    	String titleBar="";
    	SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
    	if ("Y".equalsIgnoreCase(user.getIsTd())) { 
    		titleText="EAM设备信息(TD)";
    		titleBar="EAM设备信息(TD)";
    	} else {
    		titleText="EAM设备信息";
    		titleBar="EAM设备信息";
    	}
    %>	
	<title><%=titleText %></title>
</head>
<%
    EtsItemInfoDTO dto = (EtsItemInfoDTO) request.getAttribute("AMS_HEADER");
    String countyOption = (String) request.getAttribute("COUNTY_OPTION");
    String act = StrUtil.nullToString(request.getParameter("act"));
%>
<body topmargin="0" leftmargin="0" onload="do_SetPageWidth();mainForm.key.focus();" onkeydown="autoExeFunction('do_query();')">
<jsp:include page="/public/exportMsg.jsp"/>
<form action="/servlet/com.sino.ams.match.servlet.ManualMatchAMS" name="mainForm" method="post">
    <script type="text/javascript">
        printTitleBar("<%=titleBar%>")
    </script>
    <%=WebConstant.WAIT_TIP_MSG%>


    <table border="0" width="100%" class="queryTable">
        <tr>
            <td width="15%" align="right">关键字：</td>
            <td width="20%"><input class="input_style1" type="text" name="key" value="<%=dto.getKey()%>" style="width:70%"></td>
            <td width="10%" align="right">部门：</td>
            <td width="20%"><input class="input_style1" type="text"  name="responsibilityDept" style="width:70%"
                                   value="<%=dto.getResponsibilityDept()%>"><a href="#" onClick="SelectDeptName(); "
                                                                               class="linka">[…]</a></td>
            <%--<td width="15%" align="right">专业：</td>--%>
            <%--<td width="20%" align="left">--%>
                <%--<select class="select_style1" name="itemCategory" style="width:70%"><%=request.getAttribute("ITEM_CATEGORY")%></select>--%>
            <%--</td>--%>
            <td width="15%" align="right">已盘点：</td>
            <td width="20%">
            	<select class="select_style1"  name="check" style="width: 70%">
                	<option value="N" <%=dto.getCheck().equals("N") ? "selected" : ""%>>全部</option>
					<option value="Y" <%=dto.getCheck().equals("Y") ? "selected" : ""%>>是</option>
                </select>
			</td>
        </tr>
        <tr>
            <td width="15%" align="right">责任人：</td>
            <td width="20%"><input class="input_style1" type="text" name="maintainUser" value="<%=dto.getMaintainUser()%>" style="width:70%"
                                   ><a href="#" onClick="do_SelectUser();" title="点击选择责任人"
                                                         class="linka">[…]</a></td>
            <td width="10%" align="right">地点：</td>
            <td width="20%"><input class="input_style1" readonly type="text" name="workorderObjectName" value="<%=dto.getWorkorderObjectName()%>"
                                   style="width:70%" ><a href="#" onClick="do_selectObject();"
                                                                           title="点击选择地点" class="linka">[…]</a></td>
            <td width="15%" align="right">设备名称：</td>
            <td width="20%"><input class="input_style1" type="text" name="itemName" value="<%=dto.getItemName()%>" style="width:70%"
                                   ></td>
        </tr>
        <tr>
            <td width="15%" align="right">规格型号：</td>
            <td width="20%"><input class="input_style1" type="text" name="itemSpec" value="<%=dto.getItemSpec()%>" style="width:70%"
                                   ></td>
            <td width="10%" align="right">项目：</td>
            <td width="20%"><input class="input_style1" type="text" name="prjName" value="<%=dto.getPrjName()%>"
                                   style="width:70%" ><a href="#" onClick="do_SelectProject();"
                                                                           title="点击选择地点" class="linka">[…]</a></td>
            <td width="35%" align="right" colspan="2">
                <img src="/images/eam_images/search.jpg" alt="查询" onclick="do_query()">
                <img src="/images/eam_images/export.jpg" id="queryImg" style="cursor:'hand'" onclick="do_export();" alt="导出到Excel">
            </td>
        </tr>

    </table>
    <input type="hidden" name="act" value="<%=act%>">
    <input type="hidden" name="tempRadio">
    <input type="hidden" name="workorderObjectNo" value="<%=dto.getWorkorderObjectNo()%>">
    <input type= "hidden" name = "prjId" value = "<%=dto.getPrjId()%>">

    <div id="aa" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:95px;left:0px;width:100%" class="crystalScroll">
        <table class="eamHeaderTable" border="1" width="350%" >
            <tr height="20">
                <td align="center" width="2%"><!--<input type="checkbox" name="titleCheck" class="headCheckbox"
                                                     onclick="checkAll(this.name,'subCheck');">--></td>
                <td align="center" width="6%">标签号</td>
                <td align="center" width="12%">设备名称</td>
                <td align="center" width="11%">规格型号</td>
                <td align="center" width="3%">责任人</td>
                <td align="center" width="5%">设备专业</td>
                <td align="center" width="10%">地点编号</td>
                <td align="center" width="10%">地点</td>
                <td align="center" width="10%">责任部门</td>
                <td align="center" width="10%">项目</td>
                <td align="center" width="3%">使用人</td>
                <td align="center" width="5%">启用日期</td>
                <td align="center" width="6%">目录代码</td>
                <td align="center" width="6%">目录描述</td>
            </tr>
        </table>
    </div>
    <div style="overflow:scroll;position:absolute;top:118px;left:0px;height:72%;width:100%"
         align="left" onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
        <table width="350%" border="1" bordercolor="#666666" id="dataTable"
               style="TABLE-LAYOUT:fixed;word-break:break-all">
            <%
                RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
                Row row = null;
                int size = 0;
                if (rows != null && rows.getSize() > 0) {
                    size = rows.getSize();
                    for (int i = 0; i < size; i++) {
                        row = rows.getRow(i);

            %>
            <tr onclick="executeClick(this);" onMouseMove="style.backgroundColor='#EFEFEF'"
                onMouseOut="style.backgroundColor='#ffffff'">
                <td height="22" width="2%" align="center"><input type="radio" name="subCheck"
                                                                 value="<%=row.getValue("SYSTEMID").toString()%>"></td>
                <td height="22" width="6%"><input readonly class="finput2" value="<%=row.getValue("BARCODE")%>"></td>

                <td height="22" width="12%"><input readonly class="finput" value="<%=row.getValue("ITEM_NAME")%>"></td>
                <td height="22" width="11%"><input readonly class="finput" value="<%=row.getValue("ITEM_SPEC")%>"></td>
                <td height="22" width="3%"><input readonly class="finput"
                                                  value="<%=row.getValue("RESPONSIBILITY_USER_NAME")%>"></td>
                 <td height="22" width="5%"><input readonly class="finput" value="<%=row.getValue("ITEM_CATEGORY")%>">
                </td>
                <td height="22" width="10%"><input readonly class="finput2"
                                                   value="<%=row.getValue("WORKORDER_OBJECT_CODE")%>"></td>
                <td height="22" width="10%"><input readonly class="finput"
                                                   value="<%=row.getValue("WORKORDER_OBJECT_NAME")%>"></td>
                <td height="22" width="10%"><input readonly class="finput"
                                                   value="<%=row.getValue("RESPONSIBILITY_DEPT_NAME")%>"></td>
                <td height="22" width="10%"><input readonly class="finput"
                                                   value="<%=row.getValue("PROJECT_NAME")%>"></td>
                <td height="22" width="3%"><input readonly class="finput" value="<%=row.getValue("MAINTAIN_USER")%>">
                </td>
                <td height="22" width="5%"><input readonly class="finput2" value="<%=row.getValue("START_DATE")%>"></td>
                <td height="22" width="6%"><input readonly class="finput2" value="<%=row.getValue("CONTENT_CODE")%>"></td>
                <td height="22" width="6%"><input readonly class="finput2" value="<%=row.getValue("CONTENT_NAME")%>"></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</form>
<div style="position:absolute;bottom:1px;left:0; right:20px"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
    <jsp:include page="/message/MessageProcess"/>
</body>

<script type="text/javascript">
window.attachEvent("onresize",do_SetPageWidth);

function init() {
    document.getElementById("scrollTb").height = document.getElementById("dataTable").offsetHeight;
}
function do_query() {
    document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    document.forms[0].act.value = "<%=WebActionConstant.QUERY_ACTION%>"
    document.forms[0].submit();
}

function do_export() {
    document.forms[0].act.value = "<%=WebActionConstant.EXPORT_ACTION%>"
    document.forms[0].submit();
    
    openExportMsgDiv();
}

function do_selectObject() {
    var isCheck=document.getElementById("check").value;
    var obj = getLookUpValues("LOOK_UP_ASSETS_OBJECT", 48, 30, "organizationId=<%=user.getOrganizationId()%>&isTd="+isCheck);
    if (obj) {
        document.mainForm.workorderObjectName.value = obj[0].workorderObjectName;
        document.mainForm.workorderObjectNo.value = obj[0].workorderObjectNo;
    } else {
        document.mainForm.workorderObjectName.value = "";
        document.mainForm.workorderObjectNo.value = "";
    }
}
function do_SelectUser() {
    //var projects = getLookUpValues("LOOK_UP_PERSON", 48, 30, "organizationId=<%=user.getOrganizationId()%>");
    //if (projects) {
    //    document.mainForm.maintainUser.value = projects[0].executeUserName;
    //} else {
    //    document.mainForm.maintainUser.value = "";
   // }
    var lookUpName = "LOOK_UP_PERSON";
	var dialogWidth = 47;
	var dialogHeight = 28;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if(objs){
		var obj = objs[0];
		mainForm.maintainUser.value = obj["userName"];
	}
}
function do_SelectProject(){
	var projects = getLookUpValues("LOOKUP_PROJECT", 48, 30, "organizationId=<%=user.getOrganizationId()%>");
    if (projects) {
        document.mainForm.prjName.value = projects[0].prjName;
        document.mainForm.prjId.value = projects[0].prjId;
    } else {
        document.mainForm.prjName.value = "";
        document.mainForm.prjId.value = "";
    }
}

function matchIt() {
    if (!getRadioValue("subCheck", ";")) {
        alert("请选择标签号后，再执行本操作！");
        return false;
    }
    var radioObj = parent.misInfo.mainForm.assetId;
    if (radioObj == null || radioObj == '')
    {
        alert('请选择资产后再操作！');
        return false;
    }
    var radioCode;
    if (radioObj.length) {
        for (var i = 0; i < radioObj.length; i++) {
            if (radioObj[i].checked) {
                radioCode = radioObj[i].value;
                break;
            }
        }
    } else {
        if (radioObj.checked) {
            radioCode = radioObj.value;
        }
    }
    if (!radioCode) {
        alert("请选择一个设备后，再执行本操作！");
        return false;
    }
    do_verify();

    return true;
}

function match() {
    var j = getRadioCount("subCheck");
    j = (j == null) ? "0" : j;
    var assetId = window.parent.misInfo.getRadioValue("assetId");
    var num = assetId.split(";")[1];
    var proviceCode = "<%=user.getCompanyCode()%>".substring(0, 2);
<%--if (proviceCode == "42") {--%>
<%--document.forms[0].tempRadio.value = assetId.split(";")[0];--%>
<%--document.forms[0].act.value = "<%=WebActionConstant.SAVE_ACTION%>";--%>
<%--document.forms[0].submit();--%>
<%--} else {--%>
    if (j > num) {
        alert("对不起，左边选定的设备数量大于右边选定的设备数量，无法匹配！");
        return;
    } else {
        document.forms[0].tempRadio.value = assetId.split(";")[0];
        document.forms[0].act.value = "<%=WebActionConstant.SAVE_ACTION%>";
        document.forms[0].submit();
    }
//    }
}
function validateData() {
    if (parent.document.getElementById("working").value == '1') {
        alert('正在处理中，请稍候...');
        return false;
    }
    var j = getCheckedBoxCount("subCheck");
    //systemid
    if (j != 1) {
        alert("左边应选择并只能选择一条记录！");
        return false;
    }

    var assetId = window.parent.misInfo.getRadioValue("assetId");
    //    var sysid = window.parent.itemInfoView.manualL.getCheckBoxValue('sysid');
    //    var radioValue = window.parent.systemInfoView.manualR.getRadioValue('assid');
    if (assetId == "") {
        alert("右边应选择一条记录！");
        return false;
    }
    return true;
}
function matchByLocation() {
    if (validateData()) {
        if (!confirm("确定按地点匹配吗？"))
            return false;
        matchByCondition("MatchByLocation")
    }
}
function matchByCounty() {
    if (validateData()) {
        if (!confirm("确定按县匹配吗？"))
            return false;
        matchByCondition("MatchByCounty");
    }
}

function matchByCity() {
    if (validateData()) {
        if (!confirm("确定按市匹配吗？"))
            return false;
        matchByCondition("MatchByCity");
    }
}

function matchByCondition(condition) {
    var radioValue = parent.misInfo.getRadioValue('assetId');
    var arr = radioValue.split(';');
    var assid = arr[0];
    var systemid = getCheckBoxValue('subCheck');
    var url = "/match/wait.jsp?act=" + condition + "&systemid=" + systemid + "&assetId=" + assid;
    var popscript = "dialogWidth:20;dialogHeight:7.5;center:yes;status:no;scroll:no";
    parent.document.getElementById("working").value = 1;
    window.showModalDialog(url, "", popscript);
    parent.document.getElementById("working").value = 0;
    do_query();
    parent.misInfo.do_query();
}

window["onscroll"] = function() {
    if (document.getElementById('scrollDiv')) {
        document.getElementById('scrollDiv').style.left = document.body.scrollLeft + document.getElementById("tb").offsetWidth - 18 + "px";
    }
}
window["onresize"] = function() {
    if (document.getElementById('scrollDiv')) {
        document.getElementById('scrollDiv').style.left = document.body.scrollLeft + document.getElementById("tb").offsetWidth - 18 + "px";
    }
}


function SelectDeptName() {
    var url = "/servlet/com.sino.ams.bean.AMSLookUpServlet?lookUpName=<%=LookUpConstant.LOOK_UP_MIS_DEPT%>";
    var popscript = "dialogWidth:47.5;dialogHeight:30;center:yes;status:no;scrollbars:no";
    var vendorNames = window.showModalDialog(url, null, popscript);
    if (vendorNames) {
        document.forms[0].responsibilityDept.value = vendorNames[0].deptName;
    }
}

var xmlHttp;
function do_verify() {
    var url = "";
    createXMLHttpRequest();
    var systemId = getRadioValue("subCheck");//getCheckedBoxValue("subCheck");
    var assetId = window.parent.misInfo.getRadioValue("assetId");
    url = "/servlet/com.sino.ams.match.servlet.ManualMatchAMS?act=VERIFY&systemId=" + systemId + "&assetId=" + assetId;
    xmlHttp.onreadystatechange = handleReadyStateChange;
    xmlHttp.open("post", url, true);
    xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xmlHttp.send(null);
}

function createXMLHttpRequest() {     //创建XMLHttpRequest对象
    try {
        xmlHttp = new ActiveXObject('Msxml2.XMLHTTP');
    } catch(e) {
        try {
            xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');
        } catch(e) {
            try {
                xmlHttp = new XMLHttpRequest();
            } catch(e) {
                alert("创建XMLHttpRequest对象失败！");
            }
        }
    }
}

function handleReadyStateChange() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            if (xmlHttp.responseText == 'Y') {
                match();      
            } else {
                alert("该条码在MIS上已存在，不能和MIS上其他条码进行匹配！");
            }
        } else {
            alert(xmlHttp.status);
        }
    }
}

</script>
</html>
