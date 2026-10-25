package com.emrekiziltoprak.libraryapp.book;

import java.util.Arrays;

public enum Genre {

    NOVEL("Novel"),
    SCIENCE("Science"),
    HISTORY("History"),
    PHILOSOPHY("Philosophy"),
    POETRY("Poetry"),
    OTHER("Other");

    private final String displayName;

    private Genre(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }

    public Genre fromDisplayName(String name){

        return Arrays.stream(values())
                .filter(genre -> genre.displayName.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown genre: " + name));
    }

}
