package com.neev.service
import grails.transaction.Transactional
import com.neev.trac.Ticket
import com.neev.trac.Project
import com.neev.trac.History
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Transactional
class TicketService 
{
final Logger logger = LoggerFactory.getLogger(TicketService.class);
    def serviceMethod() 
    {
    }
    def save(def ticket,def user1)
    {
        logger.info("in Ticket Service save ticket method")
        if(ticket.save())
        {
                    logger.info("ticket saved and mail sent")
             sendMail{
                    to "${user1.email}"
                    subject "Regarding assignment of a ticket"
                    body "You r getting this email since a ticket has been assigned to you"
                }
             return true
        }
       else
        return false
        
        
    }
    def show()
    {
        logger.info("in Ticket Service show all ticket method")
        List list=Ticket.list()
        if(list)
            return list
        else
            return null
    }
    def getById(def id)
    {
        logger.info("in Ticket Service get by ticket id method")
        def ticket=Ticket.get(id)
        if(ticket)
            return ticket
        else
           return null
    }
    def getAllTicketsByUser(def userobj)
    {
        logger.info("in Ticket Service get all tickets raised by user method")
        def ticket = Ticket.findAllByUser(userobj)
        if(ticket)
            return ticket
        else
            return null
    }
    def getAllTicketsByAssignTo(def userobj)
    {
        logger.info("in Ticket Service get all tickets assigned to user method")
        def ticket = Ticket.findAllByAssignTo(userobj)
        if(ticket)
            return ticket
        else
            return null
    }
    def getAllTicketsByAssignToProject(def projectobj,def userobj)
    {
        logger.info("in Ticket Service get all tickets for a project method")
       def ticket = Ticket.findAllByAssignToAndProject(userobj,projectobj)
        //def ticket = Ticket.findAllByAssignTo(projectobj)
        if(ticket){
       return ticket
        }
        else
            return null
    }
    def remove(def id)
    {
        logger.info("in Ticket Service remove ticket method")
       def ticket=Ticket.get(id)
         if(ticket)
        {
            
            try
            {
                ticket.delete(flush:true) 
                return true
            }
            catch(e)
            {
                return false
            }
        }
        else
            return false
           
    }
    def update(def id,def description)
    {
       logger.info("in Ticket Service update ticket method")
        def ticket= Ticket.get(id)
        ticket.description = description
        if(ticket.save())
            return true
        else
            return null
    }
    def getAllTicketsForAProject(def id,def userobj)
    {
        logger.info("in Ticket Service get all tickets for a project method")
        def project =Project.get(id)  
        if(project)
        {
           List list = Ticket.findAllByUserAndProject(userobj,project)
           if(list)
                return list
           else
                return null
        }
        else
            return null
    }
}