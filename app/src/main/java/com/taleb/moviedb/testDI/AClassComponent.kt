package com.taleb.moviedb.testDI

import dagger.Component


@Component(modules = [AClassModule::class])
interface AClassComponent {

    fun getAClass() : AClass
}