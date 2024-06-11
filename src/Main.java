import java.sql.*;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    private static void printMenu() {
        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println();
        System.out.println("1. About the System.");
        System.out.println("2. Show Details of Registered Students.");
        System.out.println("3. Show Detail of a individual Student.");
        System.out.println("4. Number of Students at Campuses.");
        System.out.println("5. Admit a student.");
        System.out.println("6. Cancel the admission.");
        System.out.println("7. Show Past Students.");
        System.out.println("8. Update password of a student.");
        System.out.println("0. Exit");
        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println();
    }

    private static void aboutSystem() {
        System.out.println("This system assists the SDC admin in managing VIT's student email addresses and registrations. \n" +
                "It provides functionalities such as displaying registered students, \n" +
                "managing student admissions, and handling registration cancellations.\n");
    }

    private static void showRegisteredStudents() {
        try {
            System.out.println("-------------------------------------------------");
            System.out.println("Show Details of Registered Students");
            System.out.println("-------------------------------------------------");
            System.out.println("Choose the college to view registered students:");
            System.out.println("1. VIT Vellore");
            System.out.println("2. VIT AP");
            System.out.println("0. Back");
            System.out.print(" >> ");
            int collegeChoice = sc.nextInt();
            System.out.println();

            switch (collegeChoice) {
                case 1:
                    System.out.println("Registered Students at VIT Vellore:");
                    System.out.println("-----------------------------------");
                    Admin.getTable("csestudents", 1);
                    Admin.getTable("ecestudents", 1);
                    break;
                case 2:
                    System.out.println("Registered Students at VIT AP:");
                    System.out.println("-----------------------------------");
                    Admin.getTable("csestudents", 2);
                    Admin.getTable("ecestudents", 2);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void numberOfStudentsAtCampuses() {
        try {
            System.out.println("-------------------------------------------------");
            System.out.println("Number of Students at Campuses");
            System.out.println("-------------------------------------------------");
            System.out.println("1. VIT Vellore");
            System.out.println("2. VIT AP");
            System.out.println("0. Back");
            System.out.print(" >> ");
            int campusChoice = sc.nextInt();
            System.out.println();

            switch (campusChoice) {
                case 1:
                    Admin.getNoofStudents(1);
                    break;
                case 2:
                    Admin.getNoofStudents(2);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void admitStudent() {
        while (true) {
            System.out.println("Admit a student");

            System.out.print("Application number: ");
            long applicationNo = sc.nextLong();
            System.out.println();

            System.out.print("Rank: ");
            int rank = sc.nextInt();
            System.out.println();

            sc.nextLine();

            System.out.print("Name of student: ");
            String name = sc.nextLine();
            System.out.println();

            System.out.print("Phone Number: ");
            String phoneNumber = sc.nextLine();
            System.out.println();

            if (rank < 10000) {
                System.out.println("You have two college options:");
                System.out.println("1. VIT Vellore");
                System.out.println("2. VIT AP");
                System.out.println("0. Go Back");
                System.out.print("Choose a college (1, 2, or 0 to go back): ");
                int collegeChoice = sc.nextInt();
                System.out.println();

                switch (collegeChoice) {
                    case 1:
                        admitStudentToVellore(name, applicationNo, rank, phoneNumber);
                        System.out.println("The student has been admitted.");
                        return;
                    case 2:
                        admitStudentToAP(name, applicationNo, rank, phoneNumber);
                        return;
                    case 0:
                        // Go back to main menu
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else if (rank >= 10000 && rank < 20000) {
                System.out.println("You have VIT AP as only option:");
                System.out.println("1. VIT AP");
                System.out.println("0. Go back");
                System.out.print("Choose a college (1 or 0 to go back): ");
                int collegeChoice = sc.nextInt();
                System.out.println();
                switch (collegeChoice) {
                    case 1:
                        admitStudentToAP(name, applicationNo, rank, phoneNumber);
                        return;
                    case 0:
                        return;

                }
            } else {
                System.out.println("Sorry, the student is not eligible for admission.");
                return; // Return to main menu
            }
        }
    }

    private static void admitStudentToVellore(String name, long applicationNo, int rank, String phn) {
        try {
            while (true) {
                System.out.println("VIT Vellore");
                if (rank <= 5000) {
                    System.out.println("You have two options:");
                    System.out.println("1. CSE");
                    System.out.println("2. ECE");
                    System.out.println("0. Go Back");
                    System.out.print("Choose a branch (1, 2, or 0 to go back): ");
                    int branchChoice = sc.nextInt();
                    System.out.println();

                    switch (branchChoice) {
                        case 1:
                            System.out.println();
                            Admin cseVellore = new Admin(name, "bce", applicationNo, phn, rank);
                            cseVellore.addStudent(1, phn, rank);
                            System.out.println();
                            System.out.println("Admitted to CSE in VIT Vellore");
                            return;
                        case 2:
                            System.out.println();
                            Admin eceVellore = new Admin(name, "bee", applicationNo, phn, rank);
                            eceVellore.addStudent(1, phn, rank);
                            System.out.println();
                            System.out.println("Admitted to ECE in VIT Vellore");
                            return;
                        case 0:
                            // Go back to college selection
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } else if (rank <= 10000) {
                    System.out.println();
                    System.out.println("You are not eligible for CSE. \n" +
                            "You can try ECE if you want to go back.\n" +
                            " press 0 to go back" +
                            "\nPress 1 for ECE...");
                    int choice = sc.nextInt();
                    if (choice == 0) {
                        return;
                    }
                    System.out.println();
                    Admin eceVellore = new Admin(name, "bee", applicationNo, phn, rank);
                    eceVellore.addStudent(1, phn, rank);
                    System.out.println();
                    System.out.println("Admitted to ECE in VIT Vellore");
                    return; // Go back to admitStudent
                } else {
                    System.out.println("Sorry, student is not eligible for admission to VIT Vellore. ");
                    return; // Go back to main menu
                }
            }
        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void admitStudentToAP(String name, long applicationNo, int rank, String phn) {
        try {
            while (true) {
                System.out.println("VIT AP");
                if (rank <= 10000) {
                    System.out.println("You have two options:");
                    System.out.println("1. CSE");
                    System.out.println("2. ECE");
                    System.out.println("0. Go Back");
                    System.out.print("Choose a branch (1, 2, or 0 to go back): ");
                    int branchChoice = sc.nextInt();
                    System.out.println();

                    switch (branchChoice) {
                        case 1:
                            System.out.println();
                            Admin cseAP = new Admin(name, "bce", applicationNo, phn, rank);
                            cseAP.addStudent(2, phn, rank);
                            System.out.println();
                            System.out.println("Admitted to CSE in VIT AP");

                            return; // Go back to admitStudent
                        case 2:
                            System.out.println();
                            Admin eceAP = new Admin(name, "bee", applicationNo, phn, rank);
                            eceAP.addStudent(2, phn, rank);
                            System.out.println();
                            System.out.println("Admitted to ECE in VIT AP");
                            return; // Go back to admitStudent
                        case 0:
                            // Go back to college selection
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } else if (rank <= 20000) {
                    System.out.println();
                    System.out.println("If student dont want to participate fo ECE");
                    System.out.println("0. To Go back");
                    System.out.println("Press 1 for ECE...");
                    int choice = sc.nextInt();
                    if (choice == 0) {
                        return;
                    }
                    System.out.println();
                    Admin eceAP = new Admin(name, "bee", applicationNo, phn, rank);
                    eceAP.addStudent(2, phn, rank);
                    System.out.println();
                    System.out.println("Admitted to ECE in VIT AP");
                    return; // Go back to admitStudent
                } else {
                    System.out.println("Sorry, the student is not eligible for admission to VIT AP.");
                    return; // Go back to main menu
                }
            }
        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void cancelRegistration() {
        try {
            System.out.println("Note down the registration no.**");
            System.out.println("Enter the College :");
            System.out.println("1 for VIT-Vellore");
            System.out.println("2 for VIT-AP");
            int col = sc.nextInt();
            if (col == 1) {
                System.out.println("Enter the branch :");
                System.out.println("1 for CSE");
                System.out.println("2 for ECE");
                int branch = sc.nextInt();
                if (branch == 1) {
                    Admin.getTable("csestudents", 1);
                } else if (branch == 2) {
                    Admin.getTable("ecestudents", 1);
                }
            } else if (col == 2) {
                System.out.println("Enter the branch :");
                System.out.println("1 for CSE");
                System.out.println("2 for ECE");
                int branch = sc.nextInt();
                if (branch == 1) {
                    Admin.getTable("csestudents", 2);

                } else if (branch == 2) {
                    Admin.getTable("ecestudents", 2);
                }

            }

            System.out.println();
            System.out.println("Check the Registeration Number of which is to be cancelled.");
            System.out.println();

            System.out.print("Give the Registration No. >> ");
            String reg = sc.next();
            System.out.println();

            Admin.deleteStudent(reg);

            System.out.println("The admission has been cancelled.");
        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void pastStudents() {
        try {
            Admin.getTable("Cancelled_Students");
        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void updatePass() {
        try {
            System.out.println();

            System.out.println("Note down the registration no. and password**");
            System.out.println("Enter the College :");
            System.out.println("1 for VIT-Vellore");
            System.out.println("2 for VIT-AP");
            int col = sc.nextInt();
            if (col == 1) {
                System.out.println("Enter the branch :");
                System.out.println("1 for CSE");
                System.out.println("2 for ECE");
                int branch = sc.nextInt();
                if (branch == 1) {
                    Admin.getTable("csestudents", 1);
                } else if (branch == 2) {
                    Admin.getTable("ecestudents", 1);
                }
            } else if (col == 2) {
                System.out.println("Enter the branch :");
                System.out.println("1 for CSE");
                System.out.println("2 for ECE");
                int branch = sc.nextInt();
                if (branch == 1) {
                    Admin.getTable("csestudents", 2);

                } else if (branch == 2) {
                    Admin.getTable("ecestudents", 2);
                }

            }

            System.out.println();
            System.out.print("Enter the registration no : ");
            String reg = sc.next();
            System.out.println();

            System.out.println("Enter the old password : ");
            String oldPass = sc.next();
            System.out.println();
            System.out.println("Enter the new password : ");
            String newPass = sc.next();
            System.out.println();
            Admin.updatePassword(reg, oldPass, newPass);
            System.out.println();
            System.out.println("The Login password has been updated.");
            System.out.println();
        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void showDetails() {
        try {
            while (true) {
                System.out.println("Enter the College:");
                System.out.println("1 for VIT-Vellore");
                System.out.println("2 for VIT-AP");
                int col = sc.nextInt();
                if (col == 1 || col == 2) {
                    System.out.println("Enter the branch:");
                    System.out.println("1 for CSE");
                    System.out.println("2 for ECE");
                    int branch = sc.nextInt();
                    if (branch == 1 || branch == 2) {
                        String tableName = (branch == 1) ? "csestudents" : "ecestudents";
                        Admin.getTable(tableName, col);
                        System.out.println();
                        Admin.showStudent(tableName, col);
                        break;
                    } else {
                        System.out.println("Invalid branch number! Please enter 1 for CSE or 2 for ECE.");
                    }
                } else {
                    System.out.println("Invalid college number! Please enter 1 for VIT-Vellore or 2 for VIT-AP.");
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter a valid integer.");
            sc.nextLine();
        }
    }

    public static void main(String[] args) {
        System.out.println("VIT Email and Registration Management");
        boolean exit = false;
        while (!exit) {
            printMenu();
            System.out.print(" >>  ");
            int choice = sc.nextInt();
            System.out.println();
            switch (choice) {
                case 0:
                    System.out.println("Thank you for using our system");
                    exit = true;
                    break;
                case 1:
                    aboutSystem();
                    break;
                case 2:
                    showRegisteredStudents();
                    break;

                case 3:
                    showDetails();
                    break;
                case 4:
                    numberOfStudentsAtCampuses();
                    break;
                case 5:
                    admitStudent();
                    break;
                case 6:
                    cancelRegistration();
                    break;
                case 7:
                    pastStudents();
                    break;
                case 8:
                    updatePass();
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
        sc.close(); // Closing the scanner
    }
}
