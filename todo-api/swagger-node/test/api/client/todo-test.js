'use strict';
var chai = require('chai');
var ZSchema = require('z-schema');
var validator = new ZSchema({});
var supertest = require('supertest');
var api = supertest('http://localhost:10010'); // supertest init;
var expect = chai.expect;

describe('/todo', function() {
  describe('get', function() {
    it('should respond with 200 Success Get', function(done) {
      /*eslint-disable*/
      var schema = {
        "properties": {
          "total": {
            "type": "integer",
            "description": "トータル数"
          },
          "list": {
            "type": "array",
            "items": {
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
            }
          }
        },
        "required": [
          "list"
        ]
      };

      /*eslint-enable*/
      api.get('/todo')
      .query({
        offset: 1,limit: 10
      })
      .set('Accept', 'application/json')
      .expect(200)
      .end(function(err, res) {
        if (err) return done(err);

        expect(validator.validate(res.body, schema)).to.be.true;
        done();
      });
    });

  });

  describe('post', function() {
    it('should respond with 201 Success Create', function(done) {
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
      api.post('/todo')
      .set('Accept', 'application/json')
      .send({
        title: 'title1',
        description: 'description1'
      })
      .expect(201)
      .end(function(err, res) {
        if (err) return done(err);

        expect(validator.validate(res.body, schema)).to.be.true;
        done();
      });
    });

  });

});
