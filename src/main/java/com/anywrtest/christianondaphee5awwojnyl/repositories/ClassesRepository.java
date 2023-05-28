package com.anywrtest.christianondaphee5awwojnyl.repositories;

import com.anywrtest.christianondaphee5awwojnyl.entities.Classes;
import com.anywrtest.christianondaphee5awwojnyl.entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassesRepository extends JpaRepository<Classes, Long> {
    List<Students> findByName(String name);
}
