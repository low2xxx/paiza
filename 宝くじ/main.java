import java.util.*;
import java.util.function.Predicate;

/** 宝くじのチケットクラス */
class Ticket {

    private int number;

    public Ticket(int number) {
        if (number < 100000 || 199999 < number) {
            throw new RuntimeException("ticket number error.");
        }
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getNumberStr() {
        return String.valueOf(number);
    }

}

/** 宝くじ抽選クラス */
class Lottery {

    private int winningNumber;

    private String winningNumberStr;

    private final String first = "first";

    private final String second = "second";

    private final String third = "third";

    private final String adjacent = "adjacent";

    private final String blank = "blank";

    public Lottery(int number) {
        if (number < 100000 || 199999 < number) {
            throw new RuntimeException("winning number error.");
        }
        this.winningNumber = number;
        this.winningNumberStr = String.valueOf(number);
    }

    public String result(Ticket ticket) {
        if (isFirst(ticket)) {
            return first;
        } else if (isSecond(ticket)) {
            return second;
        } else if (isThird(ticket)) {
            return third;
        } else if (isAdjucent(ticket)) {
            return adjacent;
        }
        return blank;
    }

    private boolean isFirst(Ticket ticket) {
        return ticket.getNumber() == winningNumber;
    }

    private boolean isSecond(Ticket ticket) {
        String n = ticket.getNumberStr();

        return n.substring(n.length() - 4).equals(winningNumberStr.substring(winningNumberStr.length() - 4));
    }

    private boolean isThird(Ticket ticket) {
        String n = ticket.getNumberStr();

        return n.substring(n.length() - 3).equals(winningNumberStr.substring(winningNumberStr.length() - 3));
    }

    private boolean isAdjucent(Ticket ticket) {
        int n = ticket.getNumber();

        return n == winningNumber + 1 || n == winningNumber - 1;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        final String winning = sc.nextLine();

        int sheets = Integer.parseInt(sc.nextLine());

        List<String> results = new LinkedList<String>();

        Lottery lottery = new Lottery(Integer.parseInt(winning));

        for (int i = 0; i < sheets; i++) {
            String number = sc.nextLine();

            Ticket ticket = new Ticket(Integer.parseInt(number));

            results.add(lottery.result(ticket));
        }

        System.out.println(String.join("\n", results));
    }
}