package com.junqing;

import static org.junit.Assert.assertTrue;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.junqing.mybatis.bean.User;
import com.junqing.mybatis.bean.UserExample;
import com.junqing.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void testMBG(){
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = build.openSession(true);
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            PageHelper.startPage(2,5);
            UserExample userExample = new UserExample();
            userExample.createCriteria().andPasswordEqualTo("123456").andUsernameIsNotNull();
            userExample.or().andUsernameEqualTo("yfj");
            List<User> users = mapper.selectByExample(userExample);
            //PageInfo中的泛型是List中存放的类型
            //参数：第一个是查询到的集合，第二个是展示的页码个数（类似于页面中展示的当前页面，和当前页面左右两边的页码）
            PageInfo<User> page=new PageInfo<User>(users,5);
            System.out.println(page);
            System.out.println("fix sout");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
