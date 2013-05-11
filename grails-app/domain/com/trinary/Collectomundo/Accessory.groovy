package com.trinary.Collectomundo

class Accessory {
	String name
	Date releaseDate
	Integer rarity
	
	String toString() {
		return name
	}
	
	static belongsTo = [console: Console]

    static constraints = {
    }
}
