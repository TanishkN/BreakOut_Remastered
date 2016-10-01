// The "BreakOutRemasteredFinal" class.
/*


BreakOutRemasteredFinal

Created by: Tanishk Nalamolu and Mohid Qureshi



MIN MEMORY FOR JVM: 900mb

Full screen necessary,

*/
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.lang.*;


public class BreakOutRemasteredFinalTanishk_Mohid extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener
{
    // Place instance variables here
    // Initialization of variables

    int appletsize_x = 800; //Width of the screen game will be played on
    int appletsize_y = 800;

    //GAME VARIABLES
    int x_pos = 300;         // x - Position of ball
    int y_pos = 400;        // y - Position of ball

    int x_posR = 300; // x - Position of paddle
    int y_posR = 600; // y - Position of paddle

    int radius = 20;        // Radius of ball

    int x_speed = 10; // Speed of paddle

    int xBall_Speed = 10; // x Speed of ball
    int yBall_Speed = 10; // y Speed of ball

    int PADDLE_HEIGHT = 30; //Height of paddle
    int PADDLE_WIDTH = 150; //Width of paddle

    int PICTURE_PADDLE_HEIGHT = 25; //Height of the paddle picture
    int PICTURE_PADDLE_WIDTH = 200; //Width of the paddle picture

    int BUFFER = 5;

    int brickCount = 0; //The number of bricks user hit

    int randomPosBallx;
    int points = 0;
    int lives = 3;

    int BLOCK_HEIGHT = 15;
    int BLOCK_WIDTH = 45;
    //2d array representing the postitions of each of the blocks
    int blocks[] [] = {{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0},
	    {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {3, 0, 0, 5, 0, 0, 0, 0, 0, 0, 5, 0, 2, 0, 0, 0, 0, 0, 5, 0, 0, 3},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 5, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 2, 0, 5, 0},
	    {0, 0, 2, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0}};



    // Image declerations for Menu

    public Image mainMenu;
    public Image mainMenu_PLAY;
    public Image mainMenu_OPTIONS;

    public Image mainMenu_CREDITS;

    public Image PlayerOption;
    public Image PlayerOption_CLASSICMODE;
    public Image PlayerOption_SUDDENDEATH;
    public Image gameOver;
    public Image gameWin;
    public Image theGameStore;
    public Image MainMenu_STORE;
    public Image Options;
    public Image Credits;


    boolean running = false;

    int Pic = 0; //What integer Pic will be set to, so picture shows up

    //Location of the Menu keys

    boolean menu1 = true; //If original menu appears.(Starts off as menu1, so will be default true)
    boolean menu_Play = false; // Stays depending if the play button was clicked
    boolean menu_Classic = false;
    boolean menu_Sudden = false;
    boolean options = false;

    private Image dbImage;
    private Graphics dbg;

    Image img;
    MediaTracker tr;
    private final String PICTURE_PATH = "Paddle.jpg";
    private final String PICTURE_BRICK = "ball.jpg";
    private final String PICTURE_LIVE = "heart.jpg";
    private final String PICTURE_BRICK_IM = "brickIM.jpg";
    private final String PICTURE_BRICK_UB = "brickUB.jpg";

    Image picture;
    Image picture1;
    Image picture2;
    Image picture3;
    Image picture4;
    int picWidth, picHeight;

    AudioClip mainMenuMusic;


