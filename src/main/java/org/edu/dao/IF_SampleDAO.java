package org.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.edu.vo.MemberVO;

/*
 *    Mapper 인터페이스는 기존의 DAO 인터페이스와 동일함.
 *    가장 큰 차이는 인터페이스의 구현을 mybatis-spring 에서 자동으로
 *    생성함.
 */
public interface IF_SampleDAO {
	// 회원정보 입력-조회-수정-삭제 메서드 명세 *학생추가부분(아래)
	public void insertMember(MemberVO vo) throws Exception;
	public List<MemberVO> selectMember() throws Exception;
	public void updateMember(MemberVO vo) throws Exception;
	public void deleteMember(String userid) throws Exception;
	
}
