package reaction;

import graphics.G;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;
import music.I;
import music.UC;

public class Shape {
  public static Shape.Database DB = Shape.Database.load();
  public static Shape DOT = DB.get("DOT");
  // List up-to-date with DB
  public static Collection<Shape> LIST = DB.values();
  public Prototype.List prototypeList = new Prototype.List();
  public String name;

  public Shape(String name){
    this.name = name;
  }

  public static Shape recognize(Ink ink){ // can return null
    if(ink.vs.size.x < UC.dotThreshold && ink.vs.size.y < UC.dotThreshold){return DOT;}
    Shape bestMatch = null;
    int bestSoFar = UC.noMatchDist;
    for(Shape s : LIST){
      int d = s.prototypeList.bestDist(ink.norm);
      if(d < bestSoFar){
        bestMatch = s;
        bestSoFar = d;
      }
    }
    return bestMatch;
  }

  //----------------Database-----------------
  public static class Database extends TreeMap<String, Shape> {
    public static Database load(){
      Database res = new Database();
      res.put("DOT", new Shape("DOT"));
      return res;
    }

    // stub
    public static void save(){}
    public boolean isKnown(String name){return containsKey(name);}
    public boolean isUnknown(String name){return !containsKey(name);}
    public boolean isLegal(String name){return !name.equals("") && !name.equals("DOT");}
  }

  //----------------Prototype----------------
  public static class Prototype extends Ink.Norm{
    public int nBland;

    public void blend(Ink.Norm norm){
      blend(norm, nBland++);
    }

    //--------------------List-------------
    public static class List extends ArrayList<Prototype> implements I.Show{
      // set as side effect of bestDist
      public static Prototype bestMatch;

      public int bestDist(Ink.Norm norm){
        bestMatch = null;
        int bestSoFar = UC.noMatchDist;
        for(Prototype p : this){
          int d = p.dist(norm);
          if(d < bestSoFar){
            bestMatch = p;
            bestSoFar = d;
          }
        }
        return bestSoFar;
      }

      private static int m = 10, w = 60;
      private static G.VS showBox = new G.VS(m, m, w, w);
      @Override
      public void show(Graphics g) {
        g.setColor(Color.ORANGE);
        for(int i = 0; i < size(); i++){
          Prototype p = get(i);
          int x = m+i*(m+w);
          showBox.loc.set(x, m);
          p.drawAt(g, showBox);
          g.drawString(""+p.nBland, x, 20);
        }
      }
    }
  }
}
