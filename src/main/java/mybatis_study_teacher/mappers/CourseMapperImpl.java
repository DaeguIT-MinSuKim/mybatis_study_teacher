package mybatis_study_teacher.mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;

import mybatis_study_teacher.dto.Course;
import mybatis_study_teacher.dto.CourseStat;

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

	@Override
	public List<Course> selectTrimCourses(Map<String, Object> map) {
		return sqlSession.selectList(namespace + ".selectTrimCourses", map);
	}

	@Override
	public List<Course> selectCoursesForeachByTutors(Map<String, Object> map) {
		return sqlSession.selectList(namespace + ".selectCoursesForeachByTutors", map);
	}

	@Override
	public int insertCourses(Map<String, Object> map) {
		return sqlSession.insert(namespace + ".insertCourses", map);
	}

	@Override
	public int deleteCourses(Map<String, Object> map) {
		return sqlSession.delete(namespace + ".deleteCourses", map);
	}

 
	@Override
	public Map<String, Object> getCourseCountByTutor(Map<String, Object> param) {
		Map<String, Object> map = new HashMap<>();
	    ResultHandler<CourseStat> resultHandler = new ResultHandler<CourseStat>() {
	        @Override
	        public void handleResult(ResultContext<? extends CourseStat> resultContext) {
	            CourseStat state = resultContext.getResultObject();
	            map.put(state.getTutor(), state.getTotal());
	        }
	    };
	    sqlSession.select(namespace + ".getCourseCountByTutor", param, resultHandler);
        return map;
	}

	@Override
	public Map<String, Object> getCourseCountByTutor2(Map<String, Object> param) {
		Map<String, Object> map = new HashMap<>();
	    ResultHandler<CourseStat> resultHandler = new ResultHandler<CourseStat>() {
	        @Override
	        public void handleResult(ResultContext<? extends CourseStat> resultContext) {
	            CourseStat state = resultContext.getResultObject();
	            map.put(state.getTutor(), state.getTotal());
	        }
	    };
	    sqlSession.select(namespace + ".getCourseCountByTutor2", param, resultHandler);
        return map;
	}

	@Override
	public CourseStat getCourseCountByTutor3(int param) {
		return sqlSession.selectOne(namespace + ".getCourseCountByTutor3", param);
	}

	@Override
	public int insertCourse(Course course) {
		return sqlSession.insert(namespace + ".insertCourse", course);
	}

	@Override
	public int deleteCourse(int courseId) {
		return sqlSession.delete(namespace + ".deleteCourse", courseId) ;
	}

}
