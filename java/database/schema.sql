BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS profiles;
DROP TABLE IF EXISTS lists;
DROP TABLE IF EXISTS birds;
DROP TABLE IF EXISTS bird_notes;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE profiles (
    profile_id serial,
    user_id int NOT NULL,
    profile_img varchar(500),
    zip_code int,
    favorite_bird varchar(100),
    skill_level varchar(50),
    most_common_bird varchar(200),

    CONSTRAINT PK_profile PRIMARY KEY (profile_id),
    CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE lists (
    list_id serial,
    list_name varchar(250) NOT NULL,
    user_id int NOT NULL,

    CONSTRAINT PK_list_id PRIMARY KEY (list_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE birds (
    bird_id serial,
    bird_name varchar(200) NOT NULL,
    bird_img varchar(500) NOT NULL,
    num_sightings int NOT NULL,
    zipcode int,
    list_id int NOT NULL,

    CONSTRAINT PK_bird_id PRIMARY KEY (bird_id),
    CONSTRAINT FK_list_id FOREIGN KEY (list_id) REFERENCES lists(list_id)
);

CREATE TABLE bird_notes (
    note_id serial,
    bird_id int NOT NULL,
    notes varchar(5000),
    date_spotted date NOT NULL,
    num_males int,
    num_females int,
    feeder_type varchar(150),
    food_blend varchar(400),

    CONSTRAINT PK_note_id PRIMARY KEY (note_id),
    CONSTRAINT FK_bird_id FOREIGN KEY (bird_id) REFERENCES birds(bird_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO profiles (user_id, profile_img, zip_code, favorite_bird, skill_level, most_common_bird)
VALUES (1, 'profile_img', 60625, 'Black-capped Chickadee', 'intermediate', 'Black-capped Chickadee');

INSERT INTO lists (list_name, user_id) VALUES ('Home', 1), ('Tennessee', 1), ('Washington', 1), ('North Carolina', 1);

INSERT INTO birds (bird_name, bird_img, num_sightings, zipcode, list_id) VALUES
('Canada Goose', 'https://macaulaylibrary.org/asset/49344561', 1, 60625, 1),
('Red-bellied Woodpecker', 'https://macaulaylibrary.org/asset/51168831', 1, 60625, 1),
('Northern Cardinal', 'https://macaulaylibrary.org/asset/257386271', 1, 60625, 1),
('Black-capped Chickadee', 'https://macaulaylibrary.org/asset/47337851', 1, 60625, 1),
('House Finch', 'https://macaulaylibrary.org/asset/283643061', 1, 60625, 1),
('Bufflehead', 'https://macaulaylibrary.org/asset/39633601', 1, 60625, 1),
('Great Horned Owl', 'https://macaulaylibrary.org/asset/63942211', 1, 37214, 2),
('Mallard', 'https://macaulaylibrary.org/asset/63736171', 1, 37214, 2),
('Belted Kingfisher', 'https://macaulaylibrary.org/asset/134468811', 1, 37214, 2),
('American Robin', 'https://macaulaylibrary.org/asset/222821411', 1, 37214, 2),
('Northern Flicker', 'https://macaulaylibrary.org/asset/29705611', 1, 37214, 2),
('Ruby-crowned Kinglet', 'https://macaulaylibrary.org/asset/53418081', 1, 37214, 2),
('Red-breasted Nuthatch', 'https://macaulaylibrary.org/asset/206532801', 1, 98028, 3),
('Pacific Wren', 'https://macaulaylibrary.org/asset/87721071', 1, 98028, 3),
('Peregrine Falcon', 'https://macaulaylibrary.org/asset/288347741', 1, 98028, 3),
('Anna''s Hummingbird', 'https://macaulaylibrary.org/asset/45345081', 1, 98028, 3),
('Yellow-rumped Warbler', 'https://macaulaylibrary.org/asset/168328261', 1, 98028, 3),
('Steller''s Jay', 'https://macaulaylibrary.org/asset/60478861', 1, 98028, 3),
('Eastern Screech Owl', 'https://macaulaylibrary.org/asset/261958361', 1, 28704, 4),
('Carolina Wren', 'https://macaulaylibrary.org/asset/210392161', 1, 28704, 4),
('Pileated Woodpecker', 'https://macaulaylibrary.org/asset/213294221', 1, 28704, 4),
('Blue Jay', 'https://macaulaylibrary.org/asset/84878861', 1, 28704, 4),
('Tufted Titmouse', 'https://macaulaylibrary.org/asset/84467431', 1, 28704, 4),
('American Goldfinch', 'https://macaulaylibrary.org/asset/172924761', 1, 28704, 4);

INSERT INTO bird_notes (bird_id, notes, date_spotted, num_males, num_females, feeder_type, food_blend) VALUES 
(1, 'insert notes here', '2022-02-07', 5, 5, null, null),
(2, 'insert notes here', '2022-02-02', 1, 1, 'cylinder', 'live-mealworms'),
(3, 'insert notes here', '2022-01-08', 3, 2, 'suet-feeder', 'suet-cake'),
(4, 'insert notes here', '2022-01-16', 4, 5, 'tray-feeder', 'wildlife-blend'),
(5, 'insert notes here', '2022-02-08', 2, 3, 'seed-tube', 'sunflower-blend'),
(6, 'insert notes here', '2022-02-01', 2, 2, null, null),
(7, 'insert notes here', '2022-02-07', 0, 1, null, null),
(8, 'insert notes here', '2022-02-02', 2, 4, null, null),
(9, 'insert notes here', '2022-01-08', 1, 0, null, null),
(10, 'insert notes here', '2022-01-16', 2, 1, 'hopper', 'live-mealworms'),
(11, 'insert notes here', '2022-02-08', 1, 1, 'cylinder', 'bark-butter'),
(12, 'insert notes here', '2022-02-01', 2, 2, 'seed-tube', 'nutty-blend'),
(13, 'insert notes here', '2022-02-07', 2, 1, 'suet-feeder', 'suet-cake'),
(14, 'insert notes here', '2022-02-02', 1, 1, 'tray-feeder', 'sunflower-blend'),
(15, 'insert notes here', '2022-01-08', 0, 1, null, null),
(16, 'insert notes here', '2022-01-16', 1, 1, 'nectar', null),
(17, 'insert notes here', '2022-02-08', 1, 2, 'hopper', 'nutty-blend'),
(18, 'insert notes here', '2022-02-01', 2, 2, 'peanut-feeder', 'nutty-blend'),
(19, 'insert notes here', '2022-02-07', 1, 1, null, null),
(20, 'insert notes here', '2022-02-02', 2, 3, 'seed-tube', 'wildlife-blend'),
(21, 'insert notes here', '2022-01-08', 1, 0, 'suet-feeder', 'suet-cake'),
(22, 'insert notes here', '2022-01-16', 1, 0, 'hopper', 'nutty-blend'),
(23, 'insert notes here', '2022-02-08', 2, 1, 'seed-tube', 'bark-butter'),
(24, 'insert notes here', '2022-02-01', 3, 2, 'hopper', 'sunflower-blend');

COMMIT TRANSACTION;
