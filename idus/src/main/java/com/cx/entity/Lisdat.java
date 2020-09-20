package com.cx.entity;

import lombok.Data;

import java.util.Set;

/**
 * @author CX
 * @PACKAGENAME:com.cx.entity
 * @ClassName: Lisdat
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2020/9/2020:37
 */
@Data
public class Lisdat {
    private Set<BaseMenu> menulist;
    private BaseUser baseUser;
}
