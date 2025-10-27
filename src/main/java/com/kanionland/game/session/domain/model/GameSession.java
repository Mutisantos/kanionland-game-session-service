package com.kanionland.game.session.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GameSession {

  private UUID id;
  private String playerId;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private GameState state;

  // Domain methods
  public void endSession() {
    this.endTime = LocalDateTime.now();
    this.state = GameState.COMPLETED;
  }
}

