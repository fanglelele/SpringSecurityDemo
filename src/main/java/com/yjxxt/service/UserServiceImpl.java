package com.yjxxt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    PasswordEncoder pw;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username) ||!"admin".equals(username)){
            System.out.println("用户名不存在！");
            throw new UsernameNotFoundException("用户名找不到");
        }
        String password = pw.encode("123");

        //前面是ROLE_开头的代表的是角色，其它代表的是权限
        return new User(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList("normal,ROLE_QQ,ROLE_AA"));
    }
}
