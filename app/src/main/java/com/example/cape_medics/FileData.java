package com.example.cape_medics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileData {

    private String path;

    public FileData(String filePath){
        path = filePath;
    }

    public String[] OpenFile() throws IOException{
        FileReader fd = new FileReader(path);
        BufferedReader reader = new BufferedReader(fd);

        int numLines = readLines();
        String[] textData = new String[numLines];

        for (int i = 0; i<numLines; i++){
            textData[i]  = reader.readLine();
        }

        reader.close();
        return textData;
    }

    int readLines() throws IOException{
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader =  new BufferedReader(fileReader);

        String line;
        int numLines = 0;

        while((line = bufferedReader.readLine()) != null){
            numLines++;
        }

        bufferedReader.close();

        return numLines;
    }

}
