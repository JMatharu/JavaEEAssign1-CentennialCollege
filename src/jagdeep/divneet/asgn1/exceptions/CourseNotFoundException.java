package jagdeep.divneet.asgn1.exceptions;

public class CourseNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CourseNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public CourseNotFoundException(String courseDesc) {
		// TODO Auto-generated constructor stub
		super(courseDesc);
	}

}
