package com.bugworm.sfx.application

import javafx.application.Application

class SFXAppMain[A <: Application](val sfxappcl : Class[A]) {
    
    //TODO arguments

    def main(args : Array[String]) : Unit = {
        Application.launch(sfxappcl, args: _*)
    }
}
