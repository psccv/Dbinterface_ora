### 인터페이스를 사용한 Db 접속, 조회 구현.

```
탐식기에서 이클립스 workspace 폴더로 이동한 후
탐색기 상단경로에서 CMD 워크스페이스 폴더경로 엔터
git clone https://github.com/miniplugin/Dbinterface_ora.git
이클립스에서 프로젝트 Import 실행
이 후 강사와 수업 함께 진행.
작업순서1: member테이블과 매칭되는 MemberVO 클래스 제작 [/src/main/java/org/edu/vo/MemberVO.java]
작업순서2: Mapper쿼리작성 [/src/main/resources/mappers/sampleMapper.xml]
작업순서3: IF와 매칭되는 구현클래스 생성
//DAO인터페이스 경로: /src/main/java/org/edu/service/IF_SampleService.java, IF_SampleDAO.java
작업순서4: IF클래스(DAO, Service)에 아래 4개의 메서드 추가 후 구현클래스 자동 생성 후 구현클래스에서 쿼리 호출
// 회원정보 입력-조회-수정-삭제 메서드 명세
	public void insertMember(MemberVO vo) throws Exception;
	public List<MemberVO> selectMember() throws Exception;
	public void updateMember(MemberVO vo) throws Exception;
	public void deleteMember(String userid)throws Exception;
구현클래스 쿼리 추가 핵심(구현 SampleDAO.java, SampleService.java 클래스에 아래 @inject 추가
@Inject
	private SqlSession sqlSession;
작업순서5: Junit 테스트 사용 [/src/test/java/org/edu/test/SampleMapperTest.java]
작업순서6: log4jdbc/AOP용디버그용 설정추가 [/src/main/webapp/WEB-INF/spring/root-context.xml]
작업순서7: log4설정변경 [/src/main/resources/log4j.xml]
작업순서8: AOP파일 [/src/main/java/org/edu/aop/SampleAdvice.java]
작업순서9: view 화면구현 [/src/main/webapp/WEB-INF/views/home.jsp]
작업순서10: 컨트롤러 클래스에서 @RequestMapping 경로추가 [/src/main/java/org/edu/controller/HomeController.java]
```

- 오라클용 으로 테이블 쿼리 변경 및 오라클용 외부 ojdbc6.jar 라이브러리 추가.

```
create table member
	(
	 userid varchar(50) not null
	    ,userpw varchar(50) not null
	    ,username varchar(50) not null
	    ,email varchar(100)
	    ,regdate DATE DEFAULT SYSDATE NOT NULL
	    ,updatedate DATE DEFAULT SYSDATE NOT NULL
	    ,CONSTRAINT MEMBERS_PK PRIMARY KEY (userid)
	)
    INSERT INTO member VALUES ('user2','1234','kimilguk','user02@test.com',SYSDATE,SYSDATE);
```

- 오라클 시작 에러 처리 

```
 sqlplus > user-name: /as sysdba > recover database; alter database open; shutdown immediate; startup; ) 
기술참조(아래) https://jh20a.tistory.com/entry/%EC%98%A4%EB%A5%98-ORA-01033-ORACLE-initialization-or-shutdown-in-progress
```
