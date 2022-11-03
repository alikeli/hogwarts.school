package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student addStudent(Student student) {
        return studentRepository.save(student);

    }

    public Student findStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(long id, Student student) {
        return studentRepository.save(student);

    }

    public void removeStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentByAge(Integer age) {
        return studentRepository.getStudentByAge(age);
    }

}
