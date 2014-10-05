package jagdeep.divneet.asgn1.exceptions;

public class DuplicateCourseException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicateCourseException() {
		// TODO Auto-generated constructor stub
	}

	public DuplicateCourseException(String courseDesc) {
		// TODO Auto-generated constructor stub
		super(courseDesc);
	}

}
