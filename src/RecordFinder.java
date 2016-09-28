import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class RecordFinder {
    public static List<PersonRecord> findByQuery(List<String> query, Map<String, BitSet> index, Map<Integer, PersonRecord> records) {
        //select all bitsets for attributes that we need
        List<BitSet> attrIndexes = query.stream()
            .map(keyword -> index.getOrDefault(keyword, new BitSet()))
            .collect(Collectors.toList());
        if (!attrIndexes.isEmpty()) {
            // we need to duplicate first bitset because and operation mutates bitset.
            BitSet start = (BitSet) attrIndexes.get(0).clone();
            //join attributes by AND
            attrIndexes.stream().forEach(start::and);
            //find records that correspond to
            return start.stream().mapToObj(l -> records.get(l)).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }

    }

}
