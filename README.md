# Wolt Software Engineering Internship Backend 2026 Project
### Spec: https://github.com/woltapp/backend-internship-2025


## 📋 Prerequisites
- **[Gradle](https://gradle.org/)** (8.12 or higher)  
- **[Java](https://www.oracle.com/java/technologies/downloads/#java21)** (Version 19 or higher)
---

The **Ktor Netty Server** is configured to listen on:
- **Host**: `localhost`
- **Port**: `8000`

## 🛠️ Testing

### Execute Tests
```bash
./gradlew test
```

### 1. Build Jar file
```bash
./gradlew clean build
```
### 2. Run Jar file
```bash
java -jar ~/2026internWolt/app/build/libs/app.jar
```

## 🔎 Source Dictionaly
```bash
app/
└── src/
    └── main/
        └── Kotlin/
            └── org/
                └── dopc/
                    ├── App.kt                       # Application entry point
                    ├── routing.kt                   # Main routing configuration
                    ├── client/
                    │   ├── httpClientFactory.kt        # Singleton Ktor HTTP client
                    │   └── venueInfoClient.kt          # Client to fetch Dynamic/Static venue information from external API
                    ├── dto/
                    │   ├── dopcReqParamsDTO.kt         # Data transfer object for client request parameters
                    │   └── dopcResJsonDTO.kt           # Data transfer object for response JSON structure
                    ├── exception/                      # Custom exceptions
                    │   └── baseException/              # Base exception classes
                    │       ├── httpClientExceptionBase.kt     # Base class for client's http request oriented exceptions
                    │       └── httpServerExceptionBase.kt     # Base class for Internal server oriented exceptions
                    ├── model/                           # Data models used in service
                    ├── plugin/                          # Ktor plugins
                    ├── routing/                         # Sub-routing configurations
                    ├── service/
                    │   ├── dopcService.kt               # Main service orchestration
                    │   ├── pricing/
                    │   │   ├── calculateDeliveryFee.kt  # Calculates delivery fee based on distance
                    │   │   ├── calculateSmallOrderSurcharge.kt  # Calculates small order surcharge
                    │   │   └── calculateTotalPrice.kt        # Calculates total delivery fee
                    │   ├── deliveryDistance/
                    │   │   └── calculateDistance.kt      # Calculates straight-line distance between two coordinates                    


app/
└── src/
    └── test/
        └── kotlin/
            └── dopc/
                ├── service/
                │   ├── pricing/
                │   │   ├── calculateDeliveryFeeTest.kt        # Unit tests for 'calculateDeliveryFee.kt'
                │   │   ├── calculateSmallOrderSurchargeTest.kt # Unit tests for 'calculateSmallOrderSurcharge.kt'
                │   │   └── calculateTotalPriceTest.kt          # Unit tests for 'calculateTotalPrice.kt'
                │   └── deliveryDistance/
                │       └── calculateDistanceTest.kt            # Unit tests for 'calculateDistance.kt'
                └── client/
                    └── venueInfoClientTest.kt                  # Unit tests for 'venueInfoClient.kt

```
Code Owner: hibiki.shibata@wolt.com

Todo:
1. Integration tests for the entire service
4. Add logging for better traceability
5. Documentation for public functions
6. Implement caching for venue information to reduce API calls
7. timtout error handling for external API calls