    public void init ()
    {
	resize (800, 800);
	//super.init ();
	// Place the body of the initialization method here
	//setBackground (Color.black);
	mainMenu = getImage (getDocumentBase (), "MainMenu.jpg"); //https://www.youtube.com/watch?v=csKGwM6SkvQ FOR IMPORTING IMAGES
	mainMenu_PLAY = getImage (getDocumentBase (), "MainMenu_PLAY.jpg");
	mainMenu_OPTIONS = getImage (getDocumentBase (), "MainMenu_OPTIONS.jpg");
	MainMenu_STORE = getImage (getDocumentBase (), "MainMenu_STORE.jpg");
	mainMenu_CREDITS = getImage (getDocumentBase (), "MainMenu_CREDITS.jpg");

	theGameStore = getImage (getDocumentBase (), "theGameStore.jpg");
	Options = getImage (getDocumentBase (), "Options.jpg");

	PlayerOption = getImage (getDocumentBase (), "PlayerOption.jpg");
	PlayerOption_CLASSICMODE = getImage (getDocumentBase (), "PlayerOption_CLASSICMODE.jpg");
	PlayerOption_SUDDENDEATH = getImage (getDocumentBase (), "PlayerOption_SUDDENDEATH.jpg");

	gameOver = getImage (getDocumentBase (), "gameOver.jpg");
	gameWin = getImage (getDocumentBase (), "gameWin.jpg");
	Credits = getImage (getDocumentBase (), "Credits.jpg");

	mainMenuMusic = getAudioClip (getCodeBase (), "Transistor.au");

	mainMenuMusic.loop ();

	if (running)
	{
	    mainMenuMusic.loop ();
	}


	// Place the body of the initialization method here
	setBackground (Color.black);
	//Toolkiting the Images
	picture = getToolkit ().getImage (PICTURE_PATH);
	picture1 = getToolkit ().getImage (PICTURE_BRICK);
	picture2 = getToolkit ().getImage (PICTURE_LIVE);
	picture3 = getToolkit ().getImage (PICTURE_BRICK_IM);
	picture4 = getToolkit ().getImage (PICTURE_BRICK_UB);
	prepareImage (picture, this);
	prepareImage (picture1, this);
	prepareImage (picture2, this);
	prepareImage (picture3, this);
	prepareImage (picture4, this);
	// Add the picture to the list of images to be tracked
	MediaTracker tracker = new MediaTracker (this);
	MediaTracker tracker1 = new MediaTracker (this);
	tracker.addImage (picture, 0);
	try
	{
	    tracker.waitForAll ();
	}
	catch (InterruptedException e)
	{
	}
	// If there were any errors loading the image, then abort the
	// program with a message.
	if (tracker.isErrorAny ())
	{
	    showStatus ("Couldn't load " + PICTURE_PATH);
	    return;
	}

	tracker1.addImage (picture1, 0);
	try
	{
	    tracker1.waitForAll ();
	}
	catch (InterruptedException e)
	{
	}
	// If there were any errors loading the image, then abort the
	// program with a message.
	if (tracker1.isErrorAny ())
	{
	    showStatus ("Couldn't load " + PICTURE_BRICK);
	    return;
	}
	addMouseListener (this);
	addMouseMotionListener (this);
	addKeyListener (this);
    } // init method





    public void mousePressed (MouseEvent e)
    {
    }


    public void mouseReleased (MouseEvent e)
    {
    }


    //Mouse motion interface
    public void mouseDragged (MouseEvent e)
    {
    }


    //Tracks the mouse movement
    public void mouseMoved (MouseEvent e)
    {
	int x = e.getX (); //X location of button click
	int y = e.getY (); //Y location of button click
	showStatus ("x: " + x + " y: " + y); //For easing process of calculating location of buttons
	//Hover over Play in the first menu

	if (running != true)
	{
	    if (menu1 == true)
	    {
		//If mouse is set in the location of the PLAY button
		if (x > 200 && x < 570 && y > 300 && y < 400)
		    Pic = 2;
		else
		    Pic = 1;
	    }

	    //If hovers over OPTION in the first menu
	    if (menu1 == true)
	    {
		if (x > 180 && x < 640 && y > 420 && y < 500)
		    Pic = 5;

	    }

	    //If hovers over CREDITS in the first menu
	    if (menu1 == true)
	    {
		if (x > 150 && x < 640 && y > 650 && y < 750)
		    Pic = 6;
	    }

	    //If hovers over STORE in the first menu
	    if (menu1 == true)
	    {
		if (x > 220 && x < 580 && y > 550 && y < 600)
		    Pic = 9;

	    }

	    if (menu_Play == true)
	    {
		//Hover over CLASSICMODE inside the player Option menu
		if (x > 0 && x < 850 && y > 0 && y < 370)
		{
		    Pic = 4;
		}
		//Hover over SUDDENDEATH inside the player Option menu
		else if (x > 0 && x < 850 && y > 370 && y < 850)
		{
		    Pic = 7;
		}
		else
		    Pic = 3;
	    }
	}
    }



