CREATE TABLE IF NOT EXISTS public.ICICIBANK (
  id integer NOT NULL,
  symbol VARCHAR(255) NULL DEFAULT NULL,
  open bigint NULL DEFAULT NULL,
  high bigint NULL default NULL,
  low bigint NULL default  NULL,
  close bigint NULL default NULL,
  volume bigint NULL default NULL,
  stocktime VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.NIFTY (
  id integer NOT NULL,
  symbol VARCHAR(255) NULL DEFAULT NULL,
  open bigint NULL DEFAULT NULL,
  high bigint NULL default NULL,
  low bigint NULL default  NULL,
  close bigint NULL default NULL,
  volume bigint NULL default NULL,
  stocktime VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.IOC (
  id integer NOT NULL,
  symbol VARCHAR(255) NULL DEFAULT NULL,
  open bigint NULL DEFAULT NULL,
  high bigint NULL default NULL,
  low bigint NULL default  NULL,
  close bigint NULL default NULL,
  volume bigint NULL default NULL,
  stocktime VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.SUNPHARMA (
  id integer NOT NULL,
  symbol VARCHAR(255) NULL DEFAULT NULL,
  open bigint NULL DEFAULT NULL,
  high bigint NULL default NULL,
  low bigint NULL default  NULL,
  close bigint NULL default NULL,
  volume bigint NULL default NULL,
  stocktime VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.HINDALCO (
  id integer NOT NULL,
  symbol VARCHAR(255) NULL DEFAULT NULL,
  open bigint NULL DEFAULT NULL,
  high bigint NULL default NULL,
  low bigint NULL default  NULL,
  close bigint NULL default NULL,
  volume bigint NULL default NULL,
  stocktime VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.COALINDIA (
  id integer NOT NULL,
  symbol VARCHAR(255) NULL DEFAULT NULL,
  open bigint NULL DEFAULT NULL,
  high bigint NULL default NULL,
  low bigint NULL default  NULL,
  close bigint NULL default NULL,
  volume bigint NULL default NULL,
  stocktime VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.VEDL (
  id integer NOT NULL,
  symbol VARCHAR(255) NULL DEFAULT NULL,
  open bigint NULL DEFAULT NULL,
  high bigint NULL default NULL,
  low bigint NULL default  NULL,
  close bigint NULL default NULL,
  volume bigint NULL default NULL,
  stocktime VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.SBIN (
  id integer NOT NULL,
  symbol VARCHAR(255) NULL DEFAULT NULL,
  open bigint NULL DEFAULT NULL,
  high bigint NULL default NULL,
  low bigint NULL default  NULL,
  close bigint NULL default NULL,
  volume bigint NULL default NULL,
  stocktime VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (id)
);
