package com.android.pet.view;

/**
 * Created by GOGINENI on 12/7/2016.
 */

import android.util.Base64;

public class newdata {
    SignUPActivity sp;
    String testValue = sp.editTextPassword.getText().toString();

    byte[] encodeValue = Base64.encode(testValue.getBytes(), Base64.DEFAULT);
    byte[] decodeValue = Base64.decode(encodeValue, Base64.DEFAULT);


}


