CREATE TABLE exchanges (
  id serial PRIMARY KEY,
  fight_id INTEGER NOT NULL REFERENCES fights (id),
  seconds_passed INTEGER NOT NULL,
  save_ts TIMESTAMP WITH TIME ZONE NOT NULL,
  action_description TEXT NOT NULL,
  scores_to_red INTEGER NOT NULL,
  penalties_to_red INTEGER NOT NULL,
  scores_to_blue INTEGER NOT NULL,
  penalties_to_blue INTEGER NOT NULL
);