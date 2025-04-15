package artists.controller.error;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, String> handleNoSuchElementException(NoSuchElementException nsex) {
		log.error("Exception occurred: {}", nsex.toString());
		return Map.of("Not Found:", nsex.toString());
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String, String> handleIllegalArgumentException(IllegalArgumentException iax) {
		log.error("Exception occured:{}", iax.toString());
		return Map.of("Bad Request:", iax.toString());
	}
}
