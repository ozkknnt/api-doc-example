package models

import org.joda.time.DateTime
import play.api.libs.json.Json

case class Todo(
  id: Long,
  title: String,
  description: String,
  isFinished: Boolean,
  createTime: DateTime,
  finishedTime: Option[DateTime]
)

object Todo {
  implicit val writes = Json.writes[Todo]
}


case class TodoListResponse(
  total: Int,
  list: Seq[Todo]
)

object TodoListResponse {
  implicit val writes = Json.writes[TodoListResponse]
}

case class TodoCreateForm(
  title: String,
  description: String
)

object TodoCreateForm {
  implicit val reads = Json.reads[TodoCreateForm]
}

case class TodoUpdateForm(
  title: String,
  description: String,
  isFinished: Boolean
)

object TodoUpdateForm {
  implicit val reads = Json.reads[TodoUpdateForm]
}
