package paardenrace;
// te doen
// uitzoeken verschillende treads
// als dit niet lukt stukje weghalen

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class paardenrace extends JFrame implements ActionListener {
   private JPanel panel;
   private Boolean winner = Boolean.FALSE;
   private Paard paard1= new Paard("paard 1",Color.blue);
   private Paard paard2 = new Paard("paard 2",Color.red);
   private int finish=350;

   public static void main(String[] args) {
      paardenrace frame =new paardenrace();
      frame.setSize(500,500);
      frame.createGUI();
      frame.setTitle("paardenrace");
      frame.setVisible(true); }

   /**
    * creates the GUI
    */
   private void createGUI(){
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      Container window =getContentPane();
      window.setLayout(new FlowLayout());

      //creation panel
      panel =new JPanel();
      panel.setPreferredSize(new Dimension(450,400));
      window.add(panel);

      // creation button
      JButton button =new JButton("start the race");
      button.setPreferredSize(new Dimension(450,50));
      window.add(button);
      button.addActionListener(this);
   }

   /**
    * het afspelen van de paardenrace als de knop wordt ingedrukt
    * @param e knop wordt in gedrukt
    */
   public void actionPerformed(ActionEvent e) {
      Graphics paper = panel.getGraphics();

      // dit is zodat je hem opnieuw kan opstarten als die bezig is (als ik multithread kan laten werken geen idee wat er gebeurt)
      paper.clearRect(0,0,450,400);
      paper.setColor(Color.black);

      // tekent de finish lijn en schrijft de tekst erbij
      paper.drawLine(390,0,390,450);
      paper.drawString("Paard 1", 50,30);
      paper.drawString("Paard 2",50,150);

      while (winner==Boolean.FALSE) {
      // beeld paard 1 af op het scherm en laat hem lopen
      paper.setColor(paard1.getKleur());
      paper.fillRect(paard1.getAfstand(),paard1.getPaardNummer()+60,50,50);
      paard1.loop();

      // beeld paard 2 af op het scherm en laat hem lopen
      paper.setColor(paard2.getKleur());
      paper.fillRect(paard2.getAfstand(),paard2.getPaardNummer()+180,50,50);
      paard2.loop();

      // pauzeert alles
      pauzeer(20);

      //checkt of er een winner is
      checkwinner();}

   }

   /** checkt of er een winner is en sluit het spel af */
   public void checkwinner() {
      if (paard1.getAfstand() >= finish) {
         JOptionPane.showMessageDialog(null,paard1.getNaam()+" heeft gewonnen");
         winner = Boolean.TRUE;  // stopt de while loop
         dispose();     // sluit het scherm af
      }
      if (paard2.getAfstand() >= finish){
         JOptionPane.showMessageDialog(null,paard2.getNaam()+" heeft gewonnen");
         winner = Boolean.TRUE;
         dispose();
      }
   }

   /** Pauzeer gedurende x millisecondes (gekopieerd van de onderwijsonline)*/
   public void pauzeer(int msec) {
      try {
         Thread.sleep(msec);
      } catch (InterruptedException e) {
         System.out.println("Pauze interruptie");
      }
   }
}


