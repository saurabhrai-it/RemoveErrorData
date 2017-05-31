package qait.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import qait.utils.FileLoader;

public class TestData {

	public static void main(String[] args) throws IOException {
		
		List<String> errorList =  new ArrayList<String>();
		List<String> lines =  new ArrayList<String>();
        String mainLine = null;
        String errorLine = null;
		File dirPath=FileLoader.getFileLocation();

		FileReader fileReaderMainData = new FileReader(dirPath+"\\MainData.csv");
		FileReader fileReaderErrorData = new FileReader(dirPath+"\\ErrorData.csv");
        BufferedReader bufferedReaderMainData = new BufferedReader(fileReaderMainData);
        BufferedReader bufferedReaderErrorData = new BufferedReader(fileReaderErrorData);
        
        while ((errorLine = bufferedReaderErrorData.readLine()) != null) {
        	errorList.add(errorLine);
        }
        
        while ((mainLine = bufferedReaderMainData.readLine()) != null) {
        	int i=0;
        	
        	for(i = 0; i < errorList.size();i++) {
        		if(!mainLine.equals(errorList.get(i)))
        			continue;
        		else
        			break;
        	}
        	
        	if(i == errorList.size())
        	{
        		lines.add(mainLine);
	             lines.add("\n");
        	}
        	
        }

        fileReaderMainData.close();
        fileReaderErrorData.close();
        bufferedReaderMainData.close();
        bufferedReaderErrorData.close();
		
        File newFile = new File(dirPath+"\\CorrectData.csv");
        if(!newFile.exists())
         newFile.createNewFile();
        FileWriter  fileWriter=new FileWriter(dirPath+"\\CorrectData.csv");
//        System.out.println("qwertyuiop");
//        System.out.println(lines);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for(String s : lines)
        		bufferedWriter.write(s);
        bufferedWriter.flush();
        bufferedWriter.close();
        fileWriter.close();
        
	}

}
