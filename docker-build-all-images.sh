cd eureka-server
docker build -t microservices/eureka .
cd ..
cd eureka-client-exam-service
docker build -t microservices/exam .
cd ..
cd eureka-client-gateway-service
docker build -t microservices/gateway .
cd ..
cd eureka-client-registration-service
docker build -t microservices/reg .
cd ..
cd eureka-client-report-service
docker build -t microservices/report .
cd ..
cd eureka-client-student-service
docker build -t microservices/student .
cd ..
cd eureka-client-subject-service
docker build -t microservices/subject .
cd ..

