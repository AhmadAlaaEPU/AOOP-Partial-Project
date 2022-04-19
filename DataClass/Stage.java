package DataClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import API.API;

public class Stage {

	public String id;
	public String name;
	public ArrayList<String> coursesIds = new ArrayList<String>();

	public Stage(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Stage(String name) {
		this(UUID.randomUUID().toString(), name);
	}
	
	public Stage(String name, String ...coursesIds) {
		this(name);
		this.coursesIds.addAll(Arrays.asList(coursesIds));
	}
	
	public Stage(String id, String name, String ...coursesIds) {
		this(id, name);
		this.coursesIds.addAll(Arrays.asList(coursesIds));
	}
	
	public Course[] getCourses()
	{
		return API.getAllCoursesById(coursesIds);
	}
	
	public void delete()
	{
		API.deleteStageById(id);
	}
	
	public void addCourse(Course course)
	{
		API.addCourse(course);
		coursesIds.add(course.id);
		API.updateStageById(id, this);
	}
	
	
    @Override
    public String toString() {
        return name;
    }
}