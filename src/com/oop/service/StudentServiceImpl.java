package com.oop.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.oop.model.Student;
import com.oop.util.CommonConstants;
import com.oop.util.CommonUtil;
import com.oop.util.DBConnectionUtil;
import com.oop.util.QueryUtil;

public class StudentServiceImpl implements IStudentService {
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(StudentServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;

	//static{
		//create table or drop if exist
		//createStudentTable();
	//}
	
	private PreparedStatement preparedStatement;

	/**
	 * This method initially drop existing Students table in the database and
	 * recreate table structure to insert student entries
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error
	 * @throws NullPointerException
	 *             - Service is not available
	 * 
	 */
	
	public static void createStudentTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_TABLE));
			// Create new students table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_TABLE));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
	
	
	/**
	 * Add set of students for as a batch from the selected student List
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * 
	 */
	@Override
	public void addStudent(Student student) {

		//String studentID = CommonUtil.generateIDs(getStudentIDs());
		
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Query is available in StudentQuery.xml file and use
			 * insert_student key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_EMPLOYEES));
			connection.setAutoCommit(false);
			
			//Generate student IDs
			//student.setStudentID(studentID);
			//preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, student.getStudentID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, student.getName());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, student.getEmail());
			// Add student
			preparedStatement.execute();
			connection.commit();

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/**
	 * Student details can be retrieved based on the provided student ID
	 * 
	 * @param studentID
	 *            - student details are filtered based on the ID
	 * 
	 * @see #actionOnStudent()
	 */
	@Override
	public Student getStudentByID(String studentID) {

		return actionOnStudent(studentID).get(0);
	}
	
	/**
	 * Get all list of students
	 * 
	 * @return ArrayList<Student> 
	 * 						- Array of student list will be return
	 * 
	 * @see #actionOnStudent()
	 */
	@Override
	public ArrayList<Student> getStudents() {
		
		return actionOnStudent(null);
	}

	/**
	 * This method delete an student based on the provided ID
	 * 
	 * @param studentID
	 *            - Delete student according to the filtered student details
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 */
	@Override
	public void removeStudent(String studentID) {

		// Before deleting check whether student ID is available
		if (studentID != null && !studentID.isEmpty()) {
			/*
			 * Remove student query will be retrieved from StudentQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_EMPLOYEE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, studentID);
				preparedStatement.executeUpdate();
			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				/*
				 * Close prepared statement and database connectivity at the end
				 * of transaction
				 */
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}
	}

	/**
	 * This performs GET student by ID and Display all students
	 * 
	 * @param studentID
	 *            ID of the student to remove or select from the list

	 * @return ArrayList<Student> Array of student list will be return
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 * 
	 * @see #getStudents()
	 * @see #getStudentByID(String)
	 */
	private ArrayList<Student> actionOnStudent(String studentID) {

		ArrayList<Student> studentList = new ArrayList<Student>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Before fetching student it checks whether student ID is
			 * available
			 */
			if (studentID != null && !studentID.isEmpty()) {
				/*
				 * Get student by ID query will be retrieved from
				 * StudentQuery.xml
				 */
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_EMPLOYEE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, studentID);
			}
			/*
			 * If student ID is not provided for get student option it display
			 * all students
			 */
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_EMPLOYEES));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Student student = new Student();
				student.setStudentID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				student.setName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				student.setEmail(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				
				studentList.add(student);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return studentList;
	}

	/**
	 * Get the updated student
	 * 
	 * @param studentID
	 *            ID of the student to remove or select from the list
	 * 
	 * @return return the Student object
	 * 
	 */
	@Override
	public Student updateStudent(String studentID, Student student) {

		/*
		 * Before fetching student it checks whether student ID is available
		 */
		if (studentID != null && !studentID.isEmpty()) {
			/*
			 * Update student query will be retrieved from StudentQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_EMPLOYEE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, student.getName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, student.getEmail());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_EIGHT, student.getStudentID());
				preparedStatement.executeUpdate();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				/*
				 * Close prepared statement and database connectivity at the end
				 * of transaction
				 */
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}
		// Get the updated student
		return getStudentByID(studentID);
	}
	
	/**
	 *
	 * @return ArrayList<String> Array of student id list will be return
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 */
	private ArrayList<String> getStudentIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		/*
		 * List of student IDs will be retrieved from StudentQuery.xml
		 */
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_EMPLOYEE_IDS));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
			}
		} catch (SQLException | SAXException | IOException | ParserConfigurationException
				| ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return arrayList;
	}
}