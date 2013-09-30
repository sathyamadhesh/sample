package com.neev.controller
import grails.converters.*
import groovy.json.JsonBuilder
import com.neev.service.ProjectService
import com.neev.trac.Project
import com.neev.trac.User
import com.neev.trac.Status

class ProjectController 
{
    def projectService
    def index() { }
    JsonBuilder jsonbuilder=new JsonBuilder()
    def save()
    {
        
//        print request.JSON;
//        render  jsonbuilder.response(status:"user authentication success and Data Save",code:"200").toString(); 
        def json = request.JSON
        if(json.token)
        {
            println "In Controller begining"
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
                if(!(Project.findByName(project.name)))
                {
                    println "In Controller "
                    def stat = projectService.save(project) 
                    if(stat)
                    {
                        jsonbuilder.response(status:"ok",code:"200")
                        render jsonbuilder.toString() 
                    }
                    else
                    {
                        jsonbuilder.response(error:"Problem in Saving",code:"401")
                        render jsonbuilder.toString()
                    }
                }
                else
                {
                    jsonbuilder.response(error:"project already exists",code:"401")
                    render jsonbuilder.toString()
                }
            }
            else
            {
                jsonbuilder.response(error:"invalid token",code:"401").toString()
                render jsonbuilder.toString()
            }
        }
        else
        {
            jsonbuilder.response(error:"token not available",code:"401").toString()
            render jsonbuilder.toString()
        }
           
    }
    def show()
    {
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
                    jsonbuilder.response(error:"no project is available",code:"401").toString()
                    render jsonbuilder.toString()
                }
            }
            else
            {
                jsonbuilder.response(error:"invalid token",code:"401").toString()
                render jsonbuilder.toString()
            }
        }
        else
        {
            jsonbuilder.response(error:"token not available",code:"401").toString()
            render jsonbuilder.toString()
        }
    }
    def getById()
    {
        if(params.token)
        {
            def token = params.token
            def userobj = User.findByAuthToken(token)
            def id = params.id
            println token
            println id
            if(userobj)
            {
                Project project=  projectService.getById(id)
                if(project)
                    render project as JSON
                else
                {
                    jsonbuilder.response(error:"requested project is not available",code:"401").toString()
                    render jsonbuilder.toString()
                }
            }
            else
            {
                jsonbuilder.response(error:"invalid token",code:"401").toString()
                render jsonbuilder.toString()
            }
        }
        else
        {
            jsonbuilder.response(error:"token not available",code:"401").toString()
            render jsonbuilder.toString()
        }
    }
    def remove()
    {
        def token = params.token
        def userobj = User.findByAuthToken(token)
        if(userobj)
        {
            def id = params.id
            if(projectService.remove(id))
            {
                jsonbuilder.response(status:"ok",code:"200")
                render jsonbuilder.toString()
            }
            else
            {
                jsonbuilder.response(error:"cannot remove the project",code:"401")
                render jsonbuilder.toString()
            }   
        }
        else
        {
            jsonbuilder.response(error:"invalid token",code:"401")
            render jsonbuilder.toString()
        }
    }
    
    
     def update()
    {
         def id = params.id
         def json = request.JSON
         if(json.token)
          {
            def token = json.token
            def userobj = User.findByAuthToken(token)
            def description=json.description
            if(userobj)
               {
                  if(projectService.update(id,description))
                  {
                      jsonbuilder.response(status:"ok",code:"200")
                      render jsonbuilder.toString()
                  }
                  else
                  {
                      jsonbuilder.response(status:"not updated",code:"401")
                      render jsonbuilder.toString()
                  }
               }
           else
               {
                   jsonbuilder.response(status:"invalid token",code:"401")
                   render jsonbuilder.toString()
               }
          }
          else
          {
              jsonbuilder.response(status:"token not available",code:"401")
              render jsonbuilder.toString()
          }
          
    }

}