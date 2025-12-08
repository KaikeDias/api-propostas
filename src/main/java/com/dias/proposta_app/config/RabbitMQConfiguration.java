package com.dias.proposta_app.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitmq.pending-proposal.exchange}")
    private String exchange;

    @Bean
    public Queue createQueuePendingProposalMsCreditAnalasys() {
        return QueueBuilder.durable("pending-proposal.ms-credit-analysis").build();
    }

    @Bean
    public Queue createQueuePendingProposalMsNotification() {
        return QueueBuilder.durable("pending-proposal.ms-notification").build();
    }

    @Bean
    public Queue createQueueConcludedProposalMsProposal() {
        return QueueBuilder.durable("concluded-proposal.ms-proposal").build();
    }

    @Bean
    public Queue createQueueConcludedProposalMsNotification() {
        return QueueBuilder.durable("concluded-proposal.ms-notification").build();
    }



    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public FanoutExchange createFanoutExchangePendingProposal() {
        return ExchangeBuilder.fanoutExchange(exchange).build();
    }

    @Bean
    public Binding createBindingPendingProposalCreditAnalysis() {
        return BindingBuilder.bind(createQueuePendingProposalMsCreditAnalasys()).to(createFanoutExchangePendingProposal());
    }

    @Bean
    public Binding createBindingPendingProposalNotifications() {
        return BindingBuilder.bind(createQueuePendingProposalMsNotification()).to(createFanoutExchangePendingProposal());
    }

    @Bean
    public MessageConverter jacksonJsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonJsonMessageConverter());

        return rabbitTemplate;
    }
}
