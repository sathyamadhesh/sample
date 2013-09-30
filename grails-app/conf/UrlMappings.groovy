class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.${format})?"{
           
        }

        "/project"(controller: "project", parseRequest: true){
           
            action = [GET: "show", POST: "save"]
        }

        "/project/$id"(controller: "project", parseRequest: true) 
        {
            action = [DELETE : "remove", PUT : "update", GET : "getById"]
        }    
        "/ticket"(controller: "ticket", parseRequest: true) 
        {
            action = [GET: "show"]
        }
         "/tickets"(controller: "ticket", parseRequest: true) 
        {
            action = [GET: "getAllTicketsByUser"]
        }
        "/ticket/$id"(controller: "ticket", parseRequest: true) 
        {
            action = [DELETE : "remove", PUT : "update", GET : "getById", POST: "save"]
        }
        "/tickets/$id"(controller: "ticket", parseRequest: true) 
        {
            action = [GET : "getAllTicketsForAProject"]
        }
        "/ticketsassign"(controller: "ticket", parseRequest: true) 
        {
            action = [GET : "getAllTicketsByAssignTo"]
        }
        
        
        "/ticketsassignproject/$id"(controller: "ticket", parseRequest: true) 
        {
            action = [GET : "getAllTicketsByAssignToProject"]
        }
        
        
        
         "/history"(controller: "history", parseRequest: true) 
        {
            action = [GET: "show", POST: "save"]
        }

        "/history/$id"(controller: "history", parseRequest: true) 
        {
            action = [GET : "getById"]
        }   
          "/"(view:"test")
        // "/"(view:"registration")
        "500"(view:'/error')
        
    }
}
