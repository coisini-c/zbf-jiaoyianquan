package com.zbf.auth.denglu;

import com.zbf.auth.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author: LCG
 * 作者: LCG
 * 日期: 2020/8/26 20:37
 * 描述: 这个类主要是用来根据传入的用户名获取用户信息的
 */
@Component
public class MyUserServiceDetail implements UserDetailsService {

   /**
    * 作者: LCG
    * 日期: 2020/9/8  12:44
    * 描述:  校验手机号的判断
    */
    private static final Pattern PATTERN_PHONE = Pattern.compile("^-?\\d+(\\.\\d+)?$");

    @Autowired(required = false)
    private UserDao userDao;

    /**
     * 根据用户获取
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //手机验证码
        if(isPhone(s)){
            Map<String, Object> userByPhone = userDao.getUserByTel(s);
            if (userByPhone!=null){
                List<String> userRole = userDao.getUserRole(Long.valueOf(userByPhone.get("id").toString()));
                List<GrantedAuthority> list=new ArrayList<>();
                userRole.forEach(rolecode->{
                    SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(rolecode);
                    list.add(simpleGrantedAuthority);
                    simpleGrantedAuthority=null;
                });
                User user = new User(userByPhone.get("loginName").toString(),userByPhone.get("passWord").toString(), list);
                return user;


            }
            else{
                throw new BadCredentialsException("不存在此用户");
            }
        }else{
            //查询用户信息
            Map<String, Object> userByUserName = userDao.getUserByUserName(s);

            if(userByUserName!=null){
                //根据用户查询用户的角色信息
                List<String> userRoleCodes = userDao.getUserRole(Long.valueOf(userByUserName.get("id").toString()));
                List<GrantedAuthority> list=new ArrayList<>();

                userRoleCodes.forEach(roleCode->{
                    SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(roleCode);
                    list.add(simpleGrantedAuthority);
                    simpleGrantedAuthority=null;
                });

                //创建用户对象
                User user = new User(userByUserName.get("loginName").toString(),userByUserName.get("passWord").toString(), list);
                return user;
            }else{
                throw new BadCredentialsException("用户名或密码不正确");
            }
        }
    }


    public boolean isPhone(String phone){
        return PATTERN_PHONE.matcher(phone).matches();
    }
}
