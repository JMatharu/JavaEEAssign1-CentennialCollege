package jagdeep.divneet.asgn1.server;

import jagdeep.divneet.asgn1.catalog.*;
import jagdeep.divneet.asgn1.dto.Course;
import jagdeep.divneet.asgn1.dto.Professor;
import jagdeep.divneet.asgn1.exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.activity.InvalidActivityException;

public class CourseServerThread implements Runnable {

	private Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;
	String request = null;

	public CourseServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {

			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			request = in.readLine().trim();
			System.out.println("{" + request + "}");
			// request starts "GET,"ADD","DELETE" OR "UPDATE"
			String command = request.substring(0, request.indexOf(" "))
					.toUpperCase();

			if (command.equals("GET")) {
				getCourse(request, out);
			} else if (command.equals("ADD")) {
				addCourse(command, out);
			} else if (command.equals("DELETE")) {
				deleteCourse(command, out);
			} else if (command.equals("UPDATE")) {
				updateCourse(command, out);
			} else {
				out.println("ERROR command" + command + "not supported");

			}
			socket.close();
		} catch (IOException e) {
			System.out.println("Server side I/O exception on socket");

		}

	}

	private void getCourse(String request, PrintWriter out) {
		try {
			String courseCode = request.substring(request.indexOf(" ")).trim();
			Course course = CourseManager.getInstance().getCourse(courseCode);
			out.println(course);
		} catch (CourseNotFoundException e) {
			out.println("ERROR " + e.getMessage());
		}
	}

	private void addCourse(String request, PrintWriter out) {
		try {
			Course course = buildCourse(request);
			CourseManager.getInstance().addCourse(course);
			out.println(course);
		} catch (DuplicateCourseException | InvalidDataException e) {
			out.println("ERROR " + e.getMessage());
		}
	}

	public void deleteCourse(String request, PrintWriter out) {
		try {
			String courseCode = request.substring(request.indexOf(" ")).trim();
			Course course = CourseManager.getInstance()
					.deleteCourse(courseCode);
			out.println(course);
		} catch (CourseNotFoundException e) {
			out.println("ERROR " + e.getMessage());
		}
	}

	public void updateCourse(String request, PrintWriter out) {
		try {
			Course course = buildCourse(request);
			CourseManager.getInstance().updateCourse(course);
			out.println(course);
		} catch (CourseNotFoundException | InvalidDataException e) {
			out.println("ERROR " + e.getMessage());
		}
	}

	public Course buildCourse(String request) throws InvalidDataException {
		// Get data from the request and build a local course object
		request = request.trim();

		String courseParts[] = request.split(" ");
		if (courseParts.length != 3) {
			if (courseParts[2] == null || courseParts[2].isEmpty()) {
				throw new InvalidDataException("Professor name missing...");
			}
		}
		String courseCode = courseParts[0];// =request.substring(request.indexOf(" ")).trim();
		String courseTitle = courseParts[1];// =request.substring(request.indexOf(" ")).trim();
		String profName = courseParts[2];
		// CourseCatalog cc = CatalogManager.getInstance();

		Professor pf = new Professor(profName);
		Course crse = null;
		try {
			crse = new Course(courseCode, courseTitle, pf);
		} catch (InvalidActivityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return crse;
		/*
		 * Course CrsObj=null;
		 * 
		 * String courseCode = request.substring(request.indexOf(" ")).trim();
		 * 
		 * String[] scourse = courseCode.split("-");
		 * 
		 * String sc = scourse[0]; Professor professor; if (scourse.length == 1)
		 * { try { CrsObj = new Course(sc); } catch (InvalidActivityException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); } } if
		 * (scourse.length == 2) { try { CrsObj = new Course(scourse[0],
		 * scourse[1]); } catch (InvalidActivityException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } } if
		 * (scourse.length == 3) { professor = new Professor(scourse[2]); try {
		 * CrsObj = new Course(scourse[0], scourse[1], professor); } catch
		 * (InvalidActivityException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } } else { throw new
		 * InvalidDataException("Should be a valid course"); } return CrsObj;
		 */
	}
}