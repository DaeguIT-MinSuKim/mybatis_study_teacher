package mybatis_study_teacher.mappers;

import java.util.List;
import java.util.Map;

import mybatis_study_teacher.dto.Student;

public interface StudentMapper {
	//typeHandler 사용
	Student selectStudentByNo(Student student);
	//typeHandler를 사용하지 않음
	Student selectStudentByNoWithResultMap(Student student);
	
	List<Student> selectStudentByAll();
	
	int insertStudent(Student student);
	int deleteStudent(int id);
	int updateStudent(Student student);

	//ResultMap
	List<Student> selectStudentByAllForResutlMap();
	//hasMap
	List<Map<String, Object>> selectStudentByAllForHashMap();
	
	/* 내포된 결과매핑(ResultMap)을 사용한 일대일 매핑 */
	Student selectStudentByNoAssociation(Student student);

	/* enum 타입 다루기 */
	int insertEnumStudent(Student student);
}
