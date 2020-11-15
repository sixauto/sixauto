# PROLOG SIX@AUTO

## Run the app in an Docker Container

docker build -t sixauto_prolog .
docker run -it -v "local_path"/sixauto/files/Prolog:/app sixauto_prolog
arranca_motor.
mostra_factos.

## Web Server

server(8000).