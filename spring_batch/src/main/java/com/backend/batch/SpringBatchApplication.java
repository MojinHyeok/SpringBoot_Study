package com.backend.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing//배치기능 활성화 하는 어노테이션
@SpringBootApplication
public class SpringBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);
	}

}
/**
 * EnableBatchProcessing 을 선언 하여 여러기능들을 사용할 수 있게 됩니다.
 * 이 어노테이션을 선언하지 않는다면 batch 기능을 사용할수 없기 때문에 필수로 선언해줘야 합니다. 
 */