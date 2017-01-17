package com.test.db.repository.support;

import com.test.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
class SpringSecurityAuditorAware implements AuditorAware<Long> {

    @Autowired
    private UserRepository userRepository;

    public Long getCurrentAuditor() {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return null;
//        }
//
//        return ((MyUserDetails) authentication.getPrincipal()).getUser();
        return 1L;
    }
}