package org.edu.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.edu.dao.IF_SampleDAO;
import org.edu.service.IF_SampleService;
import org.edu.vo.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private IF_SampleService sampleService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		String maker = "parksangcheon";//*학생작업 학생이 home.jsp에 나오도록 하세요.
		model.addAttribute("jspMaker", maker);
		//제네릭타입-List<Type>을 사용하는 이유는 실행시(runtime)에서만 발생될 수 있는 에러를
		//서버에 올리기 전에 미리 이클립스 단에서 컴파일시 에러를 확인 가능하게 하기 위해서 
		List<MemberVO> list = sampleService.selectMember();
		model.addAttribute("memberList", list);
		return "home";
	}
	
}
