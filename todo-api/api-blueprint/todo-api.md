FORMAT: 1A
HOST: http://localhost:9000

# Todo Sample App

# Group todo

## TODOリストを取得する [GET /todo{?offset,limit}]
+ Parameters
    + offset: 0 (number, optional) - オフセット
    + limit: 10 (number, optional) - リミット
+ Response 200 (application/json; charset=utf-8)
    + Attributes (TodoListResponse)

## TODOを作成する [POST /todo]
+ Request (application/json)
    + Attributes (TodoCreateForm)
+ Response 201 (application/json; charset=utf-8)
    + Attributes (Todo)

## TODOの更新・削除 [/todo/{todoId}]
### TODOを更新する [PUT]
+ Parameters
    + todoId: 2 (number, required) - todo ID
+ Request (application/json)
    + Attributes (TodoUpdateForm)
+ Response 200 (application/json; charset=utf-8)
    + Attributes (Todo)

### TODOを削除する [DELETE]
+ Parameters
    + todoId: 1 (number, required) - todo ID
+ Response 204

# Data Structures

## Todo (object)
+ id: 1 (number, required) - todo ID
+ title: タイトル (string, required) - タイトル
+ description: 説明 (string, required) - 説明
+ isFinished: true (boolean, required) - 終了したかどうか

## TodoCreateForm
+ title: タイトル (string, required) - タイトル
+ description: 説明 (string, required) - 説明

## TodoUpdateForm
+ title: タイトル (string, required) - タイトル
+ description: 説明 (string, required) - 説明
+ isFinished: true (boolean, required) - 終了したかどうか

## TodoListResponse (object)
+ total: 2 (number, required) - トータル件数
+ list (array[Todo], required) - Todoリスト
