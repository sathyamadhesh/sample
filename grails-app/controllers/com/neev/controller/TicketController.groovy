package com.neev.controller
import grails.converters.*
import groovy.json.JsonBuilder
import com.neev.trac.Ticket
import com.neev.trac.Project
import com.neev.trac.User
import com.neev.trac.Status
import com.neev.trac.History
import com.neev.service.HistoryService
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TicketController {
    def historyService
    def ticketService

    def index() { }

    JsonBuilder jsonbuilder=new JsonBuilder()
    final Logger logger = LoggerFactory.getLogger(TicketController.class);

    def save()
    {
        logger.info("in Ticket controller save ticket method")
     
        def json = request.JSON
        def token = json.token
     
        if(token)
        {
             logger.info("token found")
            def userobj = User.findByAuthToken(token)
            def statusobj = Status.get(1)
            if(userobj)
            {
                logger.info("User found")
                Project project=Project.get(params.id)
                if(project.user==userobj)
                {
                    logger.info("Project found for the User to Raise ticket")
                    def ticket = new Ticket()
                    ticket.name = json.name
                    ticket.description = json.description
                    ticket.priority = json.priority
                    def mail = User.get(json.assignto)
                    ticket.assignTo = User.get(json.assignto)
                    ticket.user = userobj
                    ticket.status = statusobj
                    ticket.project = project
     
                    def stat = ticketService.save(ticket,ticket.assignTo) 
                    if(stat)
                    {
                        logger.info("Ticket saved")      
                        jsonbuilder.response(status:"ok",code:"200")
                        render jsonbuilder.toString()
                    }
                    else
                    {
                        logger.info("Ticket not saved")      
                        jsonbuilder.response(error:"Problem in Saving",code:"401")
                        render jsonbuilder.toString()
                    }
                }
                else
                {
                    logger.info("Project not found")
                    jsonbuilder.response(error:"Project is not available",code:"401");
                    render jsonbuilder.toString()
                }           
                   
            }
            else
            {
                logger.info("Token not found")
                jsonbuilder.response(error:"invalid token",code:"401") 
                render jsonbuilder.toString()
            }
        } 
        else
        {
            logger.info("User not authenticated")
            jsonbuilder.response(error:"token not available",code:"401");
            render jsonbuilder.toString()
        }
    }
    def show()
    {
        logger.info("in Ticket controller show ticket method")
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
                    logger.info("No ticket is raised by You")      
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
        logger.info("in Ticket controller get all tickets for a project method")
        def id = params.id
        def token = params.token
        if(token)
        {
        
            def userobj = User.findByAuthToken(token)
           if(userobj)
            {
                def list= ticketService.getAllTicketsForAProject(id,userobj)
                if(list)
                {
                    render list as JSON
                }
                else
                {
                    logger.info("No tickets has been raised for this project")
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
        logger.info("in Ticket controller get ticket by its Id method")
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
                            logger.info("ticket not found with this Id")
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
        logger.info("in Ticket controller Get all tickets created by user method")
        def token = params.token
        if(token)
        {
            def userobj = User.findByAuthToken(token)
        
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
        
        logger.info("in Ticket controller get all tickets assigned to user method")
        def token = params.token
        if(token)
        {
            def userobj = User.findByAuthToken(token)
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
        logger.info("in Ticket controller get all tickets assigned to user for a project method")
        def token = params.token
        def projectid=params.id
        def projectobj=Project.get(projectid)
        if(token)
        {
            def userobj = User.findByAuthToken(token)
            if(userobj)
            {
                if(projectobj.user==userobj)
                {
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
        logger.info("in Ticket controller remove project method")
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
        logger.info("in Ticket controller update project method")
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
                    logger.info("ticket updated")
                    def history = new History()//for history updating
                    def statusobj = Status.get(1)
                    def projectobj = Project.findByUser(userobj)
                    def ticketobj = Ticket.findByProject(projectobj)
                    history.comment = json.comment
                    history.user = userobj
                    history.status = statusobj
                    history.project = projectobj
                    history.ticket = ticketobj
                    def stat = historyService.save(history)
                    if(stat)
                    {
                        logger.info("Added to Ticket history")
                        jsonbuilder.response(status:"OK",code:"200")    
                        render jsonbuilder.toString()
                    }
                }
                else
                {
                    logger.info("ticket not updated")
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