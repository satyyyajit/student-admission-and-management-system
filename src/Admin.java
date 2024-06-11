

import java.sql.*;
import java.util.Scanner;


interface AdminInterface {
    String getName();
    String getDept();
    long getApplicationNo();
    String getRegNo(int college) throws SQLException;
    String getEmail(int college) throws SQLException;
    void addStudent(int college,String phn, int rank) throws SQLException;
}


public class Admin implements AdminInterface {
    static DBconnect db=new DBconnect();
    static Scanner sc=new Scanner(System.in);

    String password;
    String name, dept, regNo;
    long applicationNo;
    String phoneNo;
    int Rank;
    public int getRank() {
        return Rank;
    }



    public String getPhoneNo() {
        return phoneNo;
    }

    public Admin(String name, String dept, long applicationNo, String phone, int Rank){
        this.name = name;
        this.dept =dept;
        this.applicationNo = applicationNo;
        password = getFirstName()+"123";
    }

    public String getName() {
        return name;
    }

    public String getFirstName(){
        String newName = getName()+' ';
        return newName.substring(0,newName.indexOf(' ')).toLowerCase();

    }

    public String getDept() {
        return dept;
    }

    public long getApplicationNo() {
        return applicationNo;
    }

    public String getRegNo(int college) throws SQLException {
        Connection conn = db.connect();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String tableName = "";

        if(getDept().equals("bce")){
            tableName = "csestudents";
        }
        else if(getDept().equals("bee")){
            tableName = "ecestudents";
        }

        String query = "SELECT MAX(RegNo) FROM " + tableName + " WHERE CollegeID = ? AND RegNo IS NOT NULL AND RegNo != ''";
        ;
        pst = conn.prepareStatement(query);

        pst.setInt(1, college);
        rs = pst.executeQuery();

        int id = 0;
        if(rs.next()){
            id = Integer.parseInt((rs.getString(1)).substring(5))+1;
        }

        regNo = String.valueOf(getApplicationNo()).substring(2,4)+getDept()+String.valueOf(id);

        conn.close();
        return regNo;
    }

    public String getEmail(int college) throws SQLException {

        String id = getRegNo(college);
        String email="";
        if(college==1){
            email = getFirstName()+"."+id+"@vitstudent.ac.in";
        }
        else if(college==2){
            email = getFirstName()+"."+id+"@vitapstudent.ac.in";
        }
        return email;
    }

    public void addStudent(int college, String phn, int rank) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = db.connect();
            String tableName = "";

            if (getDept().equalsIgnoreCase("bee")) {
                tableName = "ecestudents";
            } else if (getDept().equalsIgnoreCase("bce")) {
                tableName = "csestudents";
            }

            if (!tableName.isEmpty()) {
                String query = "INSERT INTO " + tableName + " (RegNo, CollegeID, ApplicationNo, Name, Email, Password, PhoneNo, ranks) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                String emailAddress = getEmail(college);

                ps = conn.prepareStatement(query);
                ps.setString(1, regNo.toUpperCase());
                ps.setInt(2, college);
                ps.setString(3, String.valueOf(getApplicationNo()));
                ps.setString(4, getName());
                ps.setString(5, emailAddress);
                ps.setString(6, password);
                ps.setString(7, phn);
                ps.setInt(8, rank);

                ps.executeUpdate();
            }
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void getTable(String tableName) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = db.connect();

            // Prepare the SQL query
            String query = "SELECT RegNo, CollegeID, ApplicationNo, Name, Email, Password, PhoneNo, ranks FROM " + tableName;
            ps = conn.prepareStatement(query);

