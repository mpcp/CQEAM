package com.sino.soa.td.eip.fi.gl.sb_fi_gl_transaccountbalancesrv;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.4
 * Thu Oct 13 11:25:44 CST 2011
 * Generated source version: 2.1.4
 * 
 */
 
@WebService(targetNamespace = "http://eip.zte.com/fi/SB_FI_GL_TransAccountBalanceSrv", name = "SB_FI_GL_TransAccountBalanceSrv")
@XmlSeeAlso({com.sino.soa.td.eip.fi.gl.sb_fi_gl_transaccountbalancesrv.odi_variables.ObjectFactory.class,ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SBFIGLTransAccountBalanceSrv {

    @WebResult(name = "SB_FI_GL_TransAccountBalanceSrvProcessResponse", targetNamespace = "http://eip.zte.com/fi/SB_FI_GL_TransAccountBalanceSrv", partName = "payload")
    @WebMethod(action = "process")
    public SBFIGLTransAccountBalanceSrvProcessResponse process(
        @WebParam(partName = "payload", name = "SB_FI_GL_TransAccountBalanceSrvProcessRequest", targetNamespace = "http://eip.zte.com/fi/SB_FI_GL_TransAccountBalanceSrv")
        SBFIGLTransAccountBalanceSrvProcessRequest payload
    );
}
