package com.ikenna.mysql.dao;

import com.ikenna.mysql.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImp implements EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Employee> rowMapper = (rs, rowNum) -> {
        Employee e = new Employee();
        e.setId(rs.getInt("id"));
        e.setName(rs.getString("name"));
        e.setEmail(rs.getString("email"));
        return e;
    };

    @Override
    public void save(Employee emp) {
        // Corrected: Don't insert ID if it's auto-incremented, and use the correct email
        String sql = "INSERT INTO employee (name, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, emp.getName(), emp.getEmail());
    }

    @Override
    public Employee getById(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Employee> getAll() {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void update(Employee emp) {
        String sql = "UPDATE employee SET name = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, emp.getName(), emp.getEmail(), emp.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
