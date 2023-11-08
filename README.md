# ru-liga-food-service
Сервис доставки еды
<h1>Сервис по доставке еды. Общая структура.</h1>
Cтруктура:

- Dependency Bom
<br>
- Common
<br>
- Migration
<br>
- Order Service
<br>
- Kitchen Service
<br>
- Delivery Service
- <br>
- Notification Service
<h3>Стек технологий</h3>

- Java 11 
<br>
- Spring Boot
<br>
- Spring Data JPA
<br>
- Spring Security
<br>
- Spring Web
<br>
- LiquiBase
<br>
- PostgreSQL
<br>
- Hibernate
<br>
- Lombok
<br>
- RabbitMQ
<br>
- Swagger
<br>
- Slf4j 
<br>
- JUnit 
<h2>Order-service <h2>
- Отправление заказа в ресторан
- Регистрация и авторизация
- Создание и редактирование пользователя
- Получение оповещений о том что заказ принят от ресторана
- Получение оповещений о том что заказ взят курьером
- Просмотр статуса заказа


<h2> Kithcen-service<h2>
- Регистрация и авторизация ресторана
- Создание и редактирвоание ресторана
- Создание и редактирование меню ресторана
- Изменение работы ресторана,статусы (Открыт-заказ может принимать заказы,Закрыт-не может принимать заказы)
- Отображение списка блюд
- Принятие и обработка или отказ от заказа
- Оповещение покупателя о статусе заказа
- Отправка уведомления курьерам что заказ можно доставить
- Прием заказа от клиента и его обработка
- Подготовка к доставке и упаковка заказа

<h2> delivery-service<h2> 
- Получение заказа на доставку
- Обработка заявки и отправка ее курьеру с указанием адреса доставки
- Подтверждение передачи заказа клиенту

<h2>Dependency Bom</h2>
Содержит зависимости используемые в проекте,и их версии.
<br>
зависимости храняться в <b>pom.xml</b>
<br>
<h2>Common</h2>
Common модуль содежит в себе общие сущности,DTO,Enum 
которая используется для проверки наличия сущности при обращении к бд.
<h2>Migration</h2>
Модуль Migration-взаимодействует с Базой данный с помощью <b>LiquidBase.</b>
<br>
Модуль создает базовые таблицы в БД, необходимые для работы всего проекта, а также отдельную схему <b>auth</b> в которой находятся таблицы необходимые для обеспечения авторизации и аутентификации пользователей.



