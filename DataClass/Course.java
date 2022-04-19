package DataClass;

import java.util.UUID;

public class Course {
	
	public String id;
	public String name;
	
	public Course(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Course(String name) {
		this(UUID.randomUUID().toString(), name);
	}

    @Override
    public String toString() {
        return name;
    }
}