package com.sino.ams.task.service.soa.td.read;

import com.sino.ams.task.service.soa.AbstractTaskSOAService;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.soa.service.TDSrvDAO;

import java.sql.Connection;

/**
 * <p>标题: SinoEAM中国移动资产管理系统后台同步任务：业务服务层对象</p>
 * <p>描述: 通过SOA服务读取TD系统项目信息</p>
 * <p>版权: 根据中华人民共和国相关法律以及中华人民共和国加入的相关国际公约审定</p>
 * <p>开发商: 北京思诺博信息技术有限公司</p>
 *
 * @author 唐明胜
 * @version 1.0
 */
public class TDProjectReadService extends AbstractTaskSOAService {

    /**
     * <p>功能说明：从TD系统读取项目信息到EAM系统：</p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          同步数据出错时抛出数据处理异常
     */
    public void readTDProjects() throws DataHandleException {
        Connection conn = null;
        try {
            conn = getDBConnection();
            initTaskExecutor(conn);
            TDSrvDAO srvDAO = new TDSrvDAO();
            srvDAO.synProjectInfo(conn, taskExecutor);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            closeDBConnection(conn);
        }
    }
}
