package com.igeek.gmall.manager.controller;

import com.igeek.gmall.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 余祥刚
 * @create 2020-01-28 12:04
 */
@RestController
@CrossOrigin
public class IndexController {

    @Value("${imageServerPath}")
    private String imageServerPath;

    @RequestMapping("fileUpload")
    public String fileUpload(@RequestParam(name = "file") MultipartFile multipartFile){
        String path = "";
        try {
            FastDFSClient client = new FastDFSClient("classpath:tracker.conf");
            String filename = multipartFile.getOriginalFilename();
            String extName = filename.substring(filename.lastIndexOf(".")+1);
            path = client.uploadFile(multipartFile.getBytes(), extName);
            path = imageServerPath + path;
            System.out.println(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
}
