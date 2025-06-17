package com.ikenna.mysql.dao;

import com.ikenna.mysql.model.Employee;

import java.util.List;

public interface EmployeeDao {
    void save(Employee emp);
    Employee getById(int id);
    List<Employee> getAll();
    void update(Employee emp);
    void delete(int id);
}