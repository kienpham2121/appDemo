package edu.fu.demoshop.reponsitory;

import edu.fu.demoshop.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleEntityRepository extends JpaRepository<RoleEntity , Integer> {
    RoleEntity findByName(String name);
}
