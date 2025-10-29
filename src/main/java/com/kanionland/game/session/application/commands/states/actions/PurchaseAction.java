package com.kanionland.game.session.application.commands.states.actions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kanionland.game.session.application.commands.ActionType;
import com.kanionland.game.session.domain.model.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JacksonXmlRootElement(localName = "purchaseAction")
public class PurchaseAction implements Action {

  @JacksonXmlProperty(localName = "amount")
  private final int amount;
  @JacksonXmlProperty(localName = "saleLocation")
  private final String saleLocation;
  @JacksonXmlProperty(localName = "item")
  private final Item item;

  @Override
  public ActionType getType() {
    return ActionType.PURCHASE;
  }
}
