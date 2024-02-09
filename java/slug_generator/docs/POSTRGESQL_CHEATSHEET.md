# 1. Update and Install

```shell
sudo apt update
sudo apt install postgresql postgresql-contrib
```

# 2. Verify Service

```shell
sudo systemctl status postgresql
```

Should look like:

```shell
● postgresql.service - PostgreSQL RDBMS
     Loaded: loaded (/lib/systemd/system/postgresql.service; enabled; vendor pr>
     Active: active (exited) since Fri 2024-02-09 16:31:48 +01; 37s ago
    Process: 40324 ExecStart=/bin/true (code=exited, status=0/SUCCESS)
   Main PID: 40324 (code=exited, status=0/SUCCESS)
        CPU: 2ms

feb 09 16:31:48 laptop systemd[1]: Starting PostgreSQL RDBMS...
feb 09 16:31:48 laptop systemd[1]: Finished PostgreSQL RDBMS.
```

# 3. Initial Configuration

Switch to the default `postgres` database user.

```shell
sudo -u postgres psql
```

# 4. Create Development User

Create a new user for your specific development needs.

```postgresql
CREATE USER slug_dev_user WITH PASSWORD 'slug_dev_passwd';
```

# 5. Switch to Password Authentication (md5)

Edit `pg_hba.conf`: 

Open the configuration file:

(often found at `/etc/postgresql/14/main/pg_hba.conf`).

Modify Authentication Mode:

Locate the line for local connections:
```
# "local" is for Unix domain socket connections only
local   all             all                                     peer
```

Change "peer" to "md5".

Restart PostgreSQL server:

```shell
sudo systemctl restart postgresql
```


# 6. Check connection with a newly created user

```shell
psql -U slug_dev -d slug_dev_db
```

# 7. Create Development Database

7.1. Connect as 'postgres' or User with Permissions

7.2. Create a database owned by the user you just created.

```postgresql
CREATE DATABASE slug_dev_db OWNER slug_dev_user; 
```

# 8. Grant access to that user

For PostgreSQL 14

```postgresql
GRANT ALL PRIVILEGES ON DATABASE slug_dev_db TO slug_dev_user;
```

For PostgreSQL 15 granting access significantly changed

# 9. Add Dependency

Include the PostgreSQL JDBC driver dependency

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

# 10. Update `application.properties` file

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/slug_dev_db
spring.datasource.username=slug_dev_user
spring.datasource.password=slug_dev_passwd
```

# 10. Option 

Define Environment Variables:

Add environment variables to your `~/.bashrc` file
to hold your database credentials:

```shell
export SLUG_DB_USER=slug_dev_user
export SLUG_DB_PASSWORD=slug_dev_passwd 
```

Modify your `persistence.xml` to reference these environment variables:

```xml
<persistence-unit name="myPersistenceUnit">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    <properties>
        <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/mydatabase" />
        <property name="javax.persistence.jdbc.user" value="${env.SLUG_DB_USER}" />
        <property name="javax.persistence.jdbc.password" value="${env.SLUG_DB_PASSWORD}" />
    </properties>
</persistence-unit>
```
