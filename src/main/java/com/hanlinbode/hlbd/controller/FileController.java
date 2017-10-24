package com.hanlinbode.hlbd.controller;

import com.hanlinbode.hlbd.bean.BaseBean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileController {
    @RequestMapping(value = "/auth/imgUpdate", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public BaseBean<String> imgUpdate(@RequestParam(value = "file") MultipartFile file) {
        BaseBean<String> result = new BaseBean<>();
        String resourceLocation = "d://test//";
        if (file.isEmpty()) {
            result.setCode(415);
            result.setMessage("文件不能为空");
            return result;
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = resourceLocation;
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return result;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.setCode(416);
        result.setMessage("上传失败");
        return result;

    }


}
