import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject {

  private Handler handler;
  Random r = new Random();
  int choose = 0;
  int hp = 100;

  public Enemy(int x, int y, Handler handler){
    super(x,y,ID.Enemy);
    this.handler = handler;
  }

  public void tick(){
    x += velX;
    y += velY;

    choose = r.nextInt(10);

    for(int i = 0; i < handler.object.size(); i++){
      GameObject tempObject = handler.object.get(i);

      if(tempObject.getID() == ID.Block){
        if(getBoundsBig().intersects(tempObject.getBounds())){
          x += -velX*5;
          y += -velY*5;
          velX *= -1;
          velY *= -1;
        } else if(choose==0){
          velX = (r.nextInt(4 - -4)+ -4);
          velY = (r.nextInt(4 - -4)+ -4);
        }
      }
    }
  }

  public void render(Graphics g){
    g.setColor(Color.PINK);
    g.fillRect(x, y, 32, 32);
  }

  public Rectangle getBounds(){
    return new Rectangle(x, y, 32, 32);
  }

  public Rectangle getBoundsBig(){
    return new Rectangle(x, y, 32, 32);
  }
}
