package io.turntabl.reportingservices.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import io.turntabl.reportingservices.controllers.LogsSubscriber;

@Configuration
public class RedisConfig {

  @Autowired
  private Environment env;

  @Value("${app.SPRING_REDIS_URL}")
  private String SPRING_REDIS_URL;

  @Value("${app.SPRING_REDIS_PORT}")
  private String SPRING_REDIS_PORT;

  @Value("${app.SPRING_REDIS_USER}")
  private String SPRING_REDIS_USER;

  @Value("${app.SPRING_REDIS_PASS}")
  private String SPRING_REDIS_PASS;

  @Autowired
  private LogsSubscriber logsSubscriber;

  private final ChannelTopic logsTopic = new ChannelTopic("logs");


  @Bean
  public JedisConnectionFactory connectionFactory() {
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();

    configuration.setHostName(SPRING_REDIS_URL);
    configuration.setPort(Integer.parseInt(SPRING_REDIS_PORT));

    if (Arrays.asList(this.env.getActiveProfiles()).contains("prod")) {
      configuration.setUsername(SPRING_REDIS_USER);
      configuration.setPassword(SPRING_REDIS_PASS);
    }

    return new JedisConnectionFactory(configuration);
  }

  @Bean
  public RedisTemplate<String, Object> template() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory());
    template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
    return template;
  }

  @Bean
  public RedisMessageListenerContainer redisMessageListenerContainer() {
    RedisMessageListenerContainer container = new RedisMessageListenerContainer();

    container.setConnectionFactory(connectionFactory());
    container.addMessageListener(new MessageListenerAdapter(logsSubscriber), logsTopic);
    
    return container;
  }
}
