# Student Admission and Management System

---

## 📚 Project Overview

The **Student Admission and Management System** is a Java-based, menu-driven application that streamlines the processes of student registration, admission, record maintenance, and reporting for university administrators. Designed as a learning and practical project, it simulates real-world admission workflows, emphasizing automation, accuracy, and user-friendliness in the command-line environment.

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

## 🏫 Supported Campuses & Branches

- **Campuses**:  
  - VIT Vellore
  - VIT AP

- **Branches**:  
  - Computer Science and Engineering (CSE)
  - Electronics and Communication Engineering (ECE)

---

## 🗃️ Database Structure

- `csestudents`: CSE student records.
- `ecestudents`: ECE student records.
- `Cancelled_Students`: Backup of canceled admissions.

**Each record includes:**  
Registration Number, College ID, Application Number, Name, Email, Password, Phone No, Rank

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
- MySQL Server
- JDBC Driver for MySQL

### **Setup Instructions**
1. **Clone the repository:**
   ```sh
   git clone https://github.com/satyyyajit/student-admission-and-management-system.git
   cd student-admission-and-management-system
   ```
2. **Configure the Database:**
   - Create required tables in MySQL as per the schema above.
   - Update DB credentials in `DBconnect` class.

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
