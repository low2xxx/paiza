import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = Integer.parseInt(sc.nextLine());

        int strikeCount = 0;
        int ballCount = 0;

        List<String> results = new LinkedList<String>();

        for (int i = 0; i < count; i++) {
            String result = sc.nextLine();

            String call = result + "!";

            if (result.equals("strike")) {
                strikeCount++;
            } else if (result.equals("ball")) {
                ballCount++;
            }

            if (strikeCount == 3) {
                call = "out!";
            } else if (ballCount == 4) {
                call = "fourball!";
            }

            results.add(call);
        }
        System.out.println(String.join("\n", results));
    }
}