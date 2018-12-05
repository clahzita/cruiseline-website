CREATE TABLE cruzeiros.cabine (
	numero INT,
	tipo TEXT,
	disponivel BOOLEAN,
	preco DOUBLE,
	PRIMARY KEY (id)
);

CREATE TYPE cruzeiros.navio_type (
	quantidade_cabines INT,
	cabines_disponiveis INT,
	todas_cabines list<FROZEN <cabine_type>>
);


CREATE TABLE cruzeiros.pacote_info (
	id UUID,
	lugar TEXT,
	preco_minimo DOUBLE,
	navio FROZEN <navio_type>,
	partida DATE,
	quantidadeDias INT,
	PRIMARY KEY (id)
);

CREATE TYPE cruzeiros.pacote_type (
	lugar TEXT,
	preco_minimo DOUBLE,
	navio FROZEN <navio_type>,
	partida DATE,
	quantidadeDias INT
);

CREATE TYPE cruzeiros.usuario_type (
	login TEXT
);

CREATE TABLE cruzeiros.reserva_info (
	id UUID,
	usuario FROZEN<usuario_type>,
	pacote FROZEN<pacote_type>,,
	todas_cabines list<FROZEN <cabine_type>>
);


