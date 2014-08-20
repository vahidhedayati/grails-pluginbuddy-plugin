pluginbuddy 0.1
=========

Grails plugin buddy. This does not deserve to be a plugin but it saves repeating a few lines of code each and every time and can be very useful for writing views depending on end users default grails application. As a plugin developer it will require some repeat calls in different styles but can ensure your plugin views are compatible with resourcees assets based sites.

Once it is release I will remove some of my own trials/tribulations and complexer methods of doing the same thing and refer to the plugin in other plugins and clean up gsp pages..



Dependency :
```groovy
	compile ":pluginbuddy:0.1", { export=false } 
```


Add this to  your plugin dependency within your BuildConfig.groovy of your own plugin,

I am going to use the work from the mailing list as an example:
[grails-app/views/mailingListSchedule/list2.gsp](https://github.com/vahidhedayati/mailinglist/blob/master/grails-app/views/mailingListSchedule/list2.gsp)

How to use within your views:

```gsp

<g:if test="${!request.xhr }">
	<meta name='layout' content="main"/>
</g:if>
<g:else>
	<g:if test="${pluginBuddy.verifyAppVersion().equals('resources')}">
		<meta name='layout' content="mailingListMini"/>
	</g:if>	
	<g:else>
		<meta name='layout' content="mailingListMiniAssets"/>
	</g:else>
</g:else>
```

Above checks user request type if non ajax by default it will fall back to main within end users main application and apply their layout. Otherwise now this being their call as ajax method. Alternatively if you remove the top if statement and else brackts from the top bit the next bit is what the plugin calculates for you. You could just have that logic and remove the rest - taking control of entire page.


### resources layout

Our resources sitemesh - typicall with defer so really for ajax call within another layout.. As you notice no scripts in here css etc - feel free to follow the traditional style of your resources calls on this page:

[grails-app / views / layouts / mailingListMini.gsp](https://github.com/vahidhedayati/mailinglist/blob/master/grails-app/views/layouts/mailingListMini.gsp)

```gsp
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> 
<html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>

<g:layoutHead>
		</g:layoutHead>
		<r:layoutResources />
	</head>
	<body>
		<g:layoutBody/>
		<r:layoutResources  disposition="defer"/>
	</body>
</html>


```

### assets layout

[grails-app/views/layouts/mailingListMiniAssets.gsp](https://github.com/vahidhedayati/mailinglist/blob/master/grails-app/views/layouts/mailingListMiniAssets.gsp)
```gsp
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<g:layoutHead/>
	</head>
	<body>
		<g:layoutBody/>
	</body>
</html>
```


## Tedious repeated work slightly easier:

```gsp
<g:if test="${pluginBuddy.verifyAppVersion().equals('resources')}">
<img src="${resource(dir: 'images', file: 'run.png')}" data-tooltip="${message(code: 'default.run.job.label', default: 'Run job')}" alt="${message(code: 'default.run.job.label', default: 'Run job')}"/>
</g:if>
<g:else>
<asset:image src="run.png" alt="Grails" data-tooltip="${message(code: 'default.stop.cancel.label', default: 'Stop/Cancel Schedule')}" alt="${message(code: 'default.stop.cancel.label', default: 'Stop/Cancel Schedule')}"/>
</g:else>
```

Another example loading css/js per app style....
```gsp


<g:if test="${pluginBuddy.verifyAppVersion().equals('resources')}">
	<link rel="stylesheet" href="${createLink(uri: '/css/bootstrap.min.css')}" type="text/css">
	<script src="${createLink(uri: '/js/bootstrap.min.js')}" type="text/javascript"></script>
</g:if>
<g:else>
 	<asset:stylesheet href="bootstrap.min.css" />
	<asset:javascript src="bootstrap.min.js"/>
	<asset:javascript src="jquery-ui.min.js"/>
</g:else>

```


Taglib call: (derived bove calls)

```
	<enduser:verifyAppVersion/> returns resources or assets


OR within your own taglib:

```
	def gfolder=returnAppVersion()
```

This will return assets/resources.



More primitive call version returned as double:

```
		def gver=grailsApplication.metadata['app.grails.version']
		double verify=getGrailsVersion(gver)
```




