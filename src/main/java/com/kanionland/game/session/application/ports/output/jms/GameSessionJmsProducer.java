package com.kanionland.game.session.application.ports.output.jms;

import com.kanionland.game.session.domain.model.GameSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class GameSessionJmsProducer {

    private final JmsTemplate jmsTemplate;
    private final String queueName;

    public GameSessionJmsProducer(JmsTemplate jmsTemplate, 
                                @Value("${jms.queue.game-session}") String queueName) {
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
    }

    public void sendGameSessionEvent(GameSession gameSession) {
        GameSessionMessage message = GameSessionMessage.builder()
                .id(gameSession.getId())
                .playerId(gameSession.getPlayerId())
                .state(gameSession.getState())
                .startTime(gameSession.getStartTime())
                .endTime(gameSession.getEndTime())
                .build();
        
        jmsTemplate.convertAndSend(queueName, message, m -> {
            m.setStringProperty("X_MessageType", "GameSession");
            m.setStringProperty("X_MessageVersion", "1.0");
            return m;
        });
    }
}
