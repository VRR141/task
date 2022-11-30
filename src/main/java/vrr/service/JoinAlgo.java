package vrr.service;

import vrr.entity.Line;

import java.util.*;

public class JoinAlgo {

    private List<Line> lines;

    private Set<Line> result;

    //Key - elementQuantity (not null) at Line from Value
    private HashMap<Integer, List<Line>> map;

    //Available elements quantity
    private TreeSet<Integer> set;

    private int lineSize;

    public JoinAlgo(List<Line> lines, Set<Line> result) {
        this.lines = lines;
        this.result = result;
        if (lines == null || result == null){
            throw new RuntimeException("Lines & result must be not null");
        }
        this.lineSize = lines.get(0).getSize();
    }

    private void initMap(){
        set = new TreeSet<>();
        map = new HashMap<>();
        for (Line line: lines){
            Integer elementQuantity = line.getElementQuantity();
            if (map.get(elementQuantity) == null){
                map.put(elementQuantity, new ArrayList<>());
                List<Line> list = map.get(elementQuantity);
                list.add(line);
            } else {
                List<Line> list = map.get(elementQuantity);
                list.add(line);
            }
            set.add(elementQuantity);
        }
    }

    public void allJoinLines(){
        transferNonNullableLines();
        initMap();
        halfJoinLines();
        secondHalfJoinLines();
    }

    private void secondHalfJoinLines() {
        TreeSet<Integer> localSet = new TreeSet<>(set);
        int stop = (1 + set.size()) / 2;

        int previous = 1;

        while (localSet.size() >= stop) {

            int lowQuantity = localSet.pollFirst();

            List<Line> basic = map.get(lowQuantity);
            List<Line> lowList = map.get(previous);

            previous = lowQuantity;

            if (lowQuantity > previous) {
                basic = map.get(lowQuantity);
                lowList = map.get(lowQuantity);
            }

            ArrayList<String> mock = new ArrayList<>();
            for (int i = 0; i < lineSize; i++){
                mock.add(null);
            }
            Line resultLine = new Line(mock);


            for (Line basicLine: basic){

                for (Line previousLine: lowList){

                    if (basicLine == previousLine){
                        continue;
                    }

                    if (basicLine.getElementQuantity() > previousLine.getElementQuantity()){
                        Line temp = JoinLine.stackLines(basicLine, previousLine);
                        int needForMerge = lineSize - temp.getElementQuantity();
                        List<Line> listForMerge = map.get(needForMerge);
                        if (listForMerge!= null){
                            for (Line forMerge: listForMerge){
                                temp = JoinLine.stackLines(temp, forMerge);
                                if (temp.getElementQuantity() == lineSize) {
                                    result.add(temp);
                                }
                            }
                        }

                    }
                    else {
                        Line temp = JoinLine.stackLines(basicLine, previousLine);
                        resultLine = JoinLine.stackLinesWOCheck(resultLine, temp);

                        if (resultLine.getElementQuantity() == lineSize) {
                            result.add(resultLine);
                        }
                    }

                }
            }

        }
    }

    private void halfJoinLines() {
        int stop = set.size() / 2;

        TreeSet<Integer> localSet = new TreeSet<>(set);

        while (localSet.size() > stop) {

            int targetQuantity = localSet.pollLast();

            int quantityForMerge = lineSize - targetQuantity;

            List<Line> targetList = map.get(targetQuantity);

            List<Line> listForMerge = map.get(quantityForMerge);

            for (Line targetLine : targetList) {

                for (Line forMergeLine : listForMerge) {

                    if (targetLine == forMergeLine) {
                        continue;
                    }

                    Line resultLine = JoinLine.stackLines(targetLine, forMergeLine);

                    if (resultLine.getElementQuantity() == lineSize) {
                        result.add(resultLine);
                    }
                }
            }
        }
    }


    private void transferNonNullableLines(){
        Iterator<Line> iterator = lines.iterator();
        while (iterator.hasNext()) {
            Line line = iterator.next();
            if (line.getNullsQuantity() == 0){
                result.add(line);
                iterator.remove();
            }
        }
    }

}
