package contents

import java.net.URL
import java.util.ResourceBundle

import com.bugworm.sfx.simplepresenter.PageController

import javafx.fxml.Initializable

class Empty extends PageController with Initializable {
    
    override def initialize(url : URL, rb : ResourceBundle) : Unit = {
    }
    
    override def doAction(fw : => Unit) : Unit = {
        fw
    }
}