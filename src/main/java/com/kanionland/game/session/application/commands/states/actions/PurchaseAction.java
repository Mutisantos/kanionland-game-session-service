package com.kanionland.game.session.application.commands.states.actions;

import com.kanionland.game.session.application.commands.ActionType;
import com.kanionland.game.session.domain.model.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PurchaseAction implements Action {
    private final int amount;
    private final String saleLocation;
    private final Item item;

    @Override
    public ActionType getType() {
        return ActionType.PURCHASE;
    }
}
