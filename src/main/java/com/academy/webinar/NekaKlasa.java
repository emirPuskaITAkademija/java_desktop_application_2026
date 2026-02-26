package com.academy.webinar;

/**
 * 1. MEtoda iz klase ima prioritet nad metodom iz interfejsa
 * 2, Ako postoji konflikt između dva interfejsa klas amora override tu metodu i reći čiju metodu print poziva
 * 3.Ako interfejsa naslijeđuje drugi interfejs , specifični interfejs ima prednost..onaj neposredni
 */
public class NekaKlasa extends AbstractNekaKlasa implements Flyable,  Printable{

}
