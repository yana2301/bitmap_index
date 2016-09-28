import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Uploader {
    public static Map<Integer, PersonRecord> uploadRecords(String fileName) throws Exception {
        BufferedReader inputStream = new BufferedReader(new FileReader(new File(fileName)));
        String colNamesStr = inputStream.readLine();
        String[] colNames = colNamesStr.split(",");
        Map<Integer, PersonRecord> records = new HashMap<>();
        String recordAsStr;
        while ((recordAsStr = inputStream.readLine()) != null) {
            PersonRecord record = createPersonRecord(recordAsStr, colNames);
            records.put(record.getId(), record);
        }
        return records;
    }

    private static PersonRecord createPersonRecord(String personStr, String[] colNames) {
        String[] recordValues = personStr.split(",");
        Set<String> flags = IntStream.range(1, colNames.length)
            .filter(i -> i < (recordValues.length - 1) && Boolean.valueOf(recordValues[i]))
            .mapToObj(i -> colNames[i])
            .collect(Collectors.toSet());
        return new PersonRecord(Integer.parseInt(recordValues[0]), flags);
    }
}
