package org.zerock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice // 스프링의 컨트롤러에서 발생하는 예외를 처리하는 존재임을 명시.
@Log4j
public class CommonExceptionAdvice {
	
	// () 안에 해당하는 예외타입을 처리한다는 것을 의미 이와 같은 경우 Exception.class를 지정 하였으므로
	// 모든 예외에 대한 처리가 except()만을 이용해서 처리할 수 있다.
	// 만일 특정한 타입의 예외를 다루고 싶다면 Exception.class 대신 구체적인 예외의 클래스를 지정해야한다.
	// jsp 화면에서도 구체적인 메시지를 보고 싶다면, Model을 이용해 전달하는게 좋다
	// org.zercok.exception 패키지는 servlet-context.xml에서 인식하지 않기 때문에
	// component-scan으로 지정해준다
	@ExceptionHandler(Exception.class)  
	public String except(Exception ex, Model model) {
		
		log.error("Exception......." + ex.getMessage());
		model.addAttribute("exception", ex);

		return "error_page";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		
		return "custom404";
		
	}
}
