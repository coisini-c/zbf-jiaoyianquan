package com.zbf.common.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author CX
 * @PACKAGENAME:com.zbf.common.entity
 * @ClassName: Dats
 * @Description: TODO(参数)
 * @date 2020/9/1321:10
 */
@Data
public class Dats<t> {
    private Long id;
    private Integer version;
    private String userName;
    private String loginName;
    private String passWord;
    private String tel;
    private String buMen;
    private String salt;
    private Date createTime;
    private String email;
    private Date updateTime;
    private Integer pagenum=1;
    private Integer pagesize=3;
    private Integer totals;
    private List<t> dat;
    private String roleName;
    private Integer[] ids;
    private Integer size;
    private String excleName;
    private String[] type;
    private String order;
    private Integer total;
    private String orderType[];
    private Integer sex;
    private Integer status;






}
