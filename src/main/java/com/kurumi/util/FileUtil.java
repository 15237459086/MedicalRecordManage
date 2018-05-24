package com.kurumi.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static boolean createOrEditFile(String data,String filePath,String fileName) throws IOException{
		return createOrEditFile(data,filePath,fileName,"utf-8");
       
	}
	
	
	public static boolean createOrEditFile(String data,String filePath,String fileName,String charsetName) throws IOException{
		File file = new File(filePath,fileName);
		if(!file.exists()){
			File destDir = new File(filePath);
			if(!destDir.exists()){
				destDir.mkdirs();
			}
			file.createNewFile();
		}
		
		FileOutputStream fileOutputStream=new FileOutputStream(filePath+fileName);  
        OutputStreamWriter outputWriter=new OutputStreamWriter(fileOutputStream,charsetName);  
        outputWriter.write(data);
         
        outputWriter.close();  
        fileOutputStream.close();
       
		return true;
	}
	
	public static String readFile(String filePath,String fileName,String charsetName){
		StringBuilder fileContent = new StringBuilder();
		File file = new File(filePath,fileName);
		if(file.exists()){
	        try {
	        	
	        	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath+fileName),charsetName));
	        	String lineTxt = null;
	        	while((lineTxt = bufferedReader.readLine()) != null)
	            {
	        		fileContent.append(lineTxt);
	            }
	        	bufferedReader.close();
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }
	        return fileContent.toString();
		}
		return null;
	}
	
	public static String readFile(String filePath,String fileName){
		return readFile(filePath,fileName,"utf-8");
	}
	
	public static List<String> getChileFilePaths(String filePath){
		List<String> chileFilePaths = new ArrayList<String>();
		File file = new File(filePath);
		if(file.exists()){
			String[] fileNames = file.list();
			for (String fileName : fileNames) {
				String chileFilePath = filePath + fileName;
				chileFilePaths.add(chileFilePath);
				System.err.println(chileFilePath);
			}
		}
		return chileFilePaths;
	}
	
	
}
