-- SET SESSION sql_mode='ANSI,ORACLE';

CREATE TABLE country
(name VARCHAR(35) NOT NULL UNIQUE,
 code VARCHAR(4),
 capital VARCHAR(35),
 province VARCHAR(35),
 area FLOAT,
 population INT,
 CONSTRAINT countryKey PRIMARY KEY(code),
 CONSTRAINT countryarea CHECK (area >= 0),
 CONSTRAINT countryPop CHECK (population >= 0));

CREATE TABLE city
(name VARCHAR(35),
 country VARCHAR(4),
 province VARCHAR(35),
 population INT,
 longitude FLOAT,
 latitude FLOAT,
 CONSTRAINT cityKey PRIMARY KEY (name, country, province),
 CONSTRAINT cityPop CHECK (population >= 0),
 CONSTRAINT cityLon CHECK ((longitude >= -180) AND (longitude <= 180)),
 CONSTRAINT cityLat CHECK ((latitude >= -90) AND (latitude <= 90)));

CREATE TABLE province
(name VARCHAR(35) NOT NULL,
 country VARCHAR(4) NOT NULL ,
 population INT,
 area FLOAT,
 capital VARCHAR(35),
 CapProv VARCHAR(35),
 CONSTRAINT PrKey PRIMARY KEY (name, country),
 CONSTRAINT PrPop CHECK (population >= 0),
 CONSTRAINT PrAr CHECK (area >= 0));

CREATE TABLE economy
(country VARCHAR(4),
 gdp FLOAT,
 agriculture FLOAT,
 service FLOAT,
 industry FLOAT,
 inflation FLOAT,
 CONSTRAINT economyKey PRIMARY KEY(country),
 CONSTRAINT economygdp CHECK (gdp >= 0));

CREATE TABLE population
(country VARCHAR(4),
 population_Growth FLOAT,
 Infant_Mortality FLOAT,
 CONSTRAINT PopKey PRIMARY KEY(country));

CREATE TABLE politics
(country VARCHAR(4),
 independence DATE,
 dependent  VARCHAR(4),
 government VARCHAR(120),
 CONSTRAINT politicsKey PRIMARY KEY(country));

CREATE TABLE language
(country VARCHAR(4),
 name VARCHAR(50),
 percentage FLOAT,
 CONSTRAINT languageKey PRIMARY KEY (name, country),
 CONSTRAINT languagePercent
   CHECK ((percentage > 0) AND (percentage <= 100)));

CREATE TABLE religion
(country VARCHAR(4),
 name VARCHAR(50),
 percentage FLOAT,
 CONSTRAINT religionKey PRIMARY KEY (name, country),
 CONSTRAINT religionPercent
   CHECK ((percentage > 0) AND (percentage <= 100)));

CREATE TABLE ethnicGroup
(country VARCHAR(4),
 name VARCHAR(50),
 percentage FLOAT,
 CONSTRAINT EthnicKey PRIMARY KEY (name, country),
 CONSTRAINT EthnicPercent 
   CHECK ((percentage > 0) AND (percentage <= 100)));

CREATE TABLE continent
(name VARCHAR(20),
 area FLOAT(10),
 CONSTRAINT continentKey PRIMARY KEY(name));

CREATE TABLE borders
(country1 VARCHAR(4),
 country2 VARCHAR(4),
 length FLOAT,
 CONSTRAINT BorderLength CHECK (length > 0),
 CONSTRAINT BorderKey PRIMARY KEY (country1,country2));

CREATE TABLE encompasses
(country VARCHAR(4) NOT NULL,
 continent VARCHAR(20) NOT NULL,
 percentage FLOAT,
 CONSTRAINT EncompassesPercentage CHECK ((percentage > 0) AND (percentage <= 100)),
 CONSTRAINT EncompassesKey PRIMARY KEY (country,continent));

CREATE TABLE organization
(Abbreviation VARCHAR(12) PRIMARY KEY,
 name VARCHAR(80) NOT NULL,
 city VARCHAR(35) ,
 country VARCHAR(4) ,
 province VARCHAR(35) ,
 established DATE,
 CONSTRAINT OrgnameUnique UNIQUE (name));

CREATE TABLE isMember
(country VARCHAR(4),
 organization VARCHAR(12),
 Type VARCHAR(35) DEFAULT 'member',
 CONSTRAINT MemberKey PRIMARY KEY (country,organization) );


CREATE TABLE mountain
(name VARCHAR(35),
 mountains VARCHAR(35),
 height FLOAT,
 Type VARCHAR(10),
 longitude FLOAT,
 latitude FLOAT,
 CONSTRAINT mountainKey PRIMARY KEY(name),
 CONSTRAINT mountainLongitude CHECK ((longitude >= -180) AND (longitude <= 180)
              AND  (latitude >= -90) AND (latitude <= 90)));

CREATE TABLE desert
(name VARCHAR(35),
 area FLOAT,
 longitude FLOAT,
 latitude FLOAT,
 CONSTRAINT desertKey PRIMARY KEY(name),
 CONSTRAINT DesCoord 
   CHECK ((longitude >= -180) AND (longitude <= 180)
     AND  (latitude >= -90) AND (latitude <= 90)));

