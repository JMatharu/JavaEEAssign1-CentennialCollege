package jagdeep.divneet.asgn1.client;

import jagdeep.divneet.asgn1.dto.Course;
import jagdeep.divneet.asgn1.dto.Professor;

public class CourseTester {

	public static void main(String[] args) {
		CourseClient cc = null;
		try {
			if ( args.length > 1 ) {
				String hostIP = args[1];
				int port = Integer.parseInt(args[0]);
				cc = new CourseClient( port, hostIP);
			} else if ( args.length > 0 ) {
				int port = Integer.parseInt(args[0]);
				cc = new CourseClient(port);
			} else {
				cc = new CourseClient();
			}
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println();
		System.out.println("Testing getting a course by code");
		try {
			// replace COMP303 with a different course you added above
			String courseCode = "COMP303";
			System.out.println("Retrieving " + courseCode);
			System.out.println(cc.getCourse(courseCode));
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
		try {
			String courseCode = "MISC101";
			System.out.println("Retrieving " + courseCode);
			System.out.println(cc.getCourse(courseCode));
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Testing adding a course");
		try {
			System.out.println("Adding MISC101");
			System.out.println(cc.addCourse(new Course("MISC101", "Miscellaneous course")));
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		try {
			System.out.println("Adding MISC101 again");
			System.out.println(cc.addCourse(new Course("MISC101","Miscellaneous course")));
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Testing deleting a course");
		try {
			System.out.println("Deleting COMP311");
			System.out.println(cc.deleteCourse("COMP311"));
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		try {
			System.out.println("Deleting COMP311 again");
			System.out.println(cc.deleteCourse("COMP311"));
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Testing updating a course");
		System.out.println("Updating MISC101");
		try {
			Course course = cc.getCourse("MISC101");
			System.out.println("Updating course MISC101");
			// insert your details in the next line - you teach MISC101
			course.setProfessor(new Professor( "Your Name" ) );
			// Set a title of your choice
			course.setCourseTitle("What would you teach?");
			course = cc.updateCourse(course);
			System.out.println("Updated: " + course);
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		try {
			System.out
					.println("Updating a course that does not exist: ABCD123");
			Course course = new Course("ABCD123", "bogus course");
			cc.updateCourse(course);
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("tests complete");
	}
}
