package com.citytechinc.cq.groovy.services

interface OsgiComponentService {

    def doWhileDisabled(String componentClassName, Closure closure)
}