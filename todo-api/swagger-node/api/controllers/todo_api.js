'use strict';

var util = require('util');
var request = require('request');
var host = 'http://localhost:9000';

function getTodoList(req, res) {
  var offset = req.swagger.params.offset.value;
  var limit = req.swagger.params.limit.value;
  var query = {
    offst: offset,
    limit: limit
  };
  var options = {
    url: host + '/todo',
    headers: {
      'Content-Type': 'application/json'
    },
    qs: query
  };
  request.get(options, function(err, response, body) {
    res.status(response.statusCode).json(body);
  });
}

function createTodo(req, res) {
  var options = {
    url: host + '/todo',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(req.body)
  };
  request.post(options, function(err, response, body) {
    res.status(response.statusCode).json(body);
  });
}

function updateTodo(req, res) {
  var todoId = req.swagger.params.todoId.value;
  var options = {
    url: host + '/todo/' + todoId,
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(req.body)
  };
  request.put(options, function(err, response, body) {
    res.status(response.statusCode).json(body);
  });
}

function deleteTodo(req, res) {
  var todoId = req.swagger.params.todoId.value;
  var options = {
    url: host + '/todo/' + todoId
  };
  request.delete(options, function(err, response, body) {
    res.status(response.statusCode).send();
  });
}

module.exports = {
  getTodoList: getTodoList,
  createTodo: createTodo,
  updateTodo: updateTodo,
  deleteTodo: deleteTodo
};
