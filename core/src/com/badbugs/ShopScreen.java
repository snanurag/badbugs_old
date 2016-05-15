package com.badbugs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by ashrinag on 5/14/2016.
 */
public class ShopScreen extends ScreenAdapter {

  public ShopScreen() {
    create();
  }

  @Override public void render(float delta) {
    render();
  }
  public void render()
  {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.act(Gdx.graphics.getDeltaTime());
    stage.draw();

  }
  private Stage stage;
  private Table container;

  public void create() {
    stage = new Stage();
    Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
    Gdx.input.setInputProcessor(stage);

    // Gdx.graphics.setVSync(false);

    container = new Table();
    stage.addActor(container);
    container.setFillParent(true);

    Table table = new Table();

    final ScrollPane scroll = new ScrollPane(table);

    table.row();

    ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle(skin.get(Button.ButtonStyle.class));
    Texture texture1 = new Texture(Gdx.files.internal("data/badlogic.jpg"));
    TextureRegion image = new TextureRegion(texture1);
    style.imageUp = new TextureRegionDrawable(image);
    TextureRegion imageFlipped = new TextureRegion(image);
    imageFlipped.flip(true, true);
    style.imageDown = new TextureRegionDrawable(imageFlipped);
    ImageButton iconButton = new ImageButton(style);

    table.add(iconButton);
    iconButton.addListener(new ClickListener() {
      public void clicked(InputEvent event, float x, float y) {

      }
    });

    Label doubleSpeedStatement = new Label("Increase knife speed to double.", skin);
    doubleSpeedStatement.setFontScale(3);
    table.add(doubleSpeedStatement);

    container.add(scroll).expand().fill().colspan(4);
    container.row().space(10).padBottom(10);
  }

  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);

    // Gdx.gl.glViewport(100, 100, width - 200, height - 200);
    // stage.setViewport(800, 600, false, 100, 100, width - 200, height - 200);
  }

  public void dispose() {
    stage.dispose();
  }

  public boolean needsGL20() {
    return false;
  }
}

