package jagdeep.divneet.asgn1.client;

import jagdeep.divneet.asgn1.catalog.CourseCatalog;
import jagdeep.divneet.asgn1.dto.Course;
import jagdeep.divneet.asgn1.dto.Professor;
import jagdeep.divneet.asgn1.exceptions.CourseNotFoundException;
import jagdeep.divneet.asgn1.exceptions.DuplicateCourseException;
import jagdeep.divneet.asgn1.exceptions.InvalidDataException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.UnknownHostException;

import javax.activity.InvalidActivityException;

public class CourseClient implements CourseCatalog {
	int port;
	InetAddress host;
	static final int DEFAULT_PORT = 12345;
	static final String hostIP = "127.0.0.1";

	public CourseClient() throws UnknownHostException {
		super();
		port = DEFAULT_PORT;
		try {
			host = InetAddress.getLocalHost();
		} catch (java.net.UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public CourseClient(int port) throws UnknownHostException {
		super();
		this.port = port;
		try {
			host = InetAddress.getLocalHost();
		} catch (java.net.UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public CourseClient(int port, String hostIP) throws UnknownHostException {
		super();
		this.port = port;
		try {
			host = InetAddress.getByName(hostIP);
		} catch (java.net.UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Course buildCourse(String courseDesc) throws InvalidDataException {
		// build and return a Course object using data supplied by server
		Course CrsObj=null;

		

		String[] scourse = courseDesc.split("-");

		String sc = scourse[0];
		Professor professor;
		if (scourse.length == 1) {
			try {
				CrsObj = new Course(sc);
			} catch (InvalidActivityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (scourse.length == 2) {
			try {
				CrsObj = new Course(scourse[0], scourse[1]);
			} catch (InvalidActivityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (scourse.length == 3) {
			professor = new Professor(scourse[2]);
			try {
				CrsObj = new Course(scourse[0], scourse[1], professor);
			} catch (InvalidActivityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new InvalidDataException("Should be a valid course");
		}
		return CrsObj;
	}

	@Override
	public Course getCourse(String courseCode) throws CourseNotFoundException {
		try {
			Socket socket = new Socket(host, port);
			// Create PrintWriter: 2nd arg = true to autoflush buffer
			PrintWriter socketOut = new PrintWriter(socket.getOutputStream(),
					true);
			socketOut.println("GET " + courseCode);
			System.out.println("REQUEST  GET " + courseCode);
			// read response from server
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String courseDesc = socketIn.readLine().trim();
			System.out.println("RESPONSE " + courseDesc);
			socket.close();
			if (courseDesc.substring(0, 5).equals("ERROR")) {
				throw new CourseNotFoundException(courseDesc);
			}
			// return new Course object built from from course description
			return buildCourse(courseDesc);
		} catch (IOException e) {
			throw new CourseNotFoundException("Socket I/O exception");
		} catch (InvalidDataException e) {
			throw new CourseNotFoundException(e.getMessage());
		}
	}

	@Override
	public Course addCourse(Course c) throws DuplicateCourseException {
		try {
			Socket socket = new Socket(host, port);
			// Create PrintWriter: 2nd arg = true to autoflush buffer
			PrintWriter socketOut = new PrintWriter(socket.getOutputStream(),
					true);
			socketOut.println("ADD " + c);
			System.out.println("REQUEST  ADD " + c);
			// read response from server
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String courseDesc = socketIn.readLine().trim();
			System.out.println("RESPONSE " + courseDesc);
			socket.close();
			if (courseDesc.substring(0, 5).equals("ERROR")) {
				throw new DuplicateCourseException(courseDesc);
			}
			// return new Course object built from from course description
			return buildCourse(courseDesc);
		} catch (IOException e) {
			throw new DuplicateCourseException("Socket I/O exception");
		} catch (InvalidDataException e) {
			throw new DuplicateCourseException(e.getMessage());
		}
	}

	@Override
	public Course deleteCourse(String courseCode)
			throws CourseNotFoundException {
		try {
			Socket socket = new Socket(host, port);
			// Create PrintWriter: 2nd arg = true to autoflush buffer
			PrintWriter socketOut = new PrintWriter(socket.getOutputStream(),
					true);
			socketOut.println("DELETE " + courseCode);
			System.out.println("DELETE " + courseCode);
			// read response from server
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String courseDesc = socketIn.readLine().trim();
			System.out.println("RESPONSE " + courseDesc);
			socket.close();
			if (courseDesc.substring(0, 5).equals("ERROR")) {
				throw new CourseNotFoundException(courseDesc);
			}
			// return new Course object built from from course description
			return buildCourse(courseDesc);
		} catch (IOException e) {
			throw new CourseNotFoundException("Socket I/O exception");
		} catch (InvalidDataException e) {
			throw new CourseNotFoundException(e.getMessage());
		}
	}

	@Override
	public Course updateCourse(Course c) throws CourseNotFoundException {
		try {
			Socket socket = new Socket(host, port);
			// Create PrintWriter: 2nd arg = true to autoflush buffer
			PrintWriter socketOut = new PrintWriter(socket.getOutputStream(),
					true);
			socketOut.println("UPDATE " + c);
			System.out.println("REQUEST UPDATE " + c);
			// read response from server
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String courseDesc = socketIn.readLine().trim();
			System.out.println("RESPONSE " + courseDesc);
			socket.close();
			if (courseDesc.substring(0, 5).equals("ERROR")) {
				throw new CourseNotFoundException(courseDesc);
			}
			// return new Course object built from from course description
			return buildCourse(courseDesc);
		} catch (IOException e) {
			throw new CourseNotFoundException("Socket I/O exception");
		} catch (InvalidDataException e) {
			throw new CourseNotFoundException(e.getMessage());
		}
	}

}
