package controllers

import com.google.inject.{Inject, Singleton}
import models.{Todo, TodoCreateForm, TodoListResponse, TodoUpdateForm}
import org.joda.time.DateTime
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._

@Singleton
class TodoController @Inject()(

) extends Controller {

  def list: Action[AnyContent] = Action { implicit request =>
    val todoList = TodoListResponse(
      total = 2,
      list = Seq(
      Todo(
        id = 1,
        title = "タイトル1",
        description = "description1",
        isFinished = true,
        createTime = new DateTime("2016-10-20"),
        finishedTime = Some(new DateTime("2016-10-23"))
      ),
      Todo(
        id = 2,
        title = "タイトル2",
        description = "description2",
        isFinished = false,
        createTime = new DateTime("2016-10-22"),
        finishedTime = None
      )
    ))
    Ok(Json.toJson(todoList))
  }

  def create(): Action[JsValue] = Action(BodyParsers.parse.json) { implicit request =>
    request.body.validate[TodoCreateForm].fold(
      error => BadRequest("invalid form"),
      form => {
        val createdTodo = Todo(
          id = System.currentTimeMillis(),
          title = form.title,
          description = form.description,
          isFinished = false,
          createTime = DateTime.now,
          finishedTime = None
        )
        Created(Json.toJson(createdTodo))
      }
    )
  }

  def update(id: Long): Action[JsValue] = Action(BodyParsers.parse.json) { implicit request =>
    request.body.validate[TodoUpdateForm].fold(
      error => BadRequest("invalid form"),
      form => {
        val updatedTodo = Todo(
          id = id,
          title = form.title,
          description = form.description,
          isFinished = false,
          createTime = DateTime.now,
          finishedTime = if (form.isFinished) Some(DateTime.now) else None
        )
        Ok(Json.toJson(updatedTodo))
      }
    )
  }

  def delete(id: Long): Action[AnyContent] = Action { implicit request =>
    NoContent
  }
}
