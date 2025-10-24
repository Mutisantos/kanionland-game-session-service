package com.kanionland.game.session.application.adapters.output.persistence.repository;

import com.kanionland.game.application.adapters.output.persistence.entity.GameSessionJpaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSessionJpaRepository extends JpaRepository<GameSessionJpaEntity, UUID> {
}
