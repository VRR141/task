package vrr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import vrr.entity.Line;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class WriteJson {

    private String path;

    private ObjectWriter objectWriter;

    public WriteJson(String path) {
        this.path = path;
        objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    public WriteJson() {
        objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    public String getJson(Set<Line> setLine) throws JsonProcessingException {
        String json = objectWriter.writeValueAsString(setLine);
        return json;
    }

    public void writeJson(String path, Set<Line> setLine) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        String json = getJson(setLine);
        try( FileWriter writer = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(json);
            bw.flush();
        }
    }

}
