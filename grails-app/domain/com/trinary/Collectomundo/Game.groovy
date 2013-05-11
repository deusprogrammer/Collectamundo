package com.trinary.Collectomundo

class Game {
	String name
	String publisher
	Date releaseDate
	Integer rarity
	
	static belongsTo = [console: Console]
	
	String toString() {
		return name
	}

    static constraints = {
		name unique: true
		publisher nullable: true
		releaseDate nullable: true
		rarity nullable: true
    }
}