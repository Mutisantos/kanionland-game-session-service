package com.kanionland.game.session.application.commands.states.actions;

import java.util.List;
import com.kanionland.game.session.application.commands.ActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DiceRollAction implements Action {
    private final String stat;
    private final int challengeValue;
    private final int rollValue;
    private final List<Integer> fateRollValues;

    @Override
    public ActionType getType() {
        return ActionType.DICE_ROLL;
    }
}
