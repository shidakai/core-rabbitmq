/**
 *
 */
package com.jxtech.jxudp.rabbitmq;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * RabbitMq配置文件读取类
 *
 * @author chenhf
 **/
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMqConfig {

	// @Value("${spring.rabbitmq.host}")
	// private String addresses;
	// @Value("${spring.rabbitmq.username}")
	// private String username;
	// @Value("${spring.rabbitmq.password}")
	// private String password;
	// @Value("${spring.rabbitmq.publisher-confirms}")
	// private Boolean publisherConfirms;
	// @Value("${spring.rabbitmq.virtual-host}")
	// private String virtualHost;

	// // 构建mq实例工厂
	// @Bean
	// public ConnectionFactory connectionFactory(){
	// CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
	// connectionFactory.setAddresses(addresses);
	// connectionFactory.setUsername(username);
	// connectionFactory.setPassword(password);
	// connectionFactory.setPublisherConfirms(publisherConfirms);
	// connectionFactory.setVirtualHost(virtualHost);
	// return connectionFactory;
	// }

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		return template;
	}

	// 1.注册exchange
	// 2.注册队列
	// 3.注册队列绑定

	// @Bean
	// public TopicExchange exchange() {
	// return new TopicExchange("exchange");
	// }
	//
	//
	// @Bean(name="message")
	// public Queue queueMessage() {
	// return new Queue("topic.message");
	// }
	// @Bean
	// Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage,
	// TopicExchange exchange) {
	// return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
	// }

}