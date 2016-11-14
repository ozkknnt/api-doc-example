var dt = require('dredd-transactions');
var fs = require('fs');

fs.readFile("./api-blueprint/todo-api.md", function(err, data) {
  // console.log(Buffer(data).toString());
  var doc = Buffer(data).toString();
  dt.compile(doc, './api-blueprint/todo-api.md', function(err, compilationResult) {
    compilationResult.transactions.forEach(function(transaction) {
      console.log(transaction);
    });
  });
});
