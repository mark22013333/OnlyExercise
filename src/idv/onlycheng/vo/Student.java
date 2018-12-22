package idv.onlycheng.vo;

import java.io.Serializable;

public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 552463120560519099L;

	private int id;
	private String name;
	private int age;
	private String sex;
	private Cluss cluss;

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *               set id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *                 set name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *                set age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *                set sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return cluss
	 */
	public Cluss getCluss() {
		return cluss;
	}

	/**
	 * @param cluss
	 *                  set cluss
	 */
	public void setCluss(Cluss cluss) {
		this.cluss = cluss;
	}

	/**
	 * @return serialVersionUID
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
