/**
 * Copyright 2012 eBay Software Foundation
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

package controllers

import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.mvc._
import scala.io.Source

import pine._

object PineController extends Controller {

  def index = Action {

    Ok("<h1>Welcome to Pine: Machine Learning As A Service!</h1> It accepts POST commands at /pred and returns predictions in JSON. <p>shaojun@ebay.com<p>March 2016").as("text/html")
  }

  def pred() = Action { request =>
    val input = request.body.asJson.get.toString
    val results = Pine.test(input)
    Ok(Json.obj("output" -> results))
  }
}
