package com.benchmark.testing.config;

import com.benchmark.testing.transfer.Easy;
import com.benchmark.testing.transfer.EasyUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.orglot.gosloto.app.model.ticket.snapshot.transfer.TicketTransferStatus;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {


	@Bean
	public JedisConnectionFactory ticketRedisConnectFactory() {
		var redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName("localhost");
		redisStandaloneConfiguration.setPort(6379);

		var jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(100);
		jedisPoolConfig.setMaxTotal(1000);
		jedisPoolConfig.setBlockWhenExhausted(false);

		return new JedisConnectionFactory(redisStandaloneConfiguration, JedisClientConfiguration.builder()
			.usePooling()
			.poolConfig(jedisPoolConfig)
			.build());
	}


	@Bean
	public RedisTemplate<String, Object> redisTicketTransferTemplate(JedisConnectionFactory ticketRedisConnectFactory) {
		var redisTemplate = new RedisTemplate<String, Object>();

		redisTemplate.setConnectionFactory(ticketRedisConnectFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		redisTemplate.setEnableTransactionSupport(true);

		return redisTemplate;
	}
	@Bean
	public HashOperations<String, Long, EasyUser> test(RedisTemplate<String, Object> redisTicketTransferTemplate){
		return  redisTicketTransferTemplate.opsForHash();
	}


}
