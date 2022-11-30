package vrr.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ParseJson {

    private String path;

    private ObjectMapper objectMapper;

    public ParseJson(String path) {
        this.path = path;
        objectMapper = new ObjectMapper();
    }

    public Object parseJson(){
        File file = new File(path);
        Object result;
        try {
            result = objectMapper.readValue(file, new TypeReference<>(){});
        } catch (IOException e) {
            System.out.println("FILE NOT FOUND!");
            throw new RuntimeException(e);
        }
        return result;
    }


}
