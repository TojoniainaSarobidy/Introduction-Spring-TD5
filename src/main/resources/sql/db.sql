CREATE DATABASE spring_boot_db;
CREATE USER spring_boot_user WITH PASSWORD '123456';
GRANT ALL PRIVILEGES ON DATABASE spring_boot_db TO spring_boot_user;
GRANT ALL PRIVILEGES ON SCHEMA PUBLIC TO spring_boot_user;