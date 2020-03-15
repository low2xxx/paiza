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

            // １回目とそれ以降でロジックの切り分け
            if (result == null || result.size() == 0) {
                // CP期間の合計アクセス数
                for (int c = 0; c < k; c++) {
                    countDays += accessInfo[i + c];
                }
            } else {
                // (前回計算結果) - (基準日の前日アクセス数) + (CP期間だった場合の最終日アクセス数)
                countDays = result.get(i - 1) - accessInfo[i - 1] + accessInfo[i + k - 1];
            }

            result.add(countDays);
        }

        // 最大値の取得
        final int max = Collections.max(result);

        // CP開催日の候補数
        long candidate = result.stream().filter(i -> i == max).count();

        // キャンペーン開始日
        int startDay = result.indexOf(max) + 1;

        System.out.printf("%d %d", candidate, startDay);

    }
}