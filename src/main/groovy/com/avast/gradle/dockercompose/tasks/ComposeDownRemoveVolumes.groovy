package com.avast.gradle.dockercompose.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction

import com.avast.gradle.dockercompose.ComposeSettings

public class ComposeDownRemoveVolumes extends DefaultTask {
	ComposeSettings settings
	
	ComposeDownRemoveVolumes() {
		group = 'docker'
		description = '''Removes volumes of docker-compose project and 
						stops/removes associated containers if necessary'''
	}
	
	@TaskAction
	void downRemoveVolumes() {
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
