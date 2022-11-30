package vrr.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.*;


@JsonFormat(shape=JsonFormat.Shape.ARRAY)
public class Line {
    private List<String> line;

    @JsonIgnore
    private int size;

    @JsonIgnore
    private int nullsQuantity;

    @JsonIgnore
    private int elementQuantity;


    public Line(List<String> line) {
        this.line = line;
        this.size = line.size();
        nullsQuantity = 0;
        for (String s: line){
            if (s == null){
                nullsQuantity++;
            }
        }
        this.elementQuantity = size - nullsQuantity;
    }

    public Line() {
    }


    public void setLine(List<String> line) {
        this.line = line;
    }

    public List<String> getLine() {
        return line;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNullsQuantity() {
        return nullsQuantity;
    }


    public void setNullsQuantity(int nullsQuantity) {
        this.nullsQuantity = nullsQuantity;
    }

    public int getElementQuantity() {
        return elementQuantity;
    }

    public void setElementQuantity(int elementQuantity) {
        this.elementQuantity = elementQuantity;
    }

    @Override
    public String toString() {
        return "line=" + line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line1 = (Line) o;
        return this.listEqual(line1) && size == line1.size && elementQuantity == line1.elementQuantity;
    }

    private boolean listEqual(Line line1){
        if (this.line == line1) return true;
        if (line1 == null || getClass() != line1.getClass()) return false;
        for (int i = 0; i < size; i++){
            if (this.line.get(i) == null && line1.line.get(i) != null){
                return false;
            }
            if (this.line.get(i) != null && line1.line.get(i) == null){
                return false;
            }
            if (this.line.get(i) == null && line1.line.get(i) == null){
               continue;
            }
            if (!this.line.get(i).equals(line1.line.get(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        if (this == null){
            return 0;
        }

        int result = 1;

        for (String s: line){
            result = 31 * result + (s == null ? 0 : s.hashCode());
        }

        result = 31 * result + size;
        result = 31 * result + elementQuantity;

        return result;
    }
}
