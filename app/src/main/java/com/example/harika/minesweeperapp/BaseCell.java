package com.example.harika.minesweeperapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created by harika93 on 2017-10-07.
 */

//abstact class for setter and getters
public abstract class BaseCell extends View{

    private int value;
    private boolean isBomb;
    private boolean isReveled;
    private boolean isClicked;
    private boolean isFlagged;
    private int x,y,pos;


    public BaseCell(Context context)
    {
        super(context);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        isBomb=false;
        isReveled=false;
        isClicked=false;
        isFlagged = false;
        this.value = value;

        if(value == -1)
        {
            isBomb=true;
        }
    }

    public boolean isBomb() {
        return isBomb;
    }

    public boolean isReveled() {
        return isReveled;
    }

    public void setReveled() {
        isReveled = true;
        invalidate();
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked() {
        this.isClicked = true;
        this.isReveled=true;
        invalidate();
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        if(flagged)
        {
            MainActivity.textView2.setText(Integer.toString(MainActivity.count--));
        }
        else
            MainActivity.textView2.setText(Integer.toString(MainActivity.count++));
        isFlagged = flagged;
    }

    public int getXPos() {
        return x;
    }

    public int getYPos() {
        return y;
    }
    public void setPos(int x, int y)
    {
        this.x =x;
        this.y = y;
        this.pos= y*GridMain.WIDTH+x;
        invalidate();
    }


}
