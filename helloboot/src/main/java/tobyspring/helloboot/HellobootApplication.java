package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args){
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
//        ServletWebServerFactory serverFactory = new JettyServletWebServerFactory();
		//톰캣 외의 다른 서블릿 컨테이너를 받을 수 있도록 일관된 방식으로 지원하기 위해 추상화를 진행한 상태입니다.
//		WebServer webServer = serverFactory.getWebServer(new ServletContextInitializer() {
//			@Override
//			public void onStartup(ServletContext servletContext) throws ServletException {
//			}
//		});
		//ServeletContextInitializer는 @FunctionalInterface 임으로 람다식으로 대체가 가능합니다.
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			// 서블릿을 ServletContext에 추가합니다. "hello"는 서블릿의 이름입니다.
			HelloController helloController = new HelloController();
			servletContext.addServlet("frontcontroller", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					//여기에서 인증과 보안 다국어 공통기능을 처리한다고 가정.
					if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
						String name = req.getParameter("name");
						String ret = helloController.hello(name);
						// 웹 요청에 대한 응답을 처리하는 메소드입니다.
						//웹 요청에 해당하는 3가지 상태코드, 헤더, 바디
						resp.setStatus(HttpStatus.OK.value());
						resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);//String보다는 Spring에서 제공하는 단어사전과 같은 기능을 통해 수정.
						resp.getWriter().println(ret);
					}else{
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}
				}
			}).addMapping("/*");
		});
		//톰캣 서블릿컨테이너 실행
		webServer.start();
	}

}
