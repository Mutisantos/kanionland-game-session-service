package com.kanionland.game.session.application.services;

import com.kanionland.game.session.application.ports.input.GameSessionService;
import com.kanionland.game.session.application.ports.output.GameSessionPersistencePort;
import com.kanionland.game.session.domain.model.GameSession;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class GameSessionServiceImpl implements GameSessionService {
    
    private final GameSessionPersistencePort gameSessionPersistencePort;
    
    public GameSessionServiceImpl(GameSessionPersistencePort gameSessionPersistencePort) {
        this.gameSessionPersistencePort = gameSessionPersistencePort;
    }
    
    @Override
    public GameSession startSession(String playerId) {
        GameSession gameSession = new GameSession(UUID.randomUUID(), playerId);
        return gameSessionPersistencePort.save(gameSession);
    }
    
    @Override
    public GameSession endSession(UUID sessionId) {
        return gameSessionPersistencePort.findById(sessionId)
            .map(gameSession -> {
                gameSession.endSession();
                return gameSessionPersistencePort.save(gameSession);
            })
            .orElseThrow(() -> new RuntimeException("Game session not found with id: " + sessionId));
    }
    
    @Override
    public Optional<GameSession> getSession(UUID sessionId) {
        return gameSessionPersistencePort.findById(sessionId);
    }
}
