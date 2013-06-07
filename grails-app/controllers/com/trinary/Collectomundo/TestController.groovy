package com.trinary.Collectomundo

class TestController {
	def EBayService
	
    def ebaySearch() { 
		String title = params.title
		
		if (!title) {
			response.setStatus(400)
			return
		}
		
		EBayService.findGamePrice(title)
		
		render "DICKS"
		return
	}
}
