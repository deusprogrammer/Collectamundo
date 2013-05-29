package com.trinary.Collectomundo

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

//@Secured(['ROLE_ROOT'])
class GameController {
	def paginateService
	def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [gameInstanceList: Game.list(params), gameInstanceTotal: Game.count()]
    }
	
	def listByConsole(String id) {
		params.offset = (params.offset?.toInteger() ?: 0)
		params.max = Math.min(params.max?.toInteger() ?: 10, 100)
		params.sort = 'name'
		
		def console = Console.findByAbbreviation(id)
		
		if (!console) {
			redirect(action: "index")
		}
		
		def map = paginateService.paginate(console.games, params)
		User user = springSecurityService.currentUser
		
		println "START: ${map.start}"
		println "END:   ${map.end}"
		
		println "BALLS: ${user.games.collect{it.id}}"
		
		def ownedGames = user.games.collect{it.id}.findAll{it >= map.start && it <= map.end}
		
		println "OWNED GAMES: ${ownedGames}"
		
		[gameInstanceList: map.list, gameInstanceTotal: map.listSize, console: console.abbreviation, owned: ownedGames]
	}
	
	def listByOwner(String id) {
		User user = User.findByUsername(id)
		def games
		
		if (params.console) {
			games = user.games.findAll{it.console.abbreviation == params.console}
		} else {
			games = user.games
		}
		
		if (!user) {
			user = springSecurityService.currentUser
		} 
		
		params.sort = "name"
		
		def map = paginateService.paginate(games, params)
		
		[gameInstanceList: map.list, gameInstanceTotal: map.listSize, username: user.username, console: params.console ?: "Game"]
	}
    
    def search() {
        def games = Game.list()
        
        if (params.console) {
            games = games.findAll{it.console.abbreviation == params.console}
        }
        if (params.owner) {
            User user = User.findByUsername(params.owner)
            games = user.games.findAll{}
        }
        if (params.title) {
            games = games.findAll{it.name.toLowerCase().contains(params.title.toLowerCase())}
        }
        if (!params.sort) {
            params.sort = "name"
        }
        
        def map = paginateService.paginate(games, params)
        
        [gameInstanceList: map.list, gameInstanceTotal: map.listSize, params: params]
    }

	def create() {
        [gameInstance: new Game(params), console: params.console.id]
    }

    def save() {
        def gameInstance = new Game(params)
        if (!gameInstance.save(flush: true)) {
            render(view: "create", model: [gameInstance: gameInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'game.label', default: 'Game'), gameInstance.id])
        redirect(action: "show", id: gameInstance.id)
    }

    def show(Long id) {
        def gameInstance = Game.get(id)
        if (!gameInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'game.label', default: 'Game'), id])
            redirect(action: "list")
            return
        }

        [gameInstance: gameInstance]
    }

    def edit(Long id) {
        def gameInstance = Game.get(id)
        if (!gameInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'game.label', default: 'Game'), id])
            redirect(action: "list")
            return
        }

        [gameInstance: gameInstance]
    }

    def update(Long id, Long version) {
        def gameInstance = Game.get(id)
        if (!gameInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'game.label', default: 'Game'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (gameInstance.version > version) {
                gameInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'game.label', default: 'Game')] as Object[],
                          "Another user has updated this Game while you were editing")
                render(view: "edit", model: [gameInstance: gameInstance])
                return
            }
        }

        gameInstance.properties = params

        if (!gameInstance.save(flush: true)) {
            render(view: "edit", model: [gameInstance: gameInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'game.label', default: 'Game'), gameInstance.id])
        redirect(action: "show", id: gameInstance.id)
    }

    def delete(Long id) {
        def gameInstance = Game.get(id)
        if (!gameInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'game.label', default: 'Game'), id])
            redirect(action: "list")
            return
        }

        try {
            gameInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'game.label', default: 'Game'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'game.label', default: 'Game'), id])
            redirect(action: "show", id: id)
        }
    }
	
	//@Secured('ROLE_USER')
	def addAllToCollection() {
		//def user = User.get(springSecurityService.currentUser)
		User user = springSecurityService.currentUser
		
		if (!(params.want instanceof List)) {
			params.want = [params.want]
		}
		
		if (!(params.own instanceof List)) {
			params.own = [params.own]
		}

		def want = Game.getAll(params.want)
		def own  = Game.getAll(params.own)
		
		println params.own

		if (user && own) {
			own.each {
				println "OWN: ${it}"
				user.addToGames(it)
			}
			user.save()
			redirect(action: "listByOwner", id: user.username)
		} else {
			flash.message = "You must supply both a user and a title in your parameters."
			redirect(action: "list")
		}
				
		return
	}
}
