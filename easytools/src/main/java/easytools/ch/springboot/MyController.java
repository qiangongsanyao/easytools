package easytools.ch.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author CH
 * @version 1
 */
@Controller
@EnableAutoConfiguration
public class MyController {

	@RequestMapping("/")
	@ResponseBody
	public String say() {
		return "Hello World!";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MyController.class, args);
	}
	
}
