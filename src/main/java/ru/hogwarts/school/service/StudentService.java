package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.component.RecordMapper;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.record.StudentRecord;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final RecordMapper recordMapper;

    public StudentService(StudentRepository studentRepository,
                          RecordMapper recordMapper) {
        this.studentRepository = studentRepository;
        this.recordMapper = recordMapper;
    }

    public StudentRecord addStudent(StudentRecord studentRecord) {
        return recordMapper.toRecord(studentRepository.save(recordMapper.toEntity(studentRecord)));
    }

    public StudentRecord findStudent(Long id) {
        return recordMapper.toRecord(studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id)));
    }

    public StudentRecord editStudent(long id, StudentRecord studentRecord) {
        Student oldStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        oldStudent.setAge(studentRecord.getAge());
        oldStudent.setName(studentRecord.getName());
        return recordMapper.toRecord(studentRepository.save(oldStudent));
    }

    public StudentRecord removeStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
        return recordMapper.toRecord(student);
    }

    public Collection<StudentRecord> getStudentByAge(Integer age) {

        return studentRepository.findAllByAge(age).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());


    }

    public Collection<StudentRecord> findAllByAgeBetween(Integer minAge, Integer maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }
}
