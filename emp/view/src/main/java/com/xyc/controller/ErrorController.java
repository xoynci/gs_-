package com.xyc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xyc.util.CommonResult;
import com.xyc.util.CommonResult.ResultStatusType;
import com.xyc.util.Constant;
import com.xyc.util.PlatformRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Controller
@RequestMapping("/error")
public class ErrorController extends AbstractErrorController {

	private Logger logger = LoggerFactory.getLogger(ErrorController.class);
	
	@Value("/error")
	private String errorPath;
	
	private static String errorMsg = "服务器错误，请联系管理员";
	
	
	public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

	
	  /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ModelAndView handleException(Exception e){
    	ModelAndView modelAndView = new ModelAndView("/error");
    	modelAndView.addObject("obj",CommonResult.resultError(errorMsg));
    	logger.error("异常信息："+e.getMessage(), e);
    	return modelAndView;
    }

    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(PlatformRuntimeException.class)
    @ResponseBody
    public ModelAndView  handleBusinessException(PlatformRuntimeException e){
		ModelAndView modelAndView = new ModelAndView("/error");
		modelAndView.addObject("obj",CommonResult.build(
				e.getCode() != null?e.getCode():ResultStatusType.ERROR.status,
				e.getMessage(),
				e.getData()));
    	logger.error("异常信息："+e.getMessage(), e);
    	return modelAndView ;
    }
	
	@RequestMapping
	@ResponseBody
	public ModelAndView  getErrorPath(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/error");
		Exception cause = this.getCause(request);

		String errMsg =  null;
		Integer code = null;
		Object data = null;
		
		if (cause != null && cause instanceof PlatformRuntimeException) {
			PlatformRuntimeException exception = (PlatformRuntimeException)cause;
			errMsg  = exception.getMessage();
			code = exception.getCode();
			data = exception.getData();
		}
		if(code == null){
			if(response.getStatus() == HttpStatus.NOT_FOUND.value()){
				code = response.getStatus();
				errMsg = HttpStatus.NOT_FOUND.getReasonPhrase();
				data = request.getRequestURI();
			}
			//这里可以补充其他未捕获的异常
		}
		
		if(code == null){
			code =  ResultStatusType.ERROR.status;
		}
		if(errMsg == null){
			errMsg = errorMsg;
		}
		CommonResult commonResult = CommonResult.build(code, errMsg, data);
		
		if (cause != null){
			logger.error("异常信息："+cause.getMessage(), cause);
		}else{
			try {
				logger.error("异常信息："+Constant.OBJECT_MAPPER.writeValueAsString(commonResult));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		modelAndView.addObject("obj",commonResult);
		return modelAndView;
		
	}

	private Exception getCause(HttpServletRequest request) {

		Throwable error = (Throwable) request.getAttribute("javax.servlet.error.exception");
		if (error != null) {
			while (error instanceof ServletException && error.getCause() != null) {
				error = ((ServletException) error).getCause();
			}
		}
		return (Exception)error;
	}

	@Override
	public String getErrorPath() {
		return this.errorPath;
	}

}
