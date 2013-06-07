package com.trinary.Collectomundo

class Platform {
	String name
	String abbreviation
	Date releaseDate
	Date endOfLife
	
	static hasMany = [games: Game, accessories: Accessory, consoles: PlatformConsole]
	static belongsTo = [company: Company]
	
	def addToPlatformConsoles(Console console) {
		def platformConsole = new PlatformConsole(platform: this, console: console)
		addToConsoles(platformConsole)
	}
	
	String toString() {
		return abbreviation ?: name
	}
	
    static constraints = {
		name unique: true
		abbreviation nullable: true
    }
}
