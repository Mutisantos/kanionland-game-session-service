package com.kanionland.game.session.infrastructure.requests.states.actions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kanionland.game.session.infrastructure.requests.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseActionDto implements ActionDto {

  private int amount;
  private String saleLocation;
  private ItemDto item;

  @Override
  @JsonIgnore
  public String getType() {
    return "PURCHASE";
  }
}
