# Vmanage Student Database

# VIT Student Management System - Java Application
This project implements a Java application for managing student registrations at VIT University. It provides functionalities for administrators to:

Add new students
View student details
Update student passwords
Delete students (with backup)
View the number of students enrolled in each branch (CSE and ECE)
Features:

Connects to a MySQL database to store and retrieve student data.
Implements a secure password generation and storage mechanism.
Generates unique registration numbers based on department and college.
Allows searching for students by registration number.
Provides separate views for students from VIT Vellore and VIT AP campuses.
Getting Started

# Prerequisites:

Java Development Kit (JDK) 8 or above
MySQL database server
A code editor or IDE (e.g., IntelliJ IDEA, Eclipse)

Running the Application:

Open a terminal in the project directory.
Compile and run the application using
Admin Interface:

The application provides an admin interface accessible through the command line. You'll be prompted to enter details when adding new students.

# Database Schema:

The application assumes a MySQL database with the following tables:

csestudents: Stores details of CSE students (Registration No, College ID, Application No, Name, Email, Password, Phone No, Rank)
ecestudents: Stores details of ECE students (same schema as csestudents)
Cancelled_Students (optional): Stores details of deleted students for backup purposes (same schema as csestudents)
Further Development:

This application serves as a basic foundation for student management. You can extend its functionalities by:

Implementing a user interface (e.g., Swing, JavaFX)
Adding functionalities for students (e.g., course registration, fee payment)
Implementing role-based access control for different user types (admin, student)
Enhancing error handling and logging
