package org.edu.service;

import java.util.List;

import org.edu.vo.MemberVO;

public interface IF_SampleService {
	// 회원정보 입력-조회-수정-삭제 메서드 명세
	public void insertMember(MemberVO vo) throws Exception;
	public List<MemberVO> selectMember() throws Exception;
	public void updateMember(MemberVO vo) throws Exception;
	public void deleteMember(String userid)throws Exception;
}
