package com.perficient.hr.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/v-pto")
public class PtoController {

	public static String ON_LEAVE = "PTO";
	public static String IS_EMPTY = "";
	public static String UPLOAD_LOCATION = "D:/demo/upload/";
	
	@RequestMapping(value="/fetchExcel",method=RequestMethod.POST)
	@Consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
	public @ResponseBody String uploadExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam("uploadFiles") MultipartFile file) throws IOException{
		writeToFileServer(file.getInputStream(), file.getName());
		readExcelFile(file.getName()); 
		return "success";
	}
	
	@RequestMapping(value="/fetchDefaultDetails",method=RequestMethod.GET)
	public @ResponseBody String returnDefaultValues(){
		// fetch values from data base
		String lastUpdated = "Feb, 2016";
		String leavesTaken = "5";
		String leavesLeft = "6";
		String contributionJson= "[{\"fetchDefaultDetails\": { \"lastUpdated\": "+lastUpdated+", \"leavesTaken\": "+leavesTaken+",\"leavesLeft\": "+leavesLeft+"}}]";
		return contributionJson;
	}
	
	public void readExcelFile(String fileName) throws IOException {
		try {
			FileInputStream fis = new FileInputStream(UPLOAD_LOCATION+fileName+".xlsx");
	        Workbook workbook = null;
	        workbook = new XSSFWorkbook(fis);
	        int numberOfSheets = workbook.getNumberOfSheets();
	        Map<String, Integer> map = new HashMap<String, Integer>();
	        for(int i=0; i < numberOfSheets; i++){
	        	Sheet sheet = workbook.getSheetAt(i);
	        	Iterator<Row> rowIterator = sheet.iterator();
	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
	                String leaveType = row.getCell(12).toString();
	                String pinNo = row.getCell(18).toString();
	                if(leaveType != IS_EMPTY && pinNo != IS_EMPTY){
	                	if(leaveType.contains(ON_LEAVE)){
	                		 int index=0;
	                		 if(null == map.get(pinNo)){
	                			 index=0; 
	                		 } else {
	                			 index = map.get(pinNo);
	                		 }
	                		 map.put(pinNo,index + 1);
	                	}
	                }
	            }
	            for (Entry<String, Integer> entry : map.entrySet()) {
	                System.out.println(entry.getKey() + "/" + entry.getValue());
	            }
	        }
            fis.close();
	    } catch(IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
     * write input stream to file server
     * @param inputStream
     * @param fileName
     * @throws IOException
     */
    private String writeToFileServer(InputStream inputStream, String fileName) throws IOException {
        OutputStream outputStream = null;
        String qualifiedUploadFilePath = "D:/Demo/upload/" + fileName+".xls";
        try {
            outputStream = new FileOutputStream(new File(qualifiedUploadFilePath));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            outputStream.close();
        }
        return qualifiedUploadFilePath;
    }
}
