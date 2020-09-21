package com.cx.controller;


import com.zbf.common.utils.UID;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author CX
 * @PACKAGENAME:com.cx.controller
 * @ClassName: MinioController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2020/9/2015:50
 */
@RestController
@RequestMapping("minio")
@CrossOrigin
public class MinioController {

    @Autowired
    private MinioClient minioClient;

    @RequestMapping("toUpload")
    public String toUpload(@RequestParam("file") MultipartFile file)  {
        String originalFilename = file.getOriginalFilename();
        String uuid16 = UID.getUUID16();
        String name= uuid16+originalFilename;
        try {
            if(!minioClient.bucketExists("authority")){
                minioClient.makeBucket("authority");
            }
            long size = file.getSize();
            InputStream inputStream = file.getInputStream();
            PutObjectOptions putObjectOptions=new PutObjectOptions(size,-1);
            minioClient.putObject("authority",name, (InputStream) inputStream,putObjectOptions);
            String url = minioClient.getObjectUrl("authority", name);
            return url;
        }catch (Exception x){
            x.printStackTrace();
            return null;
        }
    }


}
