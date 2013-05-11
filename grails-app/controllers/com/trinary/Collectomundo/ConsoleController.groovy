package com.trinary.Collectomundo

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ROOT'])
class ConsoleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [consoleInstanceList: Console.list(params), consoleInstanceTotal: Console.count()]
    }

    def create() {
        [consoleInstance: new Console(params)]
    }

    def save() {
        def consoleInstance = new Console(params)
        if (!consoleInstance.save(flush: true)) {
            render(view: "create", model: [consoleInstance: consoleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'console.label', default: 'Console'), consoleInstance.id])
        redirect(action: "show", id: consoleInstance.id)
    }

    def show(Long id) {
        def consoleInstance = Console.get(id)
        if (!consoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'console.label', default: 'Console'), id])
            redirect(action: "list")
            return
        }

        [consoleInstance: consoleInstance]
    }

    def edit(Long id) {
        def consoleInstance = Console.get(id)
        if (!consoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'console.label', default: 'Console'), id])
            redirect(action: "list")
            return
        }

        [consoleInstance: consoleInstance]
    }

    def update(Long id, Long version) {
        def consoleInstance = Console.get(id)
        if (!consoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'console.label', default: 'Console'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (consoleInstance.version > version) {
                consoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'console.label', default: 'Console')] as Object[],
                          "Another user has updated this Console while you were editing")
                render(view: "edit", model: [consoleInstance: consoleInstance])
                return
            }
        }

        consoleInstance.properties = params

        if (!consoleInstance.save(flush: true)) {
            render(view: "edit", model: [consoleInstance: consoleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'console.label', default: 'Console'), consoleInstance.id])
        redirect(action: "show", id: consoleInstance.id)
    }

    def delete(Long id) {
        def consoleInstance = Console.get(id)
        if (!consoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'console.label', default: 'Console'), id])
            redirect(action: "list")
            return
        }

        try {
            consoleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'console.label', default: 'Console'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'console.label', default: 'Console'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def editGameLibrary(Long id) {
		def console = Console.get(id)
		
		[consoleInstance: console]
	}
	
	def uploadGameLibrary() {
		def list = params.list
		def console = Console.get(params.id)
		
		if (!list) {
			flash.message = "List is a required element!"
			redirect(action: editGameLibrary, id: params.id)
			return
		}
		
		def games = list.split("\n")
		
		games.each {
			def game = new Game(name: it)
			game.console = console
			if (!game.save()) {
				game.errors.each {
					println "ERROR: ${it}"
				}
			}
		}
		
		redirect(controller: "game", action: "list")
	}
	
	def editAccessoryLibrary() {
		
	}
	
	def uploadAccessoryLibrary() {
		
	}
}
