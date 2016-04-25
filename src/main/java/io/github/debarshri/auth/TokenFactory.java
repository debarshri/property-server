package io.github.debarshri.auth;

import io.github.debarshri.Resource;

import java.util.Base64;

public class TokenFactory {

    public boolean check(String token)
    {
        return false;
    }

    public static boolean validate(String token) {

        byte[] decode = Base64.getDecoder().decode(token);
        String s = new String(decode);

        if(s.equals(Resource.TOKEN))
        {
            return true;
        }
        return false;
    }
}
