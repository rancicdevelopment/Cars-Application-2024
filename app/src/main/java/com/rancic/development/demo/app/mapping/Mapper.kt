package com.rancic.development.demo.app.mapping

interface Mapper<Local, Remote> {
    fun fromRemote(r: Remote): Local
    fun fromLocal(l: Local): Remote
}