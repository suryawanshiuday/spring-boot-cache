package com.udays.personal.projects.spring.boot.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class SpringBootCacheApplication {

	/**
	 * @EnableCaching to enable spring boot caching or embedded H2/Eh/hazelcast etc cachees
	 * @EnableScheduling to enabled scheduled cache eviction
	 * This application uses spring boot provided caching and this project is limited to its usage
	 * Spring boot has pretty good integration with other caching technologies like H2/Eh/hazercast/redis
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheApplication.class, args);
	}

}
