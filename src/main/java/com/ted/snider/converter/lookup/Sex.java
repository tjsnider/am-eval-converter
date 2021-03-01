package com.ted.snider.converter.lookup;

// TODO refactor to Gender
public enum Sex {
    MALE("male"), 
    FEMALE("female"), 
    NONBINARY("gender neutral or nonbinary"), 
    INTERSEX("intersex"), 
    TRANSGENDER("transgender"), 
    GENDERQUEER("genderqueer"), 
    OTHER("third gender, agender, or other as-yet uncoded gender");

    private String description;

    private Sex(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static Sex fromAbbreviation(String abbr) {
        switch(abbr.toLowerCase()) {
            case "m": return Sex.MALE;
            case "f": return Sex.FEMALE;
            case "n": return Sex.NONBINARY;
            case "i": return Sex.INTERSEX;
            case "t": return Sex.TRANSGENDER;
            case "q": return Sex.GENDERQUEER;
            default: return Sex.OTHER;
        }
    }
}
