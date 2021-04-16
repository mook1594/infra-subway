package nextstep.subway.common.logging;

public class LoggerTracer {

	private String httpMethod;
	private String uri;
	private Object requestBody;
	private Object responseBody;

	public LoggerTracer(Object requestBody, Object responseBody) {
		this.requestBody = requestBody;
		this.responseBody = responseBody;
	}

	public LoggerTracer(String httpMethod, String uri, Object requestBody, Object responseBody) {
		this.httpMethod = httpMethod;
		this.uri = uri;
		this.requestBody = requestBody;
		this.responseBody = responseBody;
	}

	public Object getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public String getUri() {
		return uri;
	}
}
