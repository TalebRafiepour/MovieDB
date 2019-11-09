package com.taleb.moviedb.testDI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taleb.moviedb.R

class TestDaggerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_dagger)

        val a = DaggerAClassComponent.create().getAClass()
        a.someMethodInA()
    }
}
