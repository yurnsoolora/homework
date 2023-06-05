import java.util.*;

public class PhoneBook {
    private Scanner scan;
    private  Map<String, Phone> phoneBookMap;

    public PhoneBook() {
        scan = new Scanner(System.in);
        phoneBookMap = new HashMap<>();
    }

    // 메뉴를 출력하는 메서드
    public void displayMenu(){
        System.out.println();
        System.out.println("메뉴를 선택하세요.");
        System.out.println(" 1. 전화번호 등록");
        System.out.println(" 2. 전화번호 수정");
        System.out.println(" 3. 전화번호 삭제");
        System.out.println(" 4. 전화번호 검색");
        System.out.println(" 5. 전화번호 전체 출력");
        System.out.println(" 0. 프로그램 종료");
        System.out.print(" 번호입력 >> ");
    }

    // 프로그램을 시작하는 메서드
    public void phoneBookStart(){
        System.out.println("===============================================");
        System.out.println("   전화번호 관리 프로그램(파일로 저장되지 않음)");
        System.out.println("===============================================");

        while(true){

            displayMenu();  // 메뉴 출력

            int menuNum = scan.nextInt();   // 메뉴 번호 입력

            switch(menuNum){
                case 1 : insert();		// 등록
                    break;
                case 2 : update();		// 수정
                    break;
                case 3 : delete();		// 삭제
                    break;
                case 4 : search();		// 검색
                    break;
                case 5 : displayAll();	// 전체 출력
                    break;
                case 0 :
                    System.out.println("프로그램을 종료합니다");
                    return;
                default :
                    System.out.println("다시입력하세요");
            } // switch문
        } // while문
    }

    /**
     * 이름을 이용한 전화번호 정보를 검색하는 메서드
     */
    private void search() {
        System.out.println();
        System.out.println("검색할 전화번호 정보를 입력하세요");
        System.out.print("이 름 >> ");
        String name = scan.next();

        Phone p = phoneBookMap.get(name);

        if(p == null) {
            System.out.println("등록된 전화번호 정보가 없습니다.");
        } else {
            System.out.println(name + "전화번호 정보");
            System.out.println("이      름 : " + p.getName());
            System.out.println("전화번호 : " + p.getTel());
            System.out.println("주      소 : " + p.getAddr());
        }
        System.out.println("★검색 완료★");
    }

    /**
     * 전화번호 정보를 삭제하는 메서드
     */
    private void delete() {
        System.out.println();
        System.out.println("삭제할 전화번호 정보를 입력하세요");
        System.out.print("이 름 >> ");
        String name = scan.next();

        // remove(key) => 삭제 성공하면 삭제된 value값만 반환하고, 실패하면 null 반환
        if(phoneBookMap.remove(name) == null) {
            System.out.println("등록된 전화번호 정보가 없습니다.");
        } else {
            System.out.println(name + "정보를 삭제했습니다.");
        }
        System.out.println("★삭제 완료★");
    }

    /**
     * 전화번호 정보를 수정하는 메서드
     */
    private void update() {
        System.out.println();
        System.out.println("새롭게 수정할 전화번호 정보를 입력하세요");
        System.out.print("이 름 >> ");
        String name = scan.next();

        // 수정할 자료가 있는지 검사
        if(phoneBookMap.get(name) == null) {
            System.out.println("등록된 전화번호 정보가 없습니다.");
            return; //메서드 종료
        }

        System.out.print("전화번호 >> ");
        String tel = scan.next();

        System.out.print("주소 >> ");
        scan.nextLine();	// 입력버퍼에 남아 있는 엔터키 값까지 읽어와 버리는 역할을 함.
        // next()호출 후 nextLine()호출 시 혹시 남아있을지 모름
        // 엔터값을 제거하기 위해 한번 호출한다.
        String addr = scan.nextLine();

        Phone p = new Phone(name, tel, addr);
        phoneBookMap.put(name, p);

        System.out.println("★수정 완료★");

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

    /**
     * 새로운 전화번호 정보를 등록하는 메서드
     * (이미 등록된 사람은 등록되지 않는다)
     */
    private void insert() {
        System.out.println();
        System.out.println("새롭게 등록할 전화번호 정보를 입력하세요");
        System.out.print("이 름 >> ");
        String name = scan.next();

        // 이미 등록된 사람인지 검사
        // get()메서드로 값을 가져올 때 가져올 자료가 없으면 null을 반환한다.
        if(phoneBookMap.get(name) != null) {
            System.out.println(name + "이미 등록된 사람입니다.");
            return; //메서드 종료
        }

        System.out.print("전화번호 >> ");
        String tel = scan.next();

        System.out.print("주소 >> ");
        scan.nextLine();	// 입력버퍼에 남아 있는 엔터키 값까지 읽어와 버리는 역할을 함.
        // next()호출 후 nextLine()호출 시 혹시 남아있을지 모름
        // 엔터값을 제거하기 위해 한번 호출한다.
        String addr = scan.nextLine();

        phoneBookMap.put(name, new Phone(name, tel, addr));
        System.out.println("★등록 완료★");
    }

    public static void main(String[] args) {
        new PhoneBook().phoneBookStart();
    }


}

/**
 * 전화번호 정보를 저장하기 위한 VO 클래스
 */
class Phone{
    private String name;	// 이름
    private String tel;		// 전화번호
    private String addr;	// 주소

    public Phone(String name, String tel, String addr) {
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