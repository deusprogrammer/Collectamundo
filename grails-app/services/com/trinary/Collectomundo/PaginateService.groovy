package com.trinary.Collectomundo

class PaginateService {

    def paginate(def list, def params) {
		println "PARAMS: " + params
		
		if (params["sort"]) {
			def sort = params.sort
			if (params.order && params.order == "desc") {
				list = list.sort{it["${sort}"]}.reverse()
			} else {
				list = list.sort{it["${sort}"]}
			}
		}
		
		println "OFFSET: " + params["offset"]
		println "MAX:    " + params["max"]
		
		println "COMP:   " + (params["offset"] != null && params["max"] != null)
		
		if (params["offset"] != null && params["max"] != null) {
			def offset = params.offset.toInteger()
			def max = params.max.toInteger()
			def endRange = (offset + max - 1) <= (list.size() - 1) ? (offset + max - 1) : list.size() - 1
			def range = offset..endRange
			
			println "OFFSET: " + offset
			println "RANGE:  " + endRange
  
			return [listSize: list.size(), list: list[range]]
		}
		
		return [listSize: list.size(), list: list]
    }
}
