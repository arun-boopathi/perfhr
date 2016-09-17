package com.perficient.hr.service.impl;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.perficient.hr.dao.EmployeeDAO;
import com.perficient.hr.dao.EmployeeLeavesDAO;
import com.perficient.hr.dao.NotificationDAO;
import com.perficient.hr.exception.GenericException;
import com.perficient.hr.form.NotificationMail;
import com.perficient.hr.model.Employee;
import com.perficient.hr.model.EmployeeLeaves;
import com.perficient.hr.model.Notification;
import com.perficient.hr.model.type.MailStatusType;
import com.perficient.hr.model.type.NotificationStatusType;
import com.perficient.hr.service.EmployeeLeavesService;
import com.perficient.hr.service.MailService;
import com.perficient.hr.utils.ExceptionHandlingUtil;
import com.perficient.hr.utils.LoggerUtil;
import com.perficient.hr.utils.PerfHrConstants;

@Repository("employeeLeavesService")
public class EmployeeLeavesServiceImpl implements EmployeeLeavesService {

	protected Logger logger = LoggerFactory.getLogger(EmployeeLeavesServiceImpl.class);
	
	@Autowired
    EmployeeDAO employeeDAO;
	
	@Autowired
	EmployeeLeavesDAO employeeLeavesDAO;
    
    @Autowired
    NotificationDAO notificationDAO;
    
