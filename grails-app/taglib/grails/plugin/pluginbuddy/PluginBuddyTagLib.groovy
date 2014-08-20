package grails.plugin.pluginbuddy

class PluginBuddyTagLib {
	static namespace = "enduser"
	
	def pluginbuddyService
	
	def verifyAppVersion={attrs, body ->
		def gfolder=pluginbuddyService.returnAppVersion()
		out << gfolder
	}
	
	
}
