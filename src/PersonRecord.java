import java.util.Set;


public class PersonRecord {
    private final int id;
    private final Set<String> attributes;

    public PersonRecord(int id, Set<String> attributes) {
        this.id = id;
        this.attributes = attributes;
    }

    public int getId() {
        return id;
    }

    public Set<String> getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "id = " + id + " attributes:" + attributes;
    }
}
