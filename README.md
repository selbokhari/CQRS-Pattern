# Implémentation de CQRS Pattern

## Description

L'implémentation implique la séparation des opérations de lecture et d'écriture (read & write) de la table des clients entre les services order-service et customer-service :

- **Le service order-service** est responsable de la gestion des opérations de lecture.
- **Le service customer-service** est chargé de la gestion des opérations d'écriture.
- **La cohérence des données** entre ces deux microservices est assurée grâce à une communication asynchrone via Kafka.


![Architecture de l'application](https://raw.githubusercontent.com/selbokhari/CQRS-Pattern/main/CQRS_1.png) 

## Utilisation

1. Démarrer Zookeeper: `docker-compose -f common.yml -f zookeeper.yml up`
2. Vérifier le statut de Zookeeper: `echo ruok | nc localhost 2181`
3. Démarrer le cluster Kafka: `docker-compose -f common.yml -f kafka_cluster.yml up`
4. Initialiser les topics Kafka: `docker-compose -f common.yml -f init_kafka.yml up`
5. Démarrer les micro-services.

## Tests de bout en bout

1. Afin de créer un customer (sur customer-service) : Envoyer une requête POST à `http://localhost:8184/customers` avec le body suivant:

```json
{
  "customerId": "d215b5f8-0249-4dc5-89a3-51fd148cfb42",
  "username": "user_1",
  "firstName": "first",
  "lastName": "User"
}

```

## Réponse :

```json
{
    "customerId": "d215b5f8-0249-4dc5-89a3-51fd148cfb42",
    "message": "Customer created successfully"
}
```

1. Le service `customer-service` persiste les informations du client sur `customer.customers` et déclenche un événement sur le topic `customer` dans Kafka.
2. Le service `order-service` reçoit l'événement déclenché et met à jour la table `order.customers` dans sa base de données.
