import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Player extends GameObject {

  Handler handler;

  public Player(int x, int y, Handler handler){
    super(x, y, ID.Player);
    this.handler = handler;
  }

  public void tick(){
    x += velX;
    y += velY;

    collision();

    if(handler.getUp()) velY = -5;
    else if(!handler.getDown()) velY = 0;

    if(handler.getDown()) velY = 5;
    else if(!handler.getUp()) velY = 0;

    if(handler.getRight()) velX = 5;
    else if(!handler.getLeft()) velX = 0;

    if(handler.getLeft()) velX = -5;
    else if(!handler.getRight()) velX = 0;

  }

  public boolean intersection(int x, int y, Rectangle myRect, Rectangle otherRect) {
    if(new Rectangle(x, y, myRect.width, myRect.height).intersects(otherRect)){
     return true;
    }
    return false;
   }

   public void collision(){
    for (int i = 0; i < handler.object.size(); i++) {
     GameObject tempObject = handler.object.get(i);
     if(tempObject.getID() == ID.Block){
      if(intersection((int)(x + velX), y, getBounds(), tempObject.getBounds())){
       x += velX * -1;
      }
      if(intersection(x,(int)(y + velY), getBounds(), tempObject.getBounds())){
       y += velY * -1;
      }
     }
    }

   }

  public void render(Graphics g){
    g.setColor(Color.PINK);
    g.fillRect(x, y, 16, 16);
  }

  public Rectangle getBounds(){
    return new Rectangle(x,y,16,16);
  }

}
