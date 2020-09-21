package com.zbf.common.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.Map;

/**
 * @author CX
 * @PACKAGENAME:com.zbf.common.utils
 * @ClassName: WordUtil
 * @Description: TODO(操作word)
 * @date 2020/9/2118:07
 */
public class WordUtil {

    @SuppressWarnings("unchecked")
    public static void createWord(Map dataMap, String templateName,
                                  String filePath, String fileName) {
        try {
            // 创建配置实例
            Configuration configuration = new Configuration();

            // 设置编码
            configuration.setDefaultEncoding("UTF-8");

            // ftl模板文件
            configuration.setDirectoryForTemplateLoading(new File("D:/Desktop/doc/"));

            // 获取模板
            Template template = configuration.getTemplate(templateName);

            // 输出文件
            File outFile = new File(filePath + File.separator + fileName);

            // 如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }

            // 将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));

            // 生成文件
            template.process(dataMap, out);

            // 关闭流
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 用于将之图片转换为base64字符串
     * @param filename
     * @return
     * @throws IOException
     */
    public static String getImageString(String filename) throws IOException {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(filename);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            throw e;
        } finally {
            if(in != null) in.close();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return data != null ? encoder.encode(data) : "";
    }


}
