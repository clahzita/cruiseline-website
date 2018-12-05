CREATE TABLE pacote_info (
	pacote_id UUID PRIMARY KEY,
	pacote_local_partida TEXT,
	pacote_dia_partida TEXT,
	pacote_menor_preco DOUBLE
);

CREATE TABLE pacote_navio (
	pacote_id UUID PRIMARY KEY,
	navio_id INT,
	navio_maximo INT,
	navio_disponiveis INT
);

CREATE TABLE navio_cabine (
	navio_id INT ,
	cabine_numero INT,
	cabine_tipo TEXT,
	cabine_disponivel BOOLEAN,
	cabine_preco DOUBLE,
	PRIMARY KEY (navio_id,cabine_numero)
);

CREATE TABLE reserva_info (
	reserva_id UUID PRIMARY KEY,
	usuario TEXT,
	pacote_id UUID,
	navio_id INT,
	cabines_numero LIST<INT>
);
ALTER TABLE reserva_info DROP cabines_numero;
ALTER TABLE reserva_info ADD cabine_numero LIST<INT>;
