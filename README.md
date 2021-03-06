
# Examplereceipt

**REQUISITI**

Implementare un'applicazione (Java, Kotlin o PHP) il cui scopo sia stampare la ricevuta di un acquisto in negozio. 
Dato il prezzo netto del bene, vengono applicate le seguenti regole di tassazione:

- la tassa base è il 10%;
- libri, cibo e prodotti farmaceutici sono esenti;
- alla merce importata dall'estero viene applicata una tassa addizionale del 5% (prodotti esenti inclusi);

Al momento del pagamento viene prodotta una ricevuta che indica nome e tipo di prodotti, prezzi tassati, tasse totali e costo totale.
I prezzi devono avere 2 decimali. 

**ESEMPIO**

Input: - Libro "Il signore degli anelli", prezzo 12.49 - CompactDisc "Innuendo", prezzo 14.99 - Snack "Cioccolata", prezzo 0.85 
Output: - Il signore degli anelli - BOOK: 12.49 Innuendo - MUSIC: 16.49 Cioccolata - FOOD: 0.85 TAXES: 1.50 TOTAL: 29.83 

**OPZIONALE**

Ogni 10 pagamenti la ricevuta riporta il testo del concorso:
"Se ti chiami ###NOME### hai vinto un buono da 50 euro"
Si prega di utilizzare questa API per recuperare il nome https://randomuser.me/api

## Description

Applicazione backend che espone due servizi REST per soddisfare i requisiti di cui sopra.
In src/main/resources/static è presente una collezione 2.1 di Postman pronta per essere importata e testata con request e response già configurati.
I servizi sono:

- [GET] `/product/all`: restituisce la lista di tutti i prodotti presenti a database;
- [POST] `/receipt/calculate`: calcola il testo di una ricevuta dati i prodotti selezionati.

L'applicazione si basa su un databse H2 locale. All'avvio dell'applicazione, vengono eseguite le query di INSERT presenti in /src/main/resources così da popolare la tabella dei prodotti.

## Getting Started

Su una macchina configurata per applicativi Spring Boot, dopo aver clonato il repository, è sufficiente lanciare il comando:

```
mvn spring-boot:run
```

## Building

```
mvn clean install
```

Per eseguire l'applicazione una volta compilata:

```
java -jar ./target/examplereceipt-1.0.jar
```

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot)

## Authors

* **Luca Pierini** - *Initial work* - [LucaPieriniIT](https://github.com/LucaPieriniIT)

## Acknowledgments

L'applicazione è da intendersi solo dev-purposed. Non è stata realizzata con l'ottica di un deploy in production.
