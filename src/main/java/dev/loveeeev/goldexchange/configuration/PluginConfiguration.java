package dev.loveeeev.goldexchange.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.buktify.configurate.annotation.Configuration;
import org.buktify.configurate.annotation.Variable;

@Getter
@Configuration(
        fileName = "configuration.yml",
        filePath = "%plugin_root%/%filename%"
)
@NoArgsConstructor
@SuppressWarnings("FieldMayBeFinal")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PluginConfiguration {

    @Variable("exchange.rate")
    Integer exchangeRate = 10;
}