    //Mouse clicked movement
    public void mouseClicked (MouseEvent e)
    {
	int x = e.getX (); //X location of button click
	int y = e.getY (); //Y location of button click
	if (running != true)
	{
	    //When the PLAY button is clicked
	    if (x > 200 && x < 570 && y > 300 && y < 400)
	    {
		Pic = 3;
		menu1 = false; //Changes boolean, and makes it so its not on the first(main menu) screen anymore
		menu_Play = true; //changes the boolean, and makes it so its on the PLAY screen

	    }

	    if (menu_Play == true) //&& menu_Classic == false
	    {
		//CLASSIC MODE CLICK
		if (x > 0 && x < 850 && y > 0 && y < 300) //yshouldbe370
		{
		    Pic = 8;
		    running = true;
		    menu_Classic = true;
		    appletsize_x = 1400;

		}

		//SUDDEN MODE CLICK
		if (x > 0 && x < 850 && y > 370 && y < 850) //yshouldbe370
		{
		    Pic = 8;
		    running = true;
		    menu_Sudden = true;
		    appletsize_x = 1400;

		}
	    }

	    if (menu1 == true)
	    {

		//If STORE is clicked in the first menu

		if (x > 220 && x < 580 && y > 550 && y < 600)
		{
		    Pic = 10;
		    menu1 = false;
		    running = false;
		    menu_Classic = false;
		}
		//If OPTION is clicked in the first menu

		if (x > 180 && x < 640 && y > 420 && y < 500)
		{
		    Pic = 11;
		    menu1 = false;
		    running = false;
		    menu_Classic = false;
		    options = true;

		}

		//If CREDITS is clicked in the first menu
		if (x > 150 && x < 640 && y > 650 && y < 750)
		{
		    Pic = 14;
		    menu1 = false;
		    running = false;
		    menu_Classic = false;
		    //FILE TOO LARGE TO APPEAR ON SCHOOL COMPUTERS

		}
	    }

	    //When options is clicked
	    //when play is clicked inside options/rules to go directly to game
	    if (x > 257 && x < 550 && y > 600 && y < 680)
	    {
		Pic = 14;
		menu1 = false;
		options = false;
		menu_Play = true;
		Pic = 3;

	    }



	}
    }




    public void mouseEntered (MouseEvent e)
    {

    }


    public void mouseExited (MouseEvent e)
    {
	//msg = "Exited";
    }


    public void start ()
    {
	if (menu_Classic != true)
	{
	    // define a new thread
	    Thread th = new Thread (this);
	    // start this thread
	    th.start ();
	}
    }


    public void stop ()
    {
	//nothing right now
    }


    public void destroy ()
    {
	//nothing right now
    }


    /** Update - Method, implements double buffering */
    public void update (Graphics g)
    {
	if (running != true)
	{
	    paint (g);
	}


	// initialize buffer
	if (dbImage == null)
	{
	    dbImage = createImage (10000, 10000);
	    dbg = dbImage.getGraphics ();
	}


	// clear screen in background
	dbg.setColor (getBackground ());
	dbg.fillRect (0, 0, 10000, 1000);

	// draw elements in background
	dbg.setColor (getForeground ());
	paint (dbg);

	// draw image on the screen
	g.drawImage (dbImage, 0, 0, this);



    }



    //Check to see where mouse, including button clicks is to output corresponding icons
    public void keyTyped (KeyEvent e)
    {

    }


