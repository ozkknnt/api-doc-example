package models

import io.swagger.annotations.ApiModel
import play.api.libs.json.Json

@ApiModel("Todo")
case class Todo(
  id: Long,
  title: String,
  description: String,
  isFinished: Boolean
)

object Todo {
  implicit val writes = Json.writes[Todo]
}

@ApiModel("TodoListResponse")
case class TodoListResponse(
  total: Int,
  list: Seq[Todo]
)

object TodoListResponse {
  implicit val writes = Json.writes[TodoListResponse]
}

@ApiModel("TodoCreateForm")
case class TodoCreateForm(
  title: String,
  description: String
)

object TodoCreateForm {
  implicit val reads = Json.reads[TodoCreateForm]
}

@ApiModel("TodoUpdateForm")
case class TodoUpdateForm(
  title: String,
  description: String,
  isFinished: Boolean
)

object TodoUpdateForm {
  implicit val reads = Json.reads[TodoUpdateForm]
}

case class SearchForm(
  offset: Int,
  limit: Int
)