    @Autowired
    MailService mailService;
	
	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
    }
   
    protected Session getSession(){
       return sessionFactory.openSession();
    }
    
	@Override
	public Object parseDocument(String fileName) {
		LoggerUtil.infoLog(logger, "Document Parsing. File :" + fileName );
		Session session = null;
		Transaction tx = null;
		try(FileInputStream fis = new FileInputStream(fileName);) {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
	        Workbook workbook = new XSSFWorkbook(fis);
	        int numberOfSheets = workbook.getNumberOfSheets();
	        for(int i=0; i < numberOfSheets; i++){
	        	Sheet sheet = workbook.getSheetAt(i);
	        	Iterator<Row> rowIterator = sheet.iterator();
	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
	                employeeLeavesDAO.saveLeave(session, row);
	            }
	        }
	        fis.close();
			tx.commit();
		} catch(Exception e){
			LoggerUtil.errorLog(logger, "Unable to import PTO document." , e);
			ExceptionHandlingUtil.transactionRollback(tx);
			return ExceptionHandlingUtil.returnErrorObject("Unable to import PTO document ", e);
		} finally{
			ExceptionHandlingUtil.closeSession(session);	
		}
		return true;
	}

	
	@Override
	public Object loadAllLeaves(String leaveType, String calYear) {
		LoggerUtil.infoLog(logger, "Load All Leave Details");
		List<EmployeeLeaves> list = new ArrayList<>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			list = employeeLeavesDAO.loadAllLeaves(leaveType, calYear, session);
			for(EmployeeLeaves empLeaves: list){
				empLeaves.setNotificationToList
				(notificationDAO.loadNotificationsToByGenericId(empLeaves.getPk(), session));
			}
		} 
		catch (Exception e) {
			LoggerUtil.errorLog(logger, "Unable to load all leaves. ", e);
			return ExceptionHandlingUtil.returnErrorObject("Unable to load all leaves. ", e);
		}
		finally
		{
			ExceptionHandlingUtil.closeSession(session);
		}
		return list;
	}

	@Override
	public Object loadMyLeaves(String leaveType, String calYear, String employeeId) {
		LoggerUtil.infoLog(logger, "Load Employee Leave Details for the employee : " + employeeId);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return employeeLeavesDAO.loadMyLeaves(leaveType, calYear, employeeId, session);
		}
		catch (Exception e) {
			LoggerUtil.errorLog(logger, "Unable to load leaves for employee: "+employeeId , e);
			return ExceptionHandlingUtil.returnErrorObject("Unable to load leaves for employee: "+employeeId , e);
		}
		finally
		{
			ExceptionHandlingUtil.closeSession(session);
		}
	}

	@Override
	public Object loadLeaveReport(EmployeeLeaves employeeLeaves) {
		LoggerUtil.infoLog(logger, "Load Employee Leave Report for employee: "+employeeLeaves.getEmployeeId());
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return employeeLeavesDAO.loadLeaveReport(employeeLeaves, session);
		} catch (Exception e){
			LoggerUtil.errorLog(logger, "Unable to load leave report for employee: '"+employeeLeaves.getEmployeeId() , e);
			return ExceptionHandlingUtil.returnErrorObject("Unable to load leave report for employee: '"+employeeLeaves.getEmployeeId() , e);
		}
		finally
		{
			ExceptionHandlingUtil.closeSession(session);
		}
	}
	
	@Override
	public Object getLeaveBalance(String leaveType, String calYear, String calMonth,
			String employeeId, int totalLeaves) {
		LoggerUtil.infoLog(logger, "Load Leave Balance for employee: "+employeeId);
		Long leaveBalance = null;
		Session session = null;
		try {
			leaveBalance = (long)(PerfHrConstants.LEAVE_PER_MONTH*(Long.parseLong(calMonth))*PerfHrConstants.WORK_HOURS);
			session = sessionFactory.openSession();
			leaveBalance = leaveBalance - employeeLeavesDAO.getLeaveBalance(leaveType, calYear, calMonth, employeeId, totalLeaves, session);
		} catch (Exception e){
			LoggerUtil.errorLog(logger, "Unable to get leave balance for employee: "+employeeId , e);
			return ExceptionHandlingUtil.returnErrorObject("Unable to get leave balance for employee: '"+employeeId , e);
		}
		finally
		{
			ExceptionHandlingUtil.closeSession(session);
		}
		return leaveBalance;
	}
	
	@Override
	public Object applyLeave(EmployeeLeaves employeeLeaves, String userId) {
		LoggerUtil.infoLog(logger, "Apply Employee Leave Service Started. ");
		Session session = sessionFactory.openSession();
		EmployeeLeaves empLeaves = new EmployeeLeaves();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();

			Employee employee = employeeDAO.loadById(userId, session);
			empLeaves.setEmployeeId(employeeLeaves.getEmployeeId());
			empLeaves.setAppliedById(employee.getPk());
			empLeaves.setRequestType(employeeLeaves.getRequestType());
			empLeaves.setComments(employeeLeaves.getComments());
    		empLeaves.setTitle(employeeLeaves.getTitle());
    		empLeaves.setStartsAt(employeeLeaves.getStartsAt());
    		empLeaves.setEndsAt(employeeLeaves.getEndsAt());
    		empLeaves.setDtFromHalf(employeeLeaves.getDtFromHalf());
    		empLeaves.setDtEndHalf(employeeLeaves.getDtEndHalf());
    		empLeaves.setHours(getHours(employeeLeaves));
    		empLeaves.setActive(PerfHrConstants.ACTIVE);
    		empLeaves.setDtCreated(new Date());
    		empLeaves.setDtModified(new Date());
    		empLeaves.setCreatedBy(employee.getPk());
    		empLeaves.setModifiedBy(employee.getPk());
    		
    		employeeLeavesDAO.saveLeave(empLeaves, session);
			
			List<String> recipientList = new ArrayList<>();
			for(Employee notify: employeeLeaves.getNotificationToList()){
				recipientList.add(notify.getEmail());
				Notification notification = new Notification();
	    		notification.setIdGeneric(empLeaves.getPk());
	    		notification.setNotificationTo(notify);
	    		if(employee.getPk().equals(notify.getPk()))
	    			notification.setNotificationStatus(NotificationStatusType.SUBMITTED.getNotificationStatusType());
	    		else
	    			notification.setNotificationStatus(NotificationStatusType.PENDING.getNotificationStatusType());
	    		notification.setNotificationType(employeeLeaves.getRequestType());
	    		notification.setActive(PerfHrConstants.ACTIVE);
	    		notification.setCreatedBy(employee.getPk());
	    		notification.setModifiedBy(employee.getPk());
	    		notification.setDtCreated(new Date());
	    		notification.setDtModified(new Date());
	    		if(!notificationDAO.saveNotification(notification, session)){
	    			throw new GenericException();
	    		}
			}
			tx.commit();
			if(!recipientList.isEmpty()){
				NotificationMail notificationMail = setNotificationMail(empLeaves, recipientList);
				notificationMail.setSubject(employeeLeaves.getTitle());
				notificationMail.setSequence("1");
				notificationMail.setRequestType(MailStatusType.REQUEST.getMailStatusType());
				notificationMail.setStatusType(MailStatusType.CONFIRMED.getMailStatusType());
				mailService.sendNotifcationMail(notificationMail);
			}
		} catch (Exception e){
			LoggerUtil.errorLog(logger, "Unable to apply Leave: "+employeeLeaves.getTitle() , e);
			ExceptionHandlingUtil.transactionRollback(tx);
			return ExceptionHandlingUtil.returnErrorObject("Unable to apply Leave: "+ employeeLeaves.getTitle() , e);
		}
		finally
		{
			ExceptionHandlingUtil.closeSession(session);
		}
		return empLeaves;
	}

	@Override
	public Object updateLeave(EmployeeLeaves employeeLeaves, String userId) {
		LoggerUtil.infoLog(logger, "Update Employee Leave Service Started. ");
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Employee employee = employeeDAO.loadById(userId, session);
    		employeeLeaves.setHours(getHours(employeeLeaves));
    		employeeLeaves.setModifiedBy(employee.getPk());
			employeeLeaves.setDtModified(new Date());
			employeeLeavesDAO.updateLeave(employeeLeaves, session);

			Map<Long, Employee> notifyMap = new HashMap<>();
			for(Employee emp : notificationDAO.loadNotificationsToByGenericId(employeeLeaves.getPk(), session)){
				notifyMap.put(emp.getPk(), emp);
			}
			
			Map<Long, Employee> updNotifyMap = new HashMap<>();
			for(Employee emp : employeeLeaves.getNotificationToList()){
				updNotifyMap.put(emp.getPk(), emp);
			}
			
			Map<Long, Employee> removedMap = new HashMap<>(notifyMap);
			removedMap.keySet().removeAll(updNotifyMap.keySet());
			
			List<String> removedNotifierList = new ArrayList<>();
			for(Employee removedNotifier : removedMap.values()){
				removedNotifierList.add(removedNotifier.getEmail());
				Notification notification = notificationDAO.loadByGenericAndEmployeeId(employeeLeaves.getPk(), removedNotifier.getPk(), PerfHrConstants.ACTIVE);
				notification.setActive(PerfHrConstants.INACTIVE);
				notification.setModifiedBy(employee.getPk());
				notification.setDtModified(new Date());
				notificationDAO.updateNotification(notification);
			}
			
			Map<Long, Employee> newNotifierMap = new HashMap<>(updNotifyMap);
			newNotifierMap.keySet().removeAll(notifyMap.keySet());
			
			List<String> recipientList = new ArrayList<>();
			for(Employee newNotifier : newNotifierMap.values()){
				recipientList.add(newNotifier.getEmail());
				//check if the user already exists for this leave
				Notification notification = notificationDAO.loadByGenericAndEmployeeId(employeeLeaves.getPk(), newNotifier.getPk(), PerfHrConstants.INACTIVE); 
				if(notification != null){
					notification.setActive(PerfHrConstants.ACTIVE);
					notification.setModifiedBy(employee.getPk());
					notification.setDtModified(new Date());
					notificationDAO.updateNotification(notification);
				} else {
					notification = new Notification();
					notification.setIdGeneric(employeeLeaves.getPk());
		    		notification.setNotificationTo(newNotifier);
		    		notification.setNotificationStatus(employee.getPk().equals(newNotifier.getPk())?NotificationStatusType.SUBMITTED.getNotificationStatusType():NotificationStatusType.PENDING.getNotificationStatusType());
		    		notification.setNotificationType(employeeLeaves.getRequestType());
		    		notification.setActive(PerfHrConstants.ACTIVE);
		    		notification.setCreatedBy(employee.getPk());
		    		notification.setModifiedBy(employee.getPk());
		    		notification.setDtCreated(new Date());
		    		notification.setDtModified(new Date());
		    		notificationDAO.saveNotification(notification, session);
				}
			}

			if(!removedNotifierList.isEmpty()){
				NotificationMail notificationMail = setNotificationMail(employeeLeaves, recipientList);
				notificationMail.setSequence("3");
				notificationMail.setSubject("CANCELLED-"+employeeLeaves.getTitle());
				notificationMail.setRequestType(MailStatusType.CANCEL.getMailStatusType());
				notificationMail.setStatusType(MailStatusType.CANCELLED.getMailStatusType());
				mailService.sendNotifcationMail(notificationMail);
			}
			
			if(!recipientList.isEmpty()){
				NotificationMail notificationMail = setNotificationMail(employeeLeaves, recipientList);
				notificationMail.setSubject("UPDATED-"+employeeLeaves.getTitle());
				notificationMail.setSequence("2");
				notificationMail.setRequestType(MailStatusType.REQUEST.getMailStatusType());
				notificationMail.setStatusType(MailStatusType.CONFIRMED.getMailStatusType());
				mailService.sendNotifcationMail(notificationMail);
			}
			
			tx.commit();
		} catch (Exception e){
			LoggerUtil.errorLog(logger, "Unable to update leave: "+employeeLeaves.getTitle() , e);
			ExceptionHandlingUtil.transactionRollback(tx);
			return ExceptionHandlingUtil.returnErrorObject("Unable to update leave: "+employeeLeaves.getTitle() , e);
		}
		finally
		{
			ExceptionHandlingUtil.closeSession(session);
		}
		return true;
	}
	
	private NotificationMail setNotificationMail(EmployeeLeaves employeeLeaves, List<String> recipientList){
		NotificationMail notificationMail = new NotificationMail();
		notificationMail.setCalendar(true);
		notificationMail.setUid(employeeLeaves.getPk().toString());
		notificationMail.setDescription(employeeLeaves.getComments());
		notificationMail.setSummary(employeeLeaves.getTitle());
		notificationMail.setDateEnd(employeeLeaves.getEndsAt());
		notificationMail.setDateStart(employeeLeaves.getStartsAt());
		notificationMail.setMsgBody(employeeLeaves.getComments());
		notificationMail.setRecipientsList(recipientList);
		return notificationMail;
	}
	
	private int getHours(EmployeeLeaves employeeLeaves){
		List<LocalDate> dateList = new ArrayList<>();
		final LocalDate start = new LocalDate(employeeLeaves.getStartsAt().getTime());
	    final LocalDate end = new LocalDate(employeeLeaves.getEndsAt().getTime());
	    LocalDate weekday = start;
	    if (start.getDayOfWeek() == DateTimeConstants.SATURDAY ||
	            start.getDayOfWeek() == DateTimeConstants.SUNDAY) {
	        weekday = weekday.plusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY);
	    }
	    while (weekday.isBefore(end) || weekday.isEqual(end)) {
	        dateList.add(weekday);
	        if (weekday.getDayOfWeek() == DateTimeConstants.FRIDAY)
	            weekday = weekday.plusDays(3);
	        else
	            weekday = weekday.plusDays(1);
	    }
	    int hours = dateList.size()*8;
		if(employeeLeaves.getDtFromHalf().equals(PerfHrConstants.SECOND_HALF)){
			hours = hours -4;
		}
		if(employeeLeaves.getDtEndHalf().equals(PerfHrConstants.FIRST_HALF)){
			hours = hours -4;
		}
		return hours;
	}

	@Override
	public Object deleteLeave(EmployeeLeaves employeeLeaves, String userId) {
		LoggerUtil.infoLog(logger, "Delete Employee Leave Service Started. ");
		
		Session session = null;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			employeeLeaves.setActive(PerfHrConstants.INACTIVE);
			employeeLeaves.setDtModified(new Date());
			employeeLeavesDAO.updateLeave(employeeLeaves, session);
			tx.commit();
			
			List<String> recipientList = new ArrayList<>();
			for(Employee emp : notificationDAO.loadNotificationsToByGenericId(employeeLeaves.getPk(), session)){
				recipientList.add(emp.getEmail());
			}
			
			if(!recipientList.isEmpty()){
				NotificationMail notificationMail = setNotificationMail(employeeLeaves, recipientList);
				notificationMail.setSubject("CANCELLED-"+employeeLeaves.getTitle());
				notificationMail.setSequence("3");
				notificationMail.setRequestType(MailStatusType.CANCEL.getMailStatusType());
				notificationMail.setStatusType(MailStatusType.CANCELLED.getMailStatusType());
				mailService.sendNotifcationMail(notificationMail);
			}
			
		} catch (Exception e){
			LoggerUtil.errorLog(logger, "Unable to delete employee Leaves: "+employeeLeaves.getTitle() , e);
			ExceptionHandlingUtil.transactionRollback(tx);
			return ExceptionHandlingUtil.returnErrorObject("Unable to delete employee Leaves: "+employeeLeaves.getTitle() , e);
		}
		finally
		{
			ExceptionHandlingUtil.closeSession(session);
		}
		return true;
	}

	@Override
	public Object loadLeaveById(String leaveId) {
		LoggerUtil.infoLog(logger, "Delete Employee Leave Service Started. ");
		
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			return employeeLeavesDAO.loadLeaveById(leaveId, session);
		} catch (Exception e){
			LoggerUtil.errorLog(logger, "Unable to load leave Id:"+leaveId , e);
			return ExceptionHandlingUtil.returnErrorObject("Unable to load leave Id:"+leaveId , e);
		}
		finally
		{
			ExceptionHandlingUtil.closeSession(session);
		}
	}

}