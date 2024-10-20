CREATE TABLE Degrees (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(128) NOT NULL
);

CREATE TABLE Lector (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(128) NOT NULL,
                        degree_id BIGINT,
                        FOREIGN KEY (degree_id) REFERENCES Degrees(id) ON DELETE SET NULL,
                        salary DECIMAL(10, 2)
);

CREATE TABLE Department (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            name VARCHAR(128) NOT NULL,
                            head_lector_id BIGINT,
                            FOREIGN KEY (head_lector_id) REFERENCES Lector(id) ON DELETE SET NULL
);

CREATE TABLE Lector_in_Department (
                                      lector_id BIGINT,
                                      department_id BIGINT,
                                      PRIMARY KEY (lector_id, department_id),
                                      FOREIGN KEY (lector_id) REFERENCES Lector(id) ON DELETE CASCADE,
                                      FOREIGN KEY (department_id) REFERENCES Department(id) ON DELETE CASCADE
);