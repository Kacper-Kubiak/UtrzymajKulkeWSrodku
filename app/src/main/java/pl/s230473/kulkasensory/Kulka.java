package pl.s230473.kulkasensory;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Kulka extends View {
    private Paint p;
    int pos_x = 10;
    int pos_y = 10;
    int move_pos_x = 0;
    int move_pos_y = 0;
    private final static int kulkaSize = 10;

    public Kulka(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.RED);
    }

    public void setMovePosX(int x) {
        move_pos_x = x;
    }

    public void setMovePosY(int y) {
        move_pos_y = y;
    }

    public void setCenterPos() {
        pos_x = getMeasuredWidthAndState()/2;
        pos_y = getMeasuredHeightAndState()/2;
        Log.i("x", String.valueOf(pos_x));
        Log.i("y", String.valueOf(pos_y));
    }

    public double getCenterDistance() {
        int x1 = getWidth()/2;
        int x2 = pos_x;
        int y1 = getHeight()/2;
        int y2 = pos_y;
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public void move () {
        if(pos_x+move_pos_x < getWidth() && pos_x+move_pos_x >= 0)
        {
            pos_x += move_pos_x;
        }
        if(pos_x+move_pos_x >= getWidth()-10)
        {
            pos_x = getWidth()-kulkaSize;
        }
        if(pos_x+move_pos_x < 0+kulkaSize)
        {
            pos_x = 0+kulkaSize;
        }

        if(pos_y+move_pos_y <= getHeight() && pos_y+move_pos_y >= 0)
        {
            pos_y += move_pos_y;
        }
        if(pos_y+move_pos_y > getHeight()-10)
        {
            pos_y = getHeight()-kulkaSize;
        }
        if(pos_y+move_pos_y < 0+kulkaSize)
        {
            pos_y = 0+kulkaSize;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(pos_x, pos_y, kulkaSize, p);
    }
}

