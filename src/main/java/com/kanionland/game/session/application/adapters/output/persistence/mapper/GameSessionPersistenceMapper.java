package com.kanionland.game.session.application.adapters.output.persistence.mapper;

import com.kanionland.game.session.application.adapters.output.persistence.entity.GameSessionJpaEntity;
import com.kanionland.game.session.domain.model.GameSession;
import com.kanionland.game.session.domain.model.GameState;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GameSessionPersistenceMapper {

    GameSessionPersistenceMapper INSTANCE = Mappers.getMapper(GameSessionPersistenceMapper.class);

    @Mapping(target = "state", source = "state", qualifiedByName = "mapState")
    GameSessionJpaEntity toJpaEntity(GameSession gameSession);

    @Mapping(target = "state", source = "state", qualifiedByName = "mapStateToDomain")
    GameSession toDomain(GameSessionJpaEntity entity);

    @Named("mapState")
    default GameSessionJpaEntity.GameState mapState(GameState state) {
        return GameSessionJpaEntity.GameState.valueOf(state.name());
    }

    @Named("mapStateToDomain")
    default GameState mapStateToDomain(GameSessionJpaEntity.GameState state) {
        return GameState.valueOf(state.name());
    }
}
