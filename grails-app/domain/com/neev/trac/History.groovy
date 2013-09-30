package com.neev.trac

class History {
    //TICKET_ID
    //STATUS_ID
    Ticket ticket
    Status status
    User user
    Project project
    String comment
    Date lastUpdated
//    static hasOne = [status : Status]
    static belongsTo = [ticket:Ticket,user:User,status:Status,project:Project]
    static constraints = {
    }
}
