package com.cx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: TODO
 * @author: LH
 * @date: 2020/6/19 20:42
 * @version: V1.0
 **/
@ConfigurationProperties(prefix = "minio")
@Data
public class MinioProperties {


    private String url;

    private String userName;

    private String passWord;

    private String defualtDir;



}
