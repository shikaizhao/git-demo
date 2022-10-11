package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService = new BrandServiceImpl();
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用查询的方法
        List<Brand> brands = brandService.selectAll();
        //把集合转为JSON数据
        String jsonString = JSON.toJSONString(brands);
        //设置响应数据格式
        response.setContentType("text/json;charset=utf-8");
        //发送响应数据
        response.getWriter().write(jsonString);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String jsonString = reader.readLine();
        Brand brand = JSON.parseObject(jsonString, Brand.class);
        brandService.update(brand);
        //设置响应数据格式
        response.setContentType("text/json;charset=utf-8");
        //发送响应数据
        response.getWriter().write("success");

    }
    //根据id删除
    public void delectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String jsonString = reader.readLine();
        Integer id = JSON.parseObject(jsonString, int.class);
        brandService.delectById(id);
        //设置响应数据格式
        response.setContentType("text/json;charset=utf-8");
        //发送响应数据
        response.getWriter().write("success");
    }
    //批量删除
    public void delectByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String jsonString = reader.readLine();
        int[] ids = JSON.parseObject(jsonString, int[].class);
        brandService.delectByIds(ids);
        //设置响应数据格式
        response.setContentType("text/json;charset=utf-8");
        //发送响应数据
        response.getWriter().write("success");

    }
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用方法执行
        PageBean<Brand> brandPageBean = brandService.selectByPage(currentPage, pageSize);
        //把集合转为JSON数据
        String jsonString = JSON.toJSONString(brandPageBean);
        //设置响应数据格式
        response.setContentType("text/json;charset=utf-8");
        //发送响应数据
        response.getWriter().write(jsonString);
    }
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //获取查询条件
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Brand brand = JSON.parseObject(params, Brand.class);
//        int currentPage = 1;
//        int pageSize = 5;
//        Brand brand = new Brand();
//        brand.setBrandName("华");
        //调用方法执行
        PageBean<Brand> brandPageBean = brandService.selectByPageAndCondition(currentPage, pageSize,brand);
        //把集合转为JSON数据
        String jsonString = JSON.toJSONString(brandPageBean);
        //设置响应数据格式
        response.setContentType("text/json;charset=utf-8");
        //发送响应数据
        response.getWriter().write(jsonString);
    }
}
