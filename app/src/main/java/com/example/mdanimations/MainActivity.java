package com.example.mdanimations;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnRevealAndHide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imgLauncher);
        btnRevealAndHide = findViewById(R.id.btnRevealAndHide);

       // imageView.setVisibility(View.INVISIBLE);

        btnRevealAndHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageView.getVisibility() == View.INVISIBLE){

                    int xValue = imageView.getWidth() / 2;
                    int yValue = imageView.getHeight() / 2;

                    int endRadiusValue = Math.max(imageView.getWidth(), imageView.getHeight());

                    Animator circularAnimation = ViewAnimationUtils.createCircularReveal(imageView,
                            xValue,yValue,0,endRadiusValue);
                    imageView.setVisibility(View.VISIBLE);
                    circularAnimation.start();
                }else{

                    int xValue = imageView.getWidth() / 2;
                    int yValue = imageView.getHeight() / 2;
                    int startRadiusValue = imageView.getWidth();
                    Animator circularHide = ViewAnimationUtils.createCircularReveal(imageView,
                            xValue,yValue,startRadiusValue,0);

                    //When the animation actually ending on the in that case I want to make the image view make to be the invisible.Not before that.
                    circularHide.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationCancel(animation);
                            //When the animation is ended,this code is runnig and the image view going to be INVISIBLE.
                            imageView.setVisibility(View.INVISIBLE);

                        }
                    });
                    circularHide.start();

                }
            }
        });

    }
}
