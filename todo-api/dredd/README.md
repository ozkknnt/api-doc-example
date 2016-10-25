### dreddの実行方法

1. dreddのインストール
```
$ npm install -g dredd
```

2. サンプルアプリケーションを実行しておく(sbtが必要)
```
$ cd ../swagger-play2.4
$ sbt run
```

3. テストを実行
  - API blueprint
    ```
    $ cd api-blueprint
    $ dredd
    ```

  - Swagger
    ```
    $ cd swagger
    $ dredd
    ```
