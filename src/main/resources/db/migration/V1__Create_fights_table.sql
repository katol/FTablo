CREATE TABLE fights (
  id serial PRIMARY KEY,
  seconds_passed INTEGER NOT NULL,
  end_ts TIMESTAMP WITH TIME ZONE NOT NULL,
  end_description TEXT NOT NULL,
  red_name TEXT NOT NULL,
  red_scores INTEGER NOT NULL,
  blue_name TEXT NOT NULL,
  blue_scores INTEGER NOT NULL
);