import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

  private static final long serialVersionUID = 42l;

  private boolean isRunning = false;
  private Thread thread;
  private Handler handler;
  private Camera camera;
  private DrunkenStumble ds = new DrunkenStumble();

  private BufferedImage level = null;

  public Game(){
    new Window(1000,600,"Rougelike",this);
    start();

    handler = new Handler();
    camera = new Camera(0,0);
    this.addKeyListener(new KeyInput(handler));

    loadLevel(ds.returnMatrix());
  }

  public void start(){
    isRunning = true;
    thread = new Thread(this);
    thread.start();
  }

  public void stop(){
    isRunning = false;
    try{
      thread.join();
    } catch(InterruptedException e){
      e.printStackTrace();
    }
  }

  public void run() {
    this.requestFocus();
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    while (isRunning) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while (delta >= 1) {
        tick();
        delta--;
      }
      if (isRunning)
        render();
      frames++;
      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        System.out.println("FPS: " + frames);
        frames = 0;
      }
      long endTime = System.nanoTime();
      long elapsedTime = endTime - now;
      try {
        Thread.sleep((Math.abs((long) 16666666 - elapsedTime)) / 1000000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    stop();
  }

  public void tick(){

    for(int i = 0; i < handler.object.size(); i++){
      if(handler.object.get(i).getID() == ID.Player){
        camera.tick(handler.object.get(i));
      }
    }

    handler.tick();
  }

  public void render(){
    BufferStrategy bs = this.getBufferStrategy();
    if(bs==null){
      this.createBufferStrategy(3);
      return;
    }
    Graphics g = bs.getDrawGraphics();
    Graphics2D g2d = (Graphics2D) g;
    //////////////////////////////////

    g.setColor(Color.red);
    g.fillRect(0, 0, 1000, 600);

    g2d.translate(-camera.getX(), -camera.getY());

    handler.render(g);

    g2d.translate(camera.getX(), camera.getY());

    //////////////////////////////////
    g.dispose();
    bs.show();
  }

  private void loadLevel(Cell[][] cellList){
    boolean spawned = false;
    int w = cellList.length;
    int h = cellList[0].length;
    for(int xx = 0; xx<w*2; xx++){
      for(int yy = 0; yy<h*2; yy++){
        Cell cell = cellList[xx/2][yy/2];
        switch(cell){
          case WALL:
            handler.addObject(new Block(xx*32, yy*32));
            break;
          case FLOOR:
            if(!spawned){
              handler.addObject(new Player(xx*32, yy*32, handler));
              spawned = true;
            }
            break;
          default:
            break;
        }
      }
    }
  }

  public static void main(String[] args) {
    new Game();
  }

}
