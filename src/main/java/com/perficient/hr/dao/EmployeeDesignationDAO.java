package com.perficient.hr.dao;

import java.util.List;

import com.perficient.hr.form.JobTitle;

public interface EmployeeDesignationDAO {

	public List<JobTitle> loadBySbu(String stDate, String endDate,String sbu,String desingation);
	
}
