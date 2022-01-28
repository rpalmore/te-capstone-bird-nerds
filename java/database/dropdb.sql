-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'bird_db';

DROP DATABASE bird_db;

DROP USER bird_db_owner;
DROP USER bird_db_app_user;
