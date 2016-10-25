'use strict';
var chai = require('chai');
var ZSchema = require('z-schema');
var validator = new ZSchema({});
var supertest = require('supertest');
var api = supertest('http://localhost:10010'); // supertest init;
var expect = chai.expect;

describe('/todo/{todoId}', function() {
  describe('put', function() {
    it('should respond with 200 Success Update', function(done) {
      /*eslint-disable*/
      var schema = {
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64",
            "description": "ID"
          },
          "title": {
            "type": "string",
            "description": "タイトル"
          },
          "description": {
            "type": "string",
            "description": "説明"
          },
          "isFinished": {
            "type": "boolean"
          }
        },
        "required": [
          "id",
          "title",
          "description",
          "isFinished"
        ]
      };

      /*eslint-enable*/
      api.put('/todo/1')
      .set('Accept', 'application/json')
      .send({
        title: 'title2',
        description: 'description2',
        isFinished: true
      })
      .expect(200)
      .end(function(err, res) {
        if (err) return done(err);

        expect(validator.validate(res.body, schema)).to.be.true;
        done();
      });
    });

  });

  describe('delete', function() {
    it('should respond with 204 Success Delete', function(done) {
      api.del('/todo/1')
      .set('Accept', 'application/json')
      .expect(204)
      .end(function(err, res) {
        if (err) return done(err);
        done();
      });
    });

  });

});
