package com.yildiz.services.auth;

import com.yildiz.dto.SingupRequeust;
import com.yildiz.dto.UserDto;
import com.yildiz.entity.User;
import com.yildiz.enums.UserRole;
import com.yildiz.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void creatAnAdminAccount(){
        Optional<User>  adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount.isEmpty()){
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setName("Admin");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            System.out.println("Admin acount created seccessflly");
        }else {
            System.out.println("Admin acount not created seccessflly");
        }
    }


    public UserDto createUser(SingupRequeust singupRequeust) {
        if(userRepository.findFirstByEmail(singupRequeust.getEmail()).isPresent()){
            throw new EntityExistsException("user already  present with email" + singupRequeust.getEmail());
        }
        User user = new User();
        user.setEmail(singupRequeust.getEmail());
        user.setName(singupRequeust.getName());
        user.setUserRole(UserRole.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(singupRequeust.getPassword()));
        User createdUser = userRepository.save(user);
        return createdUser.getUserDto();
    }
}
