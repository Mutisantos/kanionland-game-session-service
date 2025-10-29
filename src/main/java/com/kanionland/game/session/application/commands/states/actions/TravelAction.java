package com.kanionland.game.session.application.commands.states.actions;

import com.kanionland.game.session.application.commands.ActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TravelAction implements Action {
    private final String destination;
    private final int travelTime;

    @Override
    public ActionType getType() {
        return ActionType.TRAVEL;
    }
}
