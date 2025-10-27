package com.kanionland.game.session.application.adapters.output.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GameSessionPersistenceMapper {

    GameSessionPersistenceMapper INSTANCE = Mappers.getMapper(GameSessionPersistenceMapper.class);

}
