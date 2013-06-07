package com.trinary.Collectomundo

import org.springframework.dao.DataIntegrityViolationException

import com.trinary.Collectomundo.user.User;

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ROOT'])
class PlatformController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [platformInstanceList: Platform.list(params), platformInstanceTotal: Platform.count()]
    }

    def create() {
        [platformInstance: new Platform(params), company: params.company.id]
    }

    def save() {
        def platformInstance = new Platform(params)
        if (!platformInstance.save(flush: true)) {
            render(view: "create", model: [platformInstance: platformInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'platform.label', default: 'Console'), platformInstance.id])
        redirect(action: "show", id: platformInstance.id)
    }

    def show(Long id) {
        def platformInstance = Platform.get(id)
        if (!platformInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'platform.label', default: 'Console'), id])
            redirect(action: "list")
            return
        }

        [platformInstance: platformInstance]
    }

    def edit(Long id) {
        def platformInstance = Platform.get(id)
        if (!platformInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'platform.label', default: 'Console'), id])
            redirect(action: "list")
            return
        }

        [platformInstance: platformInstance]
    }

    def update(Long id, Long version) {
        def platformInstance = Platform.get(id)
        if (!platformInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'platform.label', default: 'Console'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (platformInstance.version > version) {
                platformInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'platform.label', default: 'Console')] as Object[],
                          "Another user has updated this Console while you were editing")
                render(view: "edit", model: [platformInstance: platformInstance])
                return
            }
        }

        platformInstance.properties = params

        if (!platformInstance.save(flush: true)) {
            render(view: "edit", model: [platformInstance: platformInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'platform.label', default: 'Console'), platformInstance.id])
        redirect(action: "show", id: platformInstance.id)
    }

    def delete(Long id) {
        def platformInstance = Platform.get(id)
        if (!platformInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'platform.label', default: 'Console'), id])
            redirect(action: "list")
            return
        }

        try {
            platformInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'platform.label', default: 'Console'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'platform.label', default: 'Console'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def editGameLibrary(Long id) {
		def platform = Platform.get(id)
		
		[platformInstance: platform]
	}
	
	def uploadGameLibrary() {
		def list = params.list
		def platform = Platform.get(params.id)
		
		if (!list) {
			flash.message = "List is a required element!"
			redirect(action: editGameLibrary, id: params.id)
			return
		}
		
		def games = list.split("\n")
		
		games.each {
			println it
			def game = new Game(name: it)
			game.platform = platform
			if (!game.save()) {
				game.errors.each {
					println "ERROR: ${it}"
				}
			}
		}
		
		redirect(controller: "game", action: "listByPlatform", id: platform.abbreviation)
	}
	
	def editAccessoryLibrary() {
		
	}
	
	def uploadAccessoryLibrary() {
		
	}
	
	@Secured(['ROLE_USER', 'ROLE_ADMIN', 'ROLE_ROOT'])
	def addToCollection(Long id) {
		def user = User.get(params.user)
		def platform = Platform.get(id)
		
		if (user && platform) {
			user.addToConsoles(platform)
			user.save()
			redirect(action: "list")
		} else {
			flash.message = "You must supply both a user and a title in your parameters."
			redirect(action: "list")
		}
		return
	}
}
