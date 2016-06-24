package com.perficient.hr.dao.impl;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
import com.perficient.hr.model.type.LeaveType;
import com.perficient.hr.model.type.MailStatusType;
import com.perficient.hr.model.type.NotificationStatusType;
import com.perficient.hr.model.type.NotificationType;
import com.perficient.hr.service.MailService;
import com.perficient.hr.utils.DateUtils;
import com.perficient.hr.utils.PerfHrConstants;

@Repository("employeeLeavesDAO")
public class EmployeeLeavesDAOImpl implements EmployeeLeavesDAO {

	protected Logger logger = LoggerFactory.getLogger(EmployeeLeavesDAOImpl.class);

	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	
	@Autowired
    EmployeeDAO employeeDAO;
    
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
	public boolean parseDocument(String fileName) {
		boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try(FileInputStream fis = new FileInputStream(fileName);){
			Transaction tx = session.beginTransaction();
	        Workbook workbook = new XSSFWorkbook(fis);
	        int numberOfSheets = workbook.getNumberOfSheets();
	        for(int i=0; i < numberOfSheets; i++){
	        	Sheet sheet = workbook.getSheetAt(i);
	        	Iterator<Row> rowIterator = sheet.iterator();
	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
	                saveLeave(session, row);
	            }
	        }
	        fis.close();
			tx.commit();
			returnVal = true;
		} catch(Exception e){
			logger.error("Unable to import PTO document. Exception is: "+e);
		} finally{
			session.close();
		}
		return returnVal;
	}

	private void saveLeave(Session session, Row row) throws ParseException{
		String leaveType = row.getCell(11).toString();
        String hours = row.getCell(31).toString();
        if((leaveType.equals(LeaveType.PTO.getLeaveType()) 
        		|| leaveType.equals(LeaveType.UNPLANNED_PTO.getLeaveType())) && !"0".equals(hours)){
    		String sqlQuery =" from Employee as o where o.employeeId=:employeeId";
    		Query query = session.createQuery(sqlQuery);
    		query.setParameter("employeeId", row.getCell(17).toString());
    		Employee employee = (Employee) query.uniqueResult();
    		if(employee != null) {
    			EmployeeLeaves employeeLeaves = new EmployeeLeaves();
    			employeeLeaves.setEmployeeId(employee.getPk());
    			employeeLeaves.setAppliedById(employee.getPk());
    			employeeLeaves.setRequestType(row.getCell(11).toString());
        		employeeLeaves.setComments(row.getCell(35).toString());
        		Date dt = sdf.parse(row.getCell(28).toString());
        		employeeLeaves.setTitle(row.getCell(18).toString()+" - "+leaveType);
        		employeeLeaves.setStartsAt(dt);
        		employeeLeaves.setEndsAt(dt);
        		employeeLeaves.setHours((Math.round(Float.parseFloat(hours)) < 4) ? 4:8);
        		employeeLeaves.setDtCreated(new Date());
        		employeeLeaves.setDtModified(new Date());
        		employeeLeaves.setCreatedBy(employee.getPk());
        		employeeLeaves.setModifiedBy(employee.getPk());
        		
        		session.save(employeeLeaves);
        		
        		Employee supervisor = employeeDAO.loadById(String.valueOf(employee.getSupervisor()));
        		
        		Notification notification = new Notification();
        		notification.setIdGeneric(employeeLeaves.getPk());
        		notification.setNotificationTo(supervisor);
        		notification.setNotificationStatus(NotificationStatusType.APPROVED.getNotificationStatusType());
        		notification.setNotificationType(NotificationType.PTO.getLeaveType());
        		notification.setDtCreated(new Date());
        		notification.setDtModified(new Date());
        		notification.setCreatedBy(employee.getPk());
        		notification.setModifiedBy(employee.getPk());
        		session.save(notification);
    		}
        }
	}
	
	@SuppressWarnings("unchecked")
	private List<EmployeeLeaves> loadEmployeeLeaves(String sqlQuery, String leaveType, Date startsAt, Date endsAt, String employeeId){
		Session session = sessionFactory.openSession();
		List<EmployeeLeaves> list = new ArrayList<>();
		try {
			Query query = session.createQuery(sqlQuery);
			List<String> leaveTypeList = new ArrayList<>();
			if(leaveType.equals(LeaveType.PTO.getLeaveType()) 
					|| leaveType.equals(LeaveType.ALL_PTO.getLeaveType())){
				leaveTypeList.add(LeaveType.PTO.getLeaveType());
				leaveTypeList.add(LeaveType.UNPLANNED_PTO.getLeaveType());
			} else {
				leaveTypeList.add(LeaveType.WFH.getLeaveType());
			}
			query.setParameterList("requestTypes", leaveTypeList);
			if(employeeId != null)
				query.setParameter("employeeId", Long.parseLong(employeeId));
			query.setParameter("active", PerfHrConstants.ACTIVE);
			query.setParameter("startsAt", startsAt);
			query.setParameter("endsAt", endsAt);
			list = query.list();
		} catch (Exception e) {
			logger.error("Unable to load leaves for employee: '"+employeeId+"'. Exception is: "+e);
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<EmployeeLeaves> loadAllLeaves(String leaveType, String calYear) {
		List<EmployeeLeaves> list = new ArrayList<>();
		try {
			String sqlQuery = " from EmployeeLeaves el WHERE el.requestType in (:requestTypes) AND el.active=:active"
					+ " AND el.startsAt>=:startsAt AND el.endsAt<=:endsAt";
			list = loadEmployeeLeaves(sqlQuery, leaveType, new java.sql.Timestamp(DateUtils.getDate(calYear+"-01-01").getTime()), 
					new java.sql.Timestamp(DateUtils.getDate(calYear+"-12-31").getTime()), null);
			for(EmployeeLeaves empLeaves: list){
				empLeaves.setNotificationToList(notificationDAO.loadNotificationsToByGenericId(empLeaves.getPk()));
			}
		} catch (Exception e) {
			logger.error("Unable to load all leaves. Exception is: "+e);
		}
		return list;
	}

	@Override
	public List<EmployeeLeaves> loadMyLeaves(String leaveType, String calYear, String employeeId) {
		List<EmployeeLeaves> list = new ArrayList<>();
		try {
			String sqlQuery = " from EmployeeLeaves el WHERE el.requestType in (:requestTypes) AND el.active=:active AND el.employeeId=:employeeId"
					+ " AND el.startsAt>=:startsAt AND el.endsAt<=:endsAt";
			list = loadEmployeeLeaves(sqlQuery, leaveType, new java.sql.Timestamp(DateUtils.getDate(calYear+"-01-01").getTime()), 
					new java.sql.Timestamp(DateUtils.getDate(calYear+"-12-31").getTime()), employeeId);
		} catch (HibernateException | ParseException e) {
			logger.error("Unable to load leaves for employee: '"+employeeId+"'. Exception is: "+e);
		}
		return list;
	}

	@Override
	public List<EmployeeLeaves> loadLeaveReport(EmployeeLeaves employeeLeaves) {
		List<EmployeeLeaves> list = new ArrayList<>();
		try {
			String sqlQuery = " from EmployeeLeaves el WHERE el.requestType in (:requestTypes) AND el.active=:active AND el.employeeId=:employeeId"
					+ " AND el.startsAt>=:startsAt AND el.endsAt<=:endsAt";
			list = loadEmployeeLeaves(sqlQuery, employeeLeaves.getRequestType(), new java.sql.Timestamp(employeeLeaves.getStartsAt().getTime()), 
					new java.sql.Timestamp(employeeLeaves.getEndsAt().getTime()), employeeLeaves.getEmployeeId().toString());
		} catch (Exception e) {
			logger.error("Unable to load leaves for employee: '"+employeeLeaves.getEmployeeId()+"'. Exception is: "+e);
		}
		return list;
	}
	
	@Override
	public Long getLeaveBalance(String leaveType, String calYear, String calMonth,
			String employeeId, int totalLeaves) {
		Session session = sessionFactory.openSession();
		long leaveBalance = (long)(PerfHrConstants.LEAVE_PER_MONTH*(Long.parseLong(calMonth))*PerfHrConstants.WORK_HOURS);
		try {
			String sqlQuery = "SELECT SUM(el.hours) from EmployeeLeaves el WHERE el.requestType in (:requestTypes) AND el.active=:active AND el.employeeId=:employeeId"
					+ " AND el.startsAt>=:startsAt AND el.endsAt<=:endsAt";
			Query query = session.createQuery(sqlQuery);
			List<String> leaveTypeList = new ArrayList<>();
			if(leaveType.equals(LeaveType.PTO.getLeaveType())){
				leaveTypeList.add(LeaveType.PTO.getLeaveType());
				leaveTypeList.add(LeaveType.UNPLANNED_PTO.getLeaveType());
			} else {
				leaveTypeList.add(LeaveType.WFH.getLeaveType());
			}
			query.setParameterList("requestTypes", leaveTypeList);
			query.setParameter("employeeId", Long.parseLong(employeeId));
			query.setParameter("active", PerfHrConstants.ACTIVE);
			query.setParameter("startsAt", new java.sql.Timestamp(DateUtils.getDate(calYear+"-01-01").getTime()));
			query.setParameter("endsAt", new java.sql.Timestamp(DateUtils.getDate(calYear+"-"+(Integer.parseInt(calMonth))+"-30").getTime()));
			leaveBalance = leaveBalance - ((Long) query.uniqueResult());
		} catch (Exception e) {
			logger.error("Unable to get leave balance for employee: '"+employeeId+"'. Exception is: "+e);
		} finally {
			session.close();
		}
		return leaveBalance;
	}
	
	@Override
	public EmployeeLeaves applyLeave(EmployeeLeaves employeeLeaves, String userId) {
		Session session = sessionFactory.openSession();
		EmployeeLeaves empLeaves = new EmployeeLeaves();
		try{
			Transaction tx = session.beginTransaction();

			Employee employee = employeeDAO.loadById(userId);
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
    		
			session.save(empLeaves);
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
	    		if(!notificationDAO.saveNotification(notification)){
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
		} catch(Exception e){
			logger.error("Unable to apply Leave: "+employeeLeaves.getTitle()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return empLeaves;
	}

	@Override
	public boolean updateLeave(EmployeeLeaves employeeLeaves, String userId) {
		boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			Employee employee = employeeDAO.loadById(userId);
    		employeeLeaves.setHours(getHours(employeeLeaves));
    		employeeLeaves.setModifiedBy(employee.getPk());
			employeeLeaves.setDtModified(new Date());
			session.merge(employeeLeaves);

			Map<Long, Employee> notifyMap = new HashMap<>();
			for(Employee emp : notificationDAO.loadNotificationsToByGenericId(employeeLeaves.getPk())){
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
		    		notificationDAO.saveNotification(notification);
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
			returnVal = true;
		} catch(Exception e){
			logger.error("Unable to update leave: "+employeeLeaves.getTitle()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
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
	public boolean deleteLeave(EmployeeLeaves employeeLeaves, String userId) {
		boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			employeeLeaves.setActive(PerfHrConstants.INACTIVE);
			employeeLeaves.setDtModified(new Date());
			session.merge(employeeLeaves);
			tx.commit();
			
			List<String> recipientList = new ArrayList<>();
			for(Employee emp : notificationDAO.loadNotificationsToByGenericId(employeeLeaves.getPk())){
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
			
			returnVal = true;
		} catch(Exception e){
			logger.error("Unable to delete  employee Leaves: "+employeeLeaves.getTitle()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
	}

	@Override
	public EmployeeLeaves loadLeaveById(String leaveId) {
		Session session = sessionFactory.openSession();
		EmployeeLeaves employeeLeave = new EmployeeLeaves();
		try{
			employeeLeave = (EmployeeLeaves)session.get(EmployeeLeaves.class, Long.parseLong(leaveId));
		} catch(Exception e){
			logger.error("Unable to load leave Id:"+leaveId+" Exception is: "+e);
		} finally {
			session.close();	
		}
		return employeeLeave;
	}

}