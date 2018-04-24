package com.example.harika.minesweeperapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

/**
 * Created by harika93 on 2017-10-07.
 */

//creates the Grid view by extending the GridView class
public class Grid extends GridView{
    public Grid(Context context, AttributeSet attrs)
    {
        super(context,attrs);
        GridMain.getInstance().DisplayGrid(context);
        setNumColumns(GridMain.WIDTH);
        setAdapter(new GridAdapter());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heighMeasureSpec)
    {
       super.onMeasure(widthMeasureSpec,widthMeasureSpec);
    }

    private class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return GridMain.getInstance().WIDTH * GridMain.getInstance().HEIGHT;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return GridMain.getInstance().getCellAt(i);
        }
    }
}
