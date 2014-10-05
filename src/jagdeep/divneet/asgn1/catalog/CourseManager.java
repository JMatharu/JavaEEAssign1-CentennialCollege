package jagdeep.divneet.asgn1.catalog;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jagdeep.divneet.asgn1.dto.Course;
import jagdeep.divneet.asgn1.exceptions.CourseNotFoundException;
import jagdeep.divneet.asgn1.exceptions.DuplicateCourseException;

public class CourseManager implements CourseCatalog {
	private static CourseManager instance = null;
	private static Map<String, Course> courses = null;

	// ArrayList<String> courses = new ArrayList<String>();

	public CourseManager() {
		// TODO Auto-generated constructor stub
		if (courses == null) {
			courses = new ConcurrentHashMap<String, Course>();
		}
	}
	
	@Override
	public Course addCourse(Course c) throws DuplicateCourseException {
		// TODO Auto-generated method stub
		if(courses.containsKey(c.getCourseCode())){
			throw new DuplicateCourseException("Code Course is Duplicate");
		}
		else{
			courses.put(c.getCourseCode(), c);
			return c;
		}
	}

	//public Course addCourse(Course c) throws DuplicateCourseException {
		// if(courses.contains(c.getCourseCode())){
		// throw new DuplicateCourseException("Course code is Duplicate "
		// + c.getCourseCode());
		// }else {
		// courses.add(e)
		// }

		// TODO Auto-generated method stub
	//	String code = c.getCourseCode();
		//
		 // if (c == null) { throw new
		 //DuplicateCourseException("Null course cannot be added"); }
		 //if (courses.containsKey(code)) {

		//	throw new DuplicateCourseException("Course code is Duplicate "
	//				+ code);
		//} else {
	//		courses.put(code, c);
	//	}
		//System.out.println("test");
		//courses.put(code, c);
		//return c;

//	}

	@Override
	public Course getCourse(String courseCode) throws CourseNotFoundException {
		// TODO Auto-generated method stub
		Course obj;
		obj=courses.get(courseCode);
		if(obj==null){
			throw new CourseNotFoundException("Course does not exists");
		}
		else{
			return obj;
		}
		/*		if (courseCode == null) {
			throw new CourseNotFoundException(
					"There is no course with null courseCode");
		}
		if (courses.containsKey(courseCode)) {
			return courses.get(courseCode);
		} else {
			throw new CourseNotFoundException("No course with code "
					+ courseCode);
		}
*/
	}

	@Override
	public Course updateCourse(Course c) throws CourseNotFoundException {
		// TODO Auto-generated method stub
		Course obj;
		obj=courses.get(c.getCourseCode());
		if(obj==null){
			throw new CourseNotFoundException("Course does not exists");
		}
		else{
			courses.remove(c.getCourseCode());
			courses.put(c.getCourseCode(),c);
			return c;
		}
		/*	if (c == null) {
			throw new CourseNotFoundException("Null Course cannot be updated");
		}
		Course oldC = getCourse(c.getCourseCode());
		if (c.equals(oldC)) {
			return c;
		}
		courses.put(c.getCourseCode(), c);
		return getCourse(c.getCourseCode());
*/
	}

	@Override
	public Course deleteCourse(String courseCode)
			throws CourseNotFoundException {
		// TODO Auto-generated method stub
		Course obj;
		obj=courses.get(courseCode);
		if(obj==null){
			throw new CourseNotFoundException("Course does not exists");
		}
		else{
			courses.remove(courseCode);
			return obj;
		}
		/*		if (courseCode == null) {
			throw new CourseNotFoundException(
					"There is no course with null courseCode");
		}
		if (courses.containsKey(courseCode)) {
			Course c = courses.get(courseCode);
			courses.remove(courseCode);
			return c;
		} else {
			throw new CourseNotFoundException("No Course with code "
					+ courseCode);
		}
*/
	}

	public synchronized static CourseManager getInstance() {
		// TODO Auto-generated method stub
		if (instance == null) {
			instance = new CourseManager();
		}
		return instance;

	}

	public Collection<Course> getAllCourses() {
		// TODO Auto-generated method stub
		 Collection<Course> cs=courses.values();
			return cs;
		//return courses.values();
	}



}
