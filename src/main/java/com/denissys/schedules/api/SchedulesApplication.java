package com.denissys.schedules.api;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

@EnableHystrixDashboard
@SpringCloudApplication
@ComponentScan(basePackages = "com.denissys.schedules.api.*")
//@EnableJpaRepositories
public class SchedulesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulesApplication.class, args);
	}
}
