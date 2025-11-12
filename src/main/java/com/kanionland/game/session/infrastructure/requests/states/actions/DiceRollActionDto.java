package com.kanionland.game.session.infrastructure.requests.states.actions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiceRollActionDto implements ActionDto {

  private String stat;
  private int challengeValue;
  private int rollValue;
  private int statValue;
  private List<Integer> fateRollValues;

  @Override
  @JsonIgnore
  public String getType() {
    return "DICE_ROLL";
  }
}
