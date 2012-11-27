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

class Empty extends PageController with Initializable {
    
    override def initialize(url : URL, rb : ResourceBundle) : Unit = {
    }
    
    override def doAction(fw : => Unit) : Unit = {
        fw
    }
}