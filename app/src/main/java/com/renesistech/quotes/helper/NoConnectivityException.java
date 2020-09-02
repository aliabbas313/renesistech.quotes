package com.renesistech.quotes.helper;

import java.io.IOException;

public class NoConnectivityException extends IOException {
 
    @Override
    public String getMessage() {
        return "Please check your connection and try again";
    }
 
}