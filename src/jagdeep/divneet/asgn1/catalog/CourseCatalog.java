package jagdeep.divneet.asgn1.catalog;
import jagdeep.divneet.asgn1.dto.*;
import jagdeep.divneet.asgn1.exceptions.*;

public interface CourseCatalog {
	public Course addCourse(Course c) throws DuplicateCourseException;
	public Course getCourse(String courseCode) throws CourseNotFoundException;
	public Course updateCourse(Course c) throws CourseNotFoundException;
	public Course deleteCourse(String courseCode) throws CourseNotFoundException;

}
