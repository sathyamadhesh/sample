package com.neev.service

import grails.transaction.Transactional
import com.neev.trac.Project

@Transactional
class ProjectService {

    def serviceMethod() {

    }
 

    def save(def project)
    {
        println "in service"
        if(!(project.save()))
        {
            project.errors.each{print it}
            return false
        }
        else
            return true
    }
    def show(def userobj)
    {
        List list=Project.findAllByUser(userobj)
        if(list)
        return list
        else
        return null
    }
    def getById(def id)
    {
        def project=Project.get(id)
        if(project)
          return project
        else
          return null
    }
    def remove(def id)
    {
        println id
        def project=Project.get(id)
        if(project)
        {
            try
            {
                project.delete(flush:true) 
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
        print 'hello'
        def project= Project.get(id)
        print "${project.name}"
        project.Description = description
        if(project.save())
        return true
        else
        return null
    }
}

    
    

