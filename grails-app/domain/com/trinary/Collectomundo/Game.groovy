package com.trinary.Collectomundo

class Game {
	String name
	String publisher
	Date releaseDate
	Integer rarity
	
	static belongsTo = [console: Console, owner: User]
	static hasMany = [owner: User]
	
	String toString() {
		return name
	}

    static constraints = {
		owner nullable: true
		name unique: true
		publisher nullable: true
		releaseDate nullable: true
		rarity nullable: true
    }
}
