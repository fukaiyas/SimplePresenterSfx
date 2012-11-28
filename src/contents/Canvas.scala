package contents

import java.net.URL
import java.util.ResourceBundle

import com.bugworm.sfx.simplepresenter.PageController

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label

class Canvas extends PageController with Initializable {

    @FXML
    var canvas : Label = _

    override def initialize(url : URL, rb : ResourceBundle) : Unit = {
        canvas.setOpacity(0.0)
    }

    override def doAction(fw: => Unit): Unit = {
        canvas.getOpacity match {
            case 0.0 => canvas.setOpacity(1.0)
            case _ => fw
        }
    }
}