# WHMS

## Prerequisites

- Docker
- Java 21
- Python (for simulating sensor messages)

## How to run

- start the kafka broker by running 
```
docker-compose up -d
```
- start the cms & whs1 services

- simulate sensor sending messages (running any of these)

```
py sender.py
py sender10.py
```
