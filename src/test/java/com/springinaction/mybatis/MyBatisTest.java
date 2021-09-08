package com.springinaction.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springinaction.common.Spitter;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zjjfly on 2017/3/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataSourceConfig.class)
public class MyBatisTest {

    @Resource(name = "spitterMap")
    private SpitterMapper spitterMapper;

    @Resource
    SqlSession sqlSession;

    @Test
    public void mapper() {
        Spitter spiiter = spitterMapper.getSpitter(1L);
        System.out.println(spiiter);
    }

    @Test
    public void sqlsessionTemplate() {
        Object o = sqlSession.selectOne("com.springinaction.mybatis.SpitterMapper.getSpitter", 1L);
        System.out.println(o);
    }

    @Test
    public void testPageHelper() {
        Page page = PageHelper.startPage(0, 5);
        List<Spitter> spitters = sqlSession.selectList("com.springinaction.mybatis.SpitterMapper.getAllSpitter");
        PageInfo pageInfo = new PageInfo(spitters);
        System.out.println(pageInfo.getTotal());
    }

    @Test
    public void test() {
        List<Spitter> objects = sqlSession.selectList("com.springinaction.mybatis.SpitterMapper.getAllSpitter",null,new RowBounds(1 ,10));
        System.out.println(objects.size());
    }
}
