package per.llt.account.dto;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * ConfigurationProperties Usage need to add this annotation in AccountAppliation
 *
 * @EnableConfigurationProperties(value = {AccountContactInfoDto.class})
 **/
@ConfigurationProperties(prefix = "accounts")
public record AccountContactInfoDto(
        String message, Map<String, String> contactDetails, List<String> onCallSupport) {
}
