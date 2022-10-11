package com.itheima.web.servlet;
/**
 * 替换httpServlet,根据最后一段请求路径分发方法
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    //根据最后一段请求路径分发方法
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
        String requestURI = req.getRequestURI();
        //获取最后一段路径,方法名
        int index = requestURI.lastIndexOf("/");
        String methodName = requestURI.substring(index + 1);
        //执行方法
        //通过反射获取字节码文件
        Class<? extends BaseServlet> cls = this.getClass();//this指调用方法的对象
        try {
            Method method = cls.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
