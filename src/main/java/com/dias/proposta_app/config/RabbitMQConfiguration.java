package com.dias.proposta_app.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

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


    private final ConnectionFactory connectionFactory;

    public RabbitMQConfiguration(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
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
        return ExchangeBuilder.fanoutExchange("pending-proposal.ex").build();
    }

    @Bean
    public Binding createBindingPendingProposalCreditAnalysis() {
        return BindingBuilder.bind(createQueuePendingProposalMsCreditAnalasys()).to(createFanoutExchangePendingProposal());
    }

    @Bean
    public Binding createBindingPendingProposalNotifications() {
        return BindingBuilder.bind(createQueuePendingProposalMsNotification()).to(createFanoutExchangePendingProposal());
    }

}
