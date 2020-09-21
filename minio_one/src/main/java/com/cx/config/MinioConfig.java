package com.cx.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CX
 * @PACKAGENAME:com.cx.config
 * @ClassName: MinioConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2020/9/2015:50
 */
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfig {

    @Autowired
    private MinioProperties minioProperties;

    @Bean
    public MinioClient getMinioClient() throws InvalidPortException, InvalidEndpointException {
        MinioClient minioClient=new MinioClient(minioProperties.getUrl(),minioProperties.getUserName(),minioProperties.getPassWord());
        return minioClient;
    }
}
