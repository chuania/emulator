## Эмулятор Балансового Сервиса

## Описание

Эта программа представляет собой эмулятор сервиса для обработки запросов на получение баланса. 
Сервис реализован с использованием Spring Boot и предназначен для демонстрации обработки запросов с информацией о клиенте,
предоставляя информацию о балансе в зависимости от идентификатора клиента и валюты.

## Эндпоинты

### `POST /info/postBalances`

**Описание**: Обрабатывает запросы для получения информации о балансе клиента.

**Запрос**:

- **Content-Type**: `application/json`
- **Тело запроса**:
  ```json
  {
    "rqUID": "unique-request-id",
    "clientId": "8123456789",
    "account": "123456789",
    "openDate": "2024-01-01",
    "closeDate": "2024-12-31"
  }
