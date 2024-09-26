package sandbox;

import graphics.G;
import graphics.WinApp;
import music.UC;

import java.awt.*;

public class PaintInk extends WinApp {



  public PaintInk(){
    super("paint ink", UC.screenWidth, UC.screenHeight);
  }

  public void paintComponent(Graphics g){
    G.fillBackground(g, Color.WHITE);
    g.setColor(Color.RED);
    g.fillRect(100,100,100,100);
  }

  public static void main(String[] args){
    PANEL = new PaintInk();
    WinApp.launch();
  }
}
