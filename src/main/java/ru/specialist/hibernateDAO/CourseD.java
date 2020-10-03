package ru.specialist.hibernateDAO;


import java.util.List;

public interface CourseD {
    CourseI findById(int id);

    List<CourseI> findAll();

    void insert(CourseI course);

    void update(CourseI course);

    void delete(int id);
}
