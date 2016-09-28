import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Indexer {

    public static Map<String, BitSet> indexPersons(Map<Integer, PersonRecord> personRecords) {
        Map<String, BitSet> index = new HashMap<>();
        personRecords.values().stream().forEach(record -> indexRecord(index, record));
        return Collections.unmodifiableMap(index);
    }

    private static void indexRecord(Map<String, BitSet> index, PersonRecord record) {
        record.getAttributes().forEach(
            attr -> {
                BitSet attrIndex = index.computeIfAbsent(attr, k -> new BitSet());
                attrIndex.set(record.getId());
            }
        );
    }

}
