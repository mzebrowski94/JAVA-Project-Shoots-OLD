package game.controllers.impl;

import game.consts.MenuNavigationKey;
import game.consts.MenuOption;
import game.consts.RoundPhase;
import game.controllers.InputController;
import game.controllers.MenuController;
import game.drawables.factories.MenuDrawableFactory;
import game.drawables.menu.MenuDrawable;
import game.events.impl.RoundPhaseUpdateEvent;
import game.gui.service.GraphicsController;
import game.services.MenuLogicService;
import game.settings.MutualGameSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;
import java.game.events.impl.MenuOptionUpdateEvent;
import java.util.Objects;


/**
 * @author Mateusz Żebrowski
 */
public class MenuControllerImpl implements MenuController {

    @Autowired
    private InputController inputController;

    @Autowired
    private GraphicsController graphicsController;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MenuLogicService menuLogicService;

    @Autowired
    private MenuDrawableFactory menuDrawableFactory;

    private MenuDrawable menuDrawable;

    private RoundPhase actualRoundPhase;

    @PostConstruct
    private void init() {
        menuDrawable = menuDrawableFactory.produceMenuDrawable();
    }

    @Override
    public void submitActualOption() {
        RoundPhase roundPhase = null;

        MenuOption option = menuLogicService.getActuallyPointedOption();
        MutualGameSettings menuSettings = MutualGameSettings.getEmpty();

        if (option == MenuOption.CONTINUE) {
            roundPhase = RoundPhase.CONTINUES;
        } else if (option == MenuOption.START_NEW_GAME) {
            menuSettings = menuLogicService.getMenuSettings();
            roundPhase = RoundPhase.BEGINS;
        } else if (option == MenuOption.QUIT) {
            roundPhase = RoundPhase.ENDS;
        }

        if (Objects.nonNull(roundPhase)) {
            publisher.publishEvent(new RoundPhaseUpdateEvent(roundPhase, menuSettings));
        }
    }

    @Override
    public void update() {
        if (inputController.isExitKeyPressed() && actualRoundPhase == RoundPhase.PAUSED) {
            publisher.publishEvent(new RoundPhaseUpdateEvent(RoundPhase.UNPAUSED));
        } else if (inputController.isSubmitKeyPressed()) {
            submitActualOption();
        } else if (Objects.nonNull(inputController.getMenuNavigationKeyPressed())) {
            updateState(inputController.getMenuNavigationKeyPressed());
        }
    }

    @Override
    public void updateGraphics() {
        MenuOption actuallyPointedOption = menuLogicService.getActuallyPointedOption();
        MutualGameSettings menuSettings = menuLogicService.getMenuSettings();

        menuDrawable.update(actuallyPointedOption, menuSettings, actualRoundPhase);

        graphicsController.addToDrawQueue(menuDrawable);
    }

    private void updateState(MenuNavigationKey menuNavigationKey) {
        menuLogicService.updateMenuState(menuNavigationKey);
    }

    @Override
    @EventListener
    public void MenuOptionUpdate(MenuOptionUpdateEvent menuOptionUpdateEvent) {
        menuLogicService.setActuallyPointedOption(menuOptionUpdateEvent.getMenuOption());
    }


    @Override
    public void handleRoundPhaseUpdate(RoundPhaseUpdateEvent roundPhaseUpdateEvent) {
        actualRoundPhase = roundPhaseUpdateEvent.getRoundPhase();
    }
}
