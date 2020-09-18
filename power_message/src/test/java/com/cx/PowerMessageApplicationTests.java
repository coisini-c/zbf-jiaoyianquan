package com.cx;

import com.cx.dao.UserDao;
import com.zbf.common.entity.Dats;
import org.apache.ibatis.annotations.Update;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class PowerMessageApplicationTests {
    @Resource
    UserDao userDao;

    @Test
    void contextLoads() {
        Dats dats=new Dats();
        dats.setLoginName("zhangsan");
        int insertuser = userDao.insertuser(dats);
        System.err.println(insertuser);

    }
    @Test
    void upd(){
        Dats dats=new Dats();
        dats.setTel("17696935214");
        dats.setPassWord("55555");
        dats.setEmail("583788430@qq.com");
        int i = userDao.updByTel(dats.getTel(), dats.getPassWord());
        System.err.println(i);
    }



    }
