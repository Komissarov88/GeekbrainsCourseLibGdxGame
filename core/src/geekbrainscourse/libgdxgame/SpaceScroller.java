package geekbrainscourse.libgdxgame;

import com.badlogic.gdx.Game;

import geekbrainscourse.libgdxgame.screen.MenuScreen;

public class SpaceScroller extends Game {

	@Override
	public void create () {
		setScreen(new MenuScreen(this));
	}
}
