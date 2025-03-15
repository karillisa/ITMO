--Создание таблиц
CREATE TABLE Characteristics (
characteristic_id SERIAL PRIMARY KEY,
size INT,
appearance VARCHAR(100),
method_of_transportation VARCHAR(100),
life_expectancy INT
);

CREATE TABLE HabitatRegion (
    region_id SERIAL PRIMARY KEY,
    name CHAR(50),
    geographical_coordinates CHAR(50) ,
    description TEXT,
    climate_type VARCHAR(50)
);

CREATE TABLE LizardSpecies (
    species_id SERIAL PRIMARY KEY,
    name CHAR(50) NOT NULL,
    description TEXT NOT NULL,
    coloration VARCHAR(100)
);

-- Создание связей
CREATE TABLE LizardSpecies_HabitatRegion (
    species_id INT REFERENCES LizardSpecies(species_id),
    region_id INT REFERENCES HabitatRegion(region_id),
    PRIMARY KEY (species_id, region_id)
);

CREATE TABLE Researcher (
    researcher_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    scientific_degree VARCHAR(50),
    specialization VARCHAR(100)
);

CREATE TABLE ResearchEquipment (
    equipment_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    equipment_type VARCHAR(50),
    year_of_manufacture INT NOT NULL,
    condition VARCHAR(50),
    researcher_id INT REFERENCES Researcher(researcher_id)
);

CREATE TABLE Observation (
    observation_id SERIAL PRIMARY KEY,
    datetime TIMESTAMP,
    location VARCHAR(255) CONSTRAINT location_check CHECK (length(location) >= 5 AND SUBSTRING(location FROM 1 FOR 1) = upper(SUBSTRING(location FROM 1 FOR 1))),
    observation_details TEXT,
    species_id INT REFERENCES LizardSpecies(species_id),
    researcher_id INT REFERENCES Researcher(researcher_id),
    equipment_id INT REFERENCES ResearchEquipment(equipment_id)
);

-- Добавление many-to-many связи
CREATE TABLE Observation_LizardSpecies (
    observation_id INT REFERENCES Observation(observation_id),
    species_id INT REFERENCES LizardSpecies(species_id),
    PRIMARY KEY (observation_id, species_id)
);

-- Добавление остальных внешних ключей
ALTER TABLE Observation ADD CONSTRAINT fk_observation_researcher
    FOREIGN KEY (researcher_id) REFERENCES Researcher(researcher_id);


ALTER TABLE Observation ADD CONSTRAINT fk_observation_equipment
    FOREIGN KEY (equipment_id) REFERENCES ResearchEquipment(equipment_id);

-- Вставка тестовых данных в таблицу LizardSpecies
INSERT INTO LizardSpecies (name, description, coloration)
VALUES
    ('Green Anole', 'Small green lizard', 'Green'),
    ('Basilisk Lizard', 'Known for running on water', 'Brown'),
    ('Iguana', 'Large herbivorous lizard', 'Various colors'),
    ('Chameleon', 'Can change color', 'Varies');

-- Вставка тестовых данных в таблицу HabitatRegion
INSERT INTO HabitatRegion (name, geographical_coordinates, description, climate_type)
VALUES
    ('Amazon Rainforest', 'Latitude: -3.4653, Longitude: -62.2159', 'Lush green rainforest', 'Tropical'),
    ('Andes Mountains', 'Latitude: -13.1631, Longitude: -72.5450', 'High-altitude mountain range', 'Mountainous'),
    ('Patagonia', 'Latitude: -40.4637, Longitude: -65.2484', 'Vast, sparsely populated region', 'Cold'),
    ('Latin America', 'Latitude: 19.4326, Longitude: -99.1332', 'Located in the Western Hemisphere', 'Tropical');

-- Вставка тестовых данных в таблицу Researcher
INSERT INTO Researcher (first_name, last_name, scientific_degree, specialization)
VALUES
    ('John', 'Smith', 'Ph.D.', 'Herpetology'),
    ('Alice', 'Johnson', 'M.Sc.', 'Ecology'),
    ('Robert', 'Jones', 'B.Sc.', 'Wildlife Biology');

-- Вставка тестовых данных в таблицу ResearchEquipment
INSERT INTO ResearchEquipment (name, equipment_type, year_of_manufacture, condition)
VALUES
    ('Binoculars', 'Optical', 2021, 'Good'),
    ('Camera Traps', 'Photographic', 2020, 'Excellent'),
    ('GPS Device', 'Navigation', 2019, 'Fair');

-- Вставка тестовых данных в таблицу Characteristics
INSERT INTO Characteristics (size, appearance, method_of_transportation, life_expectancy)
VALUES
    (20, 'Small size, slender body', 'In an upright position', 8),
    (60, 'Crest on the head, long tail', 'In an upright position' , 10),
    (150, 'Dewlap, spines on back', 'In an upright position', 15),
    (60, 'Prehensile tail, independently moving eyes', 'In an upright position', 10);

-- Вставка тестовых данных в таблицу LizardSpecies_HabitatRegion (many-to-many связь)
INSERT INTO LizardSpecies_HabitatRegion (species_id, region_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 1),
    (4, 3);

-- Вставка тестовых данных в таблицу Observation
INSERT INTO Observation (datetime, location, observation_details, species_id, researcher_id, equipment_id)
VALUES
    ('2022-01-15 10:30:00', 'Amazon Rainforest', 'Sighted a Green Anole', 1, 1, 1),
    ('2022-02-02 15:45:00', 'Andes Mountains', 'Observed a Basilisk Lizard running on water', 2, 2, 2),
    ('2022-03-20 08:00:00', 'Patagonia', 'Spotted an Iguana feeding', 3, 3, 3),
    ('2022-04-10 12:20:00', 'Amazon Rainforest', 'Chameleon changing color', 4, 1, 1);

