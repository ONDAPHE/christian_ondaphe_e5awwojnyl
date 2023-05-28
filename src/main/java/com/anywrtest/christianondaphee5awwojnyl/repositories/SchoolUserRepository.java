package com.anywrtest.christianondaphee5awwojnyl.repositories;

import com.anywrtest.christianondaphee5awwojnyl.entities.SchoolUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolUserRepository extends JpaRepository<SchoolUser, Long> {
    SchoolUser findByUsername(String username);
}
