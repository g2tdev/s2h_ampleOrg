package com.ampleexchange.api.controller;

import javax.servlet.http.HttpServletRequest;

//import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class HttpErrorHandler extends AbstractErrorController {
	 
  private final static String ERROR_PATH = "/error";
  
  public HttpErrorHandler(ErrorAttributes errorAttributes) {  // V2.1
    super(errorAttributes);
  }
  
  /**
   * Supports the HTML Error View
   *
   * @param request
   * @return
   */
  @RequestMapping(value = ERROR_PATH, produces = "text/html")
  public String errorHtml(HttpServletRequest request) {
      return "404";
  }
  /**
   * Supports other formats like JSON, XML
   *
   * @param request
   * @return
   */
  @RequestMapping(value = ERROR_PATH)
  @ResponseBody
  public Object error(HttpServletRequest request) {
      return "404";
  }

  /**
   * Returns the path of the error page.
   *
   * @return the error path
   */
  
  @Override
  public String getErrorPath() {
      return ERROR_PATH;
  }

}
