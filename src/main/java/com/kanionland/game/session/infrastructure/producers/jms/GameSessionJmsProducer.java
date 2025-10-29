package com.kanionland.game.session.infrastructure.producers.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kanionland.game.session.application.commands.GameActionCommand;
import com.kanionland.game.session.domain.model.GameSession;
import jakarta.jms.TextMessage;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class GameSessionJmsProducer {

  private final JmsTemplate jmsTemplate;
  private final ObjectMapper xmlMapper;
  private final String queueName;

  public GameSessionJmsProducer(JmsTemplate jmsTemplate,
      ObjectMapper xmlMapper,
      @Value("${jms.queue.game-session}") String queueName) {
    this.jmsTemplate = jmsTemplate;
    this.queueName = queueName;
    this.xmlMapper = xmlMapper;
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

  public void sendGameSessionEvent(GameActionCommand gameSession) throws JsonProcessingException {
    GameActionMessage message = GameActionMessage.builder()
        .sessionId(gameSession.getSessionId())
        .username(gameSession.getUsername())
        .description(gameSession.getDescription())
        .character(gameSession.getCharacter())
        .actionType(gameSession.getType().name())
        .actionDetails(gameSession.getAction())
        .timestamp(LocalDateTime.now())
        .build();

    String xmlMessage = xmlMapper.writeValueAsString(message);

    jmsTemplate.send(queueName, session -> {
      TextMessage jmsMessage = session.createTextMessage(xmlMessage);
      jmsMessage.setStringProperty("X_MessageType", "GameAction");
      jmsMessage.setStringProperty("X_MessageVersion", "1.0");
      jmsMessage.setStringProperty("Content-Type", "application/xml");
      return jmsMessage;
    });
  }
}
