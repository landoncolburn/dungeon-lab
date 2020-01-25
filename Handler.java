import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

  LinkedList<GameObject> object = new LinkedList<GameObject>();

  private boolean up = false;
  private boolean right = false;
  private boolean down = false;
  private boolean left = false;

  public void tick(){
    for(int i = 0; i < object.size(); i++){
      GameObject tempObject = object.get(i);
      tempObject.tick();
    }
  }

  public void render(Graphics g){
    for(int i = 0; i < object.size(); i++){
      GameObject tempObject = object.get(i);
      tempObject.render(g);
    }
  }

  public void addObject(GameObject tempObject){
    object.add(tempObject);
  }

  public void removeObject(GameObject tempObject){
    object.remove(tempObject);
  }

  public boolean getUp(){
    return up;
  }

  public void setUp(boolean b){
    this.up = b;
  }

  public boolean getRight(){
    return right;
  }

  public void setRight(boolean b){
    this.right = b;
  }

  public boolean getDown(){
    return down;
  }

  public void setDown(boolean b){
    this.down = b;
  }

  public boolean getLeft(){
    return left;
  }

  public void setLeft(boolean b){
    this.left = b;
  }

}
