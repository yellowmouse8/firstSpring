package ru.springJpa.dao;




import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Service("jpaCourseService")
@Transactional(isolation = Isolation.READ_COMMITTED)
@Repository
public class JPACourseDAO implements CourseDAO {
    private static final Log LOG = LogFactory.getLog(JPACourseDAO.class);
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public Course findById(int id) {
        return em.find(Course.class, id);
    }

    @Override
    public List<Course> findAll() {
        return em.createQuery("select c from Course c", Course.class).getResultList();
    }

    @Override
    public List<Course> findByTitle(String title) {
        TypedQuery <Course> query = em.createQuery("select c from Course c where c.title LIKE :title"
                , Course.class);
            query.setParameter("title", "%"+title.trim()+"%");
        return query.getResultList();
    }

    @Override
    public void insert(Course course) {
    em.persist(course);
    LOG.info(" Course saved with id: " + course.getId());
    }

    @Override
    public void update(Course course) {
    if (course.getId() != 0 && em.find(Course.class, course.getId()) != null){
        Course updatedCourse = em.merge(course);
    }
    LOG.info("Course updated with id: " + course.getId());
    }

    @Override
    public void delete(int id) {
    em.remove(findById(id));
    LOG.info("Course deleted with id:" + id);
    }
}
