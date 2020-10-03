package ru.specialist.hibernateDAO;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository("courseD")
public class HibernateCourseDAO implements CourseD {
    private static final Log LOG = LogFactory.getLog(HibernateCourseDAO.class);
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public CourseI findById(int id) {
        return (CourseI) getSessionFactory().getCurrentSession().byId(CourseI.class).load(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseI> findAll() {
        return getSessionFactory().getCurrentSession().createQuery("from ru.specialist.hibernateDAO.CourseI c").list();
    }

    @Override
    public void insert(CourseI course) {
        getSessionFactory().getCurrentSession().save(course);
        LOG.info(" Course saved with id: " + course.getId());
    }

    @Override
    public void update(CourseI course) {
        getSessionFactory().getCurrentSession().update(course);
        LOG.info(" Course updated with id: " + course.getId());
    }

    @Override
    public void delete(int id) {
        CourseI c = new CourseI();
        c.setId(id);
        getSessionFactory().getCurrentSession().delete(c);
        LOG.info(" Course deleted with id: " + c.getId());
    }
}
