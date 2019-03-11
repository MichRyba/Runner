package runner;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Runner extends Application {
    
       
    @Override
    public void start(Stage primaryStage) {
       
       
    StackPane root = new StackPane();
   
    Scene scene = new Scene(root, 1000, 600); 
    primaryStage.setTitle("Gaem");
    primaryStage.setScene(scene);
    
                                                                                //Zasoby i zmienne
    int cycle = 3000;
    int score = 0;
    
    Image BG = new Image(Runner.class.getResource("/runner/resources/Background.png").toString());
    Image BGElem = new Image(Runner.class.getResource("/runner/resources/BGElem.png").toString());
    Image Hero = new Image(Runner.class.getResource("/runner/resources/Hero.png").toString());
    
    AudioClip hitS = new AudioClip(Runner.class.getResource("/runner/resources/hit.mp3").toString());
    hitS.setVolume(0.1);
    
    AudioClip jumpS = new AudioClip(Runner.class.getResource("/runner/resources/jump.wav").toString());
    jumpS.setVolume(0.1);
    
    AudioClip slideS = new AudioClip(Runner.class.getResource("/runner/resources/slide.mp3").toString());
    slideS.setVolume(1);
    
    AudioClip bgm = new AudioClip(Runner.class.getResource("/runner/resources/bgmusic.mp3").toString());
    bgm.setCycleCount(Timeline.INDEFINITE);
    bgm.setVolume(0.3);
    bgm.play();
    
    
    Timeline boxTL1 = new Timeline();
    Timeline boxTL2 = new Timeline();
    Timeline bgeTL = new Timeline();
    Timeline jumpTL = new Timeline();
    Timeline runTL = new Timeline();
    Timeline slideTL = new Timeline();
    Timeline rotator = new Timeline();
    
    Rectangle killBox1 = new Rectangle(10,10);
    Rectangle killBox2 = new Rectangle(10,10);
    killBox1.setTranslateX(600);
    killBox1.setTranslateY(135);
    killBox2.setTranslateX(600);
    killBox2.setTranslateY(135);
    
    
    Game game = new Game(boxTL1, boxTL2, bgeTL, jumpTL, runTL, slideTL, bgm);
    
    
    
                                                                                //Tło gry  

    ImageView ivBG = new ImageView(BG);
    ivBG.setFitWidth(1000);
    ivBG.setFitHeight(600);
    root.getChildren().add(ivBG);
    
                                                                                //Tekst
    Text text = new Text();
    text.setFont(Font.font ("Verdana",FontWeight.BOLD, 20));
    text.setTranslateX(-400);
    text.setTranslateY(-250);    
    text.setText(Integer.toString(score));
    root.getChildren().add(text);
    
    Text GameOver = new Text();
    GameOver.setFont(Font.font ("Verdana",FontWeight.BOLD, 120));
    GameOver.setTranslateX(0);
    GameOver.setTranslateY(0);    
    GameOver.setText("Game Over");
    GameOver.setVisible(false);
    root.getChildren().add(GameOver);
    
                                                                                //Elementy tła
    bgeTL.setCycleCount(Timeline.INDEFINITE);
    bgeTL.setAutoReverse(false);
    
    
    
        
    ImageView ivBGE = new ImageView(BGElem);
    ivBGE.setFitHeight(75);
    ivBGE.setTranslateX(100);
    ivBGE.setTranslateX(300);
    root.getChildren().add(ivBGE);
    
    bgeTL.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, // ustaw start na 0s
                    new KeyValue(ivBGE.translateXProperty(), 1000),
                    new KeyValue(ivBGE.translateYProperty(), 280)),
                    new KeyFrame(new Duration(cycle*2), // ustaw koniec na 4s
                    new KeyValue(ivBGE.translateXProperty(), -1000),
                    new KeyValue(ivBGE.translateYProperty(), 280))
    );
    
    
    
 
    
                                                                                //postać
    PixelReader readerH = Hero.getPixelReader();
    WritableImage newHero1 = new WritableImage(readerH, 0, 0, 20, 40);
    WritableImage newHero2 = new WritableImage(readerH, 20, 0, 20, 40);
    WritableImage newHero3 = new WritableImage(readerH, 40, 0, 20, 40);
    WritableImage newHero4 = new WritableImage(readerH, 60, 0, 20, 40);
    WritableImage newHero5 = new WritableImage(readerH, 80, 0, 20, 40);
    WritableImage newHero6 = new WritableImage(readerH, 100, 0, 20, 40);
    WritableImage newHero7 = new WritableImage(readerH, 120, 0, 20, 40);
    
    Image heroR1 = newHero1;
    Image heroR2 = newHero2;
    Image heroR3 = newHero3;
    Image heroJ1 = newHero4;
    Image heroJ2 = newHero5;
    Image heroS1 = newHero6;
    Image heroS2 = newHero7;
    
    ImageView heroR = new ImageView(newHero1);
    
    heroR.setFitWidth(40);
    heroR.setFitHeight(80);
    heroR.setTranslateX(-450);
    heroR.setTranslateY(100);
    root.getChildren().add(heroR);
    
    
    runTL.setCycleCount(Timeline.INDEFINITE);
    runTL.setAutoReverse(true);
    
    runTL.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, e -> heroR.setImage(heroR1)),
            new KeyFrame(new Duration(500), e -> heroR.setImage(heroR2)),
            new KeyFrame(new Duration(1000), e -> heroR.setImage(heroR3))
    );
    
    
    jumpTL.setCycleCount(1);
    jumpTL.setAutoReverse(false);
    jumpTL.setOnFinished(evnt -> runTL.play());
    
    jumpTL.getKeyFrames().addAll(new KeyFrame(Duration.ZERO,
                    e ->{ 
                        heroR.setImage(heroJ1);
                    },
                new KeyValue(heroR.translateXProperty(), -450),
                new KeyValue(heroR.translateYProperty(), 100)),
                new KeyFrame(new Duration(500),
                        e ->{ 
                        heroR.setImage(heroJ2);
                    },
                new KeyValue(heroR.translateXProperty(), -450),
                new KeyValue(heroR.translateYProperty(), 70)),
                new KeyFrame(new Duration(1000),
                new KeyValue(heroR.translateXProperty(), -450),
                new KeyValue(heroR.translateYProperty(), 100))
    );

    



    slideTL.setCycleCount(1);
    slideTL.setAutoReverse(false);
    slideTL.setOnFinished(evnt -> runTL.play());
            
    slideTL.getKeyFrames().addAll(new KeyFrame(Duration.ZERO,
                    e ->{ 
                        heroR.setImage(heroS1);
                    },
                new KeyValue(heroR.translateXProperty(), -450),
                new KeyValue(heroR.translateYProperty(), 100)),
                new KeyFrame(new Duration(500),
                        e ->{ 
                        heroR.setImage(heroS2);
                    },
                new KeyValue(heroR.translateXProperty(), -450),
                new KeyValue(heroR.translateYProperty(), 100)),
                new KeyFrame(new Duration(1000),
                new KeyValue(heroR.translateXProperty(), -450),
                new KeyValue(heroR.translateYProperty(), 100))
    );

    
