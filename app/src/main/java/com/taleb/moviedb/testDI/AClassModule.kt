package com.taleb.moviedb.testDI

import dagger.Module
import dagger.Provides

@Module
class AClassModule {

    @Provides
    fun aClassProvider() : AClass {
        return AClass(BClass(CClass()))
    }
}