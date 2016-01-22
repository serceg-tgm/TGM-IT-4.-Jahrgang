CREATE DATABASE IF NOT EXISTS schokoladefabrik CHAR SET 'utf8';

USE schokoladefabrik;

CREATE TABLE person (
	persid INT PRIMARY KEY,
	vname VARCHAR(30) NOT NULL,
	nname VARCHAR(30) NOT NULL
);

CREATE TABLE produkt (
	prid INT PRIMARY KEY,
	prbez VARCHAR(100) NOT NULL,
	gewicht FLOAT NOT NULL
);

CREATE TABLE maschine (
	maschinenid INT PRIMARY KEY,
	maschinenbeschr VARCHAR(100) NOT NULL
);

CREATE TABLE lager (
	lagerbez VARCHAR(100) PRIMARY KEY,
	flaeche FLOAT NOT NULL
);

CREATE TABLE firma (
	fname VARCHAR(30) PRIMARY KEY,
	kontaktadr VARCHAR(50) NOT NULL,
	telefonnr INT NOT NULL
);

CREATE TABLE kuenstler (
	persid INT PRIMARY KEY,
	bekanntheitsgrad INT NOT NULL,
	FOREIGN KEY(persid) REFERENCES person(persid),
	-- Überprüfung, ob beim Bekanntheitsgrad ein Wert zwischen 0 und 10 eingegeben worden ist
	CHECK (bekanntheitsgrad>=0 AND bekanntheitsgrad<=10)
);

CREATE TABLE mitarbeiter (
	persid INT PRIMARY KEY,
	einstellungsdtm DATE NOT NULL,
	lagerbez VARCHAR(100) NOT NULL,
	FOREIGN KEY(lagerbez) REFERENCES lager(lagerbez)
);

CREATE TABLE kuendigung (
	persid INT PRIMARY KEY,
	kuendigungsdtm DATE,
	FOREIGN KEY(persid) REFERENCES mitarbeiter(persid)
);

CREATE TABLE standardsortiment (
	prid INT PRIMARY KEY,
	verkaufspreis FLOAT NOT NULL,
	verpackungsart VARCHAR(50) NOT NULL,
	FOREIGN KEY(prid) REFERENCES produkt(prid)
);

CREATE TABLE kunstwerk (
	prid INT PRIMARY KEY,
	schaetzwert FLOAT NOT NULL,
	kuenstlerid INT NOT NULL,
	FOREIGN KEY(kuenstlerid) REFERENCES kuenstler(persid)
);

CREATE TABLE produktion (
	prid INT,
	maschinenid INT,
	PRIMARY KEY(prid,maschinenid),
	FOREIGN KEY(prid) REFERENCES produkt(prid),
	FOREIGN KEY(maschinenid) REFERENCES maschine(maschinenid)
);

CREATE TABLE produktionsauftrag (
	auftragsnr INT,
	fname VARCHAR(30),
	auftragsdtm DATE NOT NULL,
	aktStatus VARCHAR(100) NOT NULL,
	PRIMARY KEY(auftragsnr,fname),
	FOREIGN KEY(fname) REFERENCES firma(fname)
);

CREATE TABLE auftragsinfo (
	auftragsnr INT,
	fname VARCHAR(30),
	prid INT,
	anzahl INT NOT NULL,
	PRIMARY KEY(auftragsnr,fname,prid),
	FOREIGN KEY(auftragsnr) REFERENCES produktionsauftrag(auftragsnr),
	FOREIGN KEY(fname) REFERENCES firma(fname),
	FOREIGN KEY(prid) REFERENCES produkt(prid)
);

CREATE TABLE bedienung (
	maschinenid INT,
	persid INT,
	PRIMARY KEY(maschinenid,persid),
	FOREIGN KEY(maschinenid) REFERENCES maschine(maschinenid),
	FOREIGN KEY(persid) REFERENCES person(persid)
);

CREATE TABLE lagerung (
	lagerbez VARCHAR(100),
	prid INT,
	anzahl INT NOT NULL,
	PRIMARY KEY(lagerbez,prid),
	FOREIGN KEY(lagerbez) REFERENCES lager(lagerbez),
	FOREIGN KEY(prid) REFERENCES produkt(prid)
);

CREATE TABLE kunstschau (
	ksname VARCHAR(30),
	ksdtm DATE,
	ksland VARCHAR(30) NOT NULL,
	ksort VARCHAR(30) NOT NULL,
	PRIMARY KEY(ksname,ksdtm)
);

CREATE TABLE vorfuehrung (
	ksname VARCHAR(30),
	ksdtm DATE,
	prid INT,
	platz INT NOT NULL,
	PRIMARY KEY(ksname,ksdtm,prid),
	FOREIGN KEY(ksname,ksdtm) REFERENCES kunstschau(ksname,ksdtm),
	FOREIGN KEY(prid) REFERENCES produkt(prid)
);
