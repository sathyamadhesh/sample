package com.neev.controller
import grails.converters.*
import groovy.json.JsonBuilder
import com.neev.service.ProjectService
import com.neev.trac.Project
import com.neev.trac.User
import com.neev.trac.Status
import com.neev.trac.Ticket
import com.neev.trac.History
class HistoryController 
{
    def historyService
    def index() { }
    JsonBuilder jsonbuilder=new JsonBuilder()
    def save()
    {
         def json = request.JSON
         if(json.token)
         {
            def token = json.token
            def userobj = User.findByAuthToken(token)
            if(userobj)
            {
                def statusobj = Status.get(1)
                def projectobj = Project.findByUser(userobj)
                if(projectobj)
                {
                    def ticketobj = Ticket.findByProject(projectobj)
                    if(ticketobj)
                    {
                        def history = new History()
                        history.comment = json.comment
                        history.user = userobj
                        history.status = statusobj
                        history.project = projectobj
                        history.ticket = ticketobj
                        println "In Controller "
                        def stat = historyService.save(history)
                        if(stat)
                        {
                            jsonbuilder.response(status:"ok",code:"200");
                            render jsonbuilder.toString();
                        }
                        else
                        {
                            jsonbuilder.response(error:"Problem in Saving History",code:"401");
                            render jsonbuilder.toString()
                        }
                    }
                    else
                    {
                        jsonbuilder.response(error:"Ticket is Not Available for this project",code:"401")
                        render jsonbuilder.toString() 
                    }
                }
                else
                {
                    jsonbuilder.response(error:"No Project is assigned to You",code:"401");
                    render jsonbuilder.toString() 
                }
         }
         else
         {
             jsonbuilder.response(error:"Invalid Token",code:"401")
             render jsonbuilder.toString() 
         }
      }
      else
      {
          jsonbuilder.response(error:"Token is Not Available",code:"401")
          render jsonbuilder.toString()
      }
   }
    def show()
    {
        def token = params.token
        if(token)
        {
            List l=  historyService.show()
            if(l)
                render l as JSON
            else
            {
                jsonbuilder.response(error:"History is Not Available",code:"401")
                render jsonbuilder.toString()
            }
                    
        }
        else
        {
            jsonbuilder.response(error:"Token is Not Available",code:"401")
            render jsonbuilder.toString()    
        }
    }
    def getById()
    {
        if(params.token)
        {
            print params.id
            def id = params.id
            println "hellooooooooooooooooooooooo"
            println id
            History history=  historyService.getById(id)
            if(history)
            {
                 render history as JSON
            }
            else
            {
                jsonbuilder.response(error:"History is not available",code:"401");
                render jsonbuilder.toString()    
            }
             
        }
        else
        {
            jsonbuilder.response(error:"Token is Not Available",code:"401");
            render jsonbuilder.toString()    
        }
    }
        
}
