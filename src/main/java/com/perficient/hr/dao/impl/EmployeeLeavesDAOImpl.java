package com.perficient.hr.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import com.perficient.hr.model.type.NotificationType;
import com.perficient.hr.utils.DateUtils;
import com.perficient.hr.utils.PerfHrConstants;

@Repository("employeeLeavesDAO")
public class EmployeeLeavesDAOImpl implements EmployeeLeavesDAO {

	protected Logger logger = LoggerFactory.getLogger(EmployeeLeavesDAOImpl.class);

	private String employeeId = "employeeId";
	private String dateBetween = " AND el.startsAt>=:startsAt AND el.endsAt<=:endsAt";
	private String startDate = "-01-01";
	
	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;
	
	@Autowired
	EmployeeDAO employeeDAO;

    public void setSessionFactory(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
    }
   
    protected Session getSession(){
       return sessionFactory.openSession();
    }
    
	@Override
	public void saveLeave(Session session, Row row) throws ParseException{
		String leaveType = row.getCell(11).toString();
        String hours = row.getCell(31).toString();
        if((leaveType.equals(LeaveType.PTO.getLeaveType()) 
        		|| leaveType.equals(LeaveType.UNPLANNED_PTO.getLeaveType())) && !"0".equals(hours)){
    		String sqlQuery =" from Employee as o where o.employeeId=:employeeId";
    		Query query = session.createQuery(sqlQuery);
    		query.setParameter(employeeId, row.getCell(17).toString());
    		Employee employee = (Employee) query.uniqueResult();
    		if(employee != null) {
    			EmployeeLeaves employeeLeaves = new EmployeeLeaves();
    			employeeLeaves.setEmployeeId(employee.getPk());
    			employeeLeaves.setAppliedById(employee.getPk());
    			employeeLeaves.setRequestType(row.getCell(11).toString());
        		employeeLeaves.setComments(row.getCell(35).toString());
        		employeeLeaves.setTitle(row.getCell(18).toString()+" - "+leaveType);
        		employeeLeaves.setStartsAt(DateUtils.getDateByddMMMyyyy(row.getCell(28).toString()));
        		employeeLeaves.setEndsAt(DateUtils.getDateByddMMMyyyy(row.getCell(28).toString()));
        		employeeLeaves.setHours((Math.round(Float.parseFloat(hours)) < 4) ? 4:8);
        		employeeLeaves.setDtCreated(new Date());
        		employeeLeaves.setDtModified(new Date());
        		employeeLeaves.setCreatedBy(employee.getPk());
        		employeeLeaves.setModifiedBy(employee.getPk());
        		
        		session.save(employeeLeaves);
        		
        		Employee supervisor = employeeDAO.loadById(String.valueOf(employee.getSupervisor()), session);
        		
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
	private List<EmployeeLeaves> loadEmployeeLeaves(String sqlQuery, String leaveType, 
			Date startsAt, Date endsAt, String employeeId, Session session) {
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
			query.setParameter(employeeId, Long.parseLong(employeeId));
		query.setParameter("active", PerfHrConstants.ACTIVE);
		query.setParameter("startsAt", startsAt);
		query.setParameter("endsAt", endsAt);
		return query.list();
	}
	
	
	@Override
	public List<EmployeeLeaves> loadAllLeaves(String leaveType, String calYear, Session session) throws ParseException{
		String sqlQuery = " from EmployeeLeaves el WHERE el.requestType in (:requestTypes) AND el.active=:active"+ dateBetween;
		return loadEmployeeLeaves(sqlQuery, leaveType, new java.sql.Timestamp(DateUtils.getDate(calYear+startDate).getTime()), 
				new java.sql.Timestamp(DateUtils.getDate(calYear+"-12-31").getTime()), null, session);
	}

	@Override
	public List<EmployeeLeaves> loadMyLeaves(String leaveType, String calYear, String employeeId, Session session) throws ParseException{
		String sqlQuery = " from EmployeeLeaves el WHERE el.requestType in (:requestTypes) AND el.active=:active AND el.employeeId=:employeeId"+dateBetween;
		return loadEmployeeLeaves(sqlQuery, leaveType, new java.sql.Timestamp(DateUtils.getDate(calYear+startDate).getTime()), 
				new java.sql.Timestamp(DateUtils.getDate(calYear+"-12-31").getTime()), employeeId, session);
	}

	@Override
	public List<EmployeeLeaves> loadLeaveReport(EmployeeLeaves employeeLeaves, Session session){
		String sqlQuery = " from EmployeeLeaves el WHERE el.requestType in (:requestTypes) AND el.active=:active AND el.employeeId=:employeeId"+dateBetween;
		return loadEmployeeLeaves(sqlQuery, employeeLeaves.getRequestType(), new java.sql.Timestamp(employeeLeaves.getStartsAt().getTime()), 
				new java.sql.Timestamp(employeeLeaves.getEndsAt().getTime()), employeeLeaves.getEmployeeId().toString(), session);
	}
	
	@Override
	public Long getLeaveBalance(String leaveType, String calYear, String calMonth,
		String employeeId, int totalLeaves, Session session) throws ParseException{
		String sqlQuery = "SELECT SUM(el.hours) from EmployeeLeaves el WHERE el.requestType in (:requestTypes) AND el.active=:active AND el.employeeId=:employeeId"+dateBetween;
		Query query = session.createQuery(sqlQuery);
		List<String> leaveTypeList = new ArrayList<>();
		if(leaveType.equals(LeaveType.PTO.getLeaveType())){
			leaveTypeList.add(LeaveType.PTO.getLeaveType());
			leaveTypeList.add(LeaveType.UNPLANNED_PTO.getLeaveType());
		} else {
			leaveTypeList.add(LeaveType.WFH.getLeaveType());
		}
		query.setParameterList("requestTypes", leaveTypeList);
		query.setParameter(employeeId, Long.parseLong(employeeId));
		query.setParameter("active", PerfHrConstants.ACTIVE);
		query.setParameter("startsAt", new java.sql.Timestamp(DateUtils.getDate(calYear+startDate).getTime()));
		query.setParameter("endsAt", new java.sql.Timestamp(DateUtils.getDate(calYear+"-"+(Integer.parseInt(calMonth))+"-30").getTime()));
		return (Long) query.uniqueResult();
	}
	
	@Override
	public EmployeeLeaves saveLeave(EmployeeLeaves employeeLeaves, Session session){
		session.save(employeeLeaves);
		return employeeLeaves;
	}

	@Override
	public boolean updateLeave(EmployeeLeaves employeeLeaves, Session session){
		session.merge(employeeLeaves);
		return true;
	}
	
	@Override
	public EmployeeLeaves loadLeaveById(String leaveId, Session session){
		return (EmployeeLeaves)session.get(EmployeeLeaves.class, Long.parseLong(leaveId));
	}
}