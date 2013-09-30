package com.neev.service
import grails.transaction.Transactional
import com.neev.trac.Ticket
import com.neev.trac.Project
import com.neev.trac.History
@Transactional
class TicketService 
{
    def serviceMethod() 
    {
    }
    def save(def ticket,def user1)
    {
        println "In Service Method"
        if(ticket.save())
        {
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
        println "This is the Show Method"
        List list=Ticket.list()
        if(list)
            return list
        else
            return null
    }
    def getById(def id)
    {
        def ticket=Ticket.get(id)
        if(ticket)
            return ticket
        else
           return null
    }
    def getAllTicketsByUser(def userobj)
    {
        def ticket = Ticket.findAllByUser(userobj)
        if(ticket)
            return ticket
        else
            return null
    }
    def getAllTicketsByAssignTo(def userobj)
    {
        def ticket = Ticket.findAllByAssignTo(userobj)
        if(ticket)
            return ticket
        else
            return null
    }
    def getAllTicketsByAssignToProject(def projectobj,def userobj)
    {
        println("in service")
        def ticket = Ticket.findAllByAssignToAndProject(userobj,projectobj)
        //def ticket = Ticket.findAllByAssignTo(projectobj)
        if(ticket){
        println(ticket)
            return ticket
        }
        else
            return null
    }
    def remove(def id)
    {
        print "thi sis service"
        println id
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
       
        def ticket= Ticket.get(id)
        ticket.description = description
        if(ticket.save())
            return true
        else
            return null
    }
    def getAllTicketsForAProject(def id,def userobj)
    {
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