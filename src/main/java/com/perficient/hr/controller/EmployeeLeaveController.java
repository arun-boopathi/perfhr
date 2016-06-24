package com.perficient.hr.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.perficient.hr.dao.EmployeeLeavesDAO;
import com.perficient.hr.exception.RecordExistsException;
import com.perficient.hr.model.EmployeeLeaves;
import com.perficient.hr.utils.PerfUtils;

@Controller
@RequestMapping("/v-leave")
public class EmployeeLeaveController extends AbstractController {
		
	protected Logger logger = LoggerFactory.getLogger(EmployeeLeaveController.class);
	
	@Autowired
	private EmployeeLeavesDAO employeeLeavesDAO;
	
	@RequestMapping(value="/fetchExcel", method=RequestMethod.POST)
	@Consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
	@Produces("application/json")
	@ResponseBody
	public boolean uploadExcel(@RequestParam("uploadFiles") MultipartFile file) throws IOException{
		return employeeLeavesDAO.parseDocument(writeToFileServer(file.getInputStream(), file.getName()));
	}
	
	/**
     * write input stream to file server
     * @param inputStream
     * @param fileName
     * @throws IOException
     */
    private String writeToFileServer(InputStream inputStream, String fileName) throws IOException {
    	String qualifiedUploadFilePath =  perfProperties.getPtoStoreLoc()+fileName+".xls";
        try(FileOutputStream outputStream = new FileOutputStream(new File(qualifiedUploadFilePath));) {
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException ioe) {
            logger.error("Unable to import file "+fileName+" IO Exception is: "+ioe);
        }
        return qualifiedUploadFilePath;
    }
    
    @RequestMapping(value="/loadAllLeaves/{leaveType}/{calYear}",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public List<EmployeeLeaves> loadAllLeaves(@PathVariable("leaveType") String leaveType, @PathVariable("calYear") String calYear){
		return employeeLeavesDAO.loadAllLeaves(leaveType, calYear);
	}
    
    @RequestMapping(value="/getLeaveBalance/{leaveType}/{calYear}/{calMonth}",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public Long getLeaveBalance(@PathVariable("leaveType") String leaveType, @PathVariable("calYear") String calYear,
			@PathVariable("calMonth") String calMonth, HttpServletRequest request){
		return employeeLeavesDAO.getLeaveBalance(leaveType, calYear, calMonth,
				PerfUtils.getUserId(request.getSession()), Integer.parseInt(perfProperties.getPtoCount()));
	}

    @RequestMapping(value="/loadMyLeaves/{leaveType}/{calYear}",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public List<EmployeeLeaves> loadMyLeaves(@PathVariable("leaveType") String leaveType, @PathVariable("calYear") String calYear, HttpServletRequest request){
		return employeeLeavesDAO.loadMyLeaves(leaveType, calYear, PerfUtils.getUserId(request.getSession()));
	}
    
    @RequestMapping(value="/loadLeaveReport",method=RequestMethod.POST)
	@Produces("application/json")
	@ResponseBody
	public List<EmployeeLeaves> loadLeaveReport(@RequestBody EmployeeLeaves employeeLeaves){
		return employeeLeavesDAO.loadLeaveReport(employeeLeaves);
	}
    
    @RequestMapping(value="/loadLeaveById",method=RequestMethod.GET)
	@Produces("application/json")
	@ResponseBody
	public EmployeeLeaves loadLeaveById(@RequestParam(value="leaveId") String leaveId){
		return employeeLeavesDAO.loadLeaveById(leaveId);
	}
    
    @RequestMapping(value="/applyLeave", method=RequestMethod.POST)
	@Produces("application/json")
	@ResponseBody
	public EmployeeLeaves applyLeave(@RequestBody EmployeeLeaves employeeLeaves, HttpServletRequest request) throws RecordExistsException{
		return employeeLeavesDAO.applyLeave(employeeLeaves, PerfUtils.getUserId(request.getSession()));
	}
    
    @RequestMapping(value="/updateLeave", method=RequestMethod.PUT)
    @Produces("application/json")
	@Consumes("application/json")
	@ResponseBody
	public boolean updateLeave(@RequestBody EmployeeLeaves employeeLeaves, HttpServletRequest request){
		return employeeLeavesDAO.updateLeave(employeeLeaves, PerfUtils.getUserId(request.getSession()));
	}
    
    @RequestMapping(value="/deleteLeave", method=RequestMethod.PUT)
	@Produces("application/json")
	@ResponseBody
	public boolean deleteLeave(@RequestBody EmployeeLeaves employeeLeaves, HttpServletRequest request){
		return employeeLeavesDAO.deleteLeave(employeeLeaves, PerfUtils.getUserId(request.getSession()));
	}
}
