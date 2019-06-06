package com.hfzycj.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Map;

public class FileUtil extends BaseUtil {

    public static String uploadFile(MultipartFile file, String root, Boolean isOriginalName) throws Exception {
        String originalName = file.getOriginalFilename();
        StringBuffer fileName = new StringBuffer(isOriginalName ? originalName : String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%1$tL" + originalName.substring(originalName.lastIndexOf(".")).toLowerCase(), new Date()));
        String dest = new StringBuffer(root).append(fileName).toString();
        File destFile = new File(dest);
        if (!destFile.getParentFile().exists()) {
            if (!destFile.getParentFile().mkdirs()) {
                return "";
            }
        }
        file.transferTo(destFile);
        return fileName.toString();
    }

    public static void generateHTML(Configuration configuration, Map<String, Object> map, String templateFile, String targetHtml) throws Exception {
        Template template = configuration.getTemplate(templateFile);
        File pathFile = new File(targetHtml.substring(0, targetHtml.lastIndexOf(File.separator)));
        if (!pathFile.exists()) {
            pathFile.mkdir();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetHtml));
        File htmlFile = new File(targetHtml);
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
        template.process(map, out);
        bufferedWriter.close();
        out.flush();
        out.close();
    }

}
