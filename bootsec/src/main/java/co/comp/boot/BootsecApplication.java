package co.comp.boot;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
@MapperScan(basePackages = "co.comp.**.mapper")
public class BootsecApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootsecApplication.class, args);
	}

	@GetMapping("/top")
	public void top(){
	}
	
	@GetMapping("/admin/admin")
	public String admin(){
		return "admin/admin";
	}
	
	@GetMapping("/user/user")
	public String user(@AuthenticationPrincipal Principal uservo, HttpSession session){
		UserDetails userDetails = 
				(UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails.getUsername());
		
		System.out.println(uservo.getName()); // 어노테이션으로 읽어내는 userId
		
		String sessionLoginId = (String)session.getAttribute("loginId"); // 세션에서 읽어오는 정보
		System.out.println(sessionLoginId);
		
		return "user/user";
	}
	
	@GetMapping("/login")
	public void login() {
	}
}
