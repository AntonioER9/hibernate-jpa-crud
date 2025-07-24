package com.antonio.cruddemo;

import com.antonio.cruddemo.dao.StudentDAO;
import com.antonio.cruddemo.entity.Student;

import java.util.List;

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
			// createMultipleStudents(studentDAO);
			// readStudent(studentDAO);
			// findAllStudents(studentDAO);
			// findByLastName(studentDAO);
			// updateStudent(studentDAO);
			// deleteStudent(studentDAO);
			deleteAllStudents(studentDAO);

		};

	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted " + numRowsDeleted + " students");
	}

	private void deleteStudent(StudentDAO studentDAO) {
		System.out.println("Deleting student with ID: 1");
		studentDAO.deleteById(1);
		System.out.println("Deleted student with ID: 1");
	}

	private void updateStudent(StudentDAO studentDAO) {
		// get the student from the database
		Student student = studentDAO.findById(1);
		if (student != null) {
			// update the student details
			student.setFirstName("UpdatedFirstName");
			student.setLastName("UpdatedLastName");
			student.setEmail("updatedemail@gmail.com");
			studentDAO.update(student);
			System.out.println("Updated student: " + student);
		}
	}

	private void findByLastName(StudentDAO studentDAO) {
		// get a list of students
		List<Student> students = studentDAO.findByLastName("Antonio");
		for (Student student : students) {
			System.out.println("Found student: " + student);
		}
	}

	private void findAllStudents(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findAll();
		// System.out.println("Found students: " + students);

		for (Student student : students) {
			System.out.println("Student: " + student);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("Reading student with ID: 1");
		Student student = studentDAO.findById(1);
		System.out.println("Found student: " + student);
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
