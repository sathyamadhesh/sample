package com.neev.service

import grails.transaction.Transactional
import com.neev.trac.Project
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Transactional
class ProjectService {
    final Logger logger = LoggerFactory.getLogger(ProjectService.class);
    def serviceMethod() {

    }
 

    def save(def project)
    {
        logger.info("in project service save project method")
      
        if(!(project.save()))
        {
            logger.info("project not saved")
            project.errors.each{print it}
            return false
        }
        else
        return true
    }
    def show(def userobj)
    {
        logger.info("in project service show all project by user method")
        List list=Project.findAllByUser(userobj)
        if(list)
            return list
        else
            return null
    }
    def getById(def id)
    {
        logger.info("in project service show project by id method")
        def project=Project.get(id)
        if(project)
            return project
        else
            return null
    }
    def remove(def id)
    {
        logger.info("in project service remove project by id method")
      
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
        logger.info("in project service update project by id method")
        def project= Project.get(id)
        project.Description = description
        if(project.save())
            return true
        else
            return null
    }
}

    
    

