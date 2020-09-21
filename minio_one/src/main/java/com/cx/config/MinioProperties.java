package com.cx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;



/**
 * @author CX
 * @PACKAGENAME:com.cx.config
 * @ClassName: MinioProperties
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2020/9/2015:50
 */
@ConfigurationProperties(prefix = "minio")
@Data
public class MinioProperties {


    private String url;

    private String userName;

    private String passWord;

    private String defualtDir;



}
