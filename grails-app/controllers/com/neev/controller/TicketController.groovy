package com.neev.controller
import grails.converters.*
import groovy.json.JsonBuilder
import com.neev.trac.Ticket
import com.neev.trac.Project
import com.neev.trac.User
import com.neev.trac.Status
import com.neev.trac.History
import com.neev.service.HistoryService
class TicketController {
  def historyService
    def ticketService
    def index() { }
    JsonBuilder jsonbuilder=new JsonBuilder()
    def save()
    {
        print "We are in Ticket Controller"
        def json = request.JSON
        def token = json.token
        print token
        if(token)
        {
            print token
            def userobj = User.findByAuthToken(token)
            def statusobj = Status.get(1)
            if(userobj)
            {
                Project project=Project.get(params.id)
               
                //               def assignto = json.assignto
                //               def project1=Project.findByUserAndName(userobj,project)
                //               def use = Project.get(params.id)
                //               def proj=Project.findByUserAndName(userobj,use)
                //               //def proj = Project.get(params.id)
                //                //def projectobj = Project.findAllByUser(userobj)
                //                
                //                 if(proj)
                print userobj
                print userobj.id
                print "ggggggggggggggggggggggg"
                print project.user
                println "ffffffffffffffffffffffffff"
                println json.assignto
                println "assignnnnnnnnnnnnnn Tooooooooo"
                
                
                if(project.user==userobj)
                {
                    println "SomeThing is Happening"
                    def ticket = new Ticket()
                    ticket.name = json.name
                    ticket.description = json.description
                    ticket.priority = json.priority
                    def mail = User.get(json.assignto)
                    print mail.email
                    ticket.assignTo = User.get(json.assignto)
                    ticket.user = userobj
                    ticket.status = statusobj
                    ticket.project = project
                    def stat = ticketService.save(ticket,ticket.assignTo) 
                    if(stat)
                    {
                              
                        jsonbuilder.response(status:"ok",code:"200")
                        render jsonbuilder.toString()
                    }
                    else
                    {
                        jsonbuilder.response(error:"Problem in Saving",code:"401")
                        render jsonbuilder.toString()
                    }
                }
                else
                {
                    jsonbuilder.response(error:"Project is not available",code:"401");
                    render jsonbuilder.toString()
                }           
                   
            }
            else
            {
                jsonbuilder.response(error:"invalid token",code:"401") 
                render jsonbuilder.toString()
            }
        } 
        else
        {
            jsonbuilder.response(error:"token not available",code:"401");
            render jsonbuilder.toString()
        }
    }
    def show()
    {
        
        def token = params.token
        if(token)
        {
            def userobj = User.findByAuthToken(token)
            if(userobj)
            {
                List l=  ticketService.show()
                if(l)
                render l as JSON
                else
                {
                    jsonbuilder.response(error:"No ticket is raised by You",code:"401")
                    render jsonbuilder.toString()
                }
            }
            else
            {
                jsonbuilder.response(error:"Invalid token",code:"401")
                render jsonbuilder.toString()
            }
        }
        else
        {
            jsonbuilder.response(error:"token not available",code:"401")
            render jsonbuilder.toString()
        }
    }
    def getAllTicketsForAProject()
    {
        println ("in controller")
        def id = params.id
        def token = params.token
        if(token)
        {
            println ("in controller")
            def userobj = User.findByAuthToken(token)
            println userobj
            if(userobj)
            {
                def list= ticketService.getAllTicketsForAProject(id,userobj)
                if(list)
                {
                    render list as JSON
                }
                else
                {
                    jsonbuilder.response(error:"No List is found with the given Id",code:"401")
                    render jsonbuilder.toString()
                }
            
            }
            else
            {
                jsonbuilder.response(error:"Invalid token",code:"401")
                render jsonbuilder.toString()
            }
        }
        else
        {
            jsonbuilder.response(error:"token not available",code:"401")
            render jsonbuilder.toString()
        }
    }
    def getById()
    {
        def token = params.token
        if(token)
        {
            def userobj = User.findByAuthToken(token)
            def id = params.id
            if(userobj)
            {
                Ticket ticket=  ticketService.getById(id)
                if(ticket)
                render ticket as JSON
                else
                {
                    jsonbuilder.response(error:"ticket is not available by this account",code:"401")
                    render jsonbuilder.toString()
                }
            }
            else
            {
                jsonbuilder.response(error:"Invalid token",code:"401")
                render jsonbuilder.toString()
            }
        }
        else
        {
            jsonbuilder.response(error:"token not available",code:"401")
            render jsonbuilder.toString()
        }
    }
    def getAllTicketsByUser()
    {
        def token = params.token
        if(token)
        {
            def userobj = User.findByAuthToken(token)
            println userobj
            if(userobj)
            {
                def list= ticketService.getAllTicketsByUser(userobj)
                if(list)
                {
                    render list as JSON
                }
                else
                {
                    jsonbuilder.response(error:"No List is found with the given Id",code:"401")
                    render jsonbuilder.toString()
                }
            
            }
            else
            {
                jsonbuilder.response(error:"Invalid token",code:"401")
                render jsonbuilder.toString()
            }
        }
        else
        {
            jsonbuilder.response(error:"token not available",code:"401")
            render jsonbuilder.toString()
        }
    }
    def getAllTicketsByAssignTo()
    {
        //def id = params.id
        def token = params.token
        if(token)
        {
            def userobj = User.findByAuthToken(token)
            println userobj
            
            if(userobj)
            {
                def list= ticketService.getAllTicketsByAssignTo(userobj)
                if(list)
                {
                    render list as JSON
                }
                else
                {
                    jsonbuilder.response(error:"No List is found with the given Id",code:"401")
                    render jsonbuilder.toString()
                }
            
            }
            else
            {
                jsonbuilder.response(error:"Invalid token",code:"401")
                render jsonbuilder.toString()
            }
        }
        else
        {
            jsonbuilder.response(error:"token not available",code:"401")
            render jsonbuilder.toString()
        }
    }
    
    
    def getAllTicketsByAssignToProject()
    {
        println ("in controller")
        def token = params.token
        def projectid=params.id
        def projectobj=Project.get(projectid)
        println("printing project")
        println(projectobj)
        
        if(token)
        {
            def userobj = User.findByAuthToken(token)
            println userobj
            
            println(projectobj.user)
           
                
            
            if(userobj)
            {
                println("in calling service")
                if(projectobj.user==userobj)
                {
                    println("in calling service")
                    def list= ticketService.getAllTicketsByAssignToProject(projectobj,userobj)
                    if(list)
                    {
                        render list as JSON
                    }
                    else
                    {
                        jsonbuilder.response(error:"No List is found with the given Id",code:"401")
                        render jsonbuilder.toString()
                    }
                }
            
            }
            else
            {
                jsonbuilder.response(error:"Invalid token",code:"401")
                render jsonbuilder.toString()
            }
        }
        else
        {
            jsonbuilder.response(error:"token not available",code:"401")
            render jsonbuilder.toString()
        }
    }
    
    
    
    
    
    
    def remove()
    {
        def token = params.token
        if(token)
        {
            def userobj = User.findByAuthToken(token)
            def id=params.id
            if(userobj)
            {
                if(ticketService.remove(id))
                {
                    jsonbuilder.response(status:"OK",code:"200").toString()
                    render jsonbuilder.toString()
                }
                else
                {
                    jsonbuilder.response(error:"ticket is not available",code:"401")
                    render jsonbuilder.toString()
                }
            }
            else
            {
                jsonbuilder.response(error:"Invalid token",code:"401")
                render jsonbuilder.toString()
            }
        }
        else
        {
            jsonbuilder.response(error:"token not available",code:"401")
            render jsonbuilder.toString()
        }
    }
    def update()
    {
      
        def json = request.JSON
        def token = json.token
        if(token)
        {
            def userobj = User.findByAuthToken(token)
            def id = params.id
            def description=json.description
            
            
            
            
            
            if(userobj)
            {
                if(ticketService.update(id,description) )
                {
                    def history = new History()//for history updating
                    def statusobj = Status.get(1)
                    def projectobj = Project.findByUser(userobj)
                    def ticketobj = Ticket.findByProject(projectobj)
                    history.comment = json.comment
                    history.user = userobj
                    history.status = statusobj
                    history.project = projectobj
                    history.ticket = ticketobj
                    println "In Controller "
                    def stat = historyService.save(history)
                    if(stat)
                    {
                        jsonbuilder.response(status:"OK",code:"200")    
                        render jsonbuilder.toString()
                    }
                }
                else
                {
                    jsonbuilder.response(status:"Update Failed",code:"200").toString()    
                    render jsonbuilder.toString()
                }
            }
            else
            {
                jsonbuilder.response(status:"Invalid Token",code:"200").toString()
                render jsonbuilder.toString()    
            }
        }
        else
        {
            jsonbuilder.response(status:"Token is not available",code:"200").toString()
            render jsonbuilder.toString()    
        }
    }
}