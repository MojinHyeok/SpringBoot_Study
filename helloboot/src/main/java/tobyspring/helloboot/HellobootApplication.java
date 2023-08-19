package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

public class HellobootApplication {

	public static void main(String[] args){
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
//        ServletWebServerFactory serverFactory = new JettyServletWebServerFactory();
		//톰캣 외의 다른 서블릿 컨테이너를 받을 수 있도록 일관된 방식으로 지원하기 위해 추상화를 진행한 상태입니다.
		WebServer webServer = serverFactory.getWebServer();
		//톰캣 서블릿컨테이너 실행
		webServer.start();
	}

}
