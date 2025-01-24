package com.yildiz.services.auth;

import com.yildiz.dto.SingupRequeust;
import com.yildiz.dto.UserDto;



public interface AuthService {
    UserDto createUser(SingupRequeust singupRequeust);

}
