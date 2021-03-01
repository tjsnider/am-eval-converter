package com.ted.snider.converter.lookup;

public enum State {
    ALABAMA("AL"),
    ALASKA("AK"),
    AMERICANSAMOA("AS"),
    ARIZONA("AZ"),
    ARKANSAS("AR"),
    CALIFORNIA("CA"),
    COLORADO("CO"),
    CONNECTICUT("CT"),
    DELAWARE("DE"),
    DISTRICTOFCOLUMBIA("DC"),
    FEDERATEDSTATESOFMICRONESIA("FM"),
    FLORIDA("FL"),
    GEORGIA("GA"),
    GUAM("GU"),
    HAWAII("HI"),
    IDAHO("ID"),
    ILLINOIS("IL"),
    INDIANA("IN"),
    IOWA("IA"),
    KANSAS("KS"),
    KENTUCKY("KY"),
    LOUISIANA("LA"),
    MAINE("ME"),
    MARSHALLISLANDS("MH"),
    MARYLAND("MD"),
    MASSACHUSETTS("MA"),
    MICHIGAN("MI"),
    MINNESOTA("MN"),
    MISSISSIPPI("MS"),
    MISSOURI("MO"),
    MONTANA("MT"),
    NEBRASKA("NE"),
    NEVADA("NV"),
    NEWHAMPSHIRE("NH"),
    NEWJERSEY("NJ"),
    NEWMEXICO("NM"),
    NEWYORK("NY"),
    NORTHCAROLINA("NC"),
    NORTHDAKOTA("ND"),
    NORTHERNMARIANAISLANDS("MP"),
    OHIO("OH"),
    OKLAHOMA("OK"),
    OREGON("OR"),
    PALAU("PW"),
    PENNSYLVANIA("PA"),
    PUERTORICO("PR"),
    RHODEISLAND("RI"),
    SOUTHCAROLINA("SC"),
    SOUTHDAKOTA("SD"),
    TENNESSEE("TN"),
    TEXAS("TX"),
    UTAH("UT"),
    VERMONT("VT"),
    VIRGINISLANDS("VI"),
    VIRGINIA("VA"),
    WASHINGTON("WA"),
    WESTVIRGINIA("WV"),
    WISCONSIN("WI"),
    WYOMING("WY");

    private String code;

    private State(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
