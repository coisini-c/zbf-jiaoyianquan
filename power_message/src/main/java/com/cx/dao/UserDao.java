package com.cx.dao;



import com.zbf.common.entity.Dats;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author CX
 * @PACKAGENAME:com.cx.dao
 * @ClassName: UserDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2020/9/1115:02
 */
@Mapper
public interface UserDao {
    /**
     *
     * @param tel
     * @return
     */
    @Select("SELECT *  FROM base_user u WHERE  u.tel=#{tel}")
    Map<String, Object> getUserByTel(String tel);

    /**
     * 根据邮箱查用户
     * @param email
     * @return
     */
    @Select("SELECT *  FROM base_user u WHERE  u.email=#{email}")
    public Map<String,Object> getUserByEmail(String email);

    /**
     * 根据邮箱修改
     * @param email
     * @param pwd
     * @return
     */
    @Transactional
    @Update("UPDATE base_user u SET u.passWord=#{pwd} WHERE u.email =#{email}")
    public int updByEmail(@Param("email") String email,@Param("pwd") String pwd);

    /**
     * 根据电话修改
     * @param tel
     * @param pwd
     * @return
     */
    @Transactional
    @Update("UPDATE base_user u SET u.passWord=#{pwd} WHERE u.tel =#{tel}")
    public int updByTel(@Param("tel") String tel,@Param("pwd") String pwd);

    /**
     * 添加
     * @param dats
     * @return
     */
    @Transactional
    @Insert("INSERT INTO base_user (version,userName,loginName,passWord,tel,email,createTime) VALUES (#{version},#{userName},#{loginName},#{passWord},#{tel},#{email},#{createTime})")
    public int insertuser(Dats dats);
    /**
     * 登录名查user
     * @param userName
     * @return
     */
    @Select("select * from base_user where loginName=#{userName}")
    public Map<String, Object> getUserByUserName(String userName);

    @Transactional
    @Update("UPDATE base_user u set u.version=0 WHERE loginName=#{loginName}")
    public  int updByLoginName(String loginName);

}
