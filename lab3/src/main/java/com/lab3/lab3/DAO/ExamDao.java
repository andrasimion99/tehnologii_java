package com.lab3.lab3.DAO;

import com.lab3.lab3.entity.Exam;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamDao {
    private static ExamDao instance;
    private DataSource dataSource;
    private String jndiName = "conn";

    public static ExamDao getInstance() throws NamingException {
        if (instance == null) {
            instance = new ExamDao();
        }
        return instance;
    }

    private ExamDao() throws NamingException {
        dataSource = getDataSource();
    }

    private DataSource getDataSource() throws NamingException {
        Context context = new InitialContext();
        return (DataSource) context.lookup(jndiName);
    }

    public List<Exam> getAll() throws SQLException {
        List<Exam> exams = new ArrayList<>();

        Connection conn = dataSource.getConnection();
        String query = "select * from exams order by starting_time";
        ResultSet result = conn.createStatement().executeQuery(query);

        while (result.next()) {
            int id = result.getInt("exam_id");
            String name = result.getString("name");
            Timestamp date = result.getTimestamp("starting_time");
            Integer duration= result.getInt("duration");

            exams.add(new Exam(id, name, new Date(date.getTime()), duration));
        }

        conn.close();
        result.close();

        return exams;
    }

    public void add(Exam exam) throws SQLException {
        Connection conn = dataSource.getConnection();
        String query = "insert into exams (name, starting_time, duration) values (?, ?, ?)";
//        Date date = new Date(exam.getStartingDate().getTime());
//        DateFormat df=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Date d = exam.getStartingDate();
//        df.format(d);
        System.out.println("HERE " + exam.getStartingDate());
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, exam.getName());
        statement.setTimestamp(2, new Timestamp(exam.getStartingDate().getTime()));
        statement.setInt(3, exam.getDuration());
        statement.execute();
        conn.close();
        statement.close();
    }
}
