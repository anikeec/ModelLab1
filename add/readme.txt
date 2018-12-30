Архів filesForLab2 містить файли з випадковими числами для дослідження у лаб2

Фреймворк Simulation

 Jar-архів Simulation2018 Файл
Фреймворк містить класи та інтерфейси, які допомагають створювати програмні додатки 
для моделювання систем масового обслуговування та систем з агентами у середовищі Java.

Бібліотеку слід підключати до проектів, що створюються у межах виконання індивідуальних 
завдань до лабораторних робіт та розрахункоао графічної роботи.

Тести

 Jar-файл з тестами
Файл можна використовувати для підготовки до здачі тестів з лабораторних робіт та з усього курсу 

mvn install:install-file -Dfile=d:/Simulation2018.jar -DgroupId=ua.cn.stu -DartifactId=Simulation2018 -Dversion=0 -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=d:/SimulationTests2018.jar -DgroupId=ua.cn.stu -DartifactId=SimulationTests2018 -Dversion=0 -Dpackaging=jar -DgeneratePom=true