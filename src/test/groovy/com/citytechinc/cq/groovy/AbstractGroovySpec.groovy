package com.citytechinc.cq.groovy

import com.citytechinc.cq.groovy.builders.NodeBuilder
import com.citytechinc.cq.groovy.builders.PageBuilder
import com.citytechinc.cq.groovy.metaclass.GroovyMetaClassRegistry
import com.citytechinc.cq.testing.AbstractRepositorySpec
import spock.lang.Shared

abstract class AbstractGroovySpec extends AbstractRepositorySpec {

	@Shared nodeBuilder

	@Shared pageBuilder

	def setupSpec() {
		GroovyMetaClassRegistry.registerMetaClasses()

		nodeBuilder = new NodeBuilder(session)
		pageBuilder = new PageBuilder(session)
	}

	def cleanup() {
		session.rootNode.nodes.findAll { !SYSTEM_NODE_NAMES.contains(it.name) }*.remove()
		session.save()
	}
}
