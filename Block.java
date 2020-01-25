import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Block extends GameObject{

  public Block(int x, int y){
    super(x, y, ID.Block);
  }

  public void tick(){

  }

  public void render(Graphics g){
    g.setColor(Color.BLACK);
    g.fillRect(x, y, 32, 32);
  }

  public Rectangle getBounds(){
    return new Rectangle(x, y, 32, 32);
  }

}