            // Execute the query
            rs = ps.executeQuery();
            System.out.println();
            // Print table header
            System.out.println("Table: " + tableName);
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-20s %-12s %-20s %-25s %-45s %-15s %-15s %-10s\n", "RegNo", "CollegeID", "ApplicationNo", "Name", "Email", "Password", "PhoneNo", "Rank");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            // Print table data
            while (rs.next()) {
                String regNo = rs.getString("RegNo");
                int collegeID = rs.getInt("CollegeID");
                String applicationNo = rs.getString("ApplicationNo");
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                String phoneNo = rs.getString("PhoneNo");
                int rank = rs.getInt("ranks");

                System.out.printf("%-20s %-12d %-20s %-25s %-45s %-15s %-15s %-10d\n", regNo, collegeID, applicationNo, name, email, password, phoneNo, rank);
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }


    public static void getTable(String tableName, int college) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = db.connect();

            // Prepare the SQL query
            String query = "SELECT RegNo, ApplicationNo, Name, Email, Password, PhoneNo, ranks FROM " + tableName + " WHERE CollegeID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, college);

            // Execute the query
            rs = ps.executeQuery();
            System.out.println();
            // Print table header
            if (college == 1) {
                System.out.print("VIT-Vellore - ");
            } else if (college == 2) {
                System.out.print("VIT-AP - ");
            }
            if (tableName.equalsIgnoreCase("ecestudents")) {
                System.out.println("ECE Students");
            } else if (tableName.equalsIgnoreCase("csestudents")) {
                System.out.println("CSE Students");
            }

            System.out.println("\"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\"");
            System.out.printf("%-25s %-20s %-25s %-45s %-45s %-20s %-8s\n", "Registration No", "Application No", "Name", "Email", "Password", "Phone No", "Rank");
            System.out.println("\"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\"");

            // Print table data
            while (rs.next()) {
                String regNo = rs.getString("RegNo");
                String applicationNo = rs.getString("ApplicationNo");
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                String phoneNo = rs.getString("PhoneNo");
                int rank = rs.getInt("ranks");

                System.out.printf("%-25s %-20s %-25s %-45s %-45s %-20s %-8d\n", regNo, applicationNo, name, email, password, phoneNo, rank);
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in reverse order of their creation to avoid resource leaks
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }


    public static void deleteStudent(String regNo) throws SQLException {
        Connection conn = db.connect();
        PreparedStatement psSelect = null;
        String tableName ="";

        if (regNo.substring(2, 5).equalsIgnoreCase("bce")) {
            tableName = "csestudents";
        } else if (regNo.substring(2, 5).equalsIgnoreCase("bee")) {
            tableName = "ecestudents";
        }

        String query = "SELECT * FROM " + tableName + " WHERE RegNo = ?";
        psSelect = conn.prepareStatement(query);
        psSelect.setString(1, regNo);
        ResultSet rs = psSelect.executeQuery();

        String reg = "", app = "", name1="", email1="", password1="", phn ="";
        int ranks = 0;
        int col = 0;
        if (rs.next()) {
            reg = rs.getString("RegNo");
            app = rs.getString("ApplicationNo");
            name1 = rs.getString("Name");
            email1 = rs.getString("Email");
            password1 = rs.getString("Password");
            col = rs.getInt("CollegeID");
            ranks =rs.getInt("ranks");
            phn = rs.getString("PhoneNo");
        }

        try {
            String insertQuery = "INSERT INTO Cancelled_Students VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement psInsert = conn.prepareStatement(insertQuery);
            psInsert.setString(1, reg);
            psInsert.setInt(2, col);
            psInsert.setString(3, app);
            psInsert.setString(4, name1);
            psInsert.setString(5, email1);
            psInsert.setString(6, password1);
            psInsert.setString(7, phn);
            psInsert.setInt(8, ranks);
            psInsert.executeUpdate();
            psInsert.close();
        } catch (SQLException e) {
            // If insertion into Cancelled_Students fails, revert changes
            String newDel = "DELETE FROM Cancelled_Students WHERE RegNo = ?";
            PreparedStatement psDel = conn.prepareStatement(newDel);
            psDel.setString(1, reg);
            psDel.executeUpdate();
            psDel.close();

            String newInsertQuery = "INSERT INTO Cancelled_Students VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement psInsert = conn.prepareStatement(newInsertQuery);
            psInsert.setString(1, reg);
            psInsert.setInt(2, col);
            psInsert.setString(3, app);
            psInsert.setString(4, name1);
            psInsert.setString(5, email1);
            psInsert.setString(6, password1);
            psInsert.setString(7, phn);
            psInsert.setInt(8, ranks);
            psInsert.executeUpdate();
            psInsert.close();
        }


        PreparedStatement psDelete = conn.prepareStatement("DELETE FROM " + tableName + " WHERE RegNo = ?");
        psDelete.setString(1, regNo);
        psDelete.executeUpdate();
        psDelete.close();

        rs.close();
        psSelect.close();
        conn.close();
    }


