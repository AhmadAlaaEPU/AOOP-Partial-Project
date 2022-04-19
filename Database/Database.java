package Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

import API.API;
import DataClass.Address;
import DataClass.Course;
import DataClass.Department;
import DataClass.Stage;
import DataClass.Student;

public final class Database {	
	
	private Database() {}
	
	public static LinkedHashMap<String, Student> students = new LinkedHashMap<String, Student>();
	public static LinkedHashMap<String, Department> departments = new LinkedHashMap<String, Department>();
	public static LinkedHashMap<String, Stage> stages = new LinkedHashMap<String, Stage>();
	public static LinkedHashMap<String, Course> courses = new LinkedHashMap<String, Course>();
    
	static String fileNameStudent = System.getProperty("user.dir") + "\\" + "StudentDatabase.csv";
	static String fileNameDepartment = System.getProperty("user.dir") + "\\" + "DepartmentDatabase.csv";
	static String fileNameCourse = System.getProperty("user.dir") + "\\" + "CourseDatabase.csv";
	static String fileNameStage = System.getProperty("user.dir") + "\\" + "StageDatabase.csv";
	
	static String COMMA_DELIMITER = ",";
	static String NEW_LINE_SEPARATOR = "\n";
	
    public static void save() {
		
    	// SAVE COURSES
    	
    	// https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
    	// The try-with-resources Statement
    	
		try (FileWriter writer = new FileWriter(new File(fileNameCourse))) {
			
			writer.append("Course ID, Course Name");
			writer.append(NEW_LINE_SEPARATOR);
			
			for(Course course : courses.values()) {
				writer.append(String.valueOf(course.id));
				writer.append(COMMA_DELIMITER);
				writer.append(String.valueOf(course.name));
				writer.append(NEW_LINE_SEPARATOR);
			}
			
			System.out.println("Course CSV file was written successfully!");
			
		} 
		catch(Exception e) {
			System.out.println("Error in CSV Writer (Course Database.csv)!!!");
			e.printStackTrace();
		}
		
    	// SAVE STAGE
    	
		try (FileWriter writer = new FileWriter(new File(fileNameStage))) {
			
			writer.append("Stage ID, Stage Name, Course Ids");
			writer.append(NEW_LINE_SEPARATOR);
			
			for(Stage stage : stages.values()) {
				writer.append(String.valueOf(stage.id));
				writer.append(COMMA_DELIMITER);
				writer.append(String.valueOf(stage.name));
				writer.append(COMMA_DELIMITER);
				writer.append(String.join(";", stage.coursesIds));
				writer.append(NEW_LINE_SEPARATOR);
			}
			
			System.out.println("Stage CSV file was written successfully!");
			
		} 
		catch(Exception e) {
			System.out.println("Error in CSV Writer (Stage Database.csv)!!!");
			e.printStackTrace();
		}
		
		// SAVE DEPARTMENT DATA
		
		try (FileWriter writer = new FileWriter(new File(fileNameDepartment))) {
			
			writer.append("ID, Name, Stage");
			writer.append(NEW_LINE_SEPARATOR);
			    			
			for(Department department : departments.values())
			{		
				writer.append(String.valueOf(department.id));
				writer.append(COMMA_DELIMITER);
				
				writer.append(String.valueOf(department.name));
				writer.append(COMMA_DELIMITER);

				writer.append(String.join(";", department.stagesIds));
    			writer.append(NEW_LINE_SEPARATOR);
			}
			
			System.out.println("Department CSV file was written successfully!");
			
		} 
		catch(Exception e) {
			System.out.println("Error in CSV Writer (Department Database.csv)!!!");
			e.printStackTrace();
		}

		
    	// SAVE STUDENT DATA
    	
		try (FileWriter writer = new FileWriter(new File(fileNameStudent))) {
			
			writer.append("ID, FullName, Address ,Courses, Department, MobileNumber, Stage");
			writer.append(NEW_LINE_SEPARATOR);
			
			for(Student temp : students.values()) {
				writer.append(String.valueOf(temp.id));
				writer.append(COMMA_DELIMITER);
				writer.append(temp.fullName);
				writer.append(COMMA_DELIMITER);
				
				String addressString = String.join(";", temp.address.country, temp.address.city, temp.address.street);
				writer.append(String.valueOf(addressString));
				writer.append(COMMA_DELIMITER);
				    				
				writer.append(String.join(";", temp.coursesIds));
				writer.append(COMMA_DELIMITER);
				writer.append(String.valueOf(temp.departmentId));	    		
				writer.append(COMMA_DELIMITER);
				writer.append(String.valueOf(temp.mobileNumber));
				writer.append(COMMA_DELIMITER);
				
				writer.append(String.valueOf(temp.stageId));
				writer.append(NEW_LINE_SEPARATOR);
				
			}
			
			System.out.println("Student CSV file was written successfully!");
			
		} 
		catch(Exception e) {
			System.out.println("Error in CSV Writer (Student Database.csv)!!!");
			e.printStackTrace();
		}
		
		
		
		

    }
    

