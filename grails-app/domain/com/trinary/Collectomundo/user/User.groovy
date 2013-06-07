package com.trinary.Collectomundo.user

import com.trinary.Collectomundo.Accessory
import com.trinary.Collectomundo.Game
import com.trinary.Collectomundo.Console

import com.trinary.Collectomundo.ownerships.AccessoryOwnership
import com.trinary.Collectomundo.ownerships.GameOwnership
import com.trinary.Collectomundo.ownerships.ConsoleOwnership
import com.trinary.Collectomundo.Role

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	static hasMany = [games: GameOwnership, accessories: AccessoryOwnership, consoles: ConsoleOwnership]
	
	def addToConsoleCollection(Console console) {
		def ownership = new ConsoleOwnership(user: this, console: console)
		addToConsoles(ownership)
	}
	
	def addToGameCollection(Game game) {
		def ownership = new GameOwnership(user: this, game: game)
		addToGames(ownership)
	}
	
	def addToAccessoryCollection(Accessory accessory) {
		def ownership = new AccessoryOwnership(user: this, accessory: accessory)
		addToAccessories(ownership)
	}

	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}