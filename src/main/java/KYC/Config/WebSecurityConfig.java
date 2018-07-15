/*
 * Reference: http://www.mkyong.com/spring-boot/spring-boot-spring-security-thymeleaf-example/
 * @11th July 2017 
 * REference: https://www.codebyamir.com/blog/user-account-registration-with-spring-boot
 * @15th July 2017
*/
package KYC.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {    
   @Autowired
   private UserDetailsService userDetailsService;
    
    
//    @Autowired
//    private AccessDeniedHandler accessDeniedHandler;
//    ***is this segment required?
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/webjars/**","/index","/", "/home", "/about", "/h2-console/*").permitAll()
                   //potentially to be added to ^
                .antMatchers("/admin/**").hasAnyAuthority("Admin","User")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/register").permitAll() //may need to be altered??
                .antMatchers("/confirm").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                //.and().exceptionHandling().accessDeniedPage("/403")
                ;

                //.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
   
    }
}