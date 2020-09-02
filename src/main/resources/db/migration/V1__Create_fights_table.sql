CREATE TABLE fights (
  id serial PRIMARY KEY,
  seconds_passed INTEGER NOT NULL,
  last_exchange_number INTEGER NOT NULL,
  last_ts TIMESTAMP WITH TIME ZONE NOT NULL,
  last_description TEXT NOT NULL,
  red_name TEXT NOT NULL,
  red_scores INTEGER NOT NULL,
  red_penalties INTEGER NOT NULL,
  red_video_replays INTEGER NOT NULL,
  blue_name TEXT NOT NULL,
  blue_scores INTEGER NOT NULL,
  blue_penalties INTEGER NOT NULL,
  blue_video_replays INTEGER NOT NULL
);