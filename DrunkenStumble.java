/*
  Landon Colburn
  ©2019
*/

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrunkenStumble {

  public static Cell[][] cellList;
  public static Dimension size = new Dimension(60,60);
  public static ArrayList<Walker> walkerList = new ArrayList<Walker>();

  public static int cellGridHeight;
  public static int cellGridWidth;
  public static int cellSize = 1;

  public static int counter = 0;

  public DrunkenStumble(){
    initMap();
    spawnWalkers();
    walkWalkers();
    generateWalls();
    removeSingles();
    // addSpawn();
    returnMatrix();
  }

  public void initMap(){
    int count = 0;
    cellGridHeight = (int)size.getHeight()/cellSize;
    cellGridWidth = (int)size.getWidth()/cellSize;
    cellList = new Cell[cellGridHeight][cellGridWidth];
    for(int i = 0; i<cellGridHeight; i++){
      for(int j = 0; j<cellGridWidth; j++){
        cellList[i][j] = Cell.EMPTY;
        count++;
      }
    }
  }

  public void spawnWalkers(){
    int tx = (int)(Math.random()*20)+20;
    int ty = (int)(Math.random()*20)+20;
    for(int i = 0; i<(int)(Math.random()*4)+1; i++){
      Walker w = new Walker(tx,ty,(int)(Math.random()*4));
      walkerList.add(w);
    }
  }

  public void walkWalkers(){
    while(true){
      for(int j = 0; j<walkerList.size(); j++){
        Walker w = walkerList.get(j);
        w.walk();
        if(w.getX() >= 0 && w.getX() < 60 && w.getY() >= 0 && w.getY() < 60){
          cellList[w.getX()][w.getY()]=Cell.FLOOR;
          counter++;
        }
      }
      if(counter > 400){
        break;
      }
    }
  }

  public void removeSingles(){
    for(int i = 0; i<cellList.length; i++){
      for(int j = 0; j<cellList[i].length; j++){
        if(cellList[i][j].equals(Cell.WALL)){
          int count = 0;
          for(int k = -1; k <= 1; k++){
            for(int l = -1; l <= 1; l++){
              if(cellList[i+k][j+l].equals(Cell.FLOOR)){
                count++;
              }
            }
          }
          if(count==8){
            cellList[i][j] = Cell.FLOOR;
          }
        }
      }
    }
  }

  public void generateWalls(){
    for(int i = 0; i<cellList.length; i++){
      for(int j = 0; j<cellList[i].length; j++){
        if(i==0 || j==0 || i==cellList.length-1 || j==cellList[i].length-1){

        } else {
          if(cellList[i][j].equals(Cell.FLOOR)){
            for(int k = -1; k <= 1; k++){
              for(int l = -1; l <= 1; l++){
                if(cellList[i+k][j+l].equals(Cell.EMPTY)){
                  cellList[i+k][j+l] = Cell.WALL;
                }
              }
            }
          }
        }
      }
    }
  }

  public void printMatrix(){
    for(int i = 0; i<cellList.length; i++){
      for(int j = 0; j<cellList[i].length; j++){
        switch(cellList[i][j]){
          case EMPTY:
            System.out.print(" ");
            break;
          case WALL:
            System.out.print("=");
            break;
          case FLOOR:
            System.out.print(".");
            break;
          default:
            System.out.print(" ");
            break;
        }
      }
      System.out.println("");
    }
  }

  public void addSpawn(){
    boolean spawned = false;
    for(int x = 0; x<size.getWidth(); x++){
      for(int y = 0; y<size.getHeight(); y++){
        if(cellList[x][y].equals(Cell.FLOOR) && spawned == false){
          spawned = true;
          cellList[x][y]=Cell.PLAYER;
          return;
        }
      }
    }
  }

  public Cell[][] returnMatrix(){
    return cellList;
  }

}
