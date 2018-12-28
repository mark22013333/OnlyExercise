package idv.onlycheng.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Subject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
 	private String address;
	

	private Set<Student> students = new HashSet<>();

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
