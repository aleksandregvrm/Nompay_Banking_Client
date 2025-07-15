docker exec -it mysql-container mysql -uroot -e "GRANT ALL PRIVILEGES ON auth.* TO 'application_master'@'%'; FLUSH PRIVILEGES;"
docker exec -it mysql-container mysql -uroot -e "CREATE DATABASE IF NOT EXISTS auth;"
### Those queries need to be run to set up the db.