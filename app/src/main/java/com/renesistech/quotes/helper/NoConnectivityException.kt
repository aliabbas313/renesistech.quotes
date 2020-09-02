package com.renesistech.quotes.helper

import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String
        get() = "Please check your connection and try again"
}