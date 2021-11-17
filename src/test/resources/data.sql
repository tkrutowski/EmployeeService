

INSERT INTO teams(id_team, name) values (1, 'Tomek');
INSERT INTO teams(id_team, name) values (2, 'Darek');
INSERT INTO teams(id_team, name) values (3, 'Adam');

insert into pracownik(id_pracownika, imie, nazwisko, ulica, miasto, kod, urlop_pozostaly, wymiar_urlopu, numer, inne, zatrudniony, data_zatrudnienia, data_zwolnienia, data_nast_badania_lek, data_nast_szkolenia_bhp, email, pol_etatu, czy_kadra)
values (222, 'Tomasz','Krutowski','Szyperska','Poznań','61-754', 2, 26, '662262662','brak','HIRED','2007-07-19',null,'2024-12-18','2022-09-08','tomek@focik.net','FULL_TIME','WORKER');

insert into zaliczka(id_zaliczki, id_pracownika, kwota, data, inne) values (1,1, 1000, '2020-11-01','pierwsza');
insert into zaliczka(id_zaliczki, id_pracownika, kwota, data, inne) values (2,1, 150, '2020-11-22','druga');

insert into rodzaj_dodatku(id_rodzaj_dodatku, nazwa) values (1,'pierwszy rodzaj');
insert into rodzaj_dodatku(id_rodzaj_dodatku, nazwa) values (2,'drugi rodzaj');
insert into rodzaj_dodatku(id_rodzaj_dodatku, nazwa) values (3,'trzeci rodzaj');

insert into dodatek(id_dodatku, id_pracownika, id_rodzaj_dodatku, kwota, data, inne) values (1,1, 1, 150, '2020-11-22','pierwsza');
insert into dodatek(id_dodatku, id_pracownika, id_rodzaj_dodatku, kwota, data, inne) values (2,1, 2, 1000, '2020-11-12','druga');

insert into pozyczka(id_pozyczki, id_pracownika, nazwa, kwota, data, ile_pobierac, inne, status) values (1,1,'pierwsza',5000,'2020-11-05', 500,'brak','TO_PAY');
insert into pozyczka(id_pozyczki, id_pracownika, nazwa, kwota, data, ile_pobierac, inne, status) values (2,2,'druga',15000,'2020-10-17', 1500,'brak','TO_PAY');
insert into pozyczka(id_pozyczki, id_pracownika, nazwa, kwota, data, ile_pobierac, inne, status) values (3,1,'trzecia',3000,'2020-09-27', 300,'brak','TO_PAY');
insert into rata_pozyczki(id_raty, id_pozyczki, kwota_raty, data_raty, wplata_wlasna) values (1,1,555,'2020-10-05',false);
insert into rata_pozyczki(id_raty, id_pozyczki, kwota_raty, data_raty, wplata_wlasna) values (2,1,555,'2020-11-05',false);
insert into rata_pozyczki(id_raty, id_pozyczki, kwota_raty, data_raty, wplata_wlasna) values (3,2,1300,'2020-11-05',false);
insert into rata_pozyczki(id_raty, id_pozyczki, kwota_raty, data_raty, wplata_wlasna) values (4,3,250,'2020-11-05',false);

insert into Dni_wolne values(1,'2020-01-01','Nowy Rok, Świętej Bożej Rodzicielki');
insert into Dni_wolne values(2,'2020-01-06','Trzech Króli (Objawienie Pańskie)');
insert into Dni_wolne values(3,'2020-04-12','Wielkanoc');
insert into Dni_wolne values(4,'2020-04-13','Poniedziałek Wielkanocny');
insert into Dni_wolne values(5,'2020-05-01','Święto Pracy');
insert into Dni_wolne values(6,'2020-05-03','Święto Konstytucji 3 Maja');
insert into Dni_wolne values(7,'2020-05-31','Zesłanie Ducha Świętego (Zielone Świątki)');
insert into Dni_wolne values(8,'2020-06-11','Boże Ciało');
insert into Dni_wolne values(9,'2020-08-15','Święto Wojska Polskiego, Wniebowzięcie Najświętszej Maryi Panny');
insert into Dni_wolne values(10,'2020-11-01','Wszystkich Świętych');
insert into Dni_wolne values(11,'2020-11-11','Święto Niepodległości');
insert into Dni_wolne values(12,'2020-12-25','Boże Narodzenie (pierwszy dzień)');
insert into Dni_wolne values(13,'2020-12-26','Boże Narodzenie (drugi dzień)');

insert into Dni_wolne values(14,'2021-01-01','Nowy Rok, Świętej Bożej Rodzicielki');
insert into Dni_wolne values(15,'2021-01-06','Trzech Króli (Objawienie Pańskie)');
insert into Dni_wolne values(16,'2021-04-04','Wielkanoc');
insert into Dni_wolne values(17,'2021-04-05','Poniedziałek Wielkanocny');
insert into Dni_wolne values(18,'2021-05-01','Święto Pracy');
insert into Dni_wolne values(19,'2021-05-03','Święto Konstytucji 3 Maja');
insert into Dni_wolne values(20,'2021-05-23','Zesłanie Ducha Świętego (Zielone Świątki)');
insert into Dni_wolne values(21,'2021-06-03','Boże Ciało');
insert into Dni_wolne values(22,'2021-08-15','Święto Wojska Polskiego, Wniebowzięcie Najświętszej Maryi Panny');
insert into Dni_wolne values(23,'2021-11-01','Wszystkich Świętych');
insert into Dni_wolne values(24,'2021-11-11','Święto Niepodległości');
insert into Dni_wolne values(25,'2021-12-25','Boże Narodzenie (pierwszy dzień)');
insert into Dni_wolne values(26,'2021-12-26','Boże Narodzenie (drugi dzień)');

insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin) values(2020,1,168);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin)  values(2020,2,160);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin) values(2020,3,176);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin) values(2020,4,168);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin) values(2020,5,160);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin) values(2020,6,168);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin) values(2020,7,184);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin) values(2020,8,160);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin) values(2020,9,176);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin) values(2020,10,176);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin) values(2020,11,160);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin) values(2020,12,168);

insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin)  values(2021,1,152);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin)  values(2021,2,160);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin)  values(2021,3,184);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin)  values(2021,4,168);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin)  values(2021,5,152);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin)  values(2021,6,168);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin)  values(2021,7,176);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin)  values(2021,8,176);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin)  values(2021,9,176);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin)  values(2021,10,168);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin)  values(2021,11,160);
insert into Godziny_robocze(id_rok, id_miesiac, ilosc_godzin)  values(2021,12,176);

