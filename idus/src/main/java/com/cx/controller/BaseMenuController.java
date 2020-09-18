package com.cx.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.entity.BaseMenu;
import com.cx.service.IBaseMenuService;
import com.zbf.common.utils.UID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *用来控制菜单
 * @author cx
 * @since 2020-09-16
 */
@RestController
@RequestMapping("/menu")
@CrossOrigin
@Transactional
public class BaseMenuController {
    //注入service
    @Autowired
    private IBaseMenuService baseMenuService;
    /**
     * 格式化
     */
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 查询菜单列表
     * @return
     */
    @RequestMapping("selallmenu")
    public List<BaseMenu>  selallmenu(){
            System.err.println("开始查询菜单列表");
        List<BaseMenu> codes=baseMenuService.list(new QueryWrapper<BaseMenu>().eq("parentCode",0));

        codes.forEach(co->{

            List<BaseMenu> parentCodes = baseMenuService.list(new QueryWrapper<BaseMenu>().eq("parentCode", co.getCode()));
            parentCodes.forEach(pa->{
                pa.setList(baseMenuService.list(new QueryWrapper<BaseMenu>().eq("parentCode",pa.getCode())));
            });
            co.setList(parentCodes);
        });
        return codes;
    }

    /**
     * 添加菜单
     * @param baseMenu
     * @return
     */
    @RequestMapping("addmenu")
    public boolean addmenu(@RequestBody BaseMenu baseMenu){
        System.err.println("添加的数据是"+baseMenu.toString());
        baseMenu.setCreateTime(dateToLocalDateTime(new Date()));
        baseMenu.setVersion(10);
        boolean save =false;
         save = baseMenuService.save(baseMenu);
        if (save){
            baseMenu.setCode(Long.valueOf(baseMenu.getId()));
            boolean b = baseMenuService.updateById(baseMenu);

        }


        //        save=baseMenuService.save(baseMenu);
        return save;
    }

    /**
     * date转localdatetime
     * @param time
     * @return
     */
    public  LocalDateTime dateToLocalDateTime(Date time){
        Instant it = time.toInstant();
        ZoneId zid = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(it, zid);
    }

    /**
     * 修改菜单
     * @param baseMenu
     * @return
     */
    @RequestMapping("updmenu")
    @Transactional(rollbackFor = Exception.class)
    public boolean updmenu(@RequestBody BaseMenu baseMenu){
        baseMenu.setId(UID.next());
        System.err.println("修改的数据是"+baseMenu.toString());
        boolean save =false;
    save=baseMenuService.updateById(baseMenu);
        return save;
    }

    /**
     * 删除菜单
     * @param baseMenu
     * @return
     */
    @RequestMapping("delmenu")
    public boolean delmenu(@RequestBody BaseMenu baseMenu){
        System.err.println("删除的数据是"+baseMenu.toString());
        boolean b =false;
               b = baseMenuService.removeById(baseMenu.getId());
        return b;
    }



}
