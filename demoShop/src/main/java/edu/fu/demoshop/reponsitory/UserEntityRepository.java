package edu.fu.demoshop.reponsitory;

import edu.fu.demoshop.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity , Integer> {
    UserEntity findByLogin(String login);
}
