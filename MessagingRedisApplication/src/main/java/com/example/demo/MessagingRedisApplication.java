package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * Main application class for Redis-based messaging using Spring Boot.
 * This application demonstrates how to send and receive messages using Redis.
 */
@SpringBootApplication
public class MessagingRedisApplication {

	// Logger instance for logging application events
	private static final Logger LOGGER = LoggerFactory.getLogger(MessagingRedisApplication.class);

	/**
	 * Configures a RedisMessageListenerContainer, which listens for messages on a specified topic.
	 *
	 * @param connectionFactory The Redis connection factory.
	 * @param listenerAdapter   The message listener adapter.
	 * @return Configured RedisMessageListenerContainer.
	 */
	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new PatternTopic("chat")); // Subscribing to "chat" topic

		return container;
	}

	/**
	 * Defines a MessageListenerAdapter bean that delegates message handling to the Receiver class.
	 *
	 * @param receiver The receiver instance that handles incoming messages.
	 * @return Configured MessageListenerAdapter.
	 */
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage"); // Maps "receiveMessage" method to handle messages
	}

	/**
	 * Creates a Receiver bean, which processes incoming messages.
	 *
	 * @return A new instance of the Receiver class.
	 */
	@Bean
	Receiver receiver() {
		return new Receiver();
	}

	/**
	 * Creates a StringRedisTemplate bean for sending messages to Redis.
	 *
	 * @param connectionFactory The Redis connection factory.
	 * @return Configured StringRedisTemplate.
	 */
	@Bean
	StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}

	/**
	 * Main method to start the Spring Boot application.
	 * It sends messages to the Redis "chat" topic until a message is received.
	 *
	 * @param args Command-line arguments.
	 * @throws InterruptedException If thread sleep is interrupted.
	 */
	public static void main(String[] args) throws InterruptedException {

		// Start the Spring Boot application and get the application context
		ApplicationContext ctx = SpringApplication.run(MessagingRedisApplication.class, args);

		// Retrieve beans from the application context
		StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
		Receiver receiver = ctx.getBean(Receiver.class);

		// Loop until at least one message is received
		while (receiver.getCount() == 0) {
			LOGGER.info("Sending message...");
			template.convertAndSend("chat", "Hello from Redis!"); // Publishing a message to the "chat" topic
			Thread.sleep(500L); // Pause before sending another message
		}

		System.exit(0); // Exit the application once a message is received
	}
}
