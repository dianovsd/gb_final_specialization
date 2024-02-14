-- 7. В подключенном MySQL репозитории создать базу данных “Друзья
--    человека”

DROP DATABASE IF EXISTS `humans_friends`;

CREATE DATABASE IF NOT EXISTS humans_friends;

USE humans_friends;

-- 8. Создать таблицы с иерархией из диаграммы в БД

CREATE TABLE animals
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	animal_type VARCHAR(30)
);

INSERT INTO animals (animal_type)
VALUES ('Домашние животные'), ('Вьючные животные');

CREATE TABLE pets
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	animal_class VARCHAR(30),
	animal_type_id INT DEFAULT 1,
	FOREIGN KEY (animal_type_id) REFERENCES animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO pets (animal_class)
VALUES ('Собаки'), ('Кошки'), ('Хомяки');

CREATE TABLE pack_animals
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	animal_class VARCHAR(30),
	animal_type_id INT DEFAULT 2,
	FOREIGN KEY (animal_type_id) REFERENCES animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO pack_animals (animal_class)
VALUES ('Лошади'), ('Верблюды'), ('Ослы');

-- 9. Заполнить низкоуровневые таблицы именами(животных), командами
--    которые они выполняют и датами рождения.

CREATE TABLE dogs 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20), 
    commands VARCHAR(1000),
    birthday DATE,
    animal_class_id INT DEFAULT 1,
    Foreign KEY (animal_class_id) REFERENCES pets (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO dogs (name, commands, birthday)
VALUES ('Бублик', 'лежать, сидеть', '2019-09-02'),
('Барс', 'лежать', '1990-04-07'),
('Контрабас', 'служить, петь', '2016-02-23');

CREATE TABLE cats 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20), 
    commands VARCHAR(1000),
    birthday DATE,
    animal_class_id INT DEFAULT 2,
    Foreign KEY (animal_class_id) REFERENCES pets (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO cats (name, commands, birthday)
VALUES ('Барсик', 'Доминирнуй и унижай', '2010-08-14'),
('Чуча', 'ничего не делай', '2015-09-10'),
('Мурзилка', 'ешь и спи', '2018-09-02');

CREATE TABLE hamsters 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(30), 
    commands VARCHAR(100),
    birthday DATE,
    animal_class_id INT DEFAULT 3,
    Foreign KEY (animal_class_id) REFERENCES pets (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO hamsters (name, commands, birthday)
VALUES ('Мелкий 1', 'какать', '2020-01-01'),
('Мелкий 2', 'бегать', '2021-02-02'),
('Самый мелкий', 'прыгать', '2022-03-03');

CREATE TABLE horses 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(30), 
    commands VARCHAR(100),
    birthday DATE,
    animal_class_id INT DEFAULT 1,
    Foreign KEY (animal_class_id) REFERENCES pack_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO horses (name, commands, birthday)
VALUES ('Пончик', 'скакать, сидеть', '2015-05-15'),
('Незнайка', 'лежать', '2015-05-16'),
('Знайка', 'служить, скакать', '2015-05-17');

CREATE TABLE camels 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(30), 
    commands VARCHAR(100),
    birthday DATE,
    animal_class_id INT DEFAULT 2,
    Foreign KEY (animal_class_id) REFERENCES pack_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO camels (name, commands, birthday)
VALUES ('горбун', 'плевать', '2015-05-15'),
('красавчик', 'дышать, петь', '2015-05-01'),
('двугорбый', 'сидеть, идти', '2015-05-02');

CREATE TABLE donkeys 
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(30), 
    commands VARCHAR(100),
    birthday DATE,
    animal_class_id INT DEFAULT 3,
    Foreign KEY (animal_class_id) REFERENCES pack_animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO donkeys (name, commands, birthday)
VALUES ('Умник', 'идти, стоять', '2005-07-19'),
('Ленивец', 'лежать', '2020-11-14'),
('Марта', 'нести', '2012-09-13');

-- 10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
--     питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.

-- Удалить таблицу "camels"
  DROP TABLE `humans_friends`.`camels`;
  
-- Объединить таблицы "horses", и "donkeys" в одну таблицу
-- Создаем новую таблицу "horses_and_donkeys" для объединения "horses" и "donkeys"

CREATE TABLE `horses_and_donkeys` (
   id INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `name` VARCHAR(50) NOT NULL,
  `commands` VARCHAR(100) NOT NULL,
  `birthday` DATE NOT NULL,
  `animal_class_id` INT UNSIGNED NOT NULL,
  `species` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`)
);



-- Вставляем данные из "horses" в таблицу "horses_and_donkeys"
INSERT INTO `horses_and_donkeys` (`name`,`commands` ,`birthday`, `animal_class_id`, `species`)
SELECT `name`, `commands`, `birthday`, `animal_class_id`, 'Horse' AS `species`
FROM `horses`;

-- Вставляем данные из "donkeys" в таблицу "horses_and_donkeys"
INSERT INTO `horses_and_donkeys` (`name`,`commands` ,`birthday`, `animal_class_id`, `species`)
SELECT `name`, `commands`, `birthday`, `animal_class_id`, 'Donkey' AS `species`
FROM `donkeys`;

-- 11. Создать новую таблицу “молодые животные” в которую попадут все
--     животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
--     до месяца подсчитать возраст животных в новой таблице
DROP TEMPORARY TABLE if exists all_animals;

CREATE TEMPORARY TABLE all_animals
SELECT * FROM dogs
UNION SELECT * FROM cats
UNION SELECT * FROM hamsters
UNION SELECT * FROM horses
UNION SELECT * FROM donkeys;

CREATE TABLE young_animals
SELECT name, commands, birthday, animal_class_id, TIMESTAMPDIFF(MONTH, birthday, CURDATE()) AS age_in_month
FROM all_animals
WHERE birthday BETWEEN ADDDATE(CURDATE(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);

-- 12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
--     прошлую принадлежность к старым таблицам.

SELECT dogs.name, dogs.commands, dogs.birthday, pets.animal_class, animals.animal_type
FROM dogs
LEFT JOIN pets ON pets.id = dogs.animal_class_id
LEFT JOIN animals ON animals.id=pets.animal_type_id
UNION
SELECT cats.name, cats.commands, cats.birthday, pets.animal_class, animals.animal_type
FROM cats
LEFT JOIN pets ON pets.id = cats.animal_class_id
LEFT JOIN animals ON animals.id=pets.animal_type_id
UNION
SELECT hamsters.name, hamsters.commands, hamsters.birthday, pets.animal_class, animals.animal_type
FROM hamsters
LEFT JOIN pets ON pets.id = hamsters.animal_class_id
LEFT JOIN animals ON animals.id=pets.animal_type_id
UNION
SELECT horses.name, horses.commands, horses.birthday, pack_animals.animal_class, animals.animal_type
FROM horses
LEFT JOIN pack_animals ON pack_animals.id = horses.animal_class_id
LEFT JOIN animals ON animals.id=pack_animals.animal_type_id
UNION
SELECT donkeys.name, donkeys.commands, donkeys.birthday, pack_animals.animal_class, animals.animal_type
FROM donkeys
LEFT JOIN pack_animals ON pack_animals.id = donkeys.animal_class_id
LEFT JOIN animals ON animals.id=pack_animals.animal_type_id;