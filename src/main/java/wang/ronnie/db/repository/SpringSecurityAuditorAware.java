package wang.ronnie.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import wang.ronnie.db.entity.UserEntity;

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