// This Program showcases Student Information System
// when the program runs:
// 		First: data are loaded from disk into the database with the help of API method readDatabase()
//		Second: Course, Stage and Department object are created and linked together
//		Third: The information that is available in Database is presented
//	
//	Note: when running this program multiple times the displayed data for department is repeated multiple times
//			this is not a bug, but because object for department, stage and course are created without a fixed id.
//			an then loaded back at the beginning of the program.
//		as you may have noted this not the case for student object, we gave it a constant id at the constructor

//	if you run this program for the first time it will give you multiple errors for java.io.FileNotFoundException 
//	because we do not check if database CSV files exist when loading data from disk.


package CUI;

import API.*;
import DataClass.*;

public class CUI {

	public static void main(String[] args) {

		// Load data from disk
		API.readDatabase();
		
		// Create Courses, Stage and Department

		Course course1 = new Course("AOOP");
		Course course2 = new Course("Architecture");
		Course course3 = new Course("Statistics");
		Course course4 = new Course("Web");
		Course course5 = new Course("Multimedia");

		Stage stage = new Stage("Stage 2");
		stage.addCourse(course1);
		stage.addCourse(course2);
		stage.addCourse(course3);
		stage.addCourse(course4);
		stage.addCourse(course5);

		Department department = new Department("ISE");
		department.addStage(stage);

		API.addDepartment(department);

		// Create Student
		
		Address address = new Address("Kurdistan", "Erbil", "Gulan 203582", "345");

		Student student = new Student("ID 12345678", "Ahmad Alaa", "075012345678", address, stage.id, department.id,
				stage.coursesIds);

		API.addStudent(student);
		
		// Display Information

		Department[] departments = API.getAllDepartment();

		System.out.println("----------------------------------------");
		System.out.println("Department, Stage and Course Information");

		for (Department d : departments) {
			System.out.printf("%s%n", d.name);

			for (Stage s : d.getStages()) {
				System.out.printf("%10s%n", s.name);

				for (Course c : s.getCourses()) {
					System.out.printf("%20s%n", c.name);
				}
			}
		}

		System.out.println("Student Information");

		for (Student s : API.getAllStudents()) {
			System.out.printf("%s, %20s, %20s, %20s, %20s, %s%n", s.id, s.fullName, s.mobileNumber, s.address,
					s.getStage().name, s.getDepartment().name);
		}

	}

}
