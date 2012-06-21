package contents

import java.net.URL
import java.util.ResourceBundle

import com.bugworm.sfx.simplepresenter.PageController

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.image.ImageView
import javafx.scene.text.Text
import javafx.util.Duration
import scalafx.animation.FadeTransition
import scalafx.animation.FadeTransition.sfxFadeTransition2jfx
import scalafx.scene.text.Text.sfxText2jfx
import scalafx.Includes._

class Page1 extends PageController with Initializable {

    @FXML
    var p0 : ImageView = _

    @FXML
    var p1 : Text = _
    
    override def initialize(url : URL, rb : ResourceBundle) : Unit = {
        p1.setOpacity(0.0)
    }
    
    override def doAction() : Boolean = {

        p1.getOpacity match {
            case 0.0 =>
                new FadeTransition{
                    duration = new Duration(1000L)
                    node = p1
                    toValue = 1.0
                }.play
                true
            case _ => false
        }
    }
}