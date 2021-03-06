package com.example.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Register parse models
        ParseObject.registerSubclass(Post.class);


        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("B3lAJKkg67IxBTP8ziEhVYd1bWce49SHGLQ8iFsC")
                .clientKey("g46aP2gWgvnGNdQr3JsanhGA9xZswmz0bW7ooSgn")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
