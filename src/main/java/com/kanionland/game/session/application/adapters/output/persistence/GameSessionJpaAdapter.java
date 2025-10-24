package com.kanionland.game.session.application.adapters.output.persistence;

import com.kanionland.game.session.application.adapters.output.persistence.entity.GameSessionJpaEntity;
import com.kanionland.game.session.application.adapters.output.persistence.mapper.GameSessionPersistenceMapper;
import com.kanionland.game.session.application.adapters.output.persistence.repository.GameSessionJpaRepository;
import com.kanionland.game.session.application.ports.output.GameSessionPersistencePort;
import com.kanionland.game.session.domain.model.GameSession;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameSessionJpaAdapter implements GameSessionPersistencePort {

    private final GameSessionJpaRepository gameSessionJpaRepository;
    private final GameSessionPersistenceMapper gameSessionPersistenceMapper;

    @Override
    public GameSession save(GameSession gameSession) {
        GameSessionJpaEntity entity = gameSessionPersistenceMapper.toJpaEntity(gameSession);
        GameSessionJpaEntity savedEntity = gameSessionJpaRepository.save(entity);
        return gameSessionPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<GameSession> findById(UUID id) {
        return gameSessionJpaRepository.findById(id)
                .map(gameSessionPersistenceMapper::toDomain);
    }

    @Override
    public void delete(UUID id) {
        gameSessionJpaRepository.deleteById(id);
    }
}
