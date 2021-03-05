package com.example.demo;

import com.example.demo.mapper.User;
import com.example.demo.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    //在mybatis-plus的网站上这里是用@Autowired，但是会有错误，改用@Resource后正常了。
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect(){
        System.out.println("--------------");
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5,userList.size());
        userList.forEach(System.out::println);
    }

}
