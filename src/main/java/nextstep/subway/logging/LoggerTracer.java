package nextstep.subway.logging;

public class LoggerTracer {

	private Object requestBody;
	private Object responseBody;

	public LoggerTracer(Object requestBody, Object responseBody) {
		this.requestBody = requestBody;
		this.responseBody = responseBody;
	}

	public Object getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}
}
