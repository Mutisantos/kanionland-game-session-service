package com.kanionland.game.session.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class GameSession {
    private final UUID id;
    private final String playerId;
    private final LocalDateTime startTime;
    private LocalDateTime endTime;
    private GameState state;

    public GameSession(UUID id, String playerId) {
        this.id = id;
        this.playerId = playerId;
        this.startTime = LocalDateTime.now();
        this.state = GameState.STARTED;
    }

    // Getters
    public UUID getId() { return id; }
    public String getPlayerId() { return playerId; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public GameState getState() { return state; }

    // Domain methods
    public void endSession() {
        this.endTime = LocalDateTime.now();
        this.state = GameState.COMPLETED;
    }
}

