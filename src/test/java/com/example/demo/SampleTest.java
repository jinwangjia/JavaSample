package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//参考
//https://blog.csdn.net/weixin_36292503/article/details/103011488
//https://blog.csdn.net/weixin_44472810/article/details/105649901

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

    @Test
    public void testInsert(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.orderByDesc("id").last("limit 1");
        User user1 = userMapper.selectOne(userQueryWrapper);
        long newId = user1.getId() + 1;
        User user = new User();
        user.setId(newId);
        user.setAge(1);
        user.setName("我的名字是个秘密");
        user.setEmail("我是不会告诉你的");
        int insert = userMapper.insert(user);
        Assert.assertEquals(1,insert);
        int deleteById = userMapper.deleteById(newId);
        Assert.assertEquals(1,deleteById);
    }

    @Test
    public void selectAllList(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age",23);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectBatchIds(){
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<User> userList = userMapper.selectBatchIds(integers);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","Tom");
        map.put("age","28");
        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectCount(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20);
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println(count);
    }

    @Test
    public void selectObjs(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age",23);
        List<Object> list = userMapper.selectObjs(queryWrapper);
        list.forEach(System.out::println);
    }

//    //没成功
//    @Test
//    public void selectPage(){
//        Page<User> page = new Page<>(1, 2);
//        IPage<User> userPage = userMapper.selectPage(page, null);
//        System.out.println("总记录数据："+userPage.getTotal());
//        System.out.println("总页数："+userPage.getPages());
//        List<User> userList = userPage.getRecords();
//        userList.forEach(System.out::println);
//    }
//
//    //没成功
//    @Test
//    public void selectMapsPage(){
//        Page<User> page = new Page<>(1, 2);
//        IPage<Map<String,Object>>  mapIPage = userMapper.selectMapsPage( page,null);
//        System.out.println("总记录数据："+userPage.getTotal());
//        System.out.println("总页数："+userPage.getPages());
//        List<User> userList = userPage.getRecords();
//        userList.forEach(System.out::println);
//    }

    @Test
    public void  delete(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name","B");
        int i = userMapper.delete(queryWrapper);
        System.out.println(i);
    }

    @Test
    public void update(){
        User user = new User();
        user.setEmail("");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name","Tom");
        int update = userMapper.update(user, updateWrapper);
        System.out.println(update);
    }

}
