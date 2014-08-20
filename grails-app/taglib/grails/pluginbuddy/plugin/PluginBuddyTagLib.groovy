package grails.pluginbuddy.plugin

class PluginBuddyTagLib {
	static namespace = "enduser"
	
	def grailsApplication
	
	def verifyAppVersion={attrs, body ->
		def gfolder=returnAppVersion()
		out << gfolder
	}
	
	private String returnAppVersion() {
		def gver=grailsApplication.metadata['app.grails.version']
		double verify=getGrailsVersion(gver)
		def gfolder="resources"
		if (verify >= 2.4 ) {
			gfolder="assets"
		}
		return gfolder
	}
	
	// Returns users grails version
	private getGrailsVersion(String appVersion) {
		if (appVersion && appVersion.indexOf('.')>-1) {
			int lastPos=appVersion.indexOf(".", appVersion.indexOf(".") + 1)
			double verify=appVersion.substring(0,lastPos) as double
		}
	}
}
