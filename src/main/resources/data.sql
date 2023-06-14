DROP SCHEMA public CASCADE;
CREATE SCHEMA public;



create table provider
(
    id            serial Primary key,
    provider_name varchar(100) NOT NULL,
    address       varchar(255) NOT NULL
);
create table category
(
    id            serial Primary key,
    category_name varchar(50)
);
create table workplace
(
    id           serial Primary key,
    address      varchar(255) NOT NULL,
    head_manager varchar(255) NOT NULL
);
create table equipment
(
    id           serial Primary key,
    name         varchar(100)                                                      NOT NULL,
    model        varchar(100)                                                      NOT NULL,
    description  varchar(100)                                                      NOT NULL,
    condition    varchar(20)
        CONSTRAINT chk_cond CHECK (condition IN ('Bad', 'Satisfactorily', 'Good')) NOT NULL,
    category_id  int,
    Foreign Key (category_id) references category (id) ON DELETE CASCADE,
    workplace_id int,
    Foreign Key (workplace_id) references workplace (id) ON DELETE CASCADE
);
create table purchase
(
    id            serial Primary key,
    equipment_id  int       NOT NULL,
    Foreign Key (equipment_id) references equipment (id) ON DELETE CASCADE,
    price         numeric   NOT NULL,
    purchase_date timestamp NOT NULL,
    provider_id   int       NOT NULL,
    Foreign Key (provider_id) references provider (id) ON DELETE CASCADE
);
create table repair
(
    id                 serial Primary key,
    equipment_id       int          NOT NULL,
    Foreign Key (equipment_id) references equipment (id) ON DELETE CASCADE,
    repair_date_start  timestamp    NOT NULL,
    repair_date_finish timestamp    NOT NULL,
    repair_cause       varchar(255) NOT NULL,
    repair_price       numeric      NOT NULL
);
create table revaluation
(
    id           serial Primary key,
    equipment_id int          NOT NULL,
    Foreign Key (equipment_id) references equipment (id) ON DELETE CASCADE,
    reval_cause  varchar(255) NOT NULL,
    base_price   numeric      NOT NULL,
    new_price    numeric      NOT NULL,
    date_changed timestamp    NOT NULL
);
create table equip_workplace_movement
(
    id            serial Primary key,
    equipment_id  int       NOT NULL,
    Foreign Key (equipment_id) references equipment (id) ON DELETE CASCADE,
    workplace_id  int       NOT NULL,
    Foreign Key (workplace_id) references workplace (id) ON DELETE CASCADE,
    arriving_date timestamp NOT NULL,
    day_moved     timestamp
);
create table employee
(
    id              serial Primary key,
    first_name      varchar(50) NOT NULL,
    last_name       varchar(50) NOT NULL,
    position        varchar(50) NOT NULL,
    department_name varchar(50) NOT NULL
);
create table employee_equipment
(
    id           serial Primary key,
    employee_id  int NOT NULL,
    Foreign Key (employee_id) references employee (id) ON DELETE CASCADE,
    equipment_id int NOT NULL,
    Foreign Key (equipment_id) references equipment (id) ON DELETE CASCADE
);

create table users
(
    id            serial Primary key,
    user_name     varchar(50)                                                not null,
    user_password varchar(50)                                                not null,
    user_role     varchar(20)
        CONSTRAINT chk_role CHECK (user_role IN ('ADMIN', 'USER1', 'USER2')) NOT NULL
);

CREATE TABLE user_actions
(
    id        SERIAL PRIMARY KEY,
    user_id   BIGINT,
    action    VARCHAR(255),
    timestamp TIMESTAMP
);

-- ?????????? ??????? "provider"
INSERT INTO provider (provider_name, address)
VALUES ('ABC Suppliers', '123 Main Street'),
       ('XYZ Equipment', '456 Park Avenue'),
       ('Acme Corporation', '789 Elm Road');

-- ?????????? ??????? "category"
INSERT INTO category (category_name)
VALUES ('Computers'),
       ('Machinery'),
       ('Tools');

-- ?????????? ??????? "workplace"
INSERT INTO workplace (address, head_manager)
VALUES ('10 Industrial Avenue', 'John Smith'),
       ('20 Commercial Street', 'Jane Doe'),
       ('30 Business Boulevard', 'Michael Johnson');

