/**
 *
 */
package com.jxtech.jxudp.rabbitmq;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RabbitMq发送类.
 *
 * @author jiang jinjun
 *
 */
@Component
public class RabbitMqSender implements RabbitTemplate.ConfirmCallback {

	/** logger */
	private static final Logger logger = LoggerFactory.getLogger(RabbitMqSender.class);

	private RabbitTemplate rabbitTemplate;

	public RabbitMqSender(@Autowired RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		this.rabbitTemplate.setConfirmCallback(this);
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		logger.info("confirm: " + correlationData.getId());
	}

	/**
	 * 统一发送rabbitmq的方法
	 * @param exchangeName exchangeName
	 * @param routeKey routeKey
	 * @param obj 发送的目标
	 * @param confirmCallback 是否确认
	 */
	public void sendRabbitmq(String exchangeName, String routeKey, Object obj, boolean confirmCallback) {

		if (confirmCallback) {
			CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
			logger.info("send: " + correlationData.getId());
			this.rabbitTemplate.convertAndSend(exchangeName, routeKey, obj, correlationData);
		}
		else {
			this.rabbitTemplate.convertAndSend(exchangeName, routeKey, obj);
		}
	}

}
