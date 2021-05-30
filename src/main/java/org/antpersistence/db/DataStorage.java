package org.antpersistence.db;

import org.antpersistence.record.Record;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DataStorage {

    private String fileName;
    private String[] fields;
    private List<Record> records;

    public String getFileName() {
        return fileName;
    }

    public DataStorage setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public List<Record> getRecords() {
        return records;
    }

    public DataStorage setRecords(List<Record> records) {
        this.records = records;
        return this;
    }

    public String[] getFields() {
        return fields;
    }

    public DataStorage setFields(String[] fields) {
        this.fields = fields;
        return this;
    }

    private int currentWritedIndex = 0;

    public void writeToFile() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName))));
            int i = currentWritedIndex;
            for (; i < records.size(); i++) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < fields.length; j++) {
                    Record record = records.get(i);
                    String s = record.getValues().get(fields[j]);
                    stringBuilder.append(s == null ? "" : s);
                    stringBuilder.append(",");
                }
                writer.append(stringBuilder.toString());
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert writer != null;
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataStorage that = (DataStorage) o;
        return Objects.equals(fileName, that.fileName) && Arrays.equals(fields, that.fields) && Objects.equals(records, that.records);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(fileName, records);
        result = 31 * result + Arrays.hashCode(fields);
        return result;
    }

    @Override
    public String toString() {
        return "DataStorage{" +
                "fileName='" + fileName + '\'' +
                ", fields=" + Arrays.toString(fields) +
                ", records=" + records +
                '}';
    }
}
