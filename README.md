<h1>SpringBoot Based Microservices</h1>


Eureka Server
http://localhost:8761/

Student Service:
http://localhost:8762/data

Subject Service:
http://localhost:8763/data


Registration Service:
http://localhost:8764/data

Exam Service:
http://localhost:8765/data


Report Service:

(Regular Version)
http://localhost:8766/report/find_by_roll_number?roll_number=1

(Circuit Breaker Version)
http://localhost:8766/report_cb/find_by_roll_number?roll_number=1

Consolidated Gateway Service:
(Base Service)
http://localhost:8767/

(Delegated Services)
http://localhost:8767/STUDENT-SERVICE/data
http://localhost:8767/EXAM-SERVICE/data
http://localhost:8767/SUBJECT-SERVICE/data
http://localhost:8767/REGISTRATION-SERVICE/data
http://localhost:8767/REPORT-SERVICE/report_cb/find_by_roll_number?roll_number=1



