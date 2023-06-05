import handler.MemberHandler;
import util.Prompt;

public class app9 {

    public static void main(String[] args) {

        printTitle();

        while (MemberHandler.available()) {
            MemberHandler.inputMember();
            if (!promptContinue()) {
                break;
            }
        }

        MemberHandler.printMembers();

        Prompt.close();
    }

    static void printTitle() {
        System.out.println("나의 목록 관리 시스템");
        System.out.println("----------------------------------");
    }

    static boolean promptContinue() {
        String response = Prompt.inputString("계속 하시겠습니까?(Y/n) ");
        if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
            return false;
        }
        return true;
    }
}
