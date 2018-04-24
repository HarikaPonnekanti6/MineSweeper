package com.example.harika.minesweeperapp;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

/**
 * Created by harika93 on 2017-10-07.
 */

//Main functionality class where all the logical operations of the game are performed
public class GridMain {

        private static GridMain instance;
       private Context context;
        public static final int WIDTH = 9;
        public static final int HEIGHT = 9;
    public Animation animation = null;

    private Handler timer = new Handler();
    private int secondsPassed = 0;
    //Starts the timer
    public void startTimer()
    {
        if (secondsPassed == 0)
        {
            timer.removeCallbacks(updateTimeElasped);
            timer.postDelayed(updateTimeElasped, 1000);
        }
    }
    //Stops the Timer
    public void stopTimer()
    {
        timer.removeCallbacks(updateTimeElasped);
        secondsPassed = 0;
    }
    private Runnable updateTimeElasped = new Runnable()
    {
        public void run()
        {
            long currentMilliseconds = System.currentTimeMillis();
            ++secondsPassed;
            MainActivity.textView1.setText(Integer.toString(secondsPassed));

            // add notification
            timer.postAtTime(this, currentMilliseconds);
            // notify to call back after 1 seconds
            // basically to remain in the timer loop
            timer.postDelayed(updateTimeElasped, 1000);
        }
    };

        private Cell[][] MinesweeperGrid = new Cell[WIDTH][HEIGHT];

        public static GridMain getInstance() {
            if( instance == null ){
                instance = new GridMain();
            }
            return instance;
        }

        private GridMain(){ }

    //Create Grid and Display on main Activity
        public void DisplayGrid(Context context){
            this.context = context;
            int[][] GeneratedGrid = CellGenerate.generate(MainActivity.BOMB_NUMBER,WIDTH, HEIGHT);
            animation = AnimationUtils.loadAnimation(context, R.anim.animation_grid);
            setGrid(context,GeneratedGrid);
           // set = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.property_animator);

        }

//gets the selected cell position
    public Cell getCellAt(int position) {
        int x = position % WIDTH;
        int y = position / WIDTH;

        return MinesweeperGrid[x][y];
    }

    //gets the cell value at the selected cell position
    public Cell getCellAt( int x , int y ){
        return MinesweeperGrid[x][y];
    }

        private void setGrid( final Context context, final int[][] grid ){
            for( int x = 0 ; x < WIDTH ; x++ ){
                for( int y = 0 ; y < HEIGHT ; y++ ){
                    if( getCellAt(x,y) == null ){
                        MinesweeperGrid[x][y] = new Cell(context,x,y);
                    }
                    MinesweeperGrid[x][y].setValue(grid[x][y]);
                    getCellAt(x,y).setEnabled(true);
                    getCellAt(x,y).startAnimation(animation);
                    MinesweeperGrid[x][y].invalidate();
                }
            }
        }

// performs click the grid operation
        public void clickGrid( int x , int y ){
            startTimer();
            if( x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && !getCellAt(x,y).isClicked() ){
                getCellAt(x,y).setClicked();
                if( getCellAt(x,y).getValue() == 0 ){
                    for( int xt = -1 ; xt <= 1 ; xt++ ){
                        for( int yt = -1 ; yt <= 1 ; yt++){
                            if( xt != yt ){
                                clickGrid(x + xt , y + yt);
                            }
                        }
                    }
                }

                if( getCellAt(x,y).isBomb() ){
                    LostGame();
                }
            }

            EndGame();
        }

        //ending the game by revelaing all the bomb position
        private boolean EndGame(){
            int bombNotFound = MainActivity.BOMB_NUMBER;
            int notRevealed = WIDTH * HEIGHT;
            for ( int x = 0 ; x < WIDTH ; x++ ){
                for( int y = 0 ; y < HEIGHT ; y++ ){
                    if( getCellAt(x,y).isReveled() || getCellAt(x,y).isFlagged() ){
                        notRevealed--;
                    }

                    if( getCellAt(x,y).isFlagged() && getCellAt(x,y).isBomb() ){
                        bombNotFound--;
                    }
                }
            }

            if( bombNotFound == 0 && notRevealed == 0 ){
                stopTimer();
                MainActivity.button.setImageResource(R.drawable.smileywon);
                Toast.makeText(context,"!!!Game WON!!!", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
//sets flag to a particluar cell
        public void setFlag( int x , int y ){
            boolean isFlagged = getCellAt(x,y).isFlagged();
            getCellAt(x,y).setFlagged(!isFlagged);
            getCellAt(x,y).invalidate();
        }
//called when the cell is having any bomb
        private void LostGame(){
           stopTimer();
            MainActivity.button.setImageResource(R.drawable.smileysad);
            Toast.makeText(context,"!!!Game lost!!!!", Toast.LENGTH_SHORT).show();
            for ( int x = 0 ; x < WIDTH ; x++ ) {
                for (int y = 0; y < HEIGHT; y++) {
                    getCellAt(x,y).setReveled();
                    getCellAt(x,y).setEnabled(false);
                }
            }
        }

    }

