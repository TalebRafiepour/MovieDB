package com.taleb.moviedb.testDI

import javax.inject.Inject

class AClass @Inject constructor(private val b : BClass) {

    fun someMethodInA(){
        b.someMethodInB()
    }
}