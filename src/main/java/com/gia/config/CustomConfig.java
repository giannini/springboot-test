package com.gia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义属性配置
 * 
 * @author jiangningyu
 *
 */
@Configuration
public class CustomConfig {


	@Value("${com.gia.test.switch}")
	private boolean isSwitchOn;

	public boolean isSwitchOn() {
		return isSwitchOn;
	}

	public void setSwitchOn(boolean isSwitchOn) {
		this.isSwitchOn = isSwitchOn;
	}


}