    public static void updatePassword(String regNo, String oldPassword, String newPassword) throws SQLException {
        Connection conn = db.connect();
        String tableName ="";
        if (regNo.substring(2, 5).equalsIgnoreCase("bce")) {
            tableName = "csestudents";
        }
        else if (regNo.substring(2, 5).equalsIgnoreCase("bee")) {
            tableName = "ecestudents";
        }

        // Check old password
        String selectQuery = "SELECT Password FROM " + tableName + " WHERE RegNo = ?";
        PreparedStatement psSelect = conn.prepareStatement(selectQuery);
        psSelect.setString(1, regNo);
        ResultSet rs = psSelect.executeQuery();
        if (rs.next()) {
            String savedPassword = rs.getString("Password");
            if (!savedPassword.equals(oldPassword)) {
                System.out.println("Old password is incorrect.");
                return;
            }
        } else {
            System.out.println("Student with registration number " + regNo + " not found.");
            return;
        }

        String updateQuery = "UPDATE " + tableName + " SET Password = ? WHERE RegNo = ?";
        PreparedStatement psUpdate = conn.prepareStatement(updateQuery);
        psUpdate.setString(1, newPassword); // Set the new password
        psUpdate.setString(2, regNo); // Set the registration number to identify the student
        psUpdate.executeUpdate();
        psUpdate.close();
        psSelect.close();
        rs.close();
    }

