package com.neev.controller
import com.neev.trac.User
import groovy.util.logging.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class UserController {
    def userService
    final Logger logger = LoggerFactory.getLogger(UserController.class);
    def addUser()
    {
       
        logger.info("in user controller adduser method")
        def user = new User()
        
        
        
        user.name = params.name
        user.email = params.email
        user.password = params.password
        logger.debug("Username {}. Password {}.", user.name, user.password);
        
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
        logger.info("in user controller verifying account method")
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
        logger.info("in user controller signin method")
        def user = new User()
        user.email=params.email
        user.password=params.password
         logger.debug("Username {}. Password {}.", user.email, user.password);
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
        logger.info("in user controller password reset method")
        def status=userService.reset(params.email)
         logger.debug("Username {}", user.email);
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
        logger.info("in user controller change password method")
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
        logger.info("in user controller update user method")
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
        logger.info("in user controller modifypassword method")
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
        logger.info("in user controller logout method")
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
