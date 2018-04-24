package com.example.harika.minesweeperapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by harika93 on 2017-10-07.
 */

//class where all the grid values are populated with icons using drawable and canvas

public class Cell extends BaseCell implements View.OnClickListener,View.OnLongClickListener{


    public Cell(Context context, int x,int y)
    {

        super(context);
        setPos(x,y);
        setOnClickListener(this);
        setOnLongClickListener(this);

    }

    @Override
    public boolean onLongClick(View view) {
        GridMain.getInstance().setFlag(getXPos(),getYPos());
        return true;
    }

    @Override
    public void onClick(View view) {
        GridMain.getInstance().clickGrid(getXPos(),getYPos());

    }
    //tell super class we want a square
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        drawMethod(canvas,R.drawable.buttonlight);
        if( isFlagged())
        {
            drawMethod(canvas,R.drawable.flagsicon);
        }
        else if(isReveled() && isBomb() && !isClicked())
        {
            drawMethod(canvas,R.drawable.normalbomb);
        }
        else
        {
            if(isClicked())
            {
                if(getValue()==-1)
                {
                    drawMethod(canvas,R.drawable.explodebomb);
                }
                else
                {
                    drawNumber(canvas);
                }
            }else
            {
                drawMethod(canvas,R.drawable.buttonlight);
            }

        }
    }
//make a function
    private void drawMethod(Canvas canvas,int idOfButton)
    {
         Drawable drawable= ContextCompat.getDrawable(getContext(),idOfButton);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawNumber(Canvas canvas)
    {
        Drawable drawable = null;
        switch(getValue())
        {
            case 0: drawable = ContextCompat.getDrawable(getContext(),R.drawable.numberzero);
                break;
            case 1:  drawable = ContextCompat.getDrawable(getContext(),R.drawable.number1);
                break;
            case 2:  drawable = ContextCompat.getDrawable(getContext(),R.drawable.number2);
                break;
            case 3: drawable = ContextCompat.getDrawable(getContext(),R.drawable.number3i);
                break;
            case 4:  drawable = ContextCompat.getDrawable(getContext(),R.drawable.number4);
                break;
            case 5: drawable = ContextCompat.getDrawable(getContext(),R.drawable.number5);
                break;
            case 6:  drawable = ContextCompat.getDrawable(getContext(),R.drawable.number6);
                break;
            case 7: drawable = ContextCompat.getDrawable(getContext(),R.drawable.number7);
                break;
            case 8: drawable = ContextCompat.getDrawable(getContext(),R.drawable.number8);
                break;
        }
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }
}
