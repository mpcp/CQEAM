package com.sino.hn.todo.cxf.service.todoservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.1.4
 * Thu Oct 20 17:27:28 CST 2011
 * Generated source version: 2.1.4
 * 
 */
 
@WebService(targetNamespace = "http://service.todo.mochasoft.com/TodoService", name = "TodoServicePortType")
@XmlSeeAlso({ObjectFactory.class,com.sino.hn.todo.cxf.beans.ObjectFactory.class})
public interface TodoServicePortType {

    @ResponseWrapper(localName = "saveCloseResponse", targetNamespace = "http://service.todo.mochasoft.com/TodoService", className = "com.mochasoft.todo.service.todoservice.SaveCloseResponse")
    @RequestWrapper(localName = "saveClose", targetNamespace = "http://service.todo.mochasoft.com/TodoService", className = "com.mochasoft.todo.service.todoservice.SaveClose")
    @WebResult(name = "out", targetNamespace = "http://service.todo.mochasoft.com/TodoService")
    @WebMethod
    public java.lang.String saveClose(
        @WebParam(name = "in0", targetNamespace = "http://service.todo.mochasoft.com/TodoService")
        com.sino.hn.todo.cxf.beans.ArrayOfClose in0
    );

    @ResponseWrapper(localName = "saveOpenResponse", targetNamespace = "http://service.todo.mochasoft.com/TodoService", className = "com.mochasoft.todo.service.todoservice.SaveOpenResponse")
    @RequestWrapper(localName = "saveOpen", targetNamespace = "http://service.todo.mochasoft.com/TodoService", className = "com.mochasoft.todo.service.todoservice.SaveOpen")
    @WebResult(name = "out", targetNamespace = "http://service.todo.mochasoft.com/TodoService")
    @WebMethod
    public java.lang.String saveOpen(
        @WebParam(name = "in0", targetNamespace = "http://service.todo.mochasoft.com/TodoService")
        com.sino.hn.todo.cxf.beans.ArrayOfOpen in0
    );

    @ResponseWrapper(localName = "saveCancelResponse", targetNamespace = "http://service.todo.mochasoft.com/TodoService", className = "com.mochasoft.todo.service.todoservice.SaveCancelResponse")
    @RequestWrapper(localName = "saveCancel", targetNamespace = "http://service.todo.mochasoft.com/TodoService", className = "com.mochasoft.todo.service.todoservice.SaveCancel")
    @WebResult(name = "out", targetNamespace = "http://service.todo.mochasoft.com/TodoService")
    @WebMethod
    public java.lang.String saveCancel(
        @WebParam(name = "in0", targetNamespace = "http://service.todo.mochasoft.com/TodoService")
        com.sino.hn.todo.cxf.beans.ArrayOfCancel in0
    );

    @ResponseWrapper(localName = "updateTodoResponse", targetNamespace = "http://service.todo.mochasoft.com/TodoService", className = "com.mochasoft.todo.service.todoservice.UpdateTodoResponse")
    @RequestWrapper(localName = "updateTodo", targetNamespace = "http://service.todo.mochasoft.com/TodoService", className = "com.mochasoft.todo.service.todoservice.UpdateTodo")
    @WebResult(name = "out", targetNamespace = "http://service.todo.mochasoft.com/TodoService")
    @WebMethod
    public java.lang.String updateTodo(
        @WebParam(name = "in0", targetNamespace = "http://service.todo.mochasoft.com/TodoService")
        java.lang.String in0,
        @WebParam(name = "in1", targetNamespace = "http://service.todo.mochasoft.com/TodoService")
        java.lang.String in1,
        @WebParam(name = "in2", targetNamespace = "http://service.todo.mochasoft.com/TodoService")
        com.sino.hn.todo.cxf.service.todoservice.ArrayOfString in2
    );

    @ResponseWrapper(localName = "deleteTodoResponse", targetNamespace = "http://service.todo.mochasoft.com/TodoService", className = "com.mochasoft.todo.service.todoservice.DeleteTodoResponse")
    @RequestWrapper(localName = "deleteTodo", targetNamespace = "http://service.todo.mochasoft.com/TodoService", className = "com.mochasoft.todo.service.todoservice.DeleteTodo")
    @WebResult(name = "out", targetNamespace = "http://service.todo.mochasoft.com/TodoService")
    @WebMethod
    public java.lang.String deleteTodo(
        @WebParam(name = "in0", targetNamespace = "http://service.todo.mochasoft.com/TodoService")
        java.lang.String in0,
        @WebParam(name = "in1", targetNamespace = "http://service.todo.mochasoft.com/TodoService")
        com.sino.hn.todo.cxf.service.todoservice.ArrayOfString in1
    );
}