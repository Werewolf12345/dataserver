package com.alexeiboriskin.sbtask.dataserver.repositories;

import com.alexeiboriskin.sbtask.dataserver.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
