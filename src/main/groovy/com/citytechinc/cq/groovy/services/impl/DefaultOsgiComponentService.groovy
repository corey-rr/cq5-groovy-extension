package com.citytechinc.cq.groovy.services.impl

import com.citytechinc.cq.groovy.services.OsgiComponentService
import org.apache.felix.scr.ScrService
import org.apache.felix.scr.annotations.Reference
import org.slf4j.LoggerFactory

class DefaultOsgiComponentService implements OsgiComponentService {

    static final def LOG = LoggerFactory.getLogger(DefaultOsgiComponentService)

    @Reference
    ScrService scrService

    @Override
    def doWhileDisabled(String componentClassName, Closure closure) {
        def component = scrService.components.find { it.className == componentClassName }

        def result = null

        if (component) {
            component.disable()

            LOG.info "doWhileDisabled() disabled component = {}", componentClassName

            try {
                result = closure.call()
            } finally {
                component.enable()

                LOG.info "doWhileDisabled() enabled component = {}", componentClassName
            }
        } else {
            LOG.error "doWhileDisabled() component not found = {}", componentClassName

            result = closure.call()
        }

        result
    }
}
