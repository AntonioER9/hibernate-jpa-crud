package com.antonio.cruddemo.dao;

import com.antonio.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        return entityManager.createQuery("FROM Student order by lastName asc", Student.class).getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        return entityManager.createQuery("FROM Student where lastName = :lastName", Student.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        // This method is not strictly necessary as the entity manager will
        // automatically
        // track changes to the entity and update it when the transaction commits.
        // However, if you want to explicitly update, you can use:
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            entityManager.remove(student);
        }
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }

}
