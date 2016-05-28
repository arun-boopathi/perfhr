package com.perficient.hr.dao;

import java.util.List;

import com.perficient.hr.model.Designations;

public interface DesignationsDAO {
	
	public List<Designations> loadDesignations();
	
	public Designations loadDesignationById(String designationId);
	
	public Designations addDesignation(Designations designation, String userId);
	
	public boolean updateDesignation(Designations designation, String userId);
	
	public boolean deleteDesignation(Designations designation, String userId);
	
}
