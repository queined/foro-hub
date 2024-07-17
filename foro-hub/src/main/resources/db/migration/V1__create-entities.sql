CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE course (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE topic (
    id SERIAL PRIMARY KEY,
    subject VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status BOOLEAN NOT NULL DEFAULT TRUE,
    user_id INT NOT NULL,
    course_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
);

CREATE TABLE comment (
    id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status BOOLEAN NOT NULL DEFAULT TRUE,
    user_id INT NOT NULL,
    topic_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (topic_id) REFERENCES topic(id)
);