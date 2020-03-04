import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int count = Integer.parseInt(sc.nextLine());

        List<String> history = new LinkedList<String>();

        while (sc.hasNext()) {

            String inputLine = sc.nextLine();

            int index = history.indexOf(inputLine);

            if (index != -1) {
                history.remove(inputLine);
            }

            history.add(0, inputLine);
        }

        String result = String.join("\n", history);

        System.out.println(result);
    }
}