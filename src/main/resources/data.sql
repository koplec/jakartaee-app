INSERT INTO users (name, password, admin) VALUES
('User1', 'password1', FALSE),
('User2', 'password2', FALSE),
('Admin', 'password3', TRUE);

INSERT INTO todos (title, description, due_date, user_id, completed) VALUES
('Todo1', 'Description for Todo1', '2022-12-01', 1, FALSE),
('Todo2', 'Description for Todo2', '2022-12-02', 1, FALSE),
('Todo3', 'Description for Todo3', '2022-12-03', 1, FALSE),
('Todo4', 'Description for Todo4', '2022-12-04', 2, FALSE),
('Todo5', 'Description for Todo5', '2022-12-05', 2, FALSE),
('Todo6', 'Description for Todo6', '2022-12-06', 2, FALSE),
('Todo7', 'Description for Todo7', '2022-12-07', 3, FALSE),
('Todo8', 'Description for Todo8', '2022-12-08', 3, FALSE),
('Todo9', 'Description for Todo9', '2022-12-09', 3, FALSE),
( 'Todo10', 'Description for Todo10', '2022-12-10', 3, FALSE);