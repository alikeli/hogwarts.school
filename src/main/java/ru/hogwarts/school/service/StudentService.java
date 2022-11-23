package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.component.RecordMapper;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.record.StudentRecord;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final RecordMapper recordMapper;
    private final FacultyRepository facultyRepository;
    private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository,
                          RecordMapper recordMapper, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.recordMapper = recordMapper;
        this.facultyRepository = facultyRepository;
    }

    public StudentRecord addStudent(StudentRecord studentRecord) {
        LOG.debug("was invoking method addStudent");
        Student student = recordMapper.toEntity(studentRecord);
        Faculty faculty = Optional.ofNullable(studentRecord.getFaculty())
                .map(FacultyRecord::getId)
                .flatMap(facultyRepository::findById)
                .orElse(null);

        student.setFaculty(faculty);
        return recordMapper.toRecord(studentRepository.save(student));
    }

    public StudentRecord findStudent(Long id) {
        LOG.debug("was invoking method findStudent");
        return recordMapper.toRecord(studentRepository.findById(id).orElseThrow(() ->
        {
            LOG.error("There is not student with id = " + id);
            throw new StudentNotFoundException(id);
        }));
    }

    public StudentRecord editStudent(long id, StudentRecord studentRecord) {
        LOG.debug("was invoking method editStudent");
        Student oldStudent = studentRepository.findById(id).orElseThrow(() ->
        {
            LOG.error("There is not student with id = " + id);
            throw new StudentNotFoundException(id);
        });
        oldStudent.setAge(studentRecord.getAge());
        oldStudent.setName(studentRecord.getName());
        oldStudent.setFaculty(
                Optional.ofNullable(studentRecord.getFaculty())
                        .map(FacultyRecord::getId)
                        .flatMap(facultyRepository::findById)
                        .orElseThrow(null)
        );
        return recordMapper.toRecord(studentRepository.save(oldStudent));
    }

    public StudentRecord removeStudent(Long id) {
        LOG.debug("was invoking method removeStudent");
        Student student = studentRepository.findById(id).orElseThrow(() ->
        {
            LOG.error("There is not student with id = " + id);
            throw new StudentNotFoundException(id);
        });
        studentRepository.delete(student);
        return recordMapper.toRecord(student);
    }

    public Collection<StudentRecord> getStudentByAge(Integer age) {
        LOG.debug("was invoking method getStudentByAge");

        return studentRepository.findAllByAge(age).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());


    }

    public Collection<StudentRecord> findAllByAgeBetween(Integer minAge, Integer maxAge) {
        LOG.debug("was invoking method findAllByAgeBetween");
        return studentRepository.findAllByAgeBetween(minAge, maxAge).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public FacultyRecord getFacultyByStudentId(long id) {
        LOG.debug("was invoking method getFacultyByStudentId");
        Faculty faculty = studentRepository.findById(id).orElseThrow(() -> {
            LOG.error("There is not student with id = " + id);
            throw new StudentNotFoundException(id);
        }).getFaculty();
        return recordMapper.toRecord(faculty);
    }

    public Integer getStudentCount() {
        LOG.debug("was invoking method getStudentCount");
        return studentRepository.getStudentsCount();
    }

    public Double getStudentsAverageAge() {
        LOG.debug("was invoking method getStudentsAverageAge");
        return studentRepository.getStudentsAverageAge();
    }

    public Collection<StudentRecord> getLastFiveStudents() {
        LOG.debug("was invoking method getLastFiveStudents");
        return studentRepository.findLastFiveStudents().stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<String> findStudentsWithNameStartedA() {
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }

    public double findStudentAverageAge() {
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElse(0);

    }

    public void multiThread() {
        LOG.debug("was invoking method multiThreadMethod");
        List<Student> students = studentRepository.findAll(PageRequest.of(0, 6)).getContent();

        printNamesWithoutSynchronized(students.subList(0, 2));

        new Thread(() -> printNamesWithoutSynchronized(students.subList(2, 4))).start();
        new Thread(() -> printNamesWithoutSynchronized(students.subList(4, 6))).start();


    }

    private void printNamesWithoutSynchronized(List<Student> students) {
        students.forEach(s -> LOG.info(s.getName()));
    }

    public void multiThreadSecond() {
        LOG.debug("was invoking method multiThreadMethod2");
        List<Student> students = studentRepository.findAll(PageRequest.of(0, 6)).getContent();

        printNames(students.subList(0, 2));
        new Thread(() -> printNames(students.subList(2, 4))).start();
        new Thread(() -> printNames(students.subList(4, 6))).start();
    }

    private synchronized void printNames(List<Student> students) {
        students.forEach(s -> LOG.info(s.getName()));
    }


}

