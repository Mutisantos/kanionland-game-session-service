package com.kanionland.game.session.application.services;

import com.kanionland.game.session.application.ports.input.GameSessionService;
import com.kanionland.game.session.application.ports.output.jms.GameSessionJmsProducer;
import com.kanionland.game.session.domain.model.GameSession;
import com.kanionland.game.session.domain.model.GameState;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameSessionServiceImpl implements GameSessionService {

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
  public void endSession(UUID sessionId) {
    GameSession gameSession = GameSession.builder()
        .id(sessionId)
        .state(GameState.COMPLETED)
        .endTime(LocalDateTime.now())
        .build();
    producer.sendGameSessionEvent(gameSession);
  }

}
