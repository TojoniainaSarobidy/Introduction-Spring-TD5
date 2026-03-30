INSERT INTO ingredient(nom, categorie, prix)
VALUES ('Laitue', 'VEGETABLE', 800.00),
       ('Tomate', 'VEGETABLE', 600.00),
       ('Poulet', 'ANIMAL', 4500.00),
       ('Chocolat', 'OTHER', 3000.00),
       ('Beurre', 'DAIRY', 2500.00);

INSERT INTO stockmovement(id_ingredient, quantite, type, unit, creationdatetime)
VALUES (1, 5.00, 'IN', 'KG', 2024 - 01 - 05 08:00:00.000000),
       (1, 0.20, 'OUT', 'KG', 2024 - 01 - 06 12:00:00.000000),
       (2, 4.00, 'IN', 'KG', 2024 - 01 - 05 08:00:00.000000),
       (2, 0.15, 'OUT', 'KG', 2024 - 01 - 06 12:00:00.000000),
       (3, 10.00, 'IN', 'KG', 2024 - 01 - 04 09:00:00.000000),
       (3, 1.00, 'OUT', 'KG', 2024 - 01 - 06 13:00:00.000000),
       (4, 3.00, 'IN', 'KG', 2024 - 01 - 05 10:00:00.000000),
       (4, 0.30, 'OUT', 'KG', 2024 - 01 - 06 14:00:00.000000),
       (5, 2.50, 'IN', 'KG', 2024 - 01 - 05 10:00:00.000000),
       (5, 0.20, 'OUT', 'KG', 2024 - 01 - 06 14:00:00.000000);

INSERT INTO ingredient (id, name, categorie, price)
VALUES (1, 'Laitue', 'VEGETABLE', 800.00),
       (2, 'Tomate', 'VEGETABLE', 600.00),
       (3, 'Poulet', 'ANIMAL', 4500.00),
       (4, 'Chocolat', 'OTHER', 3000.00),
       (5, 'Beurre', 'DAIRY', 2500.00);

INSERT INTO dish (id, name, price)
VALUES (1, 'Salade Poulet', 8000.00),
       (2, 'Dessert Chocolat', 6000.00),
       (3, 'Sandwich', 7000.00);

INSERT INTO dish_ingredient (dish_id, ingredient_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (2, 5);