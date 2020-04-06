package mybatis_study_teacher.mappers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import mybatis_study_teacher.AbstractTest;
import mybatis_study_teacher.dto.Gender;
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
    public void test04InsertStudent() {
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

    @Test
    public void test05DeleteStudent(){
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        int deleteStudent = dao.deleteStudent(3);
        Assert.assertSame(1, deleteStudent);
    }

    @Test
    public void test06UpdateStudent(){
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Student student = new Student();
        student.setStudId(1);
        student.setName("Timothy");
        student.setEmail("test@test.co.kr");
        student.setPhone(new PhoneNumber("987-654-3211"));
        student.setDob(new Date());
            
        int result = dao.updateStudent(student);
        Assert.assertSame(1, result);
                    
        student.setEmail("timothy@gmail.com");
        student.setPhone(new PhoneNumber("123-123-1234"));
        student.setDob(new GregorianCalendar(1988, 04, 25).getTime());
        result = dao.updateStudent(student);
        Assert.assertSame(1, result);
    }

    @Test
    public void test07SelectStudentByAllForResutlMap(){
       log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
       List<Student> lists = dao.selectStudentByAllForResutlMap();
       Assert.assertNotNull(lists);
       for(Student std : lists) {
           log.debug(std.toString());
       }
    }

    @Test
    public void test08SelectStudentByAllForHashMap(){
       log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
       List<Map<String,Object>> lists = dao.selectStudentByAllForHashMap();
       Assert.assertNotNull(lists);
       for(Map<String, Object> map : lists) {
           for(Entry<String, Object> e : map.entrySet()) {
               log.debug(String.format("%s -> %s", e.getKey(), e.getValue()));
           }
       }
    }

    @Test
    public void test09SelectStudentByNoAssociation() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        Student student = new Student();
        student.setStudId(1);
        Student seletedStd = dao.selectStudentByNoAssociation(student);
        Assert.assertNotNull(seletedStd);
        log.debug(seletedStd.toString());
    }

    @Test
    public void test10InsertEnumStudent() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");

        Calendar newDate = GregorianCalendar.getInstance();
        newDate.set(1990, 2, 28);
        Student student = new Student();
        student.setStudId(3);
        student.setName("test");
        student.setEmail("test@test.co.kr");
        student.setDob(newDate.getTime());
        student.setPhone(new PhoneNumber("010-1234-1234"));
        student.setGender(Gender.FEMALE);
        int res = dao.insertEnumStudent(student);
        Assert.assertEquals(1, res);
        log.debug(student.toString());
        
        student.setStudId(4);
        student.setName("test4");
        student.setEmail("test4@test.co.kr");
        student.setDob(newDate.getTime());
        student.setPhone(new PhoneNumber("010-1234-1234"));
        student.setGender(Gender.MALE);
        int res1 = dao.insertEnumStudent(student);
        Assert.assertEquals(1, res1);
        log.debug(student.toString());
        dao.deleteStudent(3);
        dao.deleteStudent(4);
    }

    
    @Test
    public void test11SelectStudentByMap() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        Map<String, String> maps = new HashMap<>();
        maps.put("name", "Timothy");
        maps.put("email", "timothy@gmail.com");
        Student student = dao.selectStudentByMap(maps);
        Assert.assertNotNull(student);
        log.debug(student.toString());

        maps.remove("email");
        student = dao.selectStudentByMap(maps);
        log.debug(student.toString());
       
        maps.clear();
        maps.put("email", "timothy@gmail.com");
        student = dao.selectStudentByMap(maps);
        log.debug(student.toString()); 
    }

    @Test
    public void test12SelectAllStudentByMap() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        Map<String, String> maps = new HashMap<>();
        maps.put("name", "Timothy");
        maps.put("email", "timothy@gmail.com");
        List<Student> lists = dao.selectAllStudentByMap(maps);
        Assert.assertNotNull(lists);
        for(Student std : lists) {
            log.debug(std.toString());
        }

        maps.remove("email");
        lists = dao.selectAllStudentByMap(maps);
        for(Student std : lists) {
            log.debug(std.toString());
        }
       
        maps.clear();
        maps.put("email", "timothy@gmail.com");
        lists = dao.selectAllStudentByMap(maps);
        for(Student std : lists) {
            log.debug(std.toString());
        }
        
        maps.clear();
        lists = dao.selectAllStudentByMap(maps);
        for(Student std : lists) {
            log.debug(std.toString());
        }
    }
    
    @Test
    public void test13SelectStudentForMap() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        Student student = new Student();
        student.setStudId(1);
        Map<Integer, Student> map = dao.selectStudentForMap(student);
        Assert.assertNotNull(map);
            
        for(Entry<Integer, Student>entry : map.entrySet()){
            System.out.printf("key(%s) - value(%s)%n", entry.getKey(), entry.getValue());
        }
        
        map.clear();
        map = dao.selectStudentForMap(null);
            
        for(Entry<Integer, Student>entry : map.entrySet()){
            System.out.printf("key(%s) - value(%s)%n", entry.getKey(), entry.getValue());
        }
    } 

    @Test
    public void test14UpdateSetStudent(){
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        Student student = new Student();
        student.setStudId(1);
        student.setPhone(new PhoneNumber("987-654-3211"));
        student.setDob(new Date());
            
        int result = dao.updateSetStudent(student);
        Assert.assertSame(1, result);
            
        student.setPhone(new PhoneNumber("123-123-1234"));
        student.setDob(new GregorianCalendar(1988, 04, 25).getTime());
            
        result = dao.updateSetStudent(student);
        Assert.assertSame(1, result);
    }

}
