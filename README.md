# ru-liga-food-service
Сервис доставки еды
<h1>Сервис по доставке еды.</h1>
Cтруктура:

- Dependency Bom (Содержит зависимости используемые в проекте,и их версии.)
- Common ( модуль содежит в себе общие сущности,DTO,Enum )
- Migration (содержит скрипты инициализации таблиц бд)
- Order Service (сервис отвечающий за crud операции заказов и оповещение)
- Kitchen Service (сервис отвечающий за приготовление заказов)
- Delivery Service (сервис отвечающий за доставку заказов)
- Notification Service (сервис отвечающий за отправление оповещений в)
<h3>Стек технологий</h3>
- Java 11 
- Spring Boot
- Spring Data JPA
- Spring Security
- Spring Web
- LiquiBase
- PostgreSQL
- Hibernate
- Lombok
- RabbitMQ
- Swagger
- Slf4j 
- JUnit 
<h2>Основные модули</h2>

### Order-service ###
- Отправление заказа в ресторан
  <br>
- Регистрация и авторизация
  <br>
- Создание и редактирование пользователя
  <br>
- Получение оповещений о том что заказ принят от ресторана
  <br>
- Получение оповещений о том что заказ взят курьером
  <br>
- Просмотр статуса заказа

### Kithcen-service ###
- Создание и редактироваание ресторана и меню ресторана
  <br>
- Отображение списка блюд
  <br>
- Принятие и обработка или отказ от заказа
  <br>
- Оповещение покупателя и кухни о каждом статусе заказа
  <br>
- Отправка уведомления курьерам через RabitMq  заказ готов к выдаче
  <br>
- Прием заказа от клиента и его обработка

### delivery-service ###
- Регистрация и авторизация
  <br>
- Получение заказа на доставку
  <br>
- Обработка заявки и отправка ее курьеру с указанием адреса доставки
  <br>
- Подтверждение передачи заказа клиенту
<h2> Запуск бд<h2>
  Для старта предварительно нужно запустить образы RabitMq и PostgreSql
    <br>
 добавьте в свой проект:
```docker run -lt --rm --name rabbitmq -p 5672:5672
-p 15672:15672 rabbitmq:3-management
 запускает RabitMq"
```
      <br>
<h2>Схема бд</h2>
![image](https://github.com/Powerandzeal/ru-liga-food-service/assets/102437425/4749e292-9c38-4645-bfed-2fdee776c421)




