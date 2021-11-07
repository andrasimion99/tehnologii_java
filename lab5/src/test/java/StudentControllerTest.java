import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.lab3.lab3.controller.StudentController;
import com.lab3.lab3.entity.Exam;
import com.lab3.lab3.entity.Student;
import com.lab3.lab3.service.ExamService;
import com.lab3.lab3.service.StudentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentControllerTest {
    private StudentController controller;
    private StudentService studentService;
    private ExamService examService;

    @Before
    public void setup(){
        studentService = mock(StudentService.class);
        examService = mock(ExamService.class);

        controller = new StudentController(studentService, examService);
    }

    @After
    public void after(){
        controller = null;
    }

    @Test
    public void givenLoadStudent_whenIsCalled_thenPopulateStudents() throws SQLException {
        List<Student> students = new ArrayList<Student>() {
            {
                add(new Student("Andra", new ArrayList<>()));
            }
        };
        when(studentService.getStudents()).thenReturn(students);

        controller.loadStudents();

        assertEquals(students, controller.getStudents());
    }

    @Test
    public void givenAddStudent_whenValidData_thenAddStudent() {
        List<Exam> exams = new ArrayList<Exam>(){{
            add(new Exam());
            add(new Exam());
        }};
        when(examService.getExamsById(new ArrayList<Integer>(){{
            add(1);
            add(2);
        }})).thenReturn(exams);

        controller.addStudent("Andra", "1,2");

        verify(studentService, times(1)).addStudent(any(Student.class));
    }

    @Test
    public void givenDeleteStudent_whenValidData_thenDeleteStudent() {
        List<Exam> exams = new ArrayList<Exam>(){{
            add(new Exam());
            add(new Exam());
        }};
        Student student = new Student("Andra", exams);

        controller.deleteStudent(student);

        verify(studentService, times(1)).deleteStudent(student);
    }

    @Test
    public void givenUpdateStudent_whenValidData_thenUpdateStudent(){
        List<Exam> exams = new ArrayList<Exam>(){{
            add(new Exam());
            add(new Exam());
        }};
        Student student = new Student("Andra", exams);

        controller.updateStudent(student);

        verify(studentService, times(1)).updateStudent(student);
    }

}
