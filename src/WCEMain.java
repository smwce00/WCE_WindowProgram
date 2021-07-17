import javax.swing.*;
import java.awt.*;

public class WCEMain extends JFrame{
    JTabbedPane t = new JTabbedPane();          //JTabbedPane 생성
    JPanel LogoPanel = new JPanel();            //맨위에 붙을 로고 패널
    JPanel HomePanel = new HomePanel();         //홈 패널
    JPanel CheatPanel = new CheatPanel();       //치트 패널
    JPanel SecurityPanel = new SecurityPanel(); //보안 패널
    JPanel SimulPanel = new SimulPanel();       //시뮬레이션 패널

    public WCEMain() {
        super("Main Panel"); //프레임 타이틀 제목 지정
        setLayout(new BorderLayout());

        //logoPanel at North
        ImageIcon icon = new ImageIcon(getClass().getResource("logo_2.png")); //로고 이미지
        JLabel lbl = new JLabel(icon);
        LogoPanel.add(lbl);
        add("North",LogoPanel);

        //TabbedPane at Center
        t.add("Home", HomePanel);
        t.add("What is Cheat/Hack?", CheatPanel); //설명 패널을 생성 후 JTextArea 대신 넣으면 됨
        t.add("Security Solutions", SecurityPanel); //마찬가지
        t.add("Simulations",SimulPanel);
        add("Center", t);

        setSize(700, 900);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);}

    public static void main(String[] args) {
        new WCEMain();
    }
}