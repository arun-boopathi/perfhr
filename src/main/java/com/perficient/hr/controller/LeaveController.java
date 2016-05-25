package com.perficient.hr.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.perficient.hr.dao.EmployeeLeavesDAO;
import com.perficient.hr.exception.RecordExistsException;
import com.perficient.hr.model.EmployeeLeaves;

@Controller
@RequestMapping("/v-leave")
public class LeaveController {

	@Value("${wfh.total}")
	private String wfhCount;
	
	@Value("${ptoFilesOutputDir}")
	private String ptoFilesOutputDir;
		
	@Autowired
	private EmployeeLeavesDAO employeeLeavesDAO;
	
	@RequestMapping(value="/fetchExcel", method=RequestMethod.POST)
	@Consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
	@Produces("application/json")
	@ResponseBody
	public boolean uploadExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam("uploadFiles") MultipartFile file) throws IOException{
		return employeeLeavesDAO.pasrsePtoDocument(writeToFileServer(file.getInputStream(), file.getName()));
	}
	
	/**
     * write input stream to file server
     * @param inputStream
     * @param fileName
     * @throws IOException
     */
    private String writeToFileServer(InputStream inputStream, String fileName) throws IOException {
    	FileOutputStream outputStream = null;
    	String qualifiedUploadFilePath =  this.ptoFilesOutputDir+fileName+".xls";
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
    
    @RequestMapping(value="/loadAllLeaves",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public List<EmployeeLeaves> loadAllLeaves(@RequestParam(value="leaveType") String leaveType){
		return employeeLeavesDAO.loadAllLeaves(leaveType);
	}

    @RequestMapping(value="/loadLeaveById",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public EmployeeLeaves loadLeaveById(@RequestParam(value="leaveId") String leaveId){
		return employeeLeavesDAO.loadLeaveById(leaveId);
	}
    
    @RequestMapping(value="/loadMyLeaves",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public List<EmployeeLeaves> loadMyLeaves(@RequestParam(value="leaveType") String leaveType, @RequestParam(value="empId") String employeeId){
		return employeeLeavesDAO.loadMyLeaves(leaveType, employeeId);
	}
    
    @RequestMapping(value="/applyLeave", method=RequestMethod.POST)
	@Produces("application/json")
	@ResponseBody
	public EmployeeLeaves applyLeave(@RequestBody EmployeeLeaves employeeLeaves, HttpServletRequest request) throws RecordExistsException{
    	HttpSession session = request.getSession();
		return employeeLeavesDAO.applyLeave(employeeLeaves, session.getAttribute("userId").toString());
	}
    
    @RequestMapping(value="/updateLeave", method=RequestMethod.PUT)
	@Produces("application/json")
	@ResponseBody
	public boolean updateLeave(@RequestBody EmployeeLeaves employeeLeaves, HttpServletRequest request){
    	HttpSession session = request.getSession();
		return employeeLeavesDAO.updateLeave(employeeLeaves, session.getAttribute("userId").toString());
	}
    
    @RequestMapping(value="/deleteLeave", method=RequestMethod.PUT)
	@Produces("application/json")
	@ResponseBody
	public boolean deleteLeave(@RequestBody EmployeeLeaves employeeLeaves, HttpServletRequest request){
    	HttpSession session = request.getSession();
		return employeeLeavesDAO.deleteLeave(employeeLeaves, session.getAttribute("userId").toString());
	}
}
