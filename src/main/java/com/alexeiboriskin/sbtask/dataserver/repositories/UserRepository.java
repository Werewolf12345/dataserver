package com.alexeiboriskin.sbtask.dataserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alexeiboriskin.sbtask.dataserver.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
