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

    // 表示するページ群
    val pages = Array(
            "/contents/page1.fxml",
            "/contents/page2.fxml",
            "/contents/page3.fxml")

    // 現在表示しているページ番号
    var index = 0

    override def start(st: javafx.stage.Stage): Unit = {

        val root = new Group
        new Stage(st){
            // ステージを透明にする
            delegate.initStyle(StageStyle.TRANSPARENT)
            scene = new Scene(root, SimplePresenter.scenewidth, SimplePresenter.sceneheight){
                fill = null
            }
        }.show

        // 最初のページを表示する
        goForward(root)
    }

    // ページを進める
    def goForward(root : Group) : Unit = {

        // 次のページをロードして、表示する
        val url = getClass.getResource(pages(index))
        val loader = new FXMLLoader(url)
        val next = loader.load match {
            case x : Node => x
            case _ => throw new ClassCastException
        }
        root.getChildren.add(next)
        loader.getController match {
            // マウスクリックされたら、進める
            // アニメーションがもうなければ、doAction内でgoForwardを実行する
            case x : PageController => root.onMouseClicked = x.doAction(goForward(root))
            case _ => throw new ClassCastException
        }

        // ページ遷移のアニメーションを行う
        translatePage(root, next)

        // ページインデックスを進める
        // 最後までいったら最初に戻す
        index match {
            case x if x < pages.length - 1 => index += 1
            case _ => index = 0
        }
    }

    // ページ遷移アニメーション
    def translatePage(root : Group, next : Node) : Unit = {

        // 新しいページを右からスライドさせるアニメーション
        new TranslateTransition{
            duration = new Duration(1000)
            node = next
            fromX = SimplePresenter.scenewidth
            toX = 0
        }.play
        
        if(root.getChildren.size > 1){
            // 現在表示しているページがあれば、
            // 左にスライドさせる
            def remove(n : Node) : Unit = root.getChildren.remove(n)
            new TranslateTransition{
                duration = new Duration(1000)
                node = root.getChildren.get(0)
                toX = - SimplePresenter.scenewidth
                // アニメーションが終了したら、
                // シーングラフから削除する
                onFinished = remove(node()) 
            }.play
        }
    }
}

trait PageController{
    def doAction(fw : => Unit) : Unit
}
