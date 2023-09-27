package com.satoken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satoken.entity.FileDetail;
import com.satoken.service.FileDetailService;
import com.satoken.mapper.FileDetailMapper;
import org.springframework.stereotype.Service;

/**
* @author jiuho
* @description 针对表【file_detail(文件记录表)】的数据库操作Service实现
* @createDate 2023-09-22 14:41:55
*/
@Service
public class FileDetailServiceImpl extends ServiceImpl<FileDetailMapper, FileDetail>
    implements FileDetailService {

}




