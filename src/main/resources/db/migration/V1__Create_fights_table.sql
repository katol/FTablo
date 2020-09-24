CREATE TABLE fights (
  id serial PRIMARY KEY,
  seconds_passed INTEGER,
  last_exchange_number INTEGER,
  last_ts TIMESTAMP WITH TIME ZONE,
  last_description TEXT,
  red_name TEXT NOT NULL,
  red_scores INTEGER,
  red_penalties INTEGER,
  red_video_replays INTEGER,
  blue_name TEXT NOT NULL,
  blue_scores INTEGER,
  blue_penalties INTEGER,
  blue_video_replays INTEGER
);