-- ?????????? ??????? "equipment"
INSERT INTO equipment (name, model, description, condition, category_id, workplace_id)
VALUES ('Laptop', 'HP123', 'Office laptop', 'Satisfactorily', 1, 1),
       ('Excavator', 'CAT456', 'Heavy-duty machine', 'Good', 2, 2),
       ('Drill', 'Dewalt78', 'Power drill', 'Satisfactorily', 3, 3),
       ('Laptop', 'Dell Inspiron 15', 'Powerfull laptop for gaming', 'Good', 1, 1),
       ('Projector', 'Epson PowerLite 2040', 'Quality projector for presentatons', 'Satisfactorily', 2, 2),
       ('Screwdriver', 'Bosch GSR 12V-15', 'Screwdriver on rechargable battaries ', 'Good', 3, 3);

-- ?????????? ??????? "purchase"
INSERT INTO purchase (equipment_id, price, purchase_date, provider_id)
VALUES (1, 1100.00, '2022-01-15 09:30:00', 1),
       (2, 48000.00, '2022-03-20 14:45:00', 2),
       (3, 80.00, '2022-05-10 11:20:00', 3),
       (6, 1500.00, '2022-04-10', 1),
       (4, 2500.00, '2022-02-18', 2),
       (5, 500.00, '2022-03-21', 3);
-- ?????????? ??????? "repair"
INSERT INTO repair (equipment_id, repair_date_start, repair_date_finish, repair_cause, repair_price)
VALUES (1, '2022-02-05 08:00:00', '2022-02-07 16:30:00', 'Hardware failure', 250.00),
       (2, '2022-04-10 10:15:00', '2022-04-15 14:20:00', 'Engine malfunction', 1500.00),
       (3, '2022-06-20 13:45:00', '2022-06-22 11:30:00', 'Broken power cord', 50.00),
       (4, '2022-03-12 08:00:00', '2022-02-07 16:30:00', 'Unknown', 100.00),
       (5, '2022-04-17 10:15:00', '2022-04-15 14:20:00', 'Lense broken', 350.00),
       (6, '2022-06-02 13:45:00', '2022-06-22 11:30:00', 'Broken accumulator', 50.00);

-- ?????????? ??????? "revaluation"
INSERT INTO revaluation (equipment_id, reval_cause, base_price, new_price, date_changed)
VALUES (1, 'Market price change', 1000.00, 1100.00, '2022-03-01 09:00:00'),
       (2, 'Improved features', 45000.00, 48000.00, '2022-05-15 14:30:00'),
       (3, 'Wear and tear', 100.00, 80.00, '2022-07-05 11:45:00'),
       (4, 'Inflation', 2000.00, 2500.00, '2022-02-01 09:00:00'),
       (5, 'Improved features', 400.00, 500.00, '2022-02-15 14:30:00'),
       (6, 'Wear and tear', 2000.00, 1500.00, '2022-03-05 11:45:00');

-- ?????????? ??????? "employee"
INSERT INTO employee (first_name, last_name, position, department_name)
VALUES ('John', 'Doe', 'Manager', 'Operations'),
       ('Jane', 'Smith', 'Engineer', 'Engineering'),
       ('Michael', 'Johnson', 'Technician', 'Maintenance');
-- ?????????? ??????? "equip_workplace_movement"
INSERT INTO equip_workplace_movement (equipment_id, workplace_id, arriving_date, day_moved)
VALUES (1, 1, '2022-02-10 10:00:00', '2022-02-10'),
       (2, 2, '2022-04-15 14:00:00', '2022-04-15'),
       (3, 3, '2022-06-25 09:30:00', '2022-06-25'),
       (4, 1, '2022-02-01', null),
       (5, 2, '2022-04-21', null),
       (6, 3, '2022-06-11', null);;

-- ?????????? ??????? "employee_equipment"
INSERT INTO employee_equipment (employee_id, equipment_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (1, 4),
       (2, 5),
       (3, 6);

INSERT INTO users (user_name, user_password, user_role)
VALUES ('ADMINPROF', 12345, 'ADMIN');

