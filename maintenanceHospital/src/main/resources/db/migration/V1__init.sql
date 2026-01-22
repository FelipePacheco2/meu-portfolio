CREATE TABLE category_heritage (
                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   name VARCHAR(50) NOT NULL,
                                   description TEXT NOT NULL
);

CREATE TABLE employee (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(30) NOT NULL
);

CREATE TABLE location_hospital (
                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   name VARCHAR(30) NOT NULL
);


CREATE TABLE heritage_type (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               name VARCHAR(50) NOT NULL,
                               description TEXT NOT NULL,
                               critically ENUM('ALTA', 'BAIXA', 'EMERGENCIA', 'MEDIA') NOT NULL,
                               category BIGINT NOT NULL,
                               CONSTRAINT FK_heritage_type_category FOREIGN KEY (category) REFERENCES category_heritage (id)
);

CREATE TABLE order_service (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               protocol VARCHAR(255) UNIQUE,
                               description VARCHAR(255),
                               opening_date DATETIME(6),
                               end_date DATETIME(6) NOT NULL,
                               closing_date DATETIME(6),
                               priority ENUM('ALTA', 'BAIXA', 'EMERGENCIA', 'MEDIA') NOT NULL,
                               status_service ENUM('CANCELADO', 'ESPERANDO_PECA', 'FINALIZADO', 'PENDENTE') NOT NULL,
                               technical_id BIGINT NOT NULL,
                               CONSTRAINT FK_os_technical FOREIGN KEY (technical_id) REFERENCES employee (id)
);

CREATE TABLE heritage (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          tag VARCHAR(5) NOT NULL,
                          serial_number VARCHAR(20),
                          date_buy DATETIME(6),
                          warranty_limit DATETIME(6),
                          location BIGINT NOT NULL,
                          type_heritage BIGINT NOT NULL,
                          CONSTRAINT FK_heritage_location FOREIGN KEY (location) REFERENCES location_hospital (id),
                          CONSTRAINT FK_heritage_type FOREIGN KEY (type_heritage) REFERENCES heritage_type (id)
);

CREATE TABLE occurrence (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            protocol VARCHAR(255) UNIQUE,
                            description TEXT NOT NULL,
                            sector VARCHAR(30) NOT NULL,
                            status ENUM('CANCELADO', 'ESPERANDO_PECA', 'FINALIZADO', 'PENDENTE') NOT NULL,
                            date DATETIME(6),
                            applicant_id BIGINT NOT NULL,
                            heritage_id BIGINT NOT NULL,
                            order_service_id BIGINT,
                            CONSTRAINT FK_occ_applicant FOREIGN KEY (applicant_id) REFERENCES employee (id),
                            CONSTRAINT FK_occ_heritage FOREIGN KEY (heritage_id) REFERENCES heritage (id),
                            CONSTRAINT FK_occ_order_service FOREIGN KEY (order_service_id) REFERENCES order_service (id)
);