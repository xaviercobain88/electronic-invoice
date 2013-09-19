/*
 */
package com.stf.persistence.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.stf.persistence.entity.Employee;

/**
 * @author xavier
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class EmployeeDao extends GenericDao<Employee, Integer> {

	public EmployeeDao() {
		super(Employee.class);

	}

}
