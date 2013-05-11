package com.trinary.Collectomundo

class Console {
	String name
	String abbreviation
	Date releaseDate
	Date endOfLife
	
	static hasMany = [games: Game, accessories: Accessory]
	static belongsTo = [company: Company]
	
	String toString() {
		return abbreviation ?: name
	}
	
    static constraints = {
		name unique: true
		abbreviation nullable: true
    }
}
