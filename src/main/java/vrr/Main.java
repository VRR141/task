package vrr;

import com.fasterxml.jackson.databind.ObjectMapper;
import vrr.service.JoinAlgo;
import vrr.entity.Line;
import vrr.service.ParseJson;
import vrr.service.WriteJson;

import java.io.IOException;
import java.util.*;

public class Main {

    static ObjectMapper objectMapper = new ObjectMapper();

    private static String path = "jsonForParse.json";

    private static String res = "jsonAfterMerge.json";

    public static void main(String[] args) throws IOException {
        pojoToJsonString();
    }

    static void pojoToJsonString() throws IOException {

        HashSet<Line> result = new HashSet<>();

        List<Line> lines = new ArrayList<>();

        ParseJson json = new ParseJson(path);

        List<List<String>> strings = (List<List<String>>) json.parseJson();

        for (List<String> s : strings) {
            Line line = new Line(s);
            lines.add(line);
        }

        JoinAlgo joinAlgo = new JoinAlgo(lines, result);
        joinAlgo.allJoinLines();

        WriteJson writeJson = new WriteJson();
        String jsonResult = writeJson.getJson(result);
        System.out.println(jsonResult);
        writeJson.writeJson(res, result);
    }
}