    public void keyPressed (KeyEvent e)
    {

	//when the left ke is hit move the paddle -65
	if (e.getKeyCode () == 37)
	{
	    x_posR = x_posR - 65;
	    repaint ();
	}

	//when the right key is hit move the paddle +65
	else if (e.getKeyCode () == 39)
	{

	    x_posR = x_posR + 65;
	    repaint ();
	}
    }


    public void keyReleased (KeyEvent e)
    {


    }


    public void run ()
    {
	//lower ThreadPriority
	Thread.currentThread ().setPriority (Thread.MAX_PRIORITY);

	while (true)
	{
	    repaint ();

	    if (menu_Classic == true)
	    {

		Random generator = new Random ();
		// higher ThreadPriority
		//Thread.currentThread ().setPriority (Thread.MAX_PRIORITY);
		// run a long while (true) this means in our case "always"
		while (lives > 0)
		{
		    //if the ball pos divided by the brick size and less than array size check if value is equal to 0
		    if (x_pos / 70 < 22 && y_pos / 30 < 8)
		    {
			if (blocks [y_pos / 30] [x_pos / 70] == 0)
			{
			    //if the ball pos divided by the dimentions of the blocks inside the array is equal to 0 make the yspeed of the ball negative
			    yBall_Speed = -yBall_Speed;
			    brickCount++;
			    //when it is equal to 0 make that block equal to 1
			    blocks [y_pos / 30] [x_pos / 70] = 1;
			    points = points + 12;
			}
			//when the array value is 5 the block is unbreakable
			else if (blocks [y_pos / 30] [x_pos / 70] == 5)
			{
			    yBall_Speed = -yBall_Speed;
			    brickCount++;
			}
			//when the array value is 2 the block gives u an extra heart
			else if (blocks [y_pos / 30] [x_pos / 70] == 2)
			{
			    lives = lives + 1;
			    blocks [y_pos / 30] [x_pos / 70] = 1;
			    brickCount++;
			}
			//when the array value is equal to 3 make the ball move slower
			else if (blocks [y_pos / 30] [x_pos / 70] == 3)
			{
			    // yBall_Speed = 3;
			    xBall_Speed = 3;
			    blocks [y_pos / 30] [x_pos / 70] = 1;
			    brickCount++;
			}

		    }

		    //random x pos for ball when it goes out of range
		    randomPosBallx = generator.nextInt (1200);
		    //paddle bounces back if it goes pass the screen
		    if (x_posR > appletsize_x - 50)
			x_posR = x_posR - 155;
		    if (x_posR < -55)
			x_posR = x_posR + 155;

		    //bounces back against wall if greater than the applet size of x
		    if (x_pos > appletsize_x + 150)
			// Change direction of ball movement
			xBall_Speed = -10;

		    //bounces back if greater than size of radius of the ball
		    else if (x_pos < radius)
			xBall_Speed = +10;
		    //if ball x pos plus radius plus buffer is greater than paddle x pos and ball x pos less than x pos of paddle plus width of paddle, radius and buffer check y positions
		    if (x_pos + radius + BUFFER > x_posR && x_pos < x_posR + PICTURE_PADDLE_WIDTH + BUFFER)
		    {
			//when the ball y pos is equal to the paddle y pos plus the radius subtracting the paddle height change speed in the other direction
			if (y_pos == y_posR + radius - PICTURE_PADDLE_HEIGHT - 65)
			{
			    yBall_Speed = -10;
			    points = points + 10;
			}
		    }

		    //respawn ball when it goes past a sutiabe part of the screen
		    if (y_pos > 750)
		    {
			//delay of half a second when the ball goes off the screen
			try
			{
			    // Stop thread for 500 milliseconds
			    Thread.sleep (500);
			}
			catch (InterruptedException ex)
			{
			    // do nothing
			}
			x_pos = x_posR;
			y_pos = y_posR - 200;
			xBall_Speed = 10;
			yBall_Speed = 10;
			lives--;

			if (lives == 0)
			{
			    Pic = 12;
			}
			else if (brickCount == 134)
			{

			}
		    }
		    // updates the ball pos according to the speeds
		    x_pos = xBall_Speed + x_pos;
		    y_pos = yBall_Speed + y_pos;

		    // repaint the applet
		    repaint ();
		    try
		    {
			// Stop thread for 20 milliseconds
			Thread.sleep (20);
		    }
		    catch (InterruptedException ex)
		    {
			// do nothing
		    }
		    // set ThreadPriority to maximum value
		    Thread.currentThread ().setPriority (Thread.MIN_PRIORITY);
		}
	    }

	    if (menu_Sudden == true)
	    {
		while (lives > 0)
		{
		    //if the ball pos divided by the brick size and less than array size check if value is equal to 0
		    lives = 1;
		    if (x_pos / 70 < 22 && y_pos / 30 < 8)
		    {
			if (blocks [y_pos / 30] [x_pos / 70] == 0)
			{
			    //if the ball pos divided by the dimentions of the blocks inside the array is equal to 0 make the yspeed of the ball negative
			    yBall_Speed = -yBall_Speed;
			    brickCount++;
			    //when it is equal to 0 make that block equal to 1
			    blocks [y_pos / 30] [x_pos / 70] = 1;
			    points = points + 12;
			}
			//when the array value is 5 the block is unbreakable
			else if (blocks [y_pos / 30] [x_pos / 70] == 5)
			{
			    yBall_Speed = -yBall_Speed;
			}
			//when the array value is 2 the block gives u an extra heart
			else if (blocks [y_pos / 30] [x_pos / 70] == 2)
			{
			    yBall_Speed = -yBall_Speed;
			    brickCount++;
			    //when it is equal to 0 make that block equal to 1
			    blocks [y_pos / 30] [x_pos / 70] = 1;
			    points = points + 12;
			}
			//when the array value is equal to 3 make the ball move slower
			else if (blocks [y_pos / 30] [x_pos / 70] == 3)
			{
			    yBall_Speed = -yBall_Speed;
			    brickCount++;
			    //when it is equal to 0 make that block equal to 1
			    blocks [y_pos / 30] [x_pos / 70] = 1;
			    points = points + 12;
			}

		    }

		    //paddle bounces back if it goes pass the screen
		    //if (y_pos < 15)
		    // x_pos = x_pos - 150;
		    if (x_posR > appletsize_x - 50)
			x_posR = x_posR - 155;
		    if (x_posR < -55)
			x_posR = x_posR + 155;


		    //bounces back against wall if greater than the applet size of x
		    if (x_pos > appletsize_x + 150)
			// Change direction of ball movement
			xBall_Speed = -10;

		    //bounces back if greater than size of radius of the ball
		    else if (x_pos < radius)
			xBall_Speed = +10;
		    //if ball x pos plus radius plus buffer is greater than paddle x pos and ball x pos less than x pos of paddle plus width of paddle, radius and buffer check y positions
		    if (x_pos + radius + BUFFER > x_posR && x_pos < x_posR + PICTURE_PADDLE_WIDTH + BUFFER)
		    {
			//when the ball y pos is equal to the paddle y pos plus the radius subtracting the paddle height change speed in the other direction
			if (y_pos == y_posR + radius - PICTURE_PADDLE_HEIGHT - 65)
			{
			    yBall_Speed = -10;
			    points = points + 10;
			}
		    }
		    //respawn ball when it goes past a sutiabe part of the screen
		    if (y_pos > 750)
		    {
			try
			{
			    // Stop thread for 500 milliseconds
			    Thread.sleep (500);
			}


			catch (InterruptedException ex)
			{
			    // do nothing
			}
			x_pos = randomPosBallx;
			y_pos = 300;
			xBall_Speed = 10;
			yBall_Speed = 10;
			lives--;

			//Game Over Screen
			Pic = 12;
		    }
		    // updates the ball pos according to the speeds
		    x_pos = xBall_Speed + x_pos;
		    y_pos = yBall_Speed + y_pos;
		    // repaint the applet
		    repaint ();
		    try
		    {
			// Stop thread for 20 milliseconds
			Thread.sleep (20);
		    }
		    catch (InterruptedException ex)
		    {
			// do nothing
		    }
		    // set ThreadPriority to maximum value
		    Thread.currentThread ().setPriority (Thread.MIN_PRIORITY);
		}

	    }

	}
    }


