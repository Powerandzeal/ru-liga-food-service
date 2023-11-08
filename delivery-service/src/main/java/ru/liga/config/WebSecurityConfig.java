package ru.liga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {

//    @Bean
//    public SecurityFilterChain configure1(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests().antMatchers("/courier/create-courier").authenticated()
//                .anyRequest().permitAll()
//
//                .and()
//                .httpBasic();
//
//        return http.build();
//    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests().antMatchers("/courier/create-courier").permitAll()
                .anyRequest().authenticated()

                .and()
                .httpBasic();

        return http.build();
    }

//    }
//    @Bean
//    public SecurityFilterChain configure2(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(auth->{
//            auth.anyRequest().permitAll();
//        }).httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }

//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .antMatchers("/courier/create-courier").permitAll() // Разрешить доступ к странице регистрации
//                        .antMatchers("/login").permitAll() // Разрешить доступ к странице входа
//
//                        // Запретить доступ к другим URL-адресам, если пользователь не аутентифицирован
//                        .anyRequest().authenticated()
//                )
//                .formLogin(Customizer.withDefaults()); // Настройка формы входа
//
//        return http.build();
//    }

//@Bean
//public FormLoginConfigurer<HttpSecurity> configure(HttpSecurity http) throws Exception {
//   return  http.authorizeRequests()
//           .antMatchers("/admin/**").hasRole( "ADMIN")
//           .antMatchers("/courier/**").hasAnyRole("Courier")
//           .antMatchers("/courier/create-courier").permitAll()
//           .and().formLogin();
//}
//@Bean
//public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//
//
////    return http.csrf().disable()
////            .authorizeHttpRequests(Customizer.withDefaults()).antMatchers("/tickets/buyTicket").authenticated()
////            .antMatchers("/tickets/deleteTicket").hasRole("ADMIN")
////            .antMatchers("/user/updateUser").hasRole("ADMIN")
////            .antMatchers("/carrier/**").hasRole("ADMIN")
////            .antMatchers("/route/**").hasRole("ADMIN")
////            .and().formLogin().and().build();
//}


        @Bean
        public PasswordEncoder passwordEncoder () {
            return new BCryptPasswordEncoder();
        }

}

