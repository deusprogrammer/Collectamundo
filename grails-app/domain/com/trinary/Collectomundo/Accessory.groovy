package com.trinary.Collectomundo

class Accessory {
	String name
	Date releaseDate
	Integer rarity
	
	String toString() {
		return name
	}
	
	static belongsTo = [platform: Platform]

    static constraints = {
    }
}
