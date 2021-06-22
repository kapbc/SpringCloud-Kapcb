package com.kapcb.ccc.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * <a>Title: RedissonProperties </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Redisson Properties <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/22 21:09
 */
@Data
@Builder
@Component
@ConfigurationProperties(prefix = "kapcb.redisson.lock", ignoreInvalidFields = true)
public class RedissonProperties {


}
