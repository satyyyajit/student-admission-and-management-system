# Student Admission and Management System

This project is a Java-based application designed to manage student admissions and registration for VIT University campuses (VIT Vellore and VIT AP). It streamlines administrative tasks by providing a command-line interface for administrators to add, view, update, and delete student records, as well as generate statistics and maintain a backup of cancelled admissions.

## Features

- **Student Registration:** Add new students with unique registration numbers and generate institutional email addresses.
- **View Students:** List all registered students by branch (CSE/ECE) and campus, or view details for an individual student.
- **Update Student Passwords:** Securely update passwords for existing students.
- **Delete Students (with Backup):** Cancel admissions and move records to a backup table for audit/history.
- **Statistics:** View the number of students enrolled per branch and campus.
- **Search:** Find students by registration number.
- **Admin Console:** Menu-driven command-line interface for managing all operations.

## Technologies Used

- **Java** (JDK 8+)
- **MySQL** (for persistent storage)
- **JDBC** (for database connectivity)

## Database Schema

- `csestudents`: Stores CSE student records.
- `ecestudents`: Stores ECE student records.
- `Cancelled_Students`: Stores backup of deleted/cancelled students.

Each student record contains:
- Registration Number
- College ID
- Application Number
- Name
- Email
- Password
- Phone Number
- Rank

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or above
- MySQL database server
- A code editor or IDE (IntelliJ IDEA, Eclipse, etc.)

### Setup Instructions

1. **Clone the repository**
   ```sh
   git clone https://github.com/satyyyajit/student-admission-and-management-system.git
   cd student-admission-and-management-system
   ```

2. **Configure the Database**
   - Create a MySQL database and the required tables as described in the schema above.
   - Update the database connection parameters in the `DBconnect` class if needed.

3. **Build and Run the Application**
   - Open the project in your preferred IDE.
   - Compile and run `Main.java`.
   - Follow the on-screen menu to perform administrative tasks.

## Usage

Run the application and use the menu to:
- Add new students
- View students by campus/branch
- Update passwords
- Cancel admissions
- View statistics

## Further Development

Potential improvements include:
- Adding a graphical user interface (GUI) with JavaFX or Swing
- Implementing role-based access (admin, student)
- Adding features like fee payment, course registration, or notifications
- Improving error handling and logging

## License

This project is licensed under the MIT License.

---

**Repository:** [GitHub - satyyyajit/student-admission-and-management-system](https://github.com/satyyyajit/student-admission-and-management-system)
