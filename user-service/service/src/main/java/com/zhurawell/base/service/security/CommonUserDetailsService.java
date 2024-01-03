package com.zhurawell.base.service.security;

import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.repo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommonUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CommonUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByEmailOrLogin(login, login).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists."));
        return user.getUserDetails();
    }

}
