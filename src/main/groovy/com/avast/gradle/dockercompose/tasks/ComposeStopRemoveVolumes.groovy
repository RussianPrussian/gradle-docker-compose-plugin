package com.avast.gradle.dockercompose.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction

import com.avast.gradle.dockercompose.ComposeSettings

public class ComposeStopRemoveVolumes extends DefaultTask {
	ComposeSettings settings
	
	ComposeStopRemoveVolumes() {
		group = 'docker'
		description = '''Removes volumes of docker-compose project and 
						stops associated containers if necessary'''
	}
	
	@TaskAction
	void removeVolumes() {
		settings.serviceInfoCache.clear()
		String[] args = ['down', '-v']
		def composeLog = null
		if(settings.composeLogToFile) {
		  logger.debug "Logging docker-compose removeVolumes to : ${settings.composeLogToFile}"
		  settings.composeLogToFile.parentFile.mkdirs()
		  composeLog = new FileOutputStream(settings.composeLogToFile, true)
		}
		settings.composeExecutor.executeWithCustomOutputWithExitValue(composeLog, args)
	}
}
