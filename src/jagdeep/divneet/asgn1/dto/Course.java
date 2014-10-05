package jagdeep.divneet.asgn1.dto;

import java.io.Serializable;

import jagdeep.divneet.asgn1.dto.Professor;
import jagdeep.divneet.asgn1.exceptions.InvalidDataException;

import javax.activity.InvalidActivityException;

public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	private String courseTitle;
	private Professor professer;
	private String courseCode;

	// Get Methods//
	private Object getProfessor() {
		// TODO Auto-generated method stub
		return professer;
	}

	private String getCourseTitle() {
		// TODO Auto-generated method stub
		return courseTitle;
	}

	public String getCourseCode() {
		// TODO Auto-generated method stub
		return courseCode;
	}

	// End of Get methods//

	// Set Course Methods//

	public void setCourseTitle(String courseTitle) {
		// TODO Auto-generated method stub
		this.courseTitle = courseTitle;

	}

	public void setProfessor(Professor professor) {
		// TODO Auto-generated method stub

	}

	public void setCourseCode(String courseCode) throws InvalidDataException {
		if (courseCode.equals(null) || courseCode.isEmpty()) {
			throw new InvalidDataException("Course should have a course code");
		}
		this.courseCode = courseCode;
	}

	// End of Set methods//

	// Constructors//
	public Course(String courseCode) throws InvalidActivityException {
		// TODO Auto-generated constructor stub
		super();
		setCourseTitle(courseCode);
	}

	public Course(String courseCode, String courseTitle)
			throws InvalidActivityException {
		// TODO Auto-generated constructor stub
		this.courseCode=courseCode;
		setCourseTitle(courseTitle);
	}

	public Course(String code, String title, Professor professor)
			throws InvalidDataException, InvalidActivityException {
		courseCode=code;
		courseTitle=title;
		this.professer = professor;
	}

	// End of constructors//

	public String toString() {
		if(professer == null){
			return String.format("%s-%s",courseCode, courseTitle);
		}else{
			return String.format("%s-%s-%s",courseCode, courseTitle, professer);
		}
		/*String output = getCourseCode() + ": [" + getCourseTitle() + "]";
		if (getProfessor() != null) {
			output += " Professor " + getProfessor();
		}
		return output;*/
	}

	public boolean equals(Object o){
		if(courseCode.equals(((Course) o).courseCode)){
			return true;
		}
		return false;
		
	}

}