    public static void read() {
        
    	// READ COURSE DATA
    	
    	courses.clear();
    		        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileNameCourse))) 
        {            
            //first line is the headers, we read once to ignore the first line
            reader.readLine();
         
            String line = "";  

            while ((line = reader.readLine()) != null) {
                
                String[] cell = line.split(COMMA_DELIMITER);
                
                if (cell.length > 0) {
                	//Create a new Employee object and fill it's data
                	String id = cell[0];
                	String name = cell[1];
                	Course course = new Course(id, name);

					courses.put(course.id, course );
				}
            }
            
        }
        catch (Exception e) {
        	System.out.println("Error while creating FileReader or BufferedReader (Course CSV)");
            e.printStackTrace();
        }
        
        // READ STAGE DATA
    	
    	stages.clear();
    		        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileNameStage))) 
        {            
            //first line is the headers, we read once to ignore the first line
            reader.readLine();
         
            String line = "";  

            while ((line = reader.readLine()) != null) {
                
                String[] cell = line.split(COMMA_DELIMITER);
                
                if (cell.length > 0) {
                	String id = cell[0];
                	String name = cell[1];
                	String course = (cell.length == 3) ? cell[2] : "";
                	
                	Stage stage = new Stage(id, name, course.split(";"));

					stages.put(stage.id, stage );
				}
            }
            

        }
        catch (Exception e) {
        	System.out.println("Error while creating FileReader or BufferedReader (Student CSV)");
            e.printStackTrace();
        }
        

        // READ DEPARTMENT DATA
        
        departments.clear();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileNameDepartment))) 
        {            
            //first line is the headers, we read once to ignore the first line
            reader.readLine();
         
            String line = "";  

            while ((line = reader.readLine()) != null) {
                
                String[] cell = line.split(COMMA_DELIMITER);
                
                if (cell.length > 0) {                	
                	String departmentId = cell[0];
                	String departmentName = cell[1];
                	String[] stageIds = cell[2].split(";");
                	
                	Department currentDepartment = new Department(departmentId, departmentName, stageIds);
                	departments.put(currentDepartment.id, currentDepartment);
                	
				}
            }
            
            System.out.println("Department was Read");
        
        
        } 
        catch (Exception e) {
        	System.out.println("Error while creating FileReader or BufferedReader (Department CSV)");
            e.printStackTrace();
        }
        
        
        // READ STUDENT DATA
    	
    	students.clear();
    		        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileNameStudent))) 
        {            
            //first line is the headers, we read once to ignore the first line
            reader.readLine();
         
            String line = "";  

            while ((line = reader.readLine()) != null) {
                
                String[] cell = line.split(COMMA_DELIMITER);
                
                if (cell.length > 0) {
                	//Create a new Employee object and fill it's data
                	
                	String id = cell[0];
                	String fullname = cell[1];
                	String[] addressStr = cell[2].split(";");
                	String[] courseIdsStr = cell[3].split(";");
                	String departmentId = cell[4];
                	String mobileNumber = cell[5];
                	String stageId = cell[6];
                	
                	ArrayList<String> courseIds = new ArrayList<String>();
                	Collections.addAll(courseIds, courseIdsStr);
                	
                	Address address = new Address(addressStr[0], addressStr[1], addressStr[2], "");
            		
					Student student = new Student(id, fullname, mobileNumber , address, stageId , departmentId, courseIds);

					students.put(student.id, student );
				}
            }
            

        }
        catch (Exception e) {
        	System.out.println("Error while creating FileReader or BufferedReader (Student CSV)");
            e.printStackTrace();
        }
        
        
        API.databaseUpdated();

        
    }
    
	
}

