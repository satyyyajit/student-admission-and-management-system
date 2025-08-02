# Student Admission and Management System

---

## 📚 Project Overview

The **Student Admission and Management System** is a Java-based, menu-driven application that streamlines the processes of student registration, admission, record maintenance, and reporting for university administrators. Designed as a learning and practical project, it simulates real-world admission workflows, emphasizing automation, accuracy, and user-friendliness in the command-line environment.

> **Note:**  
> The MySQL database for this project is intended to be set up and run locally on your machine. The database is not hosted online.

---

## 🎯 Key Features

- **Interactive Console UI**: Simple, menu-driven navigation for all major administrative tasks.
- **Student Admission**: Admit students based on rank and branch preferences, with eligibility checks for VIT Vellore and VIT AP.
- **Automated Registration Number & Email Generation**: Unique IDs and institution email addresses for each new student.
- **Branch-wise & Campus-wise Management**: Separate records and operations for CSE/ECE branches and both campuses.
- **Student Record Viewing**: View, search, and display details of all or individual students.
- **Password Update**: Securely update student passwords.
- **Admission Cancellation**: Delete student records with backup to a canceled students' table.
- **Statistical Reporting**: Get total counts per branch and campus.
- **Error Handling**: Input validation and informative prompts throughout.

---

## 🗃️ Database Schema (MySQL)

This project requires a MySQL database with the following tables:

### 1. `csestudents` (CSE Student Records)
### 2. `ecestudents` (ECE Student Records)
### 3. `Cancelled_Students` (Backup of Cancelled Admissions)

**Each table has the same structure:**

```sql
CREATE TABLE csestudents (
    RegNo VARCHAR(20) PRIMARY KEY,
    CollegeID INT,
    ApplicationNo VARCHAR(20),
    Name VARCHAR(100),
    Email VARCHAR(100),
    Password VARCHAR(100),
    PhoneNo VARCHAR(20),
    ranks INT
);

CREATE TABLE ecestudents (
    RegNo VARCHAR(20) PRIMARY KEY,
    CollegeID INT,
    ApplicationNo VARCHAR(20),
    Name VARCHAR(100),
    Email VARCHAR(100),
    Password VARCHAR(100),
    PhoneNo VARCHAR(20),
    ranks INT
);

CREATE TABLE Cancelled_Students (
    RegNo VARCHAR(20) PRIMARY KEY,
    CollegeID INT,
    ApplicationNo VARCHAR(20),
    Name VARCHAR(100),
    Email VARCHAR(100),
    Password VARCHAR(100),
    PhoneNo VARCHAR(20),
    ranks INT
);
```

> **To use this application:**
>
> 1. **Create a MySQL database** (e.g., `student_management`).
> 2. **Create the three tables** above in your database (you can use phpMyAdmin, MySQL Workbench, or the MySQL CLI).
> 3. **Update the database credentials** (username, password, database name) in the `DBconnect` class in your Java code to match your local setup.

---

## 🖥️ Interactive Menu Preview

```
-------------------------------------------------
1. About the System.
2. Show Details of Registered Students.
3. Show Detail of an Individual Student.
4. Number of Students at Campuses.
5. Admit a Student.
6. Cancel the Admission.
7. Show Past Students.
8. Update Password of a Student.
0. Exit
-------------------------------------------------
```

---

## ⚙️ How It Works: Admission Example

1. **Start Application:**  
   Run the program and select "Admit a Student" from the menu.

2. **Input Student Details:**  
   Enter application number, rank, name, and phone number.

3. **Eligibility & Options:**  
   - If rank < 5000: Eligible for both CSE/ECE at Vellore and AP
   - If rank 5001–10000: Eligible for ECE at Vellore, CSE/ECE at AP
   - If rank 10001–20000: Eligible for ECE at AP only

4. **Branch & Campus Selection:**  
   Choose college and branch interactively.

5. **Record Creation:**  
   System generates registration number and email, stores all details in the database.

---

## 🔒 Security & Data Integrity

- All user inputs are validated.
- Passwords are stored and can be updated securely.
- Admission deletions are backed up for future audits.

---

## 🏁 Getting Started

### **Prerequisites**
- Java JDK 8 or above
- MySQL Server (running locally)
- JDBC Driver for MySQL

### **Setup Instructions**
1. **Clone the repository:**
   ```sh
   git clone https://github.com/satyyyajit/student-admission-and-management-system.git
   cd student-admission-and-management-system
   ```
2. **Configure the Database:**
   - **Create your database** (e.g., `student_management`).
   - **Create the tables** by running the SQL above.
   - **Update DB credentials** in the `DBconnect` class to match your local MySQL setup.

3. **Compile and Run:**
   - Open in any IDE or use terminal:
     ```sh
     javac src/Main.java
     java -cp src Main
     ```

4. **Follow the Menu Prompts!**

---

## 📊 Sample Statistics Output

```
Students at VIT-Vellore
Total Students: 120
Branch  | Student Count
------------------------
CSE     | 80
ECE     | 40
```

---

## 📄 Project Report

For a detailed analysis, design decisions, and screenshots, check the [Project Report PDF](https://github.com/satyyyajit/student-admission-and-management-system/blob/main/Student-Admission-Management%20System.pdf).

---

## 💡 Further Improvements

- Add a graphical user interface (JavaFX/Swing)
- Integrate student self-service portal
- Email/SMS notifications
- Role-based access (admin/student)
- Enhanced reporting & analytics

---

## 🤝 Contributing

Pull requests are welcome! Please open an issue first to discuss major changes.

---

## 📜 License

This project is licensed under the MIT License.

---

**Repository:** [satyyyajit/student-admission-and-management-system](https://github.com/satyyyajit/student-admission-and-management-system)
