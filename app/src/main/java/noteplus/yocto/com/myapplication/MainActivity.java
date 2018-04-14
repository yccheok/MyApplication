package noteplus.yocto.com.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    boolean state = true;
    int color0 = Color.parseColor("#ff82b1ff");
    int color1 = Color.parseColor("#ff80d8ff");
    int color = color0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View card0 = findViewById(R.id.card0);
        final View card1 = findViewById(R.id.card1);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                card0.setActivated(state);
                card1.setActivated(state);

                state = !state;
            }
        });

        card1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                card1.setActivated(state);

                state = !state;
            }
        });

        Button colorButton = findViewById(R.id.color_button);

        colorButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                RippleDrawable rippleDrawable = (RippleDrawable)card1.getBackground();
                //StateListDrawable stateListDrawable = (StateListDrawable)rippleDrawable.findDrawableByLayerId(R.id.x_selector_for_ripple);

                // Load different behavior states
                int activated = android.R.attr.state_activated;

                StateListDrawable stateListDrawable = new StateListDrawable();

                LayerDrawable shape = (LayerDrawable) getResources().getDrawable(R.drawable.x_card);
                GradientDrawable gradientDrawable = (GradientDrawable)shape.findDrawableByLayerId(R.id.gradient_drawable);
                gradientDrawable.setColor(color);

                if (color == color0) color = color1; else color = color0;

                stateListDrawable.addState(new int[] {activated},  getResources().getDrawable(R.drawable.x_activated_card));
                stateListDrawable.addState(new int[] {}, shape);

                boolean outcome = rippleDrawable.setDrawableByLayerId(R.id.x_selector_for_ripple, stateListDrawable);

                Log.i("CHEOK", "outcome = " + outcome);

                card1.invalidate();

            }
        });
    }
}
