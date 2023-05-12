package co.comp.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class YedamApplication {

	public static void main(String[] args) {
		SpringApplication.run(YedamApplication.class, args);
	}

	@GetMapping("/ajax")
	@ResponseBody
	public String ajax() {
		return "hello";
	}
	
}
