package com.daon.host.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UsersController {
	
	/* jpa flow
	 * Controller -> Service -> Repository(JpaRepository 상속 인터페이스)
	 * mybatis flow
	 * Controller -> Service -> Mapper(인터페이스) -> xml(SQL Map XML)
	 참고: https://blog.jiniworld.me/55
	 * */

    @GetMapping("/test")
    public String signUp() {
    	//112
<<<<<<< HEAD
    
=======
    	//커밋 테스트
    	//1234
		//1234
		//5678
		//0000
		//7777
		//5555
>>>>>>> 886018fec8c7ce8cdcb17da84d1a8505361516a2
        return "index.html";
    }

}