package sandbox;

import graphics.G;
import graphics.WinApp;
import java.awt.event.MouseEvent;
import music.UC;
import reaction.Ink;

import java.awt.*;
import reaction.Ink.Buffer;

public class PaintInk extends WinApp {

  public static Ink.List inkList = new Ink.List();

  public PaintInk(){
    super("paint ink", UC.screenWidth, UC.screenHeight);
//    inkList.add(new Ink());
  }

  public void paintComponent(Graphics g){
    G.fillBackground(g, Color.WHITE);
    g.setColor(Color.BLACK);
    inkList.show(g);
    Ink.BUFFER.show(g);
    g.drawString("points: "+Ink.BUFFER.n, 600, 30);
    if(inkList.size() > 1){
     int last = inkList.size()-1;
     int dist = inkList.get(last).norm.dist(inkList.get(last-1).norm);
     g.setColor(dist > UC.noMatchDist? Color.RED : Color.BLACK);
     g.drawString("dist: " + dist, 600, 60);
    }
//    g.fillRect(100,100,100,100);
  }

  public void mousePressed(MouseEvent me){
    Ink.BUFFER.dn(me.getX(), me.getY());
    repaint();
  }

  public void mouseDragged(MouseEvent me){
    Ink.BUFFER.drag(me.getX(), me.getY());
    repaint();
  }

  public void mouseReleased(MouseEvent me){
    Ink.BUFFER.up(me.getX(), me.getY());
    inkList.add(new Ink());
    repaint();
  }


  public static void main(String[] args){
    PANEL = new PaintInk();
    WinApp.launch();
  }
}
