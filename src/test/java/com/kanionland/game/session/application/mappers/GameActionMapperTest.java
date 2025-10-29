package com.kanionland.game.session.application.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import com.kanionland.game.session.application.commands.ActionType;
import com.kanionland.game.session.application.commands.CombatActionType;
import com.kanionland.game.session.application.commands.GameActionCommand;
import com.kanionland.game.session.application.commands.states.actions.CombatAction;
import com.kanionland.game.session.application.commands.states.actions.DiceRollAction;
import com.kanionland.game.session.application.commands.states.actions.InteractionAction;
import com.kanionland.game.session.application.commands.states.actions.PurchaseAction;
import com.kanionland.game.session.application.commands.states.actions.TravelAction;
import com.kanionland.game.session.infrastructure.requests.GameActionRequest;
import com.kanionland.game.session.infrastructure.requests.ItemDto;
import com.kanionland.game.session.infrastructure.requests.states.actions.CombatActionDto;
import com.kanionland.game.session.infrastructure.requests.states.actions.DiceRollActionDto;
import com.kanionland.game.session.infrastructure.requests.states.actions.InteractionActionDto;
import com.kanionland.game.session.infrastructure.requests.states.actions.PurchaseActionDto;
import com.kanionland.game.session.infrastructure.requests.states.actions.TravelActionDto;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameActionMapperTest {

  public static final UUID SESSION_ID = UUID.randomUUID();
  public static final String USERNAME = "mutisantos";
  public static final String CHARACTER = "Mayu";
  public static final String STAT = "DES";
  public static final String TARGET = "Sugarina";
  public static final int PURCHASE_AMOUNT = 2;
  public static final String SALE_LOCATION = "Ikuse-o";
  private GameActionMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new GameActionMapperImpl();
  }

  @Test
  void testCombatActionMapping() throws Exception {
    // Given
    GameActionRequest request = GameActionRequest.builder()
        .username(USERNAME)
        .character(CHARACTER)
        .description("Tiro de gas pimienta")
        .action(CombatActionDto.builder()
            .actionType(CombatActionType.ACTION.name())
            .stat(STAT)
            .target(TARGET)
            .value(20)
            .build())
        .build();
    // When
    GameActionCommand command = mapper.toCommand(request, SESSION_ID);
    // Then
    assertThat(command).isNotNull();
    assertThat(command.getUsername()).isEqualTo(USERNAME);
    assertThat(command.getCharacter()).isEqualTo(CHARACTER);
    assertThat(command.getDescription()).isEqualTo("Tiro de gas pimienta");
    assertThat(command.getAction()).isInstanceOf(CombatAction.class);
    assertThat(command.getSessionId()).isEqualTo(SESSION_ID);
    CombatAction action = (CombatAction) command.getAction();
    assertThat(action.getType()).isEqualTo(ActionType.COMBAT);
    assertThat(action.getActionType()).isEqualTo(CombatActionType.ACTION);
    assertThat(action.getStat()).isEqualTo(STAT);
    assertThat(action.getTarget()).isEqualTo(TARGET);
    assertThat(action.getValue()).isEqualTo(20);
  }


  @Test
  void testPurchaseActionMapping() throws Exception {
    // Given
    GameActionRequest request = GameActionRequest.builder()
        .username(USERNAME)
        .character(CHARACTER)
        .description("Compra de Desfibriladores")
        .action(PurchaseActionDto.builder()
            .amount(PURCHASE_AMOUNT)
            .saleLocation(SALE_LOCATION)
            .item(ItemDto.builder().description("Reanimador para caidos").name("Desfibrilador")
                .price(2500L)
                .build())
            .build())
        .build();
    // When
    GameActionCommand command = mapper.toCommand(request, SESSION_ID);
    // Then
    assertThat(command).isNotNull();
    assertThat(command.getUsername()).isEqualTo(USERNAME);
    assertThat(command.getCharacter()).isEqualTo(CHARACTER);
    assertThat(command.getDescription()).isEqualTo("Compra de Desfibriladores");
    assertThat(command.getAction()).isInstanceOf(PurchaseAction.class);
    assertThat(command.getSessionId()).isEqualTo(SESSION_ID);
    PurchaseAction action = (PurchaseAction) command.getAction();
    assertThat(action.getType()).isEqualTo(ActionType.PURCHASE);
    assertThat(action.getAmount()).isEqualTo(PURCHASE_AMOUNT);
    assertThat(action.getSaleLocation()).isEqualTo(SALE_LOCATION);
    assertThat(action.getItem()).isNotNull();
    assertThat(action.getItem().getDescription()).isEqualTo("Reanimador para caidos");
    assertThat(action.getItem().getName()).isEqualTo("Desfibrilador");
    assertThat(action.getItem().getPrice()).isEqualTo(2500L);
  }


  @Test
  void testDiceRollActionMapping() throws Exception {
    // Given
    GameActionRequest request = GameActionRequest.builder()
        .username(USERNAME)
        .character(CHARACTER)
        .description("Prueba de Destreza: disparo a Niño Cometa")
        .action(DiceRollActionDto.builder()
            .stat(STAT)
            .challengeValue(14)
            .rollValue(15)
            .fateRollValues(List.of(-1, 1))
            .build())
        .build();
    // When
    GameActionCommand command = mapper.toCommand(request, SESSION_ID);
    // Then
    assertThat(command).isNotNull();
    assertThat(command.getUsername()).isEqualTo(USERNAME);
    assertThat(command.getCharacter()).isEqualTo(CHARACTER);
    assertThat(command.getDescription()).isEqualTo("Prueba de Destreza: disparo a Niño Cometa");
    assertThat(command.getAction()).isInstanceOf(DiceRollAction.class);
    assertThat(command.getSessionId()).isEqualTo(SESSION_ID);
    DiceRollAction action = (DiceRollAction) command.getAction();
    assertThat(action.getType()).isEqualTo(ActionType.DICE_ROLL);
    assertThat(action.getRollValue()).isEqualTo(15);
    assertThat(action.getStat()).isEqualTo(STAT);
    assertThat(action.getChallengeValue()).isEqualTo(14);
    assertThat(action.getFateRollValues()).hasSize(2).contains(-1, 1);
  }


  @Test
  void testInteractionActionMapping() throws Exception {
    // Given
    GameActionRequest request = GameActionRequest.builder()
        .username(USERNAME)
        .character(CHARACTER)
        .description("Escuchar detalles de la mision e instrucciones")
        .action(InteractionActionDto.builder()
            .interactionType("Dialogo")
            .interactionTarget("Morgan")
            .associatedStat("None")
            .value(0)
            .build())
        .build();
    // When
    GameActionCommand command = mapper.toCommand(request, SESSION_ID);
    // Then
    assertThat(command).isNotNull();
    assertThat(command.getUsername()).isEqualTo(USERNAME);
    assertThat(command.getCharacter()).isEqualTo(CHARACTER);
    assertThat(command.getDescription())
        .isEqualTo("Escuchar detalles de la mision e instrucciones");
    assertThat(command.getAction()).isInstanceOf(InteractionAction.class);
    assertThat(command.getSessionId()).isEqualTo(SESSION_ID);
    InteractionAction action = (InteractionAction) command.getAction();
    assertThat(action.getType()).isEqualTo(ActionType.INTERACTION);
    assertThat(action.getInteractionType()).isEqualTo("Dialogo");
    assertThat(action.getInteractionTarget()).isEqualTo("Morgan");
    assertThat(action.getAssociatedStat()).isEqualTo("None");
    assertThat(action.getValue()).isEqualTo(0);
  }


  @Test
  void testTravelActionMapping() throws Exception {
    // Given
    GameActionRequest request = GameActionRequest.builder()
        .username(USERNAME)
        .character(CHARACTER)
        .description("Escuchar detalles de la mision e instrucciones")
        .action(TravelActionDto.builder()
            .destination("Isla Espiral")
            .travelTime(10)
            .build())
        .build();
    // When
    GameActionCommand command = mapper.toCommand(request, SESSION_ID);
    // Then
    assertThat(command).isNotNull();
    assertThat(command.getUsername()).isEqualTo(USERNAME);
    assertThat(command.getCharacter()).isEqualTo(CHARACTER);
    assertThat(command.getDescription())
        .isEqualTo("Escuchar detalles de la mision e instrucciones");
    assertThat(command.getAction()).isInstanceOf(TravelAction.class);
    assertThat(command.getSessionId()).isEqualTo(SESSION_ID);
    TravelAction action = (TravelAction) command.getAction();
    assertThat(action.getType()).isEqualTo(ActionType.TRAVEL);
    assertThat(action.getDestination()).isEqualTo("Isla Espiral");
    assertThat(action.getTravelTime()).isEqualTo(10);
  }


}
