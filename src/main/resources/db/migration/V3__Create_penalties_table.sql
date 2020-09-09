CREATE TABLE penalties (
  id serial PRIMARY KEY,
  fight_id INTEGER NOT NULL REFERENCES fights (id),
  seconds_passed INTEGER NOT NULL,
  ts TIMESTAMP WITH TIME ZONE NOT NULL,
  foul_description TEXT NOT NULL,
  rule_breaker_color TEXT NOT NULL
);