# Spring Boot Lab 7

## Run

Run `MySpringBoot2DbaseApplication` from IntelliJ or execute `mvnw.cmd spring-boot:run`.
The application uses port `8082` and stores its H2 database at
`E:/Lab_sem3/testdb/students-lab7` so it does not modify the Lab 6 database.

## Pages

- `/list` — student list and CRUD form links
- `/groups` — study group list and CRUD form links

## Actuator

- `/actuator/health`
- `/actuator/info`
- `/actuator/metrics`
- `/actuator/metrics/jvm.memory.used`
- `/actuator/metrics/process.uptime`
