package com.neev.service
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import grails.transaction.Transactional
import com.neev.trac.History
@Transactional
class HistoryService {
    final Logger logger = LoggerFactory.getLogger(HistoryService.class);
    def serviceMethod() {

    }
    def save(def history)
    {
        logger.info("in history service save method")
        if(history.save())
        return true
        else
        return false
    }
    def show()
    {
        logger.info("in history service show method")
        List list=History.list()
        if(list)
        return list
        else
        return null
    }
    def getById(def id)
    {
        logger.info("in history service get by Id method")
        def history=History.get(id)
        if(history)
        return history
        else
        return null
    }
    
}