    public void Credits (Graphics g)
    {
	this.Credits = Credits;
	g.drawImage (Credits, 0, 0, appletsize_x, appletsize_y, null);
    }


    public void resultScreen (Graphics g)
    {
	g.setColor (Color.black);
	g.fillRect (0, 0, 10000, 10000);
	g.setColor (Color.red);
	Font font1 = new Font ("Bauhaus", 0, 50);
	g.setFont (font1);
	g.drawString ("Your Score: " + points, appletsize_x / 2 + appletsize_x / 6, appletsize_y / 2);
    }


    public void Options (Graphics g)
    {
	this.Options = Options;
	g.drawImage (Options, 0, 0, appletsize_x, appletsize_y, null);
    }


    public void MainMenu_STORE (Graphics g)
    {
	this.MainMenu_STORE = MainMenu_STORE;
	g.drawImage (MainMenu_STORE, 0, 0, appletsize_x, appletsize_y, null);
    }


    public void gameOver (Graphics g)
    {
	this.gameOver = gameOver;
	g.drawImage (gameOver, 0, 0, appletsize_x / 2, appletsize_y, null);
    }


    public void gameWin (Graphics g)
    {
	this.gameWin = gameWin;
	g.drawImage (gameWin, 0, 0, appletsize_x, appletsize_y, null);
    }



