package com.yildiz.services.auth;

import com.yildiz.entity.User;
import com.yildiz.enums.UserRole;
import com.yildiz.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AuthSercieIpml {
    private final UserRepository userRepository;
    @PostConstruct
    public void creatAnAdminAccount(){
        Optional<User>  adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount.isEmpty()){
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setName("Admin");
            user.setPassword("123456");
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);
            System.out.println("Admin acount created seccessflly");
        }else {
            System.out.println("Admin acount not created seccessflly");
        }
    }
}
