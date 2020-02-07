package com.eso.task.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.eso.task.R;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroSliderActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_intro_slider);
        if(checkPref(this,"one")){
            //the user is logged in for the first time
            callFirstTimeMethod();
        }else {
            startActivity(new Intent(IntroSliderActivity.this,MainActivity.class));
        }
    }
    private void callFirstTimeMethod() {
        addSlide(AppIntroFragment.newInstance(getString(R.string.title_slide1), getString(R.string.desc_slide1),
                R.drawable.ic_slide1, ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.title_slide1), getString(R.string.desc_slide1),
                R.drawable.ic_slide2, ContextCompat.getColor(getApplicationContext(), R.color.colorAccent)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.title_slide3), getString(R.string.desc_slide1),
                R.drawable.ic_slide3, ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)));
        changePrefs(this,"one");

    }


    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    public static Boolean checkPref(Context context , String prefKey){
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs",MODE_PRIVATE);
        Boolean firstStart = sharedPreferences.getBoolean(prefKey,true);
        //if this is the users first time return true
        if(firstStart){
            return  true;
        }else{
            return false;
        }
    }

    public static void changePrefs(Context context,String key){
        //this part will change the first time loging boolean fron true to false(this will make sure that the user wont see the tutorial again )
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,false);
        editor.apply();
    }
}
