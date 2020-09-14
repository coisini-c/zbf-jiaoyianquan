package com.zbf.common.entity;

import lombok.Data;

/**
 * @author CX
 * @PACKAGENAME:com.zbf.common.entity
 * @ClassName: Dats
 * @Description: TODO(参数)
 * @date 2020/9/1321:10
 */
@Data
public class Dats {
    private int id;
    private int version;
    private String userName;
    private String loginName;
    private String passWord;
    private String tel;
    private String buMen;
    private String salt;

    private String email;


}
