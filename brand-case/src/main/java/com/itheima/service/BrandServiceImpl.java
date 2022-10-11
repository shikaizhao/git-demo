package com.itheima.service;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return brands
     */

    @Override
    public List<Brand> selectAll() {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAll();
        sqlSession.close();
        return brands;
    }

    /**
     * 添加数据
     *
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //添加对象
        mapper.addAll(brand);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    /**
     * 根据id查询数据
     *
     * @param id
     * @return brands
     */
    @Override
    public List<Brand> selectByIdBrands(int id) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAllByIdBrands(id);
        sqlSession.close();
        return brands;
    }

    /**
     * 修改操作
     *
     * @param brand
     */
    @Override
    public void update(Brand brand) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.update(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delectById(int id) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public void delectByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 分页查询
     *
     * @param currentPage 当前页码数
     * @param pageSize    每页条数
     * @return
     */
    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //计算初始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条数
        int size = pageSize;
        List<Brand> rows = mapper.selectByPage(begin, size);
        //查询总条数
        int totalCount = mapper.selectToCount();

        //封装成PageBean对象
        PageBean<Brand> brandPageBean = new PageBean<>();
        brandPageBean.setRows(rows);
        brandPageBean.setTotalCount(totalCount);
        //释放资源
        sqlSession.close();
        return brandPageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //计算初始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条数
        int size = pageSize;
        //处理brand对象
        String brandName = brand.getBrandName();
        if (brandName != null && brandName != "") {
            brand.setBrandName("%" + brandName + "%");
        }
        String  companyName = brand.getCompanyName();
        if ( companyName != null &&  companyName != "") {
            brand.setCompanyName("%" +  companyName + "%");
        }
       
        List<Brand> rows = mapper.selectByPageAndCondition(begin, size, brand);
        //查询总条数
        int totalCount = mapper.selectToCountAndCondition(brand);
        //封装成PageBean对象
        PageBean<Brand> brandPageBean = new PageBean<>();
        brandPageBean.setRows(rows);
        brandPageBean.setTotalCount(totalCount);
        //释放资源
        sqlSession.close();
        return brandPageBean;
    }
}
