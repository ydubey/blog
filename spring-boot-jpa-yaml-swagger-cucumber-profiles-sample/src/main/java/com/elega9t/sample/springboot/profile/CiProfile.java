package com.elega9t.sample.springboot.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mock")
public class CiProfile {
}
