# account-api

### Migrations

Create migration files in /src/main/resources/migrations

    java -jar target/.jar db -h
    java -jar target/.jar db status config.yml
    java -jar target/.jar db migrate config.yml
    java -jar target/.jar db migrate --dry-run config.yml //pring the SQL
