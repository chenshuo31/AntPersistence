package org.antpersistence.api;

import org.antpersistence.db.DataStorage;
import org.antpersistence.record.Record;
import org.antpersistence.util.Utils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AntPersistence {

    /**
     * 生成一个存储对象，这个对象用来进行存储的操作，增删改查等
     * @param fileName
     * @param fields
     * @return
     */
    public static DataStorage basicConfig(String fileName, String... fields) {
        File file = new File(fileName);
        if (fields.length > 255) {
            throw new RuntimeException("current field number: " + fields.length + " is more than 255!");
        }
        if (file.exists()) {
            throw new RuntimeException("current file name: " + fileName + " already exists!");
        } else {
            String last = file.getName();
            File dir = new File(fileName.replace(last, ""));
            if (!dir.exists()) {
                boolean b = dir.mkdirs();
                if (!b) {
                    throw new RuntimeException("error occurred while create dir: " + dir.getName());
                }
            }
        }
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            StringBuilder stringBuffer = new StringBuilder();
            stringBuffer.append("_id_");
            stringBuffer.append(",");
            for (String field : fields) {
                stringBuffer.append(field);
                stringBuffer.append(",");
            }
            bufferedWriter.append(stringBuffer.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bufferedWriter != null;
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new DataStorage().setFields(fields).setFileName(fileName);
    }

    public static boolean insert(Map<String, String> data, DataStorage dataStorage) {
        List<Record> records = dataStorage.getRecords();
        if (records == null || records.size() == 0) {
            records = new LinkedList<>();
            dataStorage.setRecords(records);
        }
        records.add(new Record().setId(Utils.generateId()).setValues(data));
        dataStorage.writeToFile();
        return true;
    }

    public static boolean insert(List<Map<String, String>> dataList, DataStorage dataStorage) {
        List<Record> records = dataStorage.getRecords();
        if (records == null || records.size() == 0) {
            records = new LinkedList<>();
            dataStorage.setRecords(records);
        }
        for (Map<String, String> map : dataList) {
            records.add(new Record().setId(Utils.generateId()).setValues(map));
        }
        dataStorage.writeToFile();
        return true;
    }

}
