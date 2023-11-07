# Client Service


Dummy сервис. Предоставляет OAuth защищенные REST API. 

## Конфигурация

Конфигурация сервиса находится в  `application.yml` . 
Необходимо указать issuer-uri OAuth сервера, который будет использоваться для аутентификации.

## API

### /message

Возвращает приветственное сообщение. Доступно только для SCOPE_message.read.

### /resource

Возвращает тестовый ресурс. Так же доступно для SCOPE_message.read.

## Классы конфигурации

- [OAuth2ResourceServerSecurityConfiguration.java](src%2Fmain%2Fjava%2Fru%2Fliga%2Fclientservice%2Fconfig%2FOAuth2ResourceServerSecurityConfiguration.java) Конфигурация OAuth ресурс сервера. Этот класс отвечает за настройку аутентификации и авторизации в приложении.

1.  `oauth2ResourceServer()` : Этот метод указывает, что приложение использует OAuth2 для защиты ресурсов.

2.  `jwt()` : Этот метод указывает, что для аутентификации и авторизации будет использоваться JSON Web Token (JWT).

Для доступа к API необходимо получить токен доступа у OAuth сервера и указать его в заголовке  `Authorization: Bearer <token>`.
Подстановка токена в заголовки осуществляется с помощью фильтра `TokenRelay=` в Spring Gateway