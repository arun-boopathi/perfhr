package com.perficient.hr.dao;

import java.util.List;

import com.perficient.hr.model.Designations;

public interface DesignationsDAO {

	
	public List<Designations> loadDesignations();
	public boolean addDesignation(Designations designation);
	
}
