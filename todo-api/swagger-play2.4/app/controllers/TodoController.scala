package controllers

import com.google.inject.{Inject, Singleton}
import io.swagger.annotations._
import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._

@Singleton
@Api(value = "todo")
class TodoController @Inject()(

) extends Controller {

  val searchForm = Form(
    mapping(
      "offset" -> default(number, 0),
      "limit" -> default(number, 10)
    )(SearchForm.apply)(SearchForm.unapply)
  )

  @ApiOperation(
    nickname = "getTodoList",
    value = "getTodoList",
    notes = "notes",
    response = classOf[TodoListResponse],
    httpMethod = "GET",
    produces = "application/json"
  )
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "offset", value = "オフセット", required = false, dataType = "integer", paramType = "query", defaultValue = "0"),
    new ApiImplicitParam(name = "limit", value = "リミット", required = false, dataType = "integer", paramType = "query", defaultValue = "10")
  ))
  def list: Action[AnyContent] = Action { implicit request =>
    searchForm.bindFromRequest.fold(
      error => BadRequest("invalid param"),
      form => {
        val todoList = TodoListResponse(
          total = 2,
          list = Seq(
          Todo(
            id = 1,
            title = "タイトル1",
            description = "description1",
            isFinished = true
          ),
          Todo(
            id = 2,
            title = "タイトル2",
            description = "description2",
            isFinished = false
          )))
        Ok(Json.toJson(todoList))
      }
    )
  }

  @ApiOperation(
    nickname = "createTodo",
    value = "createTodo",
    notes = "notes",
    response = classOf[Todo],
    httpMethod = "POST",
    consumes = "application/json",
    produces = "application/json"
  )
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "body", value = "todo create form", required = true, dataType = "models.TodoCreateForm", paramType = "body")
  ))
  def create(): Action[JsValue] = Action(BodyParsers.parse.json) { implicit request =>
    request.body.validate[TodoCreateForm].fold(
      error => BadRequest("invalid form"),
      form => {
        val createdTodo = Todo(
          id = System.currentTimeMillis(),
          title = form.title,
          description = form.description,
          isFinished = false
        )
        Created(Json.toJson(createdTodo))
      }
    )
  }

  @ApiOperation(
    nickname = "updateTodo",
    value = "updateTodo",
    notes = "notes",
    response = classOf[Todo],
    httpMethod = "PUT",
    consumes = "application/json",
    produces = "application/json"
  )
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "body", value = "todo update form", required = true, dataType = "models.TodoUpdateForm", paramType = "body")
  ))
  def update(
    @ApiParam(value = "todo ID") id: Long): Action[JsValue] = Action(BodyParsers.parse.json) { implicit request =>
    request.body.validate[TodoUpdateForm].fold(
      error => BadRequest("invalid form"),
      form => {
        val updatedTodo = Todo(
          id = id,
          title = form.title,
          description = form.description,
          isFinished = false
        )
        Ok(Json.toJson(updatedTodo))
      }
    )
  }

  // TODO: レスポンスタイプがAnyContentになるので直す
  @ApiOperation(
    nickname = "deleteTodo",
    value = "deleteTodo",
    notes = "notes",
    httpMethod = "DELETE"
  )
  def delete(
    @ApiParam(value = "todo ID") id: Long) = Action { implicit request =>
    NoContent
  }
}
