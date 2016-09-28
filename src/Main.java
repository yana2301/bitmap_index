import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.util.Arrays.asList;


public class Main {

    public static void main(String[] args) throws Exception {
        Scanner inputReader = new Scanner(System.in);
        System.out.println("Read records from resources/test.csv");
        Map<Integer, PersonRecord> records = Uploader.uploadRecords("resources/test.csv");
        Map<String, BitSet> indexedRecords = Indexer.indexPersons(records);
        System.out.println("Enter query or \"exit\" to quit");
        String query = inputReader.nextLine();
        while (!"exit".equals(query)) {
            List<PersonRecord> queryResult = RecordFinder.findByQuery(asList(query.split(",")), indexedRecords, records);
            System.out.println("Results " + queryResult.size());
            queryResult.stream().forEach(System.out::println);
            query = inputReader.nextLine();
        }
    }
}
