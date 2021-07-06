package com.example.instagram;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("B3lAJKkg67IxBTP8ziEhVYd1bWce49SHGLQ8iFsC")
                .clientKey("g46aP2gWgvnGNdQr3JsanhGA9xZswmz0bW7ooSgn")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
