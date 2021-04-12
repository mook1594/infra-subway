package nextstep.subway.logging;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.AbstractController;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Aspect
public class LoggerAspect {

	private static final Logger JSON_CONSOL_LOGGER = LoggerFactory.getLogger("JSON_CONSOL_LOGGER");
	private static ObjectMapper objectMapper = new ObjectMapper();

	@AfterReturning(value = "pointCut()", returning = "responseEntity")
	public void afterReturning(final JoinPoint joinPoint, final Object responseEntity) {
		LoggerTracer tracer = new LoggerTracer(
			joinPoint.getArgs().length > 0 ? joinPoint.getArgs()[0] : null,
			responseEntity
		);
		JSON_CONSOL_LOGGER.info(toJson(tracer));
	}

	// @Pointcut("within(nextstep.subway.*)")
	@Pointcut("execution(* nextstep.subway..*.*())")
	private void pointCut() { }

	private String toJson(final Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception ex) {
			return Strings.EMPTY;
		}
	}

}
