package mybatis_study_teacher.mappers;

import mybatis_study_teacher.dto.UserPic;

public interface UserPicMapper {
	int insertUserPic(UserPic userPic);
    UserPic getUserPic(int id);
}
