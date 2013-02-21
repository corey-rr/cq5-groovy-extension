package com.citytechinc.cq.groovy.metaclass

import com.citytechinc.cq.groovy.AbstractGroovyToolsSpec
import spock.lang.Shared

class GroovyMetaClassRegistrySpec extends AbstractGroovyToolsSpec {

	@Shared node

	def setup() {
		def properties = [:]

		nodeBuilder.test(properties) {
			child1("nt:folder") {
				sub("nt:folder") {
					subsub("nt:folder")
				}
			}
			child2("sling:Folder")
			child3()
		}

		node = session.getNode("/test")
	}

	def "node: iterator"() {
		expect:
		node.iterator().size() == 3
		node*.name == ["child1", "child2", "child3"]
	}

	def "node: recurse"() {
		setup:
		def names = []

		node.recurse {
			names.add(it.name)
		}

		expect:
		names == ["test", "child1", "sub", "subsub", "child2", "child3"]
	}

	def "node: recurse with type"() {
		setup:
		def names = []

		node.recurse("nt:folder") {
			names.add(it.name)
		}

		expect:
		names == ["child1", "sub", "subsub"]
	}

	def "node: recurse with types"() {
		setup:
		def types = ["nt:folder", "sling:Folder"]
		def names = []

		node.recurse(types) {
			names.add(it.name)
		}

		expect:
		names == ["child1", "sub", "subsub", "child2"]
	}

	def "node: get"() {

	}

	def "node: set"() {

	}

	def "node: get or add node"() {

	}

	def "node: remove node"() {

	}
}
