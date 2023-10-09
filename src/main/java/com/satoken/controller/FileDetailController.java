package com.satoken.controller;

import cn.dev33.satoken.util.SaFoxUtil;
import cn.dev33.satoken.util.SaResult;
import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import com.satoken.service.FileDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class FileDetailController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileDetailService fileDetailService;

    @PostMapping("/upload")
    public SaResult upload(MultipartFile file,String customPath) {
        if (SaFoxUtil.isEmpty(customPath)) {
            customPath = "common";
        }
        FileInfo fileInfo = fileStorageService.of(file)
                .setPath(customPath + "/")
                .setProgressMonitor((progressSize, allSize) ->
                        log.info("已上传: " + progressSize + " 总大小: " + allSize + " 进度: " + (progressSize * 100 / allSize) + "%")
                )
                .upload();
        return SaResult.ok("上传成功").setData(fileInfo);
    }

    @PostMapping("/deleteFile")
    public SaResult deleteFile(String fileUrl) {
        boolean result = fileStorageService.delete(fileUrl);
        return result ? SaResult.ok("删除成功") : SaResult.error("删除失败");
    }

    @GetMapping("/existFile")
    public SaResult existFile(String fileUrl) {
       try {
            FileInfo fileInfo = fileStorageService.getFileInfoByUrl(fileUrl);
            return SaResult.ok("文件存在").setData(fileInfo);
       } catch (Exception e) {
            return SaResult.error("文件不存在");
       }
    }

    @GetMapping("/file/list")
    public SaResult getFileList() {
        return SaResult.ok().setData(fileDetailService.list());
    }
}
