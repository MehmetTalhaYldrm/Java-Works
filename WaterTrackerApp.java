// This file demonstrates the structure for a typical Android application in Java.
// To make this a real app, you would need Android Studio and to create corresponding
// XML layout files for the user interface.

// --- File 1: WaterBubble.java (The Logic for a single bubble) ---
// This class is self-contained and holds the state for each bubble.

class WaterBubble {

    private final double waterAmountLiters;
    private final int requiredTaps;
    private int currentTaps;
    private boolean isPopped;

    /**
     * Constructor for creating a new WaterBubble.
     * @param waterAmountLiters The amount of water in this bubble.
     */
    public WaterBubble(double waterAmountLiters) {
        this.waterAmountLiters = waterAmountLiters;
        this.currentTaps = 0;
        this.isPopped = false;
        // Simple rule: A 0.25L bubble needs 3 taps, a 0.50L needs 5 taps.
        this.requiredTaps = (int) (waterAmountLiters * 8) + 1;
    }

    /**
     * Simulates a user tapping the bubble.
     * @return true if the bubble pops on this tap, false otherwise.
     */
    public boolean tap() {
        if (isPopped) {
            return false; // Already popped
        }

        this.currentTaps++;

        if (this.currentTaps >= this.requiredTaps) {
            this.isPopped = true;
            return true; // Pop!
        }
        return false; // Not popped yet
    }
    
    // --- Getters ---
    public double getWaterAmountLiters() { return waterAmountLiters; }
    public boolean isPopped() { return isPopped; }
}


// --- File 2: WaterTracker.java (The Logic for the daily total) ---
// This class manages the overall state of water consumption.

class WaterTracker {

    private double dailyTotalLiters;

    public WaterTracker() {
        this.dailyTotalLiters = 0.0;
    }

    /**
     * Adds a specific amount of water to the daily total.
     * @param liters The amount of water in liters to add.
     */
    public void addWater(double liters) {
        if (liters > 0) {
            this.dailyTotalLiters += liters;
        }
    }

    public double getDailyTotalLiters() {
        return dailyTotalLiters;
    }

    public void reset() {
        this.dailyTotalLiters = 0.0;
    }
}


// --- File 3: MainActivity.java (The Android Screen Logic) ---
// This is a conceptual representation of your main screen's code.
// It shows how you would connect the UI to the WaterBubble and WaterTracker classes.
// You would need to import Android classes like `AppCompatActivity`, `View`, `TextView`, `Button`, etc.

/*
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import java.util.Locale;
*/

public class MainActivity /* extends AppCompatActivity */ {

    // --- Core Logic Objects ---
    private WaterTracker waterTracker;
    private WaterBubble bubble1;
    private WaterBubble bubble2;
    private WaterBubble bubble3;

    // --- UI Element Placeholders ---
    // In a real Android app, you would link these to your XML layout file.
    private TextView totalDisplayText;
    private ImageView bubble1View; // Represents the bubble image on screen
    private ImageView bubble2View;
    private ImageView bubble3View;
    private Button resetButton;

    // This is the main method in Android where your app screen starts.
    // @Override
    // protected void onCreate(Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //     setContentView(R.layout.activity_main); // Links to your XML layout

    public void setupApp() { // We use this method to simulate onCreate()
        
        // 1. Initialize the core logic
        waterTracker = new WaterTracker();
        
        // Create the bubbles with different water amounts
        bubble1 = new WaterBubble(0.25);
        bubble2 = new WaterBubble(0.50);
        bubble3 = new WaterBubble(0.75);

        // 2. Link UI variables to actual UI elements from the XML layout
        // In a real app, you'd use: totalDisplayText = findViewById(R.id.totalDisplayText);
        // We will simulate these objects.
        System.out.println("--- App Started ---");
        // totalDisplayText = new TextView(); // Simulated
        // bubble1View = new ImageView(); // Simulated
        // bubble2View = new ImageView(); // Simulated
        // bubble3View = new ImageView(); // Simulated
        // resetButton = new Button(); // Simulated

        // 3. Set up the initial display
        updateTotalDisplay();

        // 4. Set up click listeners for the bubbles
        // This is the key part: what happens when a user taps a bubble?
        
        // Listener for the first bubble (0.25L)
        // bubble1View.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         handleBubbleTap(bubble1, bubble1View);
        //     }
        // });
        
        // Listener for the second bubble (0.50L)
        // bubble2View.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         handleBubbleTap(bubble2, bubble2View);
        //     }
        // });
        
        // Listener for the third bubble (0.75L)
        // bubble3View.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         handleBubbleTap(bubble3, bubble3View);
        //     }
        // });
    }

    /**
     * A reusable method to handle the logic when any bubble is tapped.
     * @param bubble The logical bubble object.
     * @param bubbleView The visual ImageView for the bubble.
     */
    private void handleBubbleTap(WaterBubble bubble, /* ImageView */ Object bubbleView) {
        if (bubble.isPopped()) {
            System.out.println("This bubble is already popped.");
            return; // Do nothing if already popped
        }

        System.out.println("Tapped a bubble with " + bubble.getWaterAmountLiters() + "L.");
        boolean didPop = bubble.tap();

        if (didPop) {
            System.out.println("POP!");
            // Add water to the tracker
            waterTracker.addWater(bubble.getWaterAmountLiters());
            // Update the screen
            updateTotalDisplay();
            // Make the bubble disappear from the screen
            // In a real app: bubbleView.setVisibility(View.GONE);
            System.out.println("Bubble view is now hidden.");
        } else {
            // Optional: Play a wobble animation on the bubbleView
            System.out.println("Bubble wobbles...");
        }
    }

    /**
     * Updates the text on the screen to show the current total.
     */
    private void updateTotalDisplay() {
        double total = waterTracker.getDailyTotalLiters();
        String formattedTotal = String.format(Locale.US, "%.2fL", total);
        // In a real app: totalDisplayText.setText(formattedTotal);
        System.out.println("--- DISPLAY UPDATED: " + formattedTotal + " ---");
    }
    
    // --- Main method to simulate the app's lifecycle ---
    public static void main(String[] args) {
        MainActivity app = new MainActivity();
        app.setupApp();
        
        System.out.println("\n--- SIMULATING USER TAPS ---");
        
        // Simulate tapping the 0.25L bubble until it pops
        System.out.println("\n* Tapping the 0.25L bubble (needs 3 taps)...");
        app.handleBubbleTap(app.bubble1, null); // Tap 1
        app.handleBubbleTap(app.bubble1, null); // Tap 2
        app.handleBubbleTap(app.bubble1, null); // Tap 3 (should pop)
        
        // Simulate tapping the 0.50L bubble
        System.out.println("\n* Tapping the 0.50L bubble (needs 5 taps)...");
        app.handleBubbleTap(app.bubble2, null); // Tap 1
        app.handleBubbleTap(app.bubble2, null); // Tap 2
        
        // Simulate tapping the popped bubble again
        System.out.println("\n* Tapping the 0.25L bubble again...");
        app.handleBubbleTap(app.bubble1, null);
    }
}
