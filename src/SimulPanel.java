import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;

public class SimulPanel extends JPanel implements ActionListener {
    boolean imagePage = false;
    JButton b1, b2;
    JPanel select1, select2;
    JPanel open=new OpenPanel();
    JPanel open2=new OpenPanel2();

    public SimulPanel() {


        setLayout(new BorderLayout());

        select1 =new JPanel();
        //JButton b1=new JButton(new ImageIcon("2dlogo.png")); 에러
        b1=new JButton("2D 게임 시뮬레이션");
        select1.add(b1);
        b1.addActionListener(this);

        //select2 =new JPanel();
        //JButton b2=new JButton(new ImageIcon("3dlogo.png"));
        b2=new JButton("3D 게임 시뮬레이션");
        select1.add(b2);
        b2.addActionListener(this);

        add("North",select1);

        //add(select1);
        //add(open);
        //open.setVisible(false);

        //add(select2);
        //add(open2);
        //open2.setVisible(false);


    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b1) {
            if(imagePage){
                b1.setText("2D 게임 시뮬레이션");
                open.setVisible(false);
                imagePage = false;
                b2.setVisible(true);

            }
            else{
                open.setVisible(true);
                b1.setText("Back Button");
                b2.setVisible(false);
                add("Center",open);
                imagePage = true;
            }
        }
        if(e.getSource()==b2) {
            if(imagePage){
                b2.setText("3D 게임 시뮬레이션");
                open2.setVisible(false);
                imagePage = false;
                b1.setVisible(true);
            }
            else{
                open2.setVisible(true);
                b2.setText("Back Button");
                imagePage = true;
                add("Center",open2);
                b1.setVisible(false);
            }
        }

    }

}