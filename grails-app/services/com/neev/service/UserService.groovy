package com.neev.service
import java.security.MessageDigest
import grails.transaction.Transactional
import com.neev.trac.User


@Transactional
class UserService {

    def serviceMethod() 
    {

    }
    def addUser(User user)
    {
        def user1=User.findByEmail(user.email)
        if(!user1)
        {
            user.status = "inactive"
            def hashCode = md5(user.email,user.name)
            user.token = hashCode
            user.password = md5(user.password)
            if(!(user.save()))
            {
                user.errors.each{print it}
            }
            else
            {
                sendMail{
                    to "${user.email}"
                    subject "Verification Code"
                    body "Use This Verification Code to activate your account http://localhost:8080/trac/user/verifyingAccount?token=${hashCode}"
                }
                return true
            }
        }
        else
        return false
    
    }
  
    def md5(def email,def name){
        def digest = MessageDigest.getInstance("MD5")
        def text = "${email} ${name}"
        String md5hash1 = new BigInteger(1,digest.digest(text.getBytes())).toString(16).padLeft(32,"0")
        print "${md5hash1} is the Hash"
        return md5hash1
    }
    def md5(def password){
        def digest = MessageDigest.getInstance("MD5")
        def text = "${password}"
        String md5hash1 = new BigInteger(1,digest.digest(text.getBytes())).toString(16).padLeft(32,"0")
        print "${md5hash1} is the Hash"
        return md5hash1
    }
    def verifyingAccount(String token)
    {
        if(token)
        {
            def user = User.findByToken(token)
            if(user)
            {
                if(user.status == "ACTIVE")
                {
                    // user.setStatus("ACTIVE")
                    //user.save()
                    return "already verified"
                }
                else
                {
                    user.setStatus("ACTIVE")
                    user.save()
                    return "verified Succesfully"
                }
            }
            else
            {
                return "user not valid"
            }
        }
        else
        {
            return "invalid Token"
        }
        
    }
    def signIn(User user)
    {
        def user1 = User.findByEmail(user.email)
        if(user1)
        {
            def passwd = md5(user.password)
            if(user1.password == passwd)
            {
                return user1
            }
            else
            {
                return false
            }
        }
        else
        {
            return false
        }
    }
    def reset(def email)
    {
        def user = User.findByEmail(email)
        def date = new Date()
        def hashcode = md5(email,date)
        if(user && user.status=="ACTIVE")
        {
            sendMail
            {
                to "${email}"
                subject "Password Reset Link"
                body "Use This URL to Reset Your Password http://localhost:8080/trac/user/changePassword?token=${hashcode}"
            }
            user.forgotPasswordToken = hashcode
            user.save()
            return true
        }
        else
        {
            return false
        }
    }
    def changePassword(def token)
    {
        if(token)
        {
            def user = User.findByForgotPasswordToken(token)
            if(user)
            {
                user.forgotPasswordToken = null
                user.save()
                return user
            }
            else
            {
                return null
            }
        }
    }
    def update(def email,def password)
    {
        def user = User.findByEmail(email)
        String pwd=password
        if(user)
        {
            if(pwd.length()>=5)
            {
                def passwd = md5(password)
                user.password = passwd
                user.save()
                return true
            }
            else
            {
                return false
            }
        }
        
    }
    def modifyPassword(def old, def newp, def confirmp,def userobj)
    {
        def hash = md5(old)
        def hash1 = md5(newp)
        if(userobj.password==hash)
        {
            def user = User.findByEmail(userobj.email)
            user.password=hash1
            user.save()
            return true
        }
        else
        return false
    }
}
