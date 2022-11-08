package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
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
        return studentRepository.findById(id).orElseThrow();
    }

    public Student editStudent(long id, Student student) {
        return studentRepository.save(student);

    }

    public void removeStudent(Long id) {
        studentRepository.deleteById(id);
    }


    public List<Student> getStudentsByAgeBetween(Integer min, Integer max) {
        return studentRepository.getStudentsByAgeBetween(min, max);
    }
    public Collection<Student> getStudentByFacultyId(Long facultyID) {
        return studentRepository.getStudentByFacultyId(facultyID);
    }
    public Collection<Student> allStudent() {
        return studentRepository.findAll();
    }

    public Faculty getFacultyOfStudent(Long studentId) {
        Student currentStudent = studentRepository.getById(studentId);
        return currentStudent.getFaculty();
    }


}
