# account-api

### Migrations

Create migration files in /src/main/resources/migrations

    java -jar target/.jar db -h
    java -jar target/.jar db status config.yml
    java -jar target/.jar db migrate config.yml
    java -jar target/.jar db migrate --dry-run config.yml //print the SQL

Run with optimization

    java -jar -XX:+UseNUMA -XX:+UseCompressedOops -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+AggressiveOpts -XX:+UseFastAccessorMethods -XX:+UseBiasedLocking -XX:NewRatio=2 -server -d64 -Xms1g -Xmx1g target/account-api-0.0.1-SNAPSHOT.jar server config.yml


Benchmark with post request

    ab -n 20000 -c 10 http://localhost:8080/accounts
    ab -p ./content.json -T application/json -c 10 -n 20000 http://localhost:8080/accounts
