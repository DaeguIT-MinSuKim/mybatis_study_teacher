package mybatis_study_teacher.mappers;

import java.util.List;
import java.util.Map;

import mybatis_study_teacher.dto.Course;
import mybatis_study_teacher.dto.CourseStat;

public interface CourseMapper {
	List<Course> selectCoursesByCondition(Map<String, Object> map);
	List<Course> selectCaseCourses(Map<String, Object> map);
	List<Course> selectWhereCourses(Map<String, Object> map);
	List<Course> selectTrimCourses(Map<String, Object> map);
	
	int insertCourse(Course course);
	int deleteCourse(int courseId);
	
	/* foreach */
	List<Course> selectCoursesForeachByTutors(Map<String, Object> map);
	int insertCourses(Map<String, Object> map);
	int deleteCourses(Map<String, Object> map);
	
	/* procedure */
	Map<String, Object> getCourseCountByTutor(Map<String, Object> param);
	Map<String, Object> getCourseCountByTutor2(Map<String, Object> param);
	CourseStat getCourseCountByTutor3(int param);
}
