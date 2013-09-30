package com.neev.trac

class Ticket {

    Status status
    Project project
    User user
    User assignTo
    String name
    String description
    Date dateCreated
    Date lastUpdated
    String priority
    
    //PROJECT_ID
    //STATUS_ID
    //USER_ID
    //static hasOne = [status:Status]
    static belongsTo = [user:User,project:Project,status:Status,assignTo:User]
    
    static constraints = {
    }
}
