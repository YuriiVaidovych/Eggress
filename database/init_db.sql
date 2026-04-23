CREATE TABLE IF NOT EXISTS users (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    login TEXT NOT NULL UNIQUE,
    email TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    theme_preference INTEGER NOT NULL DEFAULT 0,
    language TEXT NOT NULL DEFAULT 'uk',
    avatar_uri TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS habits (
    habit_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    title TEXT NOT NULL,
    description TEXT,
    period_n INTEGER NOT NULL,
    current_points INTEGER NOT NULL DEFAULT 0,
    egg_color_hex TEXT NOT NULL,
    last_check_in TIMESTAMP,
    hatched_at TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    CHECK (period_n IN (1, 7, 30)),
    CHECK (current_points BETWEEN 0 AND 60)
);

CREATE TABLE IF NOT EXISTS habit_logs (
    log_id INTEGER PRIMARY KEY AUTOINCREMENT,
    habit_id INTEGER NOT NULL,
    points_added INTEGER NOT NULL,
    log_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (habit_id) REFERENCES habits(habit_id) ON DELETE CASCADE
);