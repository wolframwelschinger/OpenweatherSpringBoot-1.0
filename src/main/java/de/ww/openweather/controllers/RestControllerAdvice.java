package de.ww.openweather.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import de.ww.openweather.controllers.param.OrtFilterParam;
import de.ww.openweather.controllers.param.PageParam;
import de.ww.openweather.controllers.param.SortParam;
import de.ww.openweather.controllers.param.WetteraufzeichnungFilterParam;

/**
 * Hilfsklasse zur Analyse von REST-Parametern
 * 
 * Hier m&uuml;ssen alle Param-Klassen registiert werden
 * 
 * @author Michael Engelhardt, Wolfram Welschinger
 *
 */
@ControllerAdvice(basePackages = {"de.ww.openweather.controllers"})
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

	Logger log = LogManager.getLogger(this.getClass());
  
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		binder.registerCustomEditor(OrtFilterParam.class, new JsonMappingEditor<OrtFilterParam>(OrtFilterParam.class));
		binder.registerCustomEditor(WetteraufzeichnungFilterParam.class, new JsonMappingEditor<WetteraufzeichnungFilterParam>(WetteraufzeichnungFilterParam.class));
		binder.registerCustomEditor(SortParam.class, new JsonMappingEditor<SortParam>(SortParam.class));
		binder.registerCustomEditor(PageParam.class, new JsonMappingEditor<PageParam>(PageParam.class));
	}
	
	@ExceptionHandler
	public void handleException(Exception ex, HttpServletResponse response) throws IOException {
	  log.error("WetterApp REST-API error", ex);
	  response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionUtils.getRootCauseMessage(ex));
	}
	
//  @ExceptionHandler({ AccessDeniedException.class, AuthenticationException.class })
//  public void handleMethodSecurityException(Exception ex, HttpServletResponse response) throws IOException {
//    log.error("Katalogtool security error", ex);
//    response.sendError(HttpStatus.UNAUTHORIZED.value(), ExceptionUtils.getRootCauseMessage(ex));
//  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
        .collect(Collectors.toMap(error -> error.getField(), error -> error.getDefaultMessage()));
    Object responseBody = Collections.singletonMap("fieldErrors", fieldErrors);
    return super.handleExceptionInternal(ex, responseBody, headers, status, request);
  }
  
}
