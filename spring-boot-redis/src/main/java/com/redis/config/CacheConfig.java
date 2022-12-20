package com.redis.config;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class CacheConfig 
{
	
	private static final Logger LOG = LoggerFactory.getLogger(CacheConfig.class);

	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private int redisPort;

	@Value("${spring.redis.password}")
	private String redisPassword;

	public CacheConfig() {
		super();
	}

	public CacheConfig(String redisHost, int redisPort, String redisPassword) {
		super();
		this.redisHost = redisHost;
		this.redisPort = redisPort;
		this.redisPassword = redisPassword;
	}

	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		LOG.info("CONFIGURE >> redisConnectionFactory() >> Connection Factory >> Start");
		RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
		redisConf.setHostName(redisHost);
		redisConf.setPort(redisPort);
		redisConf.setPassword(RedisPassword.of(redisPassword.trim().length() > 0 ? redisPassword : ""));
		LOG.info("CONFIGURE >> redisConnectionFactory() >> Connection Factory >> Done");
		return new LettuceConnectionFactory(redisConf);
	}

	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
		LOG.info("CONFIGURE >> cacheConfiguration() >> Cache Configuration >> Start");
		RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(600)).disableCachingNullValues();
		LOG.info("CONFIGURE >> cacheConfiguration() >> Cache Configuration >> Done");
		return cacheConfig;
	}

	@Bean
	public RedisCacheManager cacheManager() {
		LOG.info("CONFIGURE >> cacheManager() >> Cache Manager >> Start");
		RedisCacheManager rcm = RedisCacheManager.builder(redisConnectionFactory()).cacheDefaults(cacheConfiguration()).transactionAware().build();
		LOG.info("CONFIGURE >> cacheManager() >> Cache Manager >> Done");
		return rcm;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		LOG.info("CONFIGURE >> redisTemplate() >> Redis Template >> Start");
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
		LettuceConnectionFactory lcf = redisConnectionFactory();
		lcf.afterPropertiesSet();
		template.setConnectionFactory(lcf);
		template.setKeySerializer(stringSerializer);
		template.setHashKeySerializer(stringSerializer);
		template.setValueSerializer(jdkSerializationRedisSerializer);
		template.setHashValueSerializer(jdkSerializationRedisSerializer);
		template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();
		LOG.info("CONFIGURE >> redisTemplate() >> Redis Template >> Start");
		return template;
	}
}