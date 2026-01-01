 
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Main Class: RobotControlSystem
public class RobotControlSystem {

    private static final String HISTORY_FILE = "movement_history.ser"; // Serialization for complex objects

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int battery = getInitialBattery(scanner);

        try {
            List<String> loadedHistory = Robot.loadHistory(HISTORY_FILE);
            if (!loadedHistory.isEmpty()) {
                System.out.println("Loaded previous movement history.");
            }
            IntelligentRobot robot = new IntelligentRobot(0, 0, battery);
            robot.movementHistory = loadedHistory; // Set the loaded history to the robot

            while (true) {
                displayMenu();
                int choice = getIntegerInput(scanner, "Enter your choice: ");

                switch (choice) {
                    case 1:
                        moveRobot(robot, scanner);
                        break;
                    case 2:
                        robot.displayPosition();
                        break;
                    case 3:
                        displayHistory(robot.movementHistory);
                        break;
                    case 4:
                        updateMovement(robot.movementHistory, scanner);
                        break;
                    case 5:
                        robot.movementHistory.clear();
                        System.out.println("Movement history reset.");
                        break;
                    case 6:
                        robot.scanSurroundings();
                        break;
                    case 7:
                        robot.recharge();
                        break;
                    case 8:
                        try {
                            Robot.saveHistory(robot.movementHistory, HISTORY_FILE);
                            System.out.println("Progress saved. Exiting.");
                        } catch (IOException e) {
                            System.err.println("Error saving progress: " + e.getMessage());
                        }
                        return; // Exit the program
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading history: " + e.getMessage());
        }
    }

    private static int getInitialBattery(Scanner scanner) {
        int battery;
        do {
            System.out.print("Enter initial battery level (1-100): ");
            battery = scanner.nextInt();
            if (battery < 1 || battery > 100) {
                System.out.println("Invalid battery level. Please enter a value between 1 and 100.");
            }
        } while (battery < 1 || battery > 100);
        return battery;
    }

    private static void displayMenu() {
        System.out.println("\n--- Robot Control System ---");
        System.out.println("1. Move Robot");
        System.out.println("2. Display Current Position");
        System.out.println("3. Display Movement History");
        System.out.println("4. Update Movement History");
        System.out.println("5. Reset Movement History");
        System.out.println("6. Scan Surroundings");
        System.out.println("7. Recharge Robot");
        System.out.println("8. Save and Exit");
    }

    private static void moveRobot(IntelligentRobot robot, Scanner scanner) {
        System.out.print("Enter direction (up, down, left, right): ");
        String direction = scanner.next();
        if (!Robot.isValidDirection(direction)) {
            System.out.println("Invalid direction. Please try again.");
            return;
        }

        int steps = getIntegerInput(scanner, "Enter number of steps: ");
        robot.move(direction, steps);
    }

    private static void displayHistory(List<String> history) {
        if (history.isEmpty()) {
            System.out.println("No movement history available.");
        } else {
            System.out.println("\n--- Movement History ---");
            for (String entry : history) {
                System.out.println(entry);
            }
        }
    }

    private static void updateMovement(List<String> history, Scanner scanner) {
        System.out.print("Enter the index of the movement to update: ");
        int index = scanner.nextInt();
        if (index < 0 || index >= history.size()) {
            System.out.println("Invalid index. No changes made.");
            return;
        }

        System.out.print("Enter new movement description: ");
        scanner.nextLine(); // Consume newline
        String newMove = scanner.nextLine();
        history.set(index, newMove + " at " + new Date());
        System.out.println("Movement updated.");
    }

    private static int getIntegerInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear invalid input
            }
        }
    }

}
