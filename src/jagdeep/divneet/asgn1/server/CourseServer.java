package jagdeep.divneet.asgn1.server;

import jagdeep.divneet.asgn1.catalog.CourseManager;
import jagdeep.divneet.asgn1.catalog.CourseCatalog;
import jagdeep.divneet.asgn1.dto.Professor;
import jagdeep.divneet.asgn1.dto.Course;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;

public class CourseServer {
	private static int port;
	static final int DEFAULT_PORT = 12345;


	public static CourseCatalog buildCatalog() {
		CourseCatalog cm = CourseManager.getInstance();
		System.out.println("Building course catalog");
		try {
			// replace up to catch to add courses you are taking this term

			Professor professor = new Professor("Paula McMillan");
			//System.out.println(cm);
			cm.addCourse(new Course("COMP303", "Java EE Programming", professor));
			cm.addCourse(new Course("COMP231", "Computer Programmer Project"));
			cm.addCourse(new Course("COMP309", "Data WareHouse And Mining"));
			cm.addCourse(new Course("COMP307", "Software Security"));
			cm.addCourse(new Course("COMP306", "Web Service Programming"));
			cm.addCourse(new Course("COMP304", "Wireless Programming"));
			// System.out.println("inside");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}

		System.out.println();
		System.out.println("Testing get all courses");
		try {
			System.out.println("Course catalog:");
			Collection<Course> courses = ((CourseManager) cm).getAllCourses();
			for (Course course : courses) {
				System.out.println(course);
			}
			System.out.println("done");
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return cm;
	}

	public static void main(String[] args) {
		// initialize the course catalog
		//CourseCatalog cm = buildCatalog();
		buildCatalog();
		// initialize the server socket
		// use default port if no command line arguments
		port = DEFAULT_PORT;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		// create a server socket bound to port
		ServerSocket ss;
		try {
			ss = new ServerSocket(port);
			System.out.println("Course server running on port "
					+ ss.getLocalPort());
			while (true) {
				// main loop -- listen for requests
				Socket socket = ss.accept();

				// spawn a thread to handle each request
				CourseServerThread cst = new CourseServerThread(socket);
				Thread t = new Thread(cst);
				t.start();
			}
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}
}