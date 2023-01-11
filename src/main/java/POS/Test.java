package POS;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.media.*;

public class Test extends JFrame {
  public static void main(String args[]) {
    Player player;

    File file = new File("yourFile");

    player = Manager.createPlayer(file.toURI().toURL());
//    player.addControllerListener(new EventHandler());
    player.start(); // start player
    
    player.close();

    Component visual = player.getVisualComponent();
    Component control = player.getControlPanelComponent();

  }
}

   
  