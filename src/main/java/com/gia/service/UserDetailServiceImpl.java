package com.gia.service;

import com.gia.domain.GiaUserDetail;
import com.gia.domain.po.Role;
import com.gia.domain.po.User;
import com.gia.domain.po.UserRole;
import com.gia.repository.RoleRepository;
import com.gia.repository.UserRepository;
import com.gia.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Fenglin on 2017/6/28.
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(s);

        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        List<Role> roles = new LinkedList<>();
        if (!CollectionUtils.isEmpty(userRoles)) {
            for (UserRole userRole : userRoles) {
                roles.add(roleRepository.findById(userRole.getId()));
            }
        }
        GiaUserDetail userDetail = new GiaUserDetail();
        userDetail.setUserName(user.getUserName());
        userDetail.setPassword(user.getPassword());
        userDetail.setRoles(roles);

        System.out.println("s:" + s);
        System.out.println("username:" + userDetail.getUsername() + ";password:" + userDetail.getPassword());
        return userDetail;
    }
}
