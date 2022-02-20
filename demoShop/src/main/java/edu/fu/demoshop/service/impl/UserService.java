package edu.fu.demoshop.service.impl;

import edu.fu.demoshop.model.RoleEntity;
import edu.fu.demoshop.model.UserEntity;
import edu.fu.demoshop.reponsitory.RoleEntityRepository;
import edu.fu.demoshop.reponsitory.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    RoleEntityRepository roleEntityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity saveUser(UserEntity userEntity){
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_ADMIN");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        return userEntityRepository.save(userEntity);
    }

    public UserEntity findByLogin(String login){
        return userEntityRepository.findByLogin(login);
    }

    public UserEntity findByLoginAndPassword(String login , String password){
        UserEntity userEntity = findByLogin(login);
        if(userEntity != null){
            if (passwordEncoder.matches(password , userEntity.getPassword())){
                return userEntity;
            }
        }
        return null;
    }

}
