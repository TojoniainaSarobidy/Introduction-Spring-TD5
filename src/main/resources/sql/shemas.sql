CREATE TYPE ingredient_type AS enum ('VEGETABLE', 'ANIMAL', 'MARINE', 'DAIRY', 'OTHER');

CREATE TYPE stockMovementType AS enum ('IN', 'OUT');

CREATE TYPE stockMovementUnit AS enum ('PCL', 'KG', 'L');

CREATE TABLE ingredient
(
    id        SERIAL PRIMARY KEY,
    nom       VARCHAR         NOT NULL,
    categorie ingredient_type NOT NULL,
    prix      NUMERIC(10, 2)
);

CREATE TABLE stockMovement
(
    id               SERIAL PRIMARY KEY,
    id_ingredient    INT,
    quantite         NUMERIC(10, 2)    NOT NULL,
    type             stockMovementType NOT NULL,
    unit             stockMovementUnit NOT NULL,
    creationDateTime TIMESTAMP,
    CONSTRAINT fk_ingredient FOREIGN KEY (id_ingredient) REFERENCES ingredient (id)
);

CREATE TABLE dish
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(100),
    price DOUBLE PRECISION
);

CREATE TABLE dish_ingredient
(
    dish_id       INT,
    ingredient_id INT,
    CONSTRAINT fk_dish FOREIGN KEY (dish_id) REFERENCES dish (id),
    CONSTRAINT fk_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredient (id)
);