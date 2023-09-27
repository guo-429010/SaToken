package com.satoken.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.recorder.FileRecorder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.satoken.entity.FileDetail;
import com.satoken.mapper.FileDetailMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class FileRecorderServiceImpl extends ServiceImpl<FileDetailMapper, FileDetail> implements FileRecorder {

    @Override
    @SneakyThrows
    public boolean save(FileInfo fileInfo) {
        FileDetail detail = BeanUtil.copyProperties(fileInfo, FileDetail.class, "attr");
        if (fileInfo.getAttr() != null) {
            detail.setAttr(new ObjectMapper().writeValueAsString(fileInfo.getAttr()));
        }
        boolean b = save(detail);
        if (b) {
            fileInfo.setId(detail.getId());
        }
        return b;
    }

    @Override
    @SneakyThrows
    public FileInfo getByUrl(String url) {
        FileDetail detail = getOne(new QueryWrapper<FileDetail>().eq("url", url));
        FileInfo info = BeanUtil.copyProperties(detail,FileInfo.class,"attr");

        if (StrUtil.isNotBlank(detail.getAttr())) {
            info.setAttr(new ObjectMapper().readValue(detail.getAttr(), Dict.class));
        }
        return info;
    }

    @Override
    public boolean delete(String url) {
        return remove(new QueryWrapper<FileDetail>().eq("url",url));
    }
}
