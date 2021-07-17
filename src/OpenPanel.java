import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;

public class OpenPanel extends JPanel implements ActionListener{
    JPanel tPanel, bPanel, iPanel;
    JButton button1, button2;
    JLabel title, lbl, lbl2 ;

    public OpenPanel() {

        setLayout(new BorderLayout()); //보더 레이아웃 사용

        //타이틀 저장 패널
        tPanel=new JPanel();
        title = new JLabel("2D 게임 치트 시뮬레이터");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("굴림",Font.BOLD,20));

        lbl = new JLabel(": 2D 게임에서 주로 사용되는 핵을 구현하였습니다.");
        lbl.setHorizontalAlignment(JLabel.CENTER);

        tPanel.add(title);
        tPanel.add(lbl);

        add(tPanel, BorderLayout.NORTH);

        //미니 게임 소개 패널
        iPanel=new JPanel();
        iPanel.setLayout(new GridLayout(3,1));

        //hp 핵
        ImageIcon icon = new ImageIcon("src/Images/Simulation/hphack.png");
        JLabel image1 = new JLabel(icon);
        JLabel content1 = new JLabel("<html>플레이어 체력값인 HP가 비정상적으로 <br>크게 늘어나게 하는 치트를 적용한 결과를 보여 줍니다.</html>");
        iPanel.add(image1);
        iPanel.add(content1);

        //스피드 핵
        ImageIcon icon2 = new ImageIcon("src/Images/Simulation/speedhack.png");
        JLabel image2 = new JLabel(icon2);
        JLabel content2 = new JLabel("<html>플레이어의 스피드와 점프값이 비정상적으로 <br>크게 늘어나게 하는 치트를 적용한 결과를 보여 줍니다.</html>");
        iPanel.add(image2);
        iPanel.add(content2);

        //스킬 쿨 핵
        ImageIcon icon3 = new ImageIcon("src/Images/Simulation/skillhack.png");
        JLabel image3 = new JLabel(icon3);
        JLabel content3 = new JLabel("<html>플레이어의 스킬  시전 시간의 대기 시간이 <br>크게 줄어들게 하는 치트를 적용한 결과를 보여 줍니다.</html>");
        iPanel.add(image3);
        iPanel.add(content3);

        add(iPanel,BorderLayout.CENTER);

        //2D 게임 실행 버튼
        bPanel=new JPanel(); //버튼 저장 패널
        bPanel.setLayout(new FlowLayout());

        button1 =new JButton("2D 게임 실행 버튼");
        button1.addActionListener(this);
        lbl2 = new JLabel("시뮬레이션을 실행하고 싶다면 버튼을 눌러 주세요");

        bPanel.add(lbl2);
        bPanel.add(button1);

        add(bPanel,BorderLayout.SOUTH);
        setVisible(true);
    }

    //button event_파일 실행
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getSource()==button1) {
            Runtime rt=Runtime.getRuntime();
            String file = "src/Simulations/WCE_2D_Game"; //빌드 실행 경로 지정 필요 (build\\WCE_2D_Game)
            Process pro;
            try {
                pro=rt.exec(file);
                pro.waitFor();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            System.out.println("파일 실행 완료");
        }
    }
}