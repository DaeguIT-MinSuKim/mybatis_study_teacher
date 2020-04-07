package mybatis_study_teacher.service;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import mybatis_study_teacher.AbstractTest;
import mybatis_study_teacher.dto.Course;
import mybatis_study_teacher.dto.Tutor;

public class CourseUiServiceTest extends AbstractTest{
	private static CourseUiService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new CourseUiService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}

	private Course getCourse(int courseId, String name, String description, Date startDate, Date endDate, int tutorId) {
		Course course =  new Course();
		course.setCourseId(courseId);
		course.setName(name);
		course.setDescription(description);
		course.setStartDate(startDate);
		course.setEndDate(endDate);
		course.setTutorId(tutorId);
		return course;
	}
	
	@Test(expected=RuntimeException.class)
    public void test1JoinNewTutorAndCourseFailTutor() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        Tutor tutor = new Tutor();
        tutor.setTutorId(4);
        tutor.setName("kim");
        tutor.setEmail("test@test.co.kr");
    
        Course course = getCourse(8, "Python", "Programming", new Date(), new Date(), 4);    
        service.joinNewTutorAndCourse(tutor, course);
    }

	@Test(expected=RuntimeException.class)
    public void test2JoinNewTutorAndCourseFailCourse() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        Tutor tutor = new Tutor();
        tutor.setTutorId(6);
        tutor.setName("kim");
        tutor.setEmail("test@test.co.kr");
    
        Course course = getCourse(2, "Python", "Programming", new Date(), new Date(), 4);
    
        service.joinNewTutorAndCourse(tutor, course);
    }

    @Test
    public void test3JoinNewTutorAndCourseSuccess() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        Tutor tutor = new Tutor();
        tutor.setTutorId(6);
        tutor.setName("kim");
        tutor.setEmail("test@test.co.kr");
    
        Course course = getCourse(8, "Python", "Programming", new Date(), new Date(), 6);
    
        service.joinNewTutorAndCourse(tutor, course);
    }	

    @Test(expected=RuntimeException.class)
    public void test4RemoveTutorAndCourseFailTutor() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        service.removeTutorAndCourse(10, 8);
    }

    @Test(expected=RuntimeException.class)
    public void test5RemoveTutorAndCourseFailCourse() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        service.removeTutorAndCourse(6, 10);
    }

    @Test
    public void test6RemoveTutorAndCourseSuccess() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        service.removeTutorAndCourse(6, 8);
    }

}
