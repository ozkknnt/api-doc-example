var should = require('should');
var request = require('supertest');
var server = require('../../../app');

describe('controllers', function() {
  describe('todo_api', function() {
    describe('GET /todo', function() {
      it('should response Ok', function(done) {
        request(server)
          .get('/todo')
          .query({ offset: 0, limit: 10})
          .set('Accept', 'application/json')
          .expect('Content-Type', /json/)
          .expect(200)
          .end(function(err, res) {
            should.not.exist(err);
            done();
          });
      });
    });
  });
});
