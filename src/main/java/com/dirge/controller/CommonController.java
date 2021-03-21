package com.dirge.controller;

import com.dirge.config.ResultData;
import com.dirge.constants.ResponseMessage;
import com.dirge.entity.Email;
import com.dirge.entity.Music;
import com.dirge.entity.User;
import com.dirge.mapper.UserMapper;
import com.dirge.service.HelloService;
import com.dirge.service.UserService;
import com.dirge.utils.SendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

import static com.dirge.constants.ResponseMessage.FILE_DOWNLOAD_FAILURE;

@RestController
public class CommonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    public RedisTemplate redisTemplate;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private SendEmail sendEmail;


    /**
     * 发送邮件
     *
     * @param email
     * @return
     */
    @PostMapping("/sendMail")
    public String sendMail(Email email) {
        email.setSender(sender);
        email.setSendDate(new Date());
        sendEmail.sendSimpleMail(email);
        return "success";
    }


    /**
     * 下载文件
     *
     * @return
     */
    @GetMapping("/downLoadFile")
    public ResultData downLoadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "";
        if(fileName != null){
            File file = new File("D://Downloads/111111.png");
            if(file.exists()){
                response.setContentType("application/force-download");
                //设置文件名
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
                byte[] buffer = new byte[1024];
                FileInputStream inputStream = null;
                BufferedInputStream bis =  null;
                try {
                    inputStream = new FileInputStream(file);
                    bis = new BufferedInputStream(inputStream);
                    OutputStream os = response.getOutputStream();
                    int i;
                    while ((i=bis.read(buffer))!=-1){
                        os.write(buffer,0,i);
                    }
                    return ResultData.ok();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(bis!=null){
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(inputStream != null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return ResultData.error(FILE_DOWNLOAD_FAILURE);
    }

    /**
     * 上传文件
     *
     * @return
     */
    @GetMapping("/upLoadFile")
    public ResultData upLoadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResultData.error(ResponseMessage.FILE_EMPTY);
            }
            //获取文件名
            String fileName = file.getOriginalFilename();
            LOGGER.info("上传的文件名：{}", fileName);
            //获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            LOGGER.info("文件的后缀名：{}", suffixName);
            //设置文件的存储路径
            String filePath = "D://Downloads/";
            String path = filePath + fileName;
            File dest = new File(path);
            //检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultData.ok();
    }
}


