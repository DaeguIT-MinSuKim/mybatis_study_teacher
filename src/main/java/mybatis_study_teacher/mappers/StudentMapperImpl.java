package mybatis_study_teacher.mappers;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis_study_teacher.dto.Student;

public class StudentMapperImpl implements StudentMapper {
	private static final StudentMapperImpl instance = new StudentMapperImpl();

	private final String namespace = "mybatis_study_teacher.mappers.StudentMapper";
	private SqlSession sqlSession;

	private StudentMapperImpl() {}

	public static StudentMapperImpl getInstance() {
		return instance;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Student selectStudentByNo(Student student) {
		return sqlSession.selectOne(namespace + ".selectStudentByNo", student);
	}

	@Override
	public Student selectStudentByNoWithResultMap(Student student) {
		return sqlSession.selectOne(namespace + ".selectStudentByNoWithResultMap", student);
	}

	@Override
	public List<Student> selectStudentByAll() {
		return sqlSession.selectList(namespace + ".selectStudentByAll");
	}

	@Override
	public int insertStudent(Student student) {
		return sqlSession.insert(namespace + ".insertStudent", student);
	}

	@Override
	public int deleteStudent(int id) {
		return sqlSession.delete(namespace+".deleteStudent", id);
	}

	@Override
	public int updateStudent(Student student) {
		return sqlSession.update(namespace + ".updateStudent", student);
	}

}
