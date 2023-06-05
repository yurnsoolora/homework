import java.util.*;

public class PhoneBookTest {
    private Scanner scan;
    private  Map<String, Phone> phoneBookMap;

    public PhoneBookTest() {
        scan = new Scanner(System.in);
        phoneBookMap = new HashMap<>();
    }

    // 메뉴를 출력하는 메서드
    public void displayMenu(){
        System.out.println();
        System.out.println("메뉴를 선택하세요.");
        System.out.println(" 1. 전화번호 등록");
        System.out.println(" 2. 전화번호 전체 출력");
        System.out.println(" 3. 프로그램 종료");
        System.out.print(" 번호입력 >> ");
    }

    // 프로그램을 시작하는 메서드
    public void phoneBookStart(){
        System.out.println("===============================================");
        System.out.println("   나의 전화번호 관리 시스템   ");
        System.out.println("===============================================");

        while(true){

            displayMenu();  // 메뉴 출력

            int menuNum = scan.nextInt();   // 메뉴 번호 입력

            switch(menuNum){
                case 1 : insert();		// 등록
                    break;
                case 2 : displayAll();	// 전체출력
                    break;
                case 3 :
                    System.out.println("프로그램을 종료합니다");
                    return;
                default :
                    System.out.println("다시입력하세요");
            } // switch문
        } // while문
    }

    /**
     * 새로운 전화번호 정보를 등록하는 메서드
     * (이미 등록된 사람은 등록되지 않는다)
     */
    private void insert() {
        while (true) {
            System.out.println();
            System.out.println("새롭게 등록할 전화번호 정보를 입력하세요");
            System.out.print("이 름 >> ");
            String name = scan.next();

            // 이미 등록된 사람인지 검사
            // get()메서드로 값을 가져올 때 가져올 자료가 없으면 null을 반환한다.
            if (phoneBookMap.get(name) != null) {
                System.out.println(name + "이미 등록된 사람입니다.");
                return; //메서드 종료
            }

            System.out.print("전화번호 >> ");
            String tel = scan.next();

            System.out.print("주소 >> ");
            scan.nextLine();    // 입력버퍼에 남아 있는 엔터키 값까지 읽어와 버리는 역할을 함.
            // next()호출 후 nextLine()호출 시 혹시 남아있을지 모름
            // 엔터값을 제거하기 위해 한번 호출한다.
            String addr = scan.nextLine();

            phoneBookMap.put(name, new Phone(name, tel, addr));
            System.out.println("★등록 완료★");

            System.out.print("계속 등록하시겠습니까? (Y/N): ");
            String answer = scan.next();
            if (answer.equalsIgnoreCase("N")) {
                break;
            }
        }
    }


    /**
     * 전체 자료를 출력하는 메서드
     */
    private void displayAll() {
        Set<String> keySet = phoneBookMap.keySet();

        System.out.println("=====================================");
        System.out.println(" 번호\t이름\t전화번호\t주소 ");
        System.out.println("=====================================");

        if(keySet.size() == 0) {
            System.out.println("등록된 전화번호 정보가 없습니다.");
        } else {
            Iterator<String> it = keySet.iterator();

            int cnt = 0;
            while(it.hasNext()) {
                cnt++;
                String name = it.next();
                Phone p = phoneBookMap.get(name);
                System.out.println(" " + cnt + "\t" + p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());
            }
        }
        System.out.println("=====================================");
        System.out.println("★출력 완료★");
    }



    public static void main(String[] args) {
        new PhoneBookTest().phoneBookStart();
    }

}

/**
 * 전화번호 정보를 저장하기 위한 VO 클래스
 */
class PhoneTest{
    private String name;	// 이름
    private String tel;		// 전화번호
    private String addr;	// 주소

    public PhoneTest(String name, String tel, String addr) {
        super();
        this.name = name;
        this.tel = tel;
        this.addr = addr;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    @Override
    public String toString() {
        return "Phone [name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
    }


}