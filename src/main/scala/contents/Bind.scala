package contents

import java.net.URL
import java.util.ResourceBundle

import com.bugworm.sfx.simplepresenter.PageController

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label

class Bind extends PageController with Initializable {

    @FXML
    var bind : Label = _

    override def initialize(url : URL, rb : ResourceBundle) : Unit = {
        bind.setOpacity(0.0)
    }

    override def doAction(fw: => Unit): Unit = {
        bind.getOpacity match {
            case 0.0 => bind.setOpacity(1.0)
            case _ => fw
        }
    }
}