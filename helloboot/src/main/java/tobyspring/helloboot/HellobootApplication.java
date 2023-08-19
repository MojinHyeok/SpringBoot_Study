package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

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
		//ServeletContextInitializer는 @FunctionalInterface 임으로 람다식으로 대체가 가능
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("hello", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					//웹 요청에 해당하는 3가지 상태코드, 헤더, 바디
					resp.setStatus(200);
					resp.setHeader("Content-Type","text/plain");
					resp.getWriter().println("Hello Servlet");
				}
			}).addMapping("/hello");// "/hello" 경로로 들어온 요청에 이 서블릿을 매핑합니다.
		});
		//톰캣 서블릿컨테이너 실행
		webServer.start();
	}

}
