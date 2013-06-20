<%--
  Created by HERRY.
  Date: 2008-3-17
  Time: 15:50:18
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.DictConstant" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<html>
<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
    String url = "/servlet/com.sino.ams.spare2.returnBj.servlet.BjReturnRepairServlet";
%>
<head><title>备件送修返还</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/TableProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
</head>
<body>
<jsp:include page="/message/MessageProcess"/>
<form name="mainForm" method="POST" action="<%=url%>">
    <script type="text/javascript">
        printTitleBar("备件送修返还");
    </script>
    <input type="hidden" name="flexValueId" value="">
    <input type="hidden" name="transType" value="<%=DictConstant.BJFH%>">
    <input type="hidden" name="transId" value="">
    <input type="hidden" name="act" value="">
    <input type="hidden" name="toObjectNo" value="<%=parser.getParameter("toObjectNo")%>">
    <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="0" class="queryHeadBg">
        <tr>
            <td width="10%" height="22" align="right">单据编号：</td>
            <td width="17%" height="22"><input type="text" name="transNo" style="width:100%"
                                               value="<%=parser.getParameter("transNo")%>"></td>
            <td width="10%" height="22" align="right">送修仓库：</td>
            <td width="17%" height="22"><input type="text" name="toObjectName" class="readonlyInput" readonly style="width:80%"
                                               value="<%=parser.getParameter("toObjectName")%>">
                <a href="#" onClick="do_SelectObject();" title="点击选择仓库"
                   class="linka">[…]</a></td>
            <td width="10%" height="22" align="right"></td>
            <td width="17%" height="22"><%--<select name="fromObjectNo" style="width:100%"><%=request.getAttribute(WebAttrConstant.INV_OPTION)%></select>--%></td>
        </tr>
        <tr>
            <td height="22" align="right">创建日期：</td>
            <td height="22"><input type="text" name="fromDate" value="<%=parser.getParameter("fromDate")%>"
                                   style="width:80%" title="点击选择开始日期" readonly class="readonlyInput"
                                   onclick="gfPop.fStartPop(fromDate, toDate)">
                <img src="/images/calendar.gif" alt="点击选择开始日期" onclick="gfPop.fStartPop(fromDate, toDate)">
            </td>
            <td height="22" align="right">到</td>
            <td height="22"><input type="text" name="toDate" value="<%=parser.getParameter("toDate")%>"
                                   style="width:80%" title="点击选择截止日期" readonly class="readonlyInput"
                                   onclick="gfPop.fEndPop(fromDate, toDate)">
                <img src="/images/calendar.gif" alt="点击选择截止日期" onclick="gfPop.fEndPop(fromDate, toDate)">
            </td>
            <td height="22" colspan="2" align="right"><img src="/images/eam_images/search.jpg" onclick="do_Search();"
                                                           alt="查询">&nbsp;&nbsp;<img src="/images/eam_images/new.jpg"
                                                                                     onclick="do_Create();"></td>
        </tr>
    </table>

    <script type="text/javascript">
        var columnArr = new Array("单据编号", "送修仓库", "创建人", "创建日期", "单据状态");
        var widthArr = new Array("20%", "25%", "10%", "10%", "10%");
        printTableHead(columnArr, widthArr);

    </script>
    <div style="overflow-y:scroll;left:1px;width:100%;height:360px">
        <table width="100%" border="1" bordercolor="#666666">
            <%
                if (rows != null && rows.getSize() > 0) {
                    Row row = null;
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'"
                onMouseOut="style.backgroundColor='#FFFFFF'"
                onclick="do_ShowDetail('<%=row.getValue("TRANS_ID")%>')">
                <td width="20%" align="center"><%=row.getValue("TRANS_NO")%>
                </td>
                <td width="25%"><%=row.getValue("TO_OBJECT_NAME")%>
                </td>
                <td width="10%" align="left"><%=row.getValue("CREATED_USER")%>
                </td>
                <td width="10%" align="center"><%=String.valueOf(row.getValue("CREATION_DATE"))%>
                </td>
                <td width="10%" align="center"><%=row.getValue("ORDER_STATUS_NAME")%>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</form>
<div style="left:0; right:20px"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>
</body>
<%=WebConstant.WAIT_TIP_MSG%>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>

<script language="javascript">
    function do_SelectObject() {
        var projects = getLookUpValues("<%=LookUpConstant.LOOK_UP_ASSETS_OBJECT%>", 48, 30, "organizationId=<%=user.getOrganizationId()%>&objectCategory=<%=DictConstant.INV_NORMAL%>");
        if (projects) {
            //            dto2Frm(projects[0], "form1");
            document.mainForm.toObjectName.value = projects[0].workorderObjectName;
            document.mainForm.toObjectNo.value = projects[0].workorderObjectNo;
            //        document.mainForm.toObjectLocation.value = projects[0].workorderObjectLocation;
        }
    }
    function do_Search() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        document.mainForm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        document.mainForm.submit();
    }

    function do_Create() {
        var transType = document.mainForm.transType.value;
        var url = "<%=url%>";
        url += "?act=<%=WebActionConstant.NEW_ACTION%>";
        var popscript = "width=1020,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=yes,location=no, status=yes";
        window.open(url, "xgrkOrder", popscript);
    }

    function do_ShowDetail(primaryKey) {
        //	document.mainForm.flexValueId.value = primaryKey;
    <%--document.mainForm.act.value = "<%=WebActionConstant.DETAIL_ACTION%>";--%>
        var transType = document.mainForm.transType.value;
        var url = "<%=url%>";
        url += "?act=<%=WebActionConstant.DETAIL_ACTION%>&transId=" + primaryKey;
        var popscript = "width=1020,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=yes,location=no, status=yes";
        window.open(url, "bjsxfhOrder", popscript);
    }
</script>