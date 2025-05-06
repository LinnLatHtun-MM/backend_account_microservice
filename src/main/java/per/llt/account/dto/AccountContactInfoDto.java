package per.llt.account.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * ConfigurationProperties Usage need to add this annotation in AccountAppliation
 *
 * @EnableConfigurationProperties(value = {AccountContactInfoDto.class})
 **/
@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class AccountContactInfoDto {

    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}
