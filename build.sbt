name := "SimplePresenterSfx"

version := "0.1"

scalaVersion := "2.10.1"

unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/jfxrt.jar"))

