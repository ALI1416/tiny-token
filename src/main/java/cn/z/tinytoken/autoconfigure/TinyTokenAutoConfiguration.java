package cn.z.tinytoken.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <h1>轻量级权限认证自动配置</h1>
 *
 * <p>
 * createDate 2023/07/24 10:09:31
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(TinyTokenProperties.class)
public class TinyTokenAutoConfiguration {

    /**
     * 日志实例
     */
    private static final Logger log = LoggerFactory.getLogger(TinyTokenAutoConfiguration.class);
    /**
     * 前缀默认值{@value}
     */
    private static final String DEFAULT_PREFIX = "tinytoken";
    /**
     * 过期时间默认值{@value}秒(30天)
     */
    private static final long DEFAULT_TIMEOUT = 2592000L;

    /**
     * 轻量级权限认证配置属性
     */
    private final TinyTokenProperties tinyTokenProperties;

    /**
     * 构造函数(自动注入)
     *
     * @param tinyTokenProperties TinyTokenProperties
     */
    public TinyTokenAutoConfiguration(TinyTokenProperties tinyTokenProperties) {
        this.tinyTokenProperties = tinyTokenProperties;
    }

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        String msg = "TinyToken配置：前缀PREFIX ";
        if (tinyTokenProperties.getPrefix() == null) {
            tinyTokenProperties.setPrefix(DEFAULT_PREFIX);
            msg += DEFAULT_PREFIX + " (默认)";
        } else {
            msg += tinyTokenProperties.getPrefix() + " ";
        }
        msg += "，过期时间TIMEOUT ";
        if (tinyTokenProperties.getTimeout() == null) {
            tinyTokenProperties.setTimeout(DEFAULT_TIMEOUT);
            msg += DEFAULT_TIMEOUT + " (秒)[30天](默认)";
        } else {
            msg += tinyTokenProperties.getTimeout() + " (秒)";
        }
        log.info(msg);
    }

}
