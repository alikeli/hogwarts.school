package ru.hogwarts.school.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> getFacultyByNameIgnoreCase(String name);

    List<Faculty> getFacultyByColorIgnoreCase(String color);
    Collection<Student> getStudentsByFaculty(Faculty faculty_id);
}
