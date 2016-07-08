package com.perficient.hr.dao;

import java.util.List;

import org.hibernate.Session;

import com.perficient.hr.model.Designations;

public interface DesignationsDAO {
	
	public List<Designations> loadDesignations(Session session) throws Exception;
	
	public Designations loadDesignationById(String designationId, Session session) throws Exception;
	
	public Designations loadDesignationByName(String designationName, Session session) throws Exception;
	
	public Designations addDesignation(Designations designation, Session session) throws Exception;
	
	public boolean updateDesignation(Designations designation, Session session) throws Exception;
	
}
