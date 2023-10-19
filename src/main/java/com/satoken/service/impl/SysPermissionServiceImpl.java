package com.satoken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satoken.entity.SysPermission;
import com.satoken.service.SysPermissionService;
import com.satoken.mapper.SysPermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author jiuho
* @description 针对表【sys_permission】的数据库操作Service实现
* @createDate 2023-09-27 13:40:39
*/
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission>
    implements SysPermissionService{

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<String> getUserPermission(List<Integer> roleIds) {
        return sysPermissionMapper.selectUserPermission(roleIds)
                .stream()
                .map(SysPermission::getPermissionIdentify)
                .collect(Collectors.toList());
    }
}




