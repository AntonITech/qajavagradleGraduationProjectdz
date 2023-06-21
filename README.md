# Дипломный проект профессии «Тестировщик»

[План автоматизации](Plan.md)

[Отчет по итогам тестирования](Report.md)

[Отчет по итогам автоматизации](Summary.md)

[Allure Report](Allure_Report.md)

---------------------
## Инструкция по запуску

1. Склонировать репозиторий  
   <code>git clone https://github.com/AntonITech/qajavagradleGraduationProjectdz.git</code>

2. Перейти в папку: qajavagradleGraduationProjectdz

3. Запустить контейнеры docker:

   Для работы с базой данных mysql выполнить команду:  
   <code>docker-compose -f docker-compose-mysql.yml up -d</code>
   После прогона тестов остановить контейнеры:  
   <code>docker-compose -f docker-compose-mysql.yml down</code>

   Для работы с базой данных postgres выполнить команду:  
   <code>docker-compose -f docker-compose-postgres.yml up -d</code>
   После прогона тестов остановить контейнеры:  
   <code>docker-compose -f docker-compose-postgres.yml down</code>

4. Запустить приложение:

   Для запуска приложения с базой данных mysql выполнить команду:  
   <code>java -Dspring.datasource.url=jdbc:mysql://192.168.99.100:3306/app -jar aqa-shop.jar</code>

   Для запуска приложения с базой данных postgres выполнить команду:  
   <code>java -Dspring.datasource.url=jdbc:postgresql://192.168.99.100:5432/app -jar aqa-shop.jar</code>

4. Запустить тесты:

   Для запуска тестов с базой данных mysql выполнить команду:  
   <code>gradlew test -Ddb.url=jdbc:mysql://192.168.99.100:3306/app</code>

   Для запуска тестов с базой данных postgres выполнить команду:  
   <code>gradlew test -Ddb.url=jdbc:postgresql://192.168.99.100:5432/app</code>

5. Сформировать отчеты командой:  
   <code>gradlew allureReport</code>

6. Открыть отчеты в браузере командой:  
   <code>gradlew allureServe</code>
