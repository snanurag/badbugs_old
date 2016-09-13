package com.badbugs.dynamics.panel;

import com.badbugs.Game;
import com.badbugs.baseframework.elements.GameStates;
import com.badbugs.baseframework.elements.ObjectsStore;
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

    public static void updatePanelPosition(BasicObject panel, TouchInfo touchInfo) throws Exception {

        if(touchInfo == null) return;

        Vector3 touchPoints = Game.cam.unproject(new Vector3(touchInfo.touchX, touchInfo.touchY, 0));
        if (!isOpen && isPanelArrowTouched(touchPoints)) {
            isOpen = true;
            panelSpeed = - Constants.PANEL_SPEED;
        } else if (isOpen && (isPanelArrowTouched(touchPoints) || isStoneKnifeTouched(touchPoints) ||
                isBronzeKnifeTouched(touchPoints) || isSteelKnifeTouched(touchPoints))) {
            if (isStoneKnifeTouched(touchPoints)) {
                GameStates.setSelectedKnife(ObjectsStore.getKnife(Constants.KNIFE_TYPE.STONE));
            } else if (isBronzeKnifeTouched(touchPoints)) {
                GameStates.setSelectedKnife(ObjectsStore.getKnife(Constants.KNIFE_TYPE.BRONZE));
            } else if (isSteelKnifeTouched(touchPoints)) {
                GameStates.setSelectedKnife(ObjectsStore.getKnife(Constants.KNIFE_TYPE.STEEL));
            }
            isOpen = false;
            panelSpeed = Constants.PANEL_SPEED;
        }

        if (panelSpeed == 0) return;
        else {
            if(panelPosition[0] < Game.cam_width - Constants.PANEL_WIDTH)
            {
                panelPosition[0] = Game.cam_width - Constants.PANEL_WIDTH;
                panelSpeed = 0;
            }
                panelPosition[0] = panelPosition[0] + panelSpeed * Gdx.graphics.getDeltaTime();
                panel.getPolygon().setPosition(panelPosition[0], panelPosition[1]);
        }
    }

    private static boolean isStoneKnifeTouched(Vector3 touchPoints) {
        return GameStates.isBronzeKnifeAvailable() && touchPoints.x > panelPosition[0] + Constants
                .PANEL_STONE_KNIFE[0] && touchPoints.x < panelPosition[0] + Constants
                .PANEL_STONE_KNIFE[0] + Constants.STONE_KNIFE_WIDTH && touchPoints.y <
                panelPosition[1] +
                        Constants
                                .PANEL_STONE_KNIFE[1] && touchPoints.y > panelPosition[1] +
                Constants
                        .PANEL_STONE_KNIFE[1] - Constants.STONE_KNIFE_HEIGHT;

    }

    private static boolean isBronzeKnifeTouched(Vector3 touchPoints) {
        return GameStates.isBronzeKnifeAvailable() && touchPoints.x > panelPosition[0] + Constants
                .PANEL_BRONZE_KNIFE[0] && touchPoints.x < panelPosition[0] + Constants
                .PANEL_BRONZE_KNIFE[0] + Constants.BRONZE_KNIFE_WIDTH && touchPoints.y <
                panelPosition[1] +
                        Constants
                                .PANEL_BRONZE_KNIFE[1] && touchPoints.y > panelPosition[1] +
                Constants
                        .PANEL_BRONZE_KNIFE[1] - Constants.BRONZE_KNIFE_HEIGHT
                ;
    }

    private static boolean isSteelKnifeTouched(Vector3 touchPoints) {
        return GameStates.isSteelKnifeAvailable() && touchPoints.x > panelPosition[0] + Constants
                .PANEL_STEEL_KNIFE[0] && touchPoints.x < panelPosition[0] + Constants
                .PANEL_STEEL_KNIFE[0] + Constants.STEEL_KNIFE_WIDTH && touchPoints.y <
                panelPosition[1] +
                        Constants
                                .PANEL_STEEL_KNIFE[1] && touchPoints.y > panelPosition[1] +
                Constants
                        .PANEL_STEEL_KNIFE[1] - Constants.STEEL_KNIFE_HEIGHT
                ;
    }

    private static boolean isPanelArrowTouched(Vector3 touchPoints) {
        return touchPoints.x > panelPosition[0] - Constants.PANEL_ARROW_WIDTH && touchPoints.x <
                panelPosition[0] &&
                touchPoints.y < panelPosition[1] && touchPoints.y > panelPosition[1] - Constants.PANEL_ARROW_WIDTH;
    }
}
