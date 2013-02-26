// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PlayingScreen.java

package vn.vhn.amazon.kindle.chinesechess.ui;

import com.amazon.kindle.kindlet.KindletContext;
import com.amazon.kindle.kindlet.ui.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;  //warning: conflict with java.util.Timer
import vn.vhn.amazon.kindle.chinesechess.Board.ChineseBoard;
import vn.vhn.amazon.kindle.chinesechess.Main;
import vn.vhn.amazon.kindle.chinesechess.posCollection;

// Referenced classes of package vn.vhn.amazon.kindle.chinesechess.ui:
//            BoardComponent

public class PlayingScreen extends JPanel
{

    public PlayingScreen(KindletContext context, ChineseBoard board)
    {
        super(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        titleLabel = new JLabel();
        infoLabel = new JLabel();
        clockLabel = new JLabel();
        debugLabel = new JLabel();
        titleLabel.setFont(new Font(null, 1, 17));
        gc.gridx = 0;
        gc.gridy = 0;
        if(Main.posmode == 0)
        {
            titleLabel.setText(Main.rLang.getString("youvs").concat(Main.levelName[Main.level]));
        } else
        {
            posCollection _tmp = Main.pc;
            titleLabel.setText(posCollection.itemName[Main.posi].concat(" ".concat(String.valueOf(Main.posj + 1))));
        }
        add(titleLabel, gc);
        boardComp = new BoardComponent(context, infoLabel, board);
        gc.gridy = 1;
        add(boardComp, gc);
        gc.gridy = 2;
        // PHO: display play time here
        //clockLabel.setText(Main.rLang.getString("gameTime").concat(" 00:00:00"));  
        //clockLabel.setText(Main.rLang.getString("gameTime").concat(" 00 hr 00 min"));  
        String clk1;
        clk1 = Main.rLang.getString("gameTime").concat(" 00 ".concat(Main.rLang.getString("hourUnit")));
        clockLabel.setText(clk1.concat(" 00 ".concat(Main.rLang.getString("minUnit"))));  

        add(clockLabel, gc);
        //debugLabel.setText("init");
        //add(debugLabel, gc);
        gc.gridy = 3;
        infoLabel.setText("- vhnvn & Awak 2013 -");
        add(infoLabel, gc);
        // added a cheat button
        //gc.gridx = 1;
        //hintButton.
        //hintButton.setSize(5,5);
        //hintButton.setText("Hint");
        //add(hintButton, gc);
        gameTimer = new Timer(1000, new ClockListener());
        gameTimer.start();
       
        enableEvents(48L);
        addKeyListener(boardComp);
    }

    public void onNewGame()
    {
        if(Main.posmode == 0)
        {
            titleLabel.setText("You vs ".concat(Main.levelName[Main.level]));
        } else
        {
            posCollection _tmp = Main.pc;
            titleLabel.setText(posCollection.itemName[Main.posi].concat(" ".concat(String.valueOf(Main.posj + 1))));
        }
        resetClock();
        boardComp.onNewGame();
    }

    public void onPosMode(String fn, String name, int posi, int posj)
    {
        posCollection _tmp = Main.pc;
        titleLabel.setText(posCollection.itemName[Main.posi].concat(" ".concat(String.valueOf(Main.posj + 1))));
        resetClock();
        boardComp.onPosMode(fn, name, posi, posj);
    }

    class ClockListener implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
           //Calendar now = Calendar.getInstance();
           //int h = now.get(Calendar.HOUR_OF_DAY);
           //int m = now.get(Calendar.MINUTE);
           //int s = now.get(Calendar.SECOND);
           //char colon = ':';
           String clk0;
           String clk1;
           String clk2;

           //clockLabel.setText("" + h + ":" + m + ":" + s);
           gameTime = gameTime + 1;
           //int s = gameTime % 60;
           int m = gameTime / 60;
           int h = m /60;
           
           // to prevent drain the battery power, display every minute instead of every second
           if (gameTime % 60 == 0) 
           {
                // Display second only at the end of the game
                //clk1 = " ".concat(String.valueOf(h).concat(":".concat(String.valueOf(m))));
                //clk2 = clk1.concat(":".concat(String.valueOf(s)));  // display every second in hh:mm:ss
               
                // display every minute in hh hr mm min
                clk0 = " ".concat(String.valueOf(h).concat(" ".concat(Main.rLang.getString("hourUnit"))));
                clk1 = clk0.concat(" ".concat(String.valueOf(m)));
                clk2 = clk1.concat(" ".concat(Main.rLang.getString("minUnit"))); 
                clockLabel.setText(Main.rLang.getString("gameTime").concat(clk2));
                clockLabel.invalidate();
                clockLabel.repaint();
            }
         }
    }
    
    public void resetClock()
    {
        String clk1;
        
        gameTime = 0;
        clk1 = Main.rLang.getString("gameTime").concat(" 00 ".concat(Main.rLang.getString("hourUnit")));
        clockLabel.setText(clk1.concat(" 00 ".concat(Main.rLang.getString("minUnit"))));  
        clockLabel.invalidate();
        clockLabel.repaint();
    }
    

    JLabel titleLabel;
    JLabel infoLabel;
    JLabel clockLabel;
    JButton hintButton;
    JLabel debugLabel;
    Timer gameTimer;
    int gameTime = 0;
    
    BoardComponent boardComp;
}


