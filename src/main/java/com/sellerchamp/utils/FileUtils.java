package com.sellerchamp.utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.sellerchamp.main.Framework;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class FileUtils {

    private FileUtils(){}

    public static boolean isFileDownloaded(String expectedFileName, String fileExtension, int timeOut){
        File[] listOfFiles;
        String fileName;
        String Folder = (String) Framework.getDownloadDirectory();
        long startTime = Instant.now().toEpochMilli();

        long waitTime = startTime + timeOut;

        while(Instant.now().toEpochMilli() < waitTime){
            listOfFiles = new File(Folder+"/"+expectedFileName).listFiles();
            for(File file: listOfFiles){
                fileName = file.getName().toLowerCase();
                if(file.lastModified() > startTime && !fileName.contains("crdownload") &&   fileName.contains(expectedFileName.toLowerCase()) && fileName.contains(fileExtension.toLowerCase())){
                    return true;
                }
            }
        }
        return false;
    }

    private static List<Map<String, String>> read(File file) throws IOException {
        List<Map<String, String>> response = new LinkedList<>();
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        MappingIterator<Map<String, String>> iterator = mapper.reader(Map.class)
                .with(schema)
                .readValues(file);
        while (iterator.hasNext()) {
            response.add(iterator.next());
        }
        return response;
    }

    public static Map<String, String> csvToMap(File file) throws IOException {

        List<Map<String, String>> list = read(file);
        Map<String, String> map = list.stream()
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return map;

    }


}
