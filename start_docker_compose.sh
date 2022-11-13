export POSTGRES_USER=postgres
export POSTGRES_PASSWORD=postgres
export DATASOURCE_URL=jdbc:postgresql://transactions-database:5432/transactions?currentSchema=transactions
docker build -t transactions_api .
docker-compose up -d --force-recreate