CREATE TABLE island
(name VARCHAR(35),
 islands VARCHAR(35),
 area FLOAT,
 height FLOAT,
 Type VARCHAR(10),
 CONSTRAINT islandKey PRIMARY KEY(name),
 CONSTRAINT islandAr check (area >= 0),
 longitude FLOAT,
 latitude FLOAT,
 CONSTRAINT islandCoord
   CHECK ((longitude >= -180) AND (longitude <= 180)
     AND  (latitude >= -90) AND (latitude <= 90)));

CREATE TABLE lake
(name VARCHAR(35),
 area FLOAT,
 depth FLOAT,
 altitude FLOAT,
 Type VARCHAR(10),
 river VARCHAR(35),
 longitude FLOAT,
 latitude FLOAT,
 CONSTRAINT lakeKey PRIMARY KEY(name),
 CONSTRAINT lakeAr CHECK (area >= 0),
 CONSTRAINT lakeDpth CHECK (depth >= 0),
 CONSTRAINT lakeCoord
   CHECK ((longitude >= -180) AND (longitude <= 180)
     AND  (latitude >= -90) AND (latitude <= 90)));

CREATE TABLE sea
(name VARCHAR(35),
 depth FLOAT,
 CONSTRAINT seaKey PRIMARY KEY(name),
 CONSTRAINT seadepth CHECK (depth >= 0));

CREATE TABLE river
(name VARCHAR(35),
 river VARCHAR(35),
 lake VARCHAR(35),
 sea VARCHAR(35),
 length FLOAT,
 sourcelongitude FLOAT,
 sourcelatitude FLOAT,
 mountains VARCHAR(35),
 sourcealtitude FLOAT,
 estuarylongitude FLOAT,
 estuarylatitude FLOAT,
 CONSTRAINT riverKey PRIMARY KEY(name),
 CONSTRAINT riverlength CHECK (length >= 0),
 CONSTRAINT sourceCoord
     CHECK ((sourcelongitude >= -180) AND
            (sourcelongitude <= 180) AND
            (sourcelatitude >= -90) AND
            (sourcelatitude <= 90)),
 CONSTRAINT EstCoord
     CHECK ((estuarylongitude >= -180) AND
            (estuarylongitude <= 180) AND
            (estuarylatitude >= -90) AND
            (estuarylatitude <= 90)));

CREATE TABLE geo_mountain
(mountain VARCHAR(35) ,
 country VARCHAR(4) ,
 province VARCHAR(35) ,
 CONSTRAINT GmountainKey PRIMARY KEY (province,country,mountain) );

CREATE TABLE geo_desert
(desert VARCHAR(35) ,
 country VARCHAR(4) ,
 province VARCHAR(35) ,
 CONSTRAINT GdesertKey PRIMARY KEY (province, country, desert) );

CREATE TABLE geo_island
(island VARCHAR(35) ,
 country VARCHAR(4) ,
 province VARCHAR(35) ,
 CONSTRAINT GislandKey PRIMARY KEY (province, country, island) );

CREATE TABLE geo_river
(river VARCHAR(35) ,
 country VARCHAR(4) ,
 province VARCHAR(35) ,
 CONSTRAINT GriverKey PRIMARY KEY (province ,country, river) );

CREATE TABLE geo_sea
(sea VARCHAR(35) ,
 country VARCHAR(4)  ,
 province VARCHAR(35) ,
 CONSTRAINT GseaKey PRIMARY KEY (province, country, sea) );

CREATE TABLE geo_lake
(lake VARCHAR(35) ,
 country VARCHAR(4) ,
 province VARCHAR(35) ,
 CONSTRAINT GlakeKey PRIMARY KEY (province, country, lake) );

CREATE TABLE geo_source
(river VARCHAR(35) ,
 country VARCHAR(4) ,
 province VARCHAR(35) ,
 CONSTRAINT GsourceKey PRIMARY KEY (province, country, river) );

CREATE TABLE geo_estuary
(river VARCHAR(35) ,
 country VARCHAR(4) ,
 province VARCHAR(35) ,
 CONSTRAINT GestuaryKey PRIMARY KEY (province, country, river) );

CREATE TABLE mergesWith
(sea1 VARCHAR(35) ,
 sea2 VARCHAR(35) ,
 CONSTRAINT MergesWithKey PRIMARY KEY (sea1, sea2) );

CREATE TABLE located
(city VARCHAR(35) ,
 province VARCHAR(35) ,
 country VARCHAR(4) ,
 river VARCHAR(35),
 lake VARCHAR(35),
 sea VARCHAR(35) );

CREATE TABLE locatedOn
(city VARCHAR(35) ,
 province VARCHAR(35) ,
 country VARCHAR(4) ,
 island VARCHAR(35) ,
 CONSTRAINT locatedOnKey PRIMARY KEY (city, province, country, island) );

CREATE TABLE islandIn
(island VARCHAR(35) ,
 sea VARCHAR(35) ,
 lake VARCHAR(35) ,
 river VARCHAR(35) );

CREATE TABLE mountainOnIsland
(mountain VARCHAR(35),
 island  VARCHAR(35),
 CONSTRAINT MntIslKey PRIMARY KEY (mountain, island) );
