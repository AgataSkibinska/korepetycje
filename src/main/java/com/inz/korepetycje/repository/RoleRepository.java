package com.inz.korepetycje.repository;

import com.inz.korepetycje.model.Role;
import com.inz.korepetycje.model.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
