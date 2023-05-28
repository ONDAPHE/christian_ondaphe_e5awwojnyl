package com.anywrtest.christianondaphee5awwojnyl;

import com.anywrtest.christianondaphee5awwojnyl.entities.Classes;
import com.anywrtest.christianondaphee5awwojnyl.entities.Students;
import com.anywrtest.christianondaphee5awwojnyl.entities.Teachers;
import com.anywrtest.christianondaphee5awwojnyl.repositories.ClassesRepository;
import com.anywrtest.christianondaphee5awwojnyl.repositories.StudentsRepository;
import com.anywrtest.christianondaphee5awwojnyl.repositories.TeachersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner startUp(ClassesRepository classesRepository,
                              TeachersRepository teachersRepository,
                              StudentsRepository studentsRepository){

        return args -> {
            Teachers teacher = Teachers.builder().firstName("first").lastName("last").build();
            teachersRepository.save(teacher);
            Teachers teacher2 = Teachers.builder().firstName("first2").lastName("last2").build();
            teachersRepository.save(teacher2);

            Classes classe = Classes.builder().name("IT3").teacher(teacher).build();
            classesRepository.save(classe);
            Classes classe2 = Classes.builder().name("IT4").teacher(teacher2).build();
            classesRepository.save(classe2);

            Students student = Students.builder().classe(classe).firstName("0 first").lastName("0 last").build();
            studentsRepository.save(student);

            Students student1 = Students.builder().classe(classe).firstName("1 first").lastName("1 last").build();
            studentsRepository.save(student1);

            Students student2 = Students.builder().classe(classe2).firstName("2 first").lastName("2 last").build();
            studentsRepository.save(student2);



        };
    }
}
