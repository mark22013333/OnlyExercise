package idv.onlycheng.vo;

import java.io.Serializable;

//如果組合主鍵的欄位是類別的屬性時，該類別必須實現Serializable

public class Result implements Serializable {
	private static final long serialVersionUID = 2618876901311067676L;

	private Student student;
	private Subject subject;
	private double score;

	public double getScore() {
		return score;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public void setScore(double score) {
		this.score = score;
	}

}
