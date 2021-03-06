package geekbrainscourse.libgdxgame.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import geekbrainscourse.libgdxgame.SpaceScroller;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(500, 720);
		config.useOpenGL3(true, 3, 3);
		new Lwjgl3Application(new SpaceScroller(), config);
	}
}
