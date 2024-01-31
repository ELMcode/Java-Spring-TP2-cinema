# Cinema API App - Java Spring - TP 2 & 3

## App
This project is a Spring Boot application that serves a RESTful API.
The API is designed to manage Films, Actors, and Directors

## API Endpoints

### Actors
- `GET /acteurs` - Retrieve all actors.
- `GET /acteurs/{id}` - Retrieve a specific actor by ID.

### Films
- `GET /films` - Retrieve all films.
- `GET /film/{id}` - Retrieve a specific film by ID.
- `GET /film/{id}/acteurs` - Retrieve actors for a specific film.
- `GET /film/{id}/realisateur` - Retrieve the director for a specific film.
- `POST /film/{id}/acteurs` - Add an existing actor to a film.

### Directors
- `GET /realisateurs` - Retrieve all directors.
- `GET /realisateurs/{id}` - Retrieve a specific director by ID.
- `GET /realisateurs/{id}/films` - Retrieve films for a specific director.