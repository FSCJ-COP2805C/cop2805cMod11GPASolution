import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class BlackJackGame extends Application {
     final int NUMCARDS = 52;
     final int CARDSPERSUIT = 13;

     final String FILE_PREFIX = "file:///C:./images/";
    
     private final int SPACING = 20;
     private final String TITLE = "Blackjack";
     private final int SCENE_H = 300;
     private final int SCENE_V = 100;
    
     private ImageView card1, card2;
     private ArrayList<Card> blackJackList;
     private HBox pane;
     private VBox ctrlInfo;
     private Text txtTotal;
     private Button btDeal;
     private int totalPoints;
     private String totalString;
    
     // Card class will manage our cards
     class Card {

         // index for suits
         final int SUIT_CLUB = 1, SUIT_DIAMOND = 2,
                SUIT_HEART = 3, SUIT_SPADE = 4;

         // names for suits
         final String[] SUIT_ARRAY = { "CLUB", "DIAMOND", "HEART", "SPADE" };
         int suit;

         // each card has a face value
         int faceValue;

            // numeric file name index
         int fileIndex;
        
         Card(int suit, int faceValue, int fileIndex) {
             this.suit = suit;
             this.faceValue = faceValue;
             this.fileIndex = fileIndex;
         }
        
         public int getFileIndex() {
            return this.fileIndex;
         }
        
         public String toString() {
            return SUIT_ARRAY[suit] + ": " + faceValue + " (index " + fileIndex + ")";
         }
     }
    
     @Override // Override the start method in the Application class
     public void start(Stage primaryStage) {
         blackJackList = new ArrayList<>();
     
         for (int i = 1; i <= NUMCARDS; i++)
            blackJackList.add(new Card(getSuit(i), getBlackJackFaceValue(i), i));
    
         //HBox for everybody!
         pane = new HBox(SPACING);
         pane.setAlignment(Pos.CENTER);
        
         //VBox for our button and text
         ctrlInfo = new VBox(SPACING);
         ctrlInfo.setAlignment(Pos.CENTER_LEFT);
         ctrlInfo.setPadding(new Insets(5,5,5,5));
        
         //Button for dealing
         btDeal = new Button("Deal");
         ctrlInfo.getChildren().add(btDeal);
        
         java.util.Collections.shuffle(blackJackList);
        
         int index = blackJackList.get(0).getFileIndex();
         card1 = new ImageView(FILE_PREFIX + index + ".png");
         //System.out.println("card1: " + FILE_PREFIX + index + ".png");
         totalPoints = getBlackJackFaceValue(index);
        
         index = blackJackList.get(1).getFileIndex();
         card2 = new ImageView(FILE_PREFIX + index + ".png");
         //System.out.println("card2: " + FILE_PREFIX + index + ".png");
         totalPoints += getBlackJackFaceValue(index);
        
         totalString = String.format("Total: %02d", totalPoints);
         txtTotal = new Text(totalString);
        
         if (totalPoints == 21) // highlight 21
            txtTotal.setFill(Color.RED);
         else
            txtTotal.setFill(Color.BLACK);

         ctrlInfo.getChildren().add(txtTotal);
         
         pane.getChildren().add(card1);
         pane.getChildren().add(card2);
         pane.getChildren().add(ctrlInfo);
        
         // button press: remove everything, get 2 new cards,
         // recalculate our total, and repopulate the pane.
         btDeal.setOnAction(e -> {
             pane.getChildren().remove(card1);
             pane.getChildren().remove(card2);
             pane.getChildren().remove(ctrlInfo);
             java.util.Collections.shuffle(blackJackList);

         getNewCardsAndRecalc();
         pane.getChildren().add(card1);
         pane.getChildren().add(card2);
         pane.getChildren().add(ctrlInfo);
    
     });
    
     // Create a scene and place it in the stage
     Scene scene = new Scene(pane, SCENE_H, SCENE_V);
         primaryStage.setTitle(TITLE); // Set the stage title
         primaryStage.setScene(scene); // Place the scene in the stage
         primaryStage.show(); // Display the stage
     }
    
     void getNewCardsAndRecalc() { // get new cards, recalculate
         int index = blackJackList.get(0).getFileIndex();
         card1 = new ImageView(FILE_PREFIX + index + ".png");
         //System.out.println("new card1: " + FILE_PREFIX + index + ".png");
         totalPoints = getBlackJackFaceValue(index);
        
         index = blackJackList.get(1).getFileIndex();
         card2 = new ImageView(FILE_PREFIX + index + ".png");
         //System.out.println("new card2: " + FILE_PREFIX + index + ".png");
         totalPoints += getBlackJackFaceValue(index);
        
         if (totalPoints == 22) // exception: two Aces
            totalPoints = 12;
        
         totalString = String.format("Total: %02d", totalPoints);
         txtTotal.setText(totalString);
        
         if (totalPoints == 21) // highlight 21
            txtTotal.setFill(Color.RED);
         else
            txtTotal.setFill(Color.BLACK);
     }
    
     int getSuit(int fileIndex) // map suits based on file indexing
     {
         int calcSuit = 0;
        
         if (fileIndex >= 14 && fileIndex <= 26)
            calcSuit = 1;
         else if (fileIndex >= 27 && fileIndex <= 39)
            calcSuit = 2;
         else if (fileIndex >=40 && fileIndex <= 52)
            calcSuit = 3;
        
         return calcSuit;
     }
    
     int getBlackJackFaceValue(int fileIndex) // blackjack-specific
     {
         int calcFaceValue = 10; // default to face card value
        
         switch (fileIndex)
         {
             case 1: case 14: case 27: case 40: // Aces
                 calcFaceValue = 11;
                 break;
             case 2: case 15: case 28: case 41:
                 calcFaceValue = 2;
                 break;
             case 3: case 16: case 29: case 42:
                 calcFaceValue = 3;
                 break;
             case 4: case 17: case 30: case 43:
                 calcFaceValue = 4;
                 break;
             case 5: case 18: case 31: case 44:
                 calcFaceValue = 5;
                 break;
             case 6: case 19: case 32: case 45:
                 calcFaceValue = 6;
                 break;
             case 7: case 20: case 33: case 46:
                 calcFaceValue = 7;
                 break;
             case 8: case 21: case 34: case 47:
                 calcFaceValue = 8;
                 break;
             case 9: case 22: case 35: case 48:
                 calcFaceValue = 9;
                 break;
         }
         return calcFaceValue;
     }
    
     /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
     public static void main(String[] args) {
        launch(args);
     }
    
     public static void log(Object o) {
        System.out.println(o);
     }
}