/* 
   Copyright 2011 Rolf Kulemann, Pascal Bockhorn

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.ssl.mina.log;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;


/**
 * Appender for {@link Log}
 * 
 * @author Rolf Kulemann, Pascal Bockhorn
 */
public class LogCatAppender extends AppenderSkeleton {

	protected Layout tagLayout;

	public LogCatAppender(final Layout messageLayout, final Layout tagLayout) {
		this.tagLayout = tagLayout;
		setLayout(messageLayout);
	}

	public LogCatAppender(final Layout messageLayout) {
		this(messageLayout, new PatternLayout("%c"));
	}

	public LogCatAppender() {
		this(new PatternLayout("%m%n"));
	}

	@Override
	protected void append(final LoggingEvent le) {
		switch (le.getLevel().toInt()) {
		case Level.TRACE_INT:
			if (le.getThrowableInformation() != null) {
			} else {
			}
			break;
		case Priority.DEBUG_INT:
			if (le.getThrowableInformation() != null) {
			} else {
			}
			break;
		case Priority.INFO_INT:
			if (le.getThrowableInformation() != null) {
			} else {
			}
			break;
		case Priority.WARN_INT:
			if (le.getThrowableInformation() != null) {
			} else {
			}
			break;
		case Priority.ERROR_INT:
			if (le.getThrowableInformation() != null) {
			} else {
			}
			break;
		case Priority.FATAL_INT:
			if (le.getThrowableInformation() != null) {
			} else {
			}
			break;
		}
	}

	@Override
	public void close() {
	}

	@Override
	public boolean requiresLayout() {
		return true;
	}

	public Layout getTagLayout() {
		return tagLayout;
	}

	public void setTagLayout(final Layout tagLayout) {
		this.tagLayout = tagLayout;
	}
}