    public void GameStore (Graphics g)
    {
	this.theGameStore = theGameStore;
	g.drawImage (theGameStore, 0, 0, appletsize_x, appletsize_y, null);
    }


    public void menuMethod (Graphics g)
    {
	this.mainMenu = mainMenu;
	g.drawImage (mainMenu, 0, 0, appletsize_x, appletsize_y, null);
    }


    public void menu_PLAY (Graphics g)
    {
	this.mainMenu_PLAY = mainMenu_PLAY;
	g.drawImage (mainMenu_PLAY, 0, 0, appletsize_x, appletsize_y, null);
    }


    public void menu_OPTIONS (Graphics g)
    {
	this.mainMenu_OPTIONS = mainMenu_OPTIONS;
	g.drawImage (mainMenu_OPTIONS, 0, 0, appletsize_x, appletsize_y, null);

    }


    public void menu_CREDITS (Graphics g)
    {
	this.mainMenu_CREDITS = mainMenu_CREDITS;
	g.drawImage (mainMenu_CREDITS, 0, 0, appletsize_x, appletsize_y, null);

    }


    public void PlayerOption (Graphics g)
    {
	this.PlayerOption = PlayerOption;
	g.drawImage (PlayerOption, 0, 0, appletsize_x, appletsize_y, null);
    }


    // This method will clear the graphic before drawing anotherString
    //@return, no return as void method
    public void clear ()
    {
	Graphics g = getGraphics ();
	Dimension d = getSize ();
	Color c = getBackground ();
	g.setColor (c);
	g.fillRect (0, 0, d.width, d.height);
    }


