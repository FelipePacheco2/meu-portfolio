-- Tabela de Piquetes
CREATE TABLE surrounded (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            name VARCHAR(50) NOT NULL,
                            max_capacity INTEGER NOT NULL,
                            type ENUM('FATTENING', 'MATERNITY', 'PASTURE_REST', 'QUARANTINE', 'REPRODUCTION') NOT NULL,
                            create_date DATETIME(6),
                            PRIMARY KEY (id),
                            CONSTRAINT UK_surrounded_name UNIQUE (name)
) ENGINE=InnoDB;

CREATE TABLE animal (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        tag_identifier VARCHAR(50) NOT NULL,
                        breed TINYINT NOT NULL CHECK (breed BETWEEN 0 AND 4),
                        status ENUM('ACTIVE', 'DECEASED', 'SOLD') NOT NULL,
                        birth_date DATE NOT NULL,
                        surrounded_id BIGINT NOT NULL,
                        PRIMARY KEY (id),
                        CONSTRAINT UK_animal_tag UNIQUE (tag_identifier),
                        CONSTRAINT FK_animal_surrounded FOREIGN KEY (surrounded_id) REFERENCES surrounded (id)
) ENGINE=InnoDB;

-- Tabela de Pesagens
CREATE TABLE weighing (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          weight FLOAT(53) NOT NULL,
                          daily_weight FLOAT(53) NOT NULL,
                          weight_date DATETIME(6) NOT NULL,
                          animal_id BIGINT NOT NULL,
                          PRIMARY KEY (id),
                          CONSTRAINT FK_weighing_animal FOREIGN KEY (animal_id) REFERENCES animal (id)
) ENGINE=InnoDB;