    public static void getNoofStudents(int collegeID) throws SQLException {

        Connection conn = null;
        PreparedStatement psTotal = null;
        PreparedStatement psCSE = null;
        PreparedStatement psECE = null;
        ResultSet rsTotal = null;
        ResultSet rsCSE = null;
        ResultSet rsECE = null;

        try {
            conn = db.connect();


            if(collegeID==1){
                System.out.println("Students at VIT-Vellore");

            }
            else
                if(collegeID==2){
                    System.out.println("Students at VIT-AP");
                }
            // Query to get the total number of students in the specified college
            String queryTotal = "SELECT (SELECT COUNT(*) FROM csestudents WHERE CollegeID = ?) + " +
                    "(SELECT COUNT(*) FROM ecestudents WHERE CollegeID = ?) AS TotalStudents";
            psTotal = conn.prepareStatement(queryTotal);
            psTotal.setInt(1, collegeID);
            psTotal.setInt(2, collegeID);
            rsTotal = psTotal.executeQuery();

            int totalStudents = 0;
            if (rsTotal.next()) {
                totalStudents = rsTotal.getInt("TotalStudents");
            }

            // Query to get the number of CSE students in the specified college
            String queryCSE = "SELECT COUNT(*) AS CSECount FROM csestudents WHERE CollegeID = ?";
            psCSE = conn.prepareStatement(queryCSE);
            psCSE.setInt(1, collegeID);
            rsCSE = psCSE.executeQuery();

            int cseCount = 0;
            if (rsCSE.next()) {
                cseCount = rsCSE.getInt("CSECount");
            }

            // Query to get the number of ECE students in the specified college
            String queryECE = "SELECT COUNT(*) AS ECECount FROM ecestudents WHERE CollegeID = ?";
            psECE = conn.prepareStatement(queryECE);
            psECE.setInt(1, collegeID);
            rsECE = psECE.executeQuery();

            int eceCount = 0;
            if (rsECE.next()) {
                eceCount = rsECE.getInt("ECECount");
            }

            // Print the results
            System.out.println("Total Students: " + totalStudents);
            System.out.println("Branch  | Student Count");
            System.out.println("------------------------");
            System.out.println("CSE     | " + cseCount);
            System.out.println("ECE     | " + eceCount);
        } finally {
            // Close resources
            if (rsTotal != null) {
                rsTotal.close();
            }
            if (psTotal != null) {
                psTotal.close();
            }
            if (rsCSE != null) {
                rsCSE.close();
            }
            if (psCSE != null) {
                psCSE.close();
            }
            if (rsECE != null) {
                rsECE.close();
            }
            if (psECE != null) {
                psECE.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    private static boolean isRegNoPresent(String tableName, String regNo) throws SQLException {
        Connection conn = db.connect();
        String query = "SELECT RegNo FROM " + tableName + " WHERE RegNo = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, regNo);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Returns true if the registration number is present
            }
        }
    }

    public static void showStudent(String tableName, int collegeID) {
        while (true) {
            try {
                System.out.println("Enter the registration number:");
                String regno = sc.next();
                if (isRegNoPresent(tableName, regno)) {
                    Connection conn = db.connect();
                    PreparedStatement psTotal = null;

                    String query = "SELECT * FROM " + tableName + " WHERE CollegeID = ? AND RegNo = ?";
                    psTotal = conn.prepareStatement(query);
                    psTotal.setInt(1, collegeID);
                    psTotal.setString(2, regno);
                    ResultSet rsTotal = psTotal.executeQuery();

                    if (rsTotal.next()) {
                        String regNo = rsTotal.getString("RegNo");
                        int col = rsTotal.getInt("CollegeID");
                        String app = rsTotal.getString("ApplicationNo");
                        String name = rsTotal.getString("Name");
                        String email = rsTotal.getString("Email");
                        String pass = rsTotal.getString("Password");
                        String phone = rsTotal.getString("PhoneNo");
                        int rank = rsTotal.getInt("ranks");
                        String d="";
                        if (email.contains("bce")){
                            d = "Computer Science and Engineering.";
                        }
                        else
                            if (email.contains("bee")){
                                d = "Electronics and Communication Engineering.";
                            }

                        if (col==1) {
                            System.out.println();
                            System.out.println("Here are the details:");
                            System.out.println("------------------------------");
                            System.out.println();
                            System.out.println("Registration No: " + regNo);
                            System.out.println("College: VIT Vellore");
                            System.out.println("Application No: " + app);
                            System.out.println("Rank: " + rank);
                            System.out.println("Name: " + name);
                            System.out.println("Branch : "+d);
                            System.out.println("Email: " + email);
                            System.out.println("Password: " + pass);
                            System.out.println("Phone No: " + phone);

                            System.out.println();
                            System.out.println("------------------------------");
                            System.out.println();
                        }
                        else if (col==2) {
                            System.out.println();
                            System.out.println("Here are the details:");
                            System.out.println("------------------------------");
                            System.out.println();
                            System.out.println("Registration No: " + regNo);
                            System.out.println("College: VIT AP");
                            System.out.println("Application No: " + app);
                            System.out.println("Rank: " + rank);
                            System.out.println("Name: " + name);
                            System.out.println("Branch : "+d);
                            System.out.println("Email: " + email);
                            System.out.println("Password: " + pass);
                            System.out.println("Phone No: " + phone);
                            System.out.println();
                        }

                        break; // Exit the loop after successful execution
                    }
                } else {
                    System.out.println("Invalid registration number! Please enter a valid one.");
                }
            } catch (SQLException e) {
                System.out.println("An error occurred while fetching data from the database: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }



}






