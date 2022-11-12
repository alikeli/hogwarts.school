package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.entity.Faculty;
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
    @GetMapping
    public Collection<FacultyRecord> getFacultyByColor(@RequestParam String color) {
        return facultyService.getFacultyByColor(color);
    }



}
