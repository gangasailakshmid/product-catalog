# Product Catalog Service

Spring Boot microservice for catalog operations: styles, products, and ticket price history.

## Service Scope
- Manage styles
- Manage products
- Manage time-phased ticket prices

## Tech Stack
- Java 25
- Spring Boot 4
- Spring Data JPA
- H2 / PostgreSQL
- OpenAPI (Swagger)

## Default Port
- `8081`

## Architecture Flow
```mermaid
flowchart LR
    C[Client] --> API[Product Catalog API]
    API --> CT[Controllers]
    CT --> SV[Services]
    SV --> RP[Repositories]
    RP --> DB[(H2 / PostgreSQL)]
    SV --> EH[Global Exception Handler]

    CT --> STY[Style Module]
    CT --> PRD[Product Module]
    CT --> TPH[Ticket Price Module]
```

## Sequence Diagram
```mermaid
sequenceDiagram
    participant U as API Client
    participant C as Controller
    participant S as Service
    participant R as Repository
    participant D as Catalog DB
    participant E as Exception Handler

    U->>C: POST/PUT/GET /styles|/products|/ticket-prices
    C->>S: Validate + map DTO
    S->>R: CRUD by business key
    R->>D: SQL via JPA
    D-->>R: rows/result
    R-->>S: entity
    S-->>C: response DTO
    C-->>U: HTTP 2xx

    alt not found / validation / integrity error
      S-->>E: throw exception
      E-->>U: HTTP 4xx/5xx with error body
    end
```

## Database Schema
- `style`
- `product` (FK -> `style`)
- `ticket_price_history` (FK -> `product`, unique `product_id + effective_date`)

### ER Diagram
```mermaid
flowchart TD
  STYLE[style] -->|1..N| PRODUCT[product]
  PRODUCT -->|1..N| TPH[ticket_price_history]
```

## Key APIs
- `GET /api/v1/styles`
- `GET /api/v1/styles/{styleId}`
- `POST /api/v1/styles`
- `PUT /api/v1/styles/{styleId}`
- `DELETE /api/v1/styles/{styleId}`
- `GET /api/v1/products`
- `GET /api/v1/products/{productId}`
- `POST /api/v1/products`
- `PUT /api/v1/products/{productId}`
- `DELETE /api/v1/products/{productId}`
- `GET /api/v1/ticket-prices`
- `GET /api/v1/ticket-prices/{productId}`
- `POST /api/v1/ticket-prices`
- `PUT /api/v1/ticket-prices/{productId}/{effectiveDate}`
- `DELETE /api/v1/ticket-prices/{productId}/{effectiveDate}`

## Build and Run
```bash
./gradlew clean build
./gradlew bootRun
```

Run with PostgreSQL profile:
```bash
./gradlew bootRun --args='--spring.profiles.active=postgres'
```

## API Docs
- Swagger: `http://localhost:8081/swagger-ui.html`
- OpenAPI: `http://localhost:8081/api-docs`
