# SEARCH-PARKING-REAL-TIME
Le mini-projet contient 3 microservices :

1 - parking-management-service : Ce microservice récupère les données d'une API (la liste des parkings pour un pays donné).
2 - parking-discovery-service : Ce microservice enregistre les microservices sur Eureka pour la découverte de service.
3 - parking-process-service : Ce microservice calcule les parkings les plus proches en se basant sur les coordonnées GPS 
données en entrée de l'API. Pour cela, il communique avec parking-management-service pour récupérer la liste des parkings pour un pays donné.

## Api rest
- l'image search_park_result.PNG contient le résultat de l'appel de lapi.

## Développement :
- J'ai pensé a développer 2 microservices car j'ai pensé à faire un microservice qui n'évolue pas (parking-process-service) il accepte une liste des parkings 
et retourne les plus proches, et le desième parking-management-service c'est évolutif selon qu'est ce que nous allons prochainement comme country. Car je pense que
chaque pays a son propre structure de données dans l'api.
- Les microservices communiquement entre eux avec une communication sychrone avec l'utilisation de open feign.
- Test : les deux autres images contiennent le résultat de test unitaire et le test d'intégration.

## Améliorations
- On peut externaliser les urls des pays dans un autre microservice qui s'appelle parking-config-service qui va centraliser tout les configs que nous avons dans
l'application.

## Installation
-1 cd parking-discovery-service -> mvn clean install -DskipTests -> mvn spring-boot:run

-2 cd parking-management-service -> mvn clean install -DskipTests -> mvn spring-boot:run

-3 cd parking-process-service -> mvn clean install -DskipTests ->mvn spring-boot:run 
