package org.edu.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.edu.dao.IF_SampleDAO;
import org.edu.service.IF_SampleService;
import org.edu.vo.MemberVO;
// import org.edu.dao.SampleSelectProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration //JUnit과 AOP동시사용 에러 처리를 위해서 추가
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class SampleMapperTest {
	
	/**
	 * hsql 사용시 아래 설정은 무시하셔도 됩니다.
	 * 실습시 Mysql 에 아래 3가지 쿼리를 실행 하고 작업 시작 합니다.
	 * CREATE SCHEMA `interface` DEFAULT CHARACTER SET utf8 ;
	 * create table member
	(
	 userid varchar(50) not null
	    ,userpw varchar(50) not null
	    ,username varchar(50) not null
	    ,email varchar(100)
	    ,regdate timestamp default now()
	    ,updatedate timestamp default now()
	    ,primary key(userid)
	)
	* INSERT INTO member VALUES ('user2','1234','kimilguk','user02@test.com',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
	
	* 오라클 실습시(아래)
	* create table member
	(
	 userid varchar(50) not null
	    ,userpw varchar(50) not null
	    ,username varchar(50) not null
	    ,email varchar(100)
	    ,regdate DATE DEFAULT SYSDATE NOT NULL
	    ,updatedate DATE DEFAULT SYSDATE NOT NULL
	    ,CONSTRAINT MEMBERS_PK PRIMARY KEY (userid)
	)	
	* INSERT INTO member VALUES ('user2','1234','kimilguk','user02@test.com',SYSDATE,SYSDATE);
	* 프로젝트폴더경로 DbInterface/src/main/webapp/WEB-INF/spring/root-context.xml (Mysql서버접속정보 변경해야 합니다.)
	*/
	
	// interface 로 Mybatis 쿼리 사용 DI처리(Dependency Injcetion)
	@Inject
	private DataSource ds;
	
	@Inject
	private IF_SampleService sampleService; //인터페이스를 실행가능하게 mapper변수로 지정.
	
	@Test
	public void testJunit() {// 출력 확인용
		System.out.println("Junit테스트 확인");// 출력문장
	}
	
	@Test // DB와 연결되어 있는지 확인
	public void testDbConnect() throws SQLException {
		Connection con = ds.getConnection(); //DB데이터소스를 가져옴
		System.out.println("데이터베이스 커넥션 결과:" + con); //가져온 데이터를 출력함
	}
		
	@Test //DB에 데이터를 입력
	public void testInsertMember() throws Exception {
		int randomInt = (int) (Math.random()*100);//아이디 값을 무작위로 생성하기위함
		testSelectMember();// 데이터 입력후 입력 전과 비교하기 위해 
		System.out.println("위쪽은 입력 전 리스트 입니다.");
		
		MemberVO vo = new MemberVO();//입력 할 vo 생성
		//*학생작업
		vo.setUserid("user" + randomInt);//아이디 set
		vo.setUserpw("1234");//패스워드 set
		vo.setUsername("오토봇"+randomInt);//이름 set
		vo.setEmail("user" + randomInt + "@test.com");//이메일 set
		sampleService.insertMember(vo);//세팅한 vo를 DB로 등록하기위해 매퍼쿼리로 보냄
		
		System.out.println("아래쪽은 입력 후 리스트 입니다.");
		testSelectMember();//입력한 데이터 확인용
	}
	@Test// DB에서 회원정보를 가져와 출력한다
	public void testSelectMember() throws Exception {
		List<MemberVO> list = sampleService.selectMember();//DB에서 멤버리스트를 가져옴
		int cnt = 1; //인덱스번호
		for(MemberVO vo:list) {//리스트개수만큼 반복함
			//아래는 가져온 회원정보를 확인하기 위해 콘솔 창에 회원정보를 출력한다
			System.out.print("회원인덱스[" + cnt + "]:");
			System.out.print(" 아이디:" + vo.getUserid());
			System.out.print(", 패스워드:" + vo.getUserpw());
			System.out.print(", 이름 :" + vo.getUsername());
			System.out.println(", 이메일:" + vo.getEmail());
			cnt = cnt + 1;//다음 회원 인덱스를 위해 1을 더함
		}
	}
	
	@Test//회원정보 수정
	public void testUpdateMember() throws Exception {
		testSelectMember();//수정 전 데이터 확인용
		System.out.println("위에서 수정 전 이름을 이름을 확인 하세요");
		
		MemberVO vo = new MemberVO(); //수정할 vo를 생성
		//*학생작업
		vo.setUserid("user3"); //아이디 set
		vo.setUserpw("4321");//패스워드 set
		vo.setUsername("바야바");//이름 set
		vo.setEmail("abc@test.com");//이메일 set
		sampleService.updateMember(vo);//세팅한 vo를 업데이트 매퍼쿼리로 보내 데이터를 업데이트함
		
		System.out.println("아래는 수정 후 이름을 확인 하세요");
		testSelectMember();//수정 후 데이터 확인용
	}
	
	@Test//회원정보 삭제
	public void testDeleteMember() throws Exception {
		testSelectMember();//삭제 전 데이터 출력
		sampleService.deleteMember("user11");//매개변수 아이디를 삭제함
		System.out.println("아래는 지운 후 회원리스트 입니다.");
		testSelectMember();//삭제 후 데이터 출력
	}
		
}
