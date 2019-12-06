# SimpleCreditor

Тестовое задание 
Язык: Java 
Другие инструменты: бд in-memory h2, Bootstrap, FreeMarker, Spring, Hibernate, REST

Простое web-приложение, реализующее операции:
- регистрация
- аутентификация администратора/пользователя
- список заявок на кредит всех/конкретного пользователя
- подача пользователем заявки на кредит
- ведение администратором черного списка пользователей
- ведение администратором лимитов количества заявок в минуту для стран

Запуск: приложение открывается по url: http://localhost:8085

По умолчанию инициализированы данные: 
  - пользователь: "admin", пароль: "admin", администратор: да, в черном списке: нет 
  - пользователь: "user1", пароль: "p1", администратор: нет, в черном списке: нет 
  - пользователь: "user2", пароль: "p2", администратор: нет, в черном списке: нет 
  - пользователь: "user3", пароль: "p3", администратор: нет, в черном списке: да 
  - пользователь: "user4", пароль: "p4", администратор: нет, в черном списке: нет 
  
  - заявки от пользователя: user1, кол-во: 3
  - заявки от пользователя: user2, кол-во: 2
  
  - лимит заявок ля страны: "Japan", кол-во: 2
  - лимит заявок для страны: "China", кол-во: 0
