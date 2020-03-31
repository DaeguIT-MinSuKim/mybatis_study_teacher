package mybatis_study_teacher.mappers;

import mybatis_study_teacher.dto.Student;

public interface StudentMapper {
	//typeHandler 사용
	Student selectStudentByNo(Student student);
	//typeHandler를 사용하지 않음
	Student selectStudentByNoWithResultMap(Student student);
}
