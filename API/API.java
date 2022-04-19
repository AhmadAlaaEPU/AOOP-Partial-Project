package API;

import java.util.ArrayList;

import DataClass.*;
import Database.*;

public final class API {
	
	private API() {}
	
	//Custom Event handling for database changes
	
	private static ArrayList<IDatabaseChangeListener> listeners = new ArrayList<IDatabaseChangeListener>();

    public static void addListener(IDatabaseChangeListener toAdd) {
        listeners.add(toAdd);
    }

    public static void databaseUpdated() {
        System.out.println("Database Updated");

        // Notify everybody in listeners ArrayList
        for (IDatabaseChangeListener l : listeners)
            l.onDatabaseUpdated();
        
        Database.save();
    }

    public static void readDatabase()
    {
    	Database.read();
    }
    
    // **************************************
    //                Student
    // **************************************
	
	public static void deleteStudentById(String id) {
		Database.students.remove(id);
		databaseUpdated();
	}
	
	public static Student[] getStudentsByName (String fullName) {
				
		ArrayList<Student> tempList = new ArrayList<Student>();

		for(Student s : Database.students.values())
		{
			if(s.fullName.toLowerCase().contains(fullName.toLowerCase()))
			{
				tempList.add(s);
			}
		}
		
		return tempList.toArray(new Student[0]);		
	}
	
	public static Student[] getStudentsByMobileNumber (String mobileNumber) {
		
		ArrayList<Student> tempList = new ArrayList<Student>();

		for(Student s : Database.students.values())
		{
			if(s.mobileNumber.equals(mobileNumber))
			{
				tempList.add(s);
			}
		}
		
		return tempList.toArray(new Student[0]);
	}
	
	public static Student[] getStudentsByCourseName (String courseName) {
		
		ArrayList<Student> tempList = new ArrayList<Student>();

		for(Student s : Database.students.values())
		{
			for(Course c: s.getCourses())
			{
				if(c.name.toLowerCase().contains(courseName.toLowerCase()))
				{
					tempList.add(s);
				}
			}
		}
		
		return tempList.toArray(new Student[0]);
	}
	
	
	public static Student[] getAllStudents(){
		return Database.students.values().toArray(new Student[0]);
	}

	public static Student getStudentById(String studentID) {
		return Database.students.get(studentID);
	}
	
	public static void updateStudentById(String studentID, Student student) {
		Database.students.put(studentID, student);
		databaseUpdated();
	}
	
	public static void addStudent(Student student)
	{
    	Database.students.put(student.id, student);
    	databaseUpdated();
	}
	
    // **************************************
    //                Department
    // **************************************
	
	public static void deleteDepartmentById(String id) {
		Database.departments.remove(id);
		databaseUpdated();
	}
	
	public static Department[] getAllDepartment()
	{
		return Database.departments.values().toArray(new Department[0]);
	}
	
	public static Department getDepartmentById(String id)
	{
		return Database.departments.get(id);
	}
	
//	public static Department[] getAllDepartmentsById(ArrayList<String> departmentIds)
//	{
//		ArrayList<Department> tempList = new ArrayList<Department>();
//		
//		for(Department department : Database.departments.values())
//		{
//			for(String id : departmentIds)
//			{
//				if(department.id.equals(id))
//				{
//					tempList.add(department);
//					
//				}
//			}
//		}
//		
//		return tempList.toArray(new Department[0]);
//	}
	
	public static void addDepartment(Department department)
	{
    	Database.departments.put(department.id, department);
    	databaseUpdated();
	}
	
	public static void updateDepartmentById(String id, Department department)
	{
		Database.departments.put(id, department);
		databaseUpdated();
	}

    // **************************************
    //                Stage
    // **************************************
	
	public static void addStage(Stage stage)
	{
    	Database.stages.put(stage.id, stage);
    	databaseUpdated();
	}
	
	public static void deleteStageById(String id) {
		Database.stages.remove(id);
		databaseUpdated();
	}
	
	public static Stage getStageById(String id) {
		return Database.stages.get(id);
	}
	
	public static Stage[] getAllStagesById(ArrayList<String> stageIds)
	{
		ArrayList<Stage> tempList = new ArrayList<Stage>();
		
		for(Stage stage : Database.stages.values())
		{
			for(String id : stageIds)
			{
				if(stage.id.equals(id))
				{
					tempList.add(stage);
					
				}
			}
		}
		
		return tempList.toArray(new Stage[0]);
	}
	
	public static Stage getStageByName(Department department, String stageName)
	{
		Stage[] stages = department.getStages();
				
		for(Stage stage : stages)
		{
			if(stage.name.equals(stageName))
			{
				return stage;
			}
		}
		
		return null;
	}
	
	public static void updateStageById(String id, Stage stage)
	{
		Database.stages.put(id, stage);
		databaseUpdated();
	}
	
	
    // **************************************
    //                Course
    // **************************************
	
	public static void deleteCourseById(String id) {
		Database.courses.remove(id);
		databaseUpdated();
	}
	
	public static void addCourse(Course course)
	{
    	Database.courses.put(course.id, course);
    	databaseUpdated();
	}
	
	
	public static Course[] getAllCoursesById(ArrayList<String> courseIds)
	{
		ArrayList<Course> tempList = new ArrayList<Course>();
		
		for(Course course : Database.courses.values())
		{
			for(String id : courseIds)
			{
				if(course.id.equals(id))
				{
					tempList.add(course);
					
				}
			}
		}
		
		return tempList.toArray(new Course[0]);
	}


}