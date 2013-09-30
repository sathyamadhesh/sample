package com.neev.controller
import com.neev.trac.User

class UserController {
    def userService
    def addUser()
    {
        println 'hello'
        
        def user = new User()
        
        
        
        user.name = params.name
        user.email = params.email
        user.password = params.password
       
        
            if(userService.addUser(user))
            {
                flash.message = "Registered Successfully!!Check Your Mail for a Link to verify Your Account"
                render(view:"/registration")
            }
            else
            {
                flash.message = "Email is alrady there!please give another"
                render(view:"/registration")
            }
        
       
    }
    def verifyingAccount()
    {
        def status = userService.verifyingAccount(params.token)
        switch(status)
        {
        case "already verified" :
            flash.message = "Email is already verified you can now login"
            render(view:"/registration")
            break;
        case "verified Succesfully" : 
            flash.message = "Email is verified Succesfully you can now login"
            render(view:"/registration")
            break;
        case "user not valid" :
            flash.message = "Invalid User"
            render(view:"/registration")
            break;
        case "invalid Token" : 
            flash.message = "Token is invalid"
            render(view:"/registration")
            break;
        default :
            break;
        }
    }
    def signIn()
    {
        def user = new User()
        user.email=params.email
        user.password=params.password
        def user1 = userService.signIn(user)
        if(user1)
        {
            def date = new Date()
            def tok = userService.md5(user1.email,date)
            user1.authToken=tok
            user1.save()
            render(view:"/homepage",model:[token:tok])
        }
        else
        {
            flash.message = "Email Id or Password is Incorrect"
            render(view:"/registration")
        }
    }
    def forgotPassword()
    {
        render(view:"/user/enterEmail")
    }
    def reset()
    {
        def status=userService.reset(params.email)
        if(status)
        {
            flash.message = "Email Has been Sent to Your Mail"
            render(view:"/registration")
        }
        else
        {
            flash.message = "Emai is not valid or Account is Not Activated"
            render(view:"/user/enterEmail")
        }
    }
    def changePassword()
    {
        def user = userService.changePassword(params.token)
        if(user)
        {
            render(view:"/user/newpassword",model:[email:user.email])
        }
        else
        {
            flash.message = "Token is Invalid for reseting Password"
            render(view:"/registration")
        }
    }
    def update()
    {
        def status = userService.update(params.email,params.password)
        if(status)
        {
            flash.message = "You Can Now Login with New Password"
            render(view:"/registration")
        }
        else
        {
            flash.message = "Password Size must be atleast 5"
            render(view:"/user/newpassword",model:[email:params.email])
        }
    }
    def modifyPassword()
    {
        def token=params.token
        
        User userobj = User.findByAuthToken(token)
        // def email = User.findByEmail(userobj.email)
        if(userobj&&token)
        {
            //def user = new User()
            def status = userService.modifyPassword(params.oldpassword,params.newpassword,params.confirmpassword,userobj)
            if(status)
            {
                flash.message = "Password Changed Successfully"
                render(view:"/homepage",model:[token:token])
                
            }
            else
            {
                flash.message = "You have entered the wrong Password"
                render(view:"/homepage",model:[token:token])
            }
        }
    }
    def logout()
    {
        def token=params.token
        def user=User.findByAuthToken(token)
        if(user)
        {
            user.authToken=null
            user.save()
        
            // session.user=null
            render(view:"/registration")
        }
    }
}
