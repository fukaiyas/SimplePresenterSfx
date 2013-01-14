package com.bugworm.sfx.simplepresenter

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.stage.StageStyle
import javafx.util.Duration
import scalafx.Includes._
import scalafx.animation.TranslateTransition.sfxTranslateTransition2jfx
import scalafx.animation.TranslateTransition
import scalafx.scene.Group.sfxGroup2jfx
import scalafx.scene.Group
import scalafx.scene.Scene
import scalafx.stage.Stage.sfxStage2jfx
import scalafx.stage.Stage
import scalafx.animation.FadeTransition
import scalafx.animation.FadeTransition._
import scalafx.scene.layout.StackPane
import scalafx.scene.Parent
import javafx.scene.layout.Pane
import scalafx.scene.image.ImageView
import scalafx.scene.image.ImageView._
import scalafx.scene.image.Image
import scalafx.scene.image.Image._
import java.net.URL
import javafx.scene.layout.AnchorPane
import javafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode
import com.bugworm.sfx.application.SFXApplication
import com.bugworm.sfx.application.SFXAppMain
import com.bugworm.sfx.application.SFXApplication
import scalafx.application.JFXApp

object SimplePresenter extends SFXAppMain(classOf[SimplePresenter])

class SimplePresenter extends SFXApplication{

    val pages = Array(
            "/contents/page1.fxml",
            "/contents/page2.fxml",
            "/contents/page3.fxml",
            "/contents/page4.fxml",
            "/contents/page5.fxml",
            "/contents/page6.fxml",
            "/contents/page7.fxml",
            "/contents/page7_1.fxml",
            "/contents/page8.fxml",
            "/contents/page9.fxml",
            "/contents/demo.fxml",
            "/contents/conclusion.fxml",
            "/contents/scalaconf1.fxml",
            "/contents/end.fxml"
    )

    var index = 0

    val rootpane = new StackPane

    JFXApp.AUTO_SHOW = false
    new Stage{
        // ステージを透明にする
        delegate.initStyle(StageStyle.TRANSPARENT)
        scene = new Scene(rootpane, 1024, 768){
            fill = null
            onKeyPressed = {e : KeyEvent => typed(e, rootpane)}
        }
    }.show

    // 最初のページを表示する
    newPage(rootpane, changePage(rootpane))

    def typed(event : KeyEvent, root : StackPane) : Unit = {
        event.code match{
	        case KeyCode.LEFT => prePage(root)
	        case KeyCode.RIGHT => postPage(root)
	        case KeyCode.Q => System.exit(0)
	        case _ =>
        }
    }

    def prePage(root : StackPane) : Unit = {
        if(index > 0){
            index -= 1
            newPage(root, changePage(root))
        }
    }

    def postPage(root : StackPane) : Unit = {
        if(index < pages.length - 1){
            index += 1
            newPage(root, changePage(root))
        }
    }

    def click(root : StackPane) : Unit = {
        if(index < pages.length - 1){
            index += 1
            newPage(root, fadeInOut(root))
        }
    }

    // ページを変える
    def newPage(root : StackPane, change : Node => Unit) : Unit = {

        // 次のページをロードして、表示する
        val url = getClass.getResource(pages(index))
        val loader = new FXMLLoader(url)
        val next = loader.load match {
            case x : Node => x
            case _ => throw new ClassCastException
        }

        //バックは共通で
        val backurl = getClass.getResource("/contents/base.fxml")
        val back = FXMLLoader.load[AnchorPane](backurl)

        val parent = new StackPane
        parent.getChildren.addAll(back, next)
        parent.opacity = 0.0

        root.getChildren.add(parent)
        root.onMouseClicked = loader.getController[PageController].doAction(click(root))

        change(parent)
    }

    //即切り替え
    def changePage(root : StackPane)(next : Node) : Unit = {
        next.opacity = 1.0
        if(root.getChildren.size > 1){
            root.getChildren.remove(0)
        }
    }

    //フェードイン・フェードアウト
    def fadeInOut(root : StackPane)(next : Node) : Unit = {

        // 新しいページをフェードイン
        new FadeTransition{
            delay = new Duration(300)
            duration = new Duration(1000)
            node = next
            fromValue = 0.0
            toValue = 1.0
        }.play
        
        if(root.getChildren.size > 1){
            // 現在表示しているページがあればフェードアウト
            def remove(n : Node) : Unit = root.getChildren.remove(n)
            new FadeTransition{
                duration = new Duration(1000)
                node = root.getChildren.get(0)
                fromValue = 1.0
                toValue = 0.0
                onFinished = remove(node()) 
            }.play
        }
    }
}

trait PageController{
    def doAction(fw : => Unit) : Unit
}
