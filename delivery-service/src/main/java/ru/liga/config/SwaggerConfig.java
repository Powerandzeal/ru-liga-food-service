package ru.liga.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Приложение для курьеров еды",
                description = "Приложение курьеров для доставки еды из ресторанов", version = "1.0.0",
                contact = @Contact(
                        name = "Sergey Panin",
                        email = "seregapanin23703@gmail.com"

                )
        )
)
@SecurityScheme(
        name = "basic",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"

)
@Schema(name = "Конфигурация swagger документации")
public class SwaggerConfig {


}
