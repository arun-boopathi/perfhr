package com.perficient.hr.dao.impl;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.perficient.hr.dao.EmployeeDAO;
import com.perficient.hr.dao.EmployeeLeavesDAO;
import com.perficient.hr.model.Employee;
import com.perficient.hr.model.EmployeeLeaves;
import com.perficient.hr.model.Notification;
import com.perficient.hr.model.type.LeaveType;
import com.perficient.hr.model.type.NotificationStatusType;
import com.perficient.hr.model.type.Notificationtype;
import com.perficient.hr.utils.DateUtils;
import com.perficient.hr.utils.PerfHrConstants;

@Repository("employeeLeavesDAO")
public class EmployeeLeavesDAOImpl implements EmployeeLeavesDAO {

	protected Logger logger = LoggerFactory.getLogger(EmployeeLeavesDAOImpl.class);
	
	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
    }
   
    protected Session getSession(){
       return sessionFactory.openSession();
    }

    @Autowired
    EmployeeDAO employeeDAO;
    
	@Override
	public boolean pasrsePtoDocument(String fileName) {
		boolean returnVal = false;
		Session session = sessionFactory.openSession();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			Transaction tx = session.beginTransaction();
			FileInputStream fis = new FileInputStream(fileName);
	        Workbook workbook = new XSSFWorkbook(fis);
	        int numberOfSheets = workbook.getNumberOfSheets();
	        for(int i=0; i < numberOfSheets; i++){
	        	Sheet sheet = workbook.getSheetAt(i);
	        	Iterator<Row> rowIterator = sheet.iterator();
	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
	                String leaveType = row.getCell(11).toString();
	                if(leaveType.trim().length() != 0 && row.getCell(17).toString().trim().length() != 0
	                		&& row.getCell(31).toString() != "0"){
	                	if(leaveType.equals(LeaveType.PTO.getLeaveType()) || leaveType.equals(LeaveType.UNPLANNED_PTO.getLeaveType())){
	                		String sqlQuery =" from Employee as o where o.employeeId=:employeeId";
	                		Query query = session.createQuery(sqlQuery);
	                		query.setParameter("employeeId", row.getCell(17).toString());
	                		Employee employee = (Employee) query.uniqueResult();
	                		if(employee != null) {
	                			EmployeeLeaves employeeLeaves = new EmployeeLeaves();
	                			employeeLeaves.setEmployeeId(employee.getPk());
	                			employeeLeaves.setRequestType(row.getCell(11).toString());
		                		employeeLeaves.setComments(row.getCell(35).toString());
		                		int hours = (Math.round(Float.parseFloat(row.getCell(31).toString())) < 4) ? 4:8;
		                		Date dt = sdf.parse(row.getCell(28).toString());
		                		employeeLeaves.setTitle(row.getCell(18).toString()+" - "+leaveType);
		                		employeeLeaves.setStartsAt(dt);
		                		employeeLeaves.setEndsAt(dt);
		                		employeeLeaves.setHours(hours);
		                		employeeLeaves.setDtCreated(new Date());
		                		employeeLeaves.setDtModified(new Date());
		                		employeeLeaves.setCreatedBy(employee.getPk());
		                		employeeLeaves.setModifiedBy(employee.getPk());
		                		
		                		session.save(employeeLeaves);
		                		
		                		Notification notification = new Notification();
		                		notification.setIdGeneric(employeeLeaves.getPk());
		                		notification.setNotificationTo((long)1);
		                		notification.setNotificationStatus(NotificationStatusType.APPROVED.getNotificationStatusType());
		                		notification.setNotificationType(Notificationtype.PTO.getLeaveType());
		                		notification.setDtCreated(new Date());
		                		notification.setDtModified(new Date());
		                		notification.setCreatedBy(employee.getPk());
		                		notification.setModifiedBy(employee.getPk());
		                		session.save(notification);
	                		} else {
	                			logger.info("Employee with PIN not found: "+row.getCell(17).toString());
	                		}
	                	}
	                }
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

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeLeaves> loadAllLeaves(String leaveType, String calYear) {
		Session session = sessionFactory.openSession();
		List<EmployeeLeaves> list = new ArrayList<EmployeeLeaves>();
		try {
			String sqlQuery = " from EmployeeLeaves el WHERE el.requestType in (:requestTypes) AND el.active=:active"
					+ " AND el.startsAt>=:startsAt AND el.endsAt<=:endsAt";
			Query query = session.createQuery(sqlQuery);
			List<String> leaveTypeList = new ArrayList<String>();
			if(leaveType.equals(LeaveType.PTO.getLeaveType())){
				leaveTypeList.add(LeaveType.PTO.getLeaveType());
				leaveTypeList.add(LeaveType.UNPLANNED_PTO.getLeaveType());
			} else {
				leaveTypeList.add(LeaveType.WFH.getLeaveType());
			}
			query.setParameterList("requestTypes", leaveTypeList);
			query.setParameter("active", PerfHrConstants.ACTIVE);
			query.setParameter("startsAt", new java.sql.Timestamp(DateUtils.getDate(calYear+"-01-01").getTime()));
			query.setParameter("endsAt", new java.sql.Timestamp(DateUtils.getDate(calYear+"-12-31").getTime()));
			list = query.list();
			session.close();
		} catch (HibernateException | ParseException e) {
			logger.error("Unable to load all leaves. Exception is: "+e);
		}
		return list;
	}

	@Override
	public EmployeeLeaves applyLeave(EmployeeLeaves employeeLeaves, String userId) {
		Session session = sessionFactory.openSession();
		EmployeeLeaves empLeaves = new EmployeeLeaves();
		try{
			Transaction tx = session.beginTransaction();

			Employee employee = employeeDAO.loadById(userId);
			empLeaves.setEmployeeId(employee.getPk());
			empLeaves.setRequestType(employeeLeaves.getRequestType());
			empLeaves.setComments(employeeLeaves.getComments());
    		empLeaves.setTitle(employeeLeaves.getTitle());
    		empLeaves.setStartsAt(employeeLeaves.getStartsAt());
    		empLeaves.setEndsAt(employeeLeaves.getEndsAt());
    		empLeaves.setDtFromHalf(employeeLeaves.getDtFromHalf());
    		empLeaves.setDtEndHalf(employeeLeaves.getDtEndHalf());
    		empLeaves.setHours(getHours(employeeLeaves));
    		empLeaves.setDtCreated(new Date());
    		empLeaves.setDtModified(new Date());
    		empLeaves.setCreatedBy(employee.getPk());
    		empLeaves.setModifiedBy(employee.getPk());
    		
			session.save(empLeaves);
			
			Notification notification = new Notification();
    		notification.setIdGeneric(empLeaves.getPk());
    		notification.setNotificationTo(employee.getSuperviser());
    		notification.setNotificationStatus(NotificationStatusType.SUBMITTED.getNotificationStatusType());
    		notification.setNotificationType(Notificationtype.WFH.getLeaveType());
    		notification.setDtCreated(new Date());
    		notification.setDtModified(new Date());
    		notification.setCreatedBy(employee.getPk());
    		notification.setModifiedBy(employee.getPk());
    		session.save(notification);
    		
			tx.commit();
		} catch(Exception e){
			logger.error("Unable to apply WFH: "+employeeLeaves.getTitle()+" Exception is: "+e);
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
    		employeeLeaves.setHours(getHours(employeeLeaves));
			employeeLeaves.setDtModified(new Date());
			session.merge(employeeLeaves);
			tx.commit();
			returnVal = true;
		} catch(Exception e){
			logger.error("Unable to update designation: "+employeeLeaves.getTitle()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
	}
	
	private int getHours(EmployeeLeaves employeeLeaves){
		int hours = (Days.daysBetween(new DateTime(employeeLeaves.getStartsAt()), 
				new DateTime(employeeLeaves.getEndsAt())).getDays()+1)*8;
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
			returnVal = true;
		} catch(Exception e){
			logger.error("Unable to update designation: "+employeeLeaves.getTitle()+" Exception is: "+e);
		} finally{
			session.close();	
		}
		return returnVal;
	}

	@Override
	public EmployeeLeaves loadLeaveById(String leaveId) {
		Session session = sessionFactory.openSession();
		EmployeeLeaves employeeLeave = (EmployeeLeaves)session.get(EmployeeLeaves.class, Long.parseLong(leaveId));
		session.close();
		return employeeLeave;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeLeaves> loadMyLeaves(String leaveType, String calYear, String employeeId) {
		Session session = sessionFactory.openSession();
		List<EmployeeLeaves> list = new ArrayList<EmployeeLeaves>();
		try {
			String sqlQuery = " from EmployeeLeaves el WHERE el.requestType in (:requestTypes) AND el.active=:active AND el.employeeId=:employeeId"
					+ " AND el.startsAt>=:startsAt AND el.endsAt<=:endsAt";
			Query query = session.createQuery(sqlQuery);
			List<String> leaveTypeList = new ArrayList<String>();
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
			query.setParameter("endsAt", new java.sql.Timestamp(DateUtils.getDate(calYear+"-12-31").getTime()));
			list = query.list();
			session.close();
		} catch (HibernateException | ParseException e) {
			logger.error("Unable to load leaves for employee: '"+employeeId+"'. Exception is: "+e);
		}
		return list;
	}

	@Override
	public Long getLeaveBalance(String leaveType, String calYear,
			String employeeId, int totalLeaves) {
		Session session = sessionFactory.openSession();
		long leaveBalance = totalLeaves;
		try {
			String sqlQuery = "SELECT SUM(el.hours) from EmployeeLeaves el WHERE el.requestType in (:requestTypes) AND el.active=:active AND el.employeeId=:employeeId"
					+ " AND el.startsAt>=:startsAt AND el.endsAt<=:endsAt";
			Query query = session.createQuery(sqlQuery);
			List<String> leaveTypeList = new ArrayList<String>();
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
			query.setParameter("endsAt", new java.sql.Timestamp(DateUtils.getDate(calYear+"-12-31").getTime()));
			leaveBalance = leaveBalance - ((Long) query.uniqueResult())/8;
		} catch (HibernateException | ParseException e) {
			logger.error("Unable to load leaves for employee: '"+employeeId+"'. Exception is: "+e);
		} catch (Exception e) {
			logger.error("Unable to load leaves for employee: '"+employeeId+"'. Exception is: "+e);
		}
		return leaveBalance;
	}
}