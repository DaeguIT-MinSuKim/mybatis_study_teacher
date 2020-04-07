package mybatis_study_teacher.service;

import org.apache.ibatis.session.SqlSession;

import mybatis_study_teacher.dto.Course;
import mybatis_study_teacher.dto.Tutor;
import mybatis_study_teacher.jdbc.MyBatisSqlSessionFactory;

public class CourseUiService {
	private String namespace_course = "mybatis_study_teacher.mappers.CourseMapper";
    private String namespace_tutor = "mybatis_study_teacher.mappers.TutorMapper";

    public void joinNewTutorAndCourse(Tutor tutor, Course course) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
        int res = 0;
        try {
            res += sqlSession.insert(namespace_tutor + ".insertTutor", tutor);
            res += sqlSession.insert(namespace_course + ".insertCourse", course);
            if (res == 2)
                sqlSession.commit();
            else
                throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw new RuntimeException(e.getCause());
        } finally {
            sqlSession.close();
        }
    }

    public void removeTutorAndCourse(int tutorId, int courseId) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
        int res = 0;
        try {
            res +=  sqlSession.delete(namespace_course + ".deleteCourse", courseId);
            res += sqlSession.delete(namespace_tutor + ".deleteTutor", tutorId);
            if (res == 2)
                sqlSession.commit();
            else
                throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw new RuntimeException(e.getCause());
        } finally {
            sqlSession.close();
        }
    }

}
