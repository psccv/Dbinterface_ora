package org.edu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.edu.vo.MemberVO;
import org.springframework.stereotype.Repository;

@Repository
public class SampleDAOImpl implements IF_SampleDAO {
	private static String mapperQuery = "org.edu.dao.IF_SampleDAO";
	@Inject
	private SqlSession sqlSession;
	//오바라이드-다형성
	@Override
	public void insertMember(MemberVO vo) throws Exception {
		//*학생작업
		sqlSession.insert(mapperQuery + ".insertMember", vo);
	}

	@Override
	public List<MemberVO> selectMember() throws Exception {
		return sqlSession.selectList(mapperQuery + ".selectMember");//*학생작업
	}

	@Override
	public void updateMember(MemberVO vo) throws Exception {
		//*학생작업
		sqlSession.update(mapperQuery + ".updateMember", vo);
	}

	@Override
	public void deleteMember(String userid) throws Exception {
		//*학생작업	
		sqlSession.delete(mapperQuery + ".deleteMember", userid);
	}

}
