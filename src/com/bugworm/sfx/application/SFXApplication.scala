package com.bugworm.sfx.application
import javafx.application.Application

trait SFXApplication[A <: Application] {
    val app : Class[A]
    def main(args : Array[String]) : Unit = {
        Application.launch(app)
    }
}