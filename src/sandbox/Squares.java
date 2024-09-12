package sandbox;
import graphics.WinApp;
import graphics.G;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class Squares extends WinApp{
  public static G.VS theVS = new G.VS(100,100,200,300);
  public static Color color = G.rndColor();
  public static Square.List squares = new Square.List();
  public static Square lastSquare;

  public Squares(){super("Squares",1000,800);}

  @Override
  public void paintComponent(Graphics g){
    G.fillBack(g);
    squares.draw(g);
  }

  @Override
  public void mousePressed(MouseEvent me){
    lastSquare = new Square(me.getX(), me.getY());
    squares.add(lastSquare);
    repaint();
  }

  @Override
  public void mouseDragged(MouseEvent me){
    lastSquare.resize(me.getX(), me.getY());
    repaint();
  }

  public static void main(String[] args){PANEL=new Squares();WinApp.launch();}

  //-----------------Square------------------------------
  public static class Square extends G.VS{
    public Color c = G.rndColor();
    public Square(int x, int y){super(x,y,100,100);}
    public void resize(int x, int y){if(x>loc.x && y>loc.y){size.set(x - loc.x, y - loc.y);}}

    //------------------List----------------------------
    public static class List extends ArrayList<Square> {
      public void draw(Graphics g){for(Square s : this){s.fill(g, s.c);}}
    }
  }
}