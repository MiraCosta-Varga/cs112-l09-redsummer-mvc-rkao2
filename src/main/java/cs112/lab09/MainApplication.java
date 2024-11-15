package cs112.lab09;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Random;

import java.io.IOException;

public class MainApplication extends Application /* implements EventHandler<ActionEvent>*/ {
    //CONSTANTS

    //array of LoteriaCards to use for game:
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
    };

    private ImageView cardImageView;
    private Label messageLabel;
    private int count = 0;

    private static LoteriaCard[] shuffleCards = shuffleLoteriaDeck();

    private static LoteriaCard[] shuffleLoteriaDeck(){
        LoteriaCard[] cards = new LoteriaCard[LOTERIA_CARDS.length];

        //deep copying EACH card object
        for(int i=0; i<cards.length; i++){
            cards[i] = new LoteriaCard(LOTERIA_CARDS[i]);
        }

        //shuffle
        for(int i = 0; i<10 * cards.length; i++){
            int randomInt1 = (int) (Math.random() * LOTERIA_CARDS.length);
            int randomInt2 = (int) (Math.random() * LOTERIA_CARDS.length);

            //code to swap two objects in array
            LoteriaCard temp = LOTERIA_CARDS[randomInt1];
            cards[randomInt1] = cards[randomInt2];
            cards[randomInt2] = temp;

        }

        return cards;
    }

    @Override
    public void start(Stage stage) throws IOException {
        LoteriaCard cardLogo = new LoteriaCard();

        //SETUP COMPONENTS
        Label titleLabel = new Label("Welcome to EChALE STEM Loteria!");
        ImageView cardImageView = new ImageView();
        Label messageLabel = new Label("Click the button to randomly draw a card.");
        Button drawCardButton = new Button("Draw Random Card");

        //CUSTOMIZE COMPONENT
        messageLabel.setWrapText(true);
        messageLabel.setAlignment(Pos.CENTER);
        messageLabel.setTextAlignment(TextAlignment.CENTER);
        //cardImageView.setFitWidth(300);
        //cardImageView.setPreserveRatio(true);
        titleLabel.setFont(new Font(20.0));
        drawCardButton.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent actionEvent){

                        if(count == shuffleCards.length){
                            //end the game
                            messageLabel.setText("GAME OVER! No more cards! Exit and run program to reset ^_^ ");
                            cardImageView.setImage(new Image("file:src/main/resources/0.png"));
                            drawCardButton.setDisable(true);
                        } else{
                            // get random card
                            LoteriaCard randomCard = shuffleCards[count];

                            // image changes to card name
                            //cardImageView.setImage( randomCard.getImage() );

                            //message label
                           // messageLabel.setText( randomCard.getCardName() );
                            count++;
                        }

                    }
                }
        );



        //ADD COMPONENTS
        VBox vbox = new VBox();
        vbox.getChildren().addAll(titleLabel, cardImageView, messageLabel, drawCardButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10.0);

        //SETUP SCENE AND SHOW
        Scene scene = new Scene(vbox, 350, 500);
        stage.setScene(scene);
        stage.setTitle("EChALE STEM Loteria");
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}





