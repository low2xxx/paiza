import java.util.*;
import java.util.stream.Stream;
import java.util.Comparator.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String line1 = sc.nextLine();

        String line2 = sc.nextLine();

        String[] dayInfoStr = line1.split(" ");

        int[] dayInfo = Stream.of(dayInfoStr).mapToInt(Integer::parseInt).toArray();

        String[] accessInfoStr = line2.split(" ");

        int[] accessInfo = Stream.of(accessInfoStr).mapToInt(Integer::parseInt).toArray();

        int n = dayInfo[0];

        int k = dayInfo[1];

        List<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < n - k + 1; i++) {

            // 連続した日数のアクセス数合計
            int countDays = 0;

            for (int c = 0; c < k; c++) {
                countDays += accessInfo[i + c];
            }

            result.add(countDays);

        }

        // 最大値 nullの場合は例外が投げられる
        final int max = result.stream().max(Comparator.naturalOrder()).get();

        long candidate = result.stream().filter(i -> i == max).count();

        Optional<Integer> maxFirstIndex = Optional.empty();
        for (int i = 0; i < result.size(); i++) {
            if (max == result.get(i)) {
                maxFirstIndex = Optional.of(i);
                break;
            }
        }

        int startDay = maxFirstIndex.get() + 1;

        System.out.printf("%d %d", candidate, startDay);

    }
}