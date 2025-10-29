package com.kanionland.game.session.application.adapters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kanionland.game.session.application.commands.GameActionCommand;
import com.kanionland.game.session.application.ports.input.GameSessionPort;
import com.kanionland.game.session.domain.model.GameSession;
import com.kanionland.game.session.domain.model.GameState;
import com.kanionland.game.session.infrastructure.producers.jms.GameSessionJmsProducer;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameSessionAdapter implements GameSessionPort {

  private final GameSessionJmsProducer producer;

  @Override
  public void startSession(String playerId) {
    GameSession gameSession = GameSession.builder()
        .id(UUID.randomUUID())
        .playerId(playerId)
        .state(GameState.STARTED)
        .startTime(LocalDateTime.now())
        .build();
    producer.sendGameSessionEvent(gameSession);
  }

  @Override
  public void sendGameAction(GameActionCommand actionCommand) {
    try {
      producer.sendGameSessionEvent(actionCommand);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void endSession(UUID sessionId) {
    GameSession gameSession = GameSession.builder()
        .id(sessionId)
        .state(GameState.COMPLETED)
        .endTime(LocalDateTime.now())
        .build();
    producer.sendGameSessionEvent(gameSession);
  }

}
