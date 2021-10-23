package com.lab3.lab3.DAO;

import com.lab3.lab3.entity.Student;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private static StudentDao instance;
    private DataSource dataSource;
    private String jndiName = "conn";

    public static StudentDao getInstance() throws NamingException {
        if (instance == null) {
            instance = new StudentDao();
        }
        return instance;
    }

    private StudentDao() throws NamingException {
        dataSource = getDataSource();
    }

    private DataSource getDataSource() throws NamingException {
        Context context = new InitialContext();
        return (DataSource) context.lookup(jndiName);
    }

    public List<Student> getAll() throws SQLException {
        List<Student> students = new ArrayList<>();

        Connection conn = dataSource.getConnection();
        String query = "select * from students order by name";
        ResultSet result = conn.createStatement().executeQuery(query);

        while (result.next()) {
            int id = result.getInt("student_id");
            String name = result.getString("name");

            students.add(new Student(id, name));
        }

        conn.close();
        result.close();

        return students;
    }

    public void add(Student student) throws SQLException {
        Connection conn = dataSource.getConnection();
        String query = "insert into students (name) values (?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, student.getName());
        statement.execute();
        conn.close();
        statement.close();
    }
}
