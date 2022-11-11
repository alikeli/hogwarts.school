package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service

public class StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    public long id = 0;

    public Student addStudent(Student student) {
        student.setId(++id);
        students.put(student.getId(), student);
        return student;
    }

    public Student findStudent(Long id) {
        if (!students.containsKey(id)) {
            throw new StudentNotFoundException(id);
        }
        return students.get(id);
    }

    public Student editStudent(long id, Student student) {
        if (!students.containsKey(id)) {
            throw new StudentNotFoundException(id);
        }
        Student oldStudent = students.get(id);
        oldStudent.setAge(student.getAge());
        oldStudent.setName(student.getName());
        students.replace(id, oldStudent);
        return oldStudent;
    }

    public Student removeStudent(Long id) {
        if (!students.containsKey(id)) {
            throw new StudentNotFoundException(id);
        }
        return students.remove(id);
    }

    public Collection<Student> getStudentByAge(Integer age) {

        return students.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());


    }
}
