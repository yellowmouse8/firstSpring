package ru.specialist.hibernateDAO;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernateDaoApp {
    public static void main (String[]args){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("hibernateContext.xml");
    CourseD courseDAOI = context.getBean(CourseD.class);
    for (CourseI c: courseDAOI.findAll()){
        System.out.println(c);
    }
    context.close();
    }
}
