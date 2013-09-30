package com.neev.trac

class User 
{
    String name
    String email
    String password
    Date dateCreated
    Date lastUpdated
    String forgotPasswordToken
    String token
    String status
    String authToken
      
    static hasMany = [project:Project,ticket:Ticket,history:History]
    static constraints = 
    {
        password(minSize:5)
        name(blank:false)
        email(email:true,blank:false,unique:true)
        forgotPasswordToken(nullable:true)
        authToken(nullable:true)
    }
}
