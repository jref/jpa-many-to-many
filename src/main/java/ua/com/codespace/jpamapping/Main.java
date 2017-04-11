package ua.com.codespace.jpamapping;

import ua.com.codespace.jpamapping.model.Author;
import ua.com.codespace.jpamapping.model.Book;
import ua.com.codespace.jpamapping.model.Course;
import ua.com.codespace.jpamapping.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        //prepare books and authors
        Author rowling = new Author("Joanne Rowling");
        Author fitzgerald = new Author("F. Scott Fitzgerald");
        Book harryPotter = new Book("Harry Potter");
        harryPotter.setAuthors(Collections.singletonList(rowling));
        rowling.setBook(Collections.singletonList(harryPotter));
        Book gatsby = new Book("The Great Gatsby");
        gatsby.setAuthors(Collections.singletonList(fitzgerald));
        fitzgerald.setBook(Collections.singletonList(gatsby));

        //prepare students and course
        Course course1 = new Course("Math");
        Course course2 = new Course("Biology");
        Student student1 = new Student("Petro");
        Student student2 = new Student("Ivan");
        student1.setCourses(Arrays.asList(course1, course2));
        student2.setCourses(Arrays.asList(course1, course2));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("h2-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(rowling);
        entityManager.persist(fitzgerald);

        entityManager.persist(student1);
        entityManager.persist(student2);
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
