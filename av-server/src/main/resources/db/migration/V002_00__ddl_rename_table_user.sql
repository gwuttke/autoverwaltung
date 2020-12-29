ALTER TABLE benutzer
ADD COLUMN enabled boolean not null default 1;

RENAME TABLE benutzer TO users;