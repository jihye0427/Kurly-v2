package kurly.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kurly.domain.dto.MemberLoginDto;
import kurly.domain.dto.MemberRequestDto;
import kurly.services.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	//@RequestMapping(value = "/member/join" , method = RequestMethod.POST)
	@GetMapping(value = {"/member/join","/member"})
	public String join() {
		return "/member/join";//이동할주소 /member/join.html
	}
	
	@PostMapping("/member/join")
	public ModelAndView join(MemberRequestDto dto ) {
		//웹페이지에서 회원정보 받고
		//System.out.println(dto);
		memberService.addMember(dto);
		
		ModelAndView mv=new ModelAndView("/member/login");
		mv.addObject("userName", dto.getName());
		return mv;
	}
	
	@GetMapping("/member/login")
	public String login() {
		return "/member/login";
	}
	@PostMapping("/member/login")
	public ModelAndView login(MemberLoginDto dto, HttpSession session) {
		MemberLoginDto logMember= memberService.login(dto);
		ModelAndView mv=new ModelAndView();
		if(logMember==null) {
			//로그인실패
			//다시로그인페이지로
			mv.addObject("log_msg", "아이디나 패스워드가 일치하지 않습니다.");
			mv.setViewName("/member/login");
		}else {
			
			session.setAttribute("logInfo", logMember);
			mv.setViewName("redirect:/");
		}
		
		return mv;
	}
	
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("logInfo");
		return "redirect:/";
	}
}
