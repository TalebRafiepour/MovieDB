package com.taleb.moviedb.testDI

import javax.inject.Inject

class CClass @Inject constructor(){

    fun someMethodInC() {
        println("someMethodInC()")
    }
}