
import java.io.*;
import java.util.*;

// Abstract Class: Robot
abstract class Robot {

    int x, y;
    int battery;
    String status;
    List<String> movementHistory;

    public Robot(int x, int y, int battery) {
        this.x = x;
        this.y = y;
        this.battery = battery;
        this.status = "Active";
        this.movementHistory = new ArrayList<>();
    }

    abstract void move(String direction, int steps);

    abstract void scanSurroundings();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBattery() {
        return battery;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void trackMovement(String move) {
        movementHistory.add(move + " at " + new Date());
    }

    public void displayPosition() {
        System.out.println("Current Position: (" + x + ", " + y + ")");
        displayGrid();
    }

    public void displayGrid() {
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (i == y && j == x) {
                    System.out.print("R ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public static boolean isValidDirection(String direction) {
        return direction.equalsIgnoreCase("up") || direction.equalsIgnoreCase("down")
                || direction.equalsIgnoreCase("left") || direction.equalsIgnoreCase("right");
    }

    public static void saveHistory(List<String> history, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(history);
        }
    }

    public static List<String> loadHistory(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<String>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>(); // Return empty list if file not found
        }
    }

}

// Interface: Rechargeable
interface Rechargeable {

    void recharge();

    boolean isLowBattery();
}

// Concrete Class: IntelligentRobot
class IntelligentRobot extends Robot implements Rechargeable {

    private boolean[][] obstacles = new boolean[10][10];
    private static final int MAX_X = 9;
    private static final int MAX_Y = 9;
    private static final int LOW_BATTERY_THRESHOLD = 5;
    private static final int RECHARGE_AMOUNT = 100;
    private List<int[]> chargingStations = Arrays.asList(new int[]{0, 0}, new int[]{5, 5}, new int[]{9, 9}); // Manually defined charging stations

    public IntelligentRobot(int x, int y, int battery) {
        super(x, y, battery);
        initializeObstacles();
    }

    private void initializeObstacles() {
        // Example obstacle positions (you can load these from a file)
        obstacles[2][3] = true;
        obstacles[7][1] = true;
        obstacles[4][8] = true;
        obstacles[1][1] = true;
        obstacles[8][6] = true;
    }

    @Override
    void move(String direction, int steps) {
        if (isLowBattery()) {
            System.out.println("Battery low! Recharge required.");
            return;
        }

        if (!isValidDirection(direction)) {
            System.out.println("Invalid direction.");
            return;
        }

        for (int i = 0; i < steps; i++) {
            if (moveOneStep(direction)) {
                battery--;
                trackMovement("Moved " + direction);
                displayPosition();
                scanForChargingStation();
            } else {
                System.out.println("Cannot move further in that direction.");
                break; // Stop if a move fails
            }

            if (isLowBattery()) {
                System.out.println("Battery low! Movement stopped.");
                break;
            }
        }
    }

    private boolean moveOneStep(String direction) {
        int newX = x;
        int newY = y;

        switch (direction.toLowerCase()) {
            case "up":
                newY = Math.min(y + 1, MAX_Y);
                break;
            case "down":
                newY = Math.max(y - 1, 0);
                break;
            case "left":
                newX = Math.max(x - 1, 0);
                break;
            case "right":
                newX = Math.min(x + 1, MAX_X);
                break;
        }

        if (newX != x || newY != y) { // Check if the robot actually moved
            if (obstacles[newY][newX]) { // Check for obstacles
                System.out.println("Obstacle detected at (" + newX + ", " + newY + "). Cannot move.");
                return false;
            }
            x = newX;
            y = newY;
            return true;
        } else {
            return false; // Did not move (out of bounds or obstacle)
        }
    }

    @Override
    void scanSurroundings() {
        System.out.println("Scanning surroundings...");
        displaySurroundingsGrid(); // Displays the grid with obstacles
    }

    private void displaySurroundingsGrid() {
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (obstacles[i][j]) {
                    System.out.print("O "); // 'O' for obstacle
                } else if (i == y && j == x) {
                    System.out.print("R "); // 'R' for robot
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public void recharge() {
        battery = RECHARGE_AMOUNT;
        System.out.println("Robot recharged. Battery level: " + battery);
    }

    @Override
    public boolean isLowBattery() {
        return battery < LOW_BATTERY_THRESHOLD;
    }

    private void scanForChargingStation() {
        for (int[] station : chargingStations) {
            if (x == station[0] && y == station[1]) {
                System.out.println("Arrived at charging station. Recharging...");
                recharge();
                break; // Stop checking after finding one
            }
        }
    }

    //Overloaded Move Method
    public void move(String direction) {
        move(direction, 1);
    }

    public void move(String direction, int steps, boolean avoidObstacles) {
        if (avoidObstacles) {
            System.out.println("Obstacle avoidance logic not yet implemented.");
        }
        move(direction, steps);
    }
    // Utility Class MovementTracker

    class MovementTracker {

        public static void saveHistory(List<String> history, String filename) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                for (String move : history) {
                    writer.write(move + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error saving history.");
            }
        }

        public static List<String> loadHistory(String filename) {
            List<String> history = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    history.add(line);
                }
            } catch (IOException e) {
                System.out.println("No previous history found.");
            }
            return history;
        }
    }
}
