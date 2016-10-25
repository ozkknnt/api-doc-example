package models

import io.swagger.annotations.{ApiModelProperty, ApiModel}
import play.api.libs.json.Json

import scala.annotation.meta.field

// FIXME: 本来は、プロパティごとにrequiredの定義をする必要はないはず。
@ApiModel("Todo")
case class Todo(
  @(ApiModelProperty @field)(required = true) id: Long,
  @(ApiModelProperty @field)(required = true) title: String,
  @(ApiModelProperty @field)(required = true) description: String,
  @(ApiModelProperty @field)(required = true) isFinished: Boolean
)

object Todo {
  implicit val writes = Json.writes[Todo]
}

@ApiModel("TodoListResponse")
case class TodoListResponse(
  @(ApiModelProperty @field)(required = true) total: Int,
  @(ApiModelProperty @field)(required = true) list: Seq[Todo]
)

object TodoListResponse {
  implicit val writes = Json.writes[TodoListResponse]
}

@ApiModel("TodoCreateForm")
case class TodoCreateForm(
  @(ApiModelProperty @field)(required = true) title: String,
  @(ApiModelProperty @field)(required = true) description: String
)

object TodoCreateForm {
  implicit val reads = Json.reads[TodoCreateForm]
}

@ApiModel("TodoUpdateForm")
case class TodoUpdateForm(
  @(ApiModelProperty @field)(required = true) title: String,
  @(ApiModelProperty @field)(required = true) description: String,
  @(ApiModelProperty @field)(required = true) isFinished: Boolean
)

object TodoUpdateForm {
  implicit val reads = Json.reads[TodoUpdateForm]
}

case class SearchForm(
  offset: Int,
  limit: Int
)
