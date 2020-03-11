import java.util.*;
import java.util.stream.*;

class Siritori {

    List<Integer> users;

    List<String> words;

    List<String> speaks;

    Iterator<Integer> usersItr;

    boolean beforeUserDroped = false;

    public Siritori(List<Integer> users, List<String> words) {
        this.users = users;
        this.words = words;
        this.speaks = new LinkedList<String>();
        this.usersItr = users.iterator();
    }

    public void speak(String word) {

        if (users.size() <= 0) {
            throw new RuntimeException("invalid users.");
        }

        // イテレータが最後まで回ったら初期化
        if (this.usersItr.hasNext() == false) {
            this.usersItr = users.iterator();
        }

        this.usersItr.next();

        boolean isFollowRules = true;
        if (!isFollowRule1(word) || !isFollowRule3(word) || !isFollowRule4(word)) {
            isFollowRules = false;
        } else if (!beforeUserDroped && !isFollowRule2(word)) {
            isFollowRules = false;
        }

        if (isFollowRules == false) {
            this.usersItr.remove();
            this.beforeUserDroped = true;
        } else {
            this.beforeUserDroped = false;
        }

        this.speaks.add(word);
    }

    public List<Integer> getUsers() {
        return this.users;
    }

    private boolean isFollowRule1(String word) {
        return words.contains(word);
    }

    private boolean isFollowRule2(String word) {

        if (speaks.size() == 0) {
            return true;
        }

        String before = this.speaks.get(this.speaks.size() - 1);

        String wordFirst = word.substring(0, 1);

        String beforeLast = before.substring(before.length() - 1);

        return wordFirst.equals(beforeLast);
    }

    private boolean isFollowRule3(String word) {
        return !this.speaks.contains(word);
    }

    private boolean isFollowRule4(String word) {
        return !word.matches(".*?z$");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 標準入力から値を取得してinput_lineに入れる
        String inputLine = sc.nextLine();

        int[] counts = Stream.of(inputLine.split(" ")).mapToInt(Integer::parseInt).toArray();

        // 参加者数
        int n = counts[0];

        // 単語の数
        int k = counts[1];

        // 発言の数
        int m = counts[2];

        List<Integer> users = new LinkedList<Integer>();

        IntStream.rangeClosed(1, n).forEach(user -> users.add(user));

        List<String> words = new LinkedList<String>();
        for (var i = 0; i < k; i++) {
            words.add(sc.nextLine());
        }

        Siritori siritori = new Siritori(users, words);

        List<String> speaks = new LinkedList<String>();
        for (var i = 0; i < m; i++) {
            speaks.add(sc.nextLine());
        }

        for (String word : speaks) {
            siritori.speak(word);
        }

        List<Integer> resultUsers = siritori.getUsers();

        StringBuffer result = new StringBuffer();

        result.append(resultUsers.size());
        result.append("\n");

        for (int user : resultUsers) {
            result.append(user);
            result.append("\n");
        }

        System.out.println(result.toString());
    }
}