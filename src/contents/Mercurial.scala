package contents

import java.net.URL
import java.util.ResourceBundle

import com.bugworm.sfx.simplepresenter.PageController

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label
import scalafx.Includes._

class Mercurial extends PageController with Initializable {

    @FXML
    var hg : Label = _

    def initialize(arg0: URL, arg1: ResourceBundle): Unit = {
        hg.opacity = 0.0
    }

    def doAction(fw : => Unit): Unit = {
        hg.getOpacity match {
            case 0.0 => hg.setOpacity(1.0)
            case _ => fw
        }
    }
}