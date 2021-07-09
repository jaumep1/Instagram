package com.example.instagram;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

@ParseClassName("User")
public class User extends ParseUser {
    public static final String KEY_IMAGE = "profileImage";
    public static final String KEY_ID = "objectId";

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile image) {
        put(KEY_IMAGE, image);
    }

    public String getObjectId() {
        return getString(KEY_ID);
    }

    public void setObjectId(String id) {
        put(KEY_ID, id);
    }
}
