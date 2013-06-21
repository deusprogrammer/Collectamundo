package com.trinary.Collectomundo

import com.ebay.services.client.ClientConfig
import com.ebay.services.client.FindingServiceClientFactory
import com.ebay.services.finding.FindItemsByKeywordsRequest
import com.ebay.services.finding.FindItemsByKeywordsResponse
import com.ebay.services.finding.FindingServicePortType
import com.ebay.services.finding.PaginationInput
import com.ebay.services.finding.SearchItem

class EBayService {

	Float findGamePrice(String title) {
        try { 
            // initialize service end-point configuration 
            ClientConfig config = new ClientConfig();
            config.setApplicationId("21044554-7d7f-4c18-89a4-ea15ea041cf3");
            
            //create a service client 
            FindingServicePortType serviceClient = FindingServiceClientFactory.getServiceClient(config) 
            
            //create request object 
            FindItemsByKeywordsRequest request = new FindItemsByKeywordsRequest() 
            
            //set request parameters 
            request.setKeywords(title)
            PaginationInput pi = new PaginationInput()
            pi.setEntriesPerPage(100)
            request.setPaginationInput(pi);
            
            //call service 
            FindItemsByKeywordsResponse result = serviceClient.findItemsByKeywords(request) 
            
            //output result 
            println "Ack = " + result.ack;
            println "Find " + result.searchResult.count + " items." 
            List<SearchItem> items = result.searchResult.item 
            for(SearchItem item : items) {
				if (item.listingInfo.buyItNowAvailable) { 
	                println item.title
					println "\t\$" + item.listingInfo.buyItNowPrice?.value.round(2)
				}
            } 
        } catch (Exception ex) { 
            // handle exception if any 
            ex.printStackTrace(); 
        }
    }
}
