package DataClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import API.API;

public class Student {

	public String id;
	public String fullName;
	public String mobileNumber;
	public Address address;
	public String stageId;
	public String departmentId;
	
	public ArrayList<String> coursesIds = new ArrayList<String>();
	
	public Student(String id, String fullName, String mobileNumber, Address address, String stageId, String departmentId, ArrayList<String> coursesIds) {
		this.id = id;
    	this.fullName = fullName;
    	this.mobileNumber = mobileNumber;
    	this.address = address;
    	this.stageId = stageId;
    	this.departmentId = departmentId;
    	this.coursesIds = coursesIds;
	}
	
	public Student(String fullName, String mobileNumber, Address address, String stageId, String departmentId, ArrayList<String> coursesIds) {
		this(UUID.randomUUID().toString(), fullName, mobileNumber, address, stageId, departmentId, coursesIds);
	}
	
	public Course[] getCourses()
	{
		return API.getAllCoursesById(this.coursesIds);
	}
	
	public Stage getStage()
	{
		return API.getStageById(stageId);
	}
	
	public Department getDepartment()
	{
		return API.getDepartmentById(departmentId);
	}
	
    @Override
    public String toString() {
        return fullName;
    }
}