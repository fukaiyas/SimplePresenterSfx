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

object SimplePresenter {
    val scenewidth = 800.0
    val sceneheight = 600.0
    def main(args : Array[String]) : Unit = {
        Application.launch(classOf[SimplePresenter], args:_*)
    }
}

class SimplePresenter extends Application{

    val pages = Array(
            "/contents/page1.fxml",
            "/contents/page2.fxml",
    		"/contents/page3.fxml")

    var pageIndex = 0

    var presentController : PageController = _

    override def start(st: javafx.stage.Stage): Unit = {

        val root = new Group{
            onMouseClicked = presentController.doAction(goForward(this))
        }
        new Stage(st){
            delegate.initStyle(StageStyle.TRANSPARENT)
            scene = new Scene(root, SimplePresenter.scenewidth, SimplePresenter.sceneheight){
                fill = null
            }
        }.show
        goForward(root)
    }

    def goForward(root : Group) : Unit = {

        val url = getClass.getResource(pages(pageIndex))
        val loader = new FXMLLoader(url)
        val next = loader.load match {
            case x : Node => x
            case _ => throw new ClassCastException
        }
        root.getChildren.add(next)
        presentController = loader.getController match {
            case x : PageController => x
            case _ => throw new ClassCastException
        }

        translatePage(root, next)

        pageIndex match {
            case x if x < pages.length - 1 => pageIndex += 1
            case _ => pageIndex = 0
        }
    }

    def translatePage(root : Group, next : Node) : Unit = {

        new TranslateTransition{
            duration = new Duration(1000)
            node = next
            fromX = SimplePresenter.scenewidth
            toX = 0
        }.play
        
        if(root.getChildren.size > 1){
            def remove(n : Node) : Unit = root.getChildren.remove(n)
            new TranslateTransition{
                duration = new Duration(1000)
                node = root.getChildren.get(0)
                toX = - SimplePresenter.scenewidth
                onFinished = remove(node()) 
            }.play
        }
    }
}

trait PageController{
    def doAction(fw : => Unit) : Unit
}
