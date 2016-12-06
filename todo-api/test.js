var dt = require('dredd-transactions');
var drafter = require('drafter.js');
var fs = require('fs');
var util = require('util');

fs.readFile("./api-blueprint/todo-api.md", function(err, data) {
  // console.log(Buffer(data).toString());
  var doc = Buffer(data).toString();
  // dt.compile(doc, './api-blueprint/todo-api.md', function(err, compilationResult) {
  //   compilationResult.transactions.forEach(function(transaction) {
  //     console.log(transaction);
  //   });
  // });
  var options = {
    type: 'ast'
  };
  drafter.parse(doc, options, function(err, result) {
    console.log(util.inspect(result, false, null));
  })
});
