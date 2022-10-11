package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.service.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //获得请求体
        BufferedReader reader = request.getReader();
        //遍历得到数据
        String jsonString = reader.readLine();
        //把JSON数据转成Java对象
        Brand brand = JSON.parseObject(jsonString, Brand.class);
        //调用添加方法
        brandService.add(brand);
        //发送响应数据
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