scene.setOnKeyPressed(e -> {
    if (e.getCode() == KeyCode.UP) {
        if(jumpTL.getStatus() != Animation.Status.RUNNING && slideTL.getStatus() != Animation.Status.RUNNING && game.GameStatus()==false){
            runTL.pause();
            jumpTL.play();
            jumpS.play();
        }
            
       
    }
    
    if (e.getCode() == KeyCode.DOWN) {
        if(jumpTL.getStatus() != Animation.Status.RUNNING && slideTL.getStatus() != Animation.Status.RUNNING && game.GameStatus()==false){
            runTL.pause();
            slideTL.play();
            slideS.play();
        }
    }
    
    if(e.getCode() == KeyCode.SPACE){
        if(game.GameStatus()==true){
        killBox1.setVisible(false);
        killBox2.setVisible(false);
        game.GameResume();
        GameOver.setVisible(false);
        text.setText(Integer.toString(game.ResetScore()));
        }
    }
    });


                                                                                //przeszkody
    
    root.getChildren().add(killBox1);
    boxTL1.setCycleCount(1);
    boxTL1.setAutoReverse(false);
    root.getChildren().add(killBox2);
    boxTL2.setCycleCount(1);
    boxTL2.setAutoReverse(false);
    rotator.setCycleCount(Timeline.INDEFINITE);
    rotator.setAutoReverse(false);
    
    
    boxTL1.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, // ustaw start na 0s
                            evt ->{
                              killBox1.setVisible(true);
                            },
                    new KeyValue(killBox1.translateXProperty(), 600),
                    new KeyValue(killBox1.translateYProperty(), 135)),
                    
                    new KeyFrame(new Duration(cycle - 430),
                            e -> {
                        if(jumpTL.getStatus() != Animation.Status.RUNNING){
                            game.GameOver();
                            GameOver.setVisible(true);
                            hitS.play();
                        }
                        }), 
                    
                    new KeyFrame(new Duration(cycle),   // ustaw koniec na 4s
                    new KeyValue(killBox1.translateXProperty(), -600),
                    new KeyValue(killBox1.translateYProperty(), 135))
                );
    
    boxTL1.setOnFinished(
            evt ->{
                    game.NextBox();
                    text.setText(Integer.toString(game.AddScore()));
                    
            }
    );
    

     boxTL2.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, // ustaw start na 0s
                            evt ->{
                              killBox2.setVisible(true);
                            },
                    new KeyValue(killBox2.translateXProperty(), 600),
                    new KeyValue(killBox2.translateYProperty(), 115)),
            
                    new KeyFrame(new Duration(cycle - 430), 
                            e -> {
                                
                        if(slideTL.getStatus() != Animation.Status.RUNNING){
                            game.GameOver();
                            GameOver.setVisible(true);
                            hitS.play();
                        }
                        }
                    ),                     
                
                    new KeyFrame(new Duration(cycle),   // ustaw koniec na 4s
                      
                    new KeyValue(killBox2.translateXProperty(), -600),
                    new KeyValue(killBox2.translateYProperty(), 115)));
    
    
    boxTL2.setOnFinished(
            evt ->{
                    game.NextBox();
                    text.setText(Integer.toString(game.AddScore()));
                    
            }
    ); 
    
    rotator.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                new KeyValue(killBox1.rotateProperty(), 0),
                new KeyValue(killBox2.rotateProperty(), 0)),
                new KeyFrame(Duration.seconds(1),
                new KeyValue(killBox1.rotateProperty(), 360),
                new KeyValue(killBox2.rotateProperty(), 360))
            );
    
    
    
        
    boxTL1.play();
    runTL.play();
    bgeTL.play();
    rotator.play();
    
    primaryStage.show();
    
   
}
    


    
    public static void main(String[] args) {
        launch(args);
    }   
}
