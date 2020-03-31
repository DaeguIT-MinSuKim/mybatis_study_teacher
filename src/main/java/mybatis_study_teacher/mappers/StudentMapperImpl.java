package mybatis_study_teacher.mappers;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis_study_teacher.dto.Student;
import mybatis_study_teacher.jdbc.MyBatisSqlSessionFactory;

public class StudentMapperImpl implements StudentMapper {
	private static final StudentMapperImpl instance = new StudentMapperImpl();
	
	private final String namespace = "mybatis_study_teacher.mappers.StudentMapper";
	private SqlSession sqlSession;
	
	private StudentMapperImpl() {
		this.sqlSession = MyBatisSqlSessionFactory.openSession();
	}

	public static StudentMapperImpl getInstance() {
		return instance;
	}
	
	@Override
	public Student selectStudentByNo(Student student) {
		return sqlSession.selectOne(namespace + ".selectStudentByNo", student);
	}

	@Override
	public Student selectStudentByNoWithResultMap(Student student) {
		return sqlSession.selectOne(namespace +".selectStudentByNoWithResultMap", student);
	}

	@Override
	public List<Student> selectStudentByAll() {
		return sqlSession.selectList(namespace+".selectStudentByAll");
	}

}
