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

INSERT INTO lists (list_name, user_id) VALUES ('Colorful birds of North America', 1);

INSERT INTO birds (bird_name, bird_img, num_sightings, zipcode, list_id) VALUES
('Northern Cardinal', 'https://res.cloudinary.com/dgupilxum/image/upload/v1646096224/sh4rblvlmm241jflxq0x.jpg', 1, 60625, 1),
('Cooper''s Hawk', 'https://res.cloudinary.com/dgupilxum/image/upload/v1646096345/k3akp5rdfwyieuagkrgl.jpg', 1, 60625, 1),
('Blue Jay', 'https://res.cloudinary.com/dgupilxum/image/upload/v1646146600/qd2zt3t2qc0bml0apwdw.jpg', 1, 60625, 1),
('Goldfinch', 'https://res.cloudinary.com/dgupilxum/image/upload/v1646155661/izfqldcwtzniyipugqe4.jpg', 1, 60625, 1);

INSERT INTO bird_notes (bird_id, notes, date_spotted, num_males, num_females, feeder_type, food_blend) VALUES
(1, 'insert notes here', '2022-02-07', 5, 5, null, null),
(2, 'insert notes here', '2022-02-02', 1, 1, null, null);

COMMIT TRANSACTION;
