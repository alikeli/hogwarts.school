package ru.hogwarts.school.record;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class StudentRecord {
    private Long id;
    @NotBlank(message = "Имя студента должно быть заполнено!")
    private  String name;
    @Min(value = 11, message = "Минимальный возраст студента 11 лет!")
    @Max(value = 25, message = "Мфксимальный возраст студента 20 лет!")
    private  int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
