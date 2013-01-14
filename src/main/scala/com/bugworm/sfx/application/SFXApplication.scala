package com.bugworm.sfx.application

import javafx.application.Application
import scalafx.stage.Stage
import scalafx.application.JFXApp

class SFXApplication extends Application with DelayedInit {

    //TODO arguments

    var initialize : () => Unit = _

    def delayedInit(x : => Unit) {
        initialize = () => x
    }

    def start(stage : javafx.stage.Stage) {
        JFXApp.STAGE = stage
        initialize()
        if(JFXApp.AUTO_SHOW){
            JFXApp.STAGE.show()
        }
    }
}
