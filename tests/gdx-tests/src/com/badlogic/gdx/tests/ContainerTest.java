
package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.tests.utils.GdxTest;
import com.badlogic.gdx.utils.ScreenUtils;

public class ContainerTest extends GdxTest {
	Skin skin;
	Stage stage;

	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		TextureRegionDrawable logo = new TextureRegionDrawable(
			new TextureRegion(new Texture(Gdx.files.internal("data/badlogic.jpg"))));

		Table root = new Table();
		root.setFillParent(true);
		root.debug().defaults().space(6).size(110);
		stage.addActor(root);

		root.add(new Container(label("center")));
		root.add(new Container(label("top")).top());
		root.add(new Container(label("right")).right());
		root.add(new Container(label("bottom")).bottom());
		root.add(new Container(label("left")).left());
		root.row();
		root.add(new Container(label("fill")).fill());
		root.add(new Container(label("fillX")).fillX());
		root.add(new Container(label("fillY")).fillY());
		root.add(new Container(label("fill 75%")).fill(0.75f, 0.75f));
		root.add(new Container(label("fill 75% br")).fill(0.75f, 0.75f).bottom().right());
		root.row();
		root.add(new Container(label("padTop 5\ntop")).padTop(5).top());
		root.add(new Container(label("padBottom 5\nbottom")).padBottom(5).bottom());
		root.add(new Container(label("padLeft 15")).padLeft(15));
		root.add(new Container(label("pad 10 fill")).pad(10).fill());
		root.add(new Container(label("pad 10 tl")).pad(10).top().left());
		root.row();
		root.add(new Container(label("bg")).background(logo));
		root.add(new Container(label("bg height 50")).background(logo).height(50));

		Container transformBG = new Container(label("bg transform")).background(logo);
		transformBG.setTransform(true);
		transformBG.setOrigin(55, 55);
		transformBG.rotateBy(90);
		root.add(transformBG);

		Container transform = new Container(label("transform"));
		transform.setTransform(true);
		transform.setOrigin(55, 55);
		transform.rotateBy(90);
		root.add(transform);

		Container clip = new Container(label("clip1clip2clip3clip4"));
		clip.setClip(true);
		root.add(clip);

		root.row();

		Table cullTable = new Table();
		cullTable.add(label("cull1")).row();
		cullTable.add(label("cull2")).row();
		cullTable.add(label("cull3")).row();
		cullTable.add(label("cull4")).row();
		cullTable.add(label("cull5")).row();
		cullTable.add(label("cull6")).row();
		cullTable.add(label("cull7")).row();
		root.add(new ScrollPane(new Container(cullTable).pad(20, 25, 15, 30)));
	}

	Table label (String text) {
		Table table = new Table().debug();
		table.add(new Label(text, skin)).grow();
		return table;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
		stage.act();
		stage.draw();
	}

	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void dispose () {
		stage.dispose();
		skin.dispose();
	}
}
