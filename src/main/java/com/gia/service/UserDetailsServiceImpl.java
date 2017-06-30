package com.gia.service;

import com.gia.domain.dto.GiaUser;
import com.gia.domain.po.Role;
import com.gia.domain.po.User;
import com.gia.domain.po.UserRole;
import com.gia.repository.RoleRepository;
import com.gia.repository.UserRepository;
import com.gia.repository.UserRoleRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Fenglin on 2017/6/30.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException(s + " not found.");
        }

        List<GrantedAuthority> authorities = new LinkedList<>();
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        if (!ArrayUtils.isEmpty(userRoles.toArray())) {
            for (UserRole userRole : userRoles) {
                Role role = roleRepository.findById(userRole.getRoleId());
                authorities.add(new SimpleGrantedAuthority(role.getRole()));
            }
        }

        GiaUser userDetail = new GiaUser();
        userDetail.setUsername(user.getUserName());
        userDetail.setPassword(user.getPassword());
        userDetail.setAuthorities(authorities);

        return userDetail;
    }
}
