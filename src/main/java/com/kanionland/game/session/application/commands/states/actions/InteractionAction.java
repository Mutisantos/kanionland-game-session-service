package com.kanionland.game.session.application.commands.states.actions;

import java.util.List;
import com.kanionland.game.session.application.commands.ActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InteractionAction implements Action {

    private final String interactionType;
    private final String interactionTarget;
    private final String associatedStat;
    private final int value;

    @Override
    public ActionType getType() {
        return ActionType.INTERACTION;
    }
}
