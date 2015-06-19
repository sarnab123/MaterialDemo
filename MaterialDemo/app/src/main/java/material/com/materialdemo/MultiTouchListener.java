package material.com.materialdemo;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MultiTouchListener implements OnTouchListener {

    private float mPrevX;
    private float mPrevY;

    public PMPActivity mainActivity;

    public MultiTouchListener(PMPActivity mainActivity1) {
        mainActivity = mainActivity1;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        float currX, currY;
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {

                mPrevX = event.getX();
                mPrevY = event.getY();
                break;
            }

            case MotionEvent.ACTION_MOVE: {

                currX = event.getRawX();
                currY = event.getRawY();


                MarginLayoutParams marginParams = new MarginLayoutParams(view.getLayoutParams());
                marginParams.setMargins((int) (currX - mPrevX), (int) (currY - mPrevY), 0, 0);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(marginParams);
                view.setLayoutParams(layoutParams);


                break;
            }


            case MotionEvent.ACTION_CANCEL:
                break;

            case MotionEvent.ACTION_UP:
                mainActivity.onAddToBackClick(view);
               // Toast.makeText(mainActivity, "Coming soon", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

}

