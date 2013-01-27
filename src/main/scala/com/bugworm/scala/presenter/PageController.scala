package com.bugworm.scala.presenter
import javafx.fxml.FXML
import javafx.scene.Node

trait PageController {

    var actionNumber : Int = 0

    def doAction() : Unit
}
