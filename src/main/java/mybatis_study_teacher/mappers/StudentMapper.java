package mybatis_study_teacher.mappers;

import mybatis_study_teacher.dto.Student;

public interface StudentMapper {
	Student selectStudentByNo(Student student);
}
