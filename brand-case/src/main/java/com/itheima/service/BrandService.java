package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有
     * @return
     */
    List<Brand> selectAll();

    /**
     * 添加
     * @param brand
     */
    void add(Brand brand);
    List<Brand> selectByIdBrands(int id);

    /**
     * 修改
     * @param brand
     */
    void update(Brand brand);

    /**
     * 根据id删除
     * @param id
     */
    void delectById(int id);

    /**
     * 批量删除
     * @param ids
     */
    void delectByIds(int [] ids);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<Brand> selectByPage(int currentPage,int pageSize);

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);
}
