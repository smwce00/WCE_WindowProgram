import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class SecurityPanel extends JPanel implements ActionListener{
    // 보안 패널 메인 페이지
    JLabel securityTitleL;
    JTextArea securityDescTA;
    JPanel methodButtonP, pageFlowButtonP, pageFrameP, pageFrameCenterP;
    JButton buildB, fileIntegB, encryptB, cheatToolB, dllInjectB, obfuscateB, fakeFuncB, blackListB;

    // 보안 기법 페이지
    JLabel[] buildTitleL, fileIntegTitleL, encryptTitleL, cheatToolTitleL, dllInjectTitleL;
    JTextArea[] buildDescTA, fileIntegDescTA, encryptDescTA, cheatToolDescTA, dllInjectDescTA;
    ImageIcon[] buildII, fileIntegII, encryptII, cheatToolII, dllInjectII;
    JLabel[] buildImageL, fileIntegImageL, encryptImageL, cheatToolImageL, dllInjectImageL;

    JButton securityMainB, securityPrevB, securityNextB;

    Font titleFont = new Font("맑은 고딕", Font.BOLD, 35);
    Font descFont = new Font("맑은 고딕", Font.PLAIN, 20);

    //page 제목들
    String[] buildTitleString = {"1. Mono 빌드 방식이란?", "2. Mono 빌드 방법", "3. IL2CPP 빌드 방식이란?", "4. IL2CPP 빌드 방법", "5. IL2CPP 빌드 시 주의사항", "6. Mono vs IL2CPP", "7. IL2CPP 빌드 최적화 방법" };
    String[] fileIntegTitleString = { "1. 파일 무결성 검사 원리", "2. 유니티 빌드마다의 핵심 파일 ", "3. 다운로드 및 코드설명" };
    String[] encryptTitleString = { "1. 변수 암호화 필요성", "2. 변수 암호화 원리", "3. 다운로드" };
    String[] cheatToolTitleString = { "1. 치팅 툴 감지 필요성", "2. 치팅 툴 스트링 감지 ", "3. 치팅 툴 해시 감지", "4. 다운로드" };
    String[] dllInjectTitleString = { "1. DLL 인젝션 감지란?", "2. Win10의 Process Mitigation", "3. Process Mitigation의 단점",
                                    "4. Process.GetCurrentProcess().Modules 활용하기", "5. 솔루션 적용하기" };
    //page 내용들들
    String[] buildDescString = {
        "IL2CPP(C++로 변환하는 중간 언어)는 Unity에서 개발한 스크립팅 백엔드입니다. 여러 플랫폼용으로 프로젝트를 빌드할 때 MONO대신 사용될 수 있습니다. IL2CPP를 사용해서 프로젝트를 빌드를 할 때는 Unity가 스크립트와 어셈블리의 IL코드를 C++코드로 변환한 후, 선택한 플랫폼에 적합한 네이티브 바이너리 파일(예: .exe, apk, .xap)로 만들어 줍니다. 이 방식은 Mono보다 디컴파일이 어렵기 때문에 보안을 위해 추천하는 방식입니다.",
            "EDIT → Project Settings 클릭\n" +
                    "\n" +
                    "Player → Other Settings → Configuration → Scripting Backend → Mono선택",
            "IL2CPP(C++로 변환하는 중간 언어)는 Unity에서 개발한 스크립팅 백엔드입니다. 여러 플랫폼용으로 프로젝트를 빌드할 때 MONO대신 사용될 수 있습니다. IL2CPP를 사용해서 프로젝트를 빌드를 할 때는 Unity가 스크립트와 어셈블리의 IL코드를 C++코드로 변환한 후, 선택한 플랫폼에 적합한 네이티브 바이너리 파일(예: .exe, apk, .xap)로 만들어 줍니다. 이 방식은 Mono보다 디컴파일이 어렵기 때문에 보안을 위해 추천하는 방식입니다.",
            "EDIT → Project Settings 클릭\n" +
                    "\t\t\n" +
                    "Player → Other Settings → Configuration → Scripting Backend → IL2CPP 선택",
            "IL2CPP 빌드 시, 아래와 같은 에러가 발생할 수 있습니다.\n" +
                    "\n" +
                    "Exception: C++ code builder is unable to build C++ code. In order to build C++ code for Windows Desktop, you must have one of these installed: Visual Studio 2015 with C++ compilers and Windows 10 SDK (it cannot build C++ code because it is not installed or missing C++ workload component)\n" +
                    "\n" +
                    "이 문제는 C++ 컴파일러가 포함된 Visual Studio를 설치하거나, 윈도우10 SDK를 설치하면 해결할 수 있습니다. 유니티 헙에서 지금 사용하고 있는 유니티 버전의 “모듈 추가” 기능으로 SDK를 추가하거나, Visual Studio 설치 관리자에서 직접 설치할 수 있습니다.",
            "Mono 빌드와 IL2CPP 빌드에는 각각의 장단점이 있습니다.",
            "1) 증분 빌드 사용하기\n" +
                    "증분 빌드(Incremental Build) 방식을 사용하면 C++ 컴파일러가 마지막 빌드 후에 변경된 파일만 골라 다시 컴파일합니다. 첫 빌드에는 해당이 안되는 이야기지만, 두 번째 빌드부터는 증분 빌드를 강력히 추천합니다.\n" +
                    "\n" +
                    "유니티에서는 Build Settings → Build 선택 후, 빌드 장소로 이전 빌드의 디렉토리를 선택한다면 자동으로 증분 빌드가 됩니다.\n" +
                    "\n" +
                    "\n" +
                    "2) 멀웨어 차단 소프트웨어의 검사에서 유니티 프로젝트 제외시키기\n" +
                    "프로젝트를 빌드하기 전에 컴퓨터에 있는 안티 말웨어 소프트웨어들을 비활성화 합니다. 유니티에서 직접 실험한 결과, 윈도우10 환경에서 윈도우 디펜터를 비활성화하니 빌드 시간이 50 - 66%나 줄었습니다\n" +
                    "\n" +
                    "\n" +
                    "3) SSD에 저장하기\n" +
                    "SSD(Solid State Drive)는 읽기 쓰기 속도가 HDD(Hard Disk Drive)에 비해 훨씬 빠르고, IL code를 C++로 변환하여 컴파일하는 과정에서 많은 읽기/쓰기 작업을 돌려야합니다. 따라서, 프로젝트를 SSD에 저장하고 빌드하면 시간을 단축할 수 있습니다."
    };
    String[] fileIntegDescString = {
            "“파일 무결성”이란 허가 되지 않은 변조(해킹, 사이버공격 등)로부터 파일이 원본과 달라지는 것을 막는 과정입니다.\n" +
                    "유니티 게임의 파일 무결성 검사란, 게임 실행 시 사용되는 핵심 파일들이 원본의 것과 달라지지 않았는지 확인하는 프로세스입니다. 이를 통해 해커들이 내 게임 파일을 변조하여 실행하는 것을 감지해내고, 플레이어들 중 치터(부정행위자)들을 잡아낼 수 있습니다. \n",
            "유니티 게임은 빌드에 따라 생성되는 핵심 파일이 달라집니다. Mono 빌드의 경우에는 Assembly-CSharp.dll 파일이, IL2CPP 빌드의 경우에는 GameAssembly.dll 파일이 생성됩니다. 이 파일들은 유니티 게임의 심장과 같은 존재이므로, 약간의 변조만으로도 당신의 게임에 큰 변화를 불러올 수 있습니다.\n",
            "WCE보안 솔루션에서는 기존 원본 파일의 해시와 게임 실행 시 사용되는 중요한 dll 파일들의 해시를 검사합니다. 해시 충돌을 방지하기 위해 SHA256을 사용했습니다. 게임 서버가 없는 경우, 기존 원본 파일의 해시를 게임 코드 속에 포함해야 하기 때문에 코드 난독화가 반드시 필요합니다. \n"
    };
    String[] encryptDescString = {
            "해커들은 해킹을 하기 위해 많은 치트 툴을 사용합니다. 대표적으로 Cheat Engine이 있는데, 이 툴을 사용하면 해커가 바꾸고 싶은 값의 주소를 찾아내어 해당 값을 마음대로 변조가 가능합니다. 내 게임의 변수들을 해커로부터 보호하고 싶다면 변수 암호화는 필수입니다.",
            "WCE보안 솔루션에서는 가장 가벼운 XOR연산을 통한 변수 암호화를 구현했습니다. 암호화 하고 싶은 변수와 랜덤으로 뽑은 KEY 값을 XOR연산을 하여 암호화 하는 방법이며, 복호화 할 때도 같은 KEY 값을 사용합니다. \n" +
                    "\n" +
                    "게임 실행 시 암호화 할 때 마다 랜덤한 수를 뽑아내는 것이 아닌, 처음 seed값만 랜덤하게 뽑은 후 그 뒤 정해진 shift 연산과 xor 연산을 통해 다음 KEY값을 뽑아내는 방식으로, 실제 랜덤을 쓰는 경우보다 더욱 가볍게 구현했습니다. \n" +
                    "\n" +
                    "또한, WCE의 변수 암호화에는 암호화 기능 뿐만 아니라, 암호화 된 값을 바꾸려는 시도까지 감지하는 기능을 추가했습니다. WCE보안 솔루션에 포함된 “낚시변수”는 변수 암호화를 하는 동시에 “낚시변수”로 암호화 되지 않은 값이 노출되게 했고, 해커가 노출된 이 낚시변수를 cheat engine과 같은 치트 툴로 바꾸는 시도를 하면 해킹으로 감지를 하는 기능입니다.",
            "(누르면 소스 폴더 띄우기) & (사용방법 gif)"
    };
    String[] cheatToolDescString = {
            "게임 치팅 툴을 사용한 해킹은 가장 빈번하게 일어나는 해킹 기법이며, 초보자들도 아주 쉽게 게임을 해킹할 수 있게 도와주는 툴이기 때문에 필수적으로 감지해야 하는 보안기법 입니다. White Cheat Engine 의 “Cheat/Hack 이란?” 탭에 게임 치팅 툴을 사용하여 해킹하는 기법에 대한 더욱 자세한 설명이 있으니 참고하시면 이해하기 수월하실 것입니다. \n" +
                    "WCE보안 솔루션은 게임 치팅 툴을 감지하는 두가지 솔루션을 포함했습니다.",
            "스트링으로 치팅 툴을 감지하는 원리는 현재 컴퓨터에서 돌아가는 모든 프로세스 이름들을 보고, 기존에 저장해 둔 치팅 툴 스트링들과 주기적으로 비교하여 치팅 툴을 감지하는 것입니다. 해당 방법은 가볍고, 관리자 권한이 필요 없으며, 어설픈 치팅 시도는 다 막아낼 수 있지만, 치팅 툴의 파일명을 바꾸는 아주 간단한 방법으로 우회가 가능하다는 치명적인 단점을 가지고 있습니다.",
            "해시로 치팅 툴을 감지하는 원리는 현재 컴퓨터에서 돌아가는 모든 프로세스에 대한 해시를 실시간으로 뽑아내서, 미리 뽑아둔 치팅 툴 해시와 비교하는 것입니다. 해당 방법은 스트링으로 치팅 툴을 감지하는 것보다 우월한 방법이지만, 스트링 감지보다 무겁고, 관리자 권한을 받아야 하며, 어셈블리를 변경해서 우회가 가능하다는 단점을 가지고 있습니다.",
            "다운로드 받으십시오."
    };
    String[] dllInjectDescString = {
            "DLL 인젝션 감지란 많은 게임 치트 기법 중 가장 대표적인 “DLL 인젝션” 시도 행위를 감지해내고, 그 시도를 차단하거나, 후에 해당 플레이어에게 게임 플레이에 제한을 주는 시스템입니다. WCE 보안 솔루션은 DLL 인젝션을 감지하는 두 가지 솔루션을 포함했습니다.\n" +
            "White Cheat Engine의 “Cheat/Hack이란?” 탭에 DLL 인젝션(DLL Injection) 기법에 대해 더욱 자세한 설명이 있으니 참고하시면 이해가 더욱 수월할 것입니다.",
            "Microsoft는 윈도우10에서부터 더욱 강력하고 다양한 Process Mitigation 모듈을 제공하고 있습니다. 이 모듈은 유저들이 취약점 악용(exploit)에 대해 쉽게 대응하고 그 피해를 완화할 수 있게 해주는 것을 목적으로 만들어졌습니다.\n" +
                    "White Cheat Engine의 솔루션은 그 중 PROCESS_MITIGATION_BINARY_SIGNATURE_POLICY의 MicrosoftSignedOnly의 값을 True로 바꿔줌으로서, Microsoft에게 인증된 DLL 파일이 아니라면 프로세스 주소 공간에 로드될 수 없도록 설정했습니다. 즉, 치트 개발자의 치트 DLL이 게임 프로세스에 인젝션될 수 없게 됩니다.",
            "Process Mitigation Policy 모듈은 강력할 뿐만 아니라 사용하기도 매우 편하지만, Windows10에서만 지원이 된다는 치명적인 단점이 있습니다. 2021년 4월을 기준으로, Windows OS 유저들 중 78%는 Windows10을 사용하고 있지만, 아직 16.3%의 유저들이 Windows7을, 나머지 6%는 놀랍게도 Windows8/XP/Vista 등을 아직 사용하고 있습니다.\n" +
                    "물론 게이머들이라면 거의 대부분 Windows10을 사용하고 있겠지만, 치트 사용을 위해 하위 OS 환경에서 게임을 돌릴 치밀한 미친자들을 위한 솔루션도 준비했습니다.\n" +
                    "(사진: Desktop Windows Version Market Share Worldwide | StatCounter Global Stats)",
            "이는 Windows10이 아닌 WinOS에서도 사용할 수 있는 솔루션으로, 게임 Start() 시점에서 게임 프로세스에 로드된 모듈의 수를 저장한 후, 게임 실행중에 주기적으로 다시 구한 숫자가 처음에 비해 늘어난 경우 게임 플레이를 불허하는 기능입니다. DLL 인젝션이 된 경우, 로드 모듈 수치가 1 늘어나기 때문에 무사히 인젝션 치트를 감지해낼 수 있습니다.",
            "여러분의 유니티 게임에 WCE의 mitigation.dll 파일과 injectiondetector.cs 파일을 추가하고, 게임 오브젝트에 injectiondetector.cs 파일을 붙여주면 바로 보안 효과를 볼 수 있습니다."
    };
    String[] buildImageString =   {"./src/Images/Security/Unity_Builds/0.png", "./src/Images/Security/Unity_Builds/1.png", "./src/Images/Security/Unity_Builds/2.png", "./src/Images/Security/Unity_Builds/3.png", "./src/Images/Security/Unity_Builds/4.png", "./src/Images/Security/Unity_Builds/5.png", "./src/Images/Security/Unity_Builds/6.png"};
    String[] fileIntegImageString = {"./src/Images/Security/File_Integrity/0.png", "./src/Images/Security/File_Integrity/1.png", "./src/Images/Security/File_Integrity/2.gif"};
    String[] encryptImageString =   {"./src/Images/Security/Encryption/0.png", "./src/Images/Security/Encryption/1.png", "./src/Images/Security/Encryption/2.gif"};
    String[] cheatToolImageString = {"./src/Images/Security/Cheat_Tool/0.png", "./src/Images/Security/Cheat_Tool/1.png", "./src/Images/Security/Cheat_Tool/2.png", "./src/Images/Security/Cheat_Tool/3.gif"};
    String[] dllInjectImageString = {"./src/Images/Security/DLL_Injection/0.png", "./src/Images/Security/DLL_Injection/1.png", "./src/Images/Security/DLL_Injection/2.png", "./src/Images/Security/DLL_Injection/3.png","./src/Images/Security/DLL_Injection/4.gif"};

    //배열 count
    public int arraytracking = 0;

    public SecurityPanel() {
        setLayout(new BorderLayout());
        pageFrameP = new JPanel();
        pageFrameP.setLayout(new BorderLayout());
        pageFrameCenterP = new JPanel();
        pageFrameCenterP.setLayout(new BoxLayout(pageFrameCenterP, BoxLayout.Y_AXIS));

        pageFrameP.add(pageFrameCenterP, BorderLayout.CENTER);

        //보안 메인페이지 타이틀 & 설명글
        securityTitleL = new JLabel("내 유니티 게임을 보호하는 방법은?");
        securityTitleL.setFont(titleFont);
        securityDescTA = new JTextArea("WCE은 보안 개념 및 기법에 익숙하지 않은 유니티 게임 개발자들도 쉽게 자신의 게임에 적용하고 응용할 수 있는 보안 솔루션들을 제공합니다." +
                "\n아래의 버튼들을 눌러 다양한 보안 솔루션들을 확인해보세요.");
        securityDescTA.setFont(descFont);
        securityDescTA.setEditable(false);
        securityDescTA.setLineWrap(true);
        securityDescTA.setOpaque(false);

        //페이지에 추가
        openSecMainPage();
        initializeContent();
    }

    //보안 가이드 탭의 메인 페이지
    public void openSecMainPage() {
        //보안 기법 버튼 8개 생성
        buildB = new JButton("유니티 빌드 방식");
        fileIntegB = new JButton("파일 무결성 검사");
        encryptB = new JButton("변수 암호화");
        cheatToolB = new JButton("치트 툴 감지");
        dllInjectB = new JButton("DLL 인젝션 감지");
        obfuscateB = new JButton("코드 난독화");
        fakeFuncB = new JButton("낚시 함수");
        blackListB = new JButton("블랙리스트");

        securityTitleL = new JLabel("내 유니티 게임을 보호하는 방법은?");
        securityTitleL.setFont(titleFont);
        securityDescTA = new JTextArea("WCE은 보안 개념 및 기법에 익숙하지 않은 유니티 게임 개발자들도 쉽게 자신의 게임에 적용하고 응용할 수 있는 보안 솔루션들을 제공합니다." +
                "\n아래의 버튼들을 눌러 다양한 보안 솔루션들을 확인해보세요.");
        securityDescTA.setFont(descFont);
        securityDescTA.setEditable(false);
        securityDescTA.setLineWrap(true);
        securityDescTA.setOpaque(false);

        //보안 기법 버튼에 이벤트 추가
        buildB.addActionListener(this);
        fileIntegB.addActionListener(this);
        encryptB.addActionListener(this);
        cheatToolB.addActionListener(this);
        dllInjectB.addActionListener(this);
        obfuscateB.addActionListener(this);
        fakeFuncB.addActionListener(this);
        blackListB.addActionListener(this);

        methodButtonP = new JPanel();
        methodButtonP.setLayout(new GridLayout(4,2,10,10));
        methodButtonP.add(buildB);
        methodButtonP.add(fileIntegB);
        methodButtonP.add(encryptB);
        methodButtonP.add(cheatToolB);
        methodButtonP.add(dllInjectB);
        methodButtonP.add(obfuscateB);
        methodButtonP.add(fakeFuncB);
        methodButtonP.add(blackListB);

        add(securityTitleL, BorderLayout.NORTH);
        add(securityDescTA, BorderLayout.CENTER);
        add(methodButtonP, BorderLayout.SOUTH);
    }

    //보안페이지 & 이전페이지 & 다음페이지 버튼 추가
    public void addPageFlowButtons(){

        pageFlowButtonP = new JPanel();
        securityMainB = new JButton("보안페이지로 돌아가기");
        securityPrevB = new JButton("이전");
        securityNextB = new JButton("다음");

        securityMainB.addActionListener(this);
        securityPrevB.addActionListener(this);
        securityNextB.addActionListener(this);

        pageFlowButtonP.add(securityPrevB);
        pageFlowButtonP.add(securityMainB);
        pageFlowButtonP.add(securityNextB);

        pageFrameP.add(pageFlowButtonP, BorderLayout.SOUTH);
    }

    public int categoryTracking = -1;

    public void initializeContent() {
        buildTitleL = new JLabel[buildTitleString.length];
        fileIntegTitleL = new JLabel[fileIntegTitleString.length];
        encryptTitleL = new JLabel[encryptTitleString.length];
        cheatToolTitleL = new JLabel[cheatToolTitleString.length];
        dllInjectTitleL = new JLabel[dllInjectTitleString.length];

        buildDescTA = new JTextArea[buildDescString.length];
        fileIntegDescTA = new JTextArea[fileIntegDescString.length];
        encryptDescTA = new JTextArea[encryptDescString.length];
        cheatToolDescTA = new JTextArea[cheatToolDescString.length];
        dllInjectDescTA = new JTextArea[dllInjectDescString.length];

        buildII = new ImageIcon[buildImageString.length];
        fileIntegII = new ImageIcon[fileIntegImageString.length];
        encryptII = new ImageIcon[encryptImageString.length];
        cheatToolII = new ImageIcon[cheatToolImageString.length];
        dllInjectII = new ImageIcon[dllInjectImageString.length];

        buildImageL = new JLabel[buildImageString.length];
        fileIntegImageL = new JLabel[fileIntegImageString.length];
        encryptImageL = new JLabel[encryptImageString.length];
        cheatToolImageL = new JLabel[cheatToolImageString.length];
        dllInjectImageL = new JLabel[dllInjectImageString.length];

        for(int i=0; i<buildTitleString.length; i++){
            buildTitleL[i] = new JLabel(buildTitleString[i]);
            buildTitleL[i].setFont(titleFont);
            buildDescTA[i] = new JTextArea(buildDescString[i]);
            buildDescTA[i].setFont(descFont);
            buildDescTA[i].setEditable(false);
            buildDescTA[i].setLineWrap(true);
            buildDescTA[i].setOpaque(false);
            buildII[i] = new ImageIcon(buildImageString[i]);
            buildImageL[i]= new JLabel(buildII[i]);

            buildImageL[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            buildImageL[i].setAlignmentY(Component.CENTER_ALIGNMENT);
        }
        for(int i=0; i<fileIntegTitleString.length; i++){
            fileIntegTitleL[i] = new JLabel(fileIntegTitleString[i]);
            fileIntegTitleL[i].setFont(titleFont);
            fileIntegDescTA[i] = new JTextArea(fileIntegDescString[i]);
            fileIntegDescTA[i].setFont(descFont);
            fileIntegDescTA[i].setEditable(false);
            fileIntegDescTA[i].setLineWrap(true);
            fileIntegDescTA[i].setOpaque(false);
            fileIntegII[i] = new ImageIcon(fileIntegImageString[i]);
            fileIntegImageL[i]= new JLabel(fileIntegII[i]);

            fileIntegImageL[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            fileIntegImageL[i].setAlignmentY(Component.CENTER_ALIGNMENT);
       }
        for(int i=0; i<encryptTitleString.length; i++){
            encryptTitleL[i] = new JLabel(encryptTitleString[i]);
            encryptTitleL[i].setFont(titleFont);
            encryptDescTA[i] = new JTextArea(encryptDescString[i]);
            encryptDescTA[i].setFont(descFont);
            encryptDescTA[i].setEditable(false);
            encryptDescTA[i].setLineWrap(true);
            encryptDescTA[i].setOpaque(false);
            encryptII[i] = new ImageIcon(encryptImageString[i]);
            encryptImageL[i]= new JLabel(encryptII[i]);

            encryptImageL[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            encryptImageL[i].setAlignmentY(Component.CENTER_ALIGNMENT);
        }
        for(int i=0; i<cheatToolTitleString.length; i++){
            cheatToolTitleL[i] = new JLabel(cheatToolTitleString[i]);
            cheatToolTitleL[i].setFont(titleFont);
            cheatToolDescTA[i] = new JTextArea(cheatToolDescString[i]);
            cheatToolDescTA[i].setFont(descFont);
            cheatToolDescTA[i].setEditable(false);
            cheatToolDescTA[i].setLineWrap(true);
            cheatToolDescTA[i].setOpaque(false);
            cheatToolII[i] = new ImageIcon(cheatToolImageString[i]);
            cheatToolImageL[i]= new JLabel(cheatToolII[i]);

            cheatToolImageL[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            cheatToolImageL[i].setAlignmentY(Component.CENTER_ALIGNMENT);
        }
        for(int i=0; i<dllInjectTitleString.length; i++) {
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

    /*
    public static void drawScaledImage(Image image, Component canvas, Graphics g) {
        int imgWidth = image.getWidth(null);
        int imgHeight = image.getHeight(null);

        double imgAspect = (double) imgHeight / imgWidth;

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        double canvasAspect = (double) canvasHeight / canvasWidth;

        int x1 = 0; // top left X position
        int y1 = 0; // top left Y position
        int x2 = 0; // bottom right X position
        int y2 = 0; // bottom right Y position

        if (imgWidth < canvasWidth && imgHeight < canvasHeight) {
            // the image is smaller than the canvas
            x1 = (canvasWidth - imgWidth)  / 2;
            y1 = (canvasHeight - imgHeight) / 2;
            x2 = imgWidth + x1;
            y2 = imgHeight + y1;

        } else {
            if (canvasAspect > imgAspect) {
                y1 = canvasHeight;
                // keep image aspect ratio
                canvasHeight = (int) (canvasWidth * imgAspect);
                y1 = (y1 - canvasHeight) / 2;
            } else {
                x1 = canvasWidth;
                // keep image aspect ratio
                canvasWidth = (int) (canvasHeight / imgAspect);
                x1 = (x1 - canvasWidth) / 2;
            }
            x2 = canvasWidth + x1;
            y2 = canvasHeight + y1;
        }

        g.drawImage(image, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
    }
    */

    public void securityPageFlow() {

        switch(categoryTracking) {
            case 0:     //컴파일 방식

                for (int i = 0; i < buildTitleString.length; i++) {
                    if (i == arraytracking) {
                        pageFrameP.add(buildTitleL[arraytracking], BorderLayout.NORTH);
                        pageFrameCenterP.add(buildDescTA[arraytracking]);
                        pageFrameCenterP.add(buildImageL[arraytracking]);
                        pageFrameP.add(pageFrameCenterP);
                    }
                }
                break;

            case 1:     //파일 무결성 검사

                // 페이지에 따라 제목을 변경
                for (int i = 0; i < fileIntegTitleString.length; i++) {
                    if (i == arraytracking) {
                        pageFrameP.add(fileIntegTitleL[arraytracking], BorderLayout.NORTH);
                        pageFrameCenterP.add(fileIntegDescTA[arraytracking]);
                        pageFrameCenterP.add(fileIntegImageL[arraytracking]);
                        pageFrameP.add(pageFrameCenterP);
                    }
                }

                // 솔루션 폴더 열어주기
                if (arraytracking == 2) {
                    File folder = new File("./src/SecuritySolutions/File_Integrity");

                    Desktop desktop = null;
                    if (Desktop.isDesktopSupported()) {
                        desktop = Desktop.getDesktop();
                    }

                    try {
                        desktop.open(folder);
                    } catch (IOException e) {
                        System.out.println("안됨요");
                    }
                }
                break;

            case 2:     //변수 암호화
                for (int i = 0; i < encryptTitleString.length; i++) {
                    if (i == arraytracking) {
                        pageFrameP.add(encryptTitleL[arraytracking], BorderLayout.NORTH);
                        pageFrameCenterP.add(encryptDescTA[arraytracking]);
                        pageFrameCenterP.add(encryptImageL[arraytracking]);
                        pageFrameP.add(pageFrameCenterP);
                    }
                }
                if (arraytracking == 2) {
                    File folder = new File("./src/SecuritySolutions/Encrypt");

                    Desktop desktop = null;
                    if (Desktop.isDesktopSupported()) {
                        desktop = Desktop.getDesktop();
                    }

                    try {
                        desktop.open(folder);
                    } catch (IOException e) {
                        System.out.println("안됨요");
                    }
                }
                break;

            case 3:     //치팅 툴 감지

                for (int i = 0; i < cheatToolTitleString.length; i++) {
                    if (i == arraytracking) {
                        pageFrameP.add(cheatToolTitleL[arraytracking], BorderLayout.NORTH);
                        pageFrameCenterP.add(cheatToolDescTA[arraytracking]);
                        pageFrameCenterP.add(cheatToolImageL[arraytracking]);
                        pageFrameP.add(pageFrameCenterP);
                    }
                }
                if (arraytracking == 3) {
                    File folder = new File("./src/SecuritySolutions/Cheat_Tool");

                    Desktop desktop = null;
                    if (Desktop.isDesktopSupported()) {
                        desktop = Desktop.getDesktop();
                    }

                    try {
                        desktop.open(folder);
                    } catch (IOException e) {
                        System.out.println("안됨요");
                    }
                }
                break;

            case 4:     //dll injection

                for (int i = 0; i < dllInjectTitleString.length; i++) {
                    if (i == arraytracking) {
                        pageFrameP.add(dllInjectTitleL[arraytracking], BorderLayout.NORTH);
                        pageFrameCenterP.add(dllInjectDescTA[arraytracking]);
                        pageFrameCenterP.add(dllInjectImageL[arraytracking]);
                        pageFrameP.add(pageFrameCenterP);
                    }
                }
                if (arraytracking == 4) {
                    File folder = new File("./src/SecuritySolutions/DLL_Injection");

                    Desktop desktop = null;
                    if (Desktop.isDesktopSupported()) {
                        desktop = Desktop.getDesktop();
                    }

                    try {
                        desktop.open(folder);
                    } catch (IOException e) {
                        System.out.println("IOException: 폴더를 열 수 없습니다");
                    }
                }
                break;

            case 5:
                break;

            case 6:
                break;



        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buildB) {
            categoryTracking = 0;
            removeAll(); pageFrameP.removeAll(); pageFrameCenterP.removeAll();
            securityPageFlow();
            addPageFlowButtons();
            add(pageFrameP);
            revalidate();
        }

        if (e.getSource() == fileIntegB) {
            categoryTracking = 1;
            removeAll(); pageFrameP.removeAll(); pageFrameCenterP.removeAll();
            securityPageFlow();
            addPageFlowButtons();
            add(pageFrameP);
            revalidate();
        }

        if (e.getSource() == encryptB) {
            categoryTracking = 2;
            removeAll(); pageFrameP.removeAll();pageFrameCenterP.removeAll();
            securityPageFlow();
            addPageFlowButtons();
            add(pageFrameP);
            revalidate();
        }

        if (e.getSource() == cheatToolB) {
            categoryTracking = 3;
            removeAll(); pageFrameP.removeAll();pageFrameCenterP.removeAll();
            securityPageFlow();
            addPageFlowButtons();
            add(pageFrameP);
            revalidate();
        }

        if (e.getSource() == dllInjectB) {
            categoryTracking = 4;
            removeAll(); pageFrameP.removeAll();pageFrameCenterP.removeAll();
            securityPageFlow();
            addPageFlowButtons();
            add(pageFrameP);
            revalidate();
        }

        // 다음 버튼 눌렀을 때
        if(e.getSource() == securityNextB){
            removeAll(); pageFrameP.removeAll();pageFrameCenterP.removeAll();

            arraytracking += 1;
            securityPageFlow();
            addPageFlowButtons();
            add(pageFrameP);
            revalidate();
        }

        // 이전 버튼 눌렀을 때
        if(e.getSource() == securityPrevB){
            removeAll(); pageFrameP.removeAll();pageFrameCenterP.removeAll();
            arraytracking -= 1;
            securityPageFlow();
            addPageFlowButtons();
            add(pageFrameP);
            revalidate();
        }

        // 보안 메인 페이지로 돌아가기 버튼 눌렀을 때
        if (e.getSource() == securityMainB) {
            removeAll(); pageFrameP.removeAll(); pageFrameCenterP.removeAll();
            openSecMainPage();  //8개 보안 메뉴 오픈,,
            arraytracking = 0;
            revalidate();
        }

        if(arraytracking == 0){
            securityPrevB.setEnabled(false);
        }

        switch(categoryTracking) {
            case 0:
                if(arraytracking == buildTitleString.length - 1) {
                    securityNextB.setEnabled(false);
                }
                break;
            case 1:
                if(arraytracking == fileIntegTitleString.length - 1) {
                    securityNextB.setEnabled(false);
                }
                break;
            case 2:
                if(arraytracking == encryptTitleString.length - 1) {
                    securityNextB.setEnabled(false);
                }
                break;
            case 3:
                if(arraytracking == cheatToolTitleString.length - 1) {
                    securityNextB.setEnabled(false);
                }
                break;
            case 4:
                if(arraytracking == dllInjectTitleString.length - 1) {
                    securityNextB.setEnabled(false);
                }
                break;
        }
        System.out.println("buildTitleString: "+buildTitleString.length);
        System.out.println("arraytracking: "+arraytracking);
        System.out.println("category: "+categoryTracking);
    }
}
