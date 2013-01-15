package com.bugworm.scala.presenter

trait PageController {

    def doAction(fw : => Unit) : Unit
}