/**
 * 
 */
package com.ten31f.mission.profiles;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author wraith
 *
 */
public class NotOffBoardCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		
		Environment environment = context.getEnvironment();
		return environment.acceptsProfiles(Profiles.of("!offboard"));
	}

}
