import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        Integer count = Integer.parseInt(sc.nextLine());
        
        List<String> history = new ArrayList<String>();
        
        for (Integer i = 0; i <= count; i++) {
            
            if (false == sc.hasNext()) {
                break;
            }
            String input_line = sc.nextLine();
            
            Integer index = history.indexOf(input_line);
            
            if (-1 != index) {
                history.remove(input_line);
            }
            
            history.add(0, input_line);
        }
        
        String result = String.join("\n", history);

        System.out.println(result);
    }
}