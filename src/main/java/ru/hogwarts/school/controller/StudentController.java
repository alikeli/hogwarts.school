package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.record.StudentRecord;
import ru.hogwarts.school.service.StudentService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentRecord createStudent(@RequestBody @Valid StudentRecord studentRecord) {
        return studentService.addStudent(studentRecord);
    }

    @GetMapping("/{id}")
    public StudentRecord getStudent(@PathVariable Long id) {
        return studentService.findStudent(id);
    }


    @PutMapping("/{id}")
    public StudentRecord editStudent(@PathVariable Long id, @RequestBody @Valid StudentRecord studentRecord) {
        return studentService.editStudent(id, studentRecord);
    }


    @DeleteMapping("/{id}")
    public StudentRecord deleteStudent(@PathVariable Long id) {
        return studentService.removeStudent(id);
    }

    @GetMapping
    public Collection<StudentRecord> getStudentByAge(@RequestParam Integer age) {
        return studentService.getStudentByAge(age);
    }
    @GetMapping({"min", "max"})
    public Collection<StudentRecord> getStudentByAgeBetween(@RequestParam("min") Integer minAge,
                                                            @RequestParam("max") Integer maxAge) {
        return studentService.findAllByAgeBetween(minAge, maxAge);
    }

    @GetMapping("/{id}/faculty")
    public ResponseEntity<FacultyRecord> getFacultyOfStudentById(@PathVariable long id) {
        return ResponseEntity.ok(studentService.getFacultyByStudentId(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getStudentCount(){
        return ResponseEntity.ok(studentService.getStudentCount());
    }

    @GetMapping("/average-age")
    public ResponseEntity<Double> getStudentsAverageAge(){
        return ResponseEntity.ok(studentService.getStudentsAverageAge());
    }

    @GetMapping("/last-five")
    public ResponseEntity<Collection<StudentRecord>> getLastFiveStudents(){
        return ResponseEntity.ok(studentService.getLastFiveStudents());
    }



}