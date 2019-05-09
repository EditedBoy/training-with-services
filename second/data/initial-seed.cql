CREATE KEYSPACE IF NOT EXISTS mykeyspace WITH REPLICATION = { 'class' : 'NetworkTopologyStrategy', 'datacenter1' : 3 };

use mykeyspace;

CREATE TABLE IF NOT EXISTS people_by_first_name(
  first_name TEXT,
  date_of_birth TIMESTAMP,
  person_id UUID,
  last_name TEXT,
  salary DOUBLE,
  PRIMARY KEY ((first_name), date_of_birth, person_id)
) WITH CLUSTERING ORDER BY (date_of_birth ASC, person_id DESC);