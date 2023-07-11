package tibba.othmane.pathfinder.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tibba.othmane.pathfinder.helpers.PathFinder;

@Configuration
public class Config {


    @Bean
    public PathFinder getPathFinder(){
        return new PathFinder();
    }
}
