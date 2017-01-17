package com.test.global;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronniewang on 16/7/31.
 */
public class FilterUriHolder {

    public static List<String> needFilteredUris = new ArrayList<>();

    static {
        needFilteredUris.add("/signUp");
    }

    public static boolean needToken(String uri) {

        return needFilteredUris.contains(uri);
    }
}
