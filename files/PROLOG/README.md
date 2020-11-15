# PROLOG SIX@AUTO

## Knowledge Base

## Inference Engine

## Run the app in an Docker Container

docker build -t sixauto_prolog .
docker run -it sixauto_prolog
consult('/app/MI.pl').
carrega_bc.
NOME DA BASE DE CONHECIMENTO (terminar com .)-> '/app/BD.txt'.