import com.lab3.lab3.DAO.StudentDao;
import com.lab3.lab3.entity.Exam;
import com.lab3.lab3.entity.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StudentDaoTest {
    private StudentDao dao;
    private EntityManager em;

    @Before
    public void setup(){
        em = mock(EntityManager.class);
        dao = new StudentDao(em);
    }

    @After
    public void after(){
        dao = null;
    }

    @Test
    public void givenGetAll_whenCall_thenReturnStudents(){
        Query mockedQuery = mock(Query.class);
        when(em.createNamedQuery("Student.findAll")).thenReturn(mockedQuery);
        when(mockedQuery.getResultList()).thenReturn(new ArrayList<Student>());

        List<Student> students = dao.getAll();

        assertNotNull(students);
        assertTrue(students.isEmpty());
    }

    @Test
    public void givenAdd_whenCalled_thenShouldAdd(){
        List<Exam> exams = new ArrayList<Exam>(){{
            add(new Exam());
        }};
        Student student = new Student("Andra", exams);
        EntityTransaction transaction = mock(EntityTransaction.class);
        when(em.getTransaction()).thenReturn(transaction);

        dao.add(student);

        verify(em, times(1)).persist(student);
        verify(em, times(1)).flush();
        verify(em, times(1)).refresh(student);
        verify(transaction, times(1)).commit();
        verify(transaction, times(1)).begin();
    }

    @Test
    public void givenDelete_whenCalled_thenShouldDelete(){
        List<Exam> exams = new ArrayList<Exam>(){{
            add(new Exam());
        }};
        Student student = new Student("Andra", exams);
        EntityTransaction transaction = mock(EntityTransaction.class);
        when(em.getTransaction()).thenReturn(transaction);

        dao.delete(student);

        verify(em, times(1)).remove(student);
        verify(transaction, times(1)).commit();
        verify(transaction, times(1)).begin();
    }

    @Test
    public void givenUpdate_whenCalled_thenShouldUpdate(){
        List<Exam> exams = new ArrayList<Exam>(){{
            add(new Exam());
        }};
        Student student = new Student("Andra", exams);
        student.setStudentId(1);
        Student updatedStudent = new Student("New Andra", exams);
        updatedStudent.setStudentId(1);
        EntityTransaction transaction = mock(EntityTransaction.class);
        when(em.getTransaction()).thenReturn(transaction);
        when(em.find(Student.class, student.getStudentId())).thenReturn(student);

        dao.update(updatedStudent);

        assertEquals(student.getStudentId(), updatedStudent.getStudentId());
        assertEquals(student.getName(), updatedStudent.getName());
        assertEquals(student.getExams(), updatedStudent.getExams());
        verify(transaction, times(1)).begin();
        verify(transaction, times(1)).commit();
    }
}
