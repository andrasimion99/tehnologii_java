package com.lab3.lab3.DAO;

import com.lab3.lab3.entity.Exam;
import com.lab3.lab3.entity.Student;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
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

    public List<Student> getAll() throws SQLException, NamingException {
        List<Student> students = new ArrayList<>();
        System.out.println("HERE get all");

        Connection conn = dataSource.getConnection();
        String query = "select * from students order by name";
        ResultSet result = conn.createStatement().executeQuery(query);

        while (result.next()) {
            int id = result.getInt("student_id");
            String name = result.getString("name");
            List<Exam> exams = new ArrayList<>();

            String exams_query = "select * from exam_and_student where student_id = ?";
            PreparedStatement statement = conn.prepareStatement(exams_query);
            statement.setInt(1, id);
            ResultSet examsResult = statement.executeQuery();

            while(examsResult.next()) {
                int examId = examsResult.getInt("exam_id");
                Exam exam = ExamDao.getInstance().getById(examId);
                exams.add(exam);
            }
            statement.close();
            examsResult.close();

            students.add(new Student(id, name, exams));
        }
        conn.close();
        result.close();

        return students;
    }

    public void add(Student student) throws SQLException {
        Connection conn = dataSource.getConnection();
        String query = "insert into students (name) values (?)";
        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, student.getName());
        statement.execute();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            student.setStudentId(generatedKeys.getInt(1));
        }

        String examQuery = "insert into exam_and_student (exam_id, student_id) value (?,?)";
        for(Exam exam: student.getExams()){
            PreparedStatement examStatement = conn.prepareStatement(examQuery);
            examStatement.setInt(1, exam.getExamId());
            examStatement.setInt(2, student.getStudentId());
            examStatement.execute();
            examStatement.close();
        }
        conn.close();
        statement.close();
    }

}
