import java.awt.*;

public class Walker{
  private int x;
  private int y;
  private int d;
  private boolean active;

  public static final double spawnThreshold = 0.15;
  public static final double directionThreshold = 0.2;
  public static final double deathThreshold = 0.1;

  public Walker(int x, int y, int d){
    this.x = x;
    this.y = y;
    this.d = d;
    active = true;
    System.out.println("x: " + x + " y: " + y + " d: " + d);
  }

  public void walk(){
    int dx = 0;
    int dy = 0;
    switch(d){
      case 0: //NORTH
        dy+=-1;
        break;
      case 1: //EAST
        dx+=1;
        break;
      case 2: //SOUTH
        dy+=1;
        break;
      case 3: //WEST
        dx+=-1;
        break;
    }
    if(!active){
      System.out.println("dead");
    } else if(x+dx>DrunkenStumble.size.getWidth()-3||x+dx<2){
      kill();
    } else if(y+dy>DrunkenStumble.size.getHeight()-3||y+dy<2){
      kill();
    } else {
      x += dx;
      y += dy;
      magic();
    }
  }

  public int getX(){
    return x;
  }

  public int getY(){
    return y;
  }

  public void kill(){
    active = false;
    DrunkenStumble.walkerList.remove(this);
  }

  public void magic(){
    if(Math.random()<spawnThreshold){
      Walker w = new Walker(x,y,(int)(Math.random()*4));
      DrunkenStumble.walkerList.add(w);
    }
    if(Math.random()<directionThreshold){
      d = (int)(Math.random()*4);
    }
    if(Math.random()<deathThreshold){
      if(DrunkenStumble.walkerList.size() > 1){
        kill();
      }
    }
  }
}
