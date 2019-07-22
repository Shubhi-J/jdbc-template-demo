package com.stackroute;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactImpl {

    // objects for jdbcTemplate and dataSource
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    // getters and setters
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
       }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // method to create a new table
    public void createContactTable(){
        String sql="CREATE TABLE Contact(Email VARCHAR(50),Name VARCHAR(50),Phone VARCHAR(10),Address VARCHAR(50))";
        jdbcTemplate.execute(sql);
    }

    // method to insert a new contact
    public void insertContact(Contact contact){
        String sql="INSERT INTO Contact(Email,Name,Phone,Address) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{contact.getEmail(),contact.getName(),contact.getPhone(),contact.getAddress()});
    }

    // method to delete a contact
    public void deleteContact(String name){
        String sql="DELETE FROM Contact WHERE Name=?";
        jdbcTemplate.update(sql,name);
    }

    // method to update a contact
    public void updateContact(String email,String name){
        String sql="UPDATE Contact SET Email=? WHERE Name=?";
        jdbcTemplate.update(sql,new Object[]{email,name});
    }

    // method to get contact
    public Contact getContacts(String name){
        String sql="SELECT * FROM Contact WHERE Name=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name},new contactMapper());
    }

    // override the contactMapper
    private static final class contactMapper implements RowMapper<Contact>{

        @Override
        public Contact mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Contact contact=new Contact();
            contact.setEmail(resultSet.getString("email"));
            contact.setName(resultSet.getString("name"));
            contact.setPhone(resultSet.getString("phone"));
            contact.setAddress(resultSet.getString("address"));
            return contact;
        }
    }
}

