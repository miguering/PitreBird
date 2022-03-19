package com.mygdx.game;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameScreen implements Screen {

	final FlappyGame game;

        Texture backgroundImage;
        Texture backgroundImage2;
        Texture gameOverItem;
	Sound tuberiaSound;
        Sound jumpSound;
        Sound gameOverSound;
        Sound gameOverMusic;
	Music backgroundMusic;
	OrthographicCamera camera;
	Array<Tuberia> tuberias;
	long lastTuberiaTime;
	int tuberiasGathered;
        Pajaro pajarillo;
        boolean game_over;
        BitmapFont font;
        float posfondo;

	public GameScreen(final FlappyGame gam) {
		this.game = gam;
                this.game_over = false;
                backgroundImage = new Texture(Gdx.files.internal("fondo2.png"));
                backgroundImage2 = new Texture(Gdx.files.internal("fondo2.png"));
                jumpSound = Gdx.audio.newSound(Gdx.files.internal("jump2.mp3"));
		// load the drop sound effect and the rain background "music"
		tuberiaSound = Gdx.audio.newSound(Gdx.files.internal("oh.mp3"));
                gameOverSound = Gdx.audio.newSound(Gdx.files.internal("chua.wav"));
                gameOverMusic = Gdx.audio.newSound(Gdx.files.internal("gameover2.mp3"));
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("gameMusic.mp3"));
                gameOverItem = new Texture(Gdx.files.internal("gameover.png"));
		backgroundMusic.setLooping(true);
                posfondo = 0;
                font = new BitmapFont(Gdx.files.internal("karma.fnt"), false);
		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		// create a Rectangle to logically represent the pajarillo
                pajarillo = new Pajaro();

		// create the tuberias array and spawn the first raindrop
                
		tuberias = new Array<Tuberia>();
		spawnTuberia();

	}

	private void spawnTuberia() {
		Tuberia tuberia = new Tuberia();
                int pos_random = MathUtils.random(200, 400);
                int espacio = MathUtils.random(510, 600);
                
                tuberia.setY_superior(pos_random);
                tuberia.setX_superior(800);
                
                tuberia.setY_inferior(pos_random - espacio);
                tuberia.setX_inferior(800);
		tuberias.add(tuberia);
		lastTuberiaTime = TimeUtils.nanoTime();
	}
        


	@Override
	public void render(float delta) {
		// clear the screen with a dark blue color. The
		// arguments to clear are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		ScreenUtils.clear(0, 0, 0.2f, 1);

		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		game.batch.setProjectionMatrix(camera.combined);

		// begin a new batch and draw the pajarillo and
		// all drops
                
                
                pajarillo.generarPajaroImage();
		game.batch.begin();
                
                game.batch.draw(backgroundImage, posfondo,0);
                game.batch.draw(backgroundImage2, 800+posfondo,0);
                
                game.batch.draw(pajarillo.getPajaroImage(), pajarillo.getX(), pajarillo.getY());
		
		
		for (Tuberia tuberia : tuberias) {
			game.batch.draw(tuberia.getTuberiaImage1(), tuberia.getX_superior(), tuberia.getY_inferior());
                        game.batch.draw(tuberia.getTuberiaImage2(), tuberia.getX_superior(), tuberia.getY_superior());
		}
                font.draw(game.batch, "PUNTOS: " + tuberiasGathered, 10, 465);
                //game.font.draw(game.batch, "PuntuaciÃ³n: " + tuberiasGathered, 0, 480);
                
		game.batch.end();

		// process user input
                
                pajarillo.caer();
                
                if (Gdx.input.justTouched() && !pajarillo.isGameOver()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
                        pajarillo.saltar();
                        jumpSound.play();
		}
                else if (Gdx.input.isKeyJustPressed(Keys.ANY_KEY) && !pajarillo.isGameOver()){
                    pajarillo.saltar();
                    jumpSound.play();
                }
                
                if (pajarillo.getY() < 0){
			pajarillo.setY(0);
                        pajarillo.gameOver(true);
                }
		if (pajarillo.getY() > 480 - 64)
			pajarillo.setY(480-64);
                
                
                
                if(pajarillo.isGameOver()){
                    
                    //pajarillo.caer();
                    pajarillo.generarPajaroImage();
                    
                    game.batch.begin();
                
                    game.batch.draw(gameOverItem, 0,0);
                    //gameOverSound.play();
                    game.batch.end();
                    
                    backgroundMusic.stop();
                    
                    
                    if (Gdx.input.justTouched()) {
                        game.setScreen(new MainMenuScreen(game));
                        dispose();
                        
                    }else if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY)){
                        game.setScreen(new MainMenuScreen(game));
                        
                        dispose();
                        
                    }
                    
                    
                }else{

                    // Comprueba si necesitamos crear una nueva tuberia
                    long intervalo = (long) (1000000000 * 1.5) - (1000 * tuberiasGathered);
                    if (TimeUtils.nanoTime() - lastTuberiaTime > intervalo){
                        spawnTuberia();
                        Tuberia.VELOCIDAD = Tuberia.VELOCIDAD + 10;
                    }

                    // Mueve las tuberías, borra las que se sobrepasen los límites de la pantalla
                    
                    if(posfondo <= -800){
                        posfondo = 0;
                    }
                    posfondo = (float) (posfondo - 0.5);
                    
                    Iterator<Tuberia> iter = tuberias.iterator();
                    while (iter.hasNext()) {
                            Tuberia tuberia = iter.next();
                            tuberia.setX_superior(tuberia.getX_superior() -  tuberia.getVelocidad() * Gdx.graphics.getDeltaTime());
                            tuberia.setX_inferior(tuberia.getX_inferior() -  tuberia.getVelocidad() * Gdx.graphics.getDeltaTime());
                            if (tuberia.getX_superior() + 64 < 0){
                                    iter.remove();
                                    tuberiasGathered++;
                            }
                            if (tuberia.getTuberia_superior().overlaps(pajarillo.getPajaro())) {
                                tuberiaSound.play();
                                pajarillo.gameOver(true);
                                gameOverMusic.play();
                            }else if(tuberia.getTuberia_inferior().overlaps(pajarillo.getPajaro())) {
                                tuberiaSound.play();
                                pajarillo.gameOver(true);
                                gameOverMusic.play();
                            }
                    }
                
                }
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		// start the playback of the background music
		// when the screen is shown
                if(!pajarillo.isGameOver())
                    backgroundMusic.play();
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		pajarillo.getPajaroCayendo().dispose();
                pajarillo.getPajaroVolando().dispose();
		tuberiaSound.dispose();
                gameOverSound.dispose();
                gameOverMusic.dispose();
		backgroundMusic.dispose();
	}

}