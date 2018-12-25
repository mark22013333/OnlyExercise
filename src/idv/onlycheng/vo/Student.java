package idv.onlycheng.vo;

import java.io.Serializable;

public class Student implements Serializable {

	private static final long serialVersionUID = 552463120560519099L;

	private int id;
	private String name;
	private int age;
	private String sex;
	private Cluss cluss;

	public Cluss getCluss() {
		return cluss;
	}

	public void setCluss(Cluss cluss) {
		this.cluss = cluss;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
