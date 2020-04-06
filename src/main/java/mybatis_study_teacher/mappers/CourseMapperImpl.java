package mybatis_study_teacher.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import mybatis_study_teacher.dto.Course;

public class CourseMapperImpl implements CourseMapper {
	private static final CourseMapperImpl instance = new CourseMapperImpl();

	private final String namespace = "mybatis_study_teacher.mappers.CourseMapper";
	private SqlSession sqlSession;

	private CourseMapperImpl() {}

	public static CourseMapperImpl getInstance() {
		return instance;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	
	@Override
	public List<Course> selectCoursesByCondition(Map<String, Object> map) {
		return sqlSession.selectList(namespace + ".selectCoursesByCondition", map);
	}

	@Override
	public List<Course> selectCaseCourses(Map<String, Object> map) {
		return sqlSession.selectList(namespace + ".selectCaseCourses", map);
	}

	@Override
	public List<Course> selectWhereCourses(Map<String, Object> map) {
		return sqlSession.selectList(namespace + ".selectWhereCourses", map);
	}

}
