INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO subject_categories(name) VALUES('Przedmioty ślisłe');
INSERT INTO subject_categories(name) VALUES('Języki obce');
INSERT INTO subject_categories(name) VALUES('Przedmioty humanistyczne');
INSERT INTO subject_categories(name) VALUES('Sztuka');

INSERT INTO subjects(name, fk_category) VALUES('Matematyka', 1);
INSERT INTO subjects(name, fk_category) VALUES('Fizyka', 1);
INSERT INTO subjects(name, fk_category) VALUES('Informatyka', 1);
INSERT INTO subjects(name, fk_category) VALUES('Chemia', 1);
INSERT INTO subjects(name, fk_category) VALUES('Biologia', 1);


INSERT INTO subjects(name, fk_category) VALUES('Język angielski', 2);
INSERT INTO subjects(name, fk_category) VALUES('Język niemiecki', 2);
INSERT INTO subjects(name, fk_category) VALUES('Język francuski', 2);
INSERT INTO subjects(name, fk_category) VALUES('Język hiszpański', 2);
INSERT INTO subjects(name, fk_category) VALUES('Język rosyjski', 2);

INSERT INTO subjects(name, fk_category) VALUES('Język polski', 3);
INSERT INTO subjects(name, fk_category) VALUES('Historia', 3);
INSERT INTO subjects(name, fk_category) VALUES('WOS', 3);
INSERT INTO subjects(name, fk_category) VALUES('Filozofia', 3);

INSERT INTO subjects(name, fk_category) VALUES('Muzyka', 4);
INSERT INTO subjects(name, fk_category) VALUES('Rysunek', 4);
INSERT INTO subjects(name, fk_category) VALUES('Malarstwo', 4);
INSERT INTO subjects(name, fk_category) VALUES('Historia sztuki', 4);
INSERT INTO subjects(name, fk_category) VALUES('Grafika komputerowa', 4);






