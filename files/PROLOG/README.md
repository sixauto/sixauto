# PROLOG SIX@AUTO

## Run the app in an Docker Container

docker build -t sixauto_prolog .
docker run -it -v local_path:/app sixauto_prolog
consult('/app/MI.pl').
carrega_bc.
NOME DA BASE DE CONHECIMENTO (terminar com .)-> '/app/BC.txt'.
arranca_motor.