package com.trinary.Collectomundo

class Company {
	String name
	
	static hasMany = [platforms: Platform]
	
	String toString() {
		return name
	}

    static constraints = {
		name unique: true
    }
}
