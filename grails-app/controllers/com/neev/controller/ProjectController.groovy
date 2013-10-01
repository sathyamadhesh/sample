package com.neev.controller
import grails.converters.*
import groovy.json.JsonBuilder
import com.neev.service.ProjectService
import com.neev.trac.Project
import com.neev.trac.User
import com.neev.trac.Status
import org.slf4j.Logger
import org.slf4j.LoggerFactory
class ProjectController 
{
    def projectService
    def index() { }
    JsonBuilder jsonbuilder=new JsonBuilder()
    final Logger logger = LoggerFactory.getLogger(ProjectController.class);
    def save()
    {
        logger.info("in project controller save project method")
        def json = request.JSON
        if(json.token)
        {
            logger.info("token found")
            def token = json.token
            def userobj = User.findByAuthToken(token)
            def statusobj = Status.get(1)
            if(userobj)
            {
                def project = new Project()
                project.Description = json.description
                project.name = json.name
                project.user=userobj
                project.status=statusobj
                logger.debug("Projectname {}. Description {}.", project.name, project.Description);
                if(!(Project.findByName(project.name)))
                {
                    
                    def stat = projectService.save(project) 
                    if(stat)
                    {
                        logger.info("project saved")
                        jsonbuilder.response(status:"ok",code:"200")
                        render jsonbuilder.toString() 
                    }
                    else
                    {
                         logger.info("project not saved")
                        jsonbuilder.response(error:"Problem in Saving",code:"401")
                        render jsonbuilder.toString()
                    }
                }
                else
                {
                     logger.info("project already exists")
                    jsonbuilder.response(error:"project already exists",code:"401")
                    render jsonbuilder.toString()
                }
            }
            else
            {
                 logger.info("no such user")
                jsonbuilder.response(error:"invalid token",code:"401").toString()
                render jsonbuilder.toString()
            }
        }
        else
        {
             logger.info("user is not authenticated")
            jsonbuilder.response(error:"token not available",code:"401").toString()
            render jsonbuilder.toString()
        }
           
    }
    def show()
    {
         logger.info("in project controller show project method")
        if(params.token)
        {
            def token = params.token
            def userobj = User.findByAuthToken(token)
            if(userobj)
            {
                List l=  projectService.show(userobj)
                if(l)
                render l as JSON
                else
                {
                    logger.info("No projects to show")
                    jsonbuilder.response(error:"no project is available",code:"401").toString()
                    render jsonbuilder.toString()
                }
            }
            else
            {
                logger.info("No such user")
                jsonbuilder.response(error:"invalid token",code:"401").toString()
                render jsonbuilder.toString()
            }
        }
        else
        {
            logger.info("User is not authenticated")
            jsonbuilder.response(error:"token not available",code:"401").toString()
            render jsonbuilder.toString()
        }
    }
    def getById()
    {
        logger.info("in project controller GetById project method")
        if(params.token)
        {
            def token = params.token
            def userobj = User.findByAuthToken(token)
            def id = params.id
          
            if(userobj)
            {
                logger.info("User found")
                Project project=  projectService.getById(id)
                if(project)
                render project as JSON
                else
                {
                    logger.info("no such project")
                    jsonbuilder.response(error:"requested project is not available",code:"401").toString()
                    render jsonbuilder.toString()
                }
            }
            else
            {
                logger.info("token not found")
                jsonbuilder.response(error:"invalid token",code:"401").toString()
                render jsonbuilder.toString()
            }
        }
        else
        {
            logger.info("User not authenticated")
            jsonbuilder.response(error:"token not available",code:"401").toString()
            render jsonbuilder.toString()
        }
    }
    def remove()
    {
        logger.info("in project controller remove project method")
        def token = params.token
        def userobj = User.findByAuthToken(token)
        if(userobj)
        {
            logger.info("User found")
            def id = params.id
            if(projectService.remove(id))
            {
                logger.info("project removed")
                jsonbuilder.response(status:"ok",code:"200")
                render jsonbuilder.toString()
            }
            else
            {
                logger.info("project not deleted")
                jsonbuilder.response(error:"cannot remove the project",code:"401")
                render jsonbuilder.toString()
            }   
        }
        else
        {
            logger.info("User not found")
            jsonbuilder.response(error:"invalid token",code:"401")
            render jsonbuilder.toString()
        }
    }
    
    
    def update()
    {
        logger.info("in project controller show project method")
        def id = params.id
        def json = request.JSON
        if(json.token)
        {
            logger.info("User authenticated")
            def token = json.token
            def userobj = User.findByAuthToken(token)
            def description=json.description
            if(userobj)
            {
                if(projectService.update(id,description))
                {
                     logger.info("project updated")
                    jsonbuilder.response(status:"ok",code:"200")
                    render jsonbuilder.toString()
                }
                else
                {
                    logger.info("project not updated")
                    jsonbuilder.response(status:"not updated",code:"401")
                    render jsonbuilder.toString()
                }
            }
            else
            {
                logger.info("user not found")
                jsonbuilder.response(status:"invalid token",code:"401")
                render jsonbuilder.toString()
            }
        }
        else
        {
            logger.info("User not authenticated")
            jsonbuilder.response(status:"token not available",code:"401")
            render jsonbuilder.toString()
        }
          
    }

}