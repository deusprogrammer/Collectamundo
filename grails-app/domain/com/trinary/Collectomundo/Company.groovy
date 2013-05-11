package com.trinary.Collectomundo

class Company {
	String name
	
	static hasMany = [consoles: Console]
	
	String toString() {
		return name
	}

    static constraints = {
		name unique: true
    }
}
