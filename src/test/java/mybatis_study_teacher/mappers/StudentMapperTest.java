package mybatis_study_teacher.mappers;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import mybatis_study_teacher.AbstractTest;
import mybatis_study_teacher.dto.PhoneNumber;
import mybatis_study_teacher.dto.Student;
import mybatis_study_teacher.jdbc.MyBatisSqlSessionFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentMapperTest extends AbstractTest{
    private static StudentMapperImpl dao;
    private static SqlSession sqlSession;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dao = StudentMapperImpl.getInstance();
        sqlSession = MyBatisSqlSessionFactory.openSession();
        dao.setSqlSession(sqlSession);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        dao = null;
        sqlSession.close();
    }

    @Test
    public void test01SelectStudentByNo() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Student student = new Student();
        student.setStudId(1);
        Student selectStudent = dao.selectStudentByNo(student);
        log.debug(selectStudent.toString());
        Assert.assertEquals(student.getStudId(), selectStudent.getStudId());
    }

    @Test
    public void test02SelectStudentByNoWithResultMap() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Student student = new Student();
        student.setStudId(1);
        Student selectStudent = dao.selectStudentByNoWithResultMap(student);
        log.debug(selectStudent.toString());
        Assert.assertEquals(student.getStudId(), selectStudent.getStudId());
    }
    
    @Test
    public void test03SelectStudentByAll() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

        List<Student> lists  = dao.selectStudentByAll();
        Assert.assertNotNull(lists);
        for(Student std : lists) {
            log.debug(std.toString());
        }
    }
    
    @Test
    public void testInsertStudent() {
        Calendar newDate = GregorianCalendar.getInstance();
        newDate.set(1990, 2, 28);
        Student student = new Student();
        student.setStudId(3);
        student.setName("홍길동3");
        student.setEmail("lee@test.co.kr");
        student.setPhone(new PhoneNumber("010-1234-1234"));
        student.setDob(newDate.getTime());
        int res = dao.insertStudent(student);
        Assert.assertEquals(1, res);
    }

}
