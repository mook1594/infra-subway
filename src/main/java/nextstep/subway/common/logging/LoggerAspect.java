package nextstep.subway.common.logging;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Aspect
public class LoggerAspect {

	private static final Logger JSON_CONSOLE_LOGGER = LoggerFactory.getLogger("file");
	private static ObjectMapper objectMapper = new ObjectMapper();

	@AfterReturning(pointcut = "execution(* nextstep.subway..*Controller.*(..))", returning = "responseEntity")
	public void afterReturning(final JoinPoint joinPoint, final Object responseEntity) {
		HttpServletRequest request =
			((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();


		LoggerTracer tracer = new LoggerTracer(
			request.getMethod(),
			request.getRequestURI(),
			joinPoint.getArgs().length > 0 ? joinPoint.getArgs()[0] : null,
			responseEntity
		);
		JSON_CONSOLE_LOGGER.info(toJson(tracer));
	}

	private String toJson(final Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception ex) {
			return Strings.EMPTY;
		}
	}

}
