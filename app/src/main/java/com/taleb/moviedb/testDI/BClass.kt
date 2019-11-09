package com.taleb.moviedb.testDI

import javax.inject.Inject

class BClass @Inject constructor (private val c : CClass){

    fun someMethodInB(){
        c.someMethodInC()
    }
}