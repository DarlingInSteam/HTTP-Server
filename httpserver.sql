CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL
);

CREATE TABLE "password_reset_tokens" (
    "id" BIGSERIAL PRIMARY KEY,
    "user_id" BIGINT,
    "token"  varchar(100),
    "expiry_date" TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE "refresh_tokens" (
    "id" BIGSERIAL PRIMARY KEY,
    "user_id" BIGINT,
    "token" varchar UNIQUE,
    "expiry_date" TIMESTAMP WITHOUT TIME ZONE
);

ALTER TABLE "refresh_tokens" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");
ALTER TABLE "password_reset_tokens" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");