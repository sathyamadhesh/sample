package com.neev.service

import grails.transaction.Transactional
import com.neev.trac.History
@Transactional
class HistoryService {

    def serviceMethod() {

    }
    def save(def history)
    {
        println "In Service Method"
        if(history.save())
            return true
        else
            return false
    }
    def show()
    {
        List list=History.list()
        if(list)
            return list
        else
            return null
    }
    def getById(def id)
    {
        def history=History.get(id)
        if(history)
               return history
        else
                return null
    }
    
}
