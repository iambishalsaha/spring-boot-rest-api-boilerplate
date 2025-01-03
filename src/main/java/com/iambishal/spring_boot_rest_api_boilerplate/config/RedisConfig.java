package com.iambishal.spring_boot_rest_api_boilerplate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

/**
 * Configuration class for setting up Redis integration with the application.
 * This class defines beans to configure a connection to a Redis database
 * and a RedisTemplate for interacting with Redis.
 */
@Configuration
public class RedisConfig {

    // Redis database index (as specified in the application properties)
    @Value("${spring.data.redis.database}")
    private String database;

    // Redis server hostname
    @Value("${spring.data.redis.host}")
    private String host;

    // Redis server port
    @Value("${spring.data.redis.port}")
    private String port;

    // Redis password (if authentication is enabled)
    @Value("${spring.data.redis.password}")
    private String password;

    // Timeout duration for Redis commands (in milliseconds)
    @Value("${spring.data.redis.timeout}")
    private String timeout;

    /**
     * Configures a LettuceConnectionFactory for connecting to the Redis server.
     *
     * @return A LettuceConnectionFactory instance configured with Redis server details.
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        // Configure Redis connection settings
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setDatabase(Integer.parseInt(database));
        configuration.setHostName(host);
        configuration.setPort(Integer.parseInt(port));
        configuration.setPassword(password);

        // Configure Lettuce client with a command timeout
        LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(Long.parseLong(timeout)))
                .build();

        // Return a connection factory with the configured settings
        return new LettuceConnectionFactory(configuration, lettuceClientConfiguration);
    }

    /**
     * Configures a RedisTemplate for serializing and deserializing Redis data.
     * This template is used to perform Redis operations.
     *
     * @return A RedisTemplate instance configured with the LettuceConnectionFactory.
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        // Create a RedisTemplate instance
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        // Set the connection factory for the template
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());

        // Return the configured RedisTemplate
        return redisTemplate;
    }
}
