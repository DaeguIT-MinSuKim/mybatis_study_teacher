package mybatis_study_teacher.mappers;

import java.util.List;
import java.util.Map;

import mybatis_study_teacher.dto.Course;

public interface CourseMapper {
	List<Course> selectCoursesByCondition(Map<String, Object> map);
	List<Course> selectCaseCourses(Map<String, Object> map);
	List<Course> selectWhereCourses(Map<String, Object> map);
	List<Course> selectTrimCourses(Map<String, Object> map);

}
