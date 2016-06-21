/**
 *  Whole House Fan Scheduler
 *
 *  Copyright 2016 Jeremy
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "Whole House Fan Scheduler",
    namespace: "jeremyfry",
    author: "Jeremy",
    description: "Allows you to schedule on and off times for furnace fan",
    category: "Convenience",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
	section("Which thermostat") {
		input "thermostat1", "capability.thermostat"
	}
	section("Turn off at what time") {
		input "offTime", "time", title: "Time?"
	}
    section("Turn on at what time") {
		input "onTime", "time", title: "Time?"
	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"
	initialize()
}

def offHandler(){
	thermostat1.fanAuto()
}

def onHandler(){
	thermostat1.fanOn()
}

def initialize() {
	schedule(onTime, onHandler)
    schedule(offTime, offHandler)
}

// TODO: implement event handlers