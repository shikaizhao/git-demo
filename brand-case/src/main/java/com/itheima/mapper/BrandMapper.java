package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {
    List<Brand> selectAll();
    void addAll(Brand brand);
    List<Brand> selectAllByIdBrands(int id);
    void  update(Brand brand);
    void deleteById(int id);
    void deleteByIds(@Param("ids") int [] ids);
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size") int size);
    int selectToCount();
    List<Brand> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size, @Param("brand") Brand brand);
    int selectToCountAndCondition(Brand brand);
}
