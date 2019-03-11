package runner;

import java.util.Random;
import javafx.animation.Timeline;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;


public class Game extends Runner {
    
    
    Timeline boxTL1;
    Timeline boxTL2;
    Timeline bgeTL;
    Timeline jumpTL;
    Timeline runTL;
    Timeline slideTL;
    AudioClip bgm;
    int score = 0;
    Boolean pause = false;
    Boolean gOver = false;
    Random random = new Random();
    int[] pattern;
    int i;
    
    public Game(Timeline boxTL1, Timeline boxTL2, Timeline bgeTL, Timeline jumpTL, Timeline runTL, Timeline slideTL, AudioClip bgm){
        
        this.boxTL1 = boxTL1;
        this.boxTL2 = boxTL2;
        this.bgeTL = bgeTL;
        this.jumpTL = jumpTL;
        this.runTL = runTL;
        this.slideTL = slideTL;
        this.bgm = bgm;
        pattern = new int[100];
        for(i = 0; i<100; i++)
        {
            pattern[i]=random.nextInt(2);
        }
        i = 0;
    }
    
    public Boolean GameStatus(){
        return gOver;
        
    }
    
    public void GameOver(){
        
    boxTL1.stop();
    boxTL2.stop();
    bgeTL.stop();
    jumpTL.stop();
    runTL.stop();
    slideTL.stop();
    bgm.stop();
    gOver = true;
    
    }
    
    public int AddScore(){
        score = score + 100;
        
        bgeTL.setRate(bgeTL.getRate() + 0.05);
        boxTL1.setRate(boxTL1.getRate() + 0.05);
        boxTL2.setRate(boxTL2.getRate() + 0.05);
        jumpTL.setRate(jumpTL.getRate() + 0.05);
        slideTL.setRate(slideTL.getRate() + 0.05);
        runTL.setRate(runTL.getRate() + 0.05);
        
        return score;
    }
    
    public int ResetScore(){
        score = 0;
        return score;
    }
    
    public void GameResume(){
        boxTL1.jumpTo(Duration.ZERO);
        boxTL2.jumpTo(Duration.ZERO);
        bgeTL.jumpTo(Duration.ZERO);
        jumpTL.jumpTo(Duration.ZERO);
        runTL.jumpTo(Duration.ZERO);
        slideTL.jumpTo(Duration.ZERO);
        bgeTL.setRate(1);
        boxTL1.setRate(1);
        boxTL2.setRate(1);
        jumpTL.setRate(1);
        slideTL.setRate(1);
        runTL.setRate(1);
        boxTL1.play();
        bgeTL.play();
        runTL.play();
        bgm.play();
        gOver = false;
    
    }
    
    public void NextBox(){
        
     if(pattern[i]==0)
         boxTL1.play();
     else
         boxTL2.play();
     i++;
        
     }   
    
    
}
