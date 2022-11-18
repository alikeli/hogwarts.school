package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.record.StudentRecord;
import ru.hogwarts.school.service.FacultyService;

import javax.validation.Valid;
import java.util.Collection;


@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public FacultyRecord addFaculty(@RequestBody @Valid FacultyRecord facultyRecord) {
        return facultyService.addFaculty(facultyRecord);
    }

    @GetMapping("/{id}")
    public FacultyRecord getFaculty(@PathVariable Long id) {
        return facultyService.findFacultyById(id);
    }


    @PutMapping("/{id}")
    public FacultyRecord editFaculty(@PathVariable Long id,
                                     @RequestBody @Valid FacultyRecord facultyRecord) {
        return facultyService.editFaculty(id, facultyRecord);
    }

    @DeleteMapping("/{id}")
    public FacultyRecord deleteFaculty(@PathVariable Long id) {
        return facultyService.removeFaculty(id);
    }

    @GetMapping(params = "!colorOrName")
    public Collection<FacultyRecord> getFacultyByColor(@RequestParam String color) {
        return facultyService.getFacultyByColor(color);
    }

    @GetMapping(params = "colorOrName")
    public Collection<FacultyRecord> getFacultyByColorOrName(@RequestParam String colorOrName) {
        return facultyService.findAllByColorOrName(colorOrName);
    }

    @GetMapping("/{id}/students")
    public Collection<StudentRecord> findStudentsByFaculty(@PathVariable Long id) {
        return facultyService.findStudentsByFaculty(id) ;

    }


}
