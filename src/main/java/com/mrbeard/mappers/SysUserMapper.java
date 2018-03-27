package com.mrbeard.mappers;

import com.mrbeard.entites.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper {

    public SysUser findByUserName(String username);
}
