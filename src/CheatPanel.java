import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class CheatPanel extends JPanel implements ActionListener{
    // 치트 패널 메인 페이지
    JLabel cheatTitleL;
    JTextArea cheatDescTA;
    JPanel methodButtonP, pageFlowButtonP, pageFrameP, pageFrameCenterP;
    JButton codeModifyB, memoryHackB, dllInjectB;

    // 보안 기법 페이지
    JLabel[] codeModifyTitleL, memoryHackTitleL, dllInjectTitleL;
    JTextArea[] codeModifyDescTA, memoryHackDescTA, dllInjectDescTA;
    ImageIcon[] codeModifyII, memoryHackII, dllInjectII;
    JLabel[] codeModifyImageL, memoryHackImageL, dllInjectImageL;

    JButton cheatMainB, cheatPrevB, cheatNextB;

    Font titleFont = new Font("맑은 고딕", Font.BOLD, 35);
    Font descFont = new Font("맑은 고딕", Font.PLAIN, 20);

    //page 제목들
    String[] codeModifyTitleString = {"1. 디컴파일러란?", "2. dnSpy를 사용한 유니티 게임 해킹", "3. 보안 방법" };
    String[] memoryHackTitleString = { "1. 메모리 해킹이란?", "2. 치팅 툴 소개 ", "3. cheat engine으로 게임 해킹 방법", "4. 보안 방법" };
    String[] dllInjectTitleString = { "1. DLL injection 이란?", "2. 인젝션 방법", "3. 보안 방법" };

    //page 내용들들
    String[] codeModifyDescString = {
            "컴파일러가 소스코드를 바이너리 결과물로 만드는 것이라면, 디컴파일러는 바이너리 결과물로부터 소스코드를 복원하는 것을 말합니다. 즉 실행파일로부터 개발 소스코드를 추출해 낼 수 있습니다. 비록 개발자가 작성한 원본과 완벽하게 같은 것은 아니지만 역컴파일된 소스코드로 다시 컴파일을 했을 때, 원본과 동일한 실행 결과가 나올 정도로 정확합니다. 따라서 EXE/DLL 상태의 어셈블리 파일을 분석하는데 매우 큰 도움을 받을 수 있습니다.\n" +
                    "\n" +
                    "대표적인 디컴파일러로는 dnspy가 있습니다. dnSpy는 디버거 및 .NET 어셈블리 편집기입니다. 사용 가능한 소스 코드가 없더라도 dnspy를 사용하여 어셈블리를 편집하고 디버그 할 수 있습니다. 주요 기능으로 .NET 및 Unity 어셈블리를 디버그와 편집을 할 수 있기 때문에 유니티 게임 해킹 할 때 많이 쓰이는 툴이기도 합니다.\n",
            "MONO형식으로 빌드된 경우, (Unity Build Folder)\\(unity project file name)_Data\\Managed에 위치한 Assembly-CSharp.dll이라는 파일이 생기는데, 여기에는 게임 내 모든 스크립트를 담고 있습니다.  코드 난독화 및 보안 솔루션이 없이 MONO형식으로 빌드된 게임에 dnSpy를 사용하면, 마치 오픈된 소스코드처럼 게임 내 모든 스크립트를 담고 있는 Assembly-CSharp.dll 파일을 통해 간단히 디컴파일 할 수 있습니다. \n" +
                    "\n사용법은 정말 간단합니다. dnSpy에서 해킹하고 싶은 게임의 Assembly-CSharp.dll 파일을 열어서 핵심 함수의 코드를 수정하여 저장한 후 재실행시키면 됩니다. \n",
            "게임 코드 변조를 막을 수 있는 가장 효과적인 방법은 코드 난독화입니다. 난독화를 하게 되면 dnSpy로 Assembly-CSharp.dll을 열었을 때 아래 사진과 같이 코드 분석을 어렵게 할 수 있기 때문입니다. 또한 mono빌드가 아닌 il2cpp로 빌드하는 것도 좋은 방법입니다. il2cpp로 빌드하게 되면 Assembly-CSharp.dll파일이 생성되지 않기 때문에, 해커들이 il2cpp로 빌드된 게임을 해킹하려면 메모리 덤프라는 과정을 거쳐야 합니다. 마지막으로 fake 함수, 즉, 낚시 함수를 게임 코드에 추가하는 것도 해커들이 게임 코드 분석을 어렵게 하는 방법입니다. 하지만 이 방법은 해킹하는 시간을 오래 걸리게 만들뿐, 결국 해킹은 가능하게 되는 방법이기 때문에 다른 보안 솔루션과 함께 수행되어야합니다. \n"
    };
    String[] memoryHackDescString = {
            "메모리해킹이란 램(RAM)이라 불리는 주기억장치에 저장되는 데이터를 절취하거나 이를 조작하는 해킹기법을 의미합니다. 보통 메모리 치팅은 메모리 스캔을 통해 수행됩니다. 메모리 스캔이란, 함수는 모르지만 값은 알고 있는 경우, 메모리를 샅샅이 수색하여 값을 가지고 있는 메모리 번지수를 찾은 뒤, 원하는 값으로 덮어 씌우는 방법입니다. 동일한 값을 여러 군데에서 가지고 있을 수 있기 때문에 값을 여러번 바꾸면서 정답을 좁혀나가는 방식으로 수행됩니다.",
            "메모리 해킹을 할 때 사용되는 치팅 툴로는 Cheat Engine, ArtMoney, Game Guardian, Squalr, CoSMOS등이 있으며, 대표적으로 Cheat Engine이 가장 많이 사용됩니다. 이런 치팅 툴들은 기본적으로 포인터 스캐너 기능을 가지고 있습니다. 포인터 스캐너는 메모리 경로를 찾도록 도와주는 기능을 제공하며, 이 기능을 가지고 해커들은 변경하고 싶은 값들을 찾아 해킹할 수 있습니다. 예를 들어, 게임 머니 값을 100원을 가지고 있었는데 포인터 스캔을 통해 값을 찾아내서 10000원으로 변경한다던지, 목숨이 3인 게임의 목숨을 100으로 늘리는 등의 해킹을 구현할 수 있습니다.",
            "https://joogle2019.tistory.com/89 \n" +
                    "(gif나 동영상 링크)\n",
            "메모리 해킹을 막으려면 암호화 과정은 필수입니다. 변수 암호화를 하게 되면 치트엔진과 같은 치트 툴로 스캔을 했을 때, 실제 값이 검색되지 않게 할 수 있습니다. 또한, 애초에 게임 실행 중, 치팅 툴을 사용하지 못하도록 치팅 툴을 감지해내는 기능을 추가하는 것도 도움이 될 수 있습니다. 해당 방법에 대해 WCE의 “Security Solutions”탭에 상세한 설명이 있으니 참고하여 주세요."
    };
    String[] dllInjectDescString = {
            "실행 중인 다른 프로세스에 특정 DLL 파일을 강제로 삽입하는 기법, 즉, 다른 프로세서의 메모리 공간으로 DLL을 강제 삽입하여 프로세스에서 해당 코드가 실행되게 하는 것을 의미합니다. 인젝션 유형으로는 라이브러리 파일을 삽입하는 DLL 인젝션, 메모리 영역에 코드를 직접 삽입하는 코드 인젝션이 있습니다. DLL 인젝션은 원래 프로그램 기능을 개선, 추가, 버그 패치를 위해서 사용될 수도 있지만, 그에 비례하게 해킹 기법이나 악성 코드 유포를 하는 데에 많이 쓰이고 있습니다.",
            "DLL인젝션을 하기 위해서는 인젝션을 수행하는 코드(DLL 인젝터)와 인젝션 코드가 필요합니다. 인젝터를 실행시키면 인젝터는 OpenProcess함수를 이용하여 인젝션할 프로세스의 제어권을 획득합니다. 프로세스 핸들을 획득했다면, 프로세스가 사용하는 메모리 내부에 접근이 가능해지고, 이 때 인젝션할 DLL 경로를 해당 프로세스에 기록합니다. 기록된 DLL을 프로세스에서 로드하기 위해 LoadLibrary 함수를 호출하고, 인젝션한 DLL이 프로세스 내부에서 실행됩니니다.",
            "DLL Injection을 감지하는 가장 대표적인 방법은 LoadLibrary함수를 후킹하는 것입니다. DLL이 프로세스에 로드될 때 LoadLibrary 함수를 호출하기 때문에, LoadLibrary 함수 전후를 후킹하여 해당 DLL이 어떤 역할을 하는지 판단 후 인젝션 여부를 결정하는 것입니다. \n" +
                    "이 방법 외에도 LdrDllNotification 메커니즘을 이용하여 DLL들이 로드되거나 언로드될 때 DLL 인젝션을 감지하는 방법, PE signature를 검사하는 방법, Windows10에서 공식적으로 제공되는 Process Mitigation기법을 사용하여 MS 서명된 DLL만 로드되게 할 수 있는 방법 등 여러가지 방법이 있습니다. 그 중 WCE는 Process Mitigation 기법을 사용한 방법을 구현하였으며, 자세한 내용은 “Security Solutions” tab에서 확인해주세요."
    };

    String[] codeModifyImageString = {"./src/Images/Cheat/Code_Modify/0.png", "./src/Images/Cheat/Code_Modify/1.png", "./src/Images/Cheat/Code_Modify/2.png"};
    String[] memoryHackImageString = {"./src/Images/Cheat/Memory_Hack/0.png", "./src/Images/Cheat/Memory_Hack/1.png", "./src/Images/Cheat/Memory_Hack/2.png", "./src/Images/Cheat/Memory_Hack/3.png"};
    String[] dllInjectImageString = {"./src/Images/Cheat/DLL_Inject/0.png", "./src/Images/Cheat/DLL_Inject/1.png", "./src/Images/Cheat/DLL_Inject/2.png"};

    //배열 count
    public int arraytracking = 0;

    public CheatPanel() {
        setLayout(new BorderLayout());
        pageFrameP = new JPanel();
        pageFrameP.setLayout(new BorderLayout());
        pageFrameCenterP = new JPanel();
        pageFrameCenterP.setLayout(new BoxLayout(pageFrameCenterP, BoxLayout.Y_AXIS));

        pageFrameP.add(pageFrameCenterP, BorderLayout.CENTER);

        //보안 메인페이지 타이틀 & 설명글
        cheatTitleL = new JLabel("게임 치트/핵이란?");
        cheatTitleL.setFont(titleFont);
        cheatDescTA = new JTextArea("게임 해킹 프로그램. 게임 프로그램 혹은 게임 내의 스크립트의 해킹 및 수정을 통해, 게임 메커니즘이 허용하지 않는 기능을 제공하여 플레이어가 부정한 방법으로 이득을 취할 수 있도록 돕는 불법 소프트웨어. 인터넷 암시장에서 거래되며, 게임 회사들에게 막대한 금전적 피해를 입힘.\n");
        cheatDescTA.setFont(descFont);
        cheatDescTA.setEditable(false);
        cheatDescTA.setLineWrap(true);
        cheatDescTA.setOpaque(false);

        //페이지에 추가
        openSecMainPage();
        initializeContent();
    }

    //보안 가이드 탭의 메인 페이지
    public void openSecMainPage() {
        //보안 기법 버튼 8개 생성
        codeModifyB = new JButton("게임 코드 변조");
        memoryHackB = new JButton("메모리 해킹");
        dllInjectB = new JButton("DLL injection");

        cheatTitleL = new JLabel("게임 치트/핵이란?");
        cheatTitleL.setFont(titleFont);
        cheatDescTA = new JTextArea("게임 해킹 프로그램. 게임 프로그램 혹은 게임 내의 스크립트의 해킹 및 수정을 통해, 게임 메커니즘이 허용하지 않는 기능을 제공하여 플레이어가 부정한 방법으로 이득을 취할 수 있도록 돕는 불법 소프트웨어. 인터넷 암시장에서 거래되며, 게임 회사들에게 막대한 금전적 피해를 입힘.\n");
        cheatDescTA.setFont(descFont);
        cheatDescTA.setEditable(false);
        cheatDescTA.setLineWrap(true);
        cheatDescTA.setOpaque(false);

        //치트 기법 버튼에 이벤트 추가
        codeModifyB.addActionListener(this);
        memoryHackB.addActionListener(this);
        dllInjectB.addActionListener(this);

        methodButtonP = new JPanel();
        methodButtonP.setLayout(new GridLayout(4,2,10,10));
        methodButtonP.add(codeModifyB);
        methodButtonP.add(memoryHackB);
        methodButtonP.add(dllInjectB);


        add(cheatTitleL, BorderLayout.NORTH);
        add(cheatDescTA, BorderLayout.CENTER);
        add(methodButtonP, BorderLayout.SOUTH);
    }

    //보안페이지 & 이전페이지 & 다음페이지 버튼 추가
    public void addPageFlowButtons(){

        pageFlowButtonP = new JPanel();
        cheatMainB = new JButton("치트페이지로 돌아가기");
        cheatPrevB = new JButton("이전");
        cheatNextB = new JButton("다음");

        cheatMainB.addActionListener(this);
        cheatPrevB.addActionListener(this);
        cheatNextB.addActionListener(this);

        pageFlowButtonP.add(cheatPrevB);
        pageFlowButtonP.add(cheatMainB);
        pageFlowButtonP.add(cheatNextB);

        pageFrameP.add(pageFlowButtonP, BorderLayout.SOUTH);
    }

    public int categoryTracking = -1;

    public void initializeContent() {
        codeModifyTitleL = new JLabel[codeModifyTitleString.length];
        memoryHackTitleL = new JLabel[memoryHackTitleString.length];
        dllInjectTitleL = new JLabel[dllInjectTitleString.length];

        codeModifyDescTA = new JTextArea[codeModifyDescString.length];
        memoryHackDescTA = new JTextArea[memoryHackDescString.length];
        dllInjectDescTA = new JTextArea[dllInjectDescString.length];


        codeModifyII = new ImageIcon[codeModifyImageString.length];
        memoryHackII = new ImageIcon[memoryHackImageString.length];
        dllInjectII = new ImageIcon[dllInjectImageString.length];


        codeModifyImageL = new JLabel[codeModifyImageString.length];
        memoryHackImageL = new JLabel[memoryHackImageString.length];
        dllInjectImageL = new JLabel[dllInjectImageString.length];


        for(int i=0; i<codeModifyTitleString.length; i++){
            codeModifyTitleL[i] = new JLabel(codeModifyTitleString[i]);
            codeModifyTitleL[i].setFont(titleFont);
            codeModifyDescTA[i] = new JTextArea(codeModifyDescString[i]);
            codeModifyDescTA[i].setFont(descFont);
            codeModifyDescTA[i].setEditable(false);
            codeModifyDescTA[i].setLineWrap(true);
            codeModifyDescTA[i].setOpaque(false);
            codeModifyII[i] = new ImageIcon(codeModifyImageString[i]);
            codeModifyImageL[i]= new JLabel(codeModifyII[i]);

            codeModifyImageL[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            codeModifyImageL[i].setAlignmentY(Component.CENTER_ALIGNMENT);
        }
        for(int i=0; i<memoryHackTitleString.length; i++){
            memoryHackTitleL[i] = new JLabel(memoryHackTitleString[i]);
            memoryHackTitleL[i].setFont(titleFont);
            memoryHackDescTA[i] = new JTextArea(memoryHackDescString[i]);
            memoryHackDescTA[i].setFont(descFont);
            memoryHackDescTA[i].setEditable(false);
            memoryHackDescTA[i].setLineWrap(true);
            memoryHackDescTA[i].setOpaque(false);
            memoryHackII[i] = new ImageIcon(memoryHackImageString[i]);
            memoryHackImageL[i]= new JLabel(memoryHackII[i]);

            memoryHackImageL[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            memoryHackImageL[i].setAlignmentY(Component.CENTER_ALIGNMENT);
        }
        for(int i=0; i<dllInjectTitleString.length; i++){
            dllInjectTitleL[i] = new JLabel(dllInjectTitleString[i]);
            dllInjectTitleL[i].setFont(titleFont);
            dllInjectDescTA[i] = new JTextArea(dllInjectDescString[i]);
            dllInjectDescTA[i].setFont(descFont);
            dllInjectDescTA[i].setEditable(false);
            dllInjectDescTA[i].setLineWrap(true);
            dllInjectDescTA[i].setOpaque(false);
            dllInjectII[i] = new ImageIcon(dllInjectImageString[i]);
            dllInjectImageL[i]= new JLabel(dllInjectII[i]);

            dllInjectImageL[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            dllInjectImageL[i].setAlignmentY(Component.CENTER_ALIGNMENT);
        }

    }

    public void securityPageFlow() {

        switch(categoryTracking) {
            case 0:     //컴파일 방식

                for (int i = 0; i < codeModifyTitleString.length; i++) {
                    if (i == arraytracking) {
                        pageFrameP.add(codeModifyTitleL[arraytracking], BorderLayout.NORTH);
                        pageFrameCenterP.add(codeModifyDescTA[arraytracking]);
                        pageFrameCenterP.add(codeModifyImageL[arraytracking]);
                        pageFrameP.add(pageFrameCenterP);
                    }
                }
                break;

            case 1:     //파일 무결성 검사

                // 페이지에 따라 제목을 변경
                for (int i = 0; i < memoryHackTitleString.length; i++) {
                    if (i == arraytracking) {
                        pageFrameP.add(memoryHackTitleL[arraytracking], BorderLayout.NORTH);
                        pageFrameCenterP.add(memoryHackDescTA[arraytracking]);
                        pageFrameCenterP.add(memoryHackImageL[arraytracking]);
                        pageFrameP.add(pageFrameCenterP);
                    }
                }

                // 솔루션 폴더 열어주기

                break;

            case 2:     //변수 암호화
                for (int i = 0; i < dllInjectTitleString.length; i++) {
                    if (i == arraytracking) {
                        pageFrameP.add(dllInjectTitleL[arraytracking], BorderLayout.NORTH);
                        pageFrameCenterP.add(dllInjectDescTA[arraytracking]);
                        pageFrameCenterP.add(dllInjectImageL[arraytracking]);
                        pageFrameP.add(pageFrameCenterP);
                    }
                }

                break;




        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == codeModifyB) {
            categoryTracking = 0;
            removeAll(); pageFrameP.removeAll(); pageFrameCenterP.removeAll();
            securityPageFlow();
            addPageFlowButtons();
            add(pageFrameP);
            revalidate();
        }

        if (e.getSource() == memoryHackB) {
            categoryTracking = 1;
            removeAll(); pageFrameP.removeAll(); pageFrameCenterP.removeAll();
            securityPageFlow();
            addPageFlowButtons();
            add(pageFrameP);
            revalidate();
        }

        if (e.getSource() == dllInjectB) {
            categoryTracking = 2;
            removeAll(); pageFrameP.removeAll();pageFrameCenterP.removeAll();
            securityPageFlow();
            addPageFlowButtons();
            add(pageFrameP);
            revalidate();
        }


        // 다음 버튼 눌렀을 때
        if(e.getSource() == cheatNextB){
            removeAll(); pageFrameP.removeAll();pageFrameCenterP.removeAll();

            arraytracking += 1;
            securityPageFlow();
            addPageFlowButtons();
            add(pageFrameP);
            revalidate();
        }

        // 이전 버튼 눌렀을 때
        if(e.getSource() == cheatPrevB){
            removeAll(); pageFrameP.removeAll();pageFrameCenterP.removeAll();
            arraytracking -= 1;
            securityPageFlow();
            addPageFlowButtons();
            add(pageFrameP);
            revalidate();
        }

        // 보안 메인 페이지로 돌아가기 버튼 눌렀을 때
        if (e.getSource() == cheatMainB) {
            removeAll(); pageFrameP.removeAll(); pageFrameCenterP.removeAll();
            openSecMainPage();  //8개 보안 메뉴 오픈,,
            arraytracking = 0;
            revalidate();
        }

        if(arraytracking == 0){
            cheatPrevB.setEnabled(false);
        }

        switch(categoryTracking) {
            case 0:
                if(arraytracking == codeModifyTitleString.length - 1) {
                    cheatNextB.setEnabled(false);
                }
                break;
            case 1:
                if(arraytracking == memoryHackTitleString.length - 1) {
                    cheatNextB.setEnabled(false);
                }
                break;
            case 2:
                if(arraytracking == dllInjectTitleString.length - 1) {
                    cheatNextB.setEnabled(false);
                }
                break;

        }
        System.out.println("buildTitleString: "+codeModifyTitleString.length);
        System.out.println("arraytracking: "+arraytracking);
        System.out.println("category: "+categoryTracking);
    }
}
