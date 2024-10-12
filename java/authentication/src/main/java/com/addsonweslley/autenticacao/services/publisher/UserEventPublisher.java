package com.addsonweslley.autenticacao.services.publisher;

import com.addsonweslley.autenticacao.dto.User.UserResponse;
import com.addsonweslley.autenticacao.dto.User.UserResponseFitApi;
import com.addsonweslley.autenticacao.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserEventPublisher {

    private final String TOPIC_USER_CREATED = "user.created";

    private final KafkaTemplate<String, UserResponseFitApi> kafkaTemplate;

    public UserEventPublisher(KafkaTemplate<String, UserResponseFitApi> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishUserCreatedEvent(UserResponseFitApi  userResponseFitApi){
        kafkaTemplate.send(TOPIC_USER_CREATED, userResponseFitApi);
    }

}
