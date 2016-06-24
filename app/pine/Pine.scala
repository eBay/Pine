/**
 * Copyright 2016 eBay Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pine

import play.api.libs.json._
import scala.io.Source

/**
 * Main app to manage json encoded decision tree model.
 */
object Pine {

  val modelFile = "data/model.json"
  lazy val m: Model = loadModel(modelFile)

  def test(input: String): Seq[JsObject] = {
    runPredictionSeq(m, input)
  }

  def loadModel(modelFile: String) : Model = {

    System.err.println(s"Load model: ${modelFile}")

    // parse model json file
    val json = Json.parse(fixAllScientificNotations(Source.fromFile(modelFile).mkString))

    // print model name
    println(json \ "name")
    Model.fromJSON(json)
  }
  
  def runPredictionSeq(m: Model, input: String): Seq[JsObject] = {
    val jsonStr = fixAllScientificNotations(input)
    val json = Json.parse(jsonStr).as[Seq[JsObject]]

    json.map{e => 
      val pred = m.eval(e)
      Json.obj("USER_ID" -> (e \ "USER_ID").as[Int], "score" -> pred)
    }
  }

  def fixAllScientificNotations(x: String) = x.replaceAll("(\\d+)(e|E)\\+(\\d+)", "$1E$3") // 1e+2 is not accepted by the parser ("+" must be removed)

}

/**
 * Small utility to read test files for regression testing.
 */
object TestFile {
  def readFile(filename: String): Seq[Map[String, Double]] = {
    val lines = Source.fromFile(filename).getLines
    lines map {
      _.split("\\s+") map {
        pair =>
          val arr = pair.split(":")
          (arr(0), arr(1).toDouble)
      } toMap
    } toSeq
  }
}