    public void paint (Graphics g)
    {
	// Place the body of the drawing method here

	if (running == true) //If the program game is running
	{
	    g.drawImage (picture, x_posR, y_posR - 65, null);
	    g.drawImage (picture1, x_pos, y_pos, null);
	    //g.fillOval (x_pos - radius, y_pos - radius, 2 * radius, 2 * radius);
	    g.setColor (Color.red);
	    //loop that goes through the array blocks[][]
	    for (int y = 0 ; y < 8 ; y++)
	    {
		for (int x = 0 ; x < 22 ; x++)
		{
		    //checks if the value inside the array is equal to 0, if it is, it prints the block
		    if (blocks [y] [x] == 0)
		    {
			//g.setColor (Color.BLUE);
			//g.fillRect (x * 70, y * 30, BLOCK_WIDTH, BLOCK_HEIGHT);
			g.drawImage (picture3, x * 70, y * 30, null);
		    }
		    if (blocks [y] [x] == 5)
		    {
			//g.setColor (Color.GRAY);
			//g.fillRect (x * 70, y * 30, BLOCK_WIDTH, BLOCK_HEIGHT);
			g.drawImage (picture4, x * 70, y * 30, null);
		    }
		    //if the ball hits inside the array and is equal to 2 draw an extra heart
		    if (blocks [y] [x] == 2)
		    {
			g.drawImage (picture3, x * 70, y * 30, null);
		    }

		    if (blocks [y] [x] == 3)
		    {
			g.drawImage (picture3, x * 70, y * 30, null);
		    }
		}
	    }

	    Font font1 = new Font ("Bauhaus", 0, 20); //Creating instance of font
	    g.setFont (font1); //Setting the font to font1
	    g.drawString ("SCORE: " + points, 100, 650); //Drawing the users score on the screen

	    if (lives == 5) //Draws pictures of the heart images depending on how many lives the user has
	    {
		g.drawImage (picture2, 940, 630, null);
		g.drawImage (picture2, 995, 630, null);
		g.drawImage (picture2, 1050, 630, null);
		g.drawImage (picture2, 1105, 630, null);
		g.drawImage (picture2, 1105, 630, null);
	    }
	    if (lives == 4)
	    {
		g.drawImage (picture2, 940, 630, null);
		g.drawImage (picture2, 995, 630, null);
		g.drawImage (picture2, 1050, 630, null);
		g.drawImage (picture2, 1105, 630, null);
	    }
	    if (lives == 3)
	    {
		g.drawImage (picture2, 940, 630, null);
		g.drawImage (picture2, 995, 630, null);
		g.drawImage (picture2, 1050, 630, null);
	    }
	    if (lives == 2)
	    {
		g.drawImage (picture2, 940, 630, null);
		g.drawImage (picture2, 995, 630, null);
	    }
	    if (lives == 1)
	    {

		g.drawImage (picture2, 940, 630, null);
	    }
	}

	switch (Pic) //Depending on the value of 'Pic', the corresponding picture will appear
	{
	    case 1: //First original menu screen
		menuMethod (g);
		break;
	    case 2: //Displays the play hover over
		menu_PLAY (g);
		break;
	    case 3: //Displays the menu when the user is given an option
		g.drawImage (PlayerOption, 0, 0, appletsize_x, appletsize_y, null);
		break;
	    case 4: //Displays the 1Player hover, in the Player Option menu
		g.drawImage (PlayerOption_CLASSICMODE, 0, 0, appletsize_x, appletsize_y, null);
		break;
	    case 5: //Displays the Options hover over
		menu_OPTIONS (g);
		break;
	    case 6: //Displays the Credits hover over
		menu_CREDITS (g);
		break;
	    case 7: //Displays the Options hover over
		g.drawImage (PlayerOption_SUDDENDEATH, 0, 0, appletsize_x, appletsize_y, null);
		break;
	    case 8: //Sets 'Pic' to zero, and clears screen
		clear ();
		Pic = 0;
		break;
	    case 9: //Displays The Store hover over
		MainMenu_STORE (g);
		break;
	    case 10: //When store is clicked
		GameStore (g);
		break;
	    case 11:
		Options (g); //
		break;
	    case 12: //When user loses the game this image is displayed
		resultScreen (g);
		gameOver (g);
		break;
	    case 13:
		resultScreen (g);
		gameWin (g); //When user wins the game this image is displayed
		break;
	    case 14:
		//g.drawImage (Credits, 0, 0, appletsize_x, appletsize_y, null);
		Credits (g); //Credit menu appears
		break;


	}
    } // paint method
} // MovingBallApplet5 class


