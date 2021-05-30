package org.antpersistence.record;

import java.util.Map;
import java.util.Objects;

public class Record {
    private int id;
    private Map<String, String> values;

    public int getId() {
        return id;
    }

    public Record setId(int id) {
        this.id = id;
        return this;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public Record setValues(Map<String, String> values) {
        this.values = values;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return id == record.id && Objects.equals(values, record.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, values);
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", values=" + values +
                '}';
    }
}
