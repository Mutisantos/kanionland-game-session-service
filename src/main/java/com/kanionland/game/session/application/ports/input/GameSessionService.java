package com.kanionland.game.session.application.ports.input;

import com.kanionland.game.session.domain.model.GameSession;
import java.util.Optional;
import java.util.UUID;

public interface GameSessionService {
    GameSession startSession(String playerId);
    GameSession endSession(UUID sessionId);
    Optional<GameSession> getSession(UUID sessionId);
}
