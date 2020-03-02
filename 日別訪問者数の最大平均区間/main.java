import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line1 = sc.nextLine();
        
        String line2 = sc.nextLine();
        
        String[] day_info_str = line1.split(" ");
        
        int[] day_info = Stream.of(day_info_str).mapToInt(Integer::parseInt).toArray();
        
        String[] access_info_str = line2.split(" ");
        
        int[] access_info = Stream.of(access_info_str).mapToInt(Integer::parseInt).toArray();

        Integer n = day_info[0];
        
        Integer k = day_info[1];
        
        ArrayList<Integer> result = new ArrayList<Integer>();

        Integer i = 0;
        for (Integer count : access_info) {

            //連続した日数のアクセス数合計
            Integer count_days =  count;
    
            if (n - i < k) {
                break;
            }
            
            for (Integer c = 1; c < k; c++) {
                count_days += access_info[i + c];
            }
            
            result.add(count_days);
            
            i++;
        }
        //最大値のインデックス
        Integer max_index = result.size() - 1;
        
        //最大値
        Integer max = result.get(max_index);

        for (i = max_index; 0 <= i; i--) {

            if (max <= result.get(i)) {
                max = result.get(i);
                max_index = i;
            }
        }
        
        //候補数
        Integer candidate = 0;
        
        for (Integer count : result) {
            if (count == max) {
                candidate++;
            }
        }

        Integer start_day = max_index + 1;
        
        System.out.printf("%d %d", candidate, start_day);

    }
}