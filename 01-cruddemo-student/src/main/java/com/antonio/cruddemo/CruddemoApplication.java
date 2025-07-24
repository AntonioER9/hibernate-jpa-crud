package com.antonio.cruddemo;

import com.antonio.cruddemo.dao.StudentDAO;
import com.antonio.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {

		return runner -> {
			// createStudent(studentDAO);
			createMultipleStudents(studentDAO);
		};

	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating new student objects...");
		Student student1 = new Student("Antonio", "Espinoza", "antonio@gmail.com");
		Student student2 = new Student("Maria", "Garcia", "maria@gmail.com");
		Student student3 = new Student("John", "Doe", "john@gmail.com");

		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);

		System.out.println("Saved students. Generated ids: ");
		System.out.println(student1.getId());
		System.out.println(student2.getId());
		System.out.println(student3.getId());
	}

	private void createStudent(StudentDAO studentDAO) {

		// create the student object

		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Antonio", "Espinoza", "antonio@gmail.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("saved student. Generated id: " + tempStudent.getId());

	}
}
