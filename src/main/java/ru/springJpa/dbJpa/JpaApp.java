package ru.springJpa.dbJpa;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.springJpa.dao.Course;
import ru.springJpa.dao.CourseDAO;

public class JpaApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("jpaContext.xml");
        CourseDAO courseDAO = context.getBean(CourseDAO.class);
        for (Course c : courseDAO.findAll()) {
            System.out.println(c);
            context.close();
        }
    }
}
