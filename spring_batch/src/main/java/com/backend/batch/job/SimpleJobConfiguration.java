package com.backend.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j// 로그 사용을 위한 lombok 어노테이션
@RequiredArgsConstructor// 생성자 DI를 위한 lombok어노테이션
@Configuration//Spring batch의 모든 Job은 Configuration으로 등록해서 사용
public class SimpleJobConfiguration {
	
	private final JobBuilderFactory jobBuilderFactory; //생성자 DI받음
	private final StepBuilderFactory stepBuilderFacory; // 생성자 DI받음
	
	@Bean
	public Job simpleJob() {
		return jobBuilderFactory.get("simpleJob")//simpleJob이라는 이름으로 Batch job생성
				.start(simpleStep1(null))
				.next(simpleStep2(null))
				.build();
	}

	@Bean
	@JobScope
	public Step simpleStep1(@Value("#{jobParameters[requestDate]}")String requestDate) {
		return stepBuilderFacory.get("simpleStep1")
				.tasklet((contribution, chunkContext)->{
					log.info("===== This is Step2");
					log.info(">>>>> requestDate ={}",requestDate);
					return RepeatStatus.FINISHED;
				})
				.build();
	}

	@Bean
	@JobScope
	public Step simpleStep2(@Value("#{jobParameters[requestDate]}")String requestDate) {
		return stepBuilderFacory.get("simpleStep2")//simpleStep2이라는 이름으로 Batch Step을 생성합니다.
				.tasklet((contribution,chunkContext) -> {
					log.info("===== This is Step2");
					log.info(">>>>> requestDate ={}",requestDate);
					return RepeatStatus.FINISHED;
				}).build();
	}
	/*
	 * tasklet은 Step 안에서 단일로 수행될 커스텀한 기능들을 선언할 때 사용
	 * 명확한 역할은 없지만, 개발자가 지정한 커스텀한 기능을 위한 단위..
	 */

}
