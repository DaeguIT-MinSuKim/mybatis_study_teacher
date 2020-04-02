package mybatis_study_teacher.mappers;

import mybatis_study_teacher.dto.Tutor;

public interface TutorMapper {
	Tutor selectTutorByTutorId(Tutor tutor);
}
