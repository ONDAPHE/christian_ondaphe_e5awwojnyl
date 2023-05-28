package com.anywrtest.christianondaphee5awwojnyl.repositories;

import com.anywrtest.christianondaphee5awwojnyl.entities.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentsRepository extends JpaRepository<Students, Long> {

    String query = "SELECT student FROM Students student INNER JOIN Classes classe on student.classe.id = classe.id\n" +
            "INNER JOIN Teachers teacher on classe.teacher.id = teacher.id\n" +
            "WHERE\n" +
            "classe.name LIKE :className and \n" +
            "CONCAT(COALESCE(teacher.firstName,''), ' ', COALESCE(teacher.lastName,'')) LIKE :teacherFullName";
    @Query(query)
    Page<Students> findByClassNameAndTeacherFullName(@Param("className") String className,
                                                     @Param("teacherFullName") String teacherFullName,
                                                     Pageable pageRequest);
}
