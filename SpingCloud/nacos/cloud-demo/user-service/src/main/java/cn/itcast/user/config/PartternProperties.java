package cn.itcast.user.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
// 属性注入到对象中
@ConfigurationProperties(prefix = "pattern")
public class PartternProperties {
    private String dataformat;
}
