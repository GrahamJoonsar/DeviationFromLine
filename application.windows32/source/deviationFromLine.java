import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class deviationFromLine extends PApplet {

int pointAX = 50;
int pointAY = 400;
int pointBX = 250;
int pointBY = 250;
int pointCX = 450;
int pointCY = 100;
boolean AMoving = false;
boolean BMoving = false;
boolean CMoving = false;
String deviation = "0.00";

//Math.sqrt(Math.pow(mouseX - pointBX, 2) + Math.pow(mouseY - pointBY, 2)) <= 1 

public void setup(){
  
}

public void mouseReleased(){
  AMoving = false;
  BMoving = false;
  CMoving = false;
}

public void movePoints(){
  if (Math.sqrt(Math.pow(mouseX - pointBX, 2) + Math.pow(mouseY - pointBY, 2)) <= 20 && mousePressed && AMoving == false && CMoving == false){
    BMoving = true;
    pointBX = mouseX;
    pointBY = mouseY;
  }
  if (Math.sqrt(Math.pow(mouseX - pointAX, 2) + Math.pow(mouseY - pointAY, 2)) <= 20 && mousePressed && BMoving == false && CMoving == false){
    AMoving = true;
    pointAX = mouseX;
    pointAY = mouseY;
  }
  if (Math.sqrt(Math.pow(mouseX - pointCX, 2) + Math.pow(mouseY - pointCY, 2)) <= 20 && mousePressed && AMoving == false && BMoving == false){
    CMoving = true;
    pointCX = mouseX;
    pointCY = mouseY;
  }
}

public void draw(){
  background(255);
  
  movePoints();
  
  deviation = String.valueOf(Math.abs(Math.sqrt(Math.pow(pointAX - pointCX, 2) + Math.pow(pointAY - pointCY, 2)) - (Math.sqrt(Math.pow(pointAX - pointBX, 2) + Math.pow(pointAY - pointBY, 2)) + Math.sqrt(Math.pow(pointBX - pointCX, 2) + Math.pow(pointBY - pointCY, 2)))));
  fill(0);
  textSize(20);
  text("Deviation is: " + deviation, 10, 20);
  
  strokeWeight(5);
  stroke(0);
  line(pointAX, pointAY, pointBX, pointBY);
  line(pointBX, pointBY, pointCX, pointCY);
  
  stroke(60);
  strokeWeight(2);
  line(pointAX, pointAY, pointCX, pointCY);
  line((pointAX + pointCX)/2, (pointAY + pointCY)/2, pointBX, pointBY);
  
  strokeWeight(7);
  stroke(255, 0, 0);
  point(pointAX, pointAY);
  point(pointBX, pointBY);
  point(pointCX, pointCY);
}
  public void settings() {  size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "deviationFromLine" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
