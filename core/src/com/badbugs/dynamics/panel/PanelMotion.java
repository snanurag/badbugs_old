package com.badbugs.dynamics.panel;

import com.badbugs.Game;
import com.badbugs.baseframework.elements.GameStates;
import com.badbugs.baseframework.elements.ObjectsStore;
import com.badbugs.listers.Inputs;
import com.badbugs.objects.BasicObject;
import com.badbugs.util.Constants;
import com.badbugs.util.TouchInfo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by ashrinag on 9/10/2016.
 */
public class PanelMotion {

    // Starting position is top right corner
    private static float[] panelPosition = {Game.cam_width/2, Game.cam_height/2};

    private static boolean isOpen = false;
    private static float panelSpeed = 0;

    public static boolean panelTriggered(TouchInfo touchInfo) throws Exception {

        boolean triggered = false;
        if(touchInfo != null){
            Vector3 touchPoints = Game.cam.unproject(new Vector3(touchInfo.touchX, touchInfo.touchY, 0));
            if (!isOpen && (isPanelArrowTouched(touchPoints)|| Inputs.leftSwipe)) {
                isOpen = true;
                triggered = true;
                panelSpeed = - Constants.PANEL_SPEED;
                Inputs.leftSwipe = false;
            } else if (isOpen && (isPanelArrowTouched(touchPoints) || isStoneKnifeTouched(touchPoints) ||
                    isBronzeKnifeTouched(touchPoints) || isSteelKnifeTouched(touchPoints))) {
                isOpen = false;
                panelSpeed = Constants.PANEL_SPEED;
                triggered = true;
                Inputs.leftSwipe = false;
            }
        }

        return triggered;
    }

    public static void updatePanelState(BasicObject panel, TouchInfo touchInfo) throws Exception
    {
        if (panelSpeed == 0) return;
        else {
            float x = panel.getPolygon().getX();
            float y = panel.getPolygon().getY();
            if (panelPosition[0] < Game.cam_width / 2 - Constants.PANEL_WIDTH + Constants.PANEL_ARROW_WIDTH) {
                panelPosition[0] = Game.cam_width / 2 - Constants.PANEL_WIDTH + Constants.PANEL_ARROW_WIDTH;
                x = Game.cam_width / 2 - Constants.PANEL_WIDTH;
                panelSpeed = 0;
            }
            if(panelPosition[0] > Game.cam_width/2) {
                panelPosition[0] = Game.cam_width / 2;
                x = Game.cam_width / 2 - Constants.PANEL_ARROW_WIDTH;
                panelSpeed = 0;
            }
            panelPosition[0] = panelPosition[0] + panelSpeed * Gdx.graphics.getDeltaTime();
            x = x + panelSpeed * Gdx.graphics.getDeltaTime();
            panel.getPolygon().setPosition(x, y);
        }
    }

    private static boolean isStoneKnifeTouched(Vector3 touchPoints) {

        boolean isTouched = touchPoints.x > panelPosition[0] + Constants
                .PANEL_STONE_KNIFE[0] && touchPoints.x < panelPosition[0] + Constants
                .PANEL_STONE_KNIFE[0] + (Constants.PANEL_WIDTH - Constants.PANEL_ARROW_WIDTH) && touchPoints.y <
                panelPosition[1] - Constants.PANEL_STONE_KNIFE[1] && touchPoints.y > panelPosition[1] -
                Constants.PANEL_STONE_KNIFE[1] - Constants.STONE_KNIFE_WIDTH;
        if(isTouched){
            GameStates.setSelectedKnife(Constants.KNIFE_TYPE.STONE);
            if (GameStates.isBronzeKnifeAvailable() && GameStates.isSteelKnifeAvailable())
                GameStates.setPanel(Constants.PANEL.BRONZE_STEEL);
            else if (GameStates.isBronzeKnifeAvailable()) GameStates.setPanel(Constants.PANEL.BRONZE);
            else if (GameStates.isSteelKnifeAvailable()) GameStates.setPanel(Constants.PANEL.STEEL);
            else GameStates.setPanel(Constants.PANEL.EMPTY);
//            GameStates.setSelectedKnife(ObjectsStore.getKnife(Constants.KNIFE_TYPE.STONE));
            return true;
        }
        return false;
    }

    private static boolean isBronzeKnifeTouched(Vector3 touchPoints) {
        boolean isTouched = GameStates.isBronzeKnifeAvailable() && touchPoints.x > panelPosition[0] + Constants
                .PANEL_BRONZE_KNIFE[0] && touchPoints.x < panelPosition[0] + Constants
                .PANEL_BRONZE_KNIFE[0] + (Constants.PANEL_WIDTH - Constants.PANEL_ARROW_WIDTH) && touchPoints.y <
                panelPosition[1] - Constants.PANEL_BRONZE_KNIFE[1] && touchPoints.y > panelPosition[1] - Constants
                .PANEL_BRONZE_KNIFE[1] - Constants.BRONZE_KNIFE_WIDTH;
        if(isTouched){
            GameStates.setSelectedKnife(Constants.KNIFE_TYPE.BRONZE);
            if (GameStates.isSteelKnifeAvailable()) GameStates.setPanel(Constants.PANEL.STONE_STEEL);
            else GameStates.setPanel(Constants.PANEL.STONE);
//            GameStates.setSelectedKnife(ObjectsStore.getKnife(Constants.KNIFE_TYPE.BRONZE));
            return true;
        }
        return false;
    }

    private static boolean isSteelKnifeTouched(Vector3 touchPoints) {
        boolean isTouched = GameStates.isSteelKnifeAvailable() && touchPoints.x > panelPosition[0] + Constants
                .PANEL_STEEL_KNIFE[0] && touchPoints.x < panelPosition[0] + Constants
                .PANEL_STEEL_KNIFE[0] + (Constants.PANEL_WIDTH - Constants.PANEL_ARROW_WIDTH) && touchPoints.y <
                panelPosition[1] - Constants.PANEL_STEEL_KNIFE[1] && touchPoints.y > panelPosition[1] - Constants
                .PANEL_STEEL_KNIFE[1] - Constants.STEEL_KNIFE_WIDTH - 4;
        if(isTouched){
            GameStates.setSelectedKnife(Constants.KNIFE_TYPE.STEEL);
            if(GameStates.isBronzeKnifeAvailable()) GameStates.setPanel(Constants.PANEL.STONE_BRONZE);
            else GameStates.setPanel(Constants.PANEL.STONE);
//            GameStates.setSelectedKnife(ObjectsStore.getKnife(Constants.KNIFE_TYPE.STEEL));
            return true;
        }
        return false;
    }

    private static boolean isPanelArrowTouched(Vector3 touchPoints) {
        return touchPoints.x >= panelPosition[0] - Constants.PANEL_ARROW_WIDTH - .5F && touchPoints.x <=
                panelPosition[0] &&
                touchPoints.y <= panelPosition[1] && touchPoints.y >= panelPosition[1] - Constants.PANEL_ARROW_HEIGHT - .5F;
    }
}
