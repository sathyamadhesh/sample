package com.neev.trac

class Project {
    
    User user
    Status status
    String name
    Date dateCreated
    Date lastUpdated
    String Description
    //    Date created
    //    Date updated
    //    STATUS_ID
    //    USER_ID
    //    static hasOne = [status:Status]
    static belongsTo = [user:User,status:Status]
    static hasMany = [user:User,ticket:Ticket]
    static constraints = {
        
        
    }
}
