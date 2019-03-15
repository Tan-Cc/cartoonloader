package com.tanc.cartoonloader;

import java.net.MalformedURLException;

public class AppRun {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        String[] arg = {"-c6284", "-H001", "-oF://cartoon"};
        App.main(arg);
    }
}
