BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Event" (
	"event_id"	TEXT NOT NULL UNIQUE,
	"associated_username"	TEXT NOT NULL,
	"person_id"	TEXT NOT NULL,
	"country"	TEXT NOT NULL,
	"city"	TEXT NOT NULL,
	"event_type"	TEXT NOT NULL,
	"latitude"	REAL NOT NULL,
	"longitude"	REAL NOT NULL,
	"year"	INTEGER NOT NULL,
	PRIMARY KEY("event_id"),
	FOREIGN KEY("person_id") REFERENCES "Person"("person_id"),
	FOREIGN KEY("associated_username") REFERENCES "Person"("associated_user_name")
);
CREATE TABLE IF NOT EXISTS "User" (
	"person_id"	TEXT NOT NULL UNIQUE,
	"username"	TEXT NOT NULL UNIQUE,
	"password"	TEXT NOT NULL,
	"email"	TEXT NOT NULL,
	"first_name"	TEXT NOT NULL,
	"last_name"	TEXT NOT NULL,
	"gender"	TEXT NOT NULL,
	PRIMARY KEY("person_id"),
	FOREIGN KEY("username") REFERENCES "Person"("associated_user_name"),
	FOREIGN KEY("person_id") REFERENCES "Person"("person_id")
);
CREATE TABLE IF NOT EXISTS "AuthToken" (
	"associated_username"	TEXT NOT NULL,
	"auth_token"	TEXT NOT NULL UNIQUE,
	FOREIGN KEY("associated_username") REFERENCES "User"("username")
);
CREATE TABLE IF NOT EXISTS "Person" (
	"person_id"	TEXT NOT NULL UNIQUE,
	"associated_user_name"	TEXT NOT NULL UNIQUE,
	"first_name"	TEXT NOT NULL,
	"last_name"	TEXT NOT NULL,
	"gender"	TEXT NOT NULL,
	"father_id"	TEXT,
	"mother_id"	TEXT,
	"spouse_id"	TEXT,
	PRIMARY KEY("person_id"),
	FOREIGN KEY("associated_user_name") REFERENCES "User"("username")
);
COMMIT;
