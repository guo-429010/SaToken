package com.satoken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satoken.entity.SysRole;
import com.satoken.service.SysRoleService;
import com.satoken.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author jiuho
* @description 针对表【sys_role】的数据库操作Service实现
* @createDate 2023-09-27 13:40:39
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService{

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> getUserRole(Object loginId) {
        return sysRoleMapper.selectUserRole(loginId);
    }

}




