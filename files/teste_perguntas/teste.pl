:-op(220,xfx,entao).
:-op(35,xfy,se).
:-op(240,fx,regra).
:-op(600,xfy,e).

dic(N):-
	( N = 'codigo', write('Codigo?'), nl);
	true.

find(Functor, LFactos):-
	(Functor = 'codigo', findall(N, facto(N, codigo(_, _)), LFactos));
	true.

carrega_bc:-
	write('NOME DA BASE DE CONHECIMENTO (terminar com .)-> '),
	read(NBC),
	consult(NBC).

arranca_motor:-
	regra Num se LHS entao RHS,
	facto_dispara_regras(Num, LHS, RHS), !.

facto_dispara_regras(Num, LHS, RHS):-
	verifica_condicoes(LHS, LFactos),
	faz_perguntas(Num, LHS, RHS), 
	length(LFactos, Count),
	read(Opcao), nl,
	((Opcao >=1 , Opcao =< Count) -> seguinte() ; write('Nao pode colocar essa opcao')), !.
facto_dispara_regras1(_, []):- !.

verifica_condicoes([X e Y],[N|LF]):- !,
	facto(N,X),
	verifica_condicoes([Y],LF).
verifica_condicoes([X],[N]):- 
	facto(N,X).

faz_perguntas(Num, [X e Y], [RHS]):-
	X=..[Functor, Entidade, Value],
	dic(Functor),
	find(Functor, LFactos), 
	Num1 = 0, 
	escreve_factos(Num1, LFactos).
faz_perguntas(Num, [LHS], [RHS]):- !.

escreve_factos(Num, [I|R]):-
	Num1 is Num+1,
	facto(I,F),
	F=..[_, Entidade, Valor], 
	opcoes(Num1, Entidade, Valor),
	escreve_factos(Num1, R).
escreve_factos(_, []).

opcoes(Num, Entidade, Valor):-
	write(Num), write('. '),
	write(Valor), nl.

seguinte():- !.