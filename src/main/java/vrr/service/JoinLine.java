package vrr.service;

import vrr.entity.Line;

import java.util.Arrays;
import java.util.List;

public class JoinLine {

    public static Line stackLines(Line line1, Line line2) {
        List<String> result = Arrays.asList(null, null, null, null);
        List<String> list1 = line1.getLine();
        List<String> list2 = line2.getLine();
        if (canStackLists(list1, list2)){
            for (int i = 0; i < list1.size(); i++){
                if (list1.get(i) == null && list2.get(i) == null){
                    result.set(i, null);
                } if (list1.get(i) != null && list2.get(i) == null){
                    result.set(i, list1.get(i));
                } if (list2.get(i) != null && list1.get(i) == null){
                    result.set(i, list2.get(i));
                }
            }
            return new Line(result);
        }
        return line1;
    }

    public static Line stackLinesWOCheck(Line line1, Line line2) {
        List<String> result = Arrays.asList(null, null, null, null);
        List<String> list1 = line1.getLine();
        List<String> list2 = line2.getLine();
            for (int i = 0; i < list1.size(); i++){
                if (list1.get(i) == null && list2.get(i) == null){
                    result.set(i, null);
                } if (list1.get(i) != null && list2.get(i) == null){
                    result.set(i, list1.get(i));
                } if (list2.get(i) != null && list1.get(i) == null){
                    result.set(i, list2.get(i));
                }
            }
            return new Line(result);
    }

    private static boolean canStackLists(List<String> list1, List<String> list2){
        for (int i = 0; i < list1.size(); i++){
            if (list1.get(i) != null && list2.get(i) != null){
                return false;
            }
        }
        return true;
    